package br.com.uol.pagseguro.plugpag.network;

import android.support.annotation.NonNull;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.security.InvalidParameterException;

public class UrlRequest {
  private static final String LOG_TAG = "PlugPag_Network";
  
  private static final String METHOD_GET = "GET";
  
  private static final String METHOD_POST = "POST";
  
  private static final String HEADER_LOCATION = "Location";
  
  private static final int BUFFER_SIZE = 4096;
  
  private byte[] mInjectedResponse = null;
  
  private boolean mFollowRedirects = true;
  
  public UrlRequest(boolean bypassSsl) {
    if (bypassSsl)
      bypassSsl(); 
  }
  
  public UrlRequest() {
    this(true);
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
        Log.e("PlugPag_Network", "Stream read exception", e);
      } 
    } else {
      response = (byte[])this.mInjectedResponse.clone();
    } 
    return response;
  }
  
  public void setFollowRedirects(boolean follow) {
    this.mFollowRedirects = follow;
  }
  
  void injectResponse(@NonNull byte[] response) {
    if (response == null)
      throw new InvalidParameterException("[UrlRequest] Mock response cannot be null"); 
    this.mInjectedResponse = (byte[])response.clone();
  }
  
  private void bypassSsl() {}
  
  private void fillResponse(NetworkResponseOld response, HttpURLConnection connection) {
    byte[] responseBytes = null;
    if (connection != null)
      try {
        responseBytes = getResponseData(connection);
        response.setResponseCode(connection.getResponseCode());
        response.setResponseMessage(connection.getResponseMessage());
        response.setResponseContent(responseBytes);
      } catch (Exception e) {
        Log.e("PlugPag_Network", "Error while filling NetworkResponseOld object", e);
      }  
  }
}
