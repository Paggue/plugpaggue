/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlugPagAbortResult
/*    */ {
/*    */   static {
/* 10 */     PlugPagLibraryLoader.loadNativeLibraries();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 17 */   private int mResult = -1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PlugPagAbortResult(int result) {
/* 29 */     this.mResult = result;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getResult() {
/* 42 */     return this.mResult;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagAbortResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */