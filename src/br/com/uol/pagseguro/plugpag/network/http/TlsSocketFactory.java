/*     */ package br.com.uol.pagseguro.plugpag.network.http;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.Socket;
/*     */ import java.net.UnknownHostException;
/*     */ import java.security.KeyManagementException;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import javax.net.ssl.SSLContext;
/*     */ import javax.net.ssl.SSLSocket;
/*     */ import javax.net.ssl.SSLSocketFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TlsSocketFactory
/*     */   extends SSLSocketFactory
/*     */ {
/*  21 */   private static final String[] ENABLED_PROTOCOLS = new String[] { "TLSv1.2" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SSLSocketFactory internalSSLSocketFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TlsSocketFactory() throws KeyManagementException, NoSuchAlgorithmException {
/*  40 */     SSLContext context = SSLContext.getInstance("TLS");
/*  41 */     context.init(null, null, null);
/*  42 */     this.internalSSLSocketFactory = context.getSocketFactory();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getDefaultCipherSuites() {
/*  51 */     return this.internalSSLSocketFactory.getDefaultCipherSuites();
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getSupportedCipherSuites() {
/*  56 */     return this.internalSSLSocketFactory.getSupportedCipherSuites();
/*     */   }
/*     */ 
/*     */   
/*     */   public Socket createSocket() throws IOException {
/*  61 */     return enableTLSOnSocket(this.internalSSLSocketFactory.createSocket());
/*     */   }
/*     */ 
/*     */   
/*     */   public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
/*  66 */     return enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(s, host, port, autoClose));
/*     */   }
/*     */ 
/*     */   
/*     */   public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
/*  71 */     return enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(host, port));
/*     */   }
/*     */ 
/*     */   
/*     */   public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException, UnknownHostException {
/*  76 */     return enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(host, port, localHost, localPort));
/*     */   }
/*     */ 
/*     */   
/*     */   public Socket createSocket(InetAddress host, int port) throws IOException {
/*  81 */     return enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(host, port));
/*     */   }
/*     */ 
/*     */   
/*     */   public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
/*  86 */     return enableTLSOnSocket(this.internalSSLSocketFactory.createSocket(address, port, localAddress, localPort));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Socket enableTLSOnSocket(Socket socket) {
/* 100 */     if (socket != null && socket instanceof SSLSocket) {
/* 101 */       ((SSLSocket)socket).setEnabledProtocols(ENABLED_PROTOCOLS);
/*     */     }
/*     */     
/* 104 */     return socket;
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/network/http/TlsSocketFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */