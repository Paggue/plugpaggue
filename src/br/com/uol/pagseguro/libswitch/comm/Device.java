package br.com.uol.pagseguro.libswitch.comm;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import java.io.Closeable;
import java.io.IOException;

class Device {
  private BluetoothDevice mDevice;
  
  private Device(BluetoothDevice device) {
    this.mDevice = device;
  }
  
  static Device forThis(BluetoothDevice device) {
    return new Device(device);
  }
  
  public String name() {
    return this.mDevice.getName();
  }
  
  private BluetoothSocket createDefaultSocket() throws CouldNotCreateSocketException {
    try {
      return this.mDevice.createRfcommSocketToServiceRecord(BluetoothFallback.SERIAL_OPERATION);
    } catch (IOException ex) {
      throw new CouldNotCreateSocketException();
    } 
  }
  
  private BluetoothSocket createFallbackSocket() throws IOException {
    try {
      return BluetoothFallback.fallbackSocketFor(this.mDevice);
    } catch (NoSuchMethodException e) {
      throw new IOException();
    } 
  }
  
  public Connection connect() throws CouldNotCreateSocketException, IOException, CouldNotCloseSocketException, CouldNotEstablishConnection, InvalidConnectionStateException {
    BluetoothSocket socket = createDefaultSocket();
    try {
      socket.connect();
    } catch (IOException ex) {
      BluetoothFallback.tryToCloseThis((Closeable)socket);
      try {
        Thread.sleep(1000L);
        socket = createFallbackSocket();
        socket.connect();
      } catch (Exception e) {
        try {
          socket.close();
        } catch (IOException e1) {
          throw new CouldNotCloseSocketException();
        } 
        throw new CouldNotEstablishConnection();
      } 
    } catch (Throwable ex) {
      BluetoothFallback.tryToCloseThis((Closeable)socket);
    } 
    Connection connection = new Connection(socket);
    connection.checkUp();
    return connection;
  }
}
