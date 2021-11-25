package br.com.uol.pagseguro.plugpag;

import java.util.HashMap;
import java.util.Map;

public class ChangeConnectionMetric {
  private String currentNetwork;
  
  private String preferredNetwork;
  
  private String successChangeConnection;
  
  private String firmwareVersion;
  
  private String model;
  
  private String simCardId;
  
  private ChangeConnectionMetric(Builder builder) {
    this.currentNetwork = builder.currentNetwork;
    this.preferredNetwork = builder.preferredNetwork;
    this.successChangeConnection = builder.successChangeConnection;
    this.firmwareVersion = builder.firmwareVersion;
    this.model = builder.model;
    this.simCardId = builder.simCardId;
  }
  
  public static class Builder {
    private String currentNetwork;
    
    private String preferredNetwork;
    
    private String successChangeConnection;
    
    private String firmwareVersion;
    
    private String model;
    
    private String simCardId;
    
    public Builder currentNetwork(String currentNetwork) {
      this.currentNetwork = currentNetwork;
      return this;
    }
    
    public Builder preferredNetwork(String preferredNetwork) {
      this.preferredNetwork = preferredNetwork;
      return this;
    }
    
    public Builder successChangeConnection(String successChangeConnection) {
      this.successChangeConnection = successChangeConnection;
      return this;
    }
    
    public Builder firmwareVersion(String firmwareVersion) {
      this.firmwareVersion = firmwareVersion;
      return this;
    }
    
    public Builder model(String model) {
      this.model = model;
      return this;
    }
    
    public Builder simCardId(String simCardId) {
      this.simCardId = simCardId;
      return this;
    }
    
    public ChangeConnectionMetric build() {
      return new ChangeConnectionMetric(this);
    }
  }
  
  public Map<String, Object> toMap() {
    Map<String, Object> bundle = new HashMap<>();
    bundle.put("CurrentNetwork", this.currentNetwork);
    bundle.put("PreferredNetwork", this.preferredNetwork);
    bundle.put("SuccessChangeConnection", this.successChangeConnection);
    bundle.put("FirmwareVersion", this.firmwareVersion);
    bundle.put("Model", this.model);
    bundle.put("idSimCard", this.simCardId);
    return bundle;
  }
}
