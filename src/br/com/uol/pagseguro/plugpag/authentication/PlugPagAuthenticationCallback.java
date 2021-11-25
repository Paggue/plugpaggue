package br.com.uol.pagseguro.plugpag.authentication;

import android.support.annotation.Nullable;

interface PlugPagAuthenticationCallback {
  void onPreAuthenticate();
  
  void onPostAuthenticate(@Nullable PlugPagAuthenticationResult paramPlugPagAuthenticationResult);
}
