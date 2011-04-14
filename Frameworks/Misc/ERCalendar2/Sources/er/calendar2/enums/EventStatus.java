package er.calendar2.enums;


import net.fortuna.ical4j.model.property.Status;

import com.webobjects.foundation.NSArray;
import com.zimbra.cs.mailbox.calendar.IcalXmlStrMap;

import er.calendar2.ERCalendarPrincipal;

public enum EventStatus implements IStatus, ICalendarProperty {

  TENTATIVE("Tentatif", Status.VEVENT_TENTATIVE, IcalXmlStrMap.STATUS_TENTATIVE),
  CANCELLED("Annulé", Status.VEVENT_CANCELLED, IcalXmlStrMap.STATUS_CANCELLED),
  CONFIRMED("Confirmé", Status.VEVENT_CONFIRMED, IcalXmlStrMap.STATUS_CONFIRMED);
  
  private String localizedDescription;
  private Status statusObject;
  private String zimbraValue;

  private EventStatus(String description, Status rfc2445Value, String zimbraValue) {
    this.localizedDescription = description;
    this.statusObject = rfc2445Value;
    this.zimbraValue = zimbraValue;
  }
    
  public String localizedDescription() {
    return ERCalendarPrincipal.localizer().localizedStringForKey(localizedDescription);
  }
  
  public Status rfc2445Value() {
    return statusObject;
  }
  
  public String zimbraValue() {
    return zimbraValue;
  }
  
  public static NSArray<EventStatus> statuses() {
    return new NSArray<EventStatus>(EventStatus.values());
  }
  
  private EventStatus() {
  }
  
}
