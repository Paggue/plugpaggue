package br.com.uol.pagseguro.plugpag;

import android.text.TextUtils;
import android.util.SparseArray;

public class PlugPagEventData {
  public static final int EVENT_CODE_CUSTOM_MESSAGE = -2;
  
  public static final int EVENT_CODE_DEFAULT = -1;
  
  public static final int EVENT_CODE_WAITING_CARD = 0;
  
  public static final int EVENT_CODE_INSERTED_CARD = 1;
  
  public static final int EVENT_CODE_PIN_REQUESTED = 2;
  
  public static final int EVENT_CODE_PIN_OK = 3;
  
  public static final int EVENT_CODE_SALE_END = 4;
  
  public static final int EVENT_CODE_AUTHORIZING = 5;
  
  public static final int EVENT_CODE_INSERTED_KEY = 6;
  
  public static final int EVENT_CODE_WAITING_REMOVE_CARD = 7;
  
  public static final int EVENT_CODE_REMOVED_CARD = 8;
  
  public static final int EVENT_CODE_CVV_REQUESTED = 9;
  
  public static final int EVENT_CODE_CVV_OK = 10;
  
  public static final int EVENT_CODE_CAR_BIN_REQUESTED = 11;
  
  public static final int EVENT_CODE_CAR_BIN_OK = 12;
  
  public static final int EVENT_CODE_CAR_HOLDER_REQUESTED = 13;
  
  public static final int EVENT_CODE_CAR_HOLDER_OK = 14;
  
  public static final int EVENT_CODE_ACTIVATION_SUCCESS = 15;
  
  public static final int EVENT_CODE_DIGIT_PASSWORD = 16;
  
  public static final int EVENT_CODE_NO_PASSWORD = 17;
  
  public static final int EVENT_CODE_SALE_APPROVED = 18;
  
  public static final int EVENT_CODE_SALE_NOT_APPROVED = 19;
  
  public static final int EVENT_CODE_QRCODE = 20;
  
  private static final SparseArray<String> MESSAGES = new SparseArray<String>(10) {
    
    };
  
  private int mEventCode;
  
  public static final String getDefaultMessage(int eventCode) {
    String message = null;
    if (MESSAGES.get(eventCode) != null) {
      message = (String)MESSAGES.get(eventCode);
    } else {
      message = (String)MESSAGES.get(-1);
    } 
    return message;
  }
  
  private String mCustomMessage = null;
  
  public PlugPagEventData(int eventCode, String customMessage) {
    this.mEventCode = eventCode;
    if (!TextUtils.isEmpty(customMessage))
      this.mCustomMessage = customMessage; 
  }
  
  public PlugPagEventData(String customMessage) {
    this.mEventCode = -2;
    if (!TextUtils.isEmpty(customMessage))
      this.mCustomMessage = customMessage; 
  }
  
  public PlugPagEventData(int eventCode) {
    this.mEventCode = eventCode;
    this.mCustomMessage = getDefaultMessage(eventCode);
  }
  
  public int getEventCode() {
    return this.mEventCode;
  }
  
  public String getCustomMessage() {
    return this.mCustomMessage;
  }
}
