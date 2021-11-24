/*    */ package br.com.uol.pagseguro.libswitch.comm;
/*    */ 
/*    */ import android.bluetooth.BluetoothAdapter;
/*    */ import android.bluetooth.BluetoothDevice;
/*    */ import android.text.TextUtils;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class Adapter
/*    */ {
/* 16 */   private final BluetoothAdapter mAdapter = BluetoothFallback.adapter();
/*    */ 
/*    */   
/*    */   public void checkAvailability() throws AdapterUnavailableException {
/* 20 */     if (this.mAdapter == null) {
/* 21 */       throw new AdapterUnavailableException();
/*    */     }
/*    */   }
/*    */   
/*    */   public Device deviceByAddress(String macAddress) {
/* 26 */     String realMacAddress = null;
/*    */     
/* 28 */     if (!TextUtils.isEmpty(macAddress)) {
/* 29 */       for (BluetoothDevice currentDevice : this.mAdapter.getBondedDevices()) {
/* 30 */         if (macAddress.toUpperCase().equals(currentDevice.getName().toUpperCase()) || macAddress
/* 31 */           .toUpperCase().equals(currentDevice.getAddress().toUpperCase())) {
/* 32 */           realMacAddress = currentDevice.getAddress().toUpperCase();
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     }
/* 38 */     BluetoothDevice device = this.mAdapter.getRemoteDevice(realMacAddress);
/* 39 */     BluetoothFallback.tryToResetConnection(device);
/* 40 */     return Device.forThis(device);
/*    */   }
/*    */   
/*    */   public Device deviceByPrefix() {
/* 44 */     Set<BluetoothDevice> bondedDevices = this.mAdapter.getBondedDevices();
/* 45 */     if (bondedDevices != null) {
/* 46 */       for (BluetoothDevice device : bondedDevices) {
/* 47 */         Logger.printLog("OpenComm: device: " + device.getName());
/* 48 */         if (device.getName() != null && (device.getName().toUpperCase().startsWith("PAX") || device
/* 49 */           .getName().toUpperCase().startsWith("MOBI") || device
/* 50 */           .getName().toUpperCase().startsWith("PRO") || device
/* 51 */           .getName().toUpperCase().startsWith("W"))) {
/* 52 */           BluetoothFallback.tryToResetConnection(device);
/* 53 */           return Device.forThis(device);
/*    */         } 
/*    */       } 
/*    */     } else {
/* 57 */       Logger.printLog("OpenComm: Lista de dispositivos pareados nula!");
/*    */     } 
/* 59 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/libswitch/comm/Adapter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */