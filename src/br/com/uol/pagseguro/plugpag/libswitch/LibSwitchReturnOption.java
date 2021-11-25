package br.com.uol.pagseguro.plugpag.libswitch;

public class LibSwitchReturnOption extends LibSwitchReturn {
  private int option;
  
  public LibSwitchReturnOption(LibSwitchReturn.LibSwitchValue returnValue, int option) {
    super(returnValue);
    this.option = option;
  }
  
  public int getOption() {
    return this.option;
  }
  
  public void setOption(int option) {
    this.option = option;
  }
}
