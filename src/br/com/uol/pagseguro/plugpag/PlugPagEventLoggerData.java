package br.com.uol.pagseguro.plugpag;

import java.util.Map;

public class PlugPagEventLoggerData {
  private String mEventType;
  
  private String mEventName;
  
  private Map<String, Object> mEventAttributes;
  
  public static PlugPagEventLoggerData getBreadCrumbInstance(String eventType, Map<String, Object> eventAttributes) {
    return new PlugPagEventLoggerData(eventType, "", eventAttributes);
  }
  
  public PlugPagEventLoggerData(String eventType, String eventName) {
    this(eventType, eventName, null);
  }
  
  public PlugPagEventLoggerData(String eventType, String eventName, Map<String, Object> eventAttributes) {
    this.mEventType = eventType;
    this.mEventName = eventName;
    this.mEventAttributes = eventAttributes;
  }
  
  public String getEventType() {
    return this.mEventType;
  }
  
  public String getEventName() {
    return this.mEventName;
  }
  
  public Map<String, Object> getEventAttributes() {
    return this.mEventAttributes;
  }
}
