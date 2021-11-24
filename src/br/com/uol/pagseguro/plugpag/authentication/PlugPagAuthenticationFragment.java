/*     */ package br.com.uol.pagseguro.plugpag.authentication;
/*     */
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.net.Uri;
/*     */ import android.os.AsyncTask;
/*     */ import android.os.Bundle;
/*     */ import android.support.annotation.NonNull;
/*     */ import android.support.annotation.Nullable;
/*     */ import android.support.design.widget.TextInputLayout;
/*     */ import android.support.v7.widget.AppCompatEditText;
/*     */ import android.text.Editable;
/*     */ import android.text.TextUtils;
/*     */ import android.text.TextWatcher;
/*     */ import android.text.method.PasswordTransformationMethod;
/*     */ import android.text.method.TransformationMethod;
/*     */ import android.view.KeyEvent;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.Button;
/*     */ import android.widget.TextView;
/*     */ import br.com.uol.pagseguro.plugpag.DeviceInfo;
/*     */ import br.com.uol.pagseguro.plugpag.PlugPag;
/*     */ import br.com.uol.pagseguro.plugpag.PlugPagFragment;
/*     */ import br.com.uol.pagseguro.plugpag.PlugPagFragmentInteractionListener;
/*     */ import br.com.uol.pagseguro.plugpag.R;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class PlugPagAuthenticationFragment
/*     */   extends PlugPagFragment
/*     */   implements View.OnClickListener, PlugPagAuthenticationCallback, TextView.OnEditorActionListener, TextWatcher
/*     */ {
/*     */   private static final int OK = 0;
/*     */   private static final int ERROR_NOT_AUTHENTICATED = 1;
/*     */   private static final int ERROR_EMPTY_EMAIL = 2;
/*     */   private static final int ERROR_EMPTY_PASSWORD = 4;
/*     */   private static final int ERROR_INVALID_EMAIL_FORMAT = 8;
/*     */   private static final String FORGOT_PASSWORD_URL = "https://sac.uol.com.br/pagseguro/#/recuperarsenha";
/*     */   private static final String REGEX_EMAIL_FORMAT = "^([a-zA-Z_]+)(\\.?[a-zA-Z0-9_]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$";
/*  55 */   private static final TransformationMethod PASSWORD_TRANSFORMATION_METHOD = (TransformationMethod)new PasswordTransformationMethod();
/*     */
/*     */
/*     */
/*     */
/*     */
/*  61 */   private TextInputLayout mWrapperEmail = null;
/*  62 */   private AppCompatEditText mEdtEmail = null;
/*  63 */   private TextInputLayout mWrapperPassword = null;
/*  64 */   private AppCompatEditText mEdtPassword = null;
/*  65 */   private Button mBtnTogglePassword = null;
/*  66 */   private Button mBtnAuthenticate = null;
/*  67 */   private TextView mTxtForgotPassword = null;
/*     */
/*  69 */   private PlugPagFragmentInteractionListener mInteractionListener = null;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   @Nullable
/*     */   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
/*  80 */     View view = null;
/*     */
/*  82 */     super.onCreateView(inflater, container, savedInstanceState);
/*  83 */     view = inflater.inflate(R.layout.fragment_plugpag_authentication, container, false);
/*     */
/*     */
/*     */
/*     */
/*  88 */     getActivity().setResult(-1);
/*     */
/*  90 */     return view;
/*     */   }
/*     */
/*     */
/*     */   public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
/*  95 */     super.onViewCreated(view, savedInstanceState);
/*  96 */     setupViewReferences();
/*  97 */     setupEventListeners();
/*     */   }
/*     */
/*     */
/*     */   public void onResume() {
/* 102 */     super.onResume();
/* 103 */     updatePasswordPadding();
/*     */   }
/*     */
/*     */
/*     */   public void onAttach(Context context) {
/* 108 */     super.onAttach(context);
/*     */
/* 110 */     if (context instanceof PlugPagFragmentInteractionListener) {
/* 111 */       this.mInteractionListener = (PlugPagFragmentInteractionListener)context;
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public void onDetach() {
/* 117 */     super.onDetach();
/* 118 */     this.mInteractionListener = null;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private void setupViewReferences() {
/* 129 */     View root = null;
/*     */
/* 131 */     root = getView();
/* 132 */     this.mWrapperEmail = (TextInputLayout)root.findViewById(R.id.plugpag_authentication_wrapper_email);
/* 133 */     this.mEdtEmail = (AppCompatEditText)root.findViewById(R.id.plugpag_authentication_email);
/* 134 */     this.mWrapperPassword = (TextInputLayout)root.findViewById(R.id.plugpag_authentication_wrapper_password);
/* 135 */     this.mEdtPassword = (AppCompatEditText)root.findViewById(R.id.plugpag_authentication_password);
/* 136 */     this.mBtnTogglePassword = (Button)root.findViewById(R.id.plugpag_authentication_toggle_password);
/* 137 */     this.mBtnAuthenticate = (Button)root.findViewById(R.id.plugpag_authentication_authenticate);
/* 138 */     this.mTxtForgotPassword = (TextView)root.findViewById(R.id.plugpag_authentication_forgot_password);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   private void setupEventListeners() {
/* 145 */     this.mEdtPassword.setOnEditorActionListener(this);
/* 146 */     this.mBtnAuthenticate.setOnClickListener(this);
/* 147 */     this.mBtnTogglePassword.setOnClickListener(this);
/* 148 */     this.mTxtForgotPassword.setOnClickListener(this);
/* 149 */     this.mEdtPassword.addTextChangedListener(this);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public void onClick(View view) {
/* 165 */     int id = -1;
/* 166 */     int validationResult = 0;
/*     */
/* 168 */     if (view != null) {
/* 169 */       id = view.getId();
/*     */
/* 171 */       if (id == R.id.plugpag_authentication_toggle_password) {
/* 172 */         togglePasswordVisibility();
/* 173 */       } else if (id == R.id.plugpag_authentication_forgot_password) {
/* 174 */         showForgotPasswordPage();
/* 175 */       } else if (id == R.id.plugpag_authentication_authenticate) {
/* 176 */         validationResult = validateAuthenticationData();
/*     */
/* 178 */         if (validationResult == 0) {
/* 179 */           startAuthenticationTask(this.mEdtEmail
/* 180 */               .getText().toString(), this.mEdtPassword
/* 181 */               .getText().toString());
/*     */         } else {
/* 183 */           handleValidationResult(validationResult);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
/* 195 */     this.mBtnAuthenticate.performClick();
/*     */
/* 197 */     return true;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private int validateAuthenticationData() {
/* 210 */     int result = 0;
/*     */
/* 212 */     clearErrors();
/*     */
/* 214 */     if (this.mEdtEmail.length() <= 0) {
/*     */
/* 216 */       result |= 0x2;
/* 217 */     } else if (!validateEmailFormat(this.mEdtEmail.getText().toString())) {
/*     */
/* 219 */       result |= 0x8;
/* 220 */     } else if (this.mEdtPassword.length() <= 0) {
/*     */
/* 222 */       result |= 0x4;
/*     */     }
/*     */
/* 225 */     return result;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private boolean validateEmailFormat(@NonNull String email) {
/* 235 */     return (!TextUtils.isEmpty(email) && email.matches("^([a-zA-Z_]+)(\\.?[a-zA-Z0-9_]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)+$"));
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private void handleValidationResult(int validationResult) {
/* 248 */     List<TextInputLayout> wrappersWithError = null;
/* 249 */     List<String> errorMessages = null;
/* 250 */     AppCompatEditText viewToFocus = null;
/*     */
/* 252 */     clearErrors();
/* 253 */     wrappersWithError = new ArrayList<>();
/* 254 */     errorMessages = new ArrayList<>();
/*     */
/* 256 */     if (validationResult != 0) {
/* 257 */       if (hasValue(validationResult, 1)) {
/*     */
/*     */
/* 260 */         wrappersWithError.add(this.mWrapperEmail);
/* 261 */         errorMessages.add(" ");
/*     */
/* 263 */         wrappersWithError.add(this.mWrapperPassword);
/* 264 */         errorMessages.add(getString(R.string.plugpag_authentication_error_invalid_email_or_password));
/*     */
/* 266 */         viewToFocus = this.mEdtPassword;
/* 267 */       } else if (hasValue(validationResult, 2)) {
/*     */
/*     */
/* 270 */         wrappersWithError.add(this.mWrapperEmail);
/* 271 */         errorMessages.add(getString(R.string.plugpag_authentication_error_missing_email));
/* 272 */         viewToFocus = this.mEdtEmail;
/* 273 */       } else if (hasValue(validationResult, 8)) {
/*     */
/* 275 */         wrappersWithError.add(this.mWrapperEmail);
/* 276 */         errorMessages.add(getString(R.string.plugpag_authentication_error_email_format));
/* 277 */         viewToFocus = this.mEdtEmail;
/* 278 */       } else if (hasValue(validationResult, 4)) {
/*     */
/*     */
/* 281 */         wrappersWithError.add(this.mWrapperPassword);
/* 282 */         errorMessages.add(getString(R.string.plugpag_authentication_error_missing_password));
/* 283 */         viewToFocus = this.mEdtPassword;
/*     */       }
/*     */
/* 286 */       for (int i = 0; i < wrappersWithError.size(); i++) {
/* 287 */         ((TextInputLayout)wrappersWithError.get(i)).setHintTextAppearance(R.style.PlugPagAuthenticationTextInputLayoutHintError);
/*     */
/* 289 */         if (errorMessages.size() > i && errorMessages.get(i) != null) {
/* 290 */           ((TextInputLayout)wrappersWithError.get(i)).setError(errorMessages.get(i));
/*     */         }
/*     */       }
/*     */
/* 294 */       if (viewToFocus != null) {
/* 295 */         viewToFocus.requestFocus();
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private boolean hasValue(int error, int value) {
/* 308 */     return ((error & value) != 0);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private void setViewsEnabled(boolean enabled) {
/* 321 */     this.mEdtEmail.setEnabled(enabled);
/* 322 */     this.mEdtEmail.setFocusable(enabled);
/* 323 */     this.mEdtEmail.setFocusableInTouchMode(enabled);
/*     */
/* 325 */     this.mEdtPassword.setEnabled(enabled);
/* 326 */     this.mEdtPassword.setFocusable(enabled);
/* 327 */     this.mEdtPassword.setFocusableInTouchMode(enabled);
/*     */
/* 329 */     this.mBtnAuthenticate.setEnabled(enabled);
/*     */
/* 331 */     this.mTxtForgotPassword.setClickable(enabled);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   private void disableViews() {
/* 338 */     setViewsEnabled(false);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   private void enableViews() {
/* 345 */     setViewsEnabled(true);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   private void clearErrors() {
/* 352 */     this.mWrapperEmail.setError(null);
/* 353 */     this.mWrapperEmail.setHintTextAppearance(R.style.PlugPagAuthenticationTextInputLayoutHintNormal);
/* 354 */     this.mWrapperPassword.setError(null);
/* 355 */     this.mWrapperPassword.setHintTextAppearance(R.style.PlugPagAuthenticationTextInputLayoutHintNormal);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public void startAuthenticationTask(@NonNull String user, @NonNull String password) {
/* 377 */     PlugPagAuthenticationRequest authenticationRequest = null;
/* 378 */     DeviceInfo deviceInfo = null;
/*     */
/* 380 */     deviceInfo = new DeviceInfo(getContext());
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/* 390 */     authenticationRequest = new PlugPagAuthenticationRequest(user, password, deviceInfo.getDeviceId(), deviceInfo.getDeviceModel(), PlugPag.getApplicationCode(), PlugPag.getLibVersion(), deviceInfo.getOs(), deviceInfo.getOsVersion(), deviceInfo.getImei());
/*     */
/* 392 */     if (getActivity() instanceof br.com.uol.pagseguro.plugpag.PlugPagActivity) {
/* 393 */       (new AuthenticationTask(this)).execute((Object[])new PlugPagAuthenticationRequest[] { authenticationRequest });
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public void onPreAuthenticate() {
/* 399 */     clearErrors();
/* 400 */     disableViews();
/*     */   }
/*     */
/*     */
/*     */   public void onPostAuthenticate(@Nullable PlugPagAuthenticationResult authenticationResult) {
/* 405 */     if (isVisible() && isResumed()) {
/* 406 */       if (authenticationResult != null && authenticationResult.isAuthenticated()) {
/* 407 */         Intent intent = new Intent("br.com.uol.pagseguro.plugpag.AUTHENTICATION_RESULT");
/* 408 */         intent.putExtra("PLUGPAG_RESULT", 0);
/* 409 */         getActivity().sendBroadcast(intent);
/* 410 */         getActivity().finish();
/*     */       } else {
/* 412 */         enableViews();
/*     */
/* 414 */         if (authenticationResult == null) {
/*     */
/* 416 */           showSnackbar(
/* 417 */               getString(R.string.plugpag_authentication_snackbar_error_title),
/* 418 */               getString(R.string.plugpag_authentication_snackbar_error_no_network));
/*     */         } else {
/*     */
/* 421 */           handleValidationResult(1);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   public void onDestroy() {
/* 429 */     getActivity().sendBroadcast(new Intent("br.com.uol.pagseguro.plugpag.AUTHENTICATION_RESULT"));
/* 430 */     super.onDestroy();
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private void showForgotPasswordPage() {
/* 440 */     Intent intent = null;
/*     */
/* 442 */     intent = createForgotPasswordIntent();
/*     */
/* 444 */     if (hasRespondingActivities(getContext(), intent)) {
/* 445 */       getContext().startActivity(intent);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private Intent createForgotPasswordIntent() {
/* 455 */     Intent intent = null;
/*     */
/* 457 */     intent = new Intent("android.intent.action.VIEW");
/* 458 */     intent.setData(Uri.parse("https://sac.uol.com.br/pagseguro/#/recuperarsenha"));
/*     */
/* 460 */     return intent;
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private boolean hasRespondingActivities(@NonNull Context context, @NonNull Intent intent) {
/* 471 */     return (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0);
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   public void onTextChanged(CharSequence s, int start, int before, int count) {}
/*     */
/*     */
/*     */
/*     */
/*     */   public void afterTextChanged(Editable s) {
/* 490 */     if (s.length() > 0) {
/* 491 */       this.mBtnTogglePassword.setVisibility(0);
/*     */     } else {
/* 493 */       this.mBtnTogglePassword.setVisibility(4);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private void togglePasswordVisibility() {
/* 505 */     int startIndex = 0;
/* 506 */     int endIndex = 0;
/*     */
/*     */
/* 509 */     startIndex = this.mEdtPassword.getSelectionStart();
/* 510 */     endIndex = this.mEdtPassword.getSelectionEnd();
/*     */
/*     */
/* 513 */     if (this.mBtnTogglePassword.getText()
/* 514 */       .equals(getString(R.string.plugpag_authentication_button_show_password))) {
/* 515 */       this.mBtnTogglePassword.setText(R.string.plugpag_authentication_button_hide_password);
/* 516 */       this.mEdtPassword.setTransformationMethod(null);
/*     */     } else {
/* 518 */       this.mBtnTogglePassword.setText(R.string.plugpag_authentication_button_show_password);
/* 519 */       this.mEdtPassword.setTransformationMethod(PASSWORD_TRANSFORMATION_METHOD);
/*     */     }
/*     */
/*     */
/*     */
/* 524 */     this.mEdtPassword.setSelection(startIndex, endIndex);
/*     */
/*     */
/* 527 */     updatePasswordPadding();
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */   private void updatePasswordPadding() {
/* 534 */     int buttonWidth = 0;
/*     */
/* 536 */     if (this.mEdtPassword.getMeasuredWidth() <= 0) {
/* 537 */       this.mEdtPassword.measure(0, 0);
/*     */     }
/*     */
/* 540 */     if (this.mBtnTogglePassword.getMeasuredWidth() <= 0) {
/* 541 */       this.mBtnTogglePassword.measure(0, 0);
/*     */     }
/*     */
/*     */
/*     */
/* 546 */     buttonWidth = this.mBtnTogglePassword.getMeasuredWidth() + this.mBtnTogglePassword.getPaddingLeft() + this.mBtnTogglePassword.getPaddingRight();
/*     */
/* 548 */     this.mEdtPassword.setPadding(this.mEdtPassword
/* 549 */         .getPaddingLeft(), this.mEdtPassword
/* 550 */         .getPaddingTop(), buttonWidth, this.mEdtPassword
/*     */
/* 552 */         .getPaddingBottom());
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private void showSnackbar(@NonNull String title, @NonNull String message) {
/* 562 */     Intent intent = null;
/*     */
/* 564 */     if (this.mInteractionListener != null) {
/* 565 */       intent = new Intent("ACTION_SHOW_SNACKBAR");
/* 566 */       intent.putExtra("KEY_TITLE", title);
/* 567 */       intent.putExtra("KEY_MESSAGE", message);
/* 568 */       this.mInteractionListener.interact(intent);
/*     */     }
/*     */   }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private native PlugPagAuthenticationResult authenticate(PlugPagAuthenticationRequest paramPlugPagAuthenticationRequest);
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */   private class AuthenticationTask
/*     */     extends AsyncTask<PlugPagAuthenticationRequest, Void, PlugPagAuthenticationResult>
/*     */   {
/* 586 */     private PlugPagAuthenticationCallback mCallback = null;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */     private AuthenticationTask(PlugPagAuthenticationCallback callback) {
/* 598 */       this.mCallback = callback;
/*     */     }
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */     protected void onPreExecute() {
/* 607 */       super.onPreExecute();
/*     */
/* 609 */       if (this.mCallback != null) {
/* 610 */         this.mCallback.onPreAuthenticate();
/*     */       }
/*     */     }
/*     */
/*     */
/*     */     protected PlugPagAuthenticationResult doInBackground(PlugPagAuthenticationRequest... args) {
/* 616 */       PlugPagAuthenticationResult authenticationResult = null;
/*     */
/* 618 */       if (args != null && args.length > 0 && args[0] != null) {
/* 619 */         authenticationResult = PlugPagAuthenticationFragment.this.authenticate(args[0]);
/*     */       }
/*     */
/* 622 */       return authenticationResult;
/*     */     }
/*     */
/*     */
/*     */     protected void onPostExecute(PlugPagAuthenticationResult authenticationResult) {
/* 627 */       super.onPostExecute(authenticationResult);
/*     */
/* 629 */       if (this.mCallback != null) {
/* 630 */         this.mCallback.onPostAuthenticate(authenticationResult);
/* 631 */         this.mCallback = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/authentication/PlugPagAuthenticationFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
