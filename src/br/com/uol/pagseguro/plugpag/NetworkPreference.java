package br.com.uol.pagseguro.plugpag;

public enum NetworkPreference {
  UNKNOWN_TEC(1, "UNKNOWN_CONNECTION"),
  TEC_2G(3, "2G"),
  TEC_3G(2, "3G"),
  TEC_4G(1, "4G/AUTO");
  
  private String name;
  
  private int type;
  
  NetworkPreference(int type, String name) {
    this.type = type;
    this.name = name;
  }
  
  public int getType() {
    return this.type;
  }
  
  public String getName() {
    return this.name;
  }
}
