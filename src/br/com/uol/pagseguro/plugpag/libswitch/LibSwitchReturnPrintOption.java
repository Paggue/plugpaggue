/*    */ package br.com.uol.pagseguro.plugpag.libswitch;
/*    */ 
/*    */ public class LibSwitchReturnPrintOption
/*    */   extends LibSwitchReturn {
/*    */   private ReceiptOptions option;
/*    */   private String phoneNumber;
/*    */   
/*    */   public LibSwitchReturnPrintOption(ReceiptOptions option, String phoneNumber) {
/*  9 */     super(LibSwitchReturn.LibSwitchValue.PSC_OK);
/* 10 */     this.option = option;
/* 11 */     this.phoneNumber = phoneNumber;
/*    */   }
/*    */   
/*    */   public enum ReceiptOptions
/*    */   {
/* 16 */     MO_RECEIPT(0),
/* 17 */     PRINT_RECEIPT(1),
/* 18 */     SEND_SMS(2);
/*    */     
/*    */     private final int value;
/*    */     
/*    */     ReceiptOptions(int value) {
/* 23 */       this.value = value;
/*    */     }
/*    */     
/*    */     public int getValue() {
/* 27 */       return this.value;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private String getPhoneNumber() {
/* 33 */     return this.phoneNumber;
/*    */   }
/*    */   
/*    */   private int getOption() {
/* 37 */     return this.option.getValue();
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/libswitch/LibSwitchReturnPrintOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */