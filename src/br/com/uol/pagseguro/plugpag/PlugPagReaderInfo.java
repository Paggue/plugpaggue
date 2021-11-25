package br.com.uol.pagseguro.plugpag;

import android.support.annotation.NonNull;

public class PlugPagReaderInfo {
  private final String mEmvKernelVersion;
  
  private final String mSharedLibraryVersion;
  
  public PlugPagReaderInfo(@NonNull String emvKernelVersion, @NonNull String sharedLibraryVersion) {
    this.mEmvKernelVersion = emvKernelVersion;
    this.mSharedLibraryVersion = sharedLibraryVersion;
  }
  
  public String getEmvKernelVersion() {
    return this.mEmvKernelVersion;
  }
  
  public String getSharedLibraryVersion() {
    return this.mSharedLibraryVersion;
  }
}
