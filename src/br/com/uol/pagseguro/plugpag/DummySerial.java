/*    */ package br.com.uol.pagseguro.plugpag;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DummySerial
/*    */ {
/*    */   static SerialPortOpenInterface serialPortOpen;
/*    */   static SerialPortCloseInterface serialPortClose;
/*    */   static SerialPortReadInterface serialPortRead;
/*    */   static SerialPortWriteInterface serialPortWrite;
/*    */   
/*    */   public static int ClearSerial() {
/* 13 */     return 0;
/*    */   }
/*    */   
/*    */   public static void setErrBase(String erroBase) {}
/*    */   
/*    */   public static int SerialPortOpen() {
/* 19 */     return serialPortOpen.execute();
/*    */   }
/*    */   
/*    */   public static int SerialPortClose() {
/* 23 */     return serialPortClose.execute();
/*    */   }
/*    */   
/*    */   public static int SerialPortRead(byte[] buffer, int[] size, int piTimeout) {
/* 27 */     return serialPortRead.execute(buffer, size, piTimeout);
/*    */   }
/*    */   
/*    */   public static int SerialPortWrite(byte[] block, int size) {
/* 31 */     return serialPortWrite.execute(block, size);
/*    */   }
/*    */   
/*    */   public static void setMacAddress(String macAddress) {}
/*    */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/DummySerial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */