package br.com.uol.pagseguro.plugpag;

public class PlugPagAbortResult {
  static {
    PlugPagLibraryLoader.loadNativeLibraries();
  }
  
  private int mResult = -1;
  
  public PlugPagAbortResult(int result) {
    this.mResult = result;
  }
  
  public int getResult() {
    return this.mResult;
  }
}
