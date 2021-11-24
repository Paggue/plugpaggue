/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ import android.content.Context;
/*    */ import android.support.annotation.NonNull;
/*    */ import android.util.Log;
/*    */ import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
/*    */ import br.com.uol.pagseguro.plugpag.listener.PlugPagNativeListener;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class PlugPag
/*    */   extends PlugPagBase
/*    */ {
/* 20 */   public static int SELECTED_ITEM_IDX = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   PlugPagEventListener mNativeEventListener = null;
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
/*    */   public PlugPag(@NonNull Context context, @NonNull PlugPagAppIdentification appIdentification) {
/* 40 */     int ret = 0;
/*    */     
/* 42 */     if (context == null) {
/* 43 */       throw new PlugPagException("Referência do Context não pode ser nula");
/*    */     }
/*    */     
/* 46 */     if (appIdentification == null) {
/* 47 */       throw new PlugPagException("AppIdentification não pode ser nula");
/*    */     }
/*    */     
/* 50 */     this.mContext = context;
/* 51 */     this.mAppIdentification = appIdentification;
/*    */     
/* 53 */     setup(context);
/* 54 */     ret = setVersionName(appIdentification.getName(), appIdentification.getVersion());
/*    */     
/* 56 */     if (ret != 0) {
/* 57 */       throw new PlugPagException("Identificação do app invalido");
/*    */     }
/*    */     
/*    */     try {
/* 61 */       InputStream is = context.getAssets().open("keymap.dat");
/* 62 */       FileOutputStream fos = new FileOutputStream(new File(context.getFilesDir(), "keymap.dat"));
/* 63 */       byte[] buffer = new byte[4096];
/* 64 */       int bytesRead = 0;
/*    */       
/* 66 */       while ((bytesRead = is.read(buffer)) > 0) {
/* 67 */         fos.write(buffer, 0, bytesRead);
/*    */       }
/*    */       
/* 70 */       fos.flush();
/* 71 */       fos.close();
/* 72 */       is.close();
/* 73 */     } catch (Exception e) {
/* 74 */       Log.e("KEYMAP", "Error while copying keymap.dat", e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEventListener(PlugPagEventListener listener) {
/* 80 */     this.mEventListener = listener;
/* 81 */     if (this.mNativeEventListener == null)
/* 82 */       setNativeEventListener((PlugPagEventListener)new PlugPagNativeListener("SmartPhone", this.mEventListener)); 
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */