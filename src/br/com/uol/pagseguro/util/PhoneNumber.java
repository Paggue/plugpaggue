/*    */ package br.com.uol.pagseguro.util;
/*    */ 
/*    */ public class PhoneNumber
/*    */ {
/*    */   public static String getDDD(String mobile) {
/*  6 */     return (mobile.length() < 10) ? "" : mobile.substring(0, 2);
/*    */   }
/*    */   
/*    */   public static String getPhoneNumber(String mobile) {
/* 10 */     return (mobile.length() < 10) ? "" : mobile.substring(2);
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/util/PhoneNumber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */