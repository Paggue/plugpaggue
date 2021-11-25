package br.com.uol.pagseguro.plugpag.datainput;

public class PlugPagPrinterManager {
  private static PlugPagPrinterManager mPlugPagPrinterManager;
  
  private String phone;
  
  private boolean printReceipt;
  
  public static synchronized PlugPagPrinterManager getInstance() {
    if (mPlugPagPrinterManager == null)
      mPlugPagPrinterManager = new PlugPagPrinterManager(); 
    return mPlugPagPrinterManager;
  }
  
  public String getPhone() {
    return this.phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  public boolean shouldPrintReceipt() {
    return this.printReceipt;
  }
  
  public void setPrintReceipt(boolean printReceipt) {
    this.printReceipt = printReceipt;
  }
}
