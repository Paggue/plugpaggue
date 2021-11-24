/*     */ package br.com.uol.pagseguro.plugpag;public class PlugPagUserData { private String mAddress; private String mCity;
/*     */   private String mCnpjCpf;
/*     */   private String mComplement;
/*     */   private String mCompanyName;
/*     */   private String mNickName;
/*     */   private String mState;
/*     */   private String mEmail;
/*     */   
/*     */   static {
/*  10 */     PlugPagLibraryLoader.loadNativeLibraries();
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
/*     */   public PlugPagUserData(String address, String city, String cnpjCpf, String complement, String companyName, String nickName, String state, String email) {
/*  28 */     this.mAddress = null;
/*     */     
/*  30 */     this.mCity = null;
/*     */     
/*  32 */     this.mCnpjCpf = null;
/*     */     
/*  34 */     this.mComplement = null;
/*     */     
/*  36 */     this.mCompanyName = null;
/*     */     
/*  38 */     this.mNickName = null;
/*     */     
/*  40 */     this.mState = null;
/*     */     
/*  42 */     this.mEmail = null; this.mAddress = address; this.mCity = city;
/*     */     this.mCnpjCpf = cnpjCpf;
/*     */     this.mComplement = complement;
/*     */     this.mCompanyName = companyName;
/*     */     this.mNickName = nickName;
/*     */     this.mState = state;
/*  48 */     this.mEmail = email; } public String getAddress() { return this.mAddress; }
/*     */ 
/*     */   
/*     */   public void setAddress(String address) {
/*  52 */     this.mAddress = address;
/*     */   }
/*     */   
/*     */   public String getCity() {
/*  56 */     return this.mCity;
/*     */   }
/*     */   
/*     */   public void setCity(String city) {
/*  60 */     this.mCity = city;
/*     */   }
/*     */   
/*     */   public String getCnpjCpf() {
/*  64 */     return this.mCnpjCpf;
/*     */   }
/*     */   
/*     */   public void setCnpjCpf(String cnpjCpf) {
/*  68 */     this.mCnpjCpf = cnpjCpf;
/*     */   }
/*     */   
/*     */   public String getComplement() {
/*  72 */     return this.mComplement;
/*     */   }
/*     */   
/*     */   public void setComplement(String complement) {
/*  76 */     this.mComplement = complement;
/*     */   }
/*     */   
/*     */   public String getCompanyName() {
/*  80 */     return this.mCompanyName;
/*     */   }
/*     */   
/*     */   public void setCompanyName(String companyName) {
/*  84 */     this.mCompanyName = companyName;
/*     */   }
/*     */   
/*     */   public String getNickName() {
/*  88 */     return this.mNickName;
/*     */   }
/*     */   
/*     */   public void setNickName(String nickName) {
/*  92 */     this.mNickName = nickName;
/*     */   }
/*     */   
/*     */   public String getState() {
/*  96 */     return this.mState;
/*     */   }
/*     */   
/*     */   public void setState(String state) {
/* 100 */     this.mState = state;
/*     */   }
/*     */   public String getEmail() {
/* 103 */     return this.mEmail;
/*     */   } public void setEmail(String email) {
/* 105 */     this.mEmail = email;
/*     */   } }


/* Location:              /home/paggue/Downloads/classes.jar!/br/com/uol/pagseguro/plugpag/PlugPagUserData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */