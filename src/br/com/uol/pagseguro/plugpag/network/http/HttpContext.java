package br.com.uol.pagseguro.plugpag.network.http;

import br.com.uol.pagseguro.plugpag.network.NetworkContext;

public class HttpContext extends NetworkContext {
  public boolean bypassSsl = false;
  
  public String host = null;
  
  public int port = 0;
  
  public String method = "POST";
  
  public String content = null;
  
  public boolean followRedirects = false;
  
  public byte[] data = null;
}
