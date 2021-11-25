package br.com.uol.pagseguro.plugpag;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
import br.com.uol.pagseguro.util.LogFunctions;
import br.com.uol.pagseguro.util.Pair;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DeviceInfo {
  static final String UNKNOW_CONNECTION = "UNKNOWN CONNECTION";
  
  private static final int DEVICE_ID_LENGTH = 32;
  
  private static final String[] LOCATION_PROVIDERS = new String[] { "gps", "network", "passive" };
  
  private static final long LOCATION_MAX_ELAPSED_TIME = 600000000L;
  
  private static final int LOCATION_MIN_ACCURACY = 0;
  
  private static final String WITHOUT_READ_PHONE_PERMISSION = "MISSING_PERMISSION";
  
  private static final String UNKNOWN_CARRIER_NAME = "UNKNOWN";
  
  public enum Terminal {
    A930("PAX_A930_BGW", "A930-0AW-RD5-06EB"),
    A930C("PAX_A930_BGWC", "A930-0AW-RD5-02EA"),
    A930B("PAX_A930_BGW", "A930-0AW-RD5-04EB"),
    A930CB("PAX_A930_BGWC", "A930-0AW-RD5-07EB"),
    A50("PAX_A50_BGWC", "A50-0AW-RC5-01EB"),
    A50B("PAX_A50_BGWC", "A50-0AW-RD5-02EB"),
    A50C("PAX_A50_BGWC", "A50-0AW-RD5-04EB"),
    SK800("PAX_SK800", "");
    
    private String model;
    
    private String partNumber;
    
    Terminal(String model, String partNumber) {
      this.model = model;
      this.partNumber = partNumber;
    }
    
    public String getModel() {
      return this.model;
    }
    
    public String getPartNumber() {
      return this.partNumber;
    }
  }
  
  private Context mContext = null;
  
  public DeviceInfo(@NonNull Context context) {
    if (context == null)
      throw new PlugPagException("Referência do Context não pode ser nula"); 
    this.mContext = context;
  }
  
  private boolean hasPermission(String permission) {
    boolean hasPermission = false;
    if (this.mContext != null)
      hasPermission = (ContextCompat.checkSelfPermission(this.mContext, permission) == 0); 
    return hasPermission;
  }
  
  public String getModel() {
    return getModelByPN(getSystemProperty("ro.fac.cfg.PN"));
  }
  
  private String getModelByPN(String partNumber) {
    for (Terminal ter : Terminal.values()) {
      if (ter.partNumber.equals(partNumber))
        return ter.model; 
    } 
    return Terminal.A930.model;
  }
  
  private String getSystemProperty(String property) {
    String result;
    try {
      Class<?> systemProperties = Class.forName("android.os.SystemProperties");
      Class[] paramTypes = { String.class };
      Method get = systemProperties.getMethod("get", (Class[])Arrays.<Class<?>[]>copyOf((Class<?>[][])paramTypes, paramTypes.length));
      Object[] params = { property };
      result = (String)get.invoke(systemProperties, Arrays.copyOf(params, params.length));
    } catch (Exception ex) {
      result = "";
    } 
    return result;
  }
  
  @SuppressLint({"MissingPermission"})
  public String getImei() {
    String imei = null;
    TelephonyManager tm = null;
    try {
      if (Build.VERSION.SDK_INT <= 28) {
        if (this.mContext != null && hasPermission("android.permission.READ_PHONE_STATE")) {
          tm = (TelephonyManager)this.mContext.getSystemService("phone");
          imei = tm.getDeviceId();
        } 
      } else {
        imei = "";
      } 
      if (imei == null || imei.length() <= 0)
        imei = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id"); 
    } catch (Exception e) {
      imei = "";
    } 
    return imei;
  }
  
  @SuppressLint({"MissingPermission"})
  public Pair<Double, Double> getCoordinates() {
    Pair<Double, Double> coordinates = null;
    LocationManager lm = null;
    List<Location> locations = null;
    Location location = null;
    Location bestLocation = null;
    if (this.mContext != null && (
      hasPermission("android.permission.ACCESS_COARSE_LOCATION") || 
      hasPermission("android.permission.ACCESS_FINE_LOCATION"))) {
      lm = (LocationManager)this.mContext.getSystemService("location");
      locations = new ArrayList<>();
      for (String provider : LOCATION_PROVIDERS) {
        location = lm.getLastKnownLocation(provider);
        if (isLocationAcceptable(location))
          locations.add(location); 
      } 
      if (location != null && locations.size() > 0)
        for (Location currentLocation : locations)
          bestLocation = chooseBetterLocation(bestLocation, currentLocation);  
      if (bestLocation != null)
        coordinates = new Pair(Double.valueOf(bestLocation.getLatitude()), Double.valueOf(bestLocation.getLongitude())); 
    } 
    return coordinates;
  }
  
  private boolean isLocationAcceptable(@Nullable Location location) {
    boolean acceptable = false;
    if (location != null) {
      acceptable = true;
      if (Build.VERSION.SDK_INT >= 17 && location
        
        .getElapsedRealtimeNanos() >= System.nanoTime() - 600000000L)
        acceptable = true; 
    } 
    return acceptable;
  }
  
  private Location chooseBetterLocation(@Nullable Location lhs, @Nullable Location rhs) {
    Location better = null;
    if (lhs == null) {
      better = rhs;
    } else if (rhs == null) {
      better = lhs;
    } else if (lhs.getAccuracy() >= rhs.getAccuracy()) {
      better = lhs;
    } else if (lhs.getAccuracy() < rhs.getAccuracy()) {
      better = rhs;
    } 
    return better;
  }
  
  public String getDeviceModel() {
    return String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL });
  }
  
  public String getDeviceId() {
    String imei = MaskValueIDeviceEnum.ESN_IMEI.getMask(getImei());
    String deviceHardware = MaskValueIDeviceEnum.HARDWARE.getMask(Build.HARDWARE);
    String deviceModel = MaskValueIDeviceEnum.MODEL.getMask(Build.MODEL);
    String macWifi = MaskValueIDeviceEnum.WIFI_MAC_ADDRESS.getMask(getWifiMacAddress(this.mContext));
    String macBluetooth = MaskValueIDeviceEnum.BLUETOOTH_MAC_ADDRESS.getMask(getBluetoothMacAddress());
    long deviceMix = (deviceHardware.hashCode() & deviceModel.hashCode());
    UUID deviceUuid = new UUID(imei.hashCode() << 32L | deviceMix, macWifi.hashCode() << 32L | macBluetooth.hashCode());
    String deviceID = deviceUuid.toString().replace("-", "");
    return deviceID;
  }
  
  public float getBatteryLevel() {
    Intent batteryIntent = this.mContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    int level = batteryIntent.getIntExtra("level", -1);
    int scale = batteryIntent.getIntExtra("scale", -1);
    if (level == -1 || scale == -1)
      return 50.0F; 
    return level / scale * 100.0F;
  }
  
  public String getSimSerialNumber() {
    try {
      if (Build.VERSION.SDK_INT <= 28) {
        TelephonyManager tm = (TelephonyManager)this.mContext.getSystemService("phone");
        if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.READ_PHONE_STATE") != 0)
          return "MISSING_PERMISSION"; 
        return tm.getSimSerialNumber();
      } 
      return "";
    } catch (Exception e) {
      return "";
    } 
  }
  
  public String getOperatorName() {
    try {
      if (Build.VERSION.SDK_INT <= 28) {
        TelephonyManager tm = (TelephonyManager)this.mContext.getSystemService("phone");
        if (ActivityCompat.checkSelfPermission(this.mContext, "android.permission.READ_PHONE_STATE") != 0)
          return "MISSING_PERMISSION"; 
        String operatorName = tm.getSimOperatorName();
        LogFunctions.log("DEVICEINFO: operatorName: " + operatorName);
        if (TextUtils.isEmpty(operatorName))
          return "UNKNOWN"; 
        return operatorName;
      } 
      return "";
    } catch (Exception e) {
      return "";
    } 
  }
  
  public String getOs() {
    return "ANDROID";
  }
  
  public String getOsVersion() {
    return Build.VERSION.RELEASE;
  }
  
  @SuppressLint({"MissingPermission"})
  public int getModemState() {
    if (this.mContext == null || !hasPermission("android.permission.READ_PHONE_STATE"))
      return TransactionMetric.ModemConnState.UNKNOWN.getValue(); 
    TelephonyManager tm = (TelephonyManager)this.mContext.getSystemService("phone");
    int simState = tm.getSimState();
    LogFunctions.log("DEVICEINFO: simState: " + simState);
    switch (simState) {
      case 0:
        return TransactionMetric.ModemConnState.UNKNOWN.getValue();
      case 1:
        return TransactionMetric.ModemConnState.NO_SIM.getValue();
      case 2:
      case 3:
        return TransactionMetric.ModemConnState.SIM_PIN.getValue();
      case 4:
      case 7:
      case 9:
        return TransactionMetric.ModemConnState.SIM_BLOCKED.getValue();
      case 6:
        return TransactionMetric.ModemConnState.SIM_NOT_READY.getValue();
      case 5:
        break;
      default:
        return TransactionMetric.ModemConnState.SIM_ERROR.getValue();
    } 
    int dataState = tm.getDataState();
    LogFunctions.log("DEVICEINFO: dataState: " + dataState);
    if (dataState != 2)
      return TransactionMetric.ModemConnState.NO_PPP_CONTEXT.getValue(); 
    int dataActivity = tm.getDataActivity();
    LogFunctions.log("DEVICEINFO: dataActivity: " + dataActivity);
    if (dataActivity == 4)
      return TransactionMetric.ModemConnState.DORMANT.getValue(); 
    return TransactionMetric.ModemConnState.READY.getValue();
  }
  
  public String getTypeNetwork() {
    TelephonyManager manager = (TelephonyManager)this.mContext.getSystemService("phone");
    switch (manager.getNetworkType()) {
      case 1:
      case 2:
      case 4:
      case 7:
      case 11:
        return NetworkPreference.TEC_2G.getName();
      case 3:
      case 5:
      case 6:
      case 8:
      case 9:
      case 10:
      case 12:
      case 14:
      case 15:
        return NetworkPreference.TEC_3G.getName();
      case 13:
        return NetworkPreference.TEC_4G.getName();
    } 
    return NetworkPreference.UNKNOWN_TEC.getName();
  }
  
  public static String getWifiMacAddress(Context context) {
    WifiManager wifiManager = (WifiManager)context.getSystemService("wifi");
    if (wifiManager == null)
      return null; 
    WifiInfo wInfo = wifiManager.getConnectionInfo();
    return wInfo.getMacAddress();
  }
  
  public static String getBluetoothMacAddress() {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (Build.VERSION.SDK_INT < 14 || mBluetoothAdapter == null)
      return null; 
    return mBluetoothAdapter.getAddress();
  }
  
  public enum MaskValueIDeviceEnum {
    ESN_IMEI(15, "0"),
    MODEL(1, " "),
    HARDWARE(1, " "),
    WIFI_MAC_ADDRESS(1, "00-00-00-00-00-00"),
    BLUETOOTH_MAC_ADDRESS(1, "00-00-00-00-00-00");
    
    private int maskSize;
    
    private String charType;
    
    MaskValueIDeviceEnum(int maskSize, String charType) {
      this.maskSize = maskSize;
      this.charType = charType;
    }
    
    public String getMask(String value) {
      StringBuilder sb = new StringBuilder();
      if (value == null) {
        for (int i = 0; i < this.maskSize; i++)
          sb.append(this.charType); 
        return sb.toString();
      } 
      return value;
    }
  }
}
