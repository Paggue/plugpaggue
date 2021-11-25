package br.com.uol.pagseguro.plugpag.authentication;

import android.support.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.json.JSONException;
import org.json.JSONObject;

class PlugPagAuthenticationResponse {
  @SerializedName("token")
  @Expose
  private String mToken = null;
  
  @SerializedName("operationResult")
  @Expose
  private OperationResult mOperationResult = null;
  
  public JSONObject toJson() throws JSONException {
    JSONObject json = null;
    return json;
  }
  
  public String getToken() {
    return this.mToken;
  }
  
  public OperationResult getOperationResult() {
    return this.mOperationResult;
  }
  
  public static class OperationResult {
    @SerializedName("status")
    @Expose
    private String mStatus = null;
    
    @SerializedName("message")
    @Expose
    private String mMessage = null;
    
    public OperationResult(@NonNull String status, @NonNull String message) {
      this.mStatus = status;
      this.mMessage = message;
    }
    
    public String getStatus() {
      return this.mStatus;
    }
    
    public String getMessage() {
      return this.mMessage;
    }
  }
  
  public static class Display {
    private String mTitle = null;
    
    private String mValue = null;
    
    public Display(@NonNull String title, @NonNull String value) {
      this.mTitle = title;
      this.mValue = value;
    }
    
    public String getTitle() {
      return this.mTitle;
    }
    
    public String getValue() {
      return this.mValue;
    }
  }
  
  public static class Receiver {
    private PlugPagAuthenticationResponse.Display mDisplayName = null;
    
    private PlugPagAuthenticationResponse.Display mUserName = null;
    
    public Receiver(@NonNull PlugPagAuthenticationResponse.Display displayName, @NonNull PlugPagAuthenticationResponse.Display userName) {
      this.mDisplayName = displayName;
      this.mUserName = userName;
    }
  }
}
