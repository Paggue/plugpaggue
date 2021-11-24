/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import android.support.annotation.NonNull;
/*     */ import android.text.TextUtils;
/*     */ import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlugPagMobileCarrierData
/*     */   implements Serializable
/*     */ {
/*     */   private String mMobileNumber;
/*     */   
/*     */   static {
/*  17 */     PlugPagLibraryLoader.loadNativeLibraries();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  25 */   private String mUserReference = null;
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
/*     */   public PlugPagMobileCarrierData(String userReference) {
/*  39 */     this(userReference, false, "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlugPagMobileCarrierData(String userReference, boolean paymentReceipt) {
/*  50 */     this(userReference, paymentReceipt, "");
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
/*     */   public PlugPagMobileCarrierData(String userReference, boolean paymentReceipt, String mobileNumber) {
/*  64 */     if (TextUtils.isEmpty(userReference)) {
/*  65 */       throw new PlugPagException("Código de vendas não pode ser nulo ou vazio");
/*     */     }
/*     */     
/*  68 */     this.mMobileNumber = mobileNumber;
/*  69 */     this.mUserReference = userReference;
/*  70 */     this.mPaymentReceipt = paymentReceipt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUserReference() {
/*  79 */     return this.mUserReference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getPaymentReceipt() {
/*  88 */     return this.mPaymentReceipt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMobileNumber() {
/*  97 */     return this.mMobileNumber;
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
/* 113 */     private String mUserReference = null;
/*     */     private boolean mPaymentReceipt = false;
/* 115 */     private String mMobileNumber = null;
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
/*     */     public Builder setUserReference(@NonNull String userReference) {
/* 129 */       if (TextUtils.isEmpty(userReference)) {
/* 130 */         throw new PlugPagException("Código de vendas não pode ser vazio ou nulo");
/*     */       }
/*     */       
/* 133 */       this.mUserReference = userReference;
/*     */       
/* 135 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setPaymentReceipt(boolean paymentReceipt) {
/* 146 */       this.mPaymentReceipt = paymentReceipt;
/*     */       
/* 148 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder setMobileNumber(String mobileNumber) {
/* 158 */       this.mMobileNumber = mobileNumber;
/* 159 */       return this;
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
/*     */     public PlugPagMobileCarrierData build() {
/* 172 */       return new PlugPagMobileCarrierData(this.mUserReference, this.mPaymentReceipt, this.mMobileNumber);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagMobileCarrierData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */