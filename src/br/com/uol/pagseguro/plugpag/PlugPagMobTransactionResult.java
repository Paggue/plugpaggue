package br.com.uol.pagseguro.plugpag;

import java.io.Serializable;

public final class PlugPagMobTransactionResult extends PlugPagTransactionResult implements Serializable {
  static {
    PlugPagLibraryLoader.loadNativeLibraries();
  }
  
  private String mCarrier = null;
  
  private String mPhoneNumber = null;
  
  private String mPaymentType = null;
  
  public PlugPagMobTransactionResult(PlugPagTransactionResult result, String carrierCod, String phoneNumber, String paymentType) {
    super(result.mMessage, result.mErrorCode, result.mTransactionCode, result.mTransactionId, result.mDate, result.mTime, result.mHostNsu, result.mCardBrand, result.mBin, result.mHolder, result.mUserReference, result.mTerminalSerialNumber, result.mAmount, result.mAvailableBalance, result.mCardApplication, result.mLabel, result.mHolderName, result.mExtendedHolderName, result.mNetworkPreference, result.mResult, result.mReaderModel, result.mNsu, result.mAutoCode, result.mInstallments, result.mOriginalAmount);
    this.mCarrier = carrierCod;
    this.mPhoneNumber = phoneNumber;
    this.mPaymentType = paymentType;
  }
  
  public String getMessage() {
    return this.mMessage;
  }
  
  public String getTransactionCode() {
    return this.mTransactionCode;
  }
  
  public String getDate() {
    return this.mDate;
  }
  
  public String getTime() {
    return this.mTime;
  }
  
  public String getHostNsu() {
    return this.mHostNsu;
  }
  
  public String getCardBrand() {
    return this.mCardBrand;
  }
  
  public String getBin() {
    return this.mBin;
  }
  
  public String getHolder() {
    return this.mHolder;
  }
  
  public String getUserReference() {
    return this.mUserReference;
  }
  
  public String getTerminalSerialNumber() {
    return this.mTerminalSerialNumber;
  }
  
  public int getResult() {
    return this.mResult;
  }
  
  public String getTransactionId() {
    return this.mTransactionId;
  }
  
  public String getErrorCode() {
    return this.mErrorCode;
  }
  
  public String getAmount() {
    return this.mAmount;
  }
  
  public String getAvailableBalance() {
    return this.mAvailableBalance;
  }
  
  public String getCardApplication() {
    return this.mCardApplication;
  }
  
  public String getLabel() {
    return this.mLabel;
  }
  
  public String getHolderName() {
    return this.mHolderName;
  }
  
  public String getExtendedHolderName() {
    return this.mExtendedHolderName;
  }
  
  public String getCarrier() {
    return this.mCarrier;
  }
  
  public String getPhoneNumber() {
    return this.mPhoneNumber;
  }
  
  public String getPaymentType() {
    return this.mPaymentType;
  }
}
