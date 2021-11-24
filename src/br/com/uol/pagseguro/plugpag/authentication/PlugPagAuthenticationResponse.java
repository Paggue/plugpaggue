/*     */ package br.com.uol.pagseguro.plugpag.authentication;
/*     */ 
/*     */ import android.support.annotation.NonNull;
/*     */ import com.google.gson.annotations.Expose;
/*     */ import com.google.gson.annotations.SerializedName;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class PlugPagAuthenticationResponse
/*     */ {
/*     */   @SerializedName("token")
/*     */   @Expose
/*  17 */   private String mToken = null;
/*     */   
/*     */   @SerializedName("operationResult")
/*     */   @Expose
/*  21 */   private OperationResult mOperationResult = null;
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
/*     */   public JSONObject toJson() throws JSONException {
/*  37 */     JSONObject json = null;
/*     */     
/*  39 */     return json;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getToken() {
/*  47 */     return this.mToken;
/*     */   }
/*     */   
/*     */   public OperationResult getOperationResult() {
/*  51 */     return this.mOperationResult;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class OperationResult
/*     */   {
/*     */     @SerializedName("status")
/*     */     @Expose
/*  64 */     private String mStatus = null;
/*     */     
/*     */     @SerializedName("message")
/*     */     @Expose
/*  68 */     private String mMessage = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public OperationResult(@NonNull String status, @NonNull String message) {
/*  77 */       this.mStatus = status;
/*  78 */       this.mMessage = message;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getStatus() {
/*  86 */       return this.mStatus;
/*     */     }
/*     */     
/*     */     public String getMessage() {
/*  90 */       return this.mMessage;
/*     */     }
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
/*     */   public static class Display
/*     */   {
/* 105 */     private String mTitle = null;
/* 106 */     private String mValue = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Display(@NonNull String title, @NonNull String value) {
/* 113 */       this.mTitle = title;
/* 114 */       this.mValue = value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getTitle() {
/* 122 */       return this.mTitle;
/*     */     }
/*     */     
/*     */     public String getValue() {
/* 126 */       return this.mValue;
/*     */     }
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
/*     */   public static class Receiver
/*     */   {
/* 141 */     private PlugPagAuthenticationResponse.Display mDisplayName = null;
/* 142 */     private PlugPagAuthenticationResponse.Display mUserName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Receiver(@NonNull PlugPagAuthenticationResponse.Display displayName, @NonNull PlugPagAuthenticationResponse.Display userName) {
/* 149 */       this.mDisplayName = displayName;
/* 150 */       this.mUserName = userName;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/authentication/PlugPagAuthenticationResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */