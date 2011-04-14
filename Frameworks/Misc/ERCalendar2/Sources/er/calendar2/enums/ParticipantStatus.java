package er.calendar2.enums;


import com.webobjects.foundation.NSArray;

/*
 * http://tools.ietf.org/html/rfc2445#section-4.2.12
 * 
"PARTSTAT" "="
                         ("NEEDS-ACTION"        ; Event needs action
                        / "ACCEPTED"            ; Event accepted
                        / "DECLINED"            ; Event declined
                        / "TENTATIVE"           ; Event tentatively
                        / "DELEGATED"           ; Event delegated
                        / x-name                ; Experimental status
                        / iana-token)           ; Other IANA registered
                                                ; status
 */

public enum ParticipantStatus {

  NEEDS_ACTION("Requiert action", "NEEDS-ACTION","NE"),
  ACCEPTED("Accepté", "ACCEPTED","AC"),
  DECLINED("Refusé", "DECLINED","DE"),
  TENTATIVE("Tentatif", "TENTATIVE","TE"),
  DELEGATED("Délégué à autrui", "DELEGATED","DG");
  
  private String description;
  private String rfc2445Value;
  private String zimbraValue;

  private ParticipantStatus(String description, String rfc2445Value, String zimbraValue) {
    this.description = description;
    this.zimbraValue = zimbraValue;
    this.rfc2445Value = rfc2445Value;
  }
    
  public String description() {
    return description;
  }
  
  public String zimbraValue() {
    return zimbraValue;
  }
  
  public String rfc2445Value() {
    return rfc2445Value;
  }
  
  public static NSArray<ParticipantStatus> statuses() {
    return new NSArray<ParticipantStatus>(ParticipantStatus.values());
  }
  
  private ParticipantStatus() {
  }
  
}
