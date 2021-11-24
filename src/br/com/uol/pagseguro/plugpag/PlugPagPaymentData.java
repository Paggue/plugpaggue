/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import android.support.annotation.NonNull;
/*     */ import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlugPagPaymentData
/*     */   implements Serializable
/*     */ {
/*     */   static {
/*  16 */     PlugPagLibraryLoader.loadNativeLibraries();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  23 */   private int mType = 0;
/*  24 */   private int mAmount = 0;
/*  25 */   private int mInstallmentType = 0;
/*  26 */   private int mInstallments = 1;
/*  27 */   private String mUserReference = null;
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
/*     */   private boolean mPaymentReceipt = false;
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
/*     */   public PlugPagPaymentData(int type, int amount, int installmentType, int installments, String userReference, boolean paymentReceipt) {
/*  62 */     if (type != 1 && type != 2 && type != 3 && type != 4)
/*     */     {
/*     */ 
/*     */       
/*  66 */       throw new PlugPagException("Tipo de pagamento inválido");
/*     */     }
/*     */     
/*  69 */     if (amount < 100) {
/*  70 */       throw new PlugPagException("Valor deve ser maior ou igual a R$ 1,00 real");
/*     */     }
/*     */     
/*  73 */     if (installmentType != 1 && installmentType != 2 && installmentType != 3)
/*     */     {
/*     */       
/*  76 */       throw new PlugPagException("Tipo de parcelamento inválido");
/*     */     }
/*     */     
/*  79 */     if (installments <= 0) {
/*  80 */       throw new PlugPagException("Quantidade de parcelas inválida");
/*     */     }
/*     */     
/*  83 */     if (userReference != null && userReference.length() <= 0) {
/*  84 */       throw new PlugPagException("Código de venda não pode ser vazio");
/*     */     }
/*     */     
/*  87 */     this.mType = type;
/*  88 */     this.mAmount = amount;
/*  89 */     this.mInstallmentType = installmentType;
/*  90 */     this.mInstallments = installments;
/*  91 */     this.mUserReference = userReference;
/*  92 */     this.mPaymentReceipt = paymentReceipt;
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
/*     */   public PlugPagPaymentData(int type, int amount, int installmentType, int installments, String userReference) {
/* 120 */     this(type, amount, installmentType, installments, userReference, false);
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
/*     */   public PlugPagPaymentData(int type, int amount, int installmentType, int installments) {
/* 145 */     this(type, amount, installmentType, installments, null);
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
/*     */   public int getType() {
/* 158 */     return this.mType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAmount() {
/* 167 */     return this.mAmount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInstallmentType() {
/* 176 */     return this.mInstallmentType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInstallments() {
/* 185 */     return this.mInstallments;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserReference() {
/* 194 */     return this.mUserReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getPaymentReceipt() {
/* 203 */     return this.mPaymentReceipt;
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
/* 219 */     private int mType = 0;
/* 220 */     private int mAmount = 0;
/* 221 */     private int mInstallmentType = 0;
/* 222 */     private int mInstallments = 0;
/* 223 */     private String mUserReference = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private boolean mPaymentReceipt = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setType(int type) {
/* 237 */       if (type != 1 && type != 2 && type != 3 && type != 4)
/*     */       {
/*     */ 
/*     */         
/* 241 */         throw new PlugPagException("Tipo de pagamento inválido");
/*     */       }
/*     */       
/* 244 */       this.mType = type;
/*     */       
/* 246 */       if (type != 1) {
/* 247 */         setInstallmentType(1);
/*     */       }
/*     */       
/* 250 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setAmount(int amount) {
/* 261 */       if (amount <= 0) {
/* 262 */         throw new PlugPagException("Valor deve maior do que zero");
/*     */       }
/*     */       
/* 265 */       this.mAmount = amount;
/*     */       
/* 267 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setInstallmentType(int installmentType) {
/* 278 */       if (installmentType != 1 && installmentType != 2 && installmentType != 3)
/*     */       {
/*     */         
/* 281 */         throw new PlugPagException("Tipo de parcelamento inválido");
/*     */       }
/*     */       
/* 284 */       this.mInstallmentType = installmentType;
/*     */       
/* 286 */       if (installmentType == 1) {
/* 287 */         this.mInstallments = 1;
/*     */       }
/*     */       
/* 290 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setInstallments(int installments) {
/* 301 */       if (installments <= 0) {
/* 302 */         throw new PlugPagException("Quantidade de parcelas inválida");
/*     */       }
/*     */       
/* 305 */       this.mInstallments = installments;
/*     */       
/* 307 */       if (installments == 1) {
/* 308 */         this.mInstallmentType = 1;
/*     */       }
/*     */       
/* 311 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setUserReference(@NonNull String userReference) {
/* 322 */       if (userReference != null && userReference.length() <= 0) {
/* 323 */         throw new PlugPagException("Código de vendas não pode ser vazio");
/*     */       }
/*     */       
/* 326 */       this.mUserReference = userReference;
/*     */       
/* 328 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setPaymentReceipt(boolean paymentReceipt) {
/* 338 */       this.mPaymentReceipt = paymentReceipt;
/*     */       
/* 340 */       return this;
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
/*     */     public PlugPagPaymentData build() {
/* 353 */       return new PlugPagPaymentData(this.mType, this.mAmount, this.mInstallmentType, this.mInstallments, this.mUserReference, this.mPaymentReceipt);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagPaymentData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */