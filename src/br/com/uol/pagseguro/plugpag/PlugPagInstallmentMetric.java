package br.com.uol.pagseguro.plugpag;

import java.util.HashMap;
import java.util.Map;

public class PlugPagInstallmentMetric {
  private int installments;
  
  private int installmentType;
  
  private int paymentMethod;
  
  private int aError;
  
  private int ppError;
  
  private int libSwitchError;
  
  private int switchError;
  
  private int mobileError;
  
  private int acquirerError;
  
  private int bcError;
  
  private String idSimCard;
  
  private String bin;
  
  private String holder;
  
  private String totalAmount;
  
  private String amount;
  
  public PlugPagInstallmentMetric(int installments, int installmentType, int paymentMethod, int aError, int ppError, int libSwitchError, int switchError, int mobileError, int acquirerError, int bcError, String idSimCard, String bin, String holder, String totalAmount, String amount) {
    this.installments = installments;
    this.installmentType = installmentType;
    this.paymentMethod = paymentMethod;
    this.aError = aError;
    this.ppError = ppError;
    this.libSwitchError = libSwitchError;
    this.switchError = switchError;
    this.mobileError = mobileError;
    this.acquirerError = acquirerError;
    this.bcError = bcError;
    this.idSimCard = idSimCard;
    this.bin = bin;
    this.holder = holder;
    this.totalAmount = totalAmount;
    this.amount = amount;
  }
  
  public int getInstallments() {
    return this.installments;
  }
  
  public int getInstallmentType() {
    return this.installmentType;
  }
  
  public int getPaymentMethod() {
    return this.paymentMethod;
  }
  
  public int getAError() {
    return this.aError;
  }
  
  public int getPpError() {
    return this.ppError;
  }
  
  public int getLibSwitchError() {
    return this.libSwitchError;
  }
  
  public int getSwitchError() {
    return this.switchError;
  }
  
  public int getMobileError() {
    return this.mobileError;
  }
  
  public int getBcError() {
    return this.bcError;
  }
  
  public String getIdSimCard() {
    return this.idSimCard;
  }
  
  public String getBin() {
    return this.bin;
  }
  
  public String getHolder() {
    return this.holder;
  }
  
  public String getTotalAmount() {
    return this.totalAmount;
  }
  
  public String getAmount() {
    return this.amount;
  }
  
  public int getAcquirerError() {
    return this.acquirerError;
  }
  
  public Map<String, Object> toInstallmentMap() {
    Map<String, Object> installmentMap = new HashMap<>();
    installmentMap.put("PlugPagInstallmentQuantity", Integer.valueOf(getInstallments()));
    installmentMap.put("PlugPagInstallmentType", Integer.valueOf(getInstallmentType()));
    installmentMap.put("PlugPagInstallmentPaymentMethod", Integer.valueOf(getPaymentMethod()));
    installmentMap.put("PlugPagInstallmentAError", Integer.valueOf(getAError()));
    installmentMap.put("PlugPagInstallmentPPError", Integer.valueOf(getPpError()));
    installmentMap.put("PlugPagInstallmentLibSwitchError", Integer.valueOf(getLibSwitchError()));
    installmentMap.put("PlugPagInstallmentSwitchError", Integer.valueOf(getSwitchError()));
    installmentMap.put("PlugPagInstallmentMobileError", Integer.valueOf(getMobileError()));
    installmentMap.put("PlugPagInstallmentBcError", Integer.valueOf(getBcError()));
    installmentMap.put("PlugPagInstallmentIdSimCard", getIdSimCard());
    installmentMap.put("PlugPagInstallmentBin", getBin());
    installmentMap.put("PlugPagInstallmentHolder", getHolder());
    installmentMap.put("PlugPagInstallmentTotalAmount", getTotalAmount());
    installmentMap.put("PlugPagInstallmentAmount", getAmount());
    installmentMap.put("PlugPagInstallmentAcquirerError", Integer.valueOf(getAcquirerError()));
    return installmentMap;
  }
}
