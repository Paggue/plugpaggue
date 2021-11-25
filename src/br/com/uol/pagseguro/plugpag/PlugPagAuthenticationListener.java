package br.com.uol.pagseguro.plugpag;

public interface PlugPagAuthenticationListener {
  void onSuccess();
  
  void onError();
}
