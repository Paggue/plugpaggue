/*    */ package br.com.uol.pagseguro.plugpag.libswitch;
/*    */ 
/*    */ public class LibSwitchReturn {
/*    */   private LibSwitchValue returnValue;
/*    */   
/*    */   public enum LibSwitchValue {
/*  7 */     PSC_OK(0),
/*  8 */     PSC_USER_ABORT(18),
/*  9 */     PSC_KEY_BACKSPACE(39);
/*    */     
/*    */     private final int value;
/*    */     
/*    */     LibSwitchValue(int value) {
/* 14 */       this.value = value;
/*    */     }
/*    */     public int getValor() {
/* 17 */       return this.value;
/*    */     }
/*    */   }
/*    */   
/*    */   public LibSwitchReturn(LibSwitchValue returnValue) {
/* 22 */     this.returnValue = returnValue;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getReturnValue() {
/* 28 */     return this.returnValue.value;
/*    */   }
/*    */   
/*    */   public void setReturnValue(LibSwitchValue returnValue) {
/* 32 */     this.returnValue = returnValue;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/libswitch/LibSwitchReturn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */