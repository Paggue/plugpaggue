/*     */ package br.com.uol.pagseguro.plugpag.network.http;
/*     */ 
/*     */ import android.support.annotation.NonNull;
/*     */ import android.support.annotation.Nullable;
/*     */ import android.text.TextUtils;
/*     */ import android.util.Log;
/*     */ import br.com.uol.pagseguro.plugpag.network.NetworkCommunication;
/*     */ import br.com.uol.pagseguro.plugpag.network.NetworkContext;
/*     */ import br.com.uol.pagseguro.plugpag.network.NetworkResponse;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.security.KeyManagementException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.security.SecureRandom;
/*     */ import java.security.cert.X509Certificate;
/*     */ import javax.net.ssl.HostnameVerifier;
/*     */ import javax.net.ssl.HttpsURLConnection;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLSession;
/*     */ import javax.net.ssl.TrustManager;
/*     */ import javax.net.ssl.X509TrustManager;
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
/*     */ public class HttpCommunication
/*     */   implements NetworkCommunication<HttpContext, HttpResponse>
/*     */ {
/*     */   public static final String LOG_TAG = "PlugPag-Network-HTTP";
/*     */   public static final String METHOD_GET = "GET";
/*     */   public static final String METHOD_POST = "POST";
/*     */   private static final String HEADER_LOCATION = "Location";
/*     */   private static final int BUFFER_SIZE = 4096;
/*     */   private static final String PROTOCOL_HTTPS = "https";
/*  46 */   private HttpResponse mInjectedResponse = null;
/*     */ 
/*     */ 
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HttpResponse send(HttpContext context) {
/*  66 */     HttpResponse response = null;
/*     */     
/*  68 */     if (context != null) {
/*  69 */       if (context.bypassSsl) {
/*  70 */         bypassSsl();
/*     */       }
/*     */       
/*  73 */       this.mFollowRedirects = context.followRedirects;
/*     */       
/*  75 */       if ("POST".equals(context.method)) {
/*  76 */         response = doPost(context.host, context.content);
/*  77 */       } else if ("GET".equals(context.method)) {
/*  78 */         response = doGet(context.host);
/*     */       } 
/*     */       
/*  81 */       if (this.mInjectedResponse != null) {
/*  82 */         response = this.mInjectedResponse;
/*     */       }
/*     */     } 
/*     */     
/*  86 */     return response;
/*     */   }
/*     */ 
/*     */   
/*     */   public void injectMockResponse(HttpResponse mockResponse) {
/*  91 */     this.mInjectedResponse = mockResponse;
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
/*     */   private HttpContext createDefaultHttpContext() {
/* 104 */     HttpContext context = null;
/*     */     
/* 106 */     context = new HttpContext();
/* 107 */     context.bypassSsl = false;
/* 108 */     context.followRedirects = true;
/* 109 */     context.method = "POST";
/*     */     
/* 111 */     return context;
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
/*     */   private HttpResponse doGet(@NonNull String url) {
/* 125 */     HttpResponse response = null;
/* 126 */     String currentUrl = null;
/* 127 */     URL urlObj = null;
/* 128 */     HttpURLConnection connection = null;
/* 129 */     boolean redirectionRequested = true;
/* 130 */     String requestMethod = null;
/*     */     
/*     */     try {
/* 133 */       currentUrl = url;
/* 134 */       requestMethod = "GET";
/*     */ 
/*     */       
/* 137 */       response = new HttpResponse();
/* 138 */       response.setRequestMethod(requestMethod);
/*     */       
/* 140 */       while (redirectionRequested) {
/*     */         
/* 142 */         response.addRequestUrl(currentUrl);
/*     */ 
/*     */         
/* 145 */         urlObj = new URL(currentUrl);
/* 146 */         connection = (HttpURLConnection)urlObj.openConnection();
/* 147 */         connection.setRequestMethod(requestMethod);
/*     */         
/* 149 */         connection.setUseCaches(false);
/* 150 */         connection.connect();
/* 151 */         redirectionRequested = mustRedirect(connection);
/*     */ 
/*     */         
/* 154 */         if (redirectionRequested) {
/* 155 */           currentUrl = connection.getHeaderField("Location");
/* 156 */           connection.disconnect();
/*     */         } 
/*     */       } 
/*     */       
/* 160 */       fillResponse(response, connection);
/* 161 */     } catch (Exception e) {
/* 162 */       Log.e("PlugPag-Network-HTTP", "Request (GET) exception", e);
/* 163 */       response = null;
/*     */     } finally {
/* 165 */       if (connection != null) {
/* 166 */         connection.disconnect();
/*     */       }
/*     */     } 
/*     */     
/* 170 */     return response;
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
/*     */   public HttpResponse doPost(@NonNull String url, @Nullable String extraContent) {
/* 185 */     HttpResponse response = null;
/* 186 */     String currentUrl = null;
/* 187 */     URL urlObj = null;
/* 188 */     HttpURLConnection connection = null;
/* 189 */     boolean redirectionRequested = true;
/* 190 */     String requestMethod = null;
/*     */     
/*     */     try {
/* 193 */       currentUrl = url;
/* 194 */       requestMethod = "POST";
/*     */ 
/*     */       
/* 197 */       response = new HttpResponse();
/* 198 */       response.setRequestMethod(requestMethod);
/*     */       
/* 200 */       while (redirectionRequested) {
/*     */         
/* 202 */         response.addRequestUrl(currentUrl);
/*     */ 
/*     */         
/* 205 */         urlObj = new URL(currentUrl);
/* 206 */         connection = (HttpURLConnection)urlObj.openConnection();
/* 207 */         connection.setRequestMethod(requestMethod);
/* 208 */         connection.setRequestProperty("Accept", "application/json");
/* 209 */         connection.setRequestProperty("Content-Type", "application/json");
/*     */         
/* 211 */         connection.setDoInput(true);
/* 212 */         connection.setUseCaches(false);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 217 */         if (!TextUtils.isEmpty(extraContent))
/*     */         {
/* 219 */           writeOutputData(connection, extraContent);
/*     */         }
/*     */ 
/*     */         
/* 223 */         connection.connect();
/* 224 */         redirectionRequested = mustRedirect(connection);
/*     */ 
/*     */         
/* 227 */         if (redirectionRequested) {
/* 228 */           currentUrl = connection.getHeaderField("Location");
/* 229 */           connection.disconnect();
/*     */         } 
/*     */         
/* 232 */         redirectionRequested = false;
/*     */       } 
/*     */       
/* 235 */       fillResponse(response, connection);
/* 236 */     } catch (Exception e) {
/* 237 */       Log.e("PlugPag-Network-HTTP", "Request (POST) exception", e);
/* 238 */       response = null;
/*     */     } finally {
/* 240 */       if (connection != null) {
/* 241 */         connection.disconnect();
/*     */       }
/*     */     } 
/*     */     
/* 245 */     return response;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private HttpResponse doPost(@NonNull String url) {
/* 255 */     return doPost(url, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeOutputData(HttpURLConnection connection, String extraContent) throws IOException {
/* 266 */     OutputStreamWriter outputStream = null;
/*     */     
/*     */     try {
/* 269 */       connection.setRequestProperty("Content-Length", String.valueOf(extraContent.length()));
/*     */       
/* 271 */       outputStream = new OutputStreamWriter(connection.getOutputStream());
/* 272 */       outputStream.write(extraContent);
/* 273 */       outputStream.flush();
/* 274 */       outputStream.close();
/* 275 */     } catch (Exception e) {
/* 276 */       e.printStackTrace();
/*     */     } 
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
/* 292 */     boolean redirect = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 297 */     redirect = (this.mFollowRedirects && connection != null && connection instanceof HttpURLConnection && isRedirectCode(((HttpURLConnection)connection).getResponseCode()));
/*     */     
/* 299 */     return redirect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isRedirectCode(int code) {
/* 309 */     return (code == 301 || code == 302);
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
/* 321 */     byte[] response = null;
/* 322 */     BufferedInputStream bis = null;
/* 323 */     ByteArrayOutputStream baos = null;
/* 324 */     byte[] buffer = null;
/* 325 */     int bytesRead = -1;
/*     */     
/* 327 */     if (this.mInjectedResponse == null) {
/*     */       
/*     */       try {
/* 330 */         buffer = new byte[4096];
/* 331 */         bis = new BufferedInputStream(connection.getInputStream());
/* 332 */         baos = new ByteArrayOutputStream();
/*     */ 
/*     */         
/* 335 */         while ((bytesRead = bis.read(buffer)) != -1) {
/* 336 */           baos.write(buffer, 0, bytesRead);
/*     */         }
/*     */ 
/*     */         
/* 340 */         baos.flush();
/* 341 */         response = baos.toByteArray();
/*     */ 
/*     */         
/* 344 */         baos.close();
/* 345 */         bis.close();
/* 346 */       } catch (IOException e) {
/* 347 */         Log.e("PlugPag-Network-HTTP", "Stream read exception", e);
/*     */       } 
/*     */     } else {
/*     */       
/* 351 */       response = this.mInjectedResponse.getBytes();
/*     */     } 
/*     */     
/* 354 */     return response;
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
/*     */   private void fillResponse(HttpResponse response, HttpURLConnection connection) {
/* 368 */     byte[] responseBytes = null;
/*     */     
/* 370 */     if (connection != null) {
/*     */       try {
/* 372 */         responseBytes = getResponseData(connection);
/* 373 */         response.setResponseCode(connection.getResponseCode());
/* 374 */         response.setResponseMessage(connection.getResponseMessage());
/* 375 */         response.setResponseContent(responseBytes);
/* 376 */       } catch (Exception e) {
/* 377 */         Log.e("PlugPag-Network-HTTP", "Error while filling NetworkResponseOld object", e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void bypassSsl() {
/* 390 */     TrustManager[] trustAllCerts = { new X509TrustManager() {
/*     */           public X509Certificate[] getAcceptedIssuers() {
/* 392 */             return null;
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void checkClientTrusted(X509Certificate[] certs, String authType) {}
/*     */ 
/*     */ 
/*     */           
/*     */           public void checkServerTrusted(X509Certificate[] certs, String authType) {}
/*     */         } };
/*     */     try {
/* 405 */       SSLContext sc = SSLContext.getInstance("SSL");
/* 406 */       sc.init(null, trustAllCerts, new SecureRandom());
/* 407 */       HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
/*     */ 
/*     */       
/* 410 */       HostnameVerifier allHostsValid = new HostnameVerifier() {
/*     */           public boolean verify(String hostname, SSLSession session) {
/* 412 */             return true;
/*     */           }
/*     */         };
/*     */ 
/*     */       
/* 417 */       HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
/* 418 */     } catch (Exception e) {
/* 419 */       Log.e("PlugPag-Network-HTTP", "Request (POST) exception", e);
/*     */     } 
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
/*     */   private void setupProtocolSupport(@NonNull HttpsURLConnection connection) throws NoSuchAlgorithmException, KeyManagementException {
/* 436 */     if (connection != null) {
/* 437 */       setupProtocolSupport(connection);
/*     */     }
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
/*     */   private void setupProtocolSupport(@NonNull HttpURLConnection connection) throws NoSuchAlgorithmException, KeyManagementException {
/* 450 */     if (connection != null && "https"
/* 451 */       .equals(connection.getURL().getProtocol()))
/* 452 */       ((HttpsURLConnection)connection).setSSLSocketFactory(new TlsSocketFactory()); 
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/network/http/HttpCommunication.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */