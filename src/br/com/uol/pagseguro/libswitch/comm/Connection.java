package br.com.uol.pagseguro.libswitch.comm;

import android.bluetooth.BluetoothSocket;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.AbstractMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

class Connection {
  private InputStream mInputStream;
  
  private final BluetoothSocket mSocket;
  
  private OutputStream mOutputStream;
  
  private int timeoutCount = 0;
  
  private final int TIMEOUT_TIME = 10;
  
  Connection(BluetoothSocket socket) throws IOException {
    this.mSocket = socket;
    if (this.mSocket != null) {
      this.mInputStream = socket.getInputStream();
      this.mOutputStream = socket.getOutputStream();
    } 
  }
  
  void dispose() {
    BluetoothFallback.tryToCloseThis((Closeable)this.mSocket);
    BluetoothFallback.tryToCloseThis(this.mInputStream);
    BluetoothFallback.tryToCloseThis(this.mOutputStream);
  }
  
  void close() throws CouldNotCloseException {
    CouldNotCloseException exception = null;
    if (this.mOutputStream != null)
      try {
        Logger.printLog("CloseComm Close OutputStream");
        this.mOutputStream.close();
      } catch (IOException ex) {
        exception = new CouldNotCloseOutputStreamException();
      }  
    if (this.mInputStream != null)
      try {
        Logger.printLog("CloseComm Close InputStream");
        this.mInputStream.close();
      } catch (IOException ex) {
        exception = new CouldNotCloseInputStreamException();
      }  
    if (this.mSocket != null)
      try {
        Logger.printLog("CloseComm socket");
        this.mSocket.close();
      } catch (IOException e) {
        exception = new CouldNotCloseSocketException();
      }  
    if (exception != null)
      throw exception; 
  }
  
  void checkUp() throws InvalidConnectionStateException {
    if (!isOpen())
      throw new InvalidConnectionStateException(); 
  }
  
  Map.Entry<Integer, Long> clear() throws IOException {
    int available = this.mInputStream.available();
    long skip = this.mInputStream.skip(available);
    return new AbstractMap.SimpleEntry<>(Integer.valueOf(available), Long.valueOf(skip));
  }
  
  void send(byte[] bytes) throws OutputStreamNotAvailableException, CouldNotWriteToOutputStreamException, CouldNotFlushStreamException {
    if (this.mOutputStream == null)
      throw new OutputStreamNotAvailableException(); 
    try {
      this.mOutputStream.write(bytes);
    } catch (IOException e) {
      throw new CouldNotWriteToOutputStreamException();
    } 
    try {
      this.mOutputStream.flush();
    } catch (IOException e) {
      throw new CouldNotFlushStreamException();
    } 
  }
  
  void readBlock(final byte[] buffer, final int[] acc, int timeout) throws CouldNotReadFromInputStreamException, TimeoutException, InterruptedException {
    performRead(new Performer<Integer>() {
          public Integer perform() throws IOException {
            int result = Connection.this.mInputStream.read(buffer, 0, acc[0]);
            acc[0] = result;
            return Integer.valueOf(0);
          }
        }timeout);
  }
  
  int read(int timeout) throws CouldNotReadFromInputStreamException, TimeoutException, InterruptedException {
    return ((Integer)performRead(new Performer<Integer>() {
          public Integer perform() throws IOException {
            return Integer.valueOf(Connection.this.mInputStream.read());
          }
        },  timeout)).intValue();
  }
  
  private boolean hasOutputStream() {
    return (this.mOutputStream != null);
  }
  
  private boolean hasInputStream() {
    return (this.mInputStream != null);
  }
  
  private boolean isOpen() {
    if (hasOutputStream())
      Logger.printLog("OpenComm: Stream de saída aberto com sucesso"); 
    if (hasInputStream())
      Logger.printLog("OpenComm: Stream de entrada aberto com sucesso"); 
    return (hasInputStream() && hasOutputStream());
  }
  
  private <T> T performRead(Performer<T> performer, int timeout) throws InterruptedException, TimeoutException, CouldNotReadFromInputStreamException {
    long now = System.currentTimeMillis();
    while (System.currentTimeMillis() < now + timeout) {
      try {
        if (this.mInputStream.available() > 0)
          return performer.perform(); 
        Thread.sleep(20L);
      } catch (IOException e) {
        throw new CouldNotReadFromInputStreamException();
      } 
    } 
    checkConnection();
    throw new TimeoutException();
  }
  
  private void checkConnection() {
    if (this.timeoutCount > 10)
      try {
        try {
          byte[] bytes = { 1, 2 };
          send(bytes);
        } catch (OutputStreamNotAvailableException e) {
          this.mSocket.close();
        } catch (CouldNotWriteToOutputStreamException e) {
          this.mSocket.close();
        } catch (CouldNotFlushStreamException e) {
          this.mSocket.close();
        } finally {
          this.timeoutCount = 0;
        } 
      } catch (IOException e) {
        Logger.printLog("checkConnection: IOException " + e);
      }  
    this.timeoutCount++;
  }
  
  private static interface Performer<T> {
    T perform() throws IOException;
  }
}
