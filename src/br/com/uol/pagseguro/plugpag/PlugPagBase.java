package br.com.uol.pagseguro.plugpag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
import br.com.uol.pagseguro.plugpag.libswitch.LibSwitchReturn;
import br.com.uol.pagseguro.plugpag.qrcodeelo.view.QrCodeEloActivity;
import br.com.uol.pagseguro.util.LogFunctions;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public abstract class PlugPagBase implements IPlugPag {
  public static QrCodeEloActivity mqQrCodeEloActivity;
  
  public static CountDownLatch latch;
  
  PlugPagEventLoggerListener mEventLoggerListener = null;
  
  PlugPagAppIdentification mAppIdentification = null;
  
  PlugPagEventListener mEventListener = null;
  
  PlugPagCustomPrinterLayout mPlugPagCustomPrinterLayout;
  
  Context mContext = null;
  
  private PlugPagReaderInfo readerInfo = null;
  
  DeviceInfo mInfo = null;
  
  static {
    PlugPagLibraryLoader.loadNativeLibraries();
  }
  
  public PlugPagReaderInfo getReaderInfo() {
    if (this.readerInfo == null) {
      LogFunctions.log("Calling get BC Info");
      this.readerInfo = getBcInfo();
    } 
    return this.readerInfo;
  }
  
  public PlugPagAppIdentification getAppIdentification() {
    return this.mAppIdentification;
  }
  
  public void setPlugPagCustomPrinterLayout(PlugPagCustomPrinterLayout plugPagCustomPrinterLayout) {
    this.mPlugPagCustomPrinterLayout = plugPagCustomPrinterLayout;
  }
  
  public void logEvent(PlugPagEventLoggerData data) {
    if (this.mEventLoggerListener != null)
      this.mEventLoggerListener.recordEvent(data); 
  }
  
  public void transactionMetricEvent(TransactionMetric transactionMetric) {
    if (transactionMetric == null)
      return; 
    Map<String, Object> attributes = transactionMetric.toMap();
    if (this.mInfo != null)
      attributes.put("batteryLevel", Float.valueOf(this.mInfo.getBatteryLevel())); 
    PlugPagEventLoggerData data = new PlugPagEventLoggerData("PlugPagTransaction", "CommunicationMetrics", attributes);
    LogFunctions.log("TransactionMetrics: " + attributes.toString());
    logEvent(data);
  }
  
  public void updateTimeMetricEvent(UpdateTimeMetric updateTimeMetric) {
    if (updateTimeMetric == null) {
      LogFunctions.logError("UpdateTimeMetric object is null");
      return;
    } 
    Map<String, Object> attributes = updateTimeMetric.toMap();
    PlugPagEventLoggerData data = new PlugPagEventLoggerData("PlugPagUpdateTime", "UpdateTimeMetrics", attributes);
    LogFunctions.log("UpdateTimeMetrics: " + attributes.toString());
    logEvent(data);
  }
  
  private LibSwitchReturn showQrCode(String qrCodeStr, int value, int qrCodeType) {
    LogFunctions.log("showQrCode: " + qrCodeStr);
    this.mContext.startActivity(QrCodeEloActivity.newInstance(this.mContext, qrCodeStr, value, qrCodeType));
    waitOperation();
    mqQrCodeEloActivity.setPlugPagEventLoggerListener(this.mEventLoggerListener);
    mqQrCodeEloActivity.setCancelListener(new QrCodeEloActivity.CancelListener() {
          public void onCancel() {
            PlugPagBase.this.abort();
          }
        });
    mqQrCodeEloActivity = null;
    return new LibSwitchReturn(LibSwitchReturn.LibSwitchValue.PSC_OK);
  }
  
  public int requestAuthentication(final PlugPagAuthenticationListener listener) {
    int result = 0;
    Intent authIntent = null;
    if (this.mContext == null)
      throw new PlugPagException("O contexto atual não pode ser nula"); 
    result = checkRequirements(0);
    if (result == 0) {
      authIntent = getBaseIntent();
      authIntent.setClass(this.mContext, PlugPagActivity.class);
      authIntent.setAction("PLUGPAG_AUTHENTICATION");
      authIntent.putExtra("EXTRA_APP_IDENTIFICATION", getAppIdentification());
      authIntent.setFlags(1342177280);
      this.mContext.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
              if (intent.hasExtra("PLUGPAG_RESULT")) {
                listener.onSuccess();
              } else {
                listener.onError();
              } 
              context.unregisterReceiver(this);
            }
          },  new IntentFilter("br.com.uol.pagseguro.plugpag.AUTHENTICATION_RESULT"));
      this.mContext.startActivity(authIntent);
    } 
    return result;
  }
  
  protected String getStoragePath(@NonNull Context context) {
    if (context == null)
      throw new PlugPagException("Referência do Context não pode ser nula"); 
    LogFunctions.log(context.getFilesDir().getAbsolutePath());
    return context.getFilesDir().getAbsolutePath();
  }
  
  protected Intent getBaseIntent() {
    Intent intent = new Intent();
    intent.addFlags(1073741824);
    return intent;
  }
  
  public int requestDataInput(int dataType, int minSize, int maxSize, int timeoutMillisec) {
    int result = 0;
    Intent intent = null;
    if (this.mContext == null)
      throw new PlugPagException("Referência da Activity não pode ser nula"); 
    result = checkRequirements(0);
    if (result == 0) {
      intent = getBaseIntent();
      intent.setClass(this.mContext, PlugPagActivity.class);
      intent.setAction("PLUGPAG_DATAINPUT");
      intent.addFlags(268435456);
      intent.putExtra("EXTRA_DATAINPUT_TYPE", dataType);
      intent.putExtra("EXTRA_DATAINPUT_MIN_LENGTH", minSize);
      intent.putExtra("EXTRA_DATAINPUT_MAX_LENGTH", maxSize);
      intent.putExtra("EXTRA_DATAINPUT_TIMEOUT", timeoutMillisec);
      this.mContext.startActivity(intent);
    } 
    return 0;
  }
  
  public int checkRequirements(int deviceType) {
    return PlugPagRequirementsCheck.checkRequirements(this.mContext, deviceType);
  }
  
  public boolean updateVersion(String ppsVersion) {
    boolean retVal = false;
    SharedPreferences curPref = getSharedPreferences("PPS_CUR_VER");
    String curPpsVersion = curPref.getString("ppsVersion_cur", null);
    String curPpVersion = curPref.getString("ppVersion_cur", null);
    if (curPpsVersion == null || (ppsVersion != null && curPpsVersion.compareTo(ppsVersion) != 0)) {
      SharedPreferences.Editor edit = curPref.edit();
      edit.putString("ppsVersion_prev", curPpsVersion);
      edit.putString("ppVersion_prev", curPpVersion);
      edit.putString("ppsVersion_cur", ppsVersion);
      edit.putString("ppVersion_cur", PlugPag.getLibVersion());
      edit.apply();
      performUpdates();
      retVal = true;
    } 
    return retVal;
  }
  
  private void performUpdates() {
    if (getFormerPlugPagVersion() == null)
      createMobTablePendings(); 
  }
  
  private String getFormerPlugPagVersion() {
    SharedPreferences curPref = getSharedPreferences("PPS_CUR_VER");
    return curPref.getString("ppVersion_prev", null);
  }
  
  SharedPreferences getSharedPreferences(String preference) {
    return this.mContext.getSharedPreferences(preference, 0);
  }
  
  void waitOperation() {
    latch = new CountDownLatch(1);
    try {
      latch.await();
    } catch (InterruptedException e) {
      LogFunctions.logError("CountDownLatch erro");
    } 
  }
  
  public native PlugPagReaderInfo getBcInfo();
  
  public native PlugPagCardInfo getCardInfo();
  
  public static native String getLibVersion();
  
  public static native String getApplicationCode();
  
  public native void setup(@NonNull Context paramContext);
  
  public native int initBTConnection(@NonNull PlugPagDevice paramPlugPagDevice);
  
  public native boolean isAuthenticated();
  
  public native void invalidateAuthentication();
  
  public native PlugPagUserData getUserData();
  
  public native PlugPagInitializationResult initializeAndActivatePinpad(PlugPagActivationData paramPlugPagActivationData);
  
  public native PlugPagInitializationResult deactivate(PlugPagActivationData paramPlugPagActivationData);
  
  public native void setNativeEventListener(PlugPagEventListener paramPlugPagEventListener);
  
  public native PlugPagTransactionResult doPayment(@NonNull PlugPagPaymentData paramPlugPagPaymentData);
  
  public native PlugPagTransactionResult voidPayment(@Nullable PlugPagVoidData paramPlugPagVoidData);
  
  public native PlugPagTransactionResult voidPayment();
  
  public native PlugPagTransactionResult voidQRCodePayment(@Nullable PlugPagVoidData paramPlugPagVoidData);
  
  public native String[] calculateInstallments(String paramString);
  
  public native PlugPagInstallmentResult calculateInstallments(String paramString, int paramInt);
  
  public native PlugPagTransactionResult getLastApprovedTransaction();
  
  public native int checkAbort();
  
  public native int checkDelay();
  
  public native int isVoidTransaction();
  
  public native PlugPagAbortResult abort();
  
  public native int setVersionName(@NonNull String paramString1, @NonNull String paramString2);
  
  public native void setConnectionChangeSupport(boolean paramBoolean);
  
  public native void setMockState(boolean paramBoolean);
  
  public native boolean getMockState();
  
  public native void setSelectMock(int paramInt);
  
  public native PlugPagMockResult getMockResult();
  
  public native void createMobTablePendings();
}
