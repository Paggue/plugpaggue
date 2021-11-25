package br.com.uol.pagseguro.plugpag;

import java.io.Serializable;

public class PlugPagCustomPrinterLayout implements Serializable {
  private String mTitle = null;
  
  private String mTitleColor = null;
  
  private String mConfirmTextColor = null;
  
  private String mCancelTextColor = null;
  
  private String mWindowBackgoundColor = null;
  
  private String mButtonBackgroundColor = null;
  
  private String mButtonBackgroundColorDisabled = null;
  
  private String mSendSMSTextColor = null;
  
  public PlugPagCustomPrinterLayout(String title, String titleColor, String confirmTextColor, String cancelTextColor, String windowBackgoundColor, String buttonBackgroundColor, String buttonBackgroundColorDisabled, String sendSMSTextColor) {
    this.mTitle = title;
    this.mTitleColor = titleColor;
    this.mConfirmTextColor = confirmTextColor;
    this.mCancelTextColor = cancelTextColor;
    this.mWindowBackgoundColor = windowBackgoundColor;
    this.mButtonBackgroundColor = buttonBackgroundColor;
    this.mButtonBackgroundColorDisabled = buttonBackgroundColorDisabled;
    this.mSendSMSTextColor = sendSMSTextColor;
  }
  
  public PlugPagCustomPrinterLayout() {}
  
  public String getTitle() {
    return this.mTitle;
  }
  
  public String getTitleColor() {
    return this.mTitleColor;
  }
  
  public String getConfirmTextColor() {
    return this.mConfirmTextColor;
  }
  
  public String getCancelTextColor() {
    return this.mCancelTextColor;
  }
  
  public String getWindowBackgoundColor() {
    return this.mWindowBackgoundColor;
  }
  
  public String getButtonBackgroundColor() {
    return this.mButtonBackgroundColor;
  }
  
  public String getSendSMSTextColor() {
    return this.mSendSMSTextColor;
  }
  
  public String getButtonBackgroundColorDisabled() {
    return this.mButtonBackgroundColorDisabled;
  }
  
  public static final class Builder {
    private String mTitle = null;
    
    private String mTitleColor = null;
    
    private String mConfirmTextColor = null;
    
    private String mCancelTextColor = null;
    
    private String mWindowBackgoundColor = null;
    
    private String mButtonBackgroundColor = null;
    
    private String mButtonBackgroundColorDisabled = null;
    
    private String mSendSMSTextColor = null;
    
    public Builder setTitle(String title) {
      this.mTitle = title;
      return this;
    }
    
    public Builder setTitleColor(String titleColor) {
      this.mTitleColor = titleColor;
      return this;
    }
    
    public Builder setConfirmTextColor(String confirmTextColor) {
      this.mConfirmTextColor = confirmTextColor;
      return this;
    }
    
    public Builder setCancelTextColor(String cancelTextColor) {
      this.mCancelTextColor = cancelTextColor;
      return this;
    }
    
    public Builder setWindowBackgoundColor(String windowBackgoundColor) {
      this.mWindowBackgoundColor = windowBackgoundColor;
      return this;
    }
    
    public Builder setButtonBackgroundColor(String buttonBackgroundColor) {
      this.mButtonBackgroundColor = buttonBackgroundColor;
      return this;
    }
    
    public Builder setSendSMSTextColor(String sendSMSTextColor) {
      this.mSendSMSTextColor = sendSMSTextColor;
      return this;
    }
    
    public Builder setButtonBackgroundColorDisabled(String buttonBackgroundColorDisabled) {
      this.mButtonBackgroundColorDisabled = buttonBackgroundColorDisabled;
      return this;
    }
    
    public PlugPagCustomPrinterLayout build() {
      return new PlugPagCustomPrinterLayout(this.mTitle, this.mTitleColor, this.mConfirmTextColor, this.mCancelTextColor, this.mWindowBackgoundColor, this.mButtonBackgroundColor, this.mButtonBackgroundColorDisabled, this.mSendSMSTextColor);
    }
  }
}
