package br.com.uol.pagseguro.plugpag;

import android.support.annotation.Nullable;

public class PlugPagCardInfo {
  private String mResult = null;
  
  private String mMessage = null;
  
  private String mBin = null;
  
  private String mHolder = null;
  
  private String mCardHolder = null;
  
  public PlugPagCardInfo(@Nullable String result, @Nullable String message, @Nullable String bin, @Nullable String holder, @Nullable String cardHolder) {
    this.mResult = result;
    this.mMessage = message;
    this.mBin = bin;
    this.mHolder = holder;
    this.mCardHolder = cardHolder;
  }
  
  public String getResult() {
    return this.mResult;
  }
  
  public String getMessage() {
    return this.mMessage;
  }
  
  public String getBin() {
    return this.mBin;
  }
  
  public String getHolder() {
    return this.mHolder;
  }
  
  public String getCardHolder() {
    return this.mCardHolder;
  }
  
  public static class Builder {
    private String mResult = null;
    
    private String mMessage = null;
    
    private String mBin = null;
    
    private String mHolder = null;
    
    private String mCardHolder = null;
    
    public Builder setResult(String result) {
      this.mResult = result;
      return this;
    }
    
    public Builder setMessage(String message) {
      this.mMessage = message;
      return this;
    }
    
    public Builder setBin(String bin) {
      this.mBin = bin;
      return this;
    }
    
    public Builder setHolder(String holder) {
      this.mHolder = holder;
      return this;
    }
    
    public Builder setCardHolder(String cardHolder) {
      this.mCardHolder = cardHolder;
      return this;
    }
    
    public PlugPagCardInfo build() {
      return new PlugPagCardInfo(this.mResult, this.mMessage, this.mBin, this.mHolder, this.mCardHolder);
    }
  }
}
