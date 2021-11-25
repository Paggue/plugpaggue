package br.com.uol.pagseguro.plugpag.network.http;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import br.com.uol.pagseguro.plugpag.network.NetworkCommunication;
import br.com.uol.pagseguro.plugpag.network.NetworkContext;
import br.com.uol.pagseguro.plugpag.network.NetworkResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpCommunication implements NetworkCommunication<HttpContext, HttpResponse> {
  public static final String LOG_TAG = "PlugPag-Network-HTTP";
  
  public static final String METHOD_GET = "GET";
  
  public static final String METHOD_POST = "POST";
  
  private static final String HEADER_LOCATION = "Location";
  
  private static final int BUFFER_SIZE = 4096;
  
  private static final String PROTOCOL_HTTPS = "https";
  
  private HttpResponse mInjectedResponse = null;
  
  private boolean mFollowRedirects = true;
  
  public HttpResponse send(HttpContext context) {
    HttpResponse response = null;
    if (context != null) {
      if (context.bypassSsl)
        bypassSsl(); 
      this.mFollowRedirects = context.followRedirects;
      if ("POST".equals(context.method)) {
        response = doPost(context.host, context.content);
      } else if ("GET".equals(context.method)) {
        response = doGet(context.host);
      } 
      if (this.mInjectedResponse != null)
        response = this.mInjectedResponse; 
    } 
    return response;
  }
  
  public void injectMockResponse(HttpResponse mockResponse) {
    this.mInjectedResponse = mockResponse;
  }
  
  private HttpContext createDefaultHttpContext() {
    HttpContext context = null;
    context = new HttpContext();
    context.bypassSsl = false;
    context.followRedirects = true;
    context.method = "POST";
    return context;
  }
  
  private HttpResponse doGet(@NonNull String url) {
    HttpResponse response = null;
    String currentUrl = null;
    URL urlObj = null;
    HttpURLConnection connection = null;
    boolean redirectionRequested = true;
    String requestMethod = null;
    try {
      currentUrl = url;
      requestMethod = "GET";
      response = new HttpResponse();
      response.setRequestMethod(requestMethod);
      while (redirectionRequested) {
        response.addRequestUrl(currentUrl);
        urlObj = new URL(currentUrl);
        connection = (HttpURLConnection)urlObj.openConnection();
        connection.setRequestMethod(requestMethod);
        connection.setUseCaches(false);
        connection.connect();
        redirectionRequested = mustRedirect(connection);
        if (redirectionRequested) {
          currentUrl = connection.getHeaderField("Location");
          connection.disconnect();
        } 
      } 
      fillResponse(response, connection);
    } catch (Exception e) {
      Log.e("PlugPag-Network-HTTP", "Request (GET) exception", e);
      response = null;
    } finally {
      if (connection != null)
        connection.disconnect(); 
    } 
    return response;
  }
  
  public HttpResponse doPost(@NonNull String url, @Nullable String extraContent) {
    HttpResponse response = null;
    String currentUrl = null;
    URL urlObj = null;
    HttpURLConnection connection = null;
    boolean redirectionRequested = true;
    String requestMethod = null;
    try {
      currentUrl = url;
      requestMethod = "POST";
      response = new HttpResponse();
      response.setRequestMethod(requestMethod);
      while (redirectionRequested) {
        response.addRequestUrl(currentUrl);
        urlObj = new URL(currentUrl);
        connection = (HttpURLConnection)urlObj.openConnection();
        connection.setRequestMethod(requestMethod);
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoInput(true);
        connection.setUseCaches(false);
        if (!TextUtils.isEmpty(extraContent))
          writeOutputData(connection, extraContent); 
        connection.connect();
        redirectionRequested = mustRedirect(connection);
        if (redirectionRequested) {
          currentUrl = connection.getHeaderField("Location");
          connection.disconnect();
        } 
        redirectionRequested = false;
      } 
      fillResponse(response, connection);
    } catch (Exception e) {
      Log.e("PlugPag-Network-HTTP", "Request (POST) exception", e);
      response = null;
    } finally {
      if (connection != null)
        connection.disconnect(); 
    } 
    return response;
  }
  
  private HttpResponse doPost(@NonNull String url) {
    return doPost(url, null);
  }
  
  private void writeOutputData(HttpURLConnection connection, String extraContent) throws IOException {
    OutputStreamWriter outputStream = null;
    try {
      connection.setRequestProperty("Content-Length", String.valueOf(extraContent.length()));
      outputStream = new OutputStreamWriter(connection.getOutputStream());
      outputStream.write(extraContent);
      outputStream.flush();
      outputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  private boolean mustRedirect(@NonNull URLConnection connection) throws IOException {
    boolean redirect = false;
    redirect = (this.mFollowRedirects && connection != null && connection instanceof HttpURLConnection && isRedirectCode(((HttpURLConnection)connection).getResponseCode()));
    return redirect;
  }
  
  private boolean isRedirectCode(int code) {
    return (code == 301 || code == 302);
  }
  
  private byte[] getResponseData(@NonNull URLConnection connection) {
    byte[] response = null;
    BufferedInputStream bis = null;
    ByteArrayOutputStream baos = null;
    byte[] buffer = null;
    int bytesRead = -1;
    if (this.mInjectedResponse == null) {
      try {
        buffer = new byte[4096];
        bis = new BufferedInputStream(connection.getInputStream());
        baos = new ByteArrayOutputStream();
        while ((bytesRead = bis.read(buffer)) != -1)
          baos.write(buffer, 0, bytesRead); 
        baos.flush();
        response = baos.toByteArray();
        baos.close();
        bis.close();
      } catch (IOException e) {
        Log.e("PlugPag-Network-HTTP", "Stream read exception", e);
      } 
    } else {
      response = this.mInjectedResponse.getBytes();
    } 
    return response;
  }
  
  private void fillResponse(HttpResponse response, HttpURLConnection connection) {
    byte[] responseBytes = null;
    if (connection != null)
      try {
        responseBytes = getResponseData(connection);
        response.setResponseCode(connection.getResponseCode());
        response.setResponseMessage(connection.getResponseMessage());
        response.setResponseContent(responseBytes);
      } catch (Exception e) {
        Log.e("PlugPag-Network-HTTP", "Error while filling NetworkResponseOld object", e);
      }  
  }
  
  private void bypassSsl() {
    TrustManager[] trustAllCerts = { new X509TrustManager() {
          public X509Certificate[] getAcceptedIssuers() {
            return null;
          }
          
          public void checkClientTrusted(X509Certificate[] certs, String authType) {}
          
          public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        } };
    try {
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
      HostnameVerifier allHostsValid = new HostnameVerifier() {
          public boolean verify(String hostname, SSLSession session) {
            return true;
          }
        };
      HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    } catch (Exception e) {
      Log.e("PlugPag-Network-HTTP", "Request (POST) exception", e);
    } 
  }
  
  private void setupProtocolSupport(@NonNull HttpsURLConnection connection) throws NoSuchAlgorithmException, KeyManagementException {
    if (connection != null)
      setupProtocolSupport(connection); 
  }
  
  private void setupProtocolSupport(@NonNull HttpURLConnection connection) throws NoSuchAlgorithmException, KeyManagementException {
    if (connection != null && "https"
      .equals(connection.getURL().getProtocol()))
      ((HttpsURLConnection)connection).setSSLSocketFactory(new TlsSocketFactory()); 
  }
}
