package br.com.uol.pagseguro.libswitch.comm;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Looper;
import java.io.Closeable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

class BluetoothFallback {
  static final UUID SERIAL_OPERATION = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
  
  private static boolean sCalledLooperAlready;
  
  static BluetoothSocket fallbackSocketFor(BluetoothDevice device) throws NoSuchMethodException {
    try {
      return (BluetoothSocket)device
        .getClass()
        .getMethod("createRfcommSocket", new Class[] { int.class }).invoke(device, new Object[] { Integer.valueOf(1) });
    } catch (InvocationTargetException e) {
      throw new NoSuchMethodException();
    } catch (IllegalAccessException e) {
      throw new NoSuchMethodException();
    } 
  }
  
  static void tryToCloseThis(Closeable obj) {
    try {
      obj.close();
    } catch (Exception exception) {}
  }
  
  static void tryToResetConnection(BluetoothDevice devSetup) {
    BluetoothSocket socket = null;
    try {
      socket = devSetup.createRfcommSocketToServiceRecord(SERIAL_OPERATION);
      Field f = socket.getClass().getDeclaredField("mFdHandle");
      f.setAccessible(true);
      f.set(socket, Integer.valueOf(32768));
    } catch (Exception exception) {
      try {
        socket.close();
      } catch (Exception exception1) {}
    } finally {
      try {
        socket.close();
      } catch (Exception exception) {}
    } 
  }
  
  static BluetoothAdapter adapter() {
    if (!sCalledLooperAlready && Looper.myLooper() == null) {
      try {
        Looper.prepare();
      } catch (RuntimeException e) {
        e.printStackTrace();
      } 
      sCalledLooperAlready = true;
    } 
    return BluetoothAdapter.getDefaultAdapter();
  }
  
  static void resetAdapter() {
    sCalledLooperAlready = false;
  }
}
