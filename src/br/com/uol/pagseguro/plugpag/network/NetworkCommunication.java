package br.com.uol.pagseguro.plugpag.network;

public interface NetworkCommunication<REQUEST extends NetworkContext, RESPONSE extends NetworkResponse> {
  RESPONSE send(REQUEST paramREQUEST);
  
  void injectMockResponse(RESPONSE paramRESPONSE);
}
