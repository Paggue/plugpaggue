package br.com.uol.pagseguro.libswitch.comm;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import java.util.Set;

class Adapter {
  private final BluetoothAdapter mAdapter = BluetoothFallback.adapter();
  
  public void checkAvailability() throws AdapterUnavailableException {
    if (this.mAdapter == null)
      throw new AdapterUnavailableException(); 
  }
  
  public Device deviceByAddress(String macAddress) {
    String realMacAddress = null;
    if (!TextUtils.isEmpty(macAddress))
      for (BluetoothDevice currentDevice : this.mAdapter.getBondedDevices()) {
        if (macAddress.toUpperCase().equals(currentDevice.getName().toUpperCase()) || macAddress
          .toUpperCase().equals(currentDevice.getAddress().toUpperCase())) {
          realMacAddress = currentDevice.getAddress().toUpperCase();
          break;
        } 
      }  
    BluetoothDevice device = this.mAdapter.getRemoteDevice(realMacAddress);
    BluetoothFallback.tryToResetConnection(device);
    return Device.forThis(device);
  }
  
  public Device deviceByPrefix() {
    Set<BluetoothDevice> bondedDevices = this.mAdapter.getBondedDevices();
    if (bondedDevices != null) {
      for (BluetoothDevice device : bondedDevices) {
        Logger.printLog("OpenComm: device: " + device.getName());
        if (device.getName() != null && (device.getName().toUpperCase().startsWith("PAX") || device
          .getName().toUpperCase().startsWith("MOBI") || device
          .getName().toUpperCase().startsWith("PRO") || device
          .getName().toUpperCase().startsWith("W"))) {
          BluetoothFallback.tryToResetConnection(device);
          return Device.forThis(device);
        } 
      } 
    } else {
      Logger.printLog("OpenComm: Lista de dispositivos pareados nula!");
    } 
    return null;
  }
}
