/*    */ package br.com.uol.pagseguro.libswitch.comm;
/*    */ 
/*    */ import android.bluetooth.BluetoothAdapter;
/*    */ import android.bluetooth.BluetoothDevice;
/*    */ import android.bluetooth.BluetoothSocket;
/*    */ import android.os.Looper;
/*    */ import java.io.Closeable;
/*    */ import java.lang.reflect.Field;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ 
/*    */ class BluetoothFallback
/*    */ {
/* 16 */   static final UUID SERIAL_OPERATION = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); private static boolean sCalledLooperAlready;
/*    */   
/*    */   static BluetoothSocket fallbackSocketFor(BluetoothDevice device) throws NoSuchMethodException {
/*    */     try {
/* 20 */       return (BluetoothSocket)device
/* 21 */         .getClass()
/* 22 */         .getMethod("createRfcommSocket", new Class[] { int.class
/* 23 */           }).invoke(device, new Object[] { Integer.valueOf(1) });
/* 24 */     } catch (InvocationTargetException e) {
/* 25 */       throw new NoSuchMethodException();
/* 26 */     } catch (IllegalAccessException e) {
/* 27 */       throw new NoSuchMethodException();
/*    */     } 
/*    */   }
/*    */   
/*    */   static void tryToCloseThis(Closeable obj) {
/*    */     try {
/* 33 */       obj.close();
/* 34 */     } catch (Exception exception) {}
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   static void tryToResetConnection(BluetoothDevice devSetup) {
/* 40 */     BluetoothSocket socket = null;
/*    */     
/* 42 */     try { socket = devSetup.createRfcommSocketToServiceRecord(SERIAL_OPERATION);
/* 43 */       Field f = socket.getClass().getDeclaredField("mFdHandle");
/* 44 */       f.setAccessible(true);
/* 45 */       f.set(socket, Integer.valueOf(32768)); }
/* 46 */     catch (Exception exception)
/*    */     
/*    */     { 
/*    */       try {
/* 50 */         socket.close();
/* 51 */       } catch (Exception exception1) {} } finally { try { socket.close(); } catch (Exception exception) {} }
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   static BluetoothAdapter adapter() {
/* 58 */     if (!sCalledLooperAlready && Looper.myLooper() == null) {
/*    */       try {
/* 60 */         Looper.prepare();
/* 61 */       } catch (RuntimeException e) {
/* 62 */         e.printStackTrace();
/*    */       } 
/* 64 */       sCalledLooperAlready = true;
/*    */     } 
/* 66 */     return BluetoothAdapter.getDefaultAdapter();
/*    */   }
/*    */   
/*    */   static void resetAdapter() {
/* 70 */     sCalledLooperAlready = false;
/*    */   }
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/libswitch/comm/BluetoothFallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */