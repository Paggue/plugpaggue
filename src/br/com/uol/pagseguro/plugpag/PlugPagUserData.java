package br.com.uol.pagseguro.plugpag;

public class PlugPagUserData {
  private String mAddress;
  
  private String mCity;
  
  private String mCnpjCpf;
  
  private String mComplement;
  
  private String mCompanyName;
  
  private String mNickName;
  
  private String mState;
  
  private String mEmail;
  
  static {
    PlugPagLibraryLoader.loadNativeLibraries();
  }
  
  public PlugPagUserData(String address, String city, String cnpjCpf, String complement, String companyName, String nickName, String state, String email) {
    this.mAddress = null;
    this.mCity = null;
    this.mCnpjCpf = null;
    this.mComplement = null;
    this.mCompanyName = null;
    this.mNickName = null;
    this.mState = null;
    this.mEmail = null;
    this.mAddress = address;
    this.mCity = city;
    this.mCnpjCpf = cnpjCpf;
    this.mComplement = complement;
    this.mCompanyName = companyName;
    this.mNickName = nickName;
    this.mState = state;
    this.mEmail = email;
  }
  
  public String getAddress() {
    return this.mAddress;
  }
  
  public void setAddress(String address) {
    this.mAddress = address;
  }
  
  public String getCity() {
    return this.mCity;
  }
  
  public void setCity(String city) {
    this.mCity = city;
  }
  
  public String getCnpjCpf() {
    return this.mCnpjCpf;
  }
  
  public void setCnpjCpf(String cnpjCpf) {
    this.mCnpjCpf = cnpjCpf;
  }
  
  public String getComplement() {
    return this.mComplement;
  }
  
  public void setComplement(String complement) {
    this.mComplement = complement;
  }
  
  public String getCompanyName() {
    return this.mCompanyName;
  }
  
  public void setCompanyName(String companyName) {
    this.mCompanyName = companyName;
  }
  
  public String getNickName() {
    return this.mNickName;
  }
  
  public void setNickName(String nickName) {
    this.mNickName = nickName;
  }
  
  public String getState() {
    return this.mState;
  }
  
  public void setState(String state) {
    this.mState = state;
  }
  
  public String getEmail() {
    return this.mEmail;
  }
  
  public void setEmail(String email) {
    this.mEmail = email;
  }
}
