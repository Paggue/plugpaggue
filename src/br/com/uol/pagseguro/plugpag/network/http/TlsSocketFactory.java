package br.com.uol.pagseguro.plugpag.network.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class TlsSocketFactory extends SSLSocketFactory {
  private static final String[] ENABLED_PROTOCOLS = new String[] { "TLSv1.2" };
  
  private SSLSocketFactory internalSSLSocketFactory;
  
  public TlsSocketFactory() throws KeyManagementException, NoSuchAlgorithmException {
    SSLContext context = SSLContext.getInstance("TLS");
    context.init(null, null, null);
    this.internalSSLSocketFactory = context.getSocketFactory();
  }
  
  public String[] getDefaultCipherSuites() {
    return this.internalSSLSocketFactory.getDefaultCipherSuites();
  }
  
  public String[] getSupportedCipherSuites() {
    return this.internalSSLSocketFactory.getSupportedCipherSuites();
  }
  
  public Socket createSocket() throws IOException {
    return enableTLSOnSocket(this.internalSSLSocketFactory.createSocket());
  }
  
  public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
    return enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(s, host, port, autoClose));
  }
  
  public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
    return enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(host, port));
  }
  
  public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
    return enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(host, port, localHost, localPort));
  }
  
  public Socket createSocket(InetAddress host, int port) throws IOException {
    return enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(host, port));
  }
  
  public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
    return enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(address, port, localAddress, localPort));
  }
  
  private Socket enableTLSOnSocket(Socket socket) {
    if (socket != null && socket instanceof SSLSocket)
      ((SSLSocket)socket).setEnabledProtocols(ENABLED_PROTOCOLS); 
    return socket;
  }
}
