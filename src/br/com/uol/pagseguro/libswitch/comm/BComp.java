package br.com.uol.pagseguro.libswitch.comm;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class BComp {
  private static int errBase = 0;
  
  private static final int TRUE = 1;
  
  private static final int FALSE = 0;
  
  private static final int ERROR_ADAPTER_NULL = -2;
  
  private static final int ERROR_DEVICE_NULL = -3;
  
  private static final int ERROR_RFCOMMSOCK = -4;
  
  private static final int ERROR_SOCKET_FALLBACK = -5;
  
  private static final int ERROR_CLOSE = -6;
  
  private static final int ERROR_BT_CONN = -7;
  
  private static final int ERROR_CREATE_SOCK = -8;
  
  private static final int ERROR_SOCK_NULL = -9;
  
  private static final int ERROR_OUT_SOCK = -10;
  
  private static final int ERROR_IN_SOCK = -11;
  
  private static final int ERROR_CLOSE_SOCK = -12;
  
  private static final int ERROR_GET_TO = -13;
  
  private static final int ERROR_IO_EXCEPT = -14;
  
  private static final int ERROR_INT_EXCEPT = -15;
  
  private static final int ERROR_WRITE_EXCEPT = -16;
  
  private static final int ERROR_FLUSH_EXCEPT = -17;
  
  private static final int ERROR_OUT_STREAM_NULL = -18;
  
  public static Connection sConnection;
  
  private static String macAddress;
  
  public static void dispose() {
    if (sConnection != null)
      sConnection.dispose(); 
    BluetoothFallback.resetAdapter();
    errBase = 0;
  }
  
  public static int ClearSerial() {
    try {
      if (sConnection != null) {
        Map.Entry<Integer, Long> result = sConnection.clear();
        Logger.printLog(String.format(Locale.getDefault(), "ClearSerial: %d Available - %d Skipped", new Object[] { result
                .getKey(), result.getValue() }));
      } 
    } catch (Exception e) {
      Logger.printLog("ClearSerial: Exception", e);
      return 0;
    } 
    return 1;
  }
  
  public static void setErrBase(int erroBase) {
    errBase = erroBase;
  }
  
  public static void setMacAddress(String macAddress) {
    BComp.macAddress = macAddress;
    errBase = 0;
  }
  
  public static int OpenComm() {
    int errorCode;
    try {
      dispose();
      Adapter adapter = new Adapter();
      adapter.checkAvailability();
      Device deviceToUse = selectDevice(adapter);
      sConnection = deviceToUse.connect();
      return 1;
    } catch (InvalidConnectionStateException e) {
      Logger.printLog("OpenComm some socket null, others");
      errorCode = -9;
    } catch (AdapterUnavailableException e) {
      Logger.printLog("OpenComm: Adaptador Bluetooth Indisponível");
      errorCode = -2;
    } catch (CouldNotEstablishConnection e) {
      Logger.printLog("OpenComm: Não foi possível estabelecer uma conexão");
      errorCode = -7;
    } catch (CouldNotCreateSocketException e) {
      Logger.printLog("OpenComm: Não foi possível criar o Socket padrão", e);
      errorCode = -4;
    } catch (CouldNotCloseSocketException e) {
      Logger.printLog("OpenComm: Não foi possível fechar o Socket", e);
      errorCode = -6;
    } catch (NoDeviceFoundException e) {
      errorCode = -3;
    } catch (Exception e) {
      Logger.printLog("OpenComm: Erro ao obter Streams ou falha genérica no processo", e);
      if (sConnection != null)
        sConnection.dispose(); 
      errorCode = -8;
    } 
    return errBase + errorCode;
  }
  
  private static Device selectDevice(Adapter adapter) throws NoDeviceFoundException {
    Device deviceToUse;
    if (macAddress != null && !"".equals(macAddress)) {
      Logger.printLog("OpenComm: Recuperando dispositivo pelo MacAddress " + macAddress);
      deviceToUse = adapter.deviceByAddress(macAddress);
    } else {
      Logger.printLog("OpenComm: Recuperando dispositivo pelo prefixo...");
      deviceToUse = adapter.deviceByPrefix();
    } 
    if (deviceToUse == null) {
      Logger.printLog("OpenComm: Dispositivo não encontrado!");
      throw new NoDeviceFoundException();
    } 
    Logger.printLog("OpenComm: Dispositivo em uso [" + deviceToUse.name() + "]");
    return deviceToUse;
  }
  
  public static int CloseComm() {
    int error;
    try {
      if (sConnection != null)
        sConnection.close(); 
      return 1;
    } catch (CouldNotCloseInputStreamException e) {
      Logger.printLog("CloseComm exception InputStream");
      error = errBase + -11;
    } catch (CouldNotCloseOutputStreamException e) {
      Logger.printLog("CloseComm exception OutputStream");
      error = errBase + -10;
    } catch (CouldNotCloseException e) {
      Logger.printLog("CloseComm exception socket");
      error = errBase + -12;
    } 
    BluetoothFallback.resetAdapter();
    return error;
  }
  
  public static int GetByte(int piTimeout) {
    try {
      return sConnection.read(piTimeout);
    } catch (CouldNotReadFromInputStreamException e) {
      Logger.printLog("GetByte: Não foi possível ler do stream", e);
      sConnection.close();
      return errBase + -14;
    } catch (TimeoutException e) {
      Logger.printLog("GetByte: Timeout");
      return errBase + -13;
    } catch (InterruptedException e) {
      sConnection.close();
      Logger.printLog("GetByte: Operação interrompida", e);
      return errBase + -15;
    } catch (CouldNotCloseException e) {
      Logger.printLog("GetBuffer: Erro ao tentar fechar o socket");
      return errBase + -7;
    } 
  }
  
  public static int GetBuffer(byte[] buffer, int[] size, int piTimeout) {
    try {
      Logger.printLog("GetBuffer: Realizando leitura...");
      sConnection.readBlock(buffer, size, piTimeout);
      return size[0];
    } catch (InterruptedException e) {
      Logger.printLog("GetBuffer: Operação interrompida", e);
      sConnection.close();
      return errBase + -15;
    } catch (CouldNotReadFromInputStreamException e) {
      Logger.printLog("GetBuffer: Não foi possível ler do stream", e);
      sConnection.close();
      return errBase + -14;
    } catch (TimeoutException e) {
      Logger.printLog("GetBuffer: Timeout");
      return errBase + -13;
    } catch (CouldNotCloseException e) {
      Logger.printLog("GetBuffer: Erro ao tentar fechar o socket");
      return errBase + -7;
    } 
  }
  
  public static int SendBlock(byte[] block, int size) {
    Logger.printLog("SendBlock");
    try {
      sConnection.send(block);
      return size;
    } catch (OutputStreamNotAvailableException e) {
      Logger.printLog("SendBlock OutStream null");
      sConnection.close();
      int errorCode = -18;
      return errBase + errorCode;
    } catch (CouldNotWriteToOutputStreamException e) {
      Logger.printLog("SendBlock write IO exception", e);
      sConnection.close();
      int errorCode = -16;
      return errBase + errorCode;
    } catch (CouldNotFlushStreamException e) {
      Logger.printLog("SendBlock flush IO exception", e);
      sConnection.close();
      int errorCode = -17;
      return errBase + errorCode;
    } catch (CouldNotCloseException e) {
      Logger.printLog("GetBuffer: Erro ao tentar fechar o socket");
      return errBase + -7;
    } 
  }
}
