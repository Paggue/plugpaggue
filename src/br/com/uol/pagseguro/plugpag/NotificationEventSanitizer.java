package br.com.uol.pagseguro.plugpag;

import android.text.TextUtils;
import br.com.uol.pagseguro.util.LogFunctions;

public class NotificationEventSanitizer {
  private String mModel;
  
  protected NotificationEventSanitizer(String model) {
    this.mModel = model;
  }
  
  protected String sanitize(String s) {
    LogFunctions.log("getSanitized init: " + s);
    String sanitized = s.trim().toUpperCase().replaceAll("[ \\t\\n]+", " ").replace("DI SPOSITIVO", "DISPOSITIVO").replace("EST ORNADA", "ESTORNADA");
    if (DeviceInfo.Terminal.A50.getModel().equals(this.mModel)) {
      if (sanitized.contains("APROXIME") || sanitized.contains("PASSE CARTAO"))
        sanitized = "APROXIME OU INSIRA O CARTAO"; 
      if (sanitized.contains("PASSE O CARTAO"))
        sanitized = "INSIRA O CARTAO"; 
    } 
    if (sanitized.contains("SOLICITE "))
      sanitized = "DIGITE A SENHA:"; 
    if (TextUtils.isEmpty(sanitized))
      sanitized = "PROCESSANDO"; 
    LogFunctions.log("getSanitized end: " + sanitized);
    return sanitized;
  }
}
