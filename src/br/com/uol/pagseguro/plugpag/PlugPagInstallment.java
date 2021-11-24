/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class PlugPagInstallment
/*    */ {
/* 11 */   private int mQuantity = 0;
/* 12 */   private int mAmount = 0;
/* 13 */   private int mTotal = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PlugPagInstallment(int quantity, int amount, int total) {
/* 38 */     if (quantity <= 0 || quantity > 18) {
/* 39 */       throw new PlugPagException("Quantidade de parcelas inválida");
/*    */     }
/*    */     
/* 42 */     if (amount < 500 && quantity > 1) {
/* 43 */       throw new PlugPagException("Valor deve ser maior ou igual a R$ 5,00 real");
/*    */     }
/*    */     
/* 46 */     if (total < 1000 && quantity > 1) {
/* 47 */       throw new PlugPagException("Valor deve ser maior ou igual a R$ 10,00 real");
/*    */     }
/*    */     
/* 50 */     this.mQuantity = quantity;
/* 51 */     this.mAmount = amount;
/* 52 */     this.mTotal = total;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getQuantity() {
/* 65 */     return this.mQuantity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getAmount() {
/* 74 */     return this.mAmount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getTotal() {
/* 83 */     return this.mTotal;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagInstallment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */