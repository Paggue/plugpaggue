/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ import android.support.annotation.NonNull;
/*    */ import android.text.TextUtils;
/*    */ import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlugPagAppIdentification
/*    */   implements Serializable
/*    */ {
/*    */   static {
/* 17 */     PlugPagLibraryLoader.loadNativeLibraries();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   private String mVersion = null;
/* 25 */   private String mName = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PlugPagAppIdentification(@NonNull String name, @NonNull String version) {
/* 40 */     if (TextUtils.isEmpty(name)) {
/* 41 */       throw new PlugPagException("Nome do aplicativo não pode ser nulo ou vazio");
/*    */     }
/*    */     
/* 44 */     if (TextUtils.isEmpty(version)) {
/* 45 */       throw new PlugPagException("Versão do aplicativo não pode ser nulo ou vazio");
/*    */     }
/*    */     
/* 48 */     this.mName = name;
/* 49 */     this.mVersion = version;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getVersion() {
/* 62 */     return this.mVersion;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 71 */     return this.mName;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagAppIdentification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */