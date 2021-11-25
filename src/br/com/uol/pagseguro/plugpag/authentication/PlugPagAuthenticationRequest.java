package br.com.uol.pagseguro.plugpag.authentication;

import android.support.annotation.NonNull;

class PlugPagAuthenticationRequest {
  private String mEmail = null;
  
  private String mPassword = null;
  
  private String mDeviceId = null;
  
  private String mDeviceModel = null;
  
  private String mApplicationVersion = null;
  
  private String mApplicationCode = null;
  
  private String mOs = null;
  
  private String mOsVersion = null;
  
  private String mImei = null;
  
  public PlugPagAuthenticationRequest(@NonNull String email, @NonNull String password, @NonNull String deviceId, @NonNull String deviceModel, @NonNull String applicationCode, @NonNull String applicationVersion, @NonNull String os, @NonNull String osVersion, @NonNull String imei) {
    this.mEmail = email;
    this.mPassword = password;
    this.mDeviceId = deviceId;
    this.mDeviceModel = deviceModel;
    this.mApplicationCode = applicationCode;
    this.mApplicationVersion = applicationVersion;
    this.mOs = os;
    this.mOsVersion = osVersion;
    this.mImei = imei;
  }
  
  public String getEmail() {
    return this.mEmail;
  }
  
  public String getPassword() {
    return this.mPassword;
  }
  
  public String getDeviceId() {
    return this.mDeviceId;
  }
  
  public String getDeviceModel() {
    return this.mDeviceModel;
  }
  
  public String getApplicationCode() {
    return this.mApplicationCode;
  }
  
  public String getApplicationVersion() {
    return this.mApplicationVersion;
  }
  
  public String getOs() {
    return this.mOs;
  }
  
  public String getOsVersion() {
    return this.mOsVersion;
  }
  
  public String getImei() {
    return this.mImei;
  }
  
  public static final class Builder {
    private String mDeviceModel = null;
    
    private String mEmail = null;
    
    private String mPassword = null;
    
    private String mAppVersion = null;
    
    private String mApplicationCode = null;
    
    private String mDeviceId = null;
    
    private String mImei = null;
    
    private String mOs = null;
    
    private String mOsVersion = null;
    
    public Builder setDeviceModel(String deviceModel) {
      this.mDeviceModel = deviceModel;
      return this;
    }
    
    public Builder setEmail(String email) {
      this.mEmail = email;
      return this;
    }
    
    public Builder setPassword(String password) {
      this.mPassword = password;
      return this;
    }
    
    public Builder setAppVersion(String appVersion) {
      this.mAppVersion = appVersion;
      return this;
    }
    
    public Builder setApplicationCode(String applicationCode) {
      this.mApplicationCode = applicationCode;
      return this;
    }
    
    public Builder setDeviceId(String deviceId) {
      this.mDeviceId = deviceId;
      return this;
    }
    
    public Builder setImei(String imei) {
      this.mImei = imei;
      return this;
    }
    
    public Builder setOs(String os) {
      this.mOs = os;
      return this;
    }
    
    public Builder setOsVersion(String osVersion) {
      this.mOsVersion = osVersion;
      return this;
    }
    
    public PlugPagAuthenticationRequest build() {
      return new PlugPagAuthenticationRequest(this.mEmail, this.mPassword, this.mDeviceId, this.mDeviceModel, this.mAppVersion, this.mApplicationCode, this.mOs, this.mOsVersion, this.mImei);
    }
  }
}
