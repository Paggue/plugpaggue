/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import android.text.TextUtils;
/*     */ import android.util.SparseArray;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlugPagEventData
/*     */ {
/*     */   public static final int EVENT_CODE_CUSTOM_MESSAGE = -2;
/*     */   public static final int EVENT_CODE_DEFAULT = -1;
/*     */   public static final int EVENT_CODE_WAITING_CARD = 0;
/*     */   public static final int EVENT_CODE_INSERTED_CARD = 1;
/*     */   public static final int EVENT_CODE_PIN_REQUESTED = 2;
/*     */   public static final int EVENT_CODE_PIN_OK = 3;
/*     */   public static final int EVENT_CODE_SALE_END = 4;
/*     */   public static final int EVENT_CODE_AUTHORIZING = 5;
/*     */   public static final int EVENT_CODE_INSERTED_KEY = 6;
/*     */   public static final int EVENT_CODE_WAITING_REMOVE_CARD = 7;
/*     */   public static final int EVENT_CODE_REMOVED_CARD = 8;
/*     */   public static final int EVENT_CODE_CVV_REQUESTED = 9;
/*     */   public static final int EVENT_CODE_CVV_OK = 10;
/*     */   public static final int EVENT_CODE_CAR_BIN_REQUESTED = 11;
/*     */   public static final int EVENT_CODE_CAR_BIN_OK = 12;
/*     */   public static final int EVENT_CODE_CAR_HOLDER_REQUESTED = 13;
/*     */   public static final int EVENT_CODE_CAR_HOLDER_OK = 14;
/*     */   public static final int EVENT_CODE_ACTIVATION_SUCCESS = 15;
/*     */   public static final int EVENT_CODE_DIGIT_PASSWORD = 16;
/*     */   public static final int EVENT_CODE_NO_PASSWORD = 17;
/*     */   public static final int EVENT_CODE_SALE_APPROVED = 18;
/*     */   public static final int EVENT_CODE_SALE_NOT_APPROVED = 19;
/*     */   public static final int EVENT_CODE_QRCODE = 20;
/*  36 */   private static final SparseArray<String> MESSAGES = new SparseArray<String>(10)
/*     */     {
/*     */     
/*     */     };
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
/*     */   private int mEventCode;
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
/*     */   public static final String getDefaultMessage(int eventCode) {
/*  74 */     String message = null;
/*     */     
/*  76 */     if (MESSAGES.get(eventCode) != null) {
/*  77 */       message = (String)MESSAGES.get(eventCode);
/*     */     } else {
/*  79 */       message = (String)MESSAGES.get(-1);
/*     */     } 
/*     */     
/*  82 */     return message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   private String mCustomMessage = null;
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
/*     */   public PlugPagEventData(int eventCode, String customMessage) {
/* 103 */     this.mEventCode = eventCode;
/*     */     
/* 105 */     if (!TextUtils.isEmpty(customMessage)) {
/* 106 */       this.mCustomMessage = customMessage;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlugPagEventData(String customMessage) {
/* 116 */     this.mEventCode = -2;
/*     */     
/* 118 */     if (!TextUtils.isEmpty(customMessage)) {
/* 119 */       this.mCustomMessage = customMessage;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlugPagEventData(int eventCode) {
/* 129 */     this.mEventCode = eventCode;
/* 130 */     this.mCustomMessage = getDefaultMessage(eventCode);
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
/*     */   public int getEventCode() {
/* 143 */     return this.mEventCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCustomMessage() {
/* 152 */     return this.mCustomMessage;
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagEventData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */