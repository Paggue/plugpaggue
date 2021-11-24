/*     */ package br.com.uol.pagseguro.plugpag;
/*     */ 
/*     */ import android.bluetooth.BluetoothAdapter;
/*     */ import android.bluetooth.BluetoothDevice;
/*     */ import android.support.annotation.NonNull;
/*     */ import android.text.TextUtils;
/*     */ import br.com.uol.pagseguro.plugpag.exception.PlugPagDeviceInfoException;
/*     */ import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
/*     */ import java.util.Set;
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
/*     */ public class PlugPagDevice
/*     */ {
/*     */   public static final int TYPE_UNDEFINED = -1;
/*     */   public static final int TYPE_PINPAD = 0;
/*     */   public static final int TYPE_TERMINAL = 1;
/*     */   
/*     */   static {
/*  28 */     PlugPagLibraryLoader.loadNativeLibraries();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   private String mIdentification = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   private int mType = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   private String mActivationCode = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   private String mSerialNumber = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean mLess = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PlugPagDevice(@NonNull String identification) {
/*  66 */     this(identification, null, null);
/*     */   }
/*     */   
/*     */   public PlugPagDevice(@NonNull String identification, String activationCode, String s920SerialNumber) {
/*  70 */     this(identification, activationCode, s920SerialNumber, false);
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
/*     */   public PlugPagDevice(@NonNull String identification, String activationCode, String s920SerialNumber, boolean less) {
/*  82 */     if (TextUtils.isEmpty(identification)) {
/*  83 */       throw new PlugPagDeviceInfoException("Identificação do aparelho não pode ser nulo ou vazio");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     this.mIdentification = identification;
/*  90 */     this.mType = evaluateType(identification);
/*  91 */     this.mSerialNumber = s920SerialNumber;
/*  92 */     this.mActivationCode = activationCode;
/*  93 */     this.mLess = less;
/*     */ 
/*     */     
/*  96 */     if (this.mType == -1) {
/*  97 */       this.mType = evaluateTypeByMacAddress(identification);
/*     */     }
/*     */     
/* 100 */     if (this.mType == -1) {
/* 101 */       throw new PlugPagException("Identificação do aparelho inválida: não é possível definir o tipo do aparelho");
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
/*     */   public String getIdentification() {
/* 118 */     return this.mIdentification;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 127 */     return this.mType;
/*     */   }
/*     */   
/*     */   public String getActivationCode() {
/* 131 */     return this.mActivationCode;
/*     */   }
/*     */   
/*     */   public String getSerialNumber() {
/* 135 */     return this.mSerialNumber;
/*     */   }
/*     */   
/*     */   public boolean isLess() {
/* 139 */     return this.mLess;
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
/*     */   private int evaluateTypeByMacAddress(@NonNull String macAddress) {
/* 161 */     int type = -1;
/* 162 */     BluetoothAdapter adapter = null;
/* 163 */     Set<BluetoothDevice> bondedDevices = null;
/*     */     
/* 165 */     adapter = BluetoothAdapter.getDefaultAdapter();
/* 166 */     bondedDevices = adapter.getBondedDevices();
/*     */     
/* 168 */     if (bondedDevices != null) {
/* 169 */       for (BluetoothDevice device : bondedDevices) {
/* 170 */         if (macAddress.equals(device.getAddress())) {
/* 171 */           type = evaluateType(device.getName());
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 177 */     return type;
/*     */   }
/*     */   
/*     */   public native int evaluateType(@NonNull String paramString);
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagDevice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */