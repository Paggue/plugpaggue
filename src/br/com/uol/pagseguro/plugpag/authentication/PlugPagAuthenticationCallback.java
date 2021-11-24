package br.com.uol.pagseguro.plugpag.authentication;

import android.support.annotation.Nullable;

interface PlugPagAuthenticationCallback {
  void onPreAuthenticate();
  
  void onPostAuthenticate(@Nullable PlugPagAuthenticationResult paramPlugPagAuthenticationResult);
}


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/authentication/PlugPagAuthenticationCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */