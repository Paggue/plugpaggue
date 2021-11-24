package br.com.uol.pagseguro.plugpag;

public interface IPlugPagTransactionState {
  int checkAbort();
  
  int checkDelay();
  
  int isVoidTransaction();
}


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/IPlugPagTransactionState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */