/*     */ package br.com.uol.pagseguro.plugpag.authentication;
/*     */ 
/*     */ import android.support.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class PlugPagAuthenticationResult
/*     */ {
/*     */   private boolean mAuthenticated = false;
/*  12 */   private String mStatus = null;
/*  13 */   private String mMessage = null;
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
/*     */   public PlugPagAuthenticationResult(boolean authenticated, @Nullable String status, @Nullable String message) {
/*  27 */     this.mAuthenticated = authenticated;
/*  28 */     this.mStatus = status;
/*  29 */     this.mMessage = message;
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
/*     */   public boolean isAuthenticated() {
/*  42 */     return this.mAuthenticated;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStatus() {
/*  51 */     return this.mStatus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*  60 */     return this.mMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */   {
/*     */     private boolean mAuthenticated = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     private String mStatus = null;
/*  79 */     private String mMessage = null;
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
/*     */     public void isAuthenticated(boolean authenticated) {
/*  91 */       this.mAuthenticated = authenticated;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setStatus(String status) {
/* 100 */       this.mStatus = status;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setMessage(String message) {
/* 109 */       this.mMessage = message;
/*     */     }
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
/*     */     public PlugPagAuthenticationResult build() {
/* 122 */       PlugPagAuthenticationResult authenticationResult = null;
/*     */       
/* 124 */       authenticationResult = new PlugPagAuthenticationResult(this.mAuthenticated, this.mStatus, this.mMessage);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 129 */       return authenticationResult;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/authentication/PlugPagAuthenticationResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */