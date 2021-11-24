/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.os.Build;
/*     */ import android.support.annotation.NonNull;
/*     */ import android.support.v4.content.ContextCompat;
/*     */ import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.InputStreamReader;
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
/*     */ public final class PlugPagRequirementsCheck
/*     */ {
/*     */   public static final int checkRequirements(@NonNull Context context, int type) {
/*  31 */     int result = 0;
/*     */     
/*  33 */     if (isDeviceRooted()) {
/*  34 */       result = -3001;
/*     */     }
/*  36 */     else if (type == 0 && 
/*  37 */       !checkPinpadPermissions(context)) {
/*  38 */       result = -3000;
/*  39 */     } else if (type == 1 && 
/*  40 */       !checkTerminalPermissions(context)) {
/*  41 */       result = -3000;
/*     */     } 
/*     */ 
/*     */     
/*  45 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final void throwExceptionIfNeeded(@NonNull Context context, int deviceType) {
/*  55 */     int result = 0;
/*     */     
/*  57 */     if (context == null) {
/*  58 */       throw new PlugPagException("Referência do context não pode ser nula");
/*     */     }
/*     */     
/*  61 */     result = checkRequirements(context, deviceType);
/*     */     
/*  63 */     if (result == -3000)
/*  64 */       throw new PlugPagException("Permissões não concedidas"); 
/*  65 */     if (result == -3001) {
/*  66 */       throw new PlugPagException("Aplicativo não pode ter permissões de root");
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
/*     */   public static final boolean checkPinpadPermissions(@NonNull Context context) {
/*  81 */     boolean permissionsCheck = false;
/*     */     
/*  83 */     if (context == null) {
/*  84 */       throw new PlugPagException("Referência do context não pode ser nulo");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     permissionsCheck = (checkStoragePermissions(context) && checkInternetPermission(context) && checkBluetoothPermission(context) && checkPhoneStatePermission(context));
/*     */ 
/*     */     
/*  93 */     return permissionsCheck;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean checkTerminalPermissions(@NonNull Context context) {
/* 103 */     boolean permissionsCheck = false;
/*     */     
/* 105 */     if (context == null) {
/* 106 */       throw new PlugPagException("Referência do context não pode ser nulo");
/*     */     }
/*     */ 
/*     */     
/* 110 */     permissionsCheck = (checkBluetoothPermission(context) && checkPhoneStatePermission(context));
/*     */     
/* 112 */     return permissionsCheck;
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
/*     */   private static final boolean checkPermission(Context context, String permission) {
/* 127 */     return (ContextCompat.checkSelfPermission(context, permission) == 0);
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
/*     */   private static final boolean checkStoragePermissions(Context context) {
/* 142 */     return (checkPermission(context, "android.permission.READ_EXTERNAL_STORAGE") && 
/* 143 */       checkPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE"));
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
/*     */   private static final boolean checkInternetPermission(Context context) {
/* 157 */     return checkPermission(context, "android.permission.INTERNET");
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
/*     */   private static final boolean checkBluetoothPermission(Context context) {
/* 171 */     return checkPermission(context, "android.permission.BLUETOOTH");
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
/*     */   private static final boolean checkPhoneStatePermission(Context context) {
/* 185 */     return checkPermission(context, "android.permission.READ_PHONE_STATE");
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
/*     */   private static final boolean checkLocationPermission(Context context) {
/* 199 */     return (checkPermission(context, "android.permission.ACCESS_COARSE_LOCATION") || 
/* 200 */       checkPermission(context, "android.permission.ACCESS_FINE_LOCATION"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isDeviceRooted() {
/* 208 */     return (checkRootMethod2() || checkRootMethod3() || checkRootMethod4());
/*     */   }
/*     */   
/*     */   private static boolean checkRootMethod1() {
/* 212 */     String buildTags = Build.TAGS;
/* 213 */     return (buildTags != null && buildTags.contains("test-keys"));
/*     */   }
/*     */   
/*     */   private static boolean checkRootMethod2() {
/* 217 */     String[] paths = { "/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su" };
/*     */ 
/*     */     
/* 220 */     for (String path : paths) {
/* 221 */       if ((new File(path)).exists()) return true; 
/*     */     } 
/* 223 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean checkRootMethod3() {
/* 227 */     Process process = null;
/*     */     try {
/* 229 */       process = Runtime.getRuntime().exec(new String[] { "/system/xbin/which", "su" });
/* 230 */       BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
/* 231 */       if (in.readLine() != null) return true; 
/* 232 */       return false;
/* 233 */     } catch (Throwable t) {
/* 234 */       return false;
/*     */     } finally {
/* 236 */       if (process != null) process.destroy(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean checkRootMethod4() {
/* 241 */     for (String pathDir : System.getenv("PATH").split(":")) {
/* 242 */       if ((new File(pathDir, "su")).exists()) {
/* 243 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 247 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagRequirementsCheck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */