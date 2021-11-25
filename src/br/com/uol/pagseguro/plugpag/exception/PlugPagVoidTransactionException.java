package br.com.uol.pagseguro.plugpag.exception;

public class PlugPagVoidTransactionException extends PlugPagException {
  public PlugPagVoidTransactionException() {}
  
  public PlugPagVoidTransactionException(String message) {
    super(message);
  }
  
  public PlugPagVoidTransactionException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public PlugPagVoidTransactionException(Throwable cause) {
    super(cause);
  }
}
