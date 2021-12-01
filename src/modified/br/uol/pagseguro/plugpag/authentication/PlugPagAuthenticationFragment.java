package br.com.uol.pagseguro.plugpag.authentication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import br.com.uol.pagseguro.plugpag.DeviceInfo;
import br.com.uol.pagseguro.plugpag.PlugPag;
import br.com.uol.pagseguro.plugpag.PlugPagActivity;
import br.com.uol.pagseguro.plugpag.PlugPagFragment;
import br.com.uol.pagseguro.plugpag.PlugPagFragmentInteractionListener;
import br.com.uol.pagseguro.plugpag.R;
import br.com.uol.pagseguro.plugpag.authentication.PlugPagAuthenticationCallback;
import br.com.uol.pagseguro.plugpag.authentication.PlugPagAuthenticationRequest;
import br.com.uol.pagseguro.plugpag.authentication.PlugPagAuthenticationResult;
import java.util.ArrayList;

public class PlugPagAuthenticationFragment
    extends PlugPagFragment
    implements View.OnClickListener,
    PlugPagAuthenticationCallback,
    TextView.OnEditorActionListener,
    TextWatcher {
  private static final int OK = 0;
  private static final int ERROR_NOT_AUTHENTICATED = 1;
  private static final int ERROR_EMPTY_EMAIL = 2;
  private static final int ERROR_EMPTY_PASSWORD = 4;
  private static final int ERROR_INVALID_EMAIL_FORMAT = 8;
  private static final String FORGOT_PASSWORD_URL = "https://sac.uol.com.br/pagseguro/#/recuperarsenha";
  private static final String REGEX_EMAIL_FORMAT = "^([a-zA-Z_]+)(\\.?[a-zA-Z0-9_]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$";
  private static final TransformationMethod PASSWORD_TRANSFORMATION_METHOD = new PasswordTransformationMethod();
  private TextInputLayout mWrapperEmail = null;
  private AppCompatEditText mEdtEmail = null;
  private TextInputLayout mWrapperPassword = null;
  private AppCompatEditText mEdtPassword = null;
  private Button mBtnTogglePassword = null;
  private Button mBtnAuthenticate = null;
  private TextView mTxtForgotPassword = null;
  private PlugPagFragmentInteractionListener mInteractionListener = null;

  @Override
  @Nullable
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = null;
    super.onCreateView(inflater, container, savedInstanceState);
    view = inflater.inflate(R.layout.fragment_plugpag_authentication, container, false);
    this.getActivity().setResult(-1);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    this.setupViewReferences();
    this.setupEventListeners();
  }

  @Override
  public void onResume() {
    super.onResume();
    this.updatePasswordPadding();
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof PlugPagFragmentInteractionListener) {
      this.mInteractionListener = (PlugPagFragmentInteractionListener) ((Object) context);
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    this.mInteractionListener = null;
  }

  private void setupViewReferences() {
    View root = null;
    root = this.getView();
    this.mWrapperEmail = (TextInputLayout) ((Object) root.findViewById(R.id.plugpag_authentication_wrapper_email));
    this.mEdtEmail = (AppCompatEditText) ((Object) root.findViewById(R.id.plugpag_authentication_email));
    this.mWrapperPassword = (TextInputLayout) ((Object) root
        .findViewById(R.id.plugpag_authentication_wrapper_password));
    this.mEdtPassword = (AppCompatEditText) ((Object) root.findViewById(R.id.plugpag_authentication_password));
    this.mBtnTogglePassword = (Button) ((Object) root.findViewById(R.id.plugpag_authentication_toggle_password));
    this.mBtnAuthenticate = (Button) ((Object) root.findViewById(R.id.plugpag_authentication_authenticate));
    this.mTxtForgotPassword = (TextView) root.findViewById(R.id.plugpag_authentication_forgot_password);
  }

  private void setupEventListeners() {
    this.mEdtPassword.setOnEditorActionListener(this);
    this.mBtnAuthenticate.setOnClickListener(this);
    this.mBtnTogglePassword.setOnClickListener(this);
    this.mTxtForgotPassword.setOnClickListener(this);
    this.mEdtPassword.addTextChangedListener(this);
  }

  public void onClick(View view) {
    int id2 = -1;
    int validationResult = 0;
    if (view != null) {
      id2 = view.getId();
      if (id2 == R.id.plugpag_authentication_toggle_password) {
        this.togglePasswordVisibility();
      } else if (id2 == R.id.plugpag_authentication_forgot_password) {
        this.showForgotPasswordPage();
      } else if (id2 == R.id.plugpag_authentication_authenticate) {
        validationResult = this.validateAuthenticationData();
        if (validationResult == 0) {
          this.startAuthenticationTask(this.mEdtEmail.getText().toString(), this.mEdtPassword.getText().toString());
        } else {
          this.handleValidationResult(validationResult);
        }
      }
    }
  }

  public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
    this.mBtnAuthenticate.performClick();
    return true;
  }

  private int validateAuthenticationData() {
    int result = 0;
    this.clearErrors();
    if (this.mEdtEmail.length() <= 0) {
      result |= 2;
    } else if (!this.validateEmailFormat(this.mEdtEmail.getText().toString())) {
      result |= 8;
    } else if (this.mEdtPassword.length() <= 0) {
      result |= 4;
    }
    return result;
  }

  private boolean validateEmailFormat(@NonNull String email) {
    return !TextUtils.isEmpty(email) && email.matches(REGEX_EMAIL_FORMAT);
  }

  private void handleValidationResult(int validationResult) {
    ArrayList<TextInputLayout> wrappersWithError = null;
    ArrayList<String> errorMessages = null;
    AppCompatEditText viewToFocus = null;
    this.clearErrors();
    wrappersWithError = new ArrayList<TextInputLayout>();
    errorMessages = new ArrayList<String>();
    if (validationResult != 0) {
      if (this.hasValue(validationResult, 1)) {
        wrappersWithError.add(this.mWrapperEmail);
        errorMessages.add(" ");
        wrappersWithError.add(this.mWrapperPassword);
        errorMessages.add(this.getString(R.string.plugpag_authentication_error_invalid_email_or_password));
        viewToFocus = this.mEdtPassword;
      } else if (this.hasValue(validationResult, 2)) {
        wrappersWithError.add(this.mWrapperEmail);
        errorMessages.add(this.getString(R.string.plugpag_authentication_error_missing_email));
        viewToFocus = this.mEdtEmail;
      } else if (this.hasValue(validationResult, 8)) {
        wrappersWithError.add(this.mWrapperEmail);
        errorMessages.add(this.getString(R.string.plugpag_authentication_error_email_format));
        viewToFocus = this.mEdtEmail;
      } else if (this.hasValue(validationResult, 4)) {
        wrappersWithError.add(this.mWrapperPassword);
        errorMessages.add(this.getString(R.string.plugpag_authentication_error_missing_password));
        viewToFocus = this.mEdtPassword;
      }
      for (int i = 0; i < wrappersWithError.size(); ++i) {
        ((TextInputLayout) wrappersWithError.get(i))
            .setHintTextAppearance(R.style.PlugPagAuthenticationTextInputLayoutHintError);
        if (errorMessages.size() <= i || errorMessages.get(i) == null)
          continue;
        ((TextInputLayout) wrappersWithError.get(i)).setError((CharSequence) errorMessages.get(i));
      }
      if (viewToFocus != null) {
        viewToFocus.requestFocus();
      }
    }
  }

  private boolean hasValue(int error, int value) {
    return (error & value) != 0;
  }

  private void setViewsEnabled(boolean enabled) {
    this.mEdtEmail.setEnabled(enabled);
    this.mEdtEmail.setFocusable(enabled);
    this.mEdtEmail.setFocusableInTouchMode(enabled);
    this.mEdtPassword.setEnabled(enabled);
    this.mEdtPassword.setFocusable(enabled);
    this.mEdtPassword.setFocusableInTouchMode(enabled);
    this.mBtnAuthenticate.setEnabled(enabled);
    this.mTxtForgotPassword.setClickable(enabled);
  }

  private void disableViews() {
    this.setViewsEnabled(false);
  }

  private void enableViews() {
    this.setViewsEnabled(true);
  }

  private void clearErrors() {
    this.mWrapperEmail.setError(null);
    this.mWrapperEmail.setHintTextAppearance(R.style.PlugPagAuthenticationTextInputLayoutHintNormal);
    this.mWrapperPassword.setError(null);
    this.mWrapperPassword.setHintTextAppearance(R.style.PlugPagAuthenticationTextInputLayoutHintNormal);
  }

  private native PlugPagAuthenticationResult authenticate(PlugPagAuthenticationRequest var1);

  private void startAuthenticationTask(@NonNull String user, @NonNull String password) {
    PlugPagAuthenticationRequest authenticationRequest = null;
    DeviceInfo deviceInfo = null;
    deviceInfo = new DeviceInfo(this.getContext());
    authenticationRequest = new PlugPagAuthenticationRequest(user, password, deviceInfo.getDeviceId(),
        deviceInfo.getDeviceModel(), PlugPag.getApplicationCode(), PlugPag.getLibVersion(), deviceInfo.getOs(),
        deviceInfo.getOsVersion(), deviceInfo.getImei());
    if (this.getActivity() instanceof PlugPagActivity) {
      new AuthenticationTask(this).execute(new PlugPagAuthenticationRequest[] { authenticationRequest });
    }
  }

  @Override
  public void onPreAuthenticate() {
    this.clearErrors();
    this.disableViews();
  }

  @Override
  public void onPostAuthenticate(@Nullable PlugPagAuthenticationResult authenticationResult) {
    if (this.isVisible() && this.isResumed()) {
      if (authenticationResult != null && authenticationResult.isAuthenticated()) {
        Intent intent = new Intent("br.com.uol.pagseguro.plugpag.AUTHENTICATION_RESULT");
        intent.putExtra("PLUGPAG_RESULT", 0);
        this.getActivity().sendBroadcast(intent);
        this.getActivity().finish();
      } else {
        this.enableViews();
        if (authenticationResult == null) {
          this.showSnackbar(this.getString(R.string.plugpag_authentication_snackbar_error_title),
              this.getString(R.string.plugpag_authentication_snackbar_error_no_network));
        } else {
          this.handleValidationResult(1);
        }
      }
    }
  }

  @Override
  public void onDestroy() {
    this.getActivity().sendBroadcast(new Intent("br.com.uol.pagseguro.plugpag.AUTHENTICATION_RESULT"));
    super.onDestroy();
  }

  private void showForgotPasswordPage() {
    Intent intent = null;
    intent = this.createForgotPasswordIntent();
    if (this.hasRespondingActivities(this.getContext(), intent)) {
      this.getContext().startActivity(intent);
    }
  }

  private Intent createForgotPasswordIntent() {
    Intent intent = null;
    intent = new Intent("android.intent.action.VIEW");
    intent.setData(Uri.parse("https://sac.uol.com.br/pagseguro/#/recuperarsenha"));
    return intent;
  }

  private boolean hasRespondingActivities(@NonNull Context context, @NonNull Intent intent) {
    return context.getPackageManager().queryIntentActivities(intent, 0).size() > 0;
  }

  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
  }

  public void onTextChanged(CharSequence s, int start, int before, int count) {
  }

  public void afterTextChanged(Editable s) {
    if (s.length() > 0) {
      this.mBtnTogglePassword.setVisibility(0);
    } else {
      this.mBtnTogglePassword.setVisibility(4);
    }
  }

  private void togglePasswordVisibility() {
    int startIndex = 0;
    int endIndex = 0;
    startIndex = this.mEdtPassword.getSelectionStart();
    endIndex = this.mEdtPassword.getSelectionEnd();
    if (this.mBtnTogglePassword.getText()
        .equals(this.getString(R.string.plugpag_authentication_button_show_password))) {
      this.mBtnTogglePassword.setText(R.string.plugpag_authentication_button_hide_password);
      this.mEdtPassword.setTransformationMethod(null);
    } else {
      this.mBtnTogglePassword.setText(R.string.plugpag_authentication_button_show_password);
      this.mEdtPassword.setTransformationMethod(PASSWORD_TRANSFORMATION_METHOD);
    }
    this.mEdtPassword.setSelection(startIndex, endIndex);
    this.updatePasswordPadding();
  }

  private void updatePasswordPadding() {
    int buttonWidth = 0;
    if (this.mEdtPassword.getMeasuredWidth() <= 0) {
      this.mEdtPassword.measure(0, 0);
    }
    if (this.mBtnTogglePassword.getMeasuredWidth() <= 0) {
      this.mBtnTogglePassword.measure(0, 0);
    }
    buttonWidth = this.mBtnTogglePassword.getMeasuredWidth() + this.mBtnTogglePassword.getPaddingLeft()
        + this.mBtnTogglePassword.getPaddingRight();
    this.mEdtPassword.setPadding(this.mEdtPassword.getPaddingLeft(), this.mEdtPassword.getPaddingTop(), buttonWidth,
        this.mEdtPassword.getPaddingBottom());
  }

  private void showSnackbar(@NonNull String title, @NonNull String message) {
    Intent intent = null;
    if (this.mInteractionListener != null) {
      intent = new Intent("ACTION_SHOW_SNACKBAR");
      intent.putExtra("KEY_TITLE", title);
      intent.putExtra("KEY_MESSAGE", message);
      this.mInteractionListener.interact(intent);
    }
  }

  private class AuthenticationTask
      extends AsyncTask<PlugPagAuthenticationRequest, Void, PlugPagAuthenticationResult> {
    private PlugPagAuthenticationCallback mCallback = null;

    private AuthenticationTask(PlugPagAuthenticationCallback callback) {
      this.mCallback = callback;
    }

    protected void onPreExecute() {
      super.onPreExecute();
      if (this.mCallback != null) {
        this.mCallback.onPreAuthenticate();
      }
    }

    protected PlugPagAuthenticationResult doInBackground(PlugPagAuthenticationRequest... args) {
      PlugPagAuthenticationResult authenticationResult = null;
      if (args != null && args.length > 0 && args[0] != null) {
        authenticationResult = PlugPagAuthenticationFragment.this.authenticate(args[0]);
      }
      return authenticationResult;
    }

    protected void onPostExecute(PlugPagAuthenticationResult authenticationResult) {
      super.onPostExecute((Object) authenticationResult);
      if (this.mCallback != null) {
        this.mCallback.onPostAuthenticate(authenticationResult);
        this.mCallback = null;
      }
    }
  }
}
