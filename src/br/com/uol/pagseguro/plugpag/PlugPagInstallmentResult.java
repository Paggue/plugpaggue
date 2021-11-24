/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import android.support.annotation.NonNull;
/*     */ import android.support.annotation.Nullable;
/*     */ import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlugPagInstallmentResult
/*     */ {
/*     */   static {
/*  16 */     PlugPagLibraryLoader.loadNativeLibraries();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  23 */   private int mResult = 0;
/*  24 */   private String mMessage = null;
/*  25 */   private String mErrorCode = null;
/*  26 */   private String mRate = "";
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
/*     */   private PlugPagInstallment[] mInstallments;
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
/*     */   public PlugPagInstallmentResult(int result, @NonNull String rate, @Nullable String errorCode, @Nullable String message, PlugPagInstallment[] installments) {
/*  59 */     if (installments == null) {
/*  60 */       throw new PlugPagException("A lista de parcelas não pode ser nula");
/*     */     }
/*     */     
/*  63 */     this.mResult = result;
/*  64 */     this.mRate = rate;
/*  65 */     this.mErrorCode = errorCode;
/*  66 */     this.mMessage = message;
/*  67 */     this.mInstallments = installments;
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
/*     */   public int getResult() {
/*  80 */     return this.mResult;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRate() {
/*  89 */     return this.mRate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getErrorCode() {
/*  98 */     return this.mRate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage() {
/* 107 */     return this.mRate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlugPagInstallment[] getInstallments() {
/* 116 */     return this.mInstallments;
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagInstallmentResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */