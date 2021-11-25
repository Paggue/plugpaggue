package br.com.uol.pagseguro.plugpag;

import java.util.HashMap;
import java.util.Map;

public class UpdateTimeMetric {
  static final String PPS_UPDATE_TIME_EVENT_TYPE = "PlugPagUpdateTime";
  
  static final String PPS_UPDATE_TIME_EVENT_NAME = "UpdateTimeMetrics";
  
  private static final String ATTRIBUTE_CURRENT_TIME = "CurrentTime";
  
  private static final String ATTRIBUTE_NEW_TIME = "NewTime";
  
  private static final String ATTRIBUTE_SUCCESS_UPDATE_TIME = "SuccessUpdateTime";
  
  private static final String ATTRIBUTE_ORIGIN_OPERATION = "OriginOperation";
  
  private String currentTime;
  
  private String newTime;
  
  private int originOperation;
  
  private boolean successUpdateTime;
  
  private UpdateTimeMetric(Builder builder) {
    this.currentTime = builder.currentTime;
    this.newTime = builder.newTime;
    this.successUpdateTime = builder.successUpdateTime;
    this.originOperation = builder.originOperation;
  }
  
  public static class Builder {
    private String currentTime;
    
    private String newTime;
    
    private boolean successUpdateTime;
    
    private int originOperation;
    
    public Builder currentTime(String currentTime) {
      this.currentTime = currentTime;
      return this;
    }
    
    public Builder newTime(String newTime) {
      this.newTime = newTime;
      return this;
    }
    
    public Builder successUpdateTime(boolean successUpdateTime) {
      this.successUpdateTime = successUpdateTime;
      return this;
    }
    
    public Builder originOperation(int originOperation) {
      this.originOperation = originOperation;
      return this;
    }
    
    public UpdateTimeMetric build() {
      return new UpdateTimeMetric(this);
    }
  }
  
  public Map<String, Object> toMap() {
    Map<String, Object> bundle = new HashMap<>();
    bundle.put("CurrentTime", this.currentTime);
    bundle.put("NewTime", this.newTime);
    bundle.put("SuccessUpdateTime", Boolean.valueOf(this.successUpdateTime));
    bundle.put("OriginOperation", Integer.valueOf(this.originOperation));
    return bundle;
  }
}
