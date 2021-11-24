/*     */ package br.com.uol.pagseguro.plugpag;public class TransactionMetric { private int aError; private int ppError; private int linuxError; private int libSwitchError; private int switchError; private int mobileError; private int acquirerError; private int bcError; private TcpConnState tcpConnState; private ModemConnState modemConnState; private ConnChannel connChannel; private String operatorName; private String applicationCode; private String NSUTerminal; private String idSimCard; private int hsmKeyExchanged; private String amount;
/*     */   private String totalAmount;
/*     */   private String bin;
/*     */   private String holder;
/*     */   private int paymentMethod;
/*     */   private int installmentType;
/*     */   private int installments;
/*     */   private String fallbackError;
/*     */   private String captureMode;
/*     */   
/*  11 */   public enum ModemConnState { UNKNOWN("UNKNOWN"),
/*  12 */     POWER_OFF("POWER_OFF"),
/*  13 */     NO_SIM("NO_SIM"),
/*  14 */     SIM_ERROR("SIM_ERROR"),
/*  15 */     SIM_PIN("SIM_PIN"),
/*  16 */     SIM_BLOCKED("SIM_BLOCKED"),
/*  17 */     SIM_NOT_READY("SIM_NOT_READY"),
/*  18 */     REGISTERING_FAILED("REGISTERING_FAILED"),
/*  19 */     ATTACH_FAILED("ATTACH_FAILED"),
/*  20 */     NO_PPP_CONTEXT("NO_PPP_CONTEXT"),
/*  21 */     DORMANT("DORMANT"),
/*  22 */     READY("READY");
/*     */     
/*     */     private String description;
/*     */     
/*  26 */     private static Map map = new HashMap<>();
/*     */     
/*     */     static {
/*  29 */       for (ModemConnState modemConnState : values()) {
/*  30 */         map.put(Integer.valueOf(modemConnState.getValue()), modemConnState);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     ModemConnState(String description) {
/*  39 */       this.description = description;
/*     */     }
/*     */     
/*     */     public String getDescription() {
/*  43 */       return this.description;
/*     */     }
/*     */     
/*     */     public int getValue() {
/*  47 */       return ordinal();
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum TcpConnState
/*     */   {
/*  54 */     UNKOWN("UNKOWN"),
/*  55 */     WAITING_RADIO("WAITING_RADIO"),
/*  56 */     RESOLVE_DNS("RESOLVE_DNS"),
/*  57 */     CONNECT("CONNECT"),
/*  58 */     SSL_HANDSHAKE("SSL_HANDSHAKE"),
/*  59 */     SEND("SEND"),
/*  60 */     RECEIVE("RECEIVE"),
/*  61 */     FINISHED("FINISHED");
/*     */     
/*     */     private String description;
/*     */     
/*  65 */     private static Map map = new HashMap<>();
/*     */     
/*     */     static {
/*  68 */       for (TcpConnState tcpConnState : values()) {
/*  69 */         map.put(Integer.valueOf(tcpConnState.getValue()), tcpConnState);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     TcpConnState(String description) {
/*  78 */       this.description = description;
/*     */     }
/*     */     
/*     */     public String getDescription() {
/*  82 */       return this.description;
/*     */     }
/*     */     
/*     */     public int getValue() {
/*  86 */       return ordinal();
/*     */     }
/*     */   }
/*     */   
/*     */   public enum ConnChannel
/*     */   {
/*  92 */     UNKOWN("UNKOWN"),
/*  93 */     GPRS("GPRS"),
/*  94 */     WIFI("WIFI"),
/*  95 */     FALLBACK("FALLBACK"),
/*  96 */     ETHERNET("ETHERNET");
/*     */     
/*     */     private String description;
/*     */     
/* 100 */     private static Map map = new HashMap<>();
/*     */     
/*     */     static {
/* 103 */       for (ConnChannel connChannel : values()) {
/* 104 */         map.put(Integer.valueOf(connChannel.getValue()), connChannel);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     ConnChannel(String description) {
/* 113 */       this.description = description;
/*     */     }
/*     */     
/*     */     public String getDescription() {
/* 117 */       return this.description;
/*     */     }
/*     */     
/*     */     public int getValue() {
/* 121 */       return ordinal();
/*     */     }
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
/*     */   public TransactionMetric(int aError, int ppError, int linuxError, int libSwitchError, int switchError, int mobileError, int acquirerError, int bcError, int tcpConnState, int modemConnState, int connChannel, String operatorName, String applicationCode, String NSUTerminal, String idSimCard, int hsmKeyExchanged, String amount, String totalAmount, String bin, String holder, int paymentMethod, int installmentType, int installments, String fallbackError, String captureMode) {
/* 176 */     this.aError = aError;
/* 177 */     this.ppError = ppError;
/* 178 */     this.linuxError = linuxError;
/* 179 */     this.libSwitchError = libSwitchError;
/* 180 */     this.switchError = switchError;
/* 181 */     this.mobileError = mobileError;
/* 182 */     this.acquirerError = acquirerError;
/* 183 */     this.bcError = bcError;
/* 184 */     this.tcpConnState = TcpConnState.valueOf(tcpConnState);
/* 185 */     this.modemConnState = ModemConnState.valueOf(modemConnState);
/* 186 */     this.connChannel = ConnChannel.valueOf(connChannel);
/* 187 */     this.operatorName = operatorName;
/* 188 */     this.applicationCode = applicationCode;
/* 189 */     this.NSUTerminal = NSUTerminal;
/* 190 */     this.idSimCard = idSimCard;
/* 191 */     this.hsmKeyExchanged = hsmKeyExchanged;
/* 192 */     this.amount = amount;
/* 193 */     this.totalAmount = totalAmount;
/* 194 */     this.bin = bin;
/* 195 */     this.holder = holder;
/* 196 */     this.paymentMethod = paymentMethod;
/* 197 */     this.installmentType = installmentType;
/* 198 */     this.installments = installments;
/* 199 */     this.fallbackError = fallbackError;
/* 200 */     this.captureMode = captureMode;
/*     */   }
/*     */   
/*     */   public Map<String, Object> toMap() {
/* 204 */     Map<String, Object> bundle = new HashMap<>();
/* 205 */     bundle.put("aError", Integer.valueOf(this.aError));
/* 206 */     bundle.put("ppError", Integer.valueOf(this.ppError));
/* 207 */     bundle.put("linuxError", Integer.valueOf(this.linuxError));
/* 208 */     bundle.put("libSwitchError", Integer.valueOf(this.libSwitchError));
/* 209 */     bundle.put("switchError", Integer.valueOf(this.switchError));
/* 210 */     bundle.put("mobileError", Integer.valueOf(this.mobileError));
/* 211 */     bundle.put("acquirerError", Integer.valueOf(this.acquirerError));
/* 212 */     bundle.put("bcError", Integer.valueOf(this.bcError));
/* 213 */     bundle.put("tcpConnState", this.tcpConnState.getDescription());
/* 214 */     bundle.put("modemConnState", this.modemConnState.getDescription());
/* 215 */     bundle.put("connChannel", this.connChannel.getDescription());
/* 216 */     bundle.put("operatorName", this.operatorName);
/* 217 */     bundle.put("applicationCode", this.applicationCode);
/* 218 */     bundle.put("NSUTerminal", this.NSUTerminal);
/* 219 */     bundle.put("idSimCard", this.idSimCard);
/* 220 */     bundle.put("hsmKeyExchanged", Integer.valueOf(this.hsmKeyExchanged));
/* 221 */     bundle.put("amount", this.amount);
/* 222 */     bundle.put("totalAmount", this.totalAmount);
/* 223 */     bundle.put("bin", this.bin);
/* 224 */     bundle.put("holder", this.holder);
/* 225 */     bundle.put("paymentMethod", Integer.valueOf(this.paymentMethod));
/* 226 */     bundle.put("installmentType", Integer.valueOf(this.installmentType));
/* 227 */     bundle.put("installments", Integer.valueOf(this.installments));
/* 228 */     bundle.put("fallbackError", this.fallbackError);
/* 229 */     bundle.put("captureMode", this.captureMode);
/* 230 */     return bundle;
/*     */   }
/*     */   
/*     */   public int getModemConnStateValue() {
/* 234 */     return this.modemConnState.getValue();
/*     */   }
/*     */   
/*     */   public void setModemConnState(ModemConnState modemConnState) {
/* 238 */     this.modemConnState = modemConnState;
/*     */   }
/*     */   
/*     */   public int getaError() {
/* 242 */     return this.aError;
/*     */   }
/*     */   
/*     */   public void setaError(int aError) {
/* 246 */     this.aError = aError;
/*     */   }
/*     */   
/*     */   public int getTcpConnStateValue() {
/* 250 */     return this.tcpConnState.getValue();
/*     */   }
/*     */   
/*     */   public void setTcpConnState(TcpConnState tcpConnState) {
/* 254 */     this.tcpConnState = tcpConnState;
/*     */   }
/*     */   
/*     */   public int getConnChannelValue() {
/* 258 */     return this.connChannel.getValue();
/*     */   }
/*     */   
/*     */   public void setConnChannel(ConnChannel connChannel) {
/* 262 */     this.connChannel = connChannel;
/*     */   }
/*     */   
/*     */   public int getLibSwitchError() {
/* 266 */     return this.libSwitchError;
/*     */   }
/*     */   
/*     */   public void setLibSwitchError(int libSwitchError) {
/* 270 */     this.libSwitchError = libSwitchError;
/*     */   }
/*     */   
/*     */   public int getPpError() {
/* 274 */     return this.ppError;
/*     */   }
/*     */   
/*     */   public void setPpError(int ppError) {
/* 278 */     this.ppError = ppError;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLinuxError() {
/* 283 */     return this.linuxError;
/*     */   }
/*     */   
/*     */   public void setLinuxError(int linuxError) {
/* 287 */     this.linuxError = linuxError;
/*     */   }
/*     */   
/*     */   public int getSwitchError() {
/* 291 */     return this.switchError;
/*     */   }
/*     */   
/*     */   public void setSwitchError(int switchError) {
/* 295 */     this.switchError = switchError;
/*     */   }
/*     */   
/*     */   public int getMobileError() {
/* 299 */     return this.mobileError;
/*     */   }
/*     */   
/*     */   public void setMobileError(int mobileError) {
/* 303 */     this.mobileError = mobileError;
/*     */   }
/*     */   
/*     */   public int getAcquirerError() {
/* 307 */     return this.acquirerError;
/*     */   }
/*     */   
/*     */   public void setAcquirerError(int acquirerError) {
/* 311 */     this.acquirerError = acquirerError;
/*     */   }
/*     */   
/*     */   public int getBcError() {
/* 315 */     return this.bcError;
/*     */   }
/*     */   
/*     */   public void setBcError(int bcError) {
/* 319 */     this.bcError = bcError;
/*     */   }
/*     */   
/*     */   public String getOperatorName() {
/* 323 */     return this.operatorName;
/*     */   }
/*     */   
/*     */   public void setOperatorName(String operatorName) {
/* 327 */     this.operatorName = operatorName;
/*     */   }
/*     */   
/*     */   public String getApplicationCode() {
/* 331 */     return this.applicationCode;
/*     */   }
/*     */   
/*     */   public void setApplicationCode(String applicationCode) {
/* 335 */     this.applicationCode = applicationCode;
/*     */   }
/*     */   
/*     */   public String getNSUTerminal() {
/* 339 */     return this.NSUTerminal;
/*     */   }
/*     */   
/*     */   public void setNSUTerminal(String NSUTerminal) {
/* 343 */     this.NSUTerminal = NSUTerminal;
/*     */   }
/*     */   
/*     */   public String getIdSimCard() {
/* 347 */     return this.idSimCard;
/*     */   }
/*     */   
/*     */   public void setIdSimCard(String idSimCard) {
/* 351 */     this.idSimCard = idSimCard;
/*     */   }
/*     */   
/*     */   public int getHsmKeyExchanged() {
/* 355 */     return this.hsmKeyExchanged;
/*     */   }
/*     */   
/*     */   public void setHsmKeyExchanged(int hsmKeyExchanged) {
/* 359 */     this.hsmKeyExchanged = hsmKeyExchanged;
/*     */   }
/*     */   
/*     */   public String getAmount() {
/* 363 */     return this.amount;
/*     */   }
/*     */   
/*     */   public void setAmount(String amount) {
/* 367 */     this.amount = amount;
/*     */   }
/*     */   
/*     */   public String getTotalAmount() {
/* 371 */     return this.totalAmount;
/*     */   }
/*     */   
/*     */   public void setTotalAmount(String totalAmount) {
/* 375 */     this.totalAmount = totalAmount;
/*     */   }
/*     */   
/*     */   public String getBin() {
/* 379 */     return this.bin;
/*     */   }
/*     */   
/*     */   public void setBin(String bin) {
/* 383 */     this.bin = bin;
/*     */   }
/*     */   
/*     */   public String getHolder() {
/* 387 */     return this.holder;
/*     */   }
/*     */   
/*     */   public void setHolder(String holder) {
/* 391 */     this.holder = holder;
/*     */   }
/*     */   
/*     */   public int getPaymentMethod() {
/* 395 */     return this.paymentMethod;
/*     */   }
/*     */   
/*     */   public void setPaymentMethod(int paymentMethod) {
/* 399 */     this.paymentMethod = paymentMethod;
/*     */   }
/*     */   
/*     */   public int getInstallmentType() {
/* 403 */     return this.installmentType;
/*     */   }
/*     */   
/*     */   public void setInstallmentType(int installmentType) {
/* 407 */     this.installmentType = installmentType;
/*     */   }
/*     */   
/*     */   public int getInstallments() {
/* 411 */     return this.installments;
/*     */   }
/*     */   
/*     */   public void setInstallments(int installments) {
/* 415 */     this.installments = installments;
/*     */   }
/*     */   
/*     */   public String getFallbackError() {
/* 419 */     return this.fallbackError;
/*     */   }
/*     */   
/*     */   public void setFallbackError(String fallbackError) {
/* 423 */     this.fallbackError = fallbackError;
/*     */   }
/*     */   
/*     */   public String getCaptureMode() {
/* 427 */     return this.captureMode;
/*     */   }
/*     */   
/*     */   public void setCaptureMode(String captureMode) {
/* 431 */     this.captureMode = captureMode;
/*     */   } }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/TransactionMetric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */