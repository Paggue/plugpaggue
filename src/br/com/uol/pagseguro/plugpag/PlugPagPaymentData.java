package br.com.uol.pagseguro.plugpag;

import android.support.annotation.NonNull;
import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
import java.io.Serializable;

public class PlugPagPaymentData implements Serializable {
  static {
    PlugPagLibraryLoader.loadNativeLibraries();
  }
  
  private int mType = 0;
  
  private int mAmount = 0;
  
  private int mInstallmentType = 0;
  
  private int mInstallments = 1;
  
  private String mUserReference = null;
  
  private boolean mPaymentReceipt = false;
  
  public PlugPagPaymentData(int type, int amount, int installmentType, int installments, String userReference, boolean paymentReceipt) {
    if (type != 1 && type != 2 && type != 3 && type != 4)
      throw new PlugPagException("Tipo de pagamento inválido"); 
    if (amount < 100)
      throw new PlugPagException("Valor deve ser maior ou igual a R$ 1,00 real"); 
    if (installmentType != 1 && installmentType != 2 && installmentType != 3)
      throw new PlugPagException("Tipo de parcelamento inválido"); 
    if (installments <= 0)
      throw new PlugPagException("Quantidade de parcelas inválida"); 
    if (userReference != null && userReference.length() <= 0)
      throw new PlugPagException("Código de venda não pode ser vazio"); 
    this.mType = type;
    this.mAmount = amount;
    this.mInstallmentType = installmentType;
    this.mInstallments = installments;
    this.mUserReference = userReference;
    this.mPaymentReceipt = paymentReceipt;
  }
  
  public PlugPagPaymentData(int type, int amount, int installmentType, int installments, String userReference) {
    this(type, amount, installmentType, installments, userReference, false);
  }
  
  public PlugPagPaymentData(int type, int amount, int installmentType, int installments) {
    this(type, amount, installmentType, installments, null);
  }
  
  public int getType() {
    return this.mType;
  }
  
  public int getAmount() {
    return this.mAmount;
  }
  
  public int getInstallmentType() {
    return this.mInstallmentType;
  }
  
  public int getInstallments() {
    return this.mInstallments;
  }
  
  public String getUserReference() {
    return this.mUserReference;
  }
  
  public boolean getPaymentReceipt() {
    return this.mPaymentReceipt;
  }
  
  public static final class Builder {
    private int mType = 0;
    
    private int mAmount = 0;
    
    private int mInstallmentType = 0;
    
    private int mInstallments = 0;
    
    private String mUserReference = null;
    
    private boolean mPaymentReceipt = false;
    
    public Builder setType(int type) {
      if (type != 1 && type != 2 && type != 3 && type != 4)
        throw new PlugPagException("Tipo de pagamento inválido"); 
      this.mType = type;
      if (type != 1)
        setInstallmentType(1); 
      return this;
    }
    
    public Builder setAmount(int amount) {
      if (amount <= 0)
        throw new PlugPagException("Valor deve maior do que zero"); 
      this.mAmount = amount;
      return this;
    }
    
    public Builder setInstallmentType(int installmentType) {
      if (installmentType != 1 && installmentType != 2 && installmentType != 3)
        throw new PlugPagException("Tipo de parcelamento inválido"); 
      this.mInstallmentType = installmentType;
      if (installmentType == 1)
        this.mInstallments = 1; 
      return this;
    }
    
    public Builder setInstallments(int installments) {
      if (installments <= 0)
        throw new PlugPagException("Quantidade de parcelas inválida"); 
      this.mInstallments = installments;
      if (installments == 1)
        this.mInstallmentType = 1; 
      return this;
    }
    
    public Builder setUserReference(@NonNull String userReference) {
      if (userReference != null && userReference.length() <= 0)
        throw new PlugPagException("Código de vendas não pode ser vazio"); 
      this.mUserReference = userReference;
      return this;
    }
    
    public Builder setPaymentReceipt(boolean paymentReceipt) {
      this.mPaymentReceipt = paymentReceipt;
      return this;
    }
    
    public PlugPagPaymentData build() {
      return new PlugPagPaymentData(this.mType, this.mAmount, this.mInstallmentType, this.mInstallments, this.mUserReference, this.mPaymentReceipt);
    }
  }
}
