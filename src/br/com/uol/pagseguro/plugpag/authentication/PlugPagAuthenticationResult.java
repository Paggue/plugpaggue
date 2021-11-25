package br.com.uol.pagseguro.plugpag.authentication;

import android.support.annotation.Nullable;

final class PlugPagAuthenticationResult {
  private boolean mAuthenticated = false;
  
  private String mStatus = null;
  
  private String mMessage = null;
  
  public PlugPagAuthenticationResult(boolean authenticated, @Nullable String status, @Nullable String message) {
    this.mAuthenticated = authenticated;
    this.mStatus = status;
    this.mMessage = message;
  }
  
  public boolean isAuthenticated() {
    return this.mAuthenticated;
  }
  
  public String getStatus() {
    return this.mStatus;
  }
  
  public String getMessage() {
    return this.mMessage;
  }
  
  public static final class Builder {
    private boolean mAuthenticated = false;
    
    private String mStatus = null;
    
    private String mMessage = null;
    
    public void isAuthenticated(boolean authenticated) {
      this.mAuthenticated = authenticated;
    }
    
    public void setStatus(String status) {
      this.mStatus = status;
    }
    
    public void setMessage(String message) {
      this.mMessage = message;
    }
    
    public PlugPagAuthenticationResult build() {
      PlugPagAuthenticationResult authenticationResult = null;
      authenticationResult = new PlugPagAuthenticationResult(this.mAuthenticated, this.mStatus, this.mMessage);
      return authenticationResult;
    }
  }
}
