/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlugPagEventLoggerData
/*    */ {
/*    */   private String mEventType;
/*    */   private String mEventName;
/*    */   private Map<String, Object> mEventAttributes;
/*    */   
/*    */   public static PlugPagEventLoggerData getBreadCrumbInstance(String eventType, Map<String, Object> eventAttributes) {
/* 14 */     return new PlugPagEventLoggerData(eventType, "", eventAttributes);
/*    */   }
/*    */   
/*    */   public PlugPagEventLoggerData(String eventType, String eventName) {
/* 18 */     this(eventType, eventName, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlugPagEventLoggerData(String eventType, String eventName, Map<String, Object> eventAttributes) {
/* 23 */     this.mEventType = eventType;
/* 24 */     this.mEventName = eventName;
/* 25 */     this.mEventAttributes = eventAttributes;
/*    */   }
/*    */   
/*    */   public String getEventType() {
/* 29 */     return this.mEventType;
/*    */   }
/*    */   
/*    */   public String getEventName() {
/* 33 */     return this.mEventName;
/*    */   }
/*    */   
/*    */   public Map<String, Object> getEventAttributes() {
/* 37 */     return this.mEventAttributes;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagEventLoggerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */