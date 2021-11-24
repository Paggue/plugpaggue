/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlugPagCustomPrinterLayout
/*     */   implements Serializable
/*     */ {
/*  11 */   private String mTitle = null;
/*  12 */   private String mTitleColor = null;
/*  13 */   private String mConfirmTextColor = null;
/*  14 */   private String mCancelTextColor = null;
/*  15 */   private String mWindowBackgoundColor = null;
/*  16 */   private String mButtonBackgroundColor = null;
/*  17 */   private String mButtonBackgroundColorDisabled = null;
/*  18 */   private String mSendSMSTextColor = null;
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
/*     */   public PlugPagCustomPrinterLayout(String title, String titleColor, String confirmTextColor, String cancelTextColor, String windowBackgoundColor, String buttonBackgroundColor, String buttonBackgroundColorDisabled, String sendSMSTextColor) {
/*  44 */     this.mTitle = title;
/*  45 */     this.mTitleColor = titleColor;
/*  46 */     this.mConfirmTextColor = confirmTextColor;
/*  47 */     this.mCancelTextColor = cancelTextColor;
/*  48 */     this.mWindowBackgoundColor = windowBackgoundColor;
/*  49 */     this.mButtonBackgroundColor = buttonBackgroundColor;
/*  50 */     this.mButtonBackgroundColorDisabled = buttonBackgroundColorDisabled;
/*  51 */     this.mSendSMSTextColor = sendSMSTextColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlugPagCustomPrinterLayout() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/*  62 */     return this.mTitle;
/*     */   }
/*     */   
/*     */   public String getTitleColor() {
/*  66 */     return this.mTitleColor;
/*     */   }
/*     */   
/*     */   public String getConfirmTextColor() {
/*  70 */     return this.mConfirmTextColor;
/*     */   }
/*     */   
/*     */   public String getCancelTextColor() {
/*  74 */     return this.mCancelTextColor;
/*     */   }
/*     */   
/*     */   public String getWindowBackgoundColor() {
/*  78 */     return this.mWindowBackgoundColor;
/*     */   }
/*     */   
/*     */   public String getButtonBackgroundColor() {
/*  82 */     return this.mButtonBackgroundColor;
/*     */   }
/*     */   
/*     */   public String getSendSMSTextColor() {
/*  86 */     return this.mSendSMSTextColor;
/*     */   }
/*     */   
/*     */   public String getButtonBackgroundColorDisabled() {
/*  90 */     return this.mButtonBackgroundColorDisabled;
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
/* 106 */     private String mTitle = null;
/* 107 */     private String mTitleColor = null;
/* 108 */     private String mConfirmTextColor = null;
/* 109 */     private String mCancelTextColor = null;
/* 110 */     private String mWindowBackgoundColor = null;
/* 111 */     private String mButtonBackgroundColor = null;
/* 112 */     private String mButtonBackgroundColorDisabled = null;
/* 113 */     private String mSendSMSTextColor = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setTitle(String title) {
/* 120 */       this.mTitle = title;
/* 121 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setTitleColor(String titleColor) {
/* 125 */       this.mTitleColor = titleColor;
/* 126 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setConfirmTextColor(String confirmTextColor) {
/* 130 */       this.mConfirmTextColor = confirmTextColor;
/* 131 */       return this;
/*     */     }
/*     */     public Builder setCancelTextColor(String cancelTextColor) {
/* 134 */       this.mCancelTextColor = cancelTextColor;
/* 135 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setWindowBackgoundColor(String windowBackgoundColor) {
/* 139 */       this.mWindowBackgoundColor = windowBackgoundColor;
/* 140 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setButtonBackgroundColor(String buttonBackgroundColor) {
/* 144 */       this.mButtonBackgroundColor = buttonBackgroundColor;
/* 145 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setSendSMSTextColor(String sendSMSTextColor) {
/* 149 */       this.mSendSMSTextColor = sendSMSTextColor;
/* 150 */       return this;
/*     */     }
/*     */     
/*     */     public Builder setButtonBackgroundColorDisabled(String buttonBackgroundColorDisabled) {
/* 154 */       this.mButtonBackgroundColorDisabled = buttonBackgroundColorDisabled;
/* 155 */       return this;
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
/*     */     public PlugPagCustomPrinterLayout build() {
/* 168 */       return new PlugPagCustomPrinterLayout(this.mTitle, this.mTitleColor, this.mConfirmTextColor, this.mCancelTextColor, this.mWindowBackgoundColor, this.mButtonBackgroundColor, this.mButtonBackgroundColorDisabled, this.mSendSMSTextColor);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagCustomPrinterLayout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */