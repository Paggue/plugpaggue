package br.com.uol.pagseguro.plugpag;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
import java.io.Serializable;

public class PlugPagAppIdentification implements Serializable {
  static {
    PlugPagLibraryLoader.loadNativeLibraries();
  }
  
  private String mVersion = null;
  
  private String mName = null;
  
  public PlugPagAppIdentification(@NonNull String name, @NonNull String version) {
    if (TextUtils.isEmpty(name))
      throw new PlugPagException("Nome do aplicativo não pode ser nulo ou vazio"); 
    if (TextUtils.isEmpty(version))
      throw new PlugPagException("Versão do aplicativo não pode ser nulo ou vazio"); 
    this.mName = name;
    this.mVersion = version;
  }
  
  public String getVersion() {
    return this.mVersion;
  }
  
  public String getName() {
    return this.mName;
  }
}
