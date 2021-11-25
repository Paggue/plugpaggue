package br.com.uol.pagseguro.plugpag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import br.com.uol.pagseguro.plugpag.exception.PlugPagException;
import br.com.uol.pagseguro.plugpag.listener.PlugPagNativeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public final class PlugPag extends PlugPagBase {
  public static int SELECTED_ITEM_IDX = 0;
  
  PlugPagEventListener mNativeEventListener = null;
  
  public PlugPag(@NonNull Context context, @NonNull PlugPagAppIdentification appIdentification) {
    int ret = 0;
    if (context == null)
      throw new PlugPagException("Referência do Context não pode ser nula"); 
    if (appIdentification == null)
      throw new PlugPagException("AppIdentification não pode ser nula"); 
    this.mContext = context;
    this.mAppIdentification = appIdentification;
    setup(context);
    ret = setVersionName(appIdentification.getName(), appIdentification.getVersion());
    if (ret != 0)
      throw new PlugPagException("Identificação do app invalido"); 
    try {
      InputStream is = context.getAssets().open("keymap.dat");
      FileOutputStream fos = new FileOutputStream(new File(context.getFilesDir(), "keymap.dat"));
      byte[] buffer = new byte[4096];
      int bytesRead = 0;
      while ((bytesRead = is.read(buffer)) > 0)
        fos.write(buffer, 0, bytesRead); 
      fos.flush();
      fos.close();
      is.close();
    } catch (Exception e) {
      Log.e("KEYMAP", "Error while copying keymap.dat", e);
    } 
  }
  
  public void setEventListener(PlugPagEventListener listener) {
    this.mEventListener = listener;
    if (this.mNativeEventListener == null)
      setNativeEventListener((PlugPagEventListener)new PlugPagNativeListener("SmartPhone", this.mEventListener)); 
  }
}
