package br.com.uol.pagseguro.plugpag;

import java.io.Serializable;

public interface PlugPagEventLoggerListener extends Serializable {
  int recordEvent(PlugPagEventLoggerData paramPlugPagEventLoggerData);
  
  int recordBreadCrumb(PlugPagEventLoggerData paramPlugPagEventLoggerData);
}


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagEventLoggerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */