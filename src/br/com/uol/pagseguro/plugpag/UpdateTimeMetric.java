/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UpdateTimeMetric
/*    */ {
/*    */   static final String PPS_UPDATE_TIME_EVENT_TYPE = "PlugPagUpdateTime";
/*    */   static final String PPS_UPDATE_TIME_EVENT_NAME = "UpdateTimeMetrics";
/*    */   private static final String ATTRIBUTE_CURRENT_TIME = "CurrentTime";
/*    */   private static final String ATTRIBUTE_NEW_TIME = "NewTime";
/*    */   private static final String ATTRIBUTE_SUCCESS_UPDATE_TIME = "SuccessUpdateTime";
/*    */   private static final String ATTRIBUTE_ORIGIN_OPERATION = "OriginOperation";
/*    */   private String currentTime;
/*    */   private String newTime;
/*    */   private int originOperation;
/*    */   private boolean successUpdateTime;
/*    */   
/*    */   private UpdateTimeMetric(Builder builder) {
/* 24 */     this.currentTime = builder.currentTime;
/* 25 */     this.newTime = builder.newTime;
/* 26 */     this.successUpdateTime = builder.successUpdateTime;
/* 27 */     this.originOperation = builder.originOperation;
/*    */   }
/*    */   
/*    */   public static class Builder {
/*    */     private String currentTime;
/*    */     private String newTime;
/*    */     private boolean successUpdateTime;
/*    */     private int originOperation;
/*    */     
/*    */     public Builder currentTime(String currentTime) {
/* 37 */       this.currentTime = currentTime;
/*    */       
/* 39 */       return this;
/*    */     }
/*    */     
/*    */     public Builder newTime(String newTime) {
/* 43 */       this.newTime = newTime;
/*    */       
/* 45 */       return this;
/*    */     }
/*    */     
/*    */     public Builder successUpdateTime(boolean successUpdateTime) {
/* 49 */       this.successUpdateTime = successUpdateTime;
/*    */       
/* 51 */       return this;
/*    */     }
/*    */     
/*    */     public Builder originOperation(int originOperation) {
/* 55 */       this.originOperation = originOperation;
/*    */       
/* 57 */       return this;
/*    */     }
/*    */     
/*    */     public UpdateTimeMetric build() {
/* 61 */       return new UpdateTimeMetric(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public Map<String, Object> toMap() {
/* 66 */     Map<String, Object> bundle = new HashMap<>();
/*    */     
/* 68 */     bundle.put("CurrentTime", this.currentTime);
/* 69 */     bundle.put("NewTime", this.newTime);
/* 70 */     bundle.put("SuccessUpdateTime", Boolean.valueOf(this.successUpdateTime));
/* 71 */     bundle.put("OriginOperation", Integer.valueOf(this.originOperation));
/*    */     
/* 73 */     return bundle;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/UpdateTimeMetric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */