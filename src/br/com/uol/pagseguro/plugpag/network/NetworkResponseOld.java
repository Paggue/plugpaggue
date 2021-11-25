package br.com.uol.pagseguro.plugpag.network;

import java.util.ArrayList;
import java.util.List;

public class NetworkResponseOld {
  private String mRequestMethod = null;
  
  private String mRequestContent = null;
  
  private List<String> mRequestUrlChain = null;
  
  private int mResponseCode = -1;
  
  private String mResponseMessage = null;
  
  private byte[] mResponseContent = null;
  
  public String getRequestMethod() {
    return this.mRequestMethod;
  }
  
  public void setRequestMethod(String requestMethod) {
    this.mRequestMethod = requestMethod;
  }
  
  public String getRequestContent() {
    return this.mRequestContent;
  }
  
  public void setRequestContent(String requestContent) {
    this.mRequestContent = requestContent;
  }
  
  public List<String> getRequestUrlChain() {
    if (this.mRequestUrlChain == null)
      this.mRequestUrlChain = new ArrayList<>(); 
    return new ArrayList<>(this.mRequestUrlChain);
  }
  
  public void addRequestUrl(String url) {
    if (this.mRequestUrlChain == null)
      this.mRequestUrlChain = new ArrayList<>(); 
    this.mRequestUrlChain.add(url);
  }
  
  public int getResponseCode() {
    return this.mResponseCode;
  }
  
  public void setResponseCode(int responseCode) {
    this.mResponseCode = responseCode;
  }
  
  public String getResponseMessage() {
    return this.mResponseMessage;
  }
  
  public void setResponseMessage(String responseMessage) {
    this.mResponseMessage = responseMessage;
  }
  
  public byte[] getResponseContent() {
    return this.mResponseContent;
  }
  
  public String getResponseContentString() {
    if (this.mResponseContent != null)
      return new String(this.mResponseContent); 
    return null;
  }
  
  public void setResponseContent(byte[] responseContent) {
    this.mResponseContent = responseContent;
  }
}
