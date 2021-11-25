package br.com.uol.pagseguro.plugpag.libswitch;

public class LibSwitchReturnPrintOption extends LibSwitchReturn {
  private ReceiptOptions option;
  
  private String phoneNumber;
  
  public LibSwitchReturnPrintOption(ReceiptOptions option, String phoneNumber) {
    super(LibSwitchReturn.LibSwitchValue.PSC_OK);
    this.option = option;
    this.phoneNumber = phoneNumber;
  }
  
  public enum ReceiptOptions {
    MO_RECEIPT(0),
    PRINT_RECEIPT(1),
    SEND_SMS(2);
    
    private final int value;
    
    ReceiptOptions(int value) {
      this.value = value;
    }
    
    public int getValue() {
      return this.value;
    }
  }
  
  private String getPhoneNumber() {
    return this.phoneNumber;
  }
  
  private int getOption() {
    return this.option.getValue();
  }
}
