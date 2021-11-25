package br.com.uol.pagseguro.plugpag;

import java.io.Serializable;

public interface PlugPagEventListener extends Serializable {
  int onEvent(PlugPagEventData paramPlugPagEventData);
}
