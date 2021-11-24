/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class PlugPagConnectionTestResult
/*    */   implements Serializable {
/*    */   private String message;
/*    */   private String errorCode;
/*    */   
/*    */   public PlugPagConnectionTestResult(String message, String errorCode) {
/* 11 */     this.message = message;
/* 12 */     this.errorCode = errorCode;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 16 */     return this.message;
/*    */   }
/*    */   
/*    */   public String getErrorCode() {
/* 20 */     return this.errorCode;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagConnectionTestResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */