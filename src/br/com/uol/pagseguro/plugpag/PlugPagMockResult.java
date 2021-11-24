/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ public class PlugPagMockResult {
/*    */   private final int codeResult;
/*    */   private final String errorCode;
/*    */   
/*    */   public PlugPagMockResult(String errorCode, int codeResult) {
/*  8 */     this.codeResult = codeResult;
/*  9 */     this.errorCode = errorCode;
/*    */   }
/*    */   
/*    */   public int getCodeResult() {
/* 13 */     return this.codeResult;
/*    */   }
/*    */   
/*    */   public String getErrorCode() {
/* 17 */     return this.errorCode;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagMockResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */