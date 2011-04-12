package er.calendar2.enums;


import com.webobjects.foundation.NSArray;

public enum EventStatus implements IStatus {

  TENTATIVE("Tentatif","TENTATIVE"),
  CANCELLED("Annulé","CANCELLED"),
  COMPLETED("Complété","COMPLETED"),
  CONFIRMED("Confirmé","CONFIRMED");
  
  private String description;
  private String zimbraValue;

  private EventStatus(String description, String rfc2445Value) {
    this.description = description;
    this.zimbraValue = rfc2445Value;
  }
    
  public String description() {
    return description;
  }
  
  public String rfc2445Value() {
    return zimbraValue;
  }
  
  public static NSArray<EventStatus> statuses() {
    return new NSArray<EventStatus>(EventStatus.values());
  }
  
  private EventStatus() {
  }
  
}
