/*    */ package br.com.uol.pagseguro.libswitch.comm;
/*    */ 
/*    */ import android.bluetooth.BluetoothDevice;
/*    */ import android.bluetooth.BluetoothSocket;
/*    */ import java.io.Closeable;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ class Device
/*    */ {
/*    */   private BluetoothDevice mDevice;
/*    */   
/*    */   private Device(BluetoothDevice device) {
/* 15 */     this.mDevice = device;
/*    */   }
/*    */   
/*    */   static Device forThis(BluetoothDevice device) {
/* 19 */     return new Device(device);
/*    */   }
/*    */   
/*    */   public String name() {
/* 23 */     return this.mDevice.getName();
/*    */   }
/*    */   
/*    */   private BluetoothSocket createDefaultSocket() throws CouldNotCreateSocketException {
/*    */     try {
/* 28 */       return this.mDevice.createRfcommSocketToServiceRecord(BluetoothFallback.SERIAL_OPERATION);
/* 29 */     } catch (IOException ex) {
/* 30 */       throw new CouldNotCreateSocketException();
/*    */     } 
/*    */   }
/*    */   
/*    */   private BluetoothSocket createFallbackSocket() throws IOException {
/*    */     try {
/* 36 */       return BluetoothFallback.fallbackSocketFor(this.mDevice);
/* 37 */     } catch (NoSuchMethodException e) {
/* 38 */       throw new IOException();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Connection connect() throws CouldNotCreateSocketException, IOException, CouldNotCloseSocketException, CouldNotEstablishConnection, InvalidConnectionStateException {
/* 44 */     BluetoothSocket socket = createDefaultSocket();
/*    */     try {
/* 46 */       socket.connect();
/* 47 */     } catch (IOException ex) {
/* 48 */       BluetoothFallback.tryToCloseThis((Closeable)socket);
/*    */       try {
/* 50 */         Thread.sleep(1000L);
/* 51 */         socket = createFallbackSocket();
/* 52 */         socket.connect();
/* 53 */       } catch (Exception e) {
/*    */         try {
/* 55 */           socket.close();
/* 56 */         } catch (IOException e1) {
/* 57 */           throw new CouldNotCloseSocketException();
/*    */         } 
/* 59 */         throw new CouldNotEstablishConnection();
/*    */       } 
/* 61 */     } catch (Throwable ex) {
/* 62 */       BluetoothFallback.tryToCloseThis((Closeable)socket);
/*    */     } 
/* 64 */     Connection connection = new Connection(socket);
/* 65 */     connection.checkUp();
/* 66 */     return connection;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/libswitch/comm/Device.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */