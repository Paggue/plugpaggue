/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ public enum NetworkPreference
/*    */ {
/*  5 */   UNKNOWN_TEC(1, "UNKNOWN_CONNECTION"),
/*  6 */   TEC_2G(3, "2G"),
/*  7 */   TEC_3G(2, "3G"),
/*  8 */   TEC_4G(1, "4G/AUTO");
/*    */   
/*    */   private String name;
/*    */   private int type;
/*    */   
/*    */   NetworkPreference(int type, String name) {
/* 14 */     this.type = type;
/* 15 */     this.name = name;
/*    */   }
/*    */   
/*    */   public int getType() {
/* 19 */     return this.type;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 23 */     return this.name;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/NetworkPreference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */