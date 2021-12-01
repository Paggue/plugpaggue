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
import br.com.uol.pagseguro.plugpag.PlugPagFragment;
import br.com.uol.pagseguro.plugpag.PlugPagFragmentInteractionListener;
import br.com.uol.pagseguro.plugpag.R;
import java.util.ArrayList;
import java.util.List;

public class PlugPagAuthenticationFragment extends PlugPagFragment implements View.OnClickListener, PlugPagAuthenticationCallback, TextView.OnEditorActionListener, TextWatcher {
  private static final int OK = 0;

  private static final int ERROR_NOT_AUTHENTICATED = 1;

  private static final int ERROR_EMPTY_EMAIL = 2;

  private static final int ERROR_EMPTY_PASSWORD = 4;

  private static final int ERROR_INVALID_EMAIL_FORMAT = 8;

  private static final String FORGOT_PASSWORD_URL = "https://sac.uol.com.br/pagseguro/#/recuperarsenha";

  private static final String REGEX_EMAIL_FORMAT = "^([a-zA-Z_]+)(\\.?[a-zA-Z0-9_]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$";

  private static final TransformationMethod PASSWORD_TRANSFORMATION_METHOD = (TransformationMethod)new PasswordTransformationMethod();

  private TextInputLayout mWrapperEmail = null;

  private AppCompatEditText mEdtEmail = null;

  private TextInputLayout mWrapperPassword = null;

  private AppCompatEditText mEdtPassword = null;

  private Button mBtnTogglePassword = null;

  private Button mBtnAuthenticate = null;

  private TextView mTxtForgotPassword = null;

  private PlugPagFragmentInteractionListener mInteractionListener = null;

  @Nullable
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = null;
    super.onCreateView(inflater, container, savedInstanceState);
    view = inflater.inflate(R.layout.fragment_plugpag_authentication, container, false);
    getActivity().setResult(-1);
    return view;
  }

  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupViewReferences();
    setupEventListeners();
  }

  public void onResume() {
    super.onResume();
    updatePasswordPadding();
  }

  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof PlugPagFragmentInteractionListener)
      this.mInteractionListener = (PlugPagFragmentInteractionListener)context;
  }

  public void onDetach() {
    super.onDetach();
    this.mInteractionListener = null;
  }

  private void setupViewReferences() {
    View root = null;
    root = getView();
    this.mWrapperEmail = (TextInputLayout)root.findViewById(R.id.plugpag_authentication_wrapper_email);
    this.mEdtEmail = (AppCompatEditText)root.findViewById(R.id.plugpag_authentication_email);
    this.mWrapperPassword = (TextInputLayout)root.findViewById(R.id.plugpag_authentication_wrapper_password);
    this.mEdtPassword = (AppCompatEditText)root.findViewById(R.id.plugpag_authentication_password);
    this.mBtnTogglePassword = (Button)root.findViewById(R.id.plugpag_authentication_toggle_password);
    this.mBtnAuthenticate = (Button)root.findViewById(R.id.plugpag_authentication_authenticate);
    this.mTxtForgotPassword = (TextView)root.findViewById(R.id.plugpag_authentication_forgot_password);
  }

  private void setupEventListeners() {
    this.mEdtPassword.setOnEditorActionListener(this);
    this.mBtnAuthenticate.setOnClickListener(this);
    this.mBtnTogglePassword.setOnClickListener(this);
    this.mTxtForgotPassword.setOnClickListener(this);
    this.mEdtPassword.addTextChangedListener(this);
  }

  public void onClick(View view) {
    int id = -1;
    int validationResult = 0;
    if (view != null) {
      id = view.getId();
      if (id == R.id.plugpag_authentication_toggle_password) {
        togglePasswordVisibility();
      } else if (id == R.id.plugpag_authentication_forgot_password) {
        showForgotPasswordPage();
      } else if (id == R.id.plugpag_authentication_authenticate) {
        validationResult = validateAuthenticationData();
        if (validationResult == 0) {
          startAuthenticationTask(this.mEdtEmail
              .getText().toString(), this.mEdtPassword
              .getText().toString());
        } else {
          handleValidationResult(validationResult);
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
    clearErrors();
    if (this.mEdtEmail.length() <= 0) {
      result |= 0x2;
    } else if (!validateEmailFormat(this.mEdtEmail.getText().toString())) {
      result |= 0x8;
    } else if (this.mEdtPassword.length() <= 0) {
      result |= 0x4;
    }
    return result;
  }

  private boolean validateEmailFormat(@NonNull String email) {
    return (!TextUtils.isEmpty(email) && email.matches("^([a-zA-Z_]+)(\\.?[a-zA-Z0-9_]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$"));
  }

  private void handleValidationResult(int validationResult) {
    List<TextInputLayout> wrappersWithError = null;
    List<String> errorMessages = null;
    AppCompatEditText viewToFocus = null;
    clearErrors();
    wrappersWithError = new ArrayList<>();
    errorMessages = new ArrayList<>();
    if (validationResult != 0) {
      if (hasValue(validationResult, 1)) {
        wrappersWithError.add(this.mWrapperEmail);
        errorMessages.add(" ");
        wrappersWithError.add(this.mWrapperPassword);
        errorMessages.add(getString(R.string.plugpag_authentication_error_invalid_email_or_password));
        viewToFocus = this.mEdtPassword;
      } else if (hasValue(validationResult, 2)) {
        wrappersWithError.add(this.mWrapperEmail);
        errorMessages.add(getString(R.string.plugpag_authentication_error_missing_email));
        viewToFocus = this.mEdtEmail;
      } else if (hasValue(validationResult, 8)) {
        wrappersWithError.add(this.mWrapperEmail);
        errorMessages.add(getString(R.string.plugpag_authentication_error_email_format));
        viewToFocus = this.mEdtEmail;
      } else if (hasValue(validationResult, 4)) {
        wrappersWithError.add(this.mWrapperPassword);
        errorMessages.add(getString(R.string.plugpag_authentication_error_missing_password));
        viewToFocus = this.mEdtPassword;
      }
      for (int i = 0; i < wrappersWithError.size(); i++) {
        ((TextInputLayout)wrappersWithError.get(i)).setHintTextAppearance(R.style.PlugPagAuthenticationTextInputLayoutHintError);
        if (errorMessages.size() > i && errorMessages.get(i) != null)
          ((TextInputLayout)wrappersWithError.get(i)).setError(errorMessages.get(i));
      }
      if (viewToFocus != null)
        viewToFocus.requestFocus();
    }
  }

  private boolean hasValue(int error, int value) {
    return ((error & value) != 0);
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
    setViewsEnabled(false);
  }

  private void enableViews() {
    setViewsEnabled(true);
  }

  private void clearErrors() {
    this.mWrapperEmail.setError(null);
    this.mWrapperEmail.setHintTextAppearance(R.style.PlugPagAuthenticationTextInputLayoutHintNormal);
    this.mWrapperPassword.setError(null);
    this.mWrapperPassword.setHintTextAppearance(R.style.PlugPagAuthenticationTextInputLayoutHintNormal);
  }

  private void startAuthenticationTask(@NonNull String user, @NonNull String password) {
    PlugPagAuthenticationRequest authenticationRequest = null;
    DeviceInfo deviceInfo = null;
    deviceInfo = new DeviceInfo(getContext());
    authenticationRequest = new PlugPagAuthenticationRequest(user, password, deviceInfo.getDeviceId(), deviceInfo.getDeviceModel(), PlugPag.getApplicationCode(), PlugPag.getLibVersion(), deviceInfo.getOs(), deviceInfo.getOsVersion(), deviceInfo.getImei());
    if (getActivity() instanceof br.com.uol.pagseguro.plugpag.PlugPagActivity)
      (new AuthenticationTask(this)).execute((Object[])new PlugPagAuthenticationRequest[] { authenticationRequest });
  }

  public void onPreAuthenticate() {
    clearErrors();
    disableViews();
  }

  public void onPostAuthenticate(@Nullable PlugPagAuthenticationResult authenticationResult) {
    if (isVisible() && isResumed())
      if (authenticationResult != null && authenticationResult.isAuthenticated()) {
        Intent intent = new Intent("br.com.uol.pagseguro.plugpag.AUTHENTICATION_RESULT");
        intent.putExtra("PLUGPAG_RESULT", 0);
        getActivity().sendBroadcast(intent);
        getActivity().finish();
      } else {
        enableViews();
        if (authenticationResult == null) {
          showSnackbar(
              getString(R.string.plugpag_authentication_snackbar_error_title),
              getString(R.string.plugpag_authentication_snackbar_error_no_network));
        } else {
          handleValidationResult(1);
        }
      }
  }

  public void onDestroy() {
    getActivity().sendBroadcast(new Intent("br.com.uol.pagseguro.plugpag.AUTHENTICATION_RESULT"));
    super.onDestroy();
  }

  private void showForgotPasswordPage() {
    Intent intent = null;
    intent = createForgotPasswordIntent();
    if (hasRespondingActivities(getContext(), intent))
      getContext().startActivity(intent);
  }

  private Intent createForgotPasswordIntent() {
    Intent intent = null;
    intent = new Intent("android.intent.action.VIEW");
    intent.setData(Uri.parse("https://sac.uol.com.br/pagseguro/#/recuperarsenha"));
    return intent;
  }

  private boolean hasRespondingActivities(@NonNull Context context, @NonNull Intent intent) {
    return (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0);
  }

  public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

  public void onTextChanged(CharSequence s, int start, int before, int count) {}

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
      .equals(getString(R.string.plugpag_authentication_button_show_password))) {
      this.mBtnTogglePassword.setText(R.string.plugpag_authentication_button_hide_password);
      this.mEdtPassword.setTransformationMethod(null);
    } else {
      this.mBtnTogglePassword.setText(R.string.plugpag_authentication_button_show_password);
      this.mEdtPassword.setTransformationMethod(PASSWORD_TRANSFORMATION_METHOD);
    }
    this.mEdtPassword.setSelection(startIndex, endIndex);
    updatePasswordPadding();
  }

  private void updatePasswordPadding() {
    int buttonWidth = 0;
    if (this.mEdtPassword.getMeasuredWidth() <= 0)
      this.mEdtPassword.measure(0, 0);
    if (this.mBtnTogglePassword.getMeasuredWidth() <= 0)
      this.mBtnTogglePassword.measure(0, 0);
    buttonWidth = this.mBtnTogglePassword.getMeasuredWidth() + this.mBtnTogglePassword.getPaddingLeft() + this.mBtnTogglePassword.getPaddingRight();
    this.mEdtPassword.setPadding(this.mEdtPassword
        .getPaddingLeft(), this.mEdtPassword
        .getPaddingTop(), buttonWidth, this.mEdtPassword

        .getPaddingBottom());
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

  private native PlugPagAuthenticationResult authenticate(PlugPagAuthenticationRequest paramPlugPagAuthenticationRequest);

  private class AuthenticationTask extends AsyncTask<PlugPagAuthenticationRequest, Void, PlugPagAuthenticationResult> {
    private PlugPagAuthenticationCallback mCallback = null;

    private AuthenticationTask(PlugPagAuthenticationCallback callback) {
      this.mCallback = callback;
    }

    protected void onPreExecute() {
      super.onPreExecute();
      if (this.mCallback != null)
        this.mCallback.onPreAuthenticate();
    }

    protected PlugPagAuthenticationResult doInBackground(PlugPagAuthenticationRequest... args) {
      PlugPagAuthenticationResult authenticationResult = null;
      if (args != null && args.length > 0 && args[0] != null)
        authenticationResult = PlugPagAuthenticationFragment.this.authenticate(args[0]);
      return authenticationResult;
    }

    protected void onPostExecute(PlugPagAuthenticationResult authenticationResult) {
      super.onPostExecute(authenticationResult);
      if (this.mCallback != null) {
        this.mCallback.onPostAuthenticate(authenticationResult);
        this.mCallback = null;
      }
    }
  }
}
