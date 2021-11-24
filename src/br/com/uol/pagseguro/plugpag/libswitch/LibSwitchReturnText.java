/*    */ package br.com.uol.pagseguro.plugpag.libswitch;
/*    */ 
/*    */ public class LibSwitchReturnText
/*    */   extends LibSwitchReturn {
/*    */   private String text;
/*    */   
/*    */   public LibSwitchReturnText(LibSwitchReturn.LibSwitchValue returnValue, String text) {
/*  8 */     super(returnValue);
/*  9 */     this.text = text;
/*    */   }
/*    */   
/*    */   public String getText() {
/* 13 */     return this.text;
/*    */   }
/*    */   
/*    */   public void setText(String text) {
/* 17 */     this.text = text;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/libswitch/LibSwitchReturnText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */