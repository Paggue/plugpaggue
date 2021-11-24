/*    */ package br.com.uol.pagseguro.plugpag.network;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NetworkResponseOld
/*    */ {
/* 12 */   private String mRequestMethod = null;
/* 13 */   private String mRequestContent = null;
/* 14 */   private List<String> mRequestUrlChain = null;
/*    */   
/* 16 */   private int mResponseCode = -1;
/* 17 */   private String mResponseMessage = null;
/* 18 */   private byte[] mResponseContent = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getRequestMethod() {
/* 25 */     return this.mRequestMethod;
/*    */   }
/*    */   
/*    */   public void setRequestMethod(String requestMethod) {
/* 29 */     this.mRequestMethod = requestMethod;
/*    */   }
/*    */   
/*    */   public String getRequestContent() {
/* 33 */     return this.mRequestContent;
/*    */   }
/*    */   
/*    */   public void setRequestContent(String requestContent) {
/* 37 */     this.mRequestContent = requestContent;
/*    */   }
/*    */   
/*    */   public List<String> getRequestUrlChain() {
/* 41 */     if (this.mRequestUrlChain == null) {
/* 42 */       this.mRequestUrlChain = new ArrayList<>();
/*    */     }
/*    */     
/* 45 */     return new ArrayList<>(this.mRequestUrlChain);
/*    */   }
/*    */   
/*    */   public void addRequestUrl(String url) {
/* 49 */     if (this.mRequestUrlChain == null) {
/* 50 */       this.mRequestUrlChain = new ArrayList<>();
/*    */     }
/*    */     
/* 53 */     this.mRequestUrlChain.add(url);
/*    */   }
/*    */   
/*    */   public int getResponseCode() {
/* 57 */     return this.mResponseCode;
/*    */   }
/*    */   
/*    */   public void setResponseCode(int responseCode) {
/* 61 */     this.mResponseCode = responseCode;
/*    */   }
/*    */   
/*    */   public String getResponseMessage() {
/* 65 */     return this.mResponseMessage;
/*    */   }
/*    */   
/*    */   public void setResponseMessage(String responseMessage) {
/* 69 */     this.mResponseMessage = responseMessage;
/*    */   }
/*    */   
/*    */   public byte[] getResponseContent() {
/* 73 */     return this.mResponseContent;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResponseContentString() {
/* 78 */     if (this.mResponseContent != null) {
/* 79 */       return new String(this.mResponseContent);
/*    */     }
/* 81 */     return null;
/*    */   }
/*    */   
/*    */   public void setResponseContent(byte[] responseContent) {
/* 85 */     this.mResponseContent = responseContent;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/network/NetworkResponseOld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */