/*     */ package br.com.uol.pagseguro.plugpag.network;
/*     */ 
/*     */ import android.support.annotation.NonNull;
/*     */ import android.util.Log;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URLConnection;
/*     */ import java.security.InvalidParameterException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UrlRequest
/*     */ {
/*     */   private static final String LOG_TAG = "PlugPag_Network";
/*     */   private static final String METHOD_GET = "GET";
/*     */   private static final String METHOD_POST = "POST";
/*     */   private static final String HEADER_LOCATION = "Location";
/*     */   private static final int BUFFER_SIZE = 4096;
/*  29 */   private byte[] mInjectedResponse = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean mFollowRedirects = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UrlRequest(boolean bypassSsl) {
/*  42 */     if (bypassSsl) {
/*  43 */       bypassSsl();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UrlRequest() {
/*  51 */     this(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean mustRedirect(@NonNull URLConnection connection) throws IOException {
/*  66 */     boolean redirect = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     redirect = (this.mFollowRedirects && connection != null && connection instanceof HttpURLConnection && isRedirectCode(((HttpURLConnection)connection).getResponseCode()));
/*     */     
/*  73 */     return redirect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isRedirectCode(int code) {
/*  83 */     return (code == 301 || code == 302);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] getResponseData(@NonNull URLConnection connection) {
/*  95 */     byte[] response = null;
/*  96 */     BufferedInputStream bis = null;
/*  97 */     ByteArrayOutputStream baos = null;
/*  98 */     byte[] buffer = null;
/*  99 */     int bytesRead = -1;
/*     */     
/* 101 */     if (this.mInjectedResponse == null) {
/*     */       
/*     */       try {
/* 104 */         buffer = new byte[4096];
/* 105 */         bis = new BufferedInputStream(connection.getInputStream());
/* 106 */         baos = new ByteArrayOutputStream();
/*     */ 
/*     */         
/* 109 */         while ((bytesRead = bis.read(buffer)) != -1) {
/* 110 */           baos.write(buffer, 0, bytesRead);
/*     */         }
/*     */ 
/*     */         
/* 114 */         baos.flush();
/* 115 */         response = baos.toByteArray();
/*     */ 
/*     */         
/* 118 */         baos.close();
/* 119 */         bis.close();
/* 120 */       } catch (IOException e) {
/* 121 */         Log.e("PlugPag_Network", "Stream read exception", e);
/*     */       } 
/*     */     } else {
/*     */       
/* 125 */       response = (byte[])this.mInjectedResponse.clone();
/*     */     } 
/*     */     
/* 128 */     return response;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFollowRedirects(boolean follow) {
/* 141 */     this.mFollowRedirects = follow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void injectResponse(@NonNull byte[] response) {
/* 155 */     if (response == null) {
/* 156 */       throw new InvalidParameterException("[UrlRequest] Mock response cannot be null");
/*     */     }
/*     */     
/* 159 */     this.mInjectedResponse = (byte[])response.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void bypassSsl() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void fillResponse(NetworkResponseOld response, HttpURLConnection connection) {
/* 214 */     byte[] responseBytes = null;
/*     */     
/* 216 */     if (connection != null)
/*     */       try {
/* 218 */         responseBytes = getResponseData(connection);
/* 219 */         response.setResponseCode(connection.getResponseCode());
/* 220 */         response.setResponseMessage(connection.getResponseMessage());
/* 221 */         response.setResponseContent(responseBytes);
/* 222 */       } catch (Exception e) {
/* 223 */         Log.e("PlugPag_Network", "Error while filling NetworkResponseOld object", e);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/network/UrlRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */