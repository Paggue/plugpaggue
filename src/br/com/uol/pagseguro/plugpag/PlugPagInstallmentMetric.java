/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class PlugPagInstallmentMetric
/*     */ {
/*     */   private int installments;
/*     */   private int installmentType;
/*     */   private int paymentMethod;
/*     */   private int aError;
/*     */   private int ppError;
/*     */   private int libSwitchError;
/*     */   private int switchError;
/*     */   private int mobileError;
/*     */   private int acquirerError;
/*     */   private int bcError;
/*     */   private String idSimCard;
/*     */   private String bin;
/*     */   private String holder;
/*     */   private String totalAmount;
/*     */   private String amount;
/*     */   
/*     */   public PlugPagInstallmentMetric(int installments, int installmentType, int paymentMethod, int aError, int ppError, int libSwitchError, int switchError, int mobileError, int acquirerError, int bcError, String idSimCard, String bin, String holder, String totalAmount, String amount) {
/*  38 */     this.installments = installments;
/*  39 */     this.installmentType = installmentType;
/*  40 */     this.paymentMethod = paymentMethod;
/*  41 */     this.aError = aError;
/*  42 */     this.ppError = ppError;
/*  43 */     this.libSwitchError = libSwitchError;
/*  44 */     this.switchError = switchError;
/*  45 */     this.mobileError = mobileError;
/*  46 */     this.acquirerError = acquirerError;
/*  47 */     this.bcError = bcError;
/*  48 */     this.idSimCard = idSimCard;
/*  49 */     this.bin = bin;
/*  50 */     this.holder = holder;
/*  51 */     this.totalAmount = totalAmount;
/*  52 */     this.amount = amount;
/*     */   }
/*     */   
/*     */   public int getInstallments() {
/*  56 */     return this.installments;
/*     */   }
/*     */   
/*     */   public int getInstallmentType() {
/*  60 */     return this.installmentType;
/*     */   }
/*     */   
/*     */   public int getPaymentMethod() {
/*  64 */     return this.paymentMethod;
/*     */   }
/*     */   
/*     */   public int getAError() {
/*  68 */     return this.aError;
/*     */   }
/*     */   
/*     */   public int getPpError() {
/*  72 */     return this.ppError;
/*     */   }
/*     */   
/*     */   public int getLibSwitchError() {
/*  76 */     return this.libSwitchError;
/*     */   }
/*     */   
/*     */   public int getSwitchError() {
/*  80 */     return this.switchError;
/*     */   }
/*     */   
/*     */   public int getMobileError() {
/*  84 */     return this.mobileError;
/*     */   }
/*     */   
/*     */   public int getBcError() {
/*  88 */     return this.bcError;
/*     */   }
/*     */   
/*     */   public String getIdSimCard() {
/*  92 */     return this.idSimCard;
/*     */   }
/*     */   
/*     */   public String getBin() {
/*  96 */     return this.bin;
/*     */   }
/*     */   
/*     */   public String getHolder() {
/* 100 */     return this.holder;
/*     */   }
/*     */   
/*     */   public String getTotalAmount() {
/* 104 */     return this.totalAmount;
/*     */   }
/*     */   
/*     */   public String getAmount() {
/* 108 */     return this.amount;
/*     */   }
/*     */   
/*     */   public int getAcquirerError() {
/* 112 */     return this.acquirerError;
/*     */   }
/*     */   
/*     */   public Map<String, Object> toInstallmentMap() {
/* 116 */     Map<String, Object> installmentMap = new HashMap<>();
/*     */     
/* 118 */     installmentMap.put("PlugPagInstallmentQuantity", Integer.valueOf(getInstallments()));
/* 119 */     installmentMap.put("PlugPagInstallmentType", Integer.valueOf(getInstallmentType()));
/* 120 */     installmentMap.put("PlugPagInstallmentPaymentMethod", Integer.valueOf(getPaymentMethod()));
/* 121 */     installmentMap.put("PlugPagInstallmentAError", Integer.valueOf(getAError()));
/* 122 */     installmentMap.put("PlugPagInstallmentPPError", Integer.valueOf(getPpError()));
/* 123 */     installmentMap.put("PlugPagInstallmentLibSwitchError", Integer.valueOf(getLibSwitchError()));
/* 124 */     installmentMap.put("PlugPagInstallmentSwitchError", Integer.valueOf(getSwitchError()));
/* 125 */     installmentMap.put("PlugPagInstallmentMobileError", Integer.valueOf(getMobileError()));
/* 126 */     installmentMap.put("PlugPagInstallmentBcError", Integer.valueOf(getBcError()));
/* 127 */     installmentMap.put("PlugPagInstallmentIdSimCard", getIdSimCard());
/* 128 */     installmentMap.put("PlugPagInstallmentBin", getBin());
/* 129 */     installmentMap.put("PlugPagInstallmentHolder", getHolder());
/* 130 */     installmentMap.put("PlugPagInstallmentTotalAmount", getTotalAmount());
/* 131 */     installmentMap.put("PlugPagInstallmentAmount", getAmount());
/* 132 */     installmentMap.put("PlugPagInstallmentAcquirerError", Integer.valueOf(getAcquirerError()));
/*     */     
/* 134 */     return installmentMap;
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagInstallmentMetric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */