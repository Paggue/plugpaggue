/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import android.content.BroadcastReceiver;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.IntentFilter;
/*     */ import android.content.SharedPreferences;
/*     */ import android.support.annotation.NonNull;
/*     */ import android.support.annotation.Nullable;
/*     */ import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
/*     */ import br.com.uol.pagseguro.plugpag.libswitch.LibSwitchReturn;
/*     */ import br.com.uol.pagseguro.plugpag.qrcodeelo.view.QrCodeEloActivity;
/*     */ import br.com.uol.pagseguro.util.LogFunctions;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.CountDownLatch;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PlugPagBase
/*     */   implements IPlugPag
/*     */ {
/*     */   public static QrCodeEloActivity mqQrCodeEloActivity;
/*     */   public static CountDownLatch latch;
/*  39 */   PlugPagEventLoggerListener mEventLoggerListener = null;
/*     */ 
/*     */ 
/*     */   
/*  43 */   PlugPagAppIdentification mAppIdentification = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   PlugPagEventListener mEventListener = null;
/*     */   
/*     */   PlugPagCustomPrinterLayout mPlugPagCustomPrinterLayout;
/*     */   
/*  52 */   Context mContext = null;
/*     */   
/*  54 */   private PlugPagReaderInfo readerInfo = null;
/*     */   
/*  56 */   DeviceInfo mInfo = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  63 */     PlugPagLibraryLoader.loadNativeLibraries();
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
/*     */   
/*     */   public PlugPagReaderInfo getReaderInfo() {
/*  78 */     if (this.readerInfo == null) {
/*  79 */       LogFunctions.log("Calling get BC Info");
/*  80 */       this.readerInfo = getBcInfo();
/*     */     } 
/*  82 */     return this.readerInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlugPagAppIdentification getAppIdentification() {
/*  91 */     return this.mAppIdentification;
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
/*     */   public void setPlugPagCustomPrinterLayout(PlugPagCustomPrinterLayout plugPagCustomPrinterLayout) {
/* 157 */     this.mPlugPagCustomPrinterLayout = plugPagCustomPrinterLayout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void logEvent(PlugPagEventLoggerData data) {
/* 166 */     if (this.mEventLoggerListener != null) {
/* 167 */       this.mEventLoggerListener.recordEvent(data);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void transactionMetricEvent(TransactionMetric transactionMetric) {
/* 173 */     if (transactionMetric == null) {
/*     */       return;
/*     */     }
/* 176 */     Map<String, Object> attributes = transactionMetric.toMap();
/*     */     
/* 178 */     if (this.mInfo != null) {
/* 179 */       attributes.put("batteryLevel", Float.valueOf(this.mInfo.getBatteryLevel()));
/*     */     }
/*     */     
/* 182 */     PlugPagEventLoggerData data = new PlugPagEventLoggerData("PlugPagTransaction", "CommunicationMetrics", attributes);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     LogFunctions.log("TransactionMetrics: " + attributes.toString());
/*     */     
/* 189 */     logEvent(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTimeMetricEvent(UpdateTimeMetric updateTimeMetric) {
/* 194 */     if (updateTimeMetric == null) {
/* 195 */       LogFunctions.logError("UpdateTimeMetric object is null");
/*     */       
/*     */       return;
/*     */     } 
/* 199 */     Map<String, Object> attributes = updateTimeMetric.toMap();
/*     */     
/* 201 */     PlugPagEventLoggerData data = new PlugPagEventLoggerData("PlugPagUpdateTime", "UpdateTimeMetrics", attributes);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 207 */     LogFunctions.log("UpdateTimeMetrics: " + attributes.toString());
/*     */     
/* 209 */     logEvent(data);
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
/*     */   private LibSwitchReturn showQrCode(String qrCodeStr, int value, int qrCodeType) {
/* 235 */     LogFunctions.log("showQrCode: " + qrCodeStr);
/*     */     
/* 237 */     this.mContext.startActivity(QrCodeEloActivity.newInstance(this.mContext, qrCodeStr, value, qrCodeType));
/*     */     
/* 239 */     waitOperation();
/* 240 */     mqQrCodeEloActivity.setPlugPagEventLoggerListener(this.mEventLoggerListener);
/* 241 */     mqQrCodeEloActivity.setCancelListener(new QrCodeEloActivity.CancelListener()
/*     */         {
/*     */           public void onCancel() {
/* 244 */             PlugPagBase.this.abort();
/*     */           }
/*     */         });
/* 247 */     mqQrCodeEloActivity = null;
/*     */     
/* 249 */     return new LibSwitchReturn(LibSwitchReturn.LibSwitchValue.PSC_OK);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int requestAuthentication(final PlugPagAuthenticationListener listener) {
/* 295 */     int result = 0;
/* 296 */     Intent authIntent = null;
/*     */     
/* 298 */     if (this.mContext == null) {
/* 299 */       throw new PlugPagException("O contexto atual não pode ser nula");
/*     */     }
/*     */     
/* 302 */     result = checkRequirements(0);
/*     */     
/* 304 */     if (result == 0) {
/* 305 */       authIntent = getBaseIntent();
/* 306 */       authIntent.setClass(this.mContext, PlugPagActivity.class);
/* 307 */       authIntent.setAction("PLUGPAG_AUTHENTICATION");
/* 308 */       authIntent.putExtra("EXTRA_APP_IDENTIFICATION", getAppIdentification());
/* 309 */       authIntent.setFlags(1342177280);
/* 310 */       this.mContext.registerReceiver(new BroadcastReceiver()
/*     */           {
/*     */             public void onReceive(Context context, Intent intent) {
/* 313 */               if (intent.hasExtra("PLUGPAG_RESULT")) {
/* 314 */                 listener.onSuccess();
/*     */               } else {
/* 316 */                 listener.onError();
/*     */               } 
/* 318 */               context.unregisterReceiver(this);
/*     */             }
/*     */           },  new IntentFilter("br.com.uol.pagseguro.plugpag.AUTHENTICATION_RESULT"));
/* 321 */       this.mContext.startActivity(authIntent);
/*     */     } 
/*     */     
/* 324 */     return result;
/*     */   }
/*     */   
/*     */   protected String getStoragePath(@NonNull Context context) {
/* 328 */     if (context == null) {
/* 329 */       throw new PlugPagException("Referência do Context não pode ser nula");
/*     */     }
/*     */     
/* 332 */     LogFunctions.log(context.getFilesDir().getAbsolutePath());
/*     */     
/* 334 */     return context.getFilesDir().getAbsolutePath();
/*     */   }
/*     */   
/*     */   protected Intent getBaseIntent() {
/* 338 */     Intent intent = new Intent();
/* 339 */     intent.addFlags(1073741824);
/*     */     
/* 341 */     return intent;
/*     */   }
/*     */   
/*     */   public int requestDataInput(int dataType, int minSize, int maxSize, int timeoutMillisec) {
/* 345 */     int result = 0;
/* 346 */     Intent intent = null;
/*     */     
/* 348 */     if (this.mContext == null) {
/* 349 */       throw new PlugPagException("Referência da Activity não pode ser nula");
/*     */     }
/*     */     
/* 352 */     result = checkRequirements(0);
/*     */     
/* 354 */     if (result == 0) {
/* 355 */       intent = getBaseIntent();
/* 356 */       intent.setClass(this.mContext, PlugPagActivity.class);
/* 357 */       intent.setAction("PLUGPAG_DATAINPUT");
/* 358 */       intent.addFlags(268435456);
/* 359 */       intent.putExtra("EXTRA_DATAINPUT_TYPE", dataType);
/* 360 */       intent.putExtra("EXTRA_DATAINPUT_MIN_LENGTH", minSize);
/* 361 */       intent.putExtra("EXTRA_DATAINPUT_MAX_LENGTH", maxSize);
/* 362 */       intent.putExtra("EXTRA_DATAINPUT_TIMEOUT", timeoutMillisec);
/* 363 */       this.mContext.startActivity(intent);
/*     */     } 
/*     */     
/* 366 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int checkRequirements(int deviceType) {
/* 375 */     return PlugPagRequirementsCheck.checkRequirements(this.mContext, deviceType);
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
/*     */   public boolean updateVersion(String ppsVersion) {
/* 400 */     boolean retVal = false;
/*     */     
/* 402 */     SharedPreferences curPref = getSharedPreferences("PPS_CUR_VER");
/*     */     
/* 404 */     String curPpsVersion = curPref.getString("ppsVersion_cur", null);
/* 405 */     String curPpVersion = curPref.getString("ppVersion_cur", null);
/*     */     
/* 407 */     if (curPpsVersion == null || (ppsVersion != null && curPpsVersion.compareTo(ppsVersion) != 0)) {
/* 408 */       SharedPreferences.Editor edit = curPref.edit();
/*     */       
/* 410 */       edit.putString("ppsVersion_prev", curPpsVersion);
/* 411 */       edit.putString("ppVersion_prev", curPpVersion);
/*     */       
/* 413 */       edit.putString("ppsVersion_cur", ppsVersion);
/* 414 */       edit.putString("ppVersion_cur", PlugPag.getLibVersion());
/*     */       
/* 416 */       edit.apply();
/*     */       
/* 418 */       performUpdates();
/*     */       
/* 420 */       retVal = true;
/*     */     } 
/*     */     
/* 423 */     return retVal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void performUpdates() {
/* 431 */     if (getFormerPlugPagVersion() == null) {
/* 432 */       createMobTablePendings();
/*     */     }
/*     */   }
/*     */   
/*     */   private String getFormerPlugPagVersion() {
/* 437 */     SharedPreferences curPref = getSharedPreferences("PPS_CUR_VER");
/* 438 */     return curPref.getString("ppVersion_prev", null);
/*     */   }
/*     */   
/*     */   SharedPreferences getSharedPreferences(String preference) {
/* 442 */     return this.mContext.getSharedPreferences(preference, 0);
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
/*     */   
/*     */   void waitOperation() {
/* 457 */     latch = new CountDownLatch(1);
/*     */     
/*     */     try {
/* 460 */       latch.await();
/* 461 */     } catch (InterruptedException e) {
/* 462 */       LogFunctions.logError("CountDownLatch erro");
/*     */     } 
/*     */   }
/*     */   
/*     */   public native PlugPagReaderInfo getBcInfo();
/*     */   
/*     */   public native PlugPagCardInfo getCardInfo();
/*     */   
/*     */   public static native String getLibVersion();
/*     */   
/*     */   public static native String getApplicationCode();
/*     */   
/*     */   public native void setup(@NonNull Context paramContext);
/*     */   
/*     */   public native int initBTConnection(@NonNull PlugPagDevice paramPlugPagDevice);
/*     */   
/*     */   public native boolean isAuthenticated();
/*     */   
/*     */   public native void invalidateAuthentication();
/*     */   
/*     */   public native PlugPagUserData getUserData();
/*     */   
/*     */   public native PlugPagInitializationResult initializeAndActivatePinpad(PlugPagActivationData paramPlugPagActivationData);
/*     */   
/*     */   public native PlugPagInitializationResult deactivate(PlugPagActivationData paramPlugPagActivationData);
/*     */   
/*     */   public native void setNativeEventListener(PlugPagEventListener paramPlugPagEventListener);
/*     */   
/*     */   public native PlugPagTransactionResult doPayment(@NonNull PlugPagPaymentData paramPlugPagPaymentData);
/*     */   
/*     */   public native PlugPagTransactionResult voidPayment(@Nullable PlugPagVoidData paramPlugPagVoidData);
/*     */   
/*     */   public native PlugPagTransactionResult voidPayment();
/*     */   
/*     */   public native PlugPagTransactionResult voidQRCodePayment(@Nullable PlugPagVoidData paramPlugPagVoidData);
/*     */   
/*     */   public native String[] calculateInstallments(String paramString);
/*     */   
/*     */   public native PlugPagInstallmentResult calculateInstallments(String paramString, int paramInt);
/*     */   
/*     */   public native PlugPagTransactionResult getLastApprovedTransaction();
/*     */   
/*     */   public native int checkAbort();
/*     */   
/*     */   public native int checkDelay();
/*     */   
/*     */   public native int isVoidTransaction();
/*     */   
/*     */   public native PlugPagAbortResult abort();
/*     */   
/*     */   public native int setVersionName(@NonNull String paramString1, @NonNull String paramString2);
/*     */   
/*     */   public native void setConnectionChangeSupport(boolean paramBoolean);
/*     */   
/*     */   public native void setMockState(boolean paramBoolean);
/*     */   
/*     */   public native boolean getMockState();
/*     */   
/*     */   public native void setSelectMock(int paramInt);
/*     */   
/*     */   public native PlugPagMockResult getMockResult();
/*     */   
/*     */   public native void createMobTablePendings();
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */