/*    */ package br.com.uol.pagseguro.plugpag.base;
/*    */ 
/*    */ import android.app.ActivityOptions;
/*    */ import android.content.Context;
/*    */ import android.content.Intent;
/*    */ import android.os.Bundle;
/*    */ import android.support.annotation.IdRes;
/*    */ import android.support.annotation.LayoutRes;
/*    */ import android.support.annotation.Nullable;
/*    */ import android.support.annotation.StringRes;
/*    */ import android.support.v7.app.AppCompatActivity;
/*    */ import android.support.v7.widget.Toolbar;
/*    */ import br.com.uol.pagseguro.plugpag.R;
/*    */ 
/*    */ 
/*    */ public abstract class BaseActivity
/*    */   extends AppCompatActivity
/*    */ {
/*    */   @StringRes
/*    */   protected abstract int getBaseTitle();
/*    */   
/*    */   @LayoutRes
/*    */   protected abstract int getLayoutResource();
/*    */   
/*    */   @IdRes
/*    */   protected abstract int getToolBarResource();
/*    */   
/*    */   protected abstract void initComponents();
/*    */   
/*    */   protected void onCreate(@Nullable Bundle savedInstanceState) {
/* 31 */     super.onCreate(savedInstanceState);
/* 32 */     requestWindowFeature(1);
/* 33 */     setContentView(getLayoutResource());
/*    */     
/* 35 */     Toolbar toolbar = (Toolbar)findViewById(getToolBarResource());
/* 36 */     setSupportActionBar(toolbar);
/*    */     
/* 38 */     if (getSupportActionBar() != null) {
/* 39 */       getSupportActionBar().setDisplayShowTitleEnabled(false);
/* 40 */       setDisplayHomeButton(Boolean.valueOf(true));
/*    */     } 
/*    */     
/* 43 */     initComponents();
/*    */   }
/*    */   
/*    */   public void setDisplayHomeButton(Boolean isHomeButton) {
/* 47 */     getSupportActionBar().setDisplayHomeAsUpEnabled(isHomeButton.booleanValue());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void onResume() {
/* 52 */     super.onResume();
/* 53 */     setTitle(getBaseTitle());
/*    */   }
/*    */   
/*    */   protected Integer getIntExtra(String key) {
/* 57 */     if (getIntent().getExtras() != null) {
/* 58 */       return Integer.valueOf(getIntent().getExtras().getInt(key));
/*    */     }
/* 60 */     return Integer.valueOf(0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected String[] getListExtra(String key) {
/* 65 */     if (getIntent().getExtras() != null) {
/* 66 */       return getIntent().getExtras().getStringArray(key);
/*    */     }
/* 68 */     return new String[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void startActivity(Intent intent) {
/* 74 */     Bundle options = ActivityOptions.makeCustomAnimation((Context)this, R.anim.enter_from_right, R.anim.fade_out).toBundle();
/* 75 */     startActivity(intent, options);
/*    */   }
/*    */ 
/*    */   
/*    */   public void finish() {
/* 80 */     super.finish();
/* 81 */     overridePendingTransition(R.anim.fade_in, R.anim.exit_to_right);
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/base/BaseActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */