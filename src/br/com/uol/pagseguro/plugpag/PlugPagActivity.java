/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.os.Bundle;
/*     */ import android.support.annotation.NonNull;
/*     */ import android.support.annotation.Nullable;
/*     */ import android.support.design.R;
/*     */ import android.support.design.widget.Snackbar;
/*     */ import android.support.v4.app.Fragment;
/*     */ import android.support.v4.content.ContextCompat;
/*     */ import android.support.v7.app.AppCompatActivity;
/*     */ import android.support.v7.widget.AppCompatTextView;
/*     */ import android.text.TextUtils;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import br.com.uol.pagseguro.plugpag.authentication.PlugPagAuthenticationFragment;
/*     */ import br.com.uol.pagseguro.plugpag.datainput.PlugPagDataInputFragment;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlugPagActivity
/*     */   extends AppCompatActivity
/*     */   implements PlugPagFragmentInteractionListener
/*     */ {
/*     */   public static final String ACTION_PLUGPAG_AUTHENTICATION = "PLUGPAG_AUTHENTICATION";
/*     */   public static final String ACTION_PLUGPAG_DATAINPUT = "PLUGPAG_DATAINPUT";
/*     */   public static final String ACTION_PLUGPAG_YNINPUT = "PLUGPAG_YNINPUT";
/*     */   public static final String ACTION_PLUGPAG_AUTHENTICATION_RESULT = "br.com.uol.pagseguro.plugpag.AUTHENTICATION_RESULT";
/*     */   public static final String EXTRA_APP_IDENTIFICATION = "EXTRA_APP_IDENTIFICATION";
/*     */   public static final String EXTRA_PLUGPAG_AUTHENTICATION_RESULT = "PLUGPAG_RESULT";
/*     */   
/*     */   protected void onStart() {
/*  48 */     super.onStart();
/*  49 */     getWindow().setSoftInputMode(16);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBackPressed() {
/*  54 */     tellFragments();
/*  55 */     super.onBackPressed();
/*     */   }
/*     */   
/*     */   private void tellFragments() {
/*  59 */     List<Fragment> fragments = getSupportFragmentManager().getFragments();
/*  60 */     for (Fragment f : fragments) {
/*  61 */       if (f != null && f instanceof PlugPagDataInputFragment) {
/*  62 */         ((PlugPagDataInputFragment)f).onBackPressed();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void onCreate(@Nullable Bundle savedInstanceState) {
/*  68 */     super.onCreate(savedInstanceState);
/*  69 */     setContentView(R.layout.activity_plugpag);
/*     */   }
/*     */ 
/*     */   
/*     */   @SuppressLint({"SourceLockedOrientationActivity"})
/*     */   protected void onResume() {
/*  75 */     Intent intent = null;
/*  76 */     String action = null;
/*  77 */     PlugPagFragment fragment = null;
/*  78 */     Bundle args = null;
/*     */     
/*  80 */     super.onResume();
/*     */ 
/*     */     
/*  83 */     setRequestedOrientation(7);
/*     */ 
/*     */     
/*  86 */     intent = getIntent();
/*     */     
/*  88 */     if (intent != null && !TextUtils.isEmpty(intent.getAction())) {
/*  89 */       PlugPagAuthenticationFragment plugPagAuthenticationFragment; PlugPagDataInputFragment plugPagDataInputFragment; action = intent.getAction();
/*     */       
/*  91 */       args = new Bundle();
/*  92 */       args.putSerializable("ARG_APP_IDENTIFICATION", intent.getSerializableExtra("EXTRA_APP_IDENTIFICATION"));
/*     */       
/*  94 */       switch (action) {
/*     */         case "PLUGPAG_AUTHENTICATION":
/*  96 */           plugPagAuthenticationFragment = new PlugPagAuthenticationFragment();
/*     */           break;
/*     */         case "PLUGPAG_DATAINPUT":
/*  99 */           plugPagDataInputFragment = new PlugPagDataInputFragment();
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 105 */       plugPagDataInputFragment.setArguments(getIntent().getExtras());
/* 106 */       getSupportFragmentManager()
/* 107 */         .beginTransaction()
/* 108 */         .replace(R.id.plupag_activity_main_container, (Fragment)plugPagDataInputFragment)
/* 109 */         .commit();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void interact(@NonNull Intent intent) {
/* 119 */     if (intent != null && 
/* 120 */       "ACTION_SHOW_SNACKBAR".equals(intent.getAction())) {
/* 121 */       showSnackbar(intent);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void showSnackbar(Intent intent) {
/* 136 */     String title = null;
/* 137 */     String message = null;
/* 138 */     Snackbar snackbar = null;
/* 139 */     ViewGroup customView = null;
/*     */ 
/*     */     
/* 142 */     title = intent.getStringExtra("KEY_TITLE");
/* 143 */     message = intent.getStringExtra("KEY_MESSAGE");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     customView = (ViewGroup)LayoutInflater.from(getApplicationContext()).inflate(R.layout.plugpag_snackbar_error, null);
/* 149 */     ((AppCompatTextView)customView.findViewById(R.id.plugpag_snackbar_title)).setText(title);
/* 150 */     ((AppCompatTextView)customView.findViewById(R.id.plugpag_snackbar_message)).setText(message);
/*     */ 
/*     */     
/* 153 */     snackbar = Snackbar.make(
/* 154 */         getWindow().getDecorView().findViewById(16908290), "", -1);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 159 */     snackbar.getView().setBackgroundColor(ContextCompat.getColor((Context)this, R.color.plugpag_error));
/* 160 */     snackbar.getView()
/* 161 */       .findViewById(R.id.snackbar_text)
/* 162 */       .setVisibility(4);
/* 163 */     ((Snackbar.SnackbarLayout)snackbar.getView()).addView((View)customView, 0);
/*     */ 
/*     */     
/* 166 */     snackbar.show();
/*     */   }
/*     */   
/*     */   public void setPlugPagTheme(int plugPagTheme) {
/* 170 */     setTheme(plugPagTheme);
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */