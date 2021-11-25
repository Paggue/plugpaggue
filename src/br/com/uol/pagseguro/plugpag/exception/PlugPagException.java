package br.com.uol.pagseguro.plugpag.exception;

public class PlugPagException extends RuntimeException {
  public PlugPagException() {}
  
  public PlugPagException(String message) {
    super(message);
  }
  
  public PlugPagException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public PlugPagException(Throwable cause) {
    super(cause);
  }
}
