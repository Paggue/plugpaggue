package br.com.uol.pagseguro.plugpag;

import br.com.uol.pagseguro.plugpag.exception.PlugPagException;

public final class PlugPagInstallment {
  private int mQuantity = 0;
  
  private int mAmount = 0;
  
  private int mTotal = 0;
  
  public PlugPagInstallment(int quantity, int amount, int total) {
    if (quantity <= 0 || quantity > 18)
      throw new PlugPagException("Quantidade de parcelas inválida"); 
    if (amount < 500 && quantity > 1)
      throw new PlugPagException("Valor deve ser maior ou igual a R$ 5,00 real"); 
    if (total < 1000 && quantity > 1)
      throw new PlugPagException("Valor deve ser maior ou igual a R$ 10,00 real"); 
    this.mQuantity = quantity;
    this.mAmount = amount;
    this.mTotal = total;
  }
  
  public int getQuantity() {
    return this.mQuantity;
  }
  
  public int getAmount() {
    return this.mAmount;
  }
  
  public int getTotal() {
    return this.mTotal;
  }
}
