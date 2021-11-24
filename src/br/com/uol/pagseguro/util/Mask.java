/*    */ package br.com.uol.pagseguro.util;
/*    */ 
/*    */ import android.text.Editable;
/*    */ import android.text.TextWatcher;
/*    */ import android.widget.EditText;
/*    */ 
/*    */ public class Mask
/*    */ {
/*    */   public static final String TELEPHONE = "####-####";
/*    */   public static final String CELLPHONE = "#####-####";
/*    */   public static final String DDD_TELEPHONE = "(##) ####-####";
/*    */   public static final String DDD_CELLPHONE = "(##) #####-####";
/*    */   private static final int MAX_LENGTH = 10;
/*    */   
/*    */   public static String applyCellPhoneMask(String cellphone) {
/* 16 */     String maskType = "(##) ####-####";
/* 17 */     if (cellphone != null && cellphone.length() > 10) {
/* 18 */       maskType = "(##) #####-####";
/*    */     }
/* 20 */     return apply(cellphone, maskType);
/*    */   }
/*    */   
/*    */   private static String apply(String str, String typeMask) {
/* 24 */     if (str == null) {
/* 25 */       return "";
/*    */     }
/* 27 */     StringBuilder mask = new StringBuilder();
/* 28 */     int indexValue = 0;
/*    */     
/* 30 */     str = removeAlphabeticCharacters(str);
/*    */     
/* 32 */     for (int indexMask = 0; indexMask < typeMask.length() && indexValue < str.length(); indexMask++) {
/* 33 */       if (typeMask.charAt(indexMask) != '#') {
/* 34 */         mask.append(typeMask.charAt(indexMask));
/*    */       } else {
/* 36 */         mask.append(str.charAt(indexValue++));
/*    */       } 
/*    */     } 
/* 39 */     return mask.toString();
/*    */   }
/*    */   
/*    */   public static String chooseMask(CharSequence s) {
/* 43 */     String str = removeAlphabeticCharacters(s.toString());
/*    */     
/* 45 */     String maskType = "####-####";
/* 46 */     if (str.length() > "####-####".length() - 1) {
/* 47 */       maskType = "#####-####";
/*    */     }
/*    */     
/* 50 */     return maskType;
/*    */   }
/*    */   
/*    */   public static String chooseFullMask(CharSequence s) {
/* 54 */     String str = removeAlphabeticCharacters(s.toString());
/*    */     
/* 56 */     String maskType = "(##) ####-####";
/* 57 */     if (str.length() > 10) {
/* 58 */       maskType = "(##) #####-####";
/*    */     }
/*    */     
/* 61 */     return maskType;
/*    */   }
/*    */   
/*    */   public static String removeAlphabeticCharacters(String value) {
/* 65 */     return value.replaceAll("[^0-9]*", "").trim();
/*    */   }
/*    */   
/*    */   public static TextWatcher insertTelephoneMask(EditText editText) {
/* 69 */     return insertTelephoneMask(editText, false);
/*    */   }
/*    */   
/*    */   public static TextWatcher insertTelephoneMask(final EditText editText, final boolean hasDDD) {
/* 73 */     return new TextWatcher() {
/*    */         boolean isUpdating;
/* 75 */         String old = "";
/*    */         
/*    */         public void onTextChanged(CharSequence s, int start, int before, int count) {
/* 78 */           String text = s.toString();
/* 79 */           String maskType = hasDDD ? Mask.chooseFullMask(text) : Mask.chooseMask(text);
/*    */           
/* 81 */           if (this.isUpdating) {
/* 82 */             this.old = text;
/* 83 */             this.isUpdating = false;
/*    */             return;
/*    */           } 
/* 86 */           String mascara = Mask.apply(text, maskType);
/*    */           
/* 88 */           this.isUpdating = true;
/* 89 */           if (editText != null) {
/* 90 */             editText.setText(mascara);
/* 91 */             editText.setSelection(mascara.length());
/*    */           } 
/*    */         }
/*    */         
/*    */         public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
/*    */         
/*    */         public void afterTextChanged(Editable s) {}
/*    */       };
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/util/Mask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */