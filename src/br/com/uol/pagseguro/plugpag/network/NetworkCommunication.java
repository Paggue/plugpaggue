package br.com.uol.pagseguro.plugpag.network;

public interface NetworkCommunication<REQUEST extends NetworkContext, RESPONSE extends NetworkResponse> {
  RESPONSE send(REQUEST paramREQUEST);
  
  void injectMockResponse(RESPONSE paramRESPONSE);
}


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/network/NetworkCommunication.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */