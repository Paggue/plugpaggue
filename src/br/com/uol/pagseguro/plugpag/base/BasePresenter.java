package br.com.uol.pagseguro.plugpag.base;

public interface BasePresenter<T> {
  void attachView(T paramT);
  
  void detachView();
}


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/base/BasePresenter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */