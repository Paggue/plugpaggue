package br.com.uol.pagseguro.plugpag;

import android.support.v4.app.Fragment;

public abstract class PlugPagFragment extends Fragment {
  public static final String ARG_PLUGPAG = "ARG_PLUGPAG";
  
  public static final String ARG_APP_IDENTIFICATION = "ARG_APP_IDENTIFICATION";
  
  protected native void onFinishDataInput(String paramString);
}
