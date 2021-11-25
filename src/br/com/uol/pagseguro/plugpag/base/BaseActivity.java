package br.com.uol.pagseguro.plugpag.base;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import br.com.uol.pagseguro.plugpag.R;

public abstract class BaseActivity extends AppCompatActivity {
  @StringRes
  protected abstract int getBaseTitle();
  
  @LayoutRes
  protected abstract int getLayoutResource();
  
  @IdRes
  protected abstract int getToolBarResource();
  
  protected abstract void initComponents();
  
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(1);
    setContentView(getLayoutResource());
    Toolbar toolbar = (Toolbar)findViewById(getToolBarResource());
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayShowTitleEnabled(false);
      setDisplayHomeButton(Boolean.valueOf(true));
    } 
    initComponents();
  }
  
  public void setDisplayHomeButton(Boolean isHomeButton) {
    getSupportActionBar().setDisplayHomeAsUpEnabled(isHomeButton.booleanValue());
  }
  
  protected void onResume() {
    super.onResume();
    setTitle(getBaseTitle());
  }
  
  protected Integer getIntExtra(String key) {
    if (getIntent().getExtras() != null)
      return Integer.valueOf(getIntent().getExtras().getInt(key)); 
    return Integer.valueOf(0);
  }
  
  protected String[] getListExtra(String key) {
    if (getIntent().getExtras() != null)
      return getIntent().getExtras().getStringArray(key); 
    return new String[0];
  }
  
  public void startActivity(Intent intent) {
    Bundle options = ActivityOptions.makeCustomAnimation((Context)this, R.anim.enter_from_right, R.anim.fade_out).toBundle();
    startActivity(intent, options);
  }
  
  public void finish() {
    super.finish();
    overridePendingTransition(R.anim.fade_in, R.anim.exit_to_right);
  }
}
