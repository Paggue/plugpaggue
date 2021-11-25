package br.com.uol.pagseguro.plugpag.qrcodeelo.view;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import br.com.uol.pagseguro.plugpag.PlugPag;
import br.com.uol.pagseguro.plugpag.PlugPagEventLoggerListener;
import br.com.uol.pagseguro.plugpag.R;
import br.com.uol.pagseguro.plugpag.base.BaseFragmentActivity;
import br.com.uol.pagseguro.plugpag.qrcodeelo.utils.QRCodeView;
import br.com.uol.pagseguro.plugpag.util.CurrencyUtil;
import br.com.uol.pagseguro.util.LogFunctions;

public class QrCodeEloActivity extends BaseFragmentActivity {
  private static final String EXTRA_DATA_QR_CODE_STRING = "EXTRA_DATA_QR_CODE_STRING";
  
  private static final String EXTRA_DATA_VALUE = "EXTRA_DATA_VALUE";
  
  private static final String EXTRA_DATA_QRTYPE = "EXTRA_DATA_QRTYPE";
  
  private static final int QRTYPE_REFUND_TRANSACTION = 1;
  
  private static QrCodeEloActivity instance;
  
  private TextView mTxtTitle;
  
  private int mSelectedTitle;
  
  private CancelListener mCancelListener;
  
  private PlugPagEventLoggerListener mPlugPagEventLoggerListener;
  
  private void customizeQRView(int qrType, View valueView) {
    if (1 == qrType) {
      this.mSelectedTitle = R.string.void_qrcode_title;
      valueView.setVisibility(8);
    } 
  }
  
  public static Intent newInstance(Context mContext, String qrCodeData, int value, int qrType) {
    Intent intent = new Intent(mContext, QrCodeEloActivity.class);
    intent.addFlags(276856832);
    intent.putExtra("EXTRA_DATA_QR_CODE_STRING", qrCodeData);
    intent.putExtra("EXTRA_DATA_VALUE", value);
    intent.putExtra("EXTRA_DATA_QRTYPE", qrType);
    return intent;
  }
  
  protected int getContainerLayoutResource() {
    return R.id.frame_qrcode_layout;
  }
  
  protected int getBaseTitle() {
    return this.mSelectedTitle;
  }
  
  protected int getLayoutResource() {
    return R.layout.activity_qrcode;
  }
  
  protected int getToolBarResource() {
    return 0;
  }
  
  protected void initComponents() {
    LogFunctions.log("Iniciando QrCodeEloActivity");
    PlugPag.mqQrCodeEloActivity = this;
    instance = this;
    getWindow().addFlags(128);
    this.mTxtTitle = (TextView)findViewById(R.id.textview_info);
    TextView textViewValue = (TextView)findViewById(R.id.textview_value);
    this.mSelectedTitle = R.string.all_qrcode_title;
    int value = getIntent().getExtras().getInt("EXTRA_DATA_VALUE");
    int qrType = getIntent().getExtras().getInt("EXTRA_DATA_QRTYPE");
    customizeQRView(qrType, (View)textViewValue);
    String qrCodeData = getIntent().getExtras().getString("EXTRA_DATA_QR_CODE_STRING");
    QRCodeView qrCodeView = (QRCodeView)findViewById(R.id.qrCodeView);
    qrCodeView.setQRCode(qrCodeData);
    textViewValue.setText(CurrencyUtil.formatMonetaryValue(Double.valueOf(value / 100.0D)));
    Button btnCancel = (Button)findViewById(R.id.button_cancel);
    btnCancel.setOnClickListener(new View.OnClickListener() {
          public void onClick(View v) {
            if (QrCodeEloActivity.this.mCancelListener != null)
              QrCodeEloActivity.this.mCancelListener.onCancel(); 
          }
        });
    if (PlugPag.latch != null)
      PlugPag.latch.countDown(); 
  }
  
  public static interface CancelListener {
    void onCancel();
  }
  
  public void setTitle(CharSequence title) {
    if (this.mTxtTitle != null)
      this.mTxtTitle.setText(title); 
  }
  
  public boolean onOptionsItemSelected(MenuItem item) {
    if (16908332 == item.getItemId())
      onBackPressed(); 
    return super.onOptionsItemSelected(item);
  }
  
  public void setCancelListener(CancelListener cancelListener) {
    this.mCancelListener = cancelListener;
  }
  
  public void setPlugPagEventLoggerListener(PlugPagEventLoggerListener plugPagEventLoggerListener) {
    this.mPlugPagEventLoggerListener = plugPagEventLoggerListener;
  }
  
  public void onBackPressed() {
    this.mCancelListener.onCancel();
  }
  
  protected void onDestroy() {
    LogFunctions.log("Finalizando QrCodeEloActivity");
    super.onDestroy();
    instance = null;
  }
  
  public static void close() {
    if (instance != null) {
      instance.finish();
      instance.overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
    } 
  }
}
