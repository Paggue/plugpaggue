/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ import android.text.TextUtils;
/*    */ import br.com.uol.pagseguro.util.LogFunctions;
/*    */ 
/*    */ 
/*    */ public class NotificationEventSanitizer
/*    */ {
/*    */   private String mModel;
/*    */   
/*    */   protected NotificationEventSanitizer(String model) {
/* 12 */     this.mModel = model;
/*    */   }
/*    */   
/*    */   protected String sanitize(String s) {
/* 16 */     LogFunctions.log("getSanitized init: " + s);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 23 */     String sanitized = s.trim().toUpperCase().replaceAll("[ \\t\\n]+", " ").replace("DI SPOSITIVO", "DISPOSITIVO").replace("EST ORNADA", "ESTORNADA");
/*    */     
/* 25 */     if (DeviceInfo.Terminal.A50.getModel().equals(this.mModel)) {
/* 26 */       if (sanitized.contains("APROXIME") || sanitized.contains("PASSE CARTAO")) {
/* 27 */         sanitized = "APROXIME OU INSIRA O CARTAO";
/*    */       }
/*    */       
/* 30 */       if (sanitized.contains("PASSE O CARTAO")) {
/* 31 */         sanitized = "INSIRA O CARTAO";
/*    */       }
/*    */     } 
/* 34 */     if (sanitized.contains("SOLICITE ")) {
/* 35 */       sanitized = "DIGITE A SENHA:";
/*    */     }
/* 37 */     if (TextUtils.isEmpty(sanitized)) {
/* 38 */       sanitized = "PROCESSANDO";
/*    */     }
/*    */     
/* 41 */     LogFunctions.log("getSanitized end: " + sanitized);
/* 42 */     return sanitized;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/NotificationEventSanitizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */