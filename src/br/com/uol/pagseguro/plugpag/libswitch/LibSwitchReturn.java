package br.com.uol.pagseguro.plugpag.libswitch;

public class LibSwitchReturn {
  private LibSwitchValue returnValue;
  
  public enum LibSwitchValue {
    PSC_OK(0),
    PSC_USER_ABORT(18),
    PSC_KEY_BACKSPACE(39);
    
    private final int value;
    
    LibSwitchValue(int value) {
      this.value = value;
    }
    
    public int getValor() {
      return this.value;
    }
  }
  
  public LibSwitchReturn(LibSwitchValue returnValue) {
    this.returnValue = returnValue;
  }
  
  public int getReturnValue() {
    return this.returnValue.value;
  }
  
  public void setReturnValue(LibSwitchValue returnValue) {
    this.returnValue = returnValue;
  }
}
