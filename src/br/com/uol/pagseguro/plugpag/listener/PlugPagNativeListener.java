/*    */ package br.com.uol.pagseguro.plugpag.listener;
/*    */ 
/*    */ import br.com.uol.pagseguro.plugpag.NotificationEventSanitizer;
/*    */ import br.com.uol.pagseguro.plugpag.PlugPagEventData;
/*    */ import br.com.uol.pagseguro.plugpag.PlugPagEventListener;
/*    */ import br.com.uol.pagseguro.plugpag.qrcodeelo.view.QrCodeEloActivity;
/*    */ 
/*    */ public class PlugPagNativeListener
/*    */   extends NotificationEventSanitizer
/*    */   implements PlugPagEventListener {
/*    */   PlugPagEventListener mEventListener;
/*    */   
/*    */   public PlugPagNativeListener(String model, PlugPagEventListener eventListener) {
/* 14 */     super(model);
/* 15 */     this.mEventListener = eventListener;
/*    */   }
/*    */ 
/*    */   
/*    */   public int onEvent(PlugPagEventData data) {
/* 20 */     QrCodeEloActivity.close();
/*    */     
/* 22 */     if (data == null) {
/* 23 */       return 0;
/*    */     }
/*    */     
/* 26 */     String customMessage = data.getCustomMessage();
/* 27 */     if (customMessage == null) {
/* 28 */       return 0;
/*    */     }
/*    */     
/* 31 */     String sanitizedMessage = sanitize(customMessage);
/* 32 */     PlugPagEventData sanitizedData = new PlugPagEventData(data.getEventCode(), sanitizedMessage);
/* 33 */     return this.mEventListener.onEvent(sanitizedData);
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/listener/PlugPagNativeListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */