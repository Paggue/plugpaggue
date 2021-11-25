package br.com.uol.pagseguro.plugpag;

public class PlugPagMockResult {
  private final int codeResult;
  
  private final String errorCode;
  
  public PlugPagMockResult(String errorCode, int codeResult) {
    this.codeResult = codeResult;
    this.errorCode = errorCode;
  }
  
  public int getCodeResult() {
    return this.codeResult;
  }
  
  public String getErrorCode() {
    return this.errorCode;
  }
}
