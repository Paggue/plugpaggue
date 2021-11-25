package br.com.uol.pagseguro.util;

public class PhoneNumber {
  public static String getDDD(String mobile) {
    return (mobile.length() < 10) ? "" : mobile.substring(0, 2);
  }
  
  public static String getPhoneNumber(String mobile) {
    return (mobile.length() < 10) ? "" : mobile.substring(2);
  }
}
