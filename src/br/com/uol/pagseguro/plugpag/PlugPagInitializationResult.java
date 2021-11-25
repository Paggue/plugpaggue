package br.com.uol.pagseguro.plugpag;

public final class PlugPagInitializationResult {
  private int mResult;
  
  private String mErrorCode;
  
  private String mErrorMessage;
  
  private int mHolderNetworkPreference;
  
  public PlugPagInitializationResult(int result, String errorCode, String errorMessage, int holderNetworkPreference) {
    this.mResult = result;
    this.mErrorCode = errorCode;
    this.mErrorMessage = errorMessage;
    this.mHolderNetworkPreference = holderNetworkPreference;
  }
  
  public int getResult() {
    return this.mResult;
  }
  
  public String getErrorCode() {
    return this.mErrorCode;
  }
  
  public String getErrorMessage() {
    return this.mErrorMessage;
  }
  
  public int getHolderNetworkPreference() {
    return this.mHolderNetworkPreference;
  }
}
