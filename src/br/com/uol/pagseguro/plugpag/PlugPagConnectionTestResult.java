package br.com.uol.pagseguro.plugpag;

import java.io.Serializable;

public class PlugPagConnectionTestResult implements Serializable {
  private String message;
  
  private String errorCode;
  
  public PlugPagConnectionTestResult(String message, String errorCode) {
    this.message = message;
    this.errorCode = errorCode;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public String getErrorCode() {
    return this.errorCode;
  }
}
