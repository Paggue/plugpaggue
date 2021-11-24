/*     */ package br.com.uol.pagseguro.plugpag.datainput;
/*     */ 
/*     */ import android.os.Bundle;
/*     */ import android.os.CountDownTimer;
/*     */ import android.support.annotation.NonNull;
/*     */ import android.support.annotation.Nullable;
/*     */ import android.support.v7.widget.AppCompatButton;
/*     */ import android.support.v7.widget.AppCompatEditText;
/*     */ import android.support.v7.widget.AppCompatTextView;
/*     */ import android.support.v7.widget.LinearLayoutManager;
/*     */ import android.support.v7.widget.RecyclerView;
/*     */ import android.text.Editable;
/*     */ import android.text.InputFilter;
/*     */ import android.text.Spanned;
/*     */ import android.text.TextWatcher;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.MotionEvent;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.Button;
/*     */ import android.widget.ProgressBar;
/*     */ import android.widget.RelativeLayout;
/*     */ import br.com.uol.pagseguro.plugpag.PlugPag;
/*     */ import br.com.uol.pagseguro.plugpag.PlugPagFragment;
/*     */ import br.com.uol.pagseguro.plugpag.R;
/*     */ import br.com.uol.pagseguro.plugpag.listener.OnBackPressedListener;
/*     */ import java.util.HashMap;
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
/*     */ public class PlugPagDataInputFragment
/*     */   extends PlugPagFragment
/*     */   implements View.OnClickListener, TextWatcher, View.OnTouchListener, OnBackPressedListener
/*     */ {
/*     */   public static final String EXTRA_DATAINPUT_TYPE = "EXTRA_DATAINPUT_TYPE";
/*     */   public static final String EXTRA_DATAINPUT_MIN_LENGTH = "EXTRA_DATAINPUT_MIN_LENGTH";
/*     */   public static final String EXTRA_DATAINPUT_MAX_LENGTH = "EXTRA_DATAINPUT_MAX_LENGTH";
/*     */   public static final String EXTRA_DATAINPUT_TIMEOUT = "EXTRA_DATAINPUT_TIMEOUT";
/*     */   public static final String EXTRA_DATAINPUT_MENU = "EXTRA_DATAINPUT_MENU";
/*     */   public static final int INPUT_TYPE_CVV = 0;
/*     */   public static final int INPUT_TYPE_FIRST_6_DIGITS = 1;
/*     */   public static final int INPUT_TYPE_LAST_4_DIGITS = 2;
/*     */   public static final int INPUT_TYPE_OPTIONS = 3;
/*     */   public static final int LAST_4_DIGITS_MIN_LENGTH = 3;
/*     */   private static final long TICK = 16L;
/*  59 */   private static final HashMap<Integer, Integer> TITLES = new HashMap<Integer, Integer>()
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   private ProgressBar mPgbTimer = null;
/*  73 */   private AppCompatTextView mTxtTitle = null;
/*  74 */   private AppCompatEditText mEdtData = null;
/*  75 */   private RelativeLayout mKeyboardLayout = null;
/*  76 */   private AppCompatButton mBtnKeyboard1 = null;
/*  77 */   private AppCompatButton mBtnKeyboard2 = null;
/*  78 */   private AppCompatButton mBtnKeyboard3 = null;
/*  79 */   private AppCompatButton mBtnKeyboard4 = null;
/*  80 */   private AppCompatButton mBtnKeyboard5 = null;
/*  81 */   private AppCompatButton mBtnKeyboard6 = null;
/*  82 */   private AppCompatButton mBtnKeyboard7 = null;
/*  83 */   private AppCompatButton mBtnKeyboard8 = null;
/*  84 */   private AppCompatButton mBtnKeyboard9 = null;
/*  85 */   private AppCompatButton mBtnKeyboard0 = null;
/*  86 */   private AppCompatButton mBtnKeyboardClear = null;
/*  87 */   private AppCompatButton mBtnKeyboardOk = null;
/*  88 */   private Button mBtnNext = null;
/*  89 */   private RecyclerView mOptionsList = null;
/*     */   
/*  91 */   private int mType = 0;
/*  92 */   private int mMinLength = 0;
/*  93 */   private int mMaxLength = 0;
/*  94 */   private int mTimeout = 0;
/*     */   
/*     */   private boolean mDataSent = false;
/*  97 */   private InputTimer mInputTimer = null;
/*  98 */   private String[] mItemsList = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   OptionsListClickListener optionsClickListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
/* 114 */     loadArguments();
/* 115 */     return inflater.inflate(R.layout.fragment_plugpag_data_input, container, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
/* 120 */     super.onViewCreated(view, savedInstanceState);
/* 121 */     setupViews(view);
/* 122 */     setupEventListeners();
/* 123 */     fillViews();
/* 124 */     startTimer();
/*     */ 
/*     */     
/* 127 */     if (this.mType == 0) {
/* 128 */       this.mBtnNext.setEnabled(true);
/* 129 */       this.mBtnKeyboardOk.setEnabled(true);
/*     */       
/* 131 */       this.mBtnKeyboardOk.setVisibility(0);
/* 132 */       this.mBtnNext.setVisibility(0);
/*     */     } 
/*     */     
/* 135 */     this.mEdtData.setText("");
/* 136 */     this.mDataSent = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onStop() {
/* 141 */     super.onStop();
/* 142 */     stopTimer();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBackPressed() {
/* 147 */     setDataInput((String)null);
/* 148 */     getActivity().getSupportFragmentManager().popBackStack();
/* 149 */     return true;
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
/*     */   private void setupViews(@NonNull View view) {
/* 162 */     this.mPgbTimer = (ProgressBar)view.findViewById(R.id.plugpag_data_input_progress_timer);
/* 163 */     this.mTxtTitle = (AppCompatTextView)view.findViewById(R.id.plugpag_data_input_title);
/* 164 */     this.mEdtData = (AppCompatEditText)view.findViewById(R.id.plugpag_data_input_data);
/*     */     
/* 166 */     this.mKeyboardLayout = (RelativeLayout)view.findViewById(R.id.plugpag_data_input_linear);
/* 167 */     this.mBtnKeyboard1 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_1);
/* 168 */     this.mBtnKeyboard2 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_2);
/* 169 */     this.mBtnKeyboard3 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_3);
/* 170 */     this.mBtnKeyboard4 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_4);
/* 171 */     this.mBtnKeyboard5 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_5);
/* 172 */     this.mBtnKeyboard6 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_6);
/* 173 */     this.mBtnKeyboard7 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_7);
/* 174 */     this.mBtnKeyboard8 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_8);
/* 175 */     this.mBtnKeyboard9 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_9);
/* 176 */     this.mBtnKeyboard0 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_0);
/* 177 */     this.mBtnKeyboardClear = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_clear);
/* 178 */     this.mBtnKeyboardOk = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_ok);
/* 179 */     this.mBtnNext = (Button)view.findViewById(R.id.plugpag_data_input_next);
/* 180 */     this.mBtnKeyboardOk.setVisibility(4);
/*     */     
/* 182 */     if (this.mItemsList != null) {
/* 183 */       this.mOptionsList = (RecyclerView)view.findViewById(R.id.plugpag_data_input_list);
/* 184 */       this.mOptionsList.setAdapter(new OptionsListAdapter(getContext(), this.mItemsList, this.optionsClickListener));
/* 185 */       this.mOptionsList.setLayoutManager((RecyclerView.LayoutManager)new LinearLayoutManager(getContext()));
/* 186 */       this.mOptionsList.setVisibility(0);
/* 187 */       this.mKeyboardLayout.setVisibility(8);
/* 188 */       this.mEdtData.setVisibility(8);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setupEventListeners() {
/* 196 */     this.mEdtData.addTextChangedListener(this);
/* 197 */     this.mEdtData.setCustomSelectionActionModeCallback(null);
/* 198 */     this.mEdtData.requestFocus();
/* 199 */     this.mEdtData.setOnTouchListener(this);
/*     */     
/* 201 */     if (this.mMaxLength > 0) {
/* 202 */       this.mEdtData.setFilters(new InputFilter[] { new MaxLengthInputFilter(this.mMaxLength) });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 207 */     this.mBtnKeyboard1.setOnClickListener(this);
/* 208 */     this.mBtnKeyboard2.setOnClickListener(this);
/* 209 */     this.mBtnKeyboard3.setOnClickListener(this);
/* 210 */     this.mBtnKeyboard4.setOnClickListener(this);
/* 211 */     this.mBtnKeyboard5.setOnClickListener(this);
/* 212 */     this.mBtnKeyboard6.setOnClickListener(this);
/* 213 */     this.mBtnKeyboard7.setOnClickListener(this);
/* 214 */     this.mBtnKeyboard8.setOnClickListener(this);
/* 215 */     this.mBtnKeyboard9.setOnClickListener(this);
/* 216 */     this.mBtnKeyboard0.setOnClickListener(this);
/* 217 */     this.mBtnKeyboardClear.setOnClickListener(this);
/* 218 */     this.mBtnKeyboardOk.setOnClickListener(this);
/* 219 */     this.mBtnNext.setOnClickListener(this);
/*     */     
/* 221 */     getActivity().getWindow().setSoftInputMode(2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void fillViews() {
/* 229 */     this.mTxtTitle.setText(getTitle(this.mType));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getTitle(int type) {
/* 239 */     String title = null;
/*     */     
/* 241 */     if (TITLES.containsKey(Integer.valueOf(type))) {
/* 242 */       title = getString(((Integer)TITLES.get(Integer.valueOf(type))).intValue());
/*     */     } else {
/* 244 */       title = "";
/*     */     } 
/*     */     
/* 247 */     return title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick(View v) {
/* 256 */     int id = 0;
/*     */     
/* 258 */     id = v.getId();
/*     */     
/* 260 */     if (id == R.id.plugpag_btn_keyboard_1 || id == R.id.plugpag_btn_keyboard_2 || id == R.id.plugpag_btn_keyboard_3 || id == R.id.plugpag_btn_keyboard_4 || id == R.id.plugpag_btn_keyboard_5 || id == R.id.plugpag_btn_keyboard_6 || id == R.id.plugpag_btn_keyboard_7 || id == R.id.plugpag_btn_keyboard_8 || id == R.id.plugpag_btn_keyboard_9 || id == R.id.plugpag_btn_keyboard_0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 270 */       this.mEdtData.setText(this.mEdtData.getText().toString() + ((AppCompatButton)v).getText());
/* 271 */     } else if (id == R.id.plugpag_btn_keyboard_clear) {
/* 272 */       this.mEdtData.setText("");
/* 273 */     } else if (id == R.id.plugpag_btn_keyboard_ok || id == R.id.plugpag_data_input_next) {
/* 274 */       this.mDataSent = true;
/* 275 */       setDataInput(this.mEdtData.getText().toString());
/*     */     } 
/*     */   }
/*     */   public PlugPagDataInputFragment() {
/* 279 */     this.optionsClickListener = new OptionsListClickListener()
/*     */       {
/*     */         public void onItemClicked(Integer selectedItem) {
/* 282 */           PlugPagDataInputFragment.this.setSelectedItem(selectedItem);
/* 283 */           PlugPagDataInputFragment.this.setDataInput((String)null);
/*     */         }
/*     */       };
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
/*     */   private void setDataInput(String data) {
/* 297 */     onFinishDataInput(data);
/* 298 */     stopTimer();
/* 299 */     getActivity().finish();
/*     */   }
/*     */   
/*     */   private void setSelectedItem(Integer selectedItem) {
/* 303 */     if (this.mType != 3) {
/*     */       return;
/*     */     }
/*     */     
/* 307 */     PlugPag.SELECTED_ITEM_IDX = selectedItem.intValue() + 1;
/* 308 */     PlugPag.latch.countDown();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadArguments() {
/* 319 */     Bundle args = null;
/*     */     
/* 321 */     args = getArguments();
/*     */     
/* 323 */     if (args.containsKey("EXTRA_DATAINPUT_TYPE")) {
/* 324 */       this.mType = args.getInt("EXTRA_DATAINPUT_TYPE");
/*     */     }
/*     */     
/* 327 */     if (args.containsKey("EXTRA_DATAINPUT_MIN_LENGTH")) {
/* 328 */       this.mMinLength = args.getInt("EXTRA_DATAINPUT_MIN_LENGTH");
/*     */     }
/*     */     
/* 331 */     if (args.containsKey("EXTRA_DATAINPUT_MAX_LENGTH")) {
/* 332 */       this.mMaxLength = args.getInt("EXTRA_DATAINPUT_MAX_LENGTH");
/*     */     }
/*     */     
/* 335 */     if (args.containsKey("EXTRA_DATAINPUT_TIMEOUT")) {
/* 336 */       this.mTimeout = args.getInt("EXTRA_DATAINPUT_TIMEOUT");
/*     */     }
/*     */     
/* 339 */     if (args.containsKey("EXTRA_DATAINPUT_MENU")) {
/* 340 */       this.mItemsList = args.getStringArray("EXTRA_DATAINPUT_MENU");
/*     */     }
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
/* 360 */     int length = 0;
/* 361 */     boolean filled = false;
/*     */     
/* 363 */     this.mEdtData.setSelection(s.length());
/* 364 */     length = s.length();
/*     */     
/* 366 */     if (this.mType == 2) {
/* 367 */       filled = (length > 3);
/*     */     } else {
/* 369 */       filled = (length > 0 && length >= this.mMinLength);
/*     */     } 
/*     */     
/* 372 */     if (filled || (this.mType == 0 && length == 0)) {
/* 373 */       this.mBtnNext.setEnabled(true);
/* 374 */       this.mBtnKeyboardOk.setVisibility(0);
/*     */     } else {
/* 376 */       this.mBtnNext.setEnabled(false);
/* 377 */       this.mBtnKeyboardOk.setVisibility(4);
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
/*     */   
/*     */   private void startTimer() {
/* 390 */     if (this.mTimeout > 0 && this.mInputTimer == null) {
/* 391 */       this.mInputTimer = new InputTimer(this.mTimeout, 16L, this.mPgbTimer);
/*     */       
/* 393 */       this.mInputTimer.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void stopTimer() {
/* 401 */     if (this.mInputTimer != null) {
/* 402 */       this.mInputTimer.cancel();
/* 403 */       this.mInputTimer = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onTouch(View v, MotionEvent event) {
/* 409 */     int id = v.getId();
/* 410 */     if (id == R.id.plugpag_data_input_data) {
/* 411 */       return true;
/*     */     }
/* 413 */     return false;
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
/*     */   private class MaxLengthInputFilter
/*     */     implements InputFilter
/*     */   {
/* 429 */     private int mMaxLength = 0;
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
/*     */     public MaxLengthInputFilter(int maxLength) {
/* 441 */       if (maxLength > 0) {
/* 442 */         this.mMaxLength = maxLength;
/*     */       } else {
/* 444 */         this.mMaxLength = Integer.MAX_VALUE;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
/* 454 */       CharSequence newValue = null;
/*     */       
/* 456 */       if (source.length() > this.mMaxLength) {
/* 457 */         newValue = source.subSequence(0, this.mMaxLength);
/*     */       } else {
/* 459 */         newValue = source;
/*     */       } 
/*     */       
/* 462 */       return newValue;
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
/*     */   private class InputTimer
/*     */     extends CountDownTimer
/*     */   {
/* 476 */     private ProgressBar mProgressBar = null;
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
/*     */     public InputTimer(long millisInFuture, long countDownInterval, ProgressBar progressBar) {
/* 493 */       super(millisInFuture, countDownInterval);
/* 494 */       this.mProgressBar = progressBar;
/*     */       
/* 496 */       if (this.mProgressBar != null) {
/* 497 */         this.mProgressBar.setMax((int)millisInFuture);
/* 498 */         this.mProgressBar.setProgress((int)millisInFuture);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void onTick(long millisUntilFinished) {
/* 508 */       if (this.mProgressBar != null) {
/* 509 */         this.mProgressBar.setProgress((int)millisUntilFinished);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void onFinish() {
/* 515 */       this.mProgressBar.setProgress(0);
/* 516 */       this.mProgressBar = null;
/* 517 */       PlugPagDataInputFragment.this.setSelectedItem(Integer.valueOf(-1));
/* 518 */       PlugPagDataInputFragment.this.setDataInput((String)null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/datainput/PlugPagDataInputFragment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */