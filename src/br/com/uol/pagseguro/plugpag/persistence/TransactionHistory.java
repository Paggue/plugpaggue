/*     */ package br.com.uol.pagseguro.plugpag.persistence;
/*     */ 
/*     */ import android.arch.persistence.room.ColumnInfo;
/*     */ import android.arch.persistence.room.Entity;
/*     */ import android.arch.persistence.room.PrimaryKey;
/*     */ import java.util.Date;
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
/*     */ @Entity(tableName = "transaction_history")
/*     */ public class TransactionHistory
/*     */ {
/*     */   @PrimaryKey(autoGenerate = true)
/*     */   private int id;
/*     */   @ColumnInfo(name = "result")
/*     */   private int result;
/*     */   @ColumnInfo(name = "message")
/*     */   private String message;
/*     */   @ColumnInfo(name = "error_code")
/*     */   private String errorCode;
/*     */   @ColumnInfo(name = "transaction_code")
/*     */   private String transactionCode;
/*     */   @ColumnInfo(name = "date")
/*     */   private String date;
/*     */   @ColumnInfo(name = "time")
/*     */   private String time;
/*     */   @ColumnInfo(name = "host_nsu")
/*     */   private String hostNsu;
/*     */   @ColumnInfo(name = "card_brand")
/*     */   private String cardBrand;
/*     */   @ColumnInfo(name = "bin")
/*     */   private String bin;
/*     */   @ColumnInfo(name = "holder")
/*     */   private String holder;
/*     */   @ColumnInfo(name = "user_reference")
/*     */   private String userReference;
/*     */   @ColumnInfo(name = "terminal_serial_number")
/*     */   private String terminalSerialNumber;
/*     */   @ColumnInfo(name = "transaction_id")
/*     */   private String transactionId;
/*     */   @ColumnInfo(name = "amount")
/*     */   private String amount;
/*     */   @ColumnInfo(name = "available_balance")
/*     */   private String availableBalance;
/*     */   @ColumnInfo(name = "card_application")
/*     */   private String cardApplication;
/*     */   @ColumnInfo(name = "label")
/*     */   private String label;
/*     */   @ColumnInfo(name = "holder_name")
/*     */   private String holderName;
/*     */   @ColumnInfo(name = "extended_holder_name")
/*     */   private String extendedHolderName;
/*     */   @ColumnInfo(name = "network_preference")
/*  71 */   private int networkPreference = 0;
/*     */ 
/*     */   
/*     */   @ColumnInfo(name = "reader_model")
/*     */   private String readerModel;
/*     */ 
/*     */   
/*     */   @ColumnInfo(name = "nsu")
/*     */   private String nsu;
/*     */ 
/*     */   
/*     */   @ColumnInfo(name = "auto_code")
/*     */   private String autoCode;
/*     */ 
/*     */   
/*     */   @ColumnInfo(name = "installments")
/*     */   private char installments;
/*     */   
/*     */   @ColumnInfo(name = "original_amount")
/*     */   private int originalAmount;
/*     */   
/*     */   @ColumnInfo(name = "date_transaction")
/*     */   private Date dateTransaction;
/*     */   
/*     */   @ColumnInfo(name = "app_identification")
/*     */   private String appIdentification;
/*     */ 
/*     */   
/*     */   public TransactionHistory() {}
/*     */ 
/*     */   
/*     */   public TransactionHistory(int result, String message, String errorCode, String transactionCode, String date, String time, String hostNsu, String cardBrand, String bin, String holder, String userReference, String terminalSerialNumber, String transactionId, String amount, String availableBalance, String cardApplication, String label, String holderName, String extendedHolderName, int networkPreference, String readerModel, String nsu, String autoCode, char installments, int originalAmount, String appIdentification, Date dateTransaction) {
/* 103 */     this.result = result;
/* 104 */     this.message = message;
/* 105 */     this.errorCode = errorCode;
/* 106 */     this.transactionCode = transactionCode;
/* 107 */     this.date = date;
/* 108 */     this.time = time;
/* 109 */     this.hostNsu = hostNsu;
/* 110 */     this.cardBrand = cardBrand;
/* 111 */     this.bin = bin;
/* 112 */     this.holder = holder;
/* 113 */     this.userReference = userReference;
/* 114 */     this.terminalSerialNumber = terminalSerialNumber;
/* 115 */     this.transactionId = transactionId;
/* 116 */     this.amount = amount;
/* 117 */     this.availableBalance = availableBalance;
/* 118 */     this.cardApplication = cardApplication;
/* 119 */     this.label = label;
/* 120 */     this.holderName = holderName;
/* 121 */     this.extendedHolderName = extendedHolderName;
/* 122 */     this.networkPreference = networkPreference;
/* 123 */     this.readerModel = readerModel;
/* 124 */     this.nsu = nsu;
/* 125 */     this.autoCode = autoCode;
/* 126 */     this.installments = installments;
/* 127 */     this.originalAmount = originalAmount;
/* 128 */     this.appIdentification = appIdentification;
/* 129 */     this.dateTransaction = dateTransaction;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getId() {
/* 134 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(int id) {
/* 138 */     this.id = id;
/*     */   }
/*     */   
/*     */   public int getResult() {
/* 142 */     return this.result;
/*     */   }
/*     */   
/*     */   public void setResult(int result) {
/* 146 */     this.result = result;
/*     */   }
/*     */   
/*     */   public String getMessage() {
/* 150 */     return this.message;
/*     */   }
/*     */   
/*     */   public void setMessage(String message) {
/* 154 */     this.message = message;
/*     */   }
/*     */   
/*     */   public String getErrorCode() {
/* 158 */     return this.errorCode;
/*     */   }
/*     */   
/*     */   public void setErrorCode(String errorCode) {
/* 162 */     this.errorCode = errorCode;
/*     */   }
/*     */   
/*     */   public String getTransactionCode() {
/* 166 */     return this.transactionCode;
/*     */   }
/*     */   
/*     */   public void setTransactionCode(String transactionCode) {
/* 170 */     this.transactionCode = transactionCode;
/*     */   }
/*     */   
/*     */   public String getDate() {
/* 174 */     return this.date;
/*     */   }
/*     */   
/*     */   public void setDate(String date) {
/* 178 */     this.date = date;
/*     */   }
/*     */   
/*     */   public String getTime() {
/* 182 */     return this.time;
/*     */   }
/*     */   
/*     */   public void setTime(String time) {
/* 186 */     this.time = time;
/*     */   }
/*     */   
/*     */   public String getHostNsu() {
/* 190 */     return this.hostNsu;
/*     */   }
/*     */   
/*     */   public void setHostNsu(String hostNsu) {
/* 194 */     this.hostNsu = hostNsu;
/*     */   }
/*     */   
/*     */   public String getCardBrand() {
/* 198 */     return this.cardBrand;
/*     */   }
/*     */   
/*     */   public void setCardBrand(String cardBrand) {
/* 202 */     this.cardBrand = cardBrand;
/*     */   }
/*     */   
/*     */   public String getBin() {
/* 206 */     return this.bin;
/*     */   }
/*     */   
/*     */   public void setBin(String bin) {
/* 210 */     this.bin = bin;
/*     */   }
/*     */   
/*     */   public String getHolder() {
/* 214 */     return this.holder;
/*     */   }
/*     */   
/*     */   public void setHolder(String holder) {
/* 218 */     this.holder = holder;
/*     */   }
/*     */   
/*     */   public String getUserReference() {
/* 222 */     return this.userReference;
/*     */   }
/*     */   
/*     */   public void setUserReference(String userReference) {
/* 226 */     this.userReference = userReference;
/*     */   }
/*     */   
/*     */   public String getTerminalSerialNumber() {
/* 230 */     return this.terminalSerialNumber;
/*     */   }
/*     */   
/*     */   public void setTerminalSerialNumber(String terminalSerialNumber) {
/* 234 */     this.terminalSerialNumber = terminalSerialNumber;
/*     */   }
/*     */   
/*     */   public String getTransactionId() {
/* 238 */     return this.transactionId;
/*     */   }
/*     */   
/*     */   public void setTransactionId(String transactionId) {
/* 242 */     this.transactionId = transactionId;
/*     */   }
/*     */   
/*     */   public String getAmount() {
/* 246 */     return this.amount;
/*     */   }
/*     */   
/*     */   public void setAmount(String amount) {
/* 250 */     this.amount = amount;
/*     */   }
/*     */   
/*     */   public String getAvailableBalance() {
/* 254 */     return this.availableBalance;
/*     */   }
/*     */   
/*     */   public void setAvailableBalance(String availableBalance) {
/* 258 */     this.availableBalance = availableBalance;
/*     */   }
/*     */   
/*     */   public String getCardApplication() {
/* 262 */     return this.cardApplication;
/*     */   }
/*     */   
/*     */   public void setCardApplication(String cardApplication) {
/* 266 */     this.cardApplication = cardApplication;
/*     */   }
/*     */   
/*     */   public String getLabel() {
/* 270 */     return this.label;
/*     */   }
/*     */   
/*     */   public void setLabel(String label) {
/* 274 */     this.label = label;
/*     */   }
/*     */   
/*     */   public String getHolderName() {
/* 278 */     return this.holderName;
/*     */   }
/*     */   
/*     */   public void setHolderName(String holderName) {
/* 282 */     this.holderName = holderName;
/*     */   }
/*     */   
/*     */   public String getExtendedHolderName() {
/* 286 */     return this.extendedHolderName;
/*     */   }
/*     */   
/*     */   public void setExtendedHolderName(String extendedHolderName) {
/* 290 */     this.extendedHolderName = extendedHolderName;
/*     */   }
/*     */   
/*     */   public int getNetworkPreference() {
/* 294 */     return this.networkPreference;
/*     */   }
/*     */   
/*     */   public void setNetworkPreference(int networkPreference) {
/* 298 */     this.networkPreference = networkPreference;
/*     */   }
/*     */   
/*     */   public String getReaderModel() {
/* 302 */     return this.readerModel;
/*     */   }
/*     */   
/*     */   public void setReaderModel(String readerModel) {
/* 306 */     this.readerModel = readerModel;
/*     */   }
/*     */   
/*     */   public String getNsu() {
/* 310 */     return this.nsu;
/*     */   }
/*     */   
/*     */   public void setNsu(String nsu) {
/* 314 */     this.nsu = nsu;
/*     */   }
/*     */   
/*     */   public String getAutoCode() {
/* 318 */     return this.autoCode;
/*     */   }
/*     */   
/*     */   public void setAutoCode(String autoCode) {
/* 322 */     this.autoCode = autoCode;
/*     */   }
/*     */   
/*     */   public char getInstallments() {
/* 326 */     return this.installments;
/*     */   }
/*     */   
/*     */   public void setInstallments(char installments) {
/* 330 */     this.installments = installments;
/*     */   }
/*     */   
/*     */   public int getOriginalAmount() {
/* 334 */     return this.originalAmount;
/*     */   }
/*     */   
/*     */   public void setOriginalAmount(int originalAmount) {
/* 338 */     this.originalAmount = originalAmount;
/*     */   }
/*     */   
/*     */   public String getAppIdentification() {
/* 342 */     return this.appIdentification;
/*     */   }
/*     */   
/*     */   public void setAppIdentification(String appIdentification) {
/* 346 */     this.appIdentification = appIdentification;
/*     */   }
/*     */   
/*     */   public Date getDateTransaction() {
/* 350 */     return this.dateTransaction;
/*     */   }
/*     */   
/*     */   public void setDateTransaction(Date dateTransaction) {
/* 354 */     this.dateTransaction = dateTransaction;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 359 */     return "TransactionHistory{id=" + this.id + ", result=" + this.result + ", message='" + this.message + '\'' + ", errorCode='" + this.errorCode + '\'' + ", transactionCode='" + this.transactionCode + '\'' + ", date='" + this.date + '\'' + ", time='" + this.time + '\'' + ", hostNsu='" + this.hostNsu + '\'' + ", cardBrand='" + this.cardBrand + '\'' + ", bin='" + this.bin + '\'' + ", holder='" + this.holder + '\'' + ", userReference='" + this.userReference + '\'' + ", terminalSerialNumber='" + this.terminalSerialNumber + '\'' + ", transactionId='" + this.transactionId + '\'' + ", amount='" + this.amount + '\'' + ", availableBalance='" + this.availableBalance + '\'' + ", cardApplication='" + this.cardApplication + '\'' + ", label='" + this.label + '\'' + ", holderName='" + this.holderName + '\'' + ", extendedHolderName='" + this.extendedHolderName + '\'' + ", networkPreference=" + this.networkPreference + ", readerModel='" + this.readerModel + '\'' + ", nsu='" + this.nsu + '\'' + ", autoCode='" + this.autoCode + '\'' + ", installments=" + this.installments + ", originalAmount=" + this.originalAmount + ", dateTransaction=" + this.dateTransaction + ", appIdentification='" + this.appIdentification + '\'' + '}';
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/persistence/TransactionHistory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */