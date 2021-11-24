/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import android.support.annotation.Nullable;
/*     */ import br.com.uol.pagseguro.util.LogFunctions;
/*     */ import java.io.Serializable;
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
/*     */ public class PlugPagTransactionResult
/*     */   implements Serializable
/*     */ {
/*     */   static {
/*  20 */     PlugPagLibraryLoader.loadNativeLibraries();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  27 */   protected int mResult = 0;
/*  28 */   protected String mMessage = null;
/*  29 */   protected String mErrorCode = null;
/*  30 */   protected String mTransactionCode = null;
/*  31 */   protected String mDate = null;
/*  32 */   protected String mTime = null;
/*  33 */   protected String mHostNsu = null;
/*  34 */   protected String mCardBrand = null;
/*  35 */   protected String mBin = null;
/*  36 */   protected String mHolder = null;
/*  37 */   protected String mUserReference = null;
/*  38 */   protected String mTerminalSerialNumber = null;
/*  39 */   protected String mTransactionId = null;
/*  40 */   protected String mAmount = null;
/*  41 */   protected String mAvailableBalance = null;
/*  42 */   protected String mCardApplication = null;
/*  43 */   protected String mLabel = null;
/*  44 */   protected String mHolderName = null;
/*  45 */   protected String mExtendedHolderName = null;
/*  46 */   protected int mNetworkPreference = 0;
/*  47 */   protected String mReaderModel = null;
/*  48 */   protected String mNsu = null;
/*  49 */   protected String mAutoCode = null;
/*  50 */   protected char mInstallments = '0';
/*  51 */   protected int mOriginalAmount = 0;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlugPagTransactionResult(@Nullable String message, @Nullable String errorCode, @Nullable String transactionCode, @Nullable String transactionId, @Nullable String date, @Nullable String time, @Nullable String hostNsu, @Nullable String cardBrand, @Nullable String bin, @Nullable String holder, @Nullable String userReference, @Nullable String terminalSerialNumber, @Nullable String amount, @Nullable String availableBalance, @Nullable String cardApplication, @Nullable String label, @Nullable String holderName, @Nullable String extendedHolderName, int networkPreference, @Nullable String readerModel, @Nullable String nsu, @Nullable String autoCode, @Nullable char installments, @Nullable int originalAmount) {
/* 111 */     this.mMessage = message;
/* 112 */     this.mErrorCode = errorCode;
/* 113 */     this.mTransactionCode = transactionCode;
/* 114 */     this.mTransactionId = transactionId;
/* 115 */     this.mDate = date;
/* 116 */     this.mTime = time;
/* 117 */     this.mHostNsu = hostNsu;
/* 118 */     this.mCardBrand = cardBrand;
/* 119 */     this.mBin = bin;
/* 120 */     this.mHolder = holder;
/* 121 */     this.mUserReference = userReference;
/* 122 */     this.mTerminalSerialNumber = terminalSerialNumber;
/* 123 */     this.mAmount = amount;
/* 124 */     this.mAvailableBalance = availableBalance;
/* 125 */     this.mCardApplication = cardApplication;
/* 126 */     this.mLabel = label;
/* 127 */     this.mHolderName = holderName;
/* 128 */     this.mExtendedHolderName = extendedHolderName;
/* 129 */     this.mNetworkPreference = networkPreference;
/* 130 */     this.mReaderModel = readerModel;
/* 131 */     this.mNsu = nsu;
/* 132 */     this.mAutoCode = autoCode;
/* 133 */     this.mInstallments = installments;
/* 134 */     this.mOriginalAmount = originalAmount;
/*     */     
/* 136 */     log();
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
/*     */   public PlugPagTransactionResult(@Nullable String message, @Nullable String errorCode, @Nullable String transactionCode, @Nullable String transactionId, @Nullable String date, @Nullable String time, @Nullable String hostNsu, @Nullable String cardBrand, @Nullable String bin, @Nullable String holder, @Nullable String userReference, @Nullable String terminalSerialNumber, @Nullable String amount, @Nullable String availableBalance, @Nullable String cardApplication, @Nullable String label, @Nullable String holderName, @Nullable String extendedHolderName, int networkPreference, int result, @Nullable String readerModel, @Nullable String nsu, @Nullable String autoCode, @Nullable char installments, @Nullable int originalAmount) {
/* 195 */     this.mMessage = message;
/* 196 */     this.mTransactionCode = transactionCode;
/* 197 */     this.mTransactionId = transactionId;
/* 198 */     this.mDate = date;
/* 199 */     this.mTime = time;
/* 200 */     this.mHostNsu = hostNsu;
/* 201 */     this.mCardBrand = cardBrand;
/* 202 */     this.mBin = bin;
/* 203 */     this.mHolder = holder;
/* 204 */     this.mUserReference = userReference;
/* 205 */     this.mTerminalSerialNumber = terminalSerialNumber;
/* 206 */     this.mResult = result;
/* 207 */     this.mErrorCode = errorCode;
/* 208 */     this.mAmount = amount;
/* 209 */     this.mCardApplication = cardApplication;
/* 210 */     this.mLabel = label;
/* 211 */     this.mHolderName = holderName;
/* 212 */     this.mExtendedHolderName = extendedHolderName;
/* 213 */     this.mAvailableBalance = availableBalance;
/* 214 */     this.mNetworkPreference = networkPreference;
/* 215 */     this.mReaderModel = readerModel;
/* 216 */     this.mNsu = nsu;
/* 217 */     this.mAutoCode = autoCode;
/* 218 */     this.mInstallments = installments;
/* 219 */     this.mOriginalAmount = originalAmount;
/*     */     
/* 221 */     log();
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
/*     */   public String getMessage() {
/* 234 */     return this.mMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTransactionCode() {
/* 243 */     return this.mTransactionCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDate() {
/* 252 */     return this.mDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTime() {
/* 261 */     return this.mTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHostNsu() {
/* 270 */     return this.mHostNsu;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCardBrand() {
/* 279 */     return this.mCardBrand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBin() {
/* 288 */     return this.mBin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHolder() {
/* 297 */     return this.mHolder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserReference() {
/* 306 */     return this.mUserReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTerminalSerialNumber() {
/* 315 */     return this.mTerminalSerialNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getResult() {
/* 324 */     return this.mResult;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTransactionId() {
/* 333 */     return this.mTransactionId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getErrorCode() {
/* 342 */     return this.mErrorCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAmount() {
/* 351 */     return this.mAmount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAvailableBalance() {
/* 360 */     return this.mAvailableBalance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCardApplication() {
/* 369 */     return this.mCardApplication;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLabel() {
/* 378 */     return this.mLabel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHolderName() {
/* 387 */     return this.mHolderName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExtendedHolderName() {
/* 396 */     return this.mExtendedHolderName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNetworkPreference() {
/* 405 */     return this.mNetworkPreference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReaderModel() {
/* 414 */     return this.mReaderModel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNsu() {
/* 423 */     return this.mNsu;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAutoCode() {
/* 432 */     return this.mAutoCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getInstallments() {
/* 441 */     return this.mInstallments;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOriginalAmount() {
/* 450 */     return this.mOriginalAmount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void log() {
/* 458 */     LogFunctions.log("PlugPagTransactionResult {");
/* 459 */     LogFunctions.log(" Result: " + this.mResult);
/* 460 */     LogFunctions.log(" Message: " + this.mMessage);
/* 461 */     LogFunctions.log(" ErrorCode: " + this.mErrorCode);
/* 462 */     LogFunctions.log(" TransactionCode: " + this.mTransactionCode);
/* 463 */     LogFunctions.log(" Date: " + this.mDate);
/* 464 */     LogFunctions.log(" Time: " + this.mTime);
/* 465 */     LogFunctions.log(" HostNsu: " + this.mHostNsu);
/* 466 */     LogFunctions.log(" CardBrand: " + this.mCardBrand);
/* 467 */     LogFunctions.log(" Bin: " + this.mBin);
/* 468 */     LogFunctions.log(" Holder: " + this.mHolder);
/* 469 */     LogFunctions.log(" UserReference: " + this.mUserReference);
/* 470 */     LogFunctions.log(" TerminalSerialNumber: " + this.mTerminalSerialNumber);
/* 471 */     LogFunctions.log(" TransactionId: " + this.mTransactionId);
/* 472 */     LogFunctions.log(" Amount: " + this.mAmount);
/* 473 */     LogFunctions.log(" AvailableBalance: " + this.mAvailableBalance);
/* 474 */     LogFunctions.log(" CardApplication: " + this.mCardApplication);
/* 475 */     LogFunctions.log(" Label: " + this.mLabel);
/* 476 */     LogFunctions.log(" HolderName: " + this.mHolderName);
/* 477 */     LogFunctions.log(" ExtendedHolderName: " + this.mExtendedHolderName);
/* 478 */     LogFunctions.log(" NetworkPreference: " + this.mNetworkPreference);
/* 479 */     LogFunctions.log(" ReaderModel: " + this.mReaderModel);
/* 480 */     LogFunctions.log(" Nsu: " + this.mNsu);
/* 481 */     LogFunctions.log(" AutoCode: " + this.mAutoCode);
/* 482 */     LogFunctions.log(" Installments: " + this.mInstallments);
/* 483 */     LogFunctions.log(" OriginalAmount: " + this.mOriginalAmount);
/* 484 */     LogFunctions.log("}");
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
/* 500 */     private String mMessage = null;
/* 501 */     private String mErrorCode = null;
/* 502 */     private String mTransactionCode = null;
/* 503 */     private String mDate = null;
/* 504 */     private String mTime = null;
/* 505 */     private String mHostNsu = null;
/* 506 */     private String mCardBrand = null;
/* 507 */     private String mBin = null;
/* 508 */     private String mHolder = null;
/* 509 */     private String mUserReference = null;
/* 510 */     private String mTerminalSerialNumber = null;
/* 511 */     private String mAmount = null;
/* 512 */     private String mAvailableBalance = null;
/* 513 */     private String mTransactionId = null;
/* 514 */     private String mCardApplication = null;
/* 515 */     private String mLabel = null;
/* 516 */     private String mHolderName = null;
/* 517 */     private String mExtendedHolderName = null;
/* 518 */     private int mNetworkPreference = 0;
/* 519 */     private int mResult = 0;
/* 520 */     private String mReaderModel = null;
/* 521 */     private String mNsu = null;
/* 522 */     private String mAutoCode = null;
/* 523 */     private char mInstallments = '0';
/* 524 */     private int mOriginalAmount = 0;
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
/*     */     public Builder setMessage(String message) {
/* 537 */       this.mMessage = message;
/*     */       
/* 539 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setErrorCode(String errorCode) {
/* 549 */       this.mErrorCode = errorCode;
/*     */       
/* 551 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setTransactionCode(String transactionCode) {
/* 561 */       this.mTransactionCode = transactionCode;
/*     */       
/* 563 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setDate(String date) {
/* 573 */       this.mDate = date;
/*     */       
/* 575 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setTime(String time) {
/* 585 */       this.mTime = time;
/*     */       
/* 587 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setHostNsu(String hostNsu) {
/* 597 */       this.mHostNsu = hostNsu;
/*     */       
/* 599 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setCardBrand(String cardBrand) {
/* 609 */       this.mCardBrand = cardBrand;
/*     */       
/* 611 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setBin(String bin) {
/* 621 */       this.mBin = bin;
/*     */       
/* 623 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setHolder(String holder) {
/* 633 */       this.mHolder = holder;
/*     */       
/* 635 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setUserReference(String userReference) {
/* 645 */       this.mUserReference = userReference;
/*     */       
/* 647 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setTerminalSerialNumber(String terminalSerialNumber) {
/* 657 */       this.mTerminalSerialNumber = terminalSerialNumber;
/*     */       
/* 659 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setAmount(String amount) {
/* 669 */       this.mAmount = amount;
/*     */       
/* 671 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setAvailableBalance(String availableBalance) {
/* 681 */       this.mAvailableBalance = availableBalance;
/*     */       
/* 683 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setTransactionId(String transactionId) {
/* 693 */       this.mTransactionId = transactionId;
/*     */       
/* 695 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setCardApplication(String cardApplication) {
/* 705 */       this.mCardApplication = cardApplication;
/*     */       
/* 707 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setLabel(String label) {
/* 717 */       this.mLabel = label;
/*     */       
/* 719 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setHolderName(String holderName) {
/* 729 */       this.mHolderName = holderName;
/*     */       
/* 731 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setExtendedHolderName(String extendedHolderName) {
/* 741 */       this.mExtendedHolderName = extendedHolderName;
/*     */       
/* 743 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setNetworkPreference(int networkPreference) {
/* 753 */       this.mNetworkPreference = networkPreference;
/*     */       
/* 755 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setResult(int result) {
/* 765 */       this.mResult = result;
/*     */       
/* 767 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setReaderModel(String readerModel) {
/* 777 */       this.mReaderModel = readerModel;
/*     */       
/* 779 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setNsu(String nsu) {
/* 789 */       this.mNsu = nsu;
/*     */       
/* 791 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setAutoCode(String autoCode) {
/* 801 */       this.mAutoCode = autoCode;
/*     */       
/* 803 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setInstallments(char installments) {
/* 813 */       this.mInstallments = installments;
/*     */       
/* 815 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setOriginalAmount(int originalAmount) {
/* 825 */       this.mOriginalAmount = originalAmount;
/*     */       
/* 827 */       return this;
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
/*     */     public PlugPagTransactionResult build() {
/* 840 */       return new PlugPagTransactionResult(this.mMessage, this.mErrorCode, this.mTransactionCode, this.mTransactionId, this.mDate, this.mTime, this.mHostNsu, this.mCardBrand, this.mBin, this.mHolder, this.mUserReference, this.mTerminalSerialNumber, this.mAmount, this.mAvailableBalance, this.mCardApplication, this.mLabel, this.mHolderName, this.mExtendedHolderName, this.mNetworkPreference, this.mResult, this.mReaderModel, this.mNsu, this.mAutoCode, this.mInstallments, this.mOriginalAmount);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagTransactionResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */