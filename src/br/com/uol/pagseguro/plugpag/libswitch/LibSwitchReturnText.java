package br.com.uol.pagseguro.plugpag.libswitch;

public class LibSwitchReturnText extends LibSwitchReturn {
  private String text;
  
  public LibSwitchReturnText(LibSwitchReturn.LibSwitchValue returnValue, String text) {
    super(returnValue);
    this.text = text;
  }
  
  public String getText() {
    return this.text;
  }
  
  public void setText(String text) {
    this.text = text;
  }
}
