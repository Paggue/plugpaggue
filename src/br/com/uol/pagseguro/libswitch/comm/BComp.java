/*     */ package br.com.uol.pagseguro.libswitch.comm;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BComp
/*     */ {
/*  11 */   private static int errBase = 0;
/*     */   
/*     */   private static final int TRUE = 1;
/*     */   
/*     */   private static final int FALSE = 0;
/*     */   
/*     */   private static final int ERROR_ADAPTER_NULL = -2;
/*     */   
/*     */   private static final int ERROR_DEVICE_NULL = -3;
/*     */   
/*     */   private static final int ERROR_RFCOMMSOCK = -4;
/*     */   
/*     */   private static final int ERROR_SOCKET_FALLBACK = -5;
/*     */   
/*     */   private static final int ERROR_CLOSE = -6;
/*     */   private static final int ERROR_BT_CONN = -7;
/*     */   private static final int ERROR_CREATE_SOCK = -8;
/*     */   private static final int ERROR_SOCK_NULL = -9;
/*     */   private static final int ERROR_OUT_SOCK = -10;
/*     */   private static final int ERROR_IN_SOCK = -11;
/*     */   private static final int ERROR_CLOSE_SOCK = -12;
/*     */   private static final int ERROR_GET_TO = -13;
/*     */   private static final int ERROR_IO_EXCEPT = -14;
/*     */   private static final int ERROR_INT_EXCEPT = -15;
/*     */   private static final int ERROR_WRITE_EXCEPT = -16;
/*     */   private static final int ERROR_FLUSH_EXCEPT = -17;
/*     */   private static final int ERROR_OUT_STREAM_NULL = -18;
/*     */   public static Connection sConnection;
/*     */   private static String macAddress;
/*     */   
/*     */   public static void dispose() {
/*  42 */     if (sConnection != null) {
/*  43 */       sConnection.dispose();
/*     */     }
/*  45 */     BluetoothFallback.resetAdapter();
/*  46 */     errBase = 0;
/*     */   }
/*     */   
/*     */   public static int ClearSerial() {
/*     */     try {
/*  51 */       if (sConnection != null) {
/*  52 */         Map.Entry<Integer, Long> result = sConnection.clear();
/*  53 */         Logger.printLog(String.format(Locale.getDefault(), "ClearSerial: %d Available - %d Skipped", new Object[] { result
/*  54 */                 .getKey(), result.getValue() }));
/*     */       } 
/*  56 */     } catch (Exception e) {
/*  57 */       Logger.printLog("ClearSerial: Exception", e);
/*  58 */       return 0;
/*     */     } 
/*  60 */     return 1;
/*     */   }
/*     */   
/*     */   public static void setErrBase(int erroBase) {
/*  64 */     errBase = erroBase;
/*     */   }
/*     */   
/*     */   public static void setMacAddress(String macAddress) {
/*  68 */     BComp.macAddress = macAddress;
/*  69 */     errBase = 0;
/*     */   }
/*     */   
/*     */   public static int OpenComm() {
/*     */     int errorCode;
/*     */     try {
/*  75 */       dispose();
/*  76 */       Adapter adapter = new Adapter();
/*  77 */       adapter.checkAvailability();
/*  78 */       Device deviceToUse = selectDevice(adapter);
/*  79 */       sConnection = deviceToUse.connect();
/*  80 */       return 1;
/*  81 */     } catch (InvalidConnectionStateException e) {
/*  82 */       Logger.printLog("OpenComm some socket null, others");
/*  83 */       errorCode = -9;
/*  84 */     } catch (AdapterUnavailableException e) {
/*  85 */       Logger.printLog("OpenComm: Adaptador Bluetooth Indisponível");
/*  86 */       errorCode = -2;
/*  87 */     } catch (CouldNotEstablishConnection e) {
/*  88 */       Logger.printLog("OpenComm: Não foi possível estabelecer uma conexão");
/*  89 */       errorCode = -7;
/*  90 */     } catch (CouldNotCreateSocketException e) {
/*  91 */       Logger.printLog("OpenComm: Não foi possível criar o Socket padrão", e);
/*  92 */       errorCode = -4;
/*  93 */     } catch (CouldNotCloseSocketException e) {
/*  94 */       Logger.printLog("OpenComm: Não foi possível fechar o Socket", e);
/*  95 */       errorCode = -6;
/*  96 */     } catch (NoDeviceFoundException e) {
/*  97 */       errorCode = -3;
/*  98 */     } catch (Exception e) {
/*  99 */       Logger.printLog("OpenComm: Erro ao obter Streams ou falha genérica no processo", e);
/* 100 */       if (sConnection != null) {
/* 101 */         sConnection.dispose();
/*     */       }
/* 103 */       errorCode = -8;
/*     */     } 
/* 105 */     return errBase + errorCode;
/*     */   }
/*     */   
/*     */   private static Device selectDevice(Adapter adapter) throws NoDeviceFoundException {
/*     */     Device deviceToUse;
/* 110 */     if (macAddress != null && !"".equals(macAddress)) {
/* 111 */       Logger.printLog("OpenComm: Recuperando dispositivo pelo MacAddress " + macAddress);
/* 112 */       deviceToUse = adapter.deviceByAddress(macAddress);
/*     */     } else {
/* 114 */       Logger.printLog("OpenComm: Recuperando dispositivo pelo prefixo...");
/* 115 */       deviceToUse = adapter.deviceByPrefix();
/*     */     } 
/*     */     
/* 118 */     if (deviceToUse == null) {
/* 119 */       Logger.printLog("OpenComm: Dispositivo não encontrado!");
/* 120 */       throw new NoDeviceFoundException();
/*     */     } 
/* 122 */     Logger.printLog("OpenComm: Dispositivo em uso [" + deviceToUse.name() + "]");
/*     */     
/* 124 */     return deviceToUse;
/*     */   }
/*     */   
/*     */   public static int CloseComm() {
/*     */     int error;
/*     */     try {
/* 130 */       if (sConnection != null) {
/* 131 */         sConnection.close();
/*     */       }
/* 133 */       return 1;
/* 134 */     } catch (CouldNotCloseInputStreamException e) {
/* 135 */       Logger.printLog("CloseComm exception InputStream");
/* 136 */       error = errBase + -11;
/* 137 */     } catch (CouldNotCloseOutputStreamException e) {
/* 138 */       Logger.printLog("CloseComm exception OutputStream");
/* 139 */       error = errBase + -10;
/* 140 */     } catch (CouldNotCloseException e) {
/* 141 */       Logger.printLog("CloseComm exception socket");
/* 142 */       error = errBase + -12;
/*     */     } 
/* 144 */     BluetoothFallback.resetAdapter();
/* 145 */     return error;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int GetByte(int piTimeout) {
/*     */     try {
/* 152 */       return sConnection.read(piTimeout);
/* 153 */     } catch (CouldNotReadFromInputStreamException e) {
/* 154 */       Logger.printLog("GetByte: Não foi possível ler do stream", e);
/* 155 */       sConnection.close();
/* 156 */       return errBase + -14;
/* 157 */     } catch (TimeoutException e) {
/* 158 */       Logger.printLog("GetByte: Timeout");
/* 159 */       return errBase + -13;
/* 160 */     } catch (InterruptedException e) {
/* 161 */       sConnection.close();
/* 162 */       Logger.printLog("GetByte: Operação interrompida", e);
/* 163 */       return errBase + -15;
/*     */     }
/* 165 */     catch (CouldNotCloseException e) {
/* 166 */       Logger.printLog("GetBuffer: Erro ao tentar fechar o socket");
/* 167 */       return errBase + -7;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int GetBuffer(byte[] buffer, int[] size, int piTimeout) {
/*     */     try {
/* 175 */       Logger.printLog("GetBuffer: Realizando leitura...");
/* 176 */       sConnection.readBlock(buffer, size, piTimeout);
/* 177 */       return size[0];
/* 178 */     } catch (InterruptedException e) {
/* 179 */       Logger.printLog("GetBuffer: Operação interrompida", e);
/* 180 */       sConnection.close();
/* 181 */       return errBase + -15;
/* 182 */     } catch (CouldNotReadFromInputStreamException e) {
/* 183 */       Logger.printLog("GetBuffer: Não foi possível ler do stream", e);
/* 184 */       sConnection.close();
/* 185 */       return errBase + -14;
/* 186 */     } catch (TimeoutException e) {
/* 187 */       Logger.printLog("GetBuffer: Timeout");
/* 188 */       return errBase + -13;
/*     */     }
/* 190 */     catch (CouldNotCloseException e) {
/* 191 */       Logger.printLog("GetBuffer: Erro ao tentar fechar o socket");
/* 192 */       return errBase + -7;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int SendBlock(byte[] block, int size) {
/* 197 */     Logger.printLog("SendBlock");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     try { sConnection.send(block);
/* 203 */       return size; }
/* 204 */     catch (OutputStreamNotAvailableException e)
/* 205 */     { Logger.printLog("SendBlock OutStream null");
/* 206 */       sConnection.close();
/* 207 */       int errorCode = -18;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 217 */       return errBase + errorCode; } catch (CouldNotWriteToOutputStreamException e) { Logger.printLog("SendBlock write IO exception", e); sConnection.close(); int errorCode = -16; return errBase + errorCode; } catch (CouldNotFlushStreamException e) { Logger.printLog("SendBlock flush IO exception", e); sConnection.close(); int errorCode = -17; return errBase + errorCode; }
/* 218 */     catch (CouldNotCloseException e)
/* 219 */     { Logger.printLog("GetBuffer: Erro ao tentar fechar o socket");
/* 220 */       return errBase + -7; }
/*     */   
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/libswitch/comm/BComp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */