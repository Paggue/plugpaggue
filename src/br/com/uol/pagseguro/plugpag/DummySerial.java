package br.com.uol.pagseguro.plugpag;

public class DummySerial {
  static SerialPortOpenInterface serialPortOpen;
  
  static SerialPortCloseInterface serialPortClose;
  
  static SerialPortReadInterface serialPortRead;
  
  static SerialPortWriteInterface serialPortWrite;
  
  public static int ClearSerial() {
    return 0;
  }
  
  public static void setErrBase(String erroBase) {}
  
  public static int SerialPortOpen() {
    return serialPortOpen.execute();
  }
  
  public static int SerialPortClose() {
    return serialPortClose.execute();
  }
  
  public static int SerialPortRead(byte[] buffer, int[] size, int piTimeout) {
    return serialPortRead.execute(buffer, size, piTimeout);
  }
  
  public static int SerialPortWrite(byte[] block, int size) {
    return serialPortWrite.execute(block, size);
  }
  
  public static void setMacAddress(String macAddress) {}
}
