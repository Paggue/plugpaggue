package br.com.uol.pagseguro.plugpag.persistence;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "transaction_history")
public class TransactionHistory {
  @PrimaryKey(autoGenerate = true)
  private int id;
  
  @ColumnInfo(name = "result")
  private int result;
  
  @ColumnInfo(name = "message")
  private String message;
  
  @ColumnInfo(name = "error_code")
  private String errorCode;
  
  @ColumnInfo(name = "transaction_code")
  private String transactionCode;
  
  @ColumnInfo(name = "date")
  private String date;
  
  @ColumnInfo(name = "time")
  private String time;
  
  @ColumnInfo(name = "host_nsu")
  private String hostNsu;
  
  @ColumnInfo(name = "card_brand")
  private String cardBrand;
  
  @ColumnInfo(name = "bin")
  private String bin;
  
  @ColumnInfo(name = "holder")
  private String holder;
  
  @ColumnInfo(name = "user_reference")
  private String userReference;
  
  @ColumnInfo(name = "terminal_serial_number")
  private String terminalSerialNumber;
  
  @ColumnInfo(name = "transaction_id")
  private String transactionId;
  
  @ColumnInfo(name = "amount")
  private String amount;
  
  @ColumnInfo(name = "available_balance")
  private String availableBalance;
  
  @ColumnInfo(name = "card_application")
  private String cardApplication;
  
  @ColumnInfo(name = "label")
  private String label;
  
  @ColumnInfo(name = "holder_name")
  private String holderName;
  
  @ColumnInfo(name = "extended_holder_name")
  private String extendedHolderName;
  
  @ColumnInfo(name = "network_preference")
  private int networkPreference = 0;
  
  @ColumnInfo(name = "reader_model")
  private String readerModel;
  
  @ColumnInfo(name = "nsu")
  private String nsu;
  
  @ColumnInfo(name = "auto_code")
  private String autoCode;
  
  @ColumnInfo(name = "installments")
  private char installments;
  
  @ColumnInfo(name = "original_amount")
  private int originalAmount;
  
  @ColumnInfo(name = "date_transaction")
  private Date dateTransaction;
  
  @ColumnInfo(name = "app_identification")
  private String appIdentification;
  
  public TransactionHistory() {}
  
  public TransactionHistory(int result, String message, String errorCode, String transactionCode, String date, String time, String hostNsu, String cardBrand, String bin, String holder, String userReference, String terminalSerialNumber, String transactionId, String amount, String availableBalance, String cardApplication, String label, String holderName, String extendedHolderName, int networkPreference, String readerModel, String nsu, String autoCode, char installments, int originalAmount, String appIdentification, Date dateTransaction) {
    this.result = result;
    this.message = message;
    this.errorCode = errorCode;
    this.transactionCode = transactionCode;
    this.date = date;
    this.time = time;
    this.hostNsu = hostNsu;
    this.cardBrand = cardBrand;
    this.bin = bin;
    this.holder = holder;
    this.userReference = userReference;
    this.terminalSerialNumber = terminalSerialNumber;
    this.transactionId = transactionId;
    this.amount = amount;
    this.availableBalance = availableBalance;
    this.cardApplication = cardApplication;
    this.label = label;
    this.holderName = holderName;
    this.extendedHolderName = extendedHolderName;
    this.networkPreference = networkPreference;
    this.readerModel = readerModel;
    this.nsu = nsu;
    this.autoCode = autoCode;
    this.installments = installments;
    this.originalAmount = originalAmount;
    this.appIdentification = appIdentification;
    this.dateTransaction = dateTransaction;
  }
  
  public int getId() {
    return this.id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public int getResult() {
    return this.result;
  }
  
  public void setResult(int result) {
    this.result = result;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public String getErrorCode() {
    return this.errorCode;
  }
  
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }
  
  public String getTransactionCode() {
    return this.transactionCode;
  }
  
  public void setTransactionCode(String transactionCode) {
    this.transactionCode = transactionCode;
  }
  
  public String getDate() {
    return this.date;
  }
  
  public void setDate(String date) {
    this.date = date;
  }
  
  public String getTime() {
    return this.time;
  }
  
  public void setTime(String time) {
    this.time = time;
  }
  
  public String getHostNsu() {
    return this.hostNsu;
  }
  
  public void setHostNsu(String hostNsu) {
    this.hostNsu = hostNsu;
  }
  
  public String getCardBrand() {
    return this.cardBrand;
  }
  
  public void setCardBrand(String cardBrand) {
    this.cardBrand = cardBrand;
  }
  
  public String getBin() {
    return this.bin;
  }
  
  public void setBin(String bin) {
    this.bin = bin;
  }
  
  public String getHolder() {
    return this.holder;
  }
  
  public void setHolder(String holder) {
    this.holder = holder;
  }
  
  public String getUserReference() {
    return this.userReference;
  }
  
  public void setUserReference(String userReference) {
    this.userReference = userReference;
  }
  
  public String getTerminalSerialNumber() {
    return this.terminalSerialNumber;
  }
  
  public void setTerminalSerialNumber(String terminalSerialNumber) {
    this.terminalSerialNumber = terminalSerialNumber;
  }
  
  public String getTransactionId() {
    return this.transactionId;
  }
  
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }
  
  public String getAmount() {
    return this.amount;
  }
  
  public void setAmount(String amount) {
    this.amount = amount;
  }
  
  public String getAvailableBalance() {
    return this.availableBalance;
  }
  
  public void setAvailableBalance(String availableBalance) {
    this.availableBalance = availableBalance;
  }
  
  public String getCardApplication() {
    return this.cardApplication;
  }
  
  public void setCardApplication(String cardApplication) {
    this.cardApplication = cardApplication;
  }
  
  public String getLabel() {
    return this.label;
  }
  
  public void setLabel(String label) {
    this.label = label;
  }
  
  public String getHolderName() {
    return this.holderName;
  }
  
  public void setHolderName(String holderName) {
    this.holderName = holderName;
  }
  
  public String getExtendedHolderName() {
    return this.extendedHolderName;
  }
  
  public void setExtendedHolderName(String extendedHolderName) {
    this.extendedHolderName = extendedHolderName;
  }
  
  public int getNetworkPreference() {
    return this.networkPreference;
  }
  
  public void setNetworkPreference(int networkPreference) {
    this.networkPreference = networkPreference;
  }
  
  public String getReaderModel() {
    return this.readerModel;
  }
  
  public void setReaderModel(String readerModel) {
    this.readerModel = readerModel;
  }
  
  public String getNsu() {
    return this.nsu;
  }
  
  public void setNsu(String nsu) {
    this.nsu = nsu;
  }
  
  public String getAutoCode() {
    return this.autoCode;
  }
  
  public void setAutoCode(String autoCode) {
    this.autoCode = autoCode;
  }
  
  public char getInstallments() {
    return this.installments;
  }
  
  public void setInstallments(char installments) {
    this.installments = installments;
  }
  
  public int getOriginalAmount() {
    return this.originalAmount;
  }
  
  public void setOriginalAmount(int originalAmount) {
    this.originalAmount = originalAmount;
  }
  
  public String getAppIdentification() {
    return this.appIdentification;
  }
  
  public void setAppIdentification(String appIdentification) {
    this.appIdentification = appIdentification;
  }
  
  public Date getDateTransaction() {
    return this.dateTransaction;
  }
  
  public void setDateTransaction(Date dateTransaction) {
    this.dateTransaction = dateTransaction;
  }
  
  public String toString() {
    return "TransactionHistory{id=" + this.id + ", result=" + this.result + ", message='" + this.message + '\'' + ", errorCode='" + this.errorCode + '\'' + ", transactionCode='" + this.transactionCode + '\'' + ", date='" + this.date + '\'' + ", time='" + this.time + '\'' + ", hostNsu='" + this.hostNsu + '\'' + ", cardBrand='" + this.cardBrand + '\'' + ", bin='" + this.bin + '\'' + ", holder='" + this.holder + '\'' + ", userReference='" + this.userReference + '\'' + ", terminalSerialNumber='" + this.terminalSerialNumber + '\'' + ", transactionId='" + this.transactionId + '\'' + ", amount='" + this.amount + '\'' + ", availableBalance='" + this.availableBalance + '\'' + ", cardApplication='" + this.cardApplication + '\'' + ", label='" + this.label + '\'' + ", holderName='" + this.holderName + '\'' + ", extendedHolderName='" + this.extendedHolderName + '\'' + ", networkPreference=" + this.networkPreference + ", readerModel='" + this.readerModel + '\'' + ", nsu='" + this.nsu + '\'' + ", autoCode='" + this.autoCode + '\'' + ", installments=" + this.installments + ", originalAmount=" + this.originalAmount + ", dateTransaction=" + this.dateTransaction + ", appIdentification='" + this.appIdentification + '\'' + '}';
  }
}
