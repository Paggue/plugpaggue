/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import android.support.annotation.NonNull;
/*     */ import android.text.TextUtils;
/*     */ import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
/*     */ import br.com.uol.pagseguro.plugpag.exception.PlugPagVoidTransactionException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PlugPagVoidData
/*     */ {
/*     */   static {
/*  16 */     PlugPagLibraryLoader.loadNativeLibraries();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  23 */   public String mTransactionCode = null;
/*  24 */   public String mTransactionId = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mVoidReceipt = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlugPagVoidData(@NonNull String transactionCode, @NonNull String transactionId) {
/*  40 */     if (TextUtils.isEmpty(transactionCode)) {
/*  41 */       throw new PlugPagVoidTransactionException("Código da transação é obrigatório para realizar um estorno");
/*     */     }
/*     */     
/*  44 */     this.mTransactionCode = transactionCode;
/*  45 */     this.mTransactionId = transactionId;
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
/*     */   public PlugPagVoidData(@NonNull String transactionCode, @NonNull String transactionId, boolean voidReceipt) {
/*  58 */     if (TextUtils.isEmpty(transactionCode)) {
/*  59 */       throw new PlugPagVoidTransactionException("Código da transação é obrigatório para realizar um estorno");
/*     */     }
/*     */     
/*  62 */     this.mTransactionCode = transactionCode;
/*  63 */     this.mTransactionId = transactionId;
/*  64 */     this.mVoidReceipt = voidReceipt;
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
/*     */   public PlugPagVoidData(@NonNull String transactionCode, @NonNull String transactionId, boolean voidReceipt, boolean mockStateActived) {
/*  77 */     if (TextUtils.isEmpty(transactionCode) && !mockStateActived) {
/*  78 */       throw new PlugPagVoidTransactionException("Código da transação é obrigatório para realizar um estorno");
/*     */     }
/*     */     
/*  81 */     this.mTransactionCode = transactionCode;
/*  82 */     this.mTransactionId = transactionId;
/*  83 */     this.mVoidReceipt = voidReceipt;
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
/*     */   public String getTransactionCode() {
/*  96 */     return this.mTransactionCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTransactionId() {
/* 105 */     return this.mTransactionId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getVoidReceipt() {
/* 114 */     return this.mVoidReceipt;
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
/* 130 */     private String mTransactionCode = null;
/* 131 */     private String mTransactionId = null;
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean mVoidReceipt = false;
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean mMock = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setTransactionCode(@NonNull String transactionCode) {
/* 145 */       if (TextUtils.isEmpty(transactionCode)) {
/* 146 */         throw new PlugPagException("Código da transação não pode ser nulo ou vazio");
/*     */       }
/*     */       
/* 149 */       this.mTransactionCode = transactionCode;
/*     */       
/* 151 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setTransactionId(@NonNull String transactionId) {
/* 161 */       this.mTransactionId = transactionId;
/*     */       
/* 163 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setVoidReceipt(boolean voidReceipt) {
/* 174 */       this.mVoidReceipt = voidReceipt;
/*     */       
/* 176 */       return this;
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
/*     */     public PlugPagVoidData build() {
/* 189 */       return new PlugPagVoidData(this.mTransactionCode, this.mTransactionId, this.mVoidReceipt, this.mMock);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagVoidData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */