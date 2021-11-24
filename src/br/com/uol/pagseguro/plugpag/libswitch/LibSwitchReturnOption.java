/*    */ package br.com.uol.pagseguro.plugpag.libswitch;
/*    */ 
/*    */ public class LibSwitchReturnOption
/*    */   extends LibSwitchReturn {
/*    */   private int option;
/*    */   
/*    */   public LibSwitchReturnOption(LibSwitchReturn.LibSwitchValue returnValue, int option) {
/*  8 */     super(returnValue);
/*  9 */     this.option = option;
/*    */   }
/*    */   
/*    */   public int getOption() {
/* 13 */     return this.option;
/*    */   }
/*    */   
/*    */   public void setOption(int option) {
/* 17 */     this.option = option;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/libswitch/LibSwitchReturnOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */