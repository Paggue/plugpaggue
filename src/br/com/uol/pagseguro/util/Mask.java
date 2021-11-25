package br.com.uol.pagseguro.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class Mask {
  public static final String TELEPHONE = "####-####";
  
  public static final String CELLPHONE = "#####-####";
  
  public static final String DDD_TELEPHONE = "(##) ####-####";
  
  public static final String DDD_CELLPHONE = "(##) #####-####";
  
  private static final int MAX_LENGTH = 10;
  
  public static String applyCellPhoneMask(String cellphone) {
    String maskType = "(##) ####-####";
    if (cellphone != null && cellphone.length() > 10)
      maskType = "(##) #####-####"; 
    return apply(cellphone, maskType);
  }
  
  private static String apply(String str, String typeMask) {
    if (str == null)
      return ""; 
    StringBuilder mask = new StringBuilder();
    int indexValue = 0;
    str = removeAlphabeticCharacters(str);
    for (int indexMask = 0; indexMask < typeMask.length() && indexValue < str.length(); indexMask++) {
      if (typeMask.charAt(indexMask) != '#') {
        mask.append(typeMask.charAt(indexMask));
      } else {
        mask.append(str.charAt(indexValue++));
      } 
    } 
    return mask.toString();
  }
  
  public static String chooseMask(CharSequence s) {
    String str = removeAlphabeticCharacters(s.toString());
    String maskType = "####-####";
    if (str.length() > "####-####".length() - 1)
      maskType = "#####-####"; 
    return maskType;
  }
  
  public static String chooseFullMask(CharSequence s) {
    String str = removeAlphabeticCharacters(s.toString());
    String maskType = "(##) ####-####";
    if (str.length() > 10)
      maskType = "(##) #####-####"; 
    return maskType;
  }
  
  public static String removeAlphabeticCharacters(String value) {
    return value.replaceAll("[^0-9]*", "").trim();
  }
  
  public static TextWatcher insertTelephoneMask(EditText editText) {
    return insertTelephoneMask(editText, false);
  }
  
  public static TextWatcher insertTelephoneMask(final EditText editText, final boolean hasDDD) {
    return new TextWatcher() {
        boolean isUpdating;
        
        String old = "";
        
        public void onTextChanged(CharSequence s, int start, int before, int count) {
          String text = s.toString();
          String maskType = hasDDD ? Mask.chooseFullMask(text) : Mask.chooseMask(text);
          if (this.isUpdating) {
            this.old = text;
            this.isUpdating = false;
            return;
          } 
          String mascara = Mask.apply(text, maskType);
          this.isUpdating = true;
          if (editText != null) {
            editText.setText(mascara);
            editText.setSelection(mascara.length());
          } 
        }
        
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        
        public void afterTextChanged(Editable s) {}
      };
  }
}
