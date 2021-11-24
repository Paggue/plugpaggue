/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PlugPagMobTransactionResult
/*     */   extends PlugPagTransactionResult
/*     */   implements Serializable
/*     */ {
/*     */   static {
/*  14 */     PlugPagLibraryLoader.loadNativeLibraries();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  21 */   private String mCarrier = null;
/*  22 */   private String mPhoneNumber = null;
/*  23 */   private String mPaymentType = null;
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
/*     */   public PlugPagMobTransactionResult(PlugPagTransactionResult result, String carrierCod, String phoneNumber, String paymentType) {
/*  38 */     super(result.mMessage, result.mErrorCode, result.mTransactionCode, result.mTransactionId, result.mDate, result.mTime, result.mHostNsu, result.mCardBrand, result.mBin, result.mHolder, result.mUserReference, result.mTerminalSerialNumber, result.mAmount, result.mAvailableBalance, result.mCardApplication, result.mLabel, result.mHolderName, result.mExtendedHolderName, result.mNetworkPreference, result.mResult, result.mReaderModel, result.mNsu, result.mAutoCode, result.mInstallments, result.mOriginalAmount);
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
/*  65 */     this.mCarrier = carrierCod;
/*  66 */     this.mPhoneNumber = phoneNumber;
/*  67 */     this.mPaymentType = paymentType;
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
/*  80 */     return this.mMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTransactionCode() {
/*  89 */     return this.mTransactionCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDate() {
/*  98 */     return this.mDate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTime() {
/* 107 */     return this.mTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHostNsu() {
/* 116 */     return this.mHostNsu;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCardBrand() {
/* 125 */     return this.mCardBrand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBin() {
/* 134 */     return this.mBin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHolder() {
/* 143 */     return this.mHolder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserReference() {
/* 152 */     return this.mUserReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTerminalSerialNumber() {
/* 161 */     return this.mTerminalSerialNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getResult() {
/* 170 */     return this.mResult;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTransactionId() {
/* 179 */     return this.mTransactionId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getErrorCode() {
/* 188 */     return this.mErrorCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAmount() {
/* 197 */     return this.mAmount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAvailableBalance() {
/* 206 */     return this.mAvailableBalance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCardApplication() {
/* 215 */     return this.mCardApplication;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLabel() {
/* 224 */     return this.mLabel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHolderName() {
/* 233 */     return this.mHolderName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getExtendedHolderName() {
/* 242 */     return this.mExtendedHolderName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCarrier() {
/* 251 */     return this.mCarrier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPhoneNumber() {
/* 260 */     return this.mPhoneNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPaymentType() {
/* 269 */     return this.mPaymentType;
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagMobTransactionResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */