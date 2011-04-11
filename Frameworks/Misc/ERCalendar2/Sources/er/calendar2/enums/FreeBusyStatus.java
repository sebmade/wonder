package er.calendar2.enums;


import com.webobjects.foundation.NSArray;

public enum FreeBusyStatus {

  FREE("Libre", "FREE"),
  BUSY("Occupé", "BUSY"),
  BUSY_TENTATIVE("Tentatif", "BUSY-TENTATIVE"),
  BUSY_UNAVAILABLE("Non disponible", "BUSY-UNAVAILABLE");
  
  private String description;
  private String rfc2445Value;

  private FreeBusyStatus(String description, String rfc2445Value) {
    this.description = description;
    this.rfc2445Value = rfc2445Value;
  }
    
  public String description() {
    return description;
  }
  
  public String rfc2445Value() {
    return rfc2445Value;
  }
  
  public static NSArray<FreeBusyStatus> statuses() {
    return new NSArray<FreeBusyStatus>(FreeBusyStatus.values());
  }
  
  private FreeBusyStatus() {
  }
  
}
