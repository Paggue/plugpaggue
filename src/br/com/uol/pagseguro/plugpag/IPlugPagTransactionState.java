package br.com.uol.pagseguro.plugpag;

public interface IPlugPagTransactionState {
  int checkAbort();
  
  int checkDelay();
  
  int isVoidTransaction();
}
