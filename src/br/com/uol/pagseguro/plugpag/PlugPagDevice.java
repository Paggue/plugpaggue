package br.com.uol.pagseguro.plugpag;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import br.com.uol.pagseguro.plugpag.exception.PlugPagDeviceInfoException;
import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
import java.util.Set;

public class PlugPagDevice {
  public static final int TYPE_UNDEFINED = -1;
  
  public static final int TYPE_PINPAD = 0;
  
  public static final int TYPE_TERMINAL = 1;
  
  static {
    PlugPagLibraryLoader.loadNativeLibraries();
  }
  
  private String mIdentification = null;
  
  private int mType = -1;
  
  private String mActivationCode = null;
  
  private String mSerialNumber = null;
  
  private boolean mLess = false;
  
  public PlugPagDevice(@NonNull String identification) {
    this(identification, null, null);
  }
  
  public PlugPagDevice(@NonNull String identification, String activationCode, String s920SerialNumber) {
    this(identification, activationCode, s920SerialNumber, false);
  }
  
  public PlugPagDevice(@NonNull String identification, String activationCode, String s920SerialNumber, boolean less) {
    if (TextUtils.isEmpty(identification))
      throw new PlugPagDeviceInfoException("Identificação do aparelho não pode ser nulo ou vazio"); 
    this.mIdentification = identification;
    this.mType = evaluateType(identification);
    this.mSerialNumber = s920SerialNumber;
    this.mActivationCode = activationCode;
    this.mLess = less;
    if (this.mType == -1)
      this.mType = evaluateTypeByMacAddress(identification); 
    if (this.mType == -1)
      throw new PlugPagException("Identificação do aparelho inválida: não é possível definir o tipo do aparelho"); 
  }
  
  public String getIdentification() {
    return this.mIdentification;
  }
  
  public int getType() {
    return this.mType;
  }
  
  public String getActivationCode() {
    return this.mActivationCode;
  }
  
  public String getSerialNumber() {
    return this.mSerialNumber;
  }
  
  public boolean isLess() {
    return this.mLess;
  }
  
  private int evaluateTypeByMacAddress(@NonNull String macAddress) {
    int type = -1;
    BluetoothAdapter adapter = null;
    Set<BluetoothDevice> bondedDevices = null;
    adapter = BluetoothAdapter.getDefaultAdapter();
    bondedDevices = adapter.getBondedDevices();
    if (bondedDevices != null)
      for (BluetoothDevice device : bondedDevices) {
        if (macAddress.equals(device.getAddress())) {
          type = evaluateType(device.getName());
          break;
        } 
      }  
    return type;
  }
  
  public native int evaluateType(@NonNull String paramString);
}
