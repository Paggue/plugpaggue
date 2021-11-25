package br.com.uol.pagseguro.plugpag.network.iso8583;

import br.com.uol.pagseguro.plugpag.network.NetworkCommunication;
import br.com.uol.pagseguro.plugpag.network.NetworkContext;
import br.com.uol.pagseguro.plugpag.network.NetworkResponse;

public class Iso8583Communication implements NetworkCommunication<Iso8583Context, Iso8583Response> {
  public static final String LOG_TAG = "PlugPag-Network-TLV";
  
  public Iso8583Response send(Iso8583Context context) {
    return null;
  }
  
  public void injectMockResponse(Iso8583Response mockResponse) {}
}
