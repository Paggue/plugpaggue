package br.com.uol.pagseguro.plugpag.network.tlv;

import br.com.uol.pagseguro.plugpag.network.NetworkCommunication;
import br.com.uol.pagseguro.plugpag.network.NetworkContext;
import br.com.uol.pagseguro.plugpag.network.NetworkResponse;

public class TlvCommunication implements NetworkCommunication<TlvContext, TlvResponse> {
  public static final String LOG_TAG = "PlugPag-Network-TLV";
  
  public TlvResponse send(TlvContext context) {
    return null;
  }
  
  public void injectMockResponse(TlvResponse mockResponse) {}
}
