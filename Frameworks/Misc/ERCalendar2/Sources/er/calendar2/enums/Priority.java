package er.calendar2.enums;


import com.webobjects.foundation.NSArray;

public enum Priority {

  UNDEFINED("Non défini",net.fortuna.ical4j.model.property.Priority.UNDEFINED),
  NORMAL("Normal",net.fortuna.ical4j.model.property.Priority.MEDIUM),
  HIGH("Haute",net.fortuna.ical4j.model.property.Priority.HIGH),
  LOW("Basse",net.fortuna.ical4j.model.property.Priority.LOW);

  private String description;
  private net.fortuna.ical4j.model.property.Priority rfc2445Value;

  private Priority(String description, net.fortuna.ical4j.model.property.Priority rfc2445Value) {
    this.description = description;
    this.rfc2445Value = rfc2445Value;
  }

  public String description() {
    return description;
  }
  
  public net.fortuna.ical4j.model.property.Priority rfc2445Value() {
    return rfc2445Value;
  }

  public static NSArray<Priority> triggers() {
    return new NSArray<Priority>(Priority.values());
  }

  private Priority() {
  }

}
