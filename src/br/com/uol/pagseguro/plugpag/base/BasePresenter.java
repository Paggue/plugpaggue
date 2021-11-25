package br.com.uol.pagseguro.plugpag.base;

public interface BasePresenter<T> {
  void attachView(T paramT);
  
  void detachView();
}
