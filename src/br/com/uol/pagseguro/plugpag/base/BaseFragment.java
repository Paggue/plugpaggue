package br.com.uol.pagseguro.plugpag.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.uol.pagseguro.plugpag.PlugPagEventLoggerData;
import br.com.uol.pagseguro.plugpag.PlugPagEventLoggerListener;

public abstract class BaseFragment extends Fragment {
  public PlugPagEventLoggerListener mPlugPagEventLoggerListener;
  
  protected abstract int getLayoutResource();
  
  public abstract String getTagName();
  
  public abstract void initComponents(View paramView);
  
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(getLayoutResource(), container, false);
  }
  
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initComponents(view);
  }
  
  public void setPlugPagEventLoggerListener(PlugPagEventLoggerListener plugPagEventLoggerListener) {
    this.mPlugPagEventLoggerListener = plugPagEventLoggerListener;
  }
  
  public void sendEventLog(PlugPagEventLoggerData data) {
    if (this.mPlugPagEventLoggerListener != null)
      this.mPlugPagEventLoggerListener.recordBreadCrumb(data); 
  }
}
