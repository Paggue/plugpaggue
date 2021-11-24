/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import android.annotation.SuppressLint;
/*     */ import android.bluetooth.BluetoothAdapter;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.content.IntentFilter;
/*     */ import android.location.Location;
/*     */ import android.location.LocationManager;
/*     */ import android.net.wifi.WifiInfo;
/*     */ import android.net.wifi.WifiManager;
/*     */ import android.os.Build;
/*     */ import android.provider.Settings;
/*     */ import android.support.annotation.NonNull;
/*     */ import android.support.annotation.Nullable;
/*     */ import android.support.v4.app.ActivityCompat;
/*     */ import android.support.v4.content.ContextCompat;
/*     */ import android.telephony.TelephonyManager;
/*     */ import android.text.TextUtils;
/*     */ import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
/*     */ import br.com.uol.pagseguro.util.LogFunctions;
/*     */ import br.com.uol.pagseguro.util.Pair;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
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
/*     */ public class DeviceInfo
/*     */ {
/*     */   static final String UNKNOW_CONNECTION = "UNKNOWN CONNECTION";
/*     */   private static final int DEVICE_ID_LENGTH = 32;
/*  67 */   private static final String[] LOCATION_PROVIDERS = new String[] { "gps", "network", "passive" };
/*     */   private static final long LOCATION_MAX_ELAPSED_TIME = 600000000L;
/*     */   private static final int LOCATION_MIN_ACCURACY = 0;
/*     */   private static final String WITHOUT_READ_PHONE_PERMISSION = "MISSING_PERMISSION";
/*     */   private static final String UNKNOWN_CARRIER_NAME = "UNKNOWN";
/*     */   
/*     */   public enum Terminal {
/*  74 */     A930("PAX_A930_BGW", "A930-0AW-RD5-06EB"),
/*  75 */     A930C("PAX_A930_BGWC", "A930-0AW-RD5-02EA"),
/*  76 */     A930B("PAX_A930_BGW", "A930-0AW-RD5-04EB"),
/*  77 */     A930CB("PAX_A930_BGWC", "A930-0AW-RD5-07EB"),
/*  78 */     A50("PAX_A50_BGWC", "A50-0AW-RC5-01EB"),
/*  79 */     A50B("PAX_A50_BGWC", "A50-0AW-RD5-02EB"),
/*  80 */     A50C("PAX_A50_BGWC", "A50-0AW-RD5-04EB"),
/*  81 */     SK800("PAX_SK800", "");
/*     */     
/*     */     private String model;
/*     */     
/*     */     private String partNumber;
/*     */     
/*     */     Terminal(String model, String partNumber) {
/*  88 */       this.model = model;
/*  89 */       this.partNumber = partNumber;
/*     */     }
/*     */     
/*     */     public String getModel() {
/*  93 */       return this.model;
/*     */     }
/*     */     
/*     */     public String getPartNumber() {
/*  97 */       return this.partNumber;
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
/* 125 */   private Context mContext = null;
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
/*     */   public DeviceInfo(@NonNull Context context) {
/* 138 */     if (context == null) {
/* 139 */       throw new PlugPagException("Referência do Context não pode ser nula");
/*     */     }
/*     */     
/* 142 */     this.mContext = context;
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
/*     */   private boolean hasPermission(String permission) {
/* 156 */     boolean hasPermission = false;
/*     */     
/* 158 */     if (this.mContext != null) {
/* 159 */       hasPermission = (ContextCompat.checkSelfPermission(this.mContext, permission) == 0);
/*     */     }
/*     */     
/* 162 */     return hasPermission;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getModel() {
/* 167 */     return getModelByPN(getSystemProperty("ro.fac.cfg.PN"));
/*     */   }
/*     */ 
/*     */   
/*     */   private String getModelByPN(String partNumber) {
/* 172 */     for (Terminal ter : Terminal.values()) {
/*     */       
/* 174 */       if (ter.partNumber.equals(partNumber)) {
/* 175 */         return ter.model;
/*     */       }
/*     */     } 
/*     */     
/* 179 */     return Terminal.A930.model;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String getSystemProperty(String property) {
/*     */     String result;
/*     */     try {
/* 187 */       Class<?> systemProperties = Class.forName("android.os.SystemProperties");
/* 188 */       Class[] paramTypes = { String.class };
/* 189 */       Method get = systemProperties.getMethod("get", (Class[])Arrays.<Class<?>[]>copyOf((Class<?>[][])paramTypes, paramTypes.length));
/* 190 */       Object[] params = { property };
/*     */       
/* 192 */       result = (String)get.invoke(systemProperties, Arrays.copyOf(params, params.length));
/*     */     }
/* 194 */     catch (Exception ex) {
/* 195 */       result = "";
/*     */     } 
/*     */ 
/*     */     
/* 199 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SuppressLint({"MissingPermission"})
/*     */   public String getImei() {
/* 208 */     String imei = null;
/* 209 */     TelephonyManager tm = null;
/*     */     
/*     */     try {
/* 212 */       if (Build.VERSION.SDK_INT <= 28) {
/* 213 */         if (this.mContext != null && hasPermission("android.permission.READ_PHONE_STATE")) {
/* 214 */           tm = (TelephonyManager)this.mContext.getSystemService("phone");
/* 215 */           imei = tm.getDeviceId();
/*     */         } 
/*     */       } else {
/* 218 */         imei = "";
/*     */       } 
/*     */ 
/*     */       
/* 222 */       if (imei == null || imei.length() <= 0) {
/* 223 */         imei = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
/*     */       }
/* 225 */     } catch (Exception e) {
/* 226 */       imei = "";
/*     */     } 
/*     */     
/* 229 */     return imei;
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
/*     */   @SuppressLint({"MissingPermission"})
/*     */   public Pair<Double, Double> getCoordinates() {
/* 243 */     Pair<Double, Double> coordinates = null;
/* 244 */     LocationManager lm = null;
/* 245 */     List<Location> locations = null;
/* 246 */     Location location = null;
/* 247 */     Location bestLocation = null;
/*     */     
/* 249 */     if (this.mContext != null && (
/* 250 */       hasPermission("android.permission.ACCESS_COARSE_LOCATION") || 
/* 251 */       hasPermission("android.permission.ACCESS_FINE_LOCATION"))) {
/* 252 */       lm = (LocationManager)this.mContext.getSystemService("location");
/* 253 */       locations = new ArrayList<>();
/*     */ 
/*     */       
/* 256 */       for (String provider : LOCATION_PROVIDERS) {
/* 257 */         location = lm.getLastKnownLocation(provider);
/*     */         
/* 259 */         if (isLocationAcceptable(location)) {
/* 260 */           locations.add(location);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 265 */       if (location != null && locations.size() > 0) {
/* 266 */         for (Location currentLocation : locations) {
/* 267 */           bestLocation = chooseBetterLocation(bestLocation, currentLocation);
/*     */         }
/*     */       }
/*     */       
/* 271 */       if (bestLocation != null) {
/* 272 */         coordinates = new Pair(Double.valueOf(bestLocation.getLatitude()), Double.valueOf(bestLocation.getLongitude()));
/*     */       }
/*     */     } 
/*     */     
/* 276 */     return coordinates;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isLocationAcceptable(@Nullable Location location) {
/* 286 */     boolean acceptable = false;
/*     */     
/* 288 */     if (location != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 293 */       acceptable = true;
/*     */ 
/*     */ 
/*     */       
/* 297 */       if (Build.VERSION.SDK_INT >= 17 && location
/*     */         
/* 299 */         .getElapsedRealtimeNanos() >= System.nanoTime() - 600000000L) {
/* 300 */         acceptable = true;
/*     */       }
/*     */     } 
/*     */     
/* 304 */     return acceptable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Location chooseBetterLocation(@Nullable Location lhs, @Nullable Location rhs) {
/* 315 */     Location better = null;
/*     */     
/* 317 */     if (lhs == null) {
/* 318 */       better = rhs;
/* 319 */     } else if (rhs == null) {
/* 320 */       better = lhs;
/* 321 */     } else if (lhs.getAccuracy() >= rhs.getAccuracy()) {
/* 322 */       better = lhs;
/* 323 */     } else if (lhs.getAccuracy() < rhs.getAccuracy()) {
/* 324 */       better = rhs;
/*     */     } 
/*     */     
/* 327 */     return better;
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
/*     */   public String getDeviceModel() {
/* 340 */     return String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL });
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
/*     */   public String getDeviceId() {
/* 359 */     String imei = MaskValueIDeviceEnum.ESN_IMEI.getMask(getImei());
/* 360 */     String deviceHardware = MaskValueIDeviceEnum.HARDWARE.getMask(Build.HARDWARE);
/* 361 */     String deviceModel = MaskValueIDeviceEnum.MODEL.getMask(Build.MODEL);
/* 362 */     String macWifi = MaskValueIDeviceEnum.WIFI_MAC_ADDRESS.getMask(getWifiMacAddress(this.mContext));
/* 363 */     String macBluetooth = MaskValueIDeviceEnum.BLUETOOTH_MAC_ADDRESS.getMask(getBluetoothMacAddress());
/*     */     
/* 365 */     long deviceMix = (deviceHardware.hashCode() & deviceModel.hashCode());
/*     */ 
/*     */     
/* 368 */     UUID deviceUuid = new UUID(imei.hashCode() << 32L | deviceMix, macWifi.hashCode() << 32L | macBluetooth.hashCode());
/*     */     
/* 370 */     String deviceID = deviceUuid.toString().replace("-", "");
/*     */     
/* 372 */     return deviceID;
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
/*     */   public float getBatteryLevel() {
/* 385 */     Intent batteryIntent = this.mContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
/* 386 */     int level = batteryIntent.getIntExtra("level", -1);
/* 387 */     int scale = batteryIntent.getIntExtra("scale", -1);
/*     */     
/* 389 */     if (level == -1 || scale == -1) {
/* 390 */       return 50.0F;
/*     */     }
/*     */     
/* 393 */     return level / scale * 100.0F;
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
/*     */   public String getSimSerialNumber() {
/*     */     try {
/* 407 */       if (Build.VERSION.SDK_INT <= 28) {
/* 408 */         TelephonyManager tm = (TelephonyManager)this.mContext.getSystemService("phone");
/* 409 */         if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.READ_PHONE_STATE") != 0) {
/* 410 */           return "MISSING_PERMISSION";
/*     */         }
/* 412 */         return tm.getSimSerialNumber();
/*     */       } 
/* 414 */       return "";
/*     */     }
/* 416 */     catch (Exception e) {
/* 417 */       return "";
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
/*     */   public String getOperatorName() {
/*     */     try {
/* 433 */       if (Build.VERSION.SDK_INT <= 28) {
/* 434 */         TelephonyManager tm = (TelephonyManager)this.mContext.getSystemService("phone");
/* 435 */         if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.READ_PHONE_STATE") != 0) {
/* 436 */           return "MISSING_PERMISSION";
/*     */         }
/*     */         
/* 439 */         String operatorName = tm.getSimOperatorName();
/* 440 */         LogFunctions.log("DEVICEINFO: operatorName: " + operatorName);
/*     */         
/* 442 */         if (TextUtils.isEmpty(operatorName)) {
/* 443 */           return "UNKNOWN";
/*     */         }
/* 445 */         return operatorName;
/*     */       } 
/* 447 */       return "";
/*     */     }
/* 449 */     catch (Exception e) {
/* 450 */       return "";
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
/*     */   public String getOs() {
/* 465 */     return "ANDROID";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOsVersion() {
/* 474 */     return Build.VERSION.RELEASE;
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
/*     */   @SuppressLint({"MissingPermission"})
/*     */   public int getModemState() {
/* 490 */     if (this.mContext == null || !hasPermission("android.permission.READ_PHONE_STATE")) {
/* 491 */       return TransactionMetric.ModemConnState.UNKNOWN.getValue();
/*     */     }
/* 493 */     TelephonyManager tm = (TelephonyManager)this.mContext.getSystemService("phone");
/*     */     
/* 495 */     int simState = tm.getSimState();
/* 496 */     LogFunctions.log("DEVICEINFO: simState: " + simState);
/* 497 */     switch (simState) {
/*     */       case 0:
/* 499 */         return TransactionMetric.ModemConnState.UNKNOWN.getValue();
/*     */       case 1:
/* 501 */         return TransactionMetric.ModemConnState.NO_SIM.getValue();
/*     */       case 2:
/*     */       case 3:
/* 504 */         return TransactionMetric.ModemConnState.SIM_PIN.getValue();
/*     */       case 4:
/*     */       case 7:
/*     */       case 9:
/* 508 */         return TransactionMetric.ModemConnState.SIM_BLOCKED.getValue();
/*     */       case 6:
/* 510 */         return TransactionMetric.ModemConnState.SIM_NOT_READY.getValue();
/*     */       case 5:
/*     */         break;
/*     */       default:
/* 514 */         return TransactionMetric.ModemConnState.SIM_ERROR.getValue();
/*     */     } 
/*     */     
/* 517 */     int dataState = tm.getDataState();
/* 518 */     LogFunctions.log("DEVICEINFO: dataState: " + dataState);
/* 519 */     if (dataState != 2) {
/* 520 */       return TransactionMetric.ModemConnState.NO_PPP_CONTEXT.getValue();
/*     */     }
/* 522 */     int dataActivity = tm.getDataActivity();
/* 523 */     LogFunctions.log("DEVICEINFO: dataActivity: " + dataActivity);
/* 524 */     if (dataActivity == 4) {
/* 525 */       return TransactionMetric.ModemConnState.DORMANT.getValue();
/*     */     }
/* 527 */     return TransactionMetric.ModemConnState.READY.getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypeNetwork() {
/* 535 */     TelephonyManager manager = (TelephonyManager)this.mContext.getSystemService("phone");
/*     */     
/* 537 */     switch (manager.getNetworkType()) {
/*     */       case 1:
/*     */       case 2:
/*     */       case 4:
/*     */       case 7:
/*     */       case 11:
/* 543 */         return NetworkPreference.TEC_2G.getName();
/*     */       case 3:
/*     */       case 5:
/*     */       case 6:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/*     */       case 12:
/*     */       case 14:
/*     */       case 15:
/* 553 */         return NetworkPreference.TEC_3G.getName();
/*     */       case 13:
/* 555 */         return NetworkPreference.TEC_4G.getName();
/*     */     } 
/* 557 */     return NetworkPreference.UNKNOWN_TEC.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getWifiMacAddress(Context context) {
/* 567 */     WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
/*     */     
/* 569 */     if (wifiManager == null) {
/* 570 */       return null;
/*     */     }
/*     */     
/* 573 */     WifiInfo wInfo = wifiManager.getConnectionInfo();
/* 574 */     return wInfo.getMacAddress();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getBluetoothMacAddress() {
/* 583 */     BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
/*     */     
/* 585 */     if (Build.VERSION.SDK_INT < 14 || mBluetoothAdapter == null) {
/* 586 */       return null;
/*     */     }
/*     */     
/* 589 */     return mBluetoothAdapter.getAddress();
/*     */   }
/*     */   
/*     */   public enum MaskValueIDeviceEnum {
/* 593 */     ESN_IMEI(15, "0"),
/* 594 */     MODEL(1, " "),
/* 595 */     HARDWARE(1, " "),
/* 596 */     WIFI_MAC_ADDRESS(1, "00-00-00-00-00-00"),
/* 597 */     BLUETOOTH_MAC_ADDRESS(1, "00-00-00-00-00-00");
/*     */     private int maskSize;
/*     */     private String charType;
/*     */     
/*     */     MaskValueIDeviceEnum(int maskSize, String charType) {
/* 602 */       this.maskSize = maskSize;
/* 603 */       this.charType = charType;
/*     */     }
/*     */     
/*     */     public String getMask(String value) {
/* 607 */       StringBuilder sb = new StringBuilder();
/*     */       
/* 609 */       if (value == null) {
/* 610 */         for (int i = 0; i < this.maskSize; i++) {
/* 611 */           sb.append(this.charType);
/*     */         }
/*     */         
/* 614 */         return sb.toString();
/*     */       } 
/* 616 */       return value;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/DeviceInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */