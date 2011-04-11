package er.calendar2.enums;


import com.webobjects.foundation.NSArray;

public enum AttendeeRole {

  CHAIR("Meeting Leader", "CHAIR"),
  REQ_PARTICIPANT("Requis", "REQ-PARTICIPANT"),
  OPT_PARTICIPANT("Optionnel", "OPT-PARTICIPANT"),
  NON_PARTICIPANT("Non participant", "NON-PARTICIPANT");
  
  private String description;
  private String zimbraValue;

  private AttendeeRole(String description, String zimbraValue) {
    this.description = description;
    this.zimbraValue = zimbraValue;
  }
    
  public String description() {
    return description;
  }
  
  public String zimbraValue() {
    return zimbraValue;
  }
  
  public static NSArray<AttendeeRole> roles() {
    return new NSArray<AttendeeRole>(AttendeeRole.values());
  }
  
  private AttendeeRole() {
  }
  
}
