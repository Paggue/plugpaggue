/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ import android.support.annotation.Nullable;
/*    */ 
/*    */ public class PlugPagActivationData {
/*  6 */   private String mActivationCode = null;
/*  7 */   private String mSerialNumber = null;
/*    */ 
/*    */   
/*    */   public PlugPagActivationData(@Nullable String mActivationCode) {
/* 11 */     this.mActivationCode = mActivationCode;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public PlugPagActivationData(@Nullable String mActivationCode, @Nullable String serialNumber) {
/* 16 */     this.mActivationCode = mActivationCode;
/* 17 */     this.mSerialNumber = serialNumber;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagActivationData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */