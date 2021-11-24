package br.com.uol.pagseguro.plugpag;

import java.io.Serializable;

public interface PlugPagEventListener extends Serializable {
  int onEvent(PlugPagEventData paramPlugPagEventData);
}


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */