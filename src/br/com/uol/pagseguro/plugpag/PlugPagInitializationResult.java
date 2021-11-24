/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ public final class PlugPagInitializationResult {
/*    */   private int mResult;
/*    */   private String mErrorCode;
/*    */   private String mErrorMessage;
/*    */   private int mHolderNetworkPreference;
/*    */   
/*    */   public PlugPagInitializationResult(int result, String errorCode, String errorMessage, int holderNetworkPreference) {
/* 10 */     this.mResult = result;
/* 11 */     this.mErrorCode = errorCode;
/* 12 */     this.mErrorMessage = errorMessage;
/* 13 */     this.mHolderNetworkPreference = holderNetworkPreference;
/*    */   }
/*    */   
/*    */   public int getResult() {
/* 17 */     return this.mResult;
/*    */   }
/*    */   
/*    */   public String getErrorCode() {
/* 21 */     return this.mErrorCode;
/*    */   }
/*    */   
/*    */   public String getErrorMessage() {
/* 25 */     return this.mErrorMessage;
/*    */   }
/*    */   
/*    */   public int getHolderNetworkPreference() {
/* 29 */     return this.mHolderNetworkPreference;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagInitializationResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */