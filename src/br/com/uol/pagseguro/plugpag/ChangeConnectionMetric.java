/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ChangeConnectionMetric
/*    */ {
/*    */   private String currentNetwork;
/*    */   private String preferredNetwork;
/*    */   private String successChangeConnection;
/*    */   private String firmwareVersion;
/*    */   private String model;
/*    */   private String simCardId;
/*    */   
/*    */   private ChangeConnectionMetric(Builder builder) {
/* 16 */     this.currentNetwork = builder.currentNetwork;
/* 17 */     this.preferredNetwork = builder.preferredNetwork;
/* 18 */     this.successChangeConnection = builder.successChangeConnection;
/* 19 */     this.firmwareVersion = builder.firmwareVersion;
/* 20 */     this.model = builder.model;
/* 21 */     this.simCardId = builder.simCardId;
/*    */   }
/*    */   
/*    */   public static class Builder
/*    */   {
/*    */     private String currentNetwork;
/*    */     private String preferredNetwork;
/*    */     private String successChangeConnection;
/*    */     private String firmwareVersion;
/*    */     private String model;
/*    */     private String simCardId;
/*    */     
/*    */     public Builder currentNetwork(String currentNetwork) {
/* 34 */       this.currentNetwork = currentNetwork;
/*    */       
/* 36 */       return this;
/*    */     }
/*    */     
/*    */     public Builder preferredNetwork(String preferredNetwork) {
/* 40 */       this.preferredNetwork = preferredNetwork;
/*    */       
/* 42 */       return this;
/*    */     }
/*    */     
/*    */     public Builder successChangeConnection(String successChangeConnection) {
/* 46 */       this.successChangeConnection = successChangeConnection;
/*    */       
/* 48 */       return this;
/*    */     }
/*    */     
/*    */     public Builder firmwareVersion(String firmwareVersion) {
/* 52 */       this.firmwareVersion = firmwareVersion;
/*    */       
/* 54 */       return this;
/*    */     }
/*    */     
/*    */     public Builder model(String model) {
/* 58 */       this.model = model;
/*    */       
/* 60 */       return this;
/*    */     }
/*    */     
/*    */     public Builder simCardId(String simCardId) {
/* 64 */       this.simCardId = simCardId;
/*    */       
/* 66 */       return this;
/*    */     }
/*    */     
/*    */     public ChangeConnectionMetric build() {
/* 70 */       return new ChangeConnectionMetric(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public Map<String, Object> toMap() {
/* 75 */     Map<String, Object> bundle = new HashMap<>();
/*    */     
/* 77 */     bundle.put("CurrentNetwork", this.currentNetwork);
/* 78 */     bundle.put("PreferredNetwork", this.preferredNetwork);
/* 79 */     bundle.put("SuccessChangeConnection", this.successChangeConnection);
/* 80 */     bundle.put("FirmwareVersion", this.firmwareVersion);
/* 81 */     bundle.put("Model", this.model);
/* 82 */     bundle.put("idSimCard", this.simCardId);
/*    */     
/* 84 */     return bundle;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/ChangeConnectionMetric.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */