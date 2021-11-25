package br.com.uol.pagseguro.plugpag;

public class PlugPagLibraryLoader {
  public static final void loadNativeLibraries() {
    System.loadLibrary("PlugPag");
  }
}
