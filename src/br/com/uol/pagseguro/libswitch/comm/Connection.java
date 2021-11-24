/*     */ package br.com.uol.pagseguro.libswitch.comm;
/*     */ 
/*     */ import android.bluetooth.BluetoothSocket;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.TimeoutException;
/*     */ 
/*     */ 
/*     */ 
/*     */ class Connection
/*     */ {
/*     */   private InputStream mInputStream;
/*     */   private final BluetoothSocket mSocket;
/*     */   private OutputStream mOutputStream;
/*  19 */   private int timeoutCount = 0;
/*  20 */   private final int TIMEOUT_TIME = 10;
/*     */   
/*     */   Connection(BluetoothSocket socket) throws IOException {
/*  23 */     this.mSocket = socket;
/*  24 */     if (this.mSocket != null) {
/*  25 */       this.mInputStream = socket.getInputStream();
/*  26 */       this.mOutputStream = socket.getOutputStream();
/*     */     } 
/*     */   }
/*     */   
/*     */   void dispose() {
/*  31 */     BluetoothFallback.tryToCloseThis((Closeable)this.mSocket);
/*  32 */     BluetoothFallback.tryToCloseThis(this.mInputStream);
/*  33 */     BluetoothFallback.tryToCloseThis(this.mOutputStream);
/*     */   }
/*     */   
/*     */   void close() throws CouldNotCloseException {
/*  37 */     CouldNotCloseException exception = null;
/*  38 */     if (this.mOutputStream != null) {
/*     */       try {
/*  40 */         Logger.printLog("CloseComm Close OutputStream");
/*  41 */         this.mOutputStream.close();
/*  42 */       } catch (IOException ex) {
/*  43 */         exception = new CouldNotCloseOutputStreamException();
/*     */       } 
/*     */     }
/*  46 */     if (this.mInputStream != null) {
/*     */       try {
/*  48 */         Logger.printLog("CloseComm Close InputStream");
/*  49 */         this.mInputStream.close();
/*  50 */       } catch (IOException ex) {
/*  51 */         exception = new CouldNotCloseInputStreamException();
/*     */       } 
/*     */     }
/*  54 */     if (this.mSocket != null) {
/*     */       try {
/*  56 */         Logger.printLog("CloseComm socket");
/*  57 */         this.mSocket.close();
/*  58 */       } catch (IOException e) {
/*  59 */         exception = new CouldNotCloseSocketException();
/*     */       } 
/*     */     }
/*  62 */     if (exception != null) {
/*  63 */       throw exception;
/*     */     }
/*     */   }
/*     */   
/*     */   void checkUp() throws InvalidConnectionStateException {
/*  68 */     if (!isOpen()) {
/*  69 */       throw new InvalidConnectionStateException();
/*     */     }
/*     */   }
/*     */   
/*     */   Map.Entry<Integer, Long> clear() throws IOException {
/*  74 */     int available = this.mInputStream.available();
/*  75 */     long skip = this.mInputStream.skip(available);
/*  76 */     return new AbstractMap.SimpleEntry<>(Integer.valueOf(available), Long.valueOf(skip));
/*     */   }
/*     */ 
/*     */   
/*     */   void send(byte[] bytes) throws OutputStreamNotAvailableException, CouldNotWriteToOutputStreamException, CouldNotFlushStreamException {
/*  81 */     if (this.mOutputStream == null) {
/*  82 */       throw new OutputStreamNotAvailableException();
/*     */     }
/*     */     try {
/*  85 */       this.mOutputStream.write(bytes);
/*  86 */     } catch (IOException e) {
/*  87 */       throw new CouldNotWriteToOutputStreamException();
/*     */     } 
/*     */     try {
/*  90 */       this.mOutputStream.flush();
/*  91 */     } catch (IOException e) {
/*  92 */       throw new CouldNotFlushStreamException();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void readBlock(final byte[] buffer, final int[] acc, int timeout) throws CouldNotReadFromInputStreamException, TimeoutException, InterruptedException {
/*  98 */     performRead(new Performer<Integer>()
/*     */         {
/*     */           public Integer perform() throws IOException {
/* 101 */             int result = Connection.this.mInputStream.read(buffer, 0, acc[0]);
/* 102 */             acc[0] = result;
/* 103 */             return Integer.valueOf(0);
/*     */           }
/*     */         }timeout);
/*     */   }
/*     */ 
/*     */   
/*     */   int read(int timeout) throws CouldNotReadFromInputStreamException, TimeoutException, InterruptedException {
/* 110 */     return ((Integer)performRead(new Performer<Integer>()
/*     */         {
/*     */           public Integer perform() throws IOException {
/* 113 */             return Integer.valueOf(Connection.this.mInputStream.read());
/*     */           }
/*     */         },  timeout)).intValue();
/*     */   }
/*     */   
/*     */   private boolean hasOutputStream() {
/* 119 */     return (this.mOutputStream != null);
/*     */   }
/*     */   
/*     */   private boolean hasInputStream() {
/* 123 */     return (this.mInputStream != null);
/*     */   }
/*     */   
/*     */   private boolean isOpen() {
/* 127 */     if (hasOutputStream()) {
/* 128 */       Logger.printLog("OpenComm: Stream de saída aberto com sucesso");
/*     */     }
/* 130 */     if (hasInputStream()) {
/* 131 */       Logger.printLog("OpenComm: Stream de entrada aberto com sucesso");
/*     */     }
/* 133 */     return (hasInputStream() && hasOutputStream());
/*     */   }
/*     */ 
/*     */   
/*     */   private <T> T performRead(Performer<T> performer, int timeout) throws InterruptedException, TimeoutException, CouldNotReadFromInputStreamException {
/* 138 */     long now = System.currentTimeMillis();
/* 139 */     while (System.currentTimeMillis() < now + timeout) {
/*     */       try {
/* 141 */         if (this.mInputStream.available() > 0) {
/* 142 */           return performer.perform();
/*     */         }
/* 144 */         Thread.sleep(20L);
/*     */       }
/* 146 */       catch (IOException e) {
/* 147 */         throw new CouldNotReadFromInputStreamException();
/*     */       } 
/*     */     } 
/* 150 */     checkConnection();
/* 151 */     throw new TimeoutException();
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkConnection() {
/* 156 */     if (this.timeoutCount > 10) {
/*     */       
/*     */       try {
/*     */         try {
/* 160 */           byte[] bytes = { 1, 2 };
/* 161 */           send(bytes);
/* 162 */         } catch (OutputStreamNotAvailableException e) {
/* 163 */           this.mSocket.close();
/* 164 */         } catch (CouldNotWriteToOutputStreamException e) {
/* 165 */           this.mSocket.close();
/* 166 */         } catch (CouldNotFlushStreamException e) {
/* 167 */           this.mSocket.close();
/*     */         } finally {
/* 169 */           this.timeoutCount = 0;
/*     */         } 
/* 171 */       } catch (IOException e) {
/* 172 */         Logger.printLog("checkConnection: IOException " + e);
/*     */       } 
/*     */     }
/* 175 */     this.timeoutCount++;
/*     */   }
/*     */   
/*     */   private static interface Performer<T> {
/*     */     T perform() throws IOException;
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/libswitch/comm/Connection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */