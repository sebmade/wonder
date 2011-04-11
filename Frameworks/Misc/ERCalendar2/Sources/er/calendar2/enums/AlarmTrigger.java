package er.calendar2.enums;


import com.webobjects.foundation.NSArray;

public enum AlarmTrigger {

  FIFTEEN_MINS("15 minutes"),
  ONE_HOUR("Une heure"),
  ONE_DAY("Une journée");
    
  private String description;

  private AlarmTrigger(String description) {
    this.description = description;
  }
  
  public String description() {
    return description;
  }
  
  public static NSArray<AlarmTrigger> triggers() {
    return new NSArray<AlarmTrigger>(AlarmTrigger.values());
  }
  
  private AlarmTrigger() {
  }
  
}
