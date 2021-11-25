package br.com.uol.pagseguro.plugpag.datainput;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import br.com.uol.pagseguro.plugpag.PlugPag;
import br.com.uol.pagseguro.plugpag.PlugPagFragment;
import br.com.uol.pagseguro.plugpag.R;
import br.com.uol.pagseguro.plugpag.listener.OnBackPressedListener;
import java.util.HashMap;

public class PlugPagDataInputFragment extends PlugPagFragment implements View.OnClickListener, TextWatcher, View.OnTouchListener, OnBackPressedListener {
  public static final String EXTRA_DATAINPUT_TYPE = "EXTRA_DATAINPUT_TYPE";
  
  public static final String EXTRA_DATAINPUT_MIN_LENGTH = "EXTRA_DATAINPUT_MIN_LENGTH";
  
  public static final String EXTRA_DATAINPUT_MAX_LENGTH = "EXTRA_DATAINPUT_MAX_LENGTH";
  
  public static final String EXTRA_DATAINPUT_TIMEOUT = "EXTRA_DATAINPUT_TIMEOUT";
  
  public static final String EXTRA_DATAINPUT_MENU = "EXTRA_DATAINPUT_MENU";
  
  public static final int INPUT_TYPE_CVV = 0;
  
  public static final int INPUT_TYPE_FIRST_6_DIGITS = 1;
  
  public static final int INPUT_TYPE_LAST_4_DIGITS = 2;
  
  public static final int INPUT_TYPE_OPTIONS = 3;
  
  public static final int LAST_4_DIGITS_MIN_LENGTH = 3;
  
  private static final long TICK = 16L;
  
  private static final HashMap<Integer, Integer> TITLES = new HashMap<Integer, Integer>() {
    
    };
  
  private ProgressBar mPgbTimer = null;
  
  private AppCompatTextView mTxtTitle = null;
  
  private AppCompatEditText mEdtData = null;
  
  private RelativeLayout mKeyboardLayout = null;
  
  private AppCompatButton mBtnKeyboard1 = null;
  
  private AppCompatButton mBtnKeyboard2 = null;
  
  private AppCompatButton mBtnKeyboard3 = null;
  
  private AppCompatButton mBtnKeyboard4 = null;
  
  private AppCompatButton mBtnKeyboard5 = null;
  
  private AppCompatButton mBtnKeyboard6 = null;
  
  private AppCompatButton mBtnKeyboard7 = null;
  
  private AppCompatButton mBtnKeyboard8 = null;
  
  private AppCompatButton mBtnKeyboard9 = null;
  
  private AppCompatButton mBtnKeyboard0 = null;
  
  private AppCompatButton mBtnKeyboardClear = null;
  
  private AppCompatButton mBtnKeyboardOk = null;
  
  private Button mBtnNext = null;
  
  private RecyclerView mOptionsList = null;
  
  private int mType = 0;
  
  private int mMinLength = 0;
  
  private int mMaxLength = 0;
  
  private int mTimeout = 0;
  
  private boolean mDataSent = false;
  
  private InputTimer mInputTimer = null;
  
  private String[] mItemsList = null;
  
  OptionsListClickListener optionsClickListener;
  
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    loadArguments();
    return inflater.inflate(R.layout.fragment_plugpag_data_input, container, false);
  }
  
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupViews(view);
    setupEventListeners();
    fillViews();
    startTimer();
    if (this.mType == 0) {
      this.mBtnNext.setEnabled(true);
      this.mBtnKeyboardOk.setEnabled(true);
      this.mBtnKeyboardOk.setVisibility(0);
      this.mBtnNext.setVisibility(0);
    } 
    this.mEdtData.setText("");
    this.mDataSent = false;
  }
  
  public void onStop() {
    super.onStop();
    stopTimer();
  }
  
  public boolean onBackPressed() {
    setDataInput((String)null);
    getActivity().getSupportFragmentManager().popBackStack();
    return true;
  }
  
  private void setupViews(@NonNull View view) {
    this.mPgbTimer = (ProgressBar)view.findViewById(R.id.plugpag_data_input_progress_timer);
    this.mTxtTitle = (AppCompatTextView)view.findViewById(R.id.plugpag_data_input_title);
    this.mEdtData = (AppCompatEditText)view.findViewById(R.id.plugpag_data_input_data);
    this.mKeyboardLayout = (RelativeLayout)view.findViewById(R.id.plugpag_data_input_linear);
    this.mBtnKeyboard1 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_1);
    this.mBtnKeyboard2 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_2);
    this.mBtnKeyboard3 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_3);
    this.mBtnKeyboard4 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_4);
    this.mBtnKeyboard5 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_5);
    this.mBtnKeyboard6 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_6);
    this.mBtnKeyboard7 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_7);
    this.mBtnKeyboard8 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_8);
    this.mBtnKeyboard9 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_9);
    this.mBtnKeyboard0 = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_0);
    this.mBtnKeyboardClear = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_clear);
    this.mBtnKeyboardOk = (AppCompatButton)view.findViewById(R.id.plugpag_btn_keyboard_ok);
    this.mBtnNext = (Button)view.findViewById(R.id.plugpag_data_input_next);
    this.mBtnKeyboardOk.setVisibility(4);
    if (this.mItemsList != null) {
      this.mOptionsList = (RecyclerView)view.findViewById(R.id.plugpag_data_input_list);
      this.mOptionsList.setAdapter(new OptionsListAdapter(getContext(), this.mItemsList, this.optionsClickListener));
      this.mOptionsList.setLayoutManager((RecyclerView.LayoutManager)new LinearLayoutManager(getContext()));
      this.mOptionsList.setVisibility(0);
      this.mKeyboardLayout.setVisibility(8);
      this.mEdtData.setVisibility(8);
    } 
  }
  
  private void setupEventListeners() {
    this.mEdtData.addTextChangedListener(this);
    this.mEdtData.setCustomSelectionActionModeCallback(null);
    this.mEdtData.requestFocus();
    this.mEdtData.setOnTouchListener(this);
    if (this.mMaxLength > 0)
      this.mEdtData.setFilters(new InputFilter[] { new MaxLengthInputFilter(this.mMaxLength) }); 
    this.mBtnKeyboard1.setOnClickListener(this);
    this.mBtnKeyboard2.setOnClickListener(this);
    this.mBtnKeyboard3.setOnClickListener(this);
    this.mBtnKeyboard4.setOnClickListener(this);
    this.mBtnKeyboard5.setOnClickListener(this);
    this.mBtnKeyboard6.setOnClickListener(this);
    this.mBtnKeyboard7.setOnClickListener(this);
    this.mBtnKeyboard8.setOnClickListener(this);
    this.mBtnKeyboard9.setOnClickListener(this);
    this.mBtnKeyboard0.setOnClickListener(this);
    this.mBtnKeyboardClear.setOnClickListener(this);
    this.mBtnKeyboardOk.setOnClickListener(this);
    this.mBtnNext.setOnClickListener(this);
    getActivity().getWindow().setSoftInputMode(2);
  }
  
  private void fillViews() {
    this.mTxtTitle.setText(getTitle(this.mType));
  }
  
  private String getTitle(int type) {
    String title = null;
    if (TITLES.containsKey(Integer.valueOf(type))) {
      title = getString(((Integer)TITLES.get(Integer.valueOf(type))).intValue());
    } else {
      title = "";
    } 
    return title;
  }
  
  public void onClick(View v) {
    int id = 0;
    id = v.getId();
    if (id == R.id.plugpag_btn_keyboard_1 || id == R.id.plugpag_btn_keyboard_2 || id == R.id.plugpag_btn_keyboard_3 || id == R.id.plugpag_btn_keyboard_4 || id == R.id.plugpag_btn_keyboard_5 || id == R.id.plugpag_btn_keyboard_6 || id == R.id.plugpag_btn_keyboard_7 || id == R.id.plugpag_btn_keyboard_8 || id == R.id.plugpag_btn_keyboard_9 || id == R.id.plugpag_btn_keyboard_0) {
      this.mEdtData.setText(this.mEdtData.getText().toString() + ((AppCompatButton)v).getText());
    } else if (id == R.id.plugpag_btn_keyboard_clear) {
      this.mEdtData.setText("");
    } else if (id == R.id.plugpag_btn_keyboard_ok || id == R.id.plugpag_data_input_next) {
      this.mDataSent = true;
      setDataInput(this.mEdtData.getText().toString());
    } 
  }
  
  public PlugPagDataInputFragment() {
    this.optionsClickListener = new OptionsListClickListener() {
        public void onItemClicked(Integer selectedItem) {
          PlugPagDataInputFragment.this.setSelectedItem(selectedItem);
          PlugPagDataInputFragment.this.setDataInput((String)null);
        }
      };
  }
  
  private void setDataInput(String data) {
    onFinishDataInput(data);
    stopTimer();
    getActivity().finish();
  }
  
  private void setSelectedItem(Integer selectedItem) {
    if (this.mType != 3)
      return; 
    PlugPag.SELECTED_ITEM_IDX = selectedItem.intValue() + 1;
    PlugPag.latch.countDown();
  }
  
  private void loadArguments() {
    Bundle args = null;
    args = getArguments();
    if (args.containsKey("EXTRA_DATAINPUT_TYPE"))
      this.mType = args.getInt("EXTRA_DATAINPUT_TYPE"); 
    if (args.containsKey("EXTRA_DATAINPUT_MIN_LENGTH"))
      this.mMinLength = args.getInt("EXTRA_DATAINPUT_MIN_LENGTH"); 
    if (args.containsKey("EXTRA_DATAINPUT_MAX_LENGTH"))
      this.mMaxLength = args.getInt("EXTRA_DATAINPUT_MAX_LENGTH"); 
    if (args.containsKey("EXTRA_DATAINPUT_TIMEOUT"))
      this.mTimeout = args.getInt("EXTRA_DATAINPUT_TIMEOUT"); 
    if (args.containsKey("EXTRA_DATAINPUT_MENU"))
      this.mItemsList = args.getStringArray("EXTRA_DATAINPUT_MENU"); 
  }
  
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
  
  public void onTextChanged(CharSequence s, int start, int before, int count) {}
  
  public void afterTextChanged(Editable s) {
    int length = 0;
    boolean filled = false;
    this.mEdtData.setSelection(s.length());
    length = s.length();
    if (this.mType == 2) {
      filled = (length > 3);
    } else {
      filled = (length > 0 && length >= this.mMinLength);
    } 
    if (filled || (this.mType == 0 && length == 0)) {
      this.mBtnNext.setEnabled(true);
      this.mBtnKeyboardOk.setVisibility(0);
    } else {
      this.mBtnNext.setEnabled(false);
      this.mBtnKeyboardOk.setVisibility(4);
    } 
  }
  
  private void startTimer() {
    if (this.mTimeout > 0 && this.mInputTimer == null) {
      this.mInputTimer = new InputTimer(this.mTimeout, 16L, this.mPgbTimer);
      this.mInputTimer.start();
    } 
  }
  
  private void stopTimer() {
    if (this.mInputTimer != null) {
      this.mInputTimer.cancel();
      this.mInputTimer = null;
    } 
  }
  
  public boolean onTouch(View v, MotionEvent event) {
    int id = v.getId();
    if (id == R.id.plugpag_data_input_data)
      return true; 
    return false;
  }
  
  private class MaxLengthInputFilter implements InputFilter {
    private int mMaxLength = 0;
    
    public MaxLengthInputFilter(int maxLength) {
      if (maxLength > 0) {
        this.mMaxLength = maxLength;
      } else {
        this.mMaxLength = Integer.MAX_VALUE;
      } 
    }
    
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
      CharSequence newValue = null;
      if (source.length() > this.mMaxLength) {
        newValue = source.subSequence(0, this.mMaxLength);
      } else {
        newValue = source;
      } 
      return newValue;
    }
  }
  
  private class InputTimer extends CountDownTimer {
    private ProgressBar mProgressBar = null;
    
    public InputTimer(long millisInFuture, long countDownInterval, ProgressBar progressBar) {
      super(millisInFuture, countDownInterval);
      this.mProgressBar = progressBar;
      if (this.mProgressBar != null) {
        this.mProgressBar.setMax((int)millisInFuture);
        this.mProgressBar.setProgress((int)millisInFuture);
      } 
    }
    
    public void onTick(long millisUntilFinished) {
      if (this.mProgressBar != null)
        this.mProgressBar.setProgress((int)millisUntilFinished); 
    }
    
    public void onFinish() {
      this.mProgressBar.setProgress(0);
      this.mProgressBar = null;
      PlugPagDataInputFragment.this.setSelectedItem(Integer.valueOf(-1));
      PlugPagDataInputFragment.this.setDataInput((String)null);
    }
  }
}
