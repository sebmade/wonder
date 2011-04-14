package er.calendar2.enums;


import net.fortuna.ical4j.model.property.BusyType;

import com.webobjects.foundation.NSArray;
import com.zimbra.cs.mailbox.calendar.IcalXmlStrMap;

import er.calendar2.ERCalendarPrincipal;

public enum FreeBusyStatus implements ICalendarProperty {

  FREE("Libre", new BusyType("FREE"), IcalXmlStrMap.FBTYPE_FREE),
  BUSY("Occupé", BusyType.BUSY, IcalXmlStrMap.FBTYPE_BUSY),
  BUSY_TENTATIVE("Tentatif", BusyType.BUSY_TENTATIVE, IcalXmlStrMap.FBTYPE_BUSY_TENTATIVE),
  BUSY_UNAVAILABLE("Non disponible", BusyType.BUSY_UNAVAILABLE, IcalXmlStrMap.FBTYPE_BUSY_UNAVAILABLE);
  
  private String localizedDescription;
  private BusyType rfc2445Value;
  private String zimbraValue;

  private FreeBusyStatus(String localizedDescription, BusyType rfc2445Value, String zimbraValue) {
    this.localizedDescription = localizedDescription;
    this.rfc2445Value = rfc2445Value;
    this.zimbraValue = zimbraValue;
  }
    
  public String localizedDescription() {
    return ERCalendarPrincipal.localizer().localizedStringForKey(localizedDescription);
  }

  public String zimbraValue() {
    return zimbraValue;
  }
  
  public BusyType rfc2445Value() {
    return rfc2445Value;
  }
  
  public static NSArray<FreeBusyStatus> statuses() {
    return new NSArray<FreeBusyStatus>(FreeBusyStatus.values());
  }
  
  private FreeBusyStatus() {
  }
  
}
