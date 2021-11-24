/*    */ package br.com.uol.pagseguro.plugpag.network.http;
/*    */ 
/*    */ import br.com.uol.pagseguro.plugpag.network.NetworkResponse;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HttpResponse
/*    */   extends NetworkResponse
/*    */ {
/* 14 */   private String mRequestMethod = null;
/* 15 */   private String mRequestContent = null;
/* 16 */   private List<String> mRequestUrlChain = null;
/*    */   
/* 18 */   private int mResponseCode = -1;
/* 19 */   private String mResponseMessage = null;
/* 20 */   private byte[] mResponseContent = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getRequestMethod() {
/* 27 */     return this.mRequestMethod;
/*    */   }
/*    */   
/*    */   public void setRequestMethod(String requestMethod) {
/* 31 */     this.mRequestMethod = requestMethod;
/*    */   }
/*    */   
/*    */   public String getRequestContent() {
/* 35 */     return this.mRequestContent;
/*    */   }
/*    */   
/*    */   public void setRequestContent(String requestContent) {
/* 39 */     this.mRequestContent = requestContent;
/*    */   }
/*    */   
/*    */   public List<String> getRequestUrlChain() {
/* 43 */     if (this.mRequestUrlChain == null) {
/* 44 */       this.mRequestUrlChain = new ArrayList<>();
/*    */     }
/*    */     
/* 47 */     return new ArrayList<>(this.mRequestUrlChain);
/*    */   }
/*    */   
/*    */   public void addRequestUrl(String url) {
/* 51 */     if (this.mRequestUrlChain == null) {
/* 52 */       this.mRequestUrlChain = new ArrayList<>();
/*    */     }
/*    */     
/* 55 */     this.mRequestUrlChain.add(url);
/*    */   }
/*    */   
/*    */   public int getResponseCode() {
/* 59 */     return this.mResponseCode;
/*    */   }
/*    */   
/*    */   public void setResponseCode(int responseCode) {
/* 63 */     this.mResponseCode = responseCode;
/*    */   }
/*    */   
/*    */   public String getResponseMessage() {
/* 67 */     return this.mResponseMessage;
/*    */   }
/*    */   
/*    */   public void setResponseMessage(String responseMessage) {
/* 71 */     this.mResponseMessage = responseMessage;
/*    */   }
/*    */   
/*    */   public byte[] getResponseContent() {
/* 75 */     return this.mResponseContent;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResponseContentString() {
/* 80 */     if (this.mResponseContent != null) {
/* 81 */       return new String(this.mResponseContent);
/*    */     }
/* 83 */     return null;
/*    */   }
/*    */   
/*    */   public void setResponseContent(byte[] responseContent) {
/* 87 */     this.mResponseContent = responseContent;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getBytes() {
/* 92 */     return new byte[0];
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/network/http/HttpResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */