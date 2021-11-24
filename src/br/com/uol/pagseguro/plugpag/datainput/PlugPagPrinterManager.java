/*    */ package br.com.uol.pagseguro.plugpag.datainput;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlugPagPrinterManager
/*    */ {
/*    */   private static PlugPagPrinterManager mPlugPagPrinterManager;
/*    */   private String phone;
/*    */   private boolean printReceipt;
/*    */   
/*    */   public static synchronized PlugPagPrinterManager getInstance() {
/* 14 */     if (mPlugPagPrinterManager == null) {
/* 15 */       mPlugPagPrinterManager = new PlugPagPrinterManager();
/*    */     }
/* 17 */     return mPlugPagPrinterManager;
/*    */   }
/*    */   
/*    */   public String getPhone() {
/* 21 */     return this.phone;
/*    */   }
/*    */   
/*    */   public void setPhone(String phone) {
/* 25 */     this.phone = phone;
/*    */   }
/*    */   
/*    */   public boolean shouldPrintReceipt() {
/* 29 */     return this.printReceipt;
/*    */   }
/*    */   
/*    */   public void setPrintReceipt(boolean printReceipt) {
/* 33 */     this.printReceipt = printReceipt;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/datainput/PlugPagPrinterManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */