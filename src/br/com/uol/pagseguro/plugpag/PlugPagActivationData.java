package br.com.uol.pagseguro.plugpag;

import android.support.annotation.Nullable;

public class PlugPagActivationData {
  private String mActivationCode = null;
  
  private String mSerialNumber = null;
  
  public PlugPagActivationData(@Nullable String mActivationCode) {
    this.mActivationCode = mActivationCode;
  }
  
  @Deprecated
  public PlugPagActivationData(@Nullable String mActivationCode, @Nullable String serialNumber) {
    this.mActivationCode = mActivationCode;
    this.mSerialNumber = serialNumber;
  }
}
