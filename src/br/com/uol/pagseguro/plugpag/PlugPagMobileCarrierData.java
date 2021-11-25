package br.com.uol.pagseguro.plugpag;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
import java.io.Serializable;

public class PlugPagMobileCarrierData implements Serializable {
  private String mMobileNumber;
  
  static {
    PlugPagLibraryLoader.loadNativeLibraries();
  }
  
  private String mUserReference = null;
  
  private boolean mPaymentReceipt = false;
  
  public PlugPagMobileCarrierData(String userReference) {
    this(userReference, false, "");
  }
  
  public PlugPagMobileCarrierData(String userReference, boolean paymentReceipt) {
    this(userReference, paymentReceipt, "");
  }
  
  public PlugPagMobileCarrierData(String userReference, boolean paymentReceipt, String mobileNumber) {
    if (TextUtils.isEmpty(userReference))
      throw new PlugPagException("Código de vendas não pode ser nulo ou vazio"); 
    this.mMobileNumber = mobileNumber;
    this.mUserReference = userReference;
    this.mPaymentReceipt = paymentReceipt;
  }
  
  public String getUserReference() {
    return this.mUserReference;
  }
  
  public boolean getPaymentReceipt() {
    return this.mPaymentReceipt;
  }
  
  public String getMobileNumber() {
    return this.mMobileNumber;
  }
  
  public static final class Builder {
    private String mUserReference = null;
    
    private boolean mPaymentReceipt = false;
    
    private String mMobileNumber = null;
    
    public Builder setUserReference(@NonNull String userReference) {
      if (TextUtils.isEmpty(userReference))
        throw new PlugPagException("Código de vendas não pode ser vazio ou nulo"); 
      this.mUserReference = userReference;
      return this;
    }
    
    public Builder setPaymentReceipt(boolean paymentReceipt) {
      this.mPaymentReceipt = paymentReceipt;
      return this;
    }
    
    public Builder setMobileNumber(String mobileNumber) {
      this.mMobileNumber = mobileNumber;
      return this;
    }
    
    public PlugPagMobileCarrierData build() {
      return new PlugPagMobileCarrierData(this.mUserReference, this.mPaymentReceipt, this.mMobileNumber);
    }
  }
}
