package br.com.uol.pagseguro.plugpag;

import android.content.Intent;
import android.support.annotation.NonNull;

public interface PlugPagFragmentInteractionListener {
  public static final String ACTION_SHOW_SNACKBAR = "ACTION_SHOW_SNACKBAR";
  
  public static final String KEY_TITLE = "KEY_TITLE";
  
  public static final String KEY_MESSAGE = "KEY_MESSAGE";
  
  void interact(@NonNull Intent paramIntent);
}
