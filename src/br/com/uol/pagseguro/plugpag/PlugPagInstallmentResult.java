package br.com.uol.pagseguro.plugpag;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import br.com.uol.pagseguro.plugpag.exception.PlugPagException;

public class PlugPagInstallmentResult {
  static {
    PlugPagLibraryLoader.loadNativeLibraries();
  }
  
  private int mResult = 0;
  
  private String mMessage = null;
  
  private String mErrorCode = null;
  
  private String mRate = "";
  
  private PlugPagInstallment[] mInstallments;
  
  public PlugPagInstallmentResult(int result, @NonNull String rate, @Nullable String errorCode, @Nullable String message, PlugPagInstallment[] installments) {
    if (installments == null)
      throw new PlugPagException("A lista de parcelas não pode ser nula"); 
    this.mResult = result;
    this.mRate = rate;
    this.mErrorCode = errorCode;
    this.mMessage = message;
    this.mInstallments = installments;
  }
  
  public int getResult() {
    return this.mResult;
  }
  
  public String getRate() {
    return this.mRate;
  }
  
  public String getErrorCode() {
    return this.mRate;
  }
  
  public String getMessage() {
    return this.mRate;
  }
  
  public PlugPagInstallment[] getInstallments() {
    return this.mInstallments;
  }
}
