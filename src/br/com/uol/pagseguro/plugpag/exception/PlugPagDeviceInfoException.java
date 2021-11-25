package br.com.uol.pagseguro.plugpag.exception;

public class PlugPagDeviceInfoException extends RuntimeException {
  public PlugPagDeviceInfoException() {}
  
  public PlugPagDeviceInfoException(String message) {
    super(message);
  }
  
  public PlugPagDeviceInfoException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public PlugPagDeviceInfoException(Throwable cause) {
    super(cause);
  }
}
