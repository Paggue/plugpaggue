/*    */ package br.com.uol.pagseguro.plugpag.base;
/*    */ 
/*    */ import android.os.Bundle;
/*    */ import android.support.annotation.NonNull;
/*    */ import android.support.annotation.Nullable;
/*    */ import android.support.v4.app.Fragment;
/*    */ import android.view.LayoutInflater;
/*    */ import android.view.View;
/*    */ import android.view.ViewGroup;
/*    */ import br.com.uol.pagseguro.plugpag.PlugPagEventLoggerData;
/*    */ import br.com.uol.pagseguro.plugpag.PlugPagEventLoggerListener;
/*    */ 
/*    */ 
/*    */ public abstract class BaseFragment
/*    */   extends Fragment
/*    */ {
/*    */   public PlugPagEventLoggerListener mPlugPagEventLoggerListener;
/*    */   
/*    */   protected abstract int getLayoutResource();
/*    */   
/*    */   public abstract String getTagName();
/*    */   
/*    */   public abstract void initComponents(View paramView);
/*    */   
/*    */   public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
/* 26 */     return inflater.inflate(getLayoutResource(), container, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
/* 31 */     super.onViewCreated(view, savedInstanceState);
/* 32 */     initComponents(view);
/*    */   }
/*    */   
/*    */   public void setPlugPagEventLoggerListener(PlugPagEventLoggerListener plugPagEventLoggerListener) {
/* 36 */     this.mPlugPagEventLoggerListener = plugPagEventLoggerListener;
/*    */   }
/*    */   
/*    */   public void sendEventLog(PlugPagEventLoggerData data) {
/* 40 */     if (this.mPlugPagEventLoggerListener != null)
/* 41 */       this.mPlugPagEventLoggerListener.recordBreadCrumb(data); 
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/base/BaseFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */