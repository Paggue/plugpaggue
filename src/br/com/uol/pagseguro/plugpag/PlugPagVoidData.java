package br.com.uol.pagseguro.plugpag;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
import br.com.uol.pagseguro.plugpag.exception.PlugPagVoidTransactionException;

public final class PlugPagVoidData {
  static {
    PlugPagLibraryLoader.loadNativeLibraries();
  }
  
  public String mTransactionCode = null;
  
  public String mTransactionId = null;
  
  public boolean mVoidReceipt = false;
  
  public PlugPagVoidData(@NonNull String transactionCode, @NonNull String transactionId) {
    if (TextUtils.isEmpty(transactionCode))
      throw new PlugPagVoidTransactionException("Código da transação é obrigatório para realizar um estorno"); 
    this.mTransactionCode = transactionCode;
    this.mTransactionId = transactionId;
  }
  
  public PlugPagVoidData(@NonNull String transactionCode, @NonNull String transactionId, boolean voidReceipt) {
    if (TextUtils.isEmpty(transactionCode))
      throw new PlugPagVoidTransactionException("Código da transação é obrigatório para realizar um estorno"); 
    this.mTransactionCode = transactionCode;
    this.mTransactionId = transactionId;
    this.mVoidReceipt = voidReceipt;
  }
  
  public PlugPagVoidData(@NonNull String transactionCode, @NonNull String transactionId, boolean voidReceipt, boolean mockStateActived) {
    if (TextUtils.isEmpty(transactionCode) && !mockStateActived)
      throw new PlugPagVoidTransactionException("Código da transação é obrigatório para realizar um estorno"); 
    this.mTransactionCode = transactionCode;
    this.mTransactionId = transactionId;
    this.mVoidReceipt = voidReceipt;
  }
  
  public String getTransactionCode() {
    return this.mTransactionCode;
  }
  
  public String getTransactionId() {
    return this.mTransactionId;
  }
  
  public boolean getVoidReceipt() {
    return this.mVoidReceipt;
  }
  
  public static final class Builder {
    private String mTransactionCode = null;
    
    private String mTransactionId = null;
    
    private boolean mVoidReceipt = false;
    
    private boolean mMock = false;
    
    public Builder setTransactionCode(@NonNull String transactionCode) {
      if (TextUtils.isEmpty(transactionCode))
        throw new PlugPagException("Código da transação não pode ser nulo ou vazio"); 
      this.mTransactionCode = transactionCode;
      return this;
    }
    
    public Builder setTransactionId(@NonNull String transactionId) {
      this.mTransactionId = transactionId;
      return this;
    }
    
    public Builder setVoidReceipt(boolean voidReceipt) {
      this.mVoidReceipt = voidReceipt;
      return this;
    }
    
    public PlugPagVoidData build() {
      return new PlugPagVoidData(this.mTransactionCode, this.mTransactionId, this.mVoidReceipt, this.mMock);
    }
  }
}
