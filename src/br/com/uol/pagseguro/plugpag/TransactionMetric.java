package br.com.uol.pagseguro.plugpag;

import java.util.HashMap;
import java.util.Map;

public class TransactionMetric {
  private int aError;
  
  private int ppError;
  
  private int linuxError;
  
  private int libSwitchError;
  
  private int switchError;
  
  private int mobileError;
  
  private int acquirerError;
  
  private int bcError;
  
  private TcpConnState tcpConnState;
  
  private ModemConnState modemConnState;
  
  private ConnChannel connChannel;
  
  private String operatorName;
  
  private String applicationCode;
  
  private String NSUTerminal;
  
  private String idSimCard;
  
  private int hsmKeyExchanged;
  
  private String amount;
  
  private String totalAmount;
  
  private String bin;
  
  private String holder;
  
  private int paymentMethod;
  
  private int installmentType;
  
  private int installments;
  
  private String fallbackError;
  
  private String captureMode;
  
  public enum ModemConnState {
    UNKNOWN("UNKNOWN"),
    POWER_OFF("POWER_OFF"),
    NO_SIM("NO_SIM"),
    SIM_ERROR("SIM_ERROR"),
    SIM_PIN("SIM_PIN"),
    SIM_BLOCKED("SIM_BLOCKED"),
    SIM_NOT_READY("SIM_NOT_READY"),
    REGISTERING_FAILED("REGISTERING_FAILED"),
    ATTACH_FAILED("ATTACH_FAILED"),
    NO_PPP_CONTEXT("NO_PPP_CONTEXT"),
    DORMANT("DORMANT"),
    READY("READY");
    
    private String description;
    
    private static Map map = new HashMap<>();
    
    static {
      for (ModemConnState modemConnState : values())
        map.put(Integer.valueOf(modemConnState.getValue()), modemConnState); 
    }
    
    ModemConnState(String description) {
      this.description = description;
    }
    
    public String getDescription() {
      return this.description;
    }
    
    public int getValue() {
      return ordinal();
    }
  }
  
  public enum TcpConnState {
    UNKOWN("UNKOWN"),
    WAITING_RADIO("WAITING_RADIO"),
    RESOLVE_DNS("RESOLVE_DNS"),
    CONNECT("CONNECT"),
    SSL_HANDSHAKE("SSL_HANDSHAKE"),
    SEND("SEND"),
    RECEIVE("RECEIVE"),
    FINISHED("FINISHED");
    
    private String description;
    
    private static Map map = new HashMap<>();
    
    static {
      for (TcpConnState tcpConnState : values())
        map.put(Integer.valueOf(tcpConnState.getValue()), tcpConnState); 
    }
    
    TcpConnState(String description) {
      this.description = description;
    }
    
    public String getDescription() {
      return this.description;
    }
    
    public int getValue() {
      return ordinal();
    }
  }
  
  public enum ConnChannel {
    UNKOWN("UNKOWN"),
    GPRS("GPRS"),
    WIFI("WIFI"),
    FALLBACK("FALLBACK"),
    ETHERNET("ETHERNET");
    
    private String description;
    
    private static Map map = new HashMap<>();
    
    static {
      for (ConnChannel connChannel : values())
        map.put(Integer.valueOf(connChannel.getValue()), connChannel); 
    }
    
    ConnChannel(String description) {
      this.description = description;
    }
    
    public String getDescription() {
      return this.description;
    }
    
    public int getValue() {
      return ordinal();
    }
  }
  
  public TransactionMetric(int aError, int ppError, int linuxError, int libSwitchError, int switchError, int mobileError, int acquirerError, int bcError, int tcpConnState, int modemConnState, int connChannel, String operatorName, String applicationCode, String NSUTerminal, String idSimCard, int hsmKeyExchanged, String amount, String totalAmount, String bin, String holder, int paymentMethod, int installmentType, int installments, String fallbackError, String captureMode) {
    this.aError = aError;
    this.ppError = ppError;
    this.linuxError = linuxError;
    this.libSwitchError = libSwitchError;
    this.switchError = switchError;
    this.mobileError = mobileError;
    this.acquirerError = acquirerError;
    this.bcError = bcError;
    this.tcpConnState = TcpConnState.valueOf(tcpConnState);
    this.modemConnState = ModemConnState.valueOf(modemConnState);
    this.connChannel = ConnChannel.valueOf(connChannel);
    this.operatorName = operatorName;
    this.applicationCode = applicationCode;
    this.NSUTerminal = NSUTerminal;
    this.idSimCard = idSimCard;
    this.hsmKeyExchanged = hsmKeyExchanged;
    this.amount = amount;
    this.totalAmount = totalAmount;
    this.bin = bin;
    this.holder = holder;
    this.paymentMethod = paymentMethod;
    this.installmentType = installmentType;
    this.installments = installments;
    this.fallbackError = fallbackError;
    this.captureMode = captureMode;
  }
  
