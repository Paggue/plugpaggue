package br.com.uol.pagseguro.plugpag;

import android.support.annotation.Nullable;
import br.com.uol.pagseguro.util.LogFunctions;
import java.io.Serializable;

public class PlugPagTransactionResult implements Serializable {
  static {
    PlugPagLibraryLoader.loadNativeLibraries();
  }
  
  protected int mResult = 0;
  
  protected String mMessage = null;
  
  protected String mErrorCode = null;
  
  protected String mTransactionCode = null;
  
  protected String mDate = null;
  
  protected String mTime = null;
  
  protected String mHostNsu = null;
  
  protected String mCardBrand = null;
  
  protected String mBin = null;
  
  protected String mHolder = null;
  
  protected String mUserReference = null;
  
  protected String mTerminalSerialNumber = null;
  
  protected String mTransactionId = null;
  
  protected String mAmount = null;
  
  protected String mAvailableBalance = null;
  
  protected String mCardApplication = null;
  
  protected String mLabel = null;
  
  protected String mHolderName = null;
  
  protected String mExtendedHolderName = null;
  
  protected int mNetworkPreference = 0;
  
  protected String mReaderModel = null;
  
  protected String mNsu = null;
  
  protected String mAutoCode = null;
  
  protected char mInstallments = '0';
  
  protected int mOriginalAmount = 0;
  
  public PlugPagTransactionResult(@Nullable String message, @Nullable String errorCode, @Nullable String transactionCode, @Nullable String transactionId, @Nullable String date, @Nullable String time, @Nullable String hostNsu, @Nullable String cardBrand, @Nullable String bin, @Nullable String holder, @Nullable String userReference, @Nullable String terminalSerialNumber, @Nullable String amount, @Nullable String availableBalance, @Nullable String cardApplication, @Nullable String label, @Nullable String holderName, @Nullable String extendedHolderName, int networkPreference, @Nullable String readerModel, @Nullable String nsu, @Nullable String autoCode, @Nullable char installments, @Nullable int originalAmount) {
    this.mMessage = message;
    this.mErrorCode = errorCode;
    this.mTransactionCode = transactionCode;
    this.mTransactionId = transactionId;
    this.mDate = date;
    this.mTime = time;
    this.mHostNsu = hostNsu;
    this.mCardBrand = cardBrand;
    this.mBin = bin;
    this.mHolder = holder;
    this.mUserReference = userReference;
    this.mTerminalSerialNumber = terminalSerialNumber;
    this.mAmount = amount;
    this.mAvailableBalance = availableBalance;
    this.mCardApplication = cardApplication;
    this.mLabel = label;
    this.mHolderName = holderName;
    this.mExtendedHolderName = extendedHolderName;
    this.mNetworkPreference = networkPreference;
    this.mReaderModel = readerModel;
    this.mNsu = nsu;
    this.mAutoCode = autoCode;
    this.mInstallments = installments;
    this.mOriginalAmount = originalAmount;
    log();
  }
  
  public PlugPagTransactionResult(@Nullable String message, @Nullable String errorCode, @Nullable String transactionCode, @Nullable String transactionId, @Nullable String date, @Nullable String time, @Nullable String hostNsu, @Nullable String cardBrand, @Nullable String bin, @Nullable String holder, @Nullable String userReference, @Nullable String terminalSerialNumber, @Nullable String amount, @Nullable String availableBalance, @Nullable String cardApplication, @Nullable String label, @Nullable String holderName, @Nullable String extendedHolderName, int networkPreference, int result, @Nullable String readerModel, @Nullable String nsu, @Nullable String autoCode, @Nullable char installments, @Nullable int originalAmount) {
    this.mMessage = message;
    this.mTransactionCode = transactionCode;
    this.mTransactionId = transactionId;
    this.mDate = date;
    this.mTime = time;
    this.mHostNsu = hostNsu;
    this.mCardBrand = cardBrand;
    this.mBin = bin;
    this.mHolder = holder;
    this.mUserReference = userReference;
    this.mTerminalSerialNumber = terminalSerialNumber;
    this.mResult = result;
    this.mErrorCode = errorCode;
    this.mAmount = amount;
    this.mCardApplication = cardApplication;
    this.mLabel = label;
    this.mHolderName = holderName;
    this.mExtendedHolderName = extendedHolderName;
    this.mAvailableBalance = availableBalance;
    this.mNetworkPreference = networkPreference;
    this.mReaderModel = readerModel;
    this.mNsu = nsu;
    this.mAutoCode = autoCode;
    this.mInstallments = installments;
    this.mOriginalAmount = originalAmount;
    log();
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
  
  public int getNetworkPreference() {
    return this.mNetworkPreference;
  }
  
  public String getReaderModel() {
    return this.mReaderModel;
  }
  
  public String getNsu() {
    return this.mNsu;
  }
  
  public String getAutoCode() {
    return this.mAutoCode;
  }
  
  public char getInstallments() {
    return this.mInstallments;
  }
  
  public int getOriginalAmount() {
    return this.mOriginalAmount;
  }
  
  public void log() {
    LogFunctions.log("PlugPagTransactionResult {");
    LogFunctions.log(" Result: " + this.mResult);
    LogFunctions.log(" Message: " + this.mMessage);
    LogFunctions.log(" ErrorCode: " + this.mErrorCode);
    LogFunctions.log(" TransactionCode: " + this.mTransactionCode);
    LogFunctions.log(" Date: " + this.mDate);
    LogFunctions.log(" Time: " + this.mTime);
    LogFunctions.log(" HostNsu: " + this.mHostNsu);
    LogFunctions.log(" CardBrand: " + this.mCardBrand);
    LogFunctions.log(" Bin: " + this.mBin);
    LogFunctions.log(" Holder: " + this.mHolder);
    LogFunctions.log(" UserReference: " + this.mUserReference);
    LogFunctions.log(" TerminalSerialNumber: " + this.mTerminalSerialNumber);
    LogFunctions.log(" TransactionId: " + this.mTransactionId);
    LogFunctions.log(" Amount: " + this.mAmount);
    LogFunctions.log(" AvailableBalance: " + this.mAvailableBalance);
    LogFunctions.log(" CardApplication: " + this.mCardApplication);
    LogFunctions.log(" Label: " + this.mLabel);
    LogFunctions.log(" HolderName: " + this.mHolderName);
    LogFunctions.log(" ExtendedHolderName: " + this.mExtendedHolderName);
    LogFunctions.log(" NetworkPreference: " + this.mNetworkPreference);
    LogFunctions.log(" ReaderModel: " + this.mReaderModel);
    LogFunctions.log(" Nsu: " + this.mNsu);
    LogFunctions.log(" AutoCode: " + this.mAutoCode);
    LogFunctions.log(" Installments: " + this.mInstallments);
    LogFunctions.log(" OriginalAmount: " + this.mOriginalAmount);
    LogFunctions.log("}");
  }
  
  public static final class Builder {
    private String mMessage = null;
    
    private String mErrorCode = null;
    
    private String mTransactionCode = null;
    
    private String mDate = null;
    
    private String mTime = null;
    
    private String mHostNsu = null;
    
    private String mCardBrand = null;
    
    private String mBin = null;
    
    private String mHolder = null;
    
    private String mUserReference = null;
    
    private String mTerminalSerialNumber = null;
    
    private String mAmount = null;
    
    private String mAvailableBalance = null;
    
    private String mTransactionId = null;
    
    private String mCardApplication = null;
    
    private String mLabel = null;
    
    private String mHolderName = null;
    
    private String mExtendedHolderName = null;
    
    private int mNetworkPreference = 0;
    
    private int mResult = 0;
    
    private String mReaderModel = null;
    
    private String mNsu = null;
    
    private String mAutoCode = null;
    
    private char mInstallments = '0';
    
    private int mOriginalAmount = 0;
    
    public Builder setMessage(String message) {
      this.mMessage = message;
      return this;
    }
    
    public Builder setErrorCode(String errorCode) {
      this.mErrorCode = errorCode;
      return this;
    }
    
    public Builder setTransactionCode(String transactionCode) {
      this.mTransactionCode = transactionCode;
      return this;
    }
    
    public Builder setDate(String date) {
      this.mDate = date;
      return this;
    }
    
    public Builder setTime(String time) {
      this.mTime = time;
      return this;
    }
    
    public Builder setHostNsu(String hostNsu) {
      this.mHostNsu = hostNsu;
      return this;
    }
    
    public Builder setCardBrand(String cardBrand) {
      this.mCardBrand = cardBrand;
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
    
    public Builder setUserReference(String userReference) {
      this.mUserReference = userReference;
      return this;
    }
    
    public Builder setTerminalSerialNumber(String terminalSerialNumber) {
      this.mTerminalSerialNumber = terminalSerialNumber;
      return this;
    }
    
    public Builder setAmount(String amount) {
      this.mAmount = amount;
      return this;
    }
    
    public Builder setAvailableBalance(String availableBalance) {
      this.mAvailableBalance = availableBalance;
      return this;
    }
    
    public Builder setTransactionId(String transactionId) {
      this.mTransactionId = transactionId;
      return this;
    }
    
    public Builder setCardApplication(String cardApplication) {
      this.mCardApplication = cardApplication;
      return this;
    }
    
    public Builder setLabel(String label) {
      this.mLabel = label;
      return this;
    }
    
    public Builder setHolderName(String holderName) {
      this.mHolderName = holderName;
      return this;
    }
    
    public Builder setExtendedHolderName(String extendedHolderName) {
      this.mExtendedHolderName = extendedHolderName;
      return this;
    }
    
    public Builder setNetworkPreference(int networkPreference) {
      this.mNetworkPreference = networkPreference;
      return this;
    }
    
    public Builder setResult(int result) {
      this.mResult = result;
      return this;
    }
    
    public Builder setReaderModel(String readerModel) {
      this.mReaderModel = readerModel;
      return this;
    }
    
    public Builder setNsu(String nsu) {
      this.mNsu = nsu;
      return this;
    }
    
    public Builder setAutoCode(String autoCode) {
      this.mAutoCode = autoCode;
      return this;
    }
    
    public Builder setInstallments(char installments) {
      this.mInstallments = installments;
      return this;
    }
    
    public Builder setOriginalAmount(int originalAmount) {
      this.mOriginalAmount = originalAmount;
      return this;
    }
    
    public PlugPagTransactionResult build() {
      return new PlugPagTransactionResult(this.mMessage, this.mErrorCode, this.mTransactionCode, this.mTransactionId, this.mDate, this.mTime, this.mHostNsu, this.mCardBrand, this.mBin, this.mHolder, this.mUserReference, this.mTerminalSerialNumber, this.mAmount, this.mAvailableBalance, this.mCardApplication, this.mLabel, this.mHolderName, this.mExtendedHolderName, this.mNetworkPreference, this.mResult, this.mReaderModel, this.mNsu, this.mAutoCode, this.mInstallments, this.mOriginalAmount);
    }
  }
}
