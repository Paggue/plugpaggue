package br.com.uol.pagseguro.plugpag.util;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {
  public static String formatMonetaryValue(Double value) throws NumberFormatException {
    if (value.doubleValue() < 0.0D)
      throw new NumberFormatException("Valor não pode ser negativo"); 
    NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    format.setMaximumFractionDigits(2);
    return format.format(value).replace(format.getCurrency().getSymbol(), format.getCurrency().getSymbol() + " ");
  }
}