  public Map<String, Object> toMap() {
    Map<String, Object> bundle = new HashMap<>();
    bundle.put("aError", Integer.valueOf(this.aError));
    bundle.put("ppError", Integer.valueOf(this.ppError));
    bundle.put("linuxError", Integer.valueOf(this.linuxError));
    bundle.put("libSwitchError", Integer.valueOf(this.libSwitchError));
    bundle.put("switchError", Integer.valueOf(this.switchError));
    bundle.put("mobileError", Integer.valueOf(this.mobileError));
    bundle.put("acquirerError", Integer.valueOf(this.acquirerError));
    bundle.put("bcError", Integer.valueOf(this.bcError));
    bundle.put("tcpConnState", this.tcpConnState.getDescription());
    bundle.put("modemConnState", this.modemConnState.getDescription());
    bundle.put("connChannel", this.connChannel.getDescription());
    bundle.put("operatorName", this.operatorName);
    bundle.put("applicationCode", this.applicationCode);
    bundle.put("NSUTerminal", this.NSUTerminal);
    bundle.put("idSimCard", this.idSimCard);
    bundle.put("hsmKeyExchanged", Integer.valueOf(this.hsmKeyExchanged));
    bundle.put("amount", this.amount);
    bundle.put("totalAmount", this.totalAmount);
    bundle.put("bin", this.bin);
    bundle.put("holder", this.holder);
    bundle.put("paymentMethod", Integer.valueOf(this.paymentMethod));
    bundle.put("installmentType", Integer.valueOf(this.installmentType));
    bundle.put("installments", Integer.valueOf(this.installments));
    bundle.put("fallbackError", this.fallbackError);
    bundle.put("captureMode", this.captureMode);
    return bundle;
  }
  
  public int getModemConnStateValue() {
    return this.modemConnState.getValue();
  }
  
  public void setModemConnState(ModemConnState modemConnState) {
    this.modemConnState = modemConnState;
  }
  
  public int getaError() {
    return this.aError;
  }
  
  public void setaError(int aError) {
    this.aError = aError;
  }
  
  public int getTcpConnStateValue() {
    return this.tcpConnState.getValue();
  }
  
  public void setTcpConnState(TcpConnState tcpConnState) {
    this.tcpConnState = tcpConnState;
  }
  
  public int getConnChannelValue() {
    return this.connChannel.getValue();
  }
  
  public void setConnChannel(ConnChannel connChannel) {
    this.connChannel = connChannel;
  }
  
  public int getLibSwitchError() {
    return this.libSwitchError;
  }
  
  public void setLibSwitchError(int libSwitchError) {
    this.libSwitchError = libSwitchError;
  }
  
  public int getPpError() {
    return this.ppError;
  }
  
  public void setPpError(int ppError) {
    this.ppError = ppError;
  }
  
  public int getLinuxError() {
    return this.linuxError;
  }
  
  public void setLinuxError(int linuxError) {
    this.linuxError = linuxError;
  }
  
  public int getSwitchError() {
    return this.switchError;
  }
  
  public void setSwitchError(int switchError) {
    this.switchError = switchError;
  }
  
  public int getMobileError() {
    return this.mobileError;
  }
  
  public void setMobileError(int mobileError) {
    this.mobileError = mobileError;
  }
  
  public int getAcquirerError() {
    return this.acquirerError;
  }
  
  public void setAcquirerError(int acquirerError) {
    this.acquirerError = acquirerError;
  }
  
  public int getBcError() {
    return this.bcError;
  }
  
  public void setBcError(int bcError) {
    this.bcError = bcError;
  }
  
  public String getOperatorName() {
    return this.operatorName;
  }
  
  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }
  
  public String getApplicationCode() {
    return this.applicationCode;
  }
  
  public void setApplicationCode(String applicationCode) {
    this.applicationCode = applicationCode;
  }
  
  public String getNSUTerminal() {
    return this.NSUTerminal;
  }
  
  public void setNSUTerminal(String NSUTerminal) {
    this.NSUTerminal = NSUTerminal;
  }
  
  public String getIdSimCard() {
    return this.idSimCard;
  }
  
  public void setIdSimCard(String idSimCard) {
    this.idSimCard = idSimCard;
  }
  
  public int getHsmKeyExchanged() {
    return this.hsmKeyExchanged;
  }
  
  public void setHsmKeyExchanged(int hsmKeyExchanged) {
    this.hsmKeyExchanged = hsmKeyExchanged;
  }
  
  public String getAmount() {
    return this.amount;
  }
  
  public void setAmount(String amount) {
    this.amount = amount;
  }
  
  public String getTotalAmount() {
    return this.totalAmount;
  }
  
  public void setTotalAmount(String totalAmount) {
    this.totalAmount = totalAmount;
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
  
  public int getPaymentMethod() {
    return this.paymentMethod;
  }
  
  public void setPaymentMethod(int paymentMethod) {
    this.paymentMethod = paymentMethod;
  }
  
  public int getInstallmentType() {
    return this.installmentType;
  }
  
  public void setInstallmentType(int installmentType) {
    this.installmentType = installmentType;
  }
  
  public int getInstallments() {
    return this.installments;
  }
  
  public void setInstallments(int installments) {
    this.installments = installments;
  }
  
  public String getFallbackError() {
    return this.fallbackError;
  }
  
  public void setFallbackError(String fallbackError) {
    this.fallbackError = fallbackError;
  }
  
  public String getCaptureMode() {
    return this.captureMode;
  }
  
  public void setCaptureMode(String captureMode) {
    this.captureMode = captureMode;
  }
}
