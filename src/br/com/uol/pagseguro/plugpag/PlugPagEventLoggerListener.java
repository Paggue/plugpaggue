package br.com.uol.pagseguro.plugpag;

import java.io.Serializable;

public interface PlugPagEventLoggerListener extends Serializable {
  int recordEvent(PlugPagEventLoggerData paramPlugPagEventLoggerData);
  
  int recordBreadCrumb(PlugPagEventLoggerData paramPlugPagEventLoggerData);
}
