package br.com.uol.pagseguro.plugpag.listener;

import br.com.uol.pagseguro.plugpag.NotificationEventSanitizer;
import br.com.uol.pagseguro.plugpag.PlugPagEventData;
import br.com.uol.pagseguro.plugpag.PlugPagEventListener;
import br.com.uol.pagseguro.plugpag.qrcodeelo.view.QrCodeEloActivity;

public class PlugPagNativeListener extends NotificationEventSanitizer implements PlugPagEventListener {
  PlugPagEventListener mEventListener;
  
  public PlugPagNativeListener(String model, PlugPagEventListener eventListener) {
    super(model);
    this.mEventListener = eventListener;
  }
  
  public int onEvent(PlugPagEventData data) {
    QrCodeEloActivity.close();
    if (data == null)
      return 0; 
    String customMessage = data.getCustomMessage();
    if (customMessage == null)
      return 0; 
    String sanitizedMessage = sanitize(customMessage);
    PlugPagEventData sanitizedData = new PlugPagEventData(data.getEventCode(), sanitizedMessage);
    return this.mEventListener.onEvent(sanitizedData);
  }
}
