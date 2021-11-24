/*    */ package br.com.uol.pagseguro.plugpag.util;
/*    */ 
/*    */ import java.text.NumberFormat;
/*    */ import java.util.Locale;
/*    */ 
/*    */ public class CurrencyUtil
/*    */ {
/*    */   public static String formatMonetaryValue(Double value) throws NumberFormatException {
/*  9 */     if (value.doubleValue() < 0.0D) {
/* 10 */       throw new NumberFormatException("Valor não pode ser negativo");
/*    */     }
/* 12 */     NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
/* 13 */     format.setMaximumFractionDigits(2);
/* 14 */     return format.format(value).replace(format.getCurrency().getSymbol(), format.getCurrency().getSymbol() + " ");
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/util/CurrencyUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */