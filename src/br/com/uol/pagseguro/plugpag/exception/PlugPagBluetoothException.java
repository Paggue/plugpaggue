package br.com.uol.pagseguro.plugpag.exception;

public class PlugPagBluetoothException extends RuntimeException {
  public PlugPagBluetoothException() {}
  
  public PlugPagBluetoothException(String message) {
    super(message);
  }
  
  public PlugPagBluetoothException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public PlugPagBluetoothException(Throwable cause) {
    super(cause);
  }
}
