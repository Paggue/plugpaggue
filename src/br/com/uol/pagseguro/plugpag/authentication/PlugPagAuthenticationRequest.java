/*     */ package br.com.uol.pagseguro.plugpag.authentication;
/*     */ 
/*     */ import android.support.annotation.NonNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class PlugPagAuthenticationRequest
/*     */ {
/*  12 */   private String mEmail = null;
/*  13 */   private String mPassword = null;
/*  14 */   private String mDeviceId = null;
/*  15 */   private String mDeviceModel = null;
/*  16 */   private String mApplicationVersion = null;
/*  17 */   private String mApplicationCode = null;
/*  18 */   private String mOs = null;
/*  19 */   private String mOsVersion = null;
/*  20 */   private String mImei = null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlugPagAuthenticationRequest(@NonNull String email, @NonNull String password, @NonNull String deviceId, @NonNull String deviceModel, @NonNull String applicationCode, @NonNull String applicationVersion, @NonNull String os, @NonNull String osVersion, @NonNull String imei) {
/*  48 */     this.mEmail = email;
/*  49 */     this.mPassword = password;
/*  50 */     this.mDeviceId = deviceId;
/*  51 */     this.mDeviceModel = deviceModel;
/*  52 */     this.mApplicationCode = applicationCode;
/*  53 */     this.mApplicationVersion = applicationVersion;
/*  54 */     this.mOs = os;
/*  55 */     this.mOsVersion = osVersion;
/*  56 */     this.mImei = imei;
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
/*     */   public String getEmail() {
/*  69 */     return this.mEmail;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPassword() {
/*  78 */     return this.mPassword;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDeviceId() {
/*  87 */     return this.mDeviceId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDeviceModel() {
/*  96 */     return this.mDeviceModel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApplicationCode() {
/* 105 */     return this.mApplicationCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getApplicationVersion() {
/* 114 */     return this.mApplicationVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOs() {
/* 123 */     return this.mOs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOsVersion() {
/* 132 */     return this.mOsVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getImei() {
/* 141 */     return this.mImei;
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
/*     */   
/*     */   public static final class Builder
/*     */   {
/* 157 */     private String mDeviceModel = null;
/* 158 */     private String mEmail = null;
/* 159 */     private String mPassword = null;
/* 160 */     private String mAppVersion = null;
/* 161 */     private String mApplicationCode = null;
/* 162 */     private String mDeviceId = null;
/* 163 */     private String mImei = null;
/* 164 */     private String mOs = null;
/* 165 */     private String mOsVersion = null;
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
/*     */     public Builder setDeviceModel(String deviceModel) {
/* 178 */       this.mDeviceModel = deviceModel;
/*     */       
/* 180 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setEmail(String email) {
/* 190 */       this.mEmail = email;
/*     */       
/* 192 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setPassword(String password) {
/* 202 */       this.mPassword = password;
/*     */       
/* 204 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setAppVersion(String appVersion) {
/* 214 */       this.mAppVersion = appVersion;
/*     */       
/* 216 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setApplicationCode(String applicationCode) {
/* 226 */       this.mApplicationCode = applicationCode;
/*     */       
/* 228 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setDeviceId(String deviceId) {
/* 238 */       this.mDeviceId = deviceId;
/*     */       
/* 240 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setImei(String imei) {
/* 250 */       this.mImei = imei;
/*     */       
/* 252 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setOs(String os) {
/* 262 */       this.mOs = os;
/*     */       
/* 264 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setOsVersion(String osVersion) {
/* 274 */       this.mOsVersion = osVersion;
/*     */       
/* 276 */       return this;
/*     */     }
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
/*     */     public PlugPagAuthenticationRequest build() {
/* 289 */       return new PlugPagAuthenticationRequest(this.mEmail, this.mPassword, this.mDeviceId, this.mDeviceModel, this.mAppVersion, this.mApplicationCode, this.mOs, this.mOsVersion, this.mImei);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/authentication/PlugPagAuthenticationRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */