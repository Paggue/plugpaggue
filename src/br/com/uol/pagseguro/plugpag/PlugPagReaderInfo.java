/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ import android.support.annotation.NonNull;
/*    */ 
/*    */ public class PlugPagReaderInfo
/*    */ {
/*    */   private final String mEmvKernelVersion;
/*    */   private final String mSharedLibraryVersion;
/*    */   
/*    */   public PlugPagReaderInfo(@NonNull String emvKernelVersion, @NonNull String sharedLibraryVersion) {
/* 11 */     this.mEmvKernelVersion = emvKernelVersion;
/* 12 */     this.mSharedLibraryVersion = sharedLibraryVersion;
/*    */   }
/*    */   
/*    */   public String getEmvKernelVersion() {
/* 16 */     return this.mEmvKernelVersion;
/*    */   }
/*    */   
/*    */   public String getSharedLibraryVersion() {
/* 20 */     return this.mSharedLibraryVersion;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagReaderInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */