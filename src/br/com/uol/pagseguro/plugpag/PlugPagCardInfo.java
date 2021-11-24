/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import android.support.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlugPagCardInfo
/*     */ {
/*  11 */   private String mResult = null;
/*  12 */   private String mMessage = null;
/*  13 */   private String mBin = null;
/*  14 */   private String mHolder = null;
/*  15 */   private String mCardHolder = null;
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
/*     */   public PlugPagCardInfo(@Nullable String result, @Nullable String message, @Nullable String bin, @Nullable String holder, @Nullable String cardHolder) {
/*  37 */     this.mResult = result;
/*  38 */     this.mMessage = message;
/*  39 */     this.mBin = bin;
/*  40 */     this.mHolder = holder;
/*  41 */     this.mCardHolder = cardHolder;
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
/*     */   public String getResult() {
/*  54 */     return this.mResult;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage() {
/*  63 */     return this.mMessage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBin() {
/*  72 */     return this.mBin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHolder() {
/*  81 */     return this.mHolder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCardHolder() {
/*  90 */     return this.mCardHolder;
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
/*     */   public static class Builder
/*     */   {
/* 103 */     private String mResult = null;
/* 104 */     private String mMessage = null;
/* 105 */     private String mBin = null;
/* 106 */     private String mHolder = null;
/* 107 */     private String mCardHolder = null;
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
/*     */     public Builder setResult(String result) {
/* 120 */       this.mResult = result;
/*     */       
/* 122 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setMessage(String message) {
/* 132 */       this.mMessage = message;
/*     */       
/* 134 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setBin(String bin) {
/* 144 */       this.mBin = bin;
/*     */       
/* 146 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setHolder(String holder) {
/* 156 */       this.mHolder = holder;
/*     */       
/* 158 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setCardHolder(String cardHolder) {
/* 168 */       this.mCardHolder = cardHolder;
/*     */       
/* 170 */       return this;
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
/*     */     public PlugPagCardInfo build() {
/* 183 */       return new PlugPagCardInfo(this.mResult, this.mMessage, this.mBin, this.mHolder, this.mCardHolder);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagCardInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */