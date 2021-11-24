/*    */ package br.com.uol.pagseguro.plugpag.exception;
/*    */ 
/*    */ public class PlugPagException
/*    */   extends RuntimeException
/*    */ {
/*    */   public PlugPagException() {}
/*    */   
/*    */   public PlugPagException(String message) {
/*  9 */     super(message);
/*    */   }
/*    */   
/*    */   public PlugPagException(String message, Throwable cause) {
/* 13 */     super(message, cause);
/*    */   }
/*    */   
/*    */   public PlugPagException(Throwable cause) {
/* 17 */     super(cause);
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/exception/PlugPagException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */