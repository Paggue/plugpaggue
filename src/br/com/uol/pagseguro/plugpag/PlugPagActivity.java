package br.com.uol.pagseguro.plugpag;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.R;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.uol.pagseguro.plugpag.authentication.PlugPagAuthenticationFragment;
import br.com.uol.pagseguro.plugpag.datainput.PlugPagDataInputFragment;
import java.util.List;

public class PlugPagActivity extends AppCompatActivity implements PlugPagFragmentInteractionListener {
  public static final String ACTION_PLUGPAG_AUTHENTICATION = "PLUGPAG_AUTHENTICATION";
  
  public static final String ACTION_PLUGPAG_DATAINPUT = "PLUGPAG_DATAINPUT";
  
  public static final String ACTION_PLUGPAG_YNINPUT = "PLUGPAG_YNINPUT";
  
  public static final String ACTION_PLUGPAG_AUTHENTICATION_RESULT = "br.com.uol.pagseguro.plugpag.AUTHENTICATION_RESULT";
  
  public static final String EXTRA_APP_IDENTIFICATION = "EXTRA_APP_IDENTIFICATION";
  
  public static final String EXTRA_PLUGPAG_AUTHENTICATION_RESULT = "PLUGPAG_RESULT";
  
  protected void onStart() {
    super.onStart();
    getWindow().setSoftInputMode(16);
  }
  
  public void onBackPressed() {
    tellFragments();
    super.onBackPressed();
  }
  
  private void tellFragments() {
    List<Fragment> fragments = getSupportFragmentManager().getFragments();
    for (Fragment f : fragments) {
      if (f != null && f instanceof PlugPagDataInputFragment)
        ((PlugPagDataInputFragment)f).onBackPressed(); 
    } 
  }
  
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_plugpag);
  }
  
  @SuppressLint({"SourceLockedOrientationActivity"})
  protected void onResume() {
    Intent intent = null;
    String action = null;
    PlugPagFragment fragment = null;
    Bundle args = null;
    super.onResume();
    setRequestedOrientation(7);
    intent = getIntent();
    if (intent != null && !TextUtils.isEmpty(intent.getAction())) {
      PlugPagAuthenticationFragment plugPagAuthenticationFragment;
      PlugPagDataInputFragment plugPagDataInputFragment;
      action = intent.getAction();
      args = new Bundle();
      args.putSerializable("ARG_APP_IDENTIFICATION", intent.getSerializableExtra("EXTRA_APP_IDENTIFICATION"));
      switch (action) {
        case "PLUGPAG_AUTHENTICATION":
          plugPagAuthenticationFragment = new PlugPagAuthenticationFragment();
          break;
        case "PLUGPAG_DATAINPUT":
          plugPagDataInputFragment = new PlugPagDataInputFragment();
          break;
      } 
      plugPagDataInputFragment.setArguments(getIntent().getExtras());
      getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.plupag_activity_main_container, (Fragment)plugPagDataInputFragment)
        .commit();
    } 
  }
  
  public void interact(@NonNull Intent intent) {
    if (intent != null && 
      "ACTION_SHOW_SNACKBAR".equals(intent.getAction()))
      showSnackbar(intent); 
  }
  
  private void showSnackbar(Intent intent) {
    String title = null;
    String message = null;
    Snackbar snackbar = null;
    ViewGroup customView = null;
    title = intent.getStringExtra("KEY_TITLE");
    message = intent.getStringExtra("KEY_MESSAGE");
    customView = (ViewGroup)LayoutInflater.from(getApplicationContext()).inflate(R.layout.plugpag_snackbar_error, null);
    ((AppCompatTextView)customView.findViewById(R.id.plugpag_snackbar_title)).setText(title);
    ((AppCompatTextView)customView.findViewById(R.id.plugpag_snackbar_message)).setText(message);
    snackbar = Snackbar.make(
        getWindow().getDecorView().findViewById(16908290), "", -1);
    snackbar.getView().setBackgroundColor(ContextCompat.getColor((Context)this, R.color.plugpag_error));
    snackbar.getView()
      .findViewById(R.id.snackbar_text)
      .setVisibility(4);
    ((Snackbar.SnackbarLayout)snackbar.getView()).addView((View)customView, 0);
    snackbar.show();
  }
  
  public void setPlugPagTheme(int plugPagTheme) {
    setTheme(plugPagTheme);
  }
}
