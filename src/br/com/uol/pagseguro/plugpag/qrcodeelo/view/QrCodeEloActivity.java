/*     */ package br.com.uol.pagseguro.plugpag.qrcodeelo.view;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.view.MenuItem;
/*     */ import android.view.View;
/*     */ import android.widget.Button;
/*     */ import android.widget.TextView;
/*     */ import br.com.uol.pagseguro.plugpag.PlugPag;
/*     */ import br.com.uol.pagseguro.plugpag.PlugPagEventLoggerListener;
/*     */ import br.com.uol.pagseguro.plugpag.R;
/*     */ import br.com.uol.pagseguro.plugpag.base.BaseFragmentActivity;
/*     */ import br.com.uol.pagseguro.plugpag.qrcodeelo.utils.QRCodeView;
/*     */ import br.com.uol.pagseguro.plugpag.util.CurrencyUtil;
/*     */ import br.com.uol.pagseguro.util.LogFunctions;
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
/*     */ public class QrCodeEloActivity
/*     */   extends BaseFragmentActivity
/*     */ {
/*     */   private static final String EXTRA_DATA_QR_CODE_STRING = "EXTRA_DATA_QR_CODE_STRING";
/*     */   private static final String EXTRA_DATA_VALUE = "EXTRA_DATA_VALUE";
/*     */   private static final String EXTRA_DATA_QRTYPE = "EXTRA_DATA_QRTYPE";
/*     */   private static final int QRTYPE_REFUND_TRANSACTION = 1;
/*     */   private static QrCodeEloActivity instance;
/*     */   private TextView mTxtTitle;
/*     */   private int mSelectedTitle;
/*     */   private CancelListener mCancelListener;
/*     */   private PlugPagEventLoggerListener mPlugPagEventLoggerListener;
/*     */   
/*     */   private void customizeQRView(int qrType, View valueView) {
/*  40 */     if (1 == qrType) {
/*  41 */       this.mSelectedTitle = R.string.void_qrcode_title;
/*  42 */       valueView.setVisibility(8);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Intent newInstance(Context mContext, String qrCodeData, int value, int qrType) {
/*  48 */     Intent intent = new Intent(mContext, QrCodeEloActivity.class);
/*  49 */     intent.addFlags(276856832);
/*  50 */     intent.putExtra("EXTRA_DATA_QR_CODE_STRING", qrCodeData);
/*  51 */     intent.putExtra("EXTRA_DATA_VALUE", value);
/*  52 */     intent.putExtra("EXTRA_DATA_QRTYPE", qrType);
/*  53 */     return intent;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getContainerLayoutResource() {
/*  58 */     return R.id.frame_qrcode_layout;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getBaseTitle() {
/*  63 */     return this.mSelectedTitle;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getLayoutResource() {
/*  68 */     return R.layout.activity_qrcode;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getToolBarResource() {
/*  73 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initComponents() {
/*  78 */     LogFunctions.log("Iniciando QrCodeEloActivity");
/*  79 */     PlugPag.mqQrCodeEloActivity = this;
/*  80 */     instance = this;
/*     */     
/*  82 */     getWindow().addFlags(128);
/*  83 */     this.mTxtTitle = (TextView)findViewById(R.id.textview_info);
/*  84 */     TextView textViewValue = (TextView)findViewById(R.id.textview_value);
/*  85 */     this.mSelectedTitle = R.string.all_qrcode_title;
/*     */ 
/*     */     
/*  88 */     int value = getIntent().getExtras().getInt("EXTRA_DATA_VALUE");
/*  89 */     int qrType = getIntent().getExtras().getInt("EXTRA_DATA_QRTYPE");
/*     */     
/*  91 */     customizeQRView(qrType, (View)textViewValue);
/*     */     
/*  93 */     String qrCodeData = getIntent().getExtras().getString("EXTRA_DATA_QR_CODE_STRING");
/*  94 */     QRCodeView qrCodeView = (QRCodeView)findViewById(R.id.qrCodeView);
/*  95 */     qrCodeView.setQRCode(qrCodeData);
/*     */     
/*  97 */     textViewValue.setText(CurrencyUtil.formatMonetaryValue(Double.valueOf(value / 100.0D)));
/*     */     
/*  99 */     Button btnCancel = (Button)findViewById(R.id.button_cancel);
/* 100 */     btnCancel.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */           public void onClick(View v) {
/* 103 */             if (QrCodeEloActivity.this.mCancelListener != null) {
/* 104 */               QrCodeEloActivity.this.mCancelListener.onCancel();
/*     */             }
/*     */           }
/*     */         });
/*     */     
/* 109 */     if (PlugPag.latch != null) {
/* 110 */       PlugPag.latch.countDown();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTitle(CharSequence title) {
/* 116 */     if (this.mTxtTitle != null)
/* 117 */       this.mTxtTitle.setText(title); 
/*     */   }
/*     */   public static interface CancelListener {
/*     */     void onCancel(); }
/*     */   
/*     */   public boolean onOptionsItemSelected(MenuItem item) {
/* 123 */     if (16908332 == item.getItemId()) {
/* 124 */       onBackPressed();
/*     */     }
/* 126 */     return super.onOptionsItemSelected(item);
/*     */   }
/*     */   
/*     */   public void setCancelListener(CancelListener cancelListener) {
/* 130 */     this.mCancelListener = cancelListener;
/*     */   }
/*     */   
/*     */   public void setPlugPagEventLoggerListener(PlugPagEventLoggerListener plugPagEventLoggerListener) {
/* 134 */     this.mPlugPagEventLoggerListener = plugPagEventLoggerListener;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBackPressed() {
/* 139 */     this.mCancelListener.onCancel();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onDestroy() {
/* 144 */     LogFunctions.log("Finalizando QrCodeEloActivity");
/* 145 */     super.onDestroy();
/* 146 */     instance = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void close() {
/* 151 */     if (instance != null) {
/* 152 */       instance.finish();
/* 153 */       instance.overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/qrcodeelo/view/QrCodeEloActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */