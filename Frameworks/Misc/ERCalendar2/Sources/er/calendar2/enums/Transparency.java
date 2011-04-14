package er.calendar2.enums;


import net.fortuna.ical4j.model.property.Transp;

import com.webobjects.foundation.NSArray;
import com.zimbra.cs.mailbox.calendar.IcalXmlStrMap;

import er.calendar2.ERCalendarPrincipal;

public enum Transparency implements ICalendarProperty {

  OPAQUE("calendar.transparency.opaque", Transp.OPAQUE, IcalXmlStrMap.TRANSP_OPAQUE),
  TRANSPARENT("calendar.transparency.transparent", Transp.TRANSPARENT, IcalXmlStrMap.TRANSP_TRANSPARENT);
  
  private String localizedDescription;
  private Transp transpObject;
  private String zimbraValue;
  
  private Transparency(String localizedDescription, Transp transpObject, String zimbraValue) {
    this.localizedDescription = localizedDescription;
    this.transpObject = transpObject;
    this.zimbraValue = zimbraValue;
  }
    
  public String localizedDescription() {
    return ERCalendarPrincipal.localizer().localizedStringForKey(localizedDescription);
  }

  public Object rfc2445Value() {
    return transpObject;
  }
  
  public String zimbraValue() {
    return zimbraValue;
  }
      
  public static NSArray<Transparency> transparencies() {
    return new NSArray<Transparency>(Transparency.values());
  }
  
  private Transparency() {
  }
  
}
