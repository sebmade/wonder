package er.calendar2.enums;


import com.webobjects.foundation.NSArray;

public enum TaskStatus implements IStatus {

  NEEDS_ACTION("Non commencé","NEEDS-ACTION"),
  IN_PROCESS("En cours","IN-PROCESS"),
  COMPLETED("Terminé","COMPLETED"),
  CANCELLED("Annulé","CANCELLED");
  
  private String description;
  private String rfc2445Value;

  private TaskStatus(String description, String rfc2445Value) {
    this.description = description;
    this.rfc2445Value = rfc2445Value;
  }
    
  public String description() {
    return description;
  }
  
  public String rfc2445Value() {
    return rfc2445Value;
  }
  
  public static NSArray<TaskStatus> statuses() {
    return new NSArray<TaskStatus>(TaskStatus.values());
  }
  
  private TaskStatus() {
  }
  
}
