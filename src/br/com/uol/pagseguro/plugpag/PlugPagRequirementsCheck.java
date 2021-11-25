package br.com.uol.pagseguro.plugpag;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public final class PlugPagRequirementsCheck {
  public static final int checkRequirements(@NonNull Context context, int type) {
    int result = 0;
    if (isDeviceRooted()) {
      result = -3001;
    } else if (type == 0 && 
      !checkPinpadPermissions(context)) {
      result = -3000;
    } else if (type == 1 && 
      !checkTerminalPermissions(context)) {
      result = -3000;
    } 
    return result;
  }
  
  public static final void throwExceptionIfNeeded(@NonNull Context context, int deviceType) {
    int result = 0;
    if (context == null)
      throw new PlugPagException("Referência do context não pode ser nula"); 
    result = checkRequirements(context, deviceType);
    if (result == -3000)
      throw new PlugPagException("Permissões não concedidas"); 
    if (result == -3001)
      throw new PlugPagException("Aplicativo não pode ter permissões de root"); 
  }
  
  public static final boolean checkPinpadPermissions(@NonNull Context context) {
    boolean permissionsCheck = false;
    if (context == null)
      throw new PlugPagException("Referência do context não pode ser nulo"); 
    permissionsCheck = (checkStoragePermissions(context) && checkInternetPermission(context) && checkBluetoothPermission(context) && checkPhoneStatePermission(context));
    return permissionsCheck;
  }
  
  public static final boolean checkTerminalPermissions(@NonNull Context context) {
    boolean permissionsCheck = false;
    if (context == null)
      throw new PlugPagException("Referência do context não pode ser nulo"); 
    permissionsCheck = (checkBluetoothPermission(context) && checkPhoneStatePermission(context));
    return permissionsCheck;
  }
  
  private static final boolean checkPermission(Context context, String permission) {
    return (ContextCompat.checkSelfPermission(context, permission) == 0);
  }
  
  private static final boolean checkStoragePermissions(Context context) {
    return (checkPermission(context, "android.permission.READ_EXTERNAL_STORAGE") && 
      checkPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE"));
  }
  
  private static final boolean checkInternetPermission(Context context) {
    return checkPermission(context, "android.permission.INTERNET");
  }
  
  private static final boolean checkBluetoothPermission(Context context) {
    return checkPermission(context, "android.permission.BLUETOOTH");
  }
  
  private static final boolean checkPhoneStatePermission(Context context) {
    return checkPermission(context, "android.permission.READ_PHONE_STATE");
  }
  
  private static final boolean checkLocationPermission(Context context) {
    return (checkPermission(context, "android.permission.ACCESS_COARSE_LOCATION") || 
      checkPermission(context, "android.permission.ACCESS_FINE_LOCATION"));
  }
  
  private static boolean isDeviceRooted() {
    return (checkRootMethod2() || checkRootMethod3() || checkRootMethod4());
  }
  
  private static boolean checkRootMethod1() {
    String buildTags = Build.TAGS;
    return (buildTags != null && buildTags.contains("test-keys"));
  }
  
  private static boolean checkRootMethod2() {
    String[] paths = { "/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su" };
    for (String path : paths) {
      if ((new File(path)).exists())
        return true; 
    } 
    return false;
  }
  
  private static boolean checkRootMethod3() {
    Process process = null;
    try {
      process = Runtime.getRuntime().exec(new String[] { "/system/xbin/which", "su" });
      BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
      if (in.readLine() != null)
        return true; 
      return false;
    } catch (Throwable t) {
      return false;
    } finally {
      if (process != null)
        process.destroy(); 
    } 
  }
  
  private static boolean checkRootMethod4() {
    for (String pathDir : System.getenv("PATH").split(":")) {
      if ((new File(pathDir, "su")).exists())
        return true; 
    } 
    return false;
  }
}
