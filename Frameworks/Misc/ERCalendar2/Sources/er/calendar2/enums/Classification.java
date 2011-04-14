package er.calendar2.enums;


import net.fortuna.ical4j.model.property.Clazz;

import com.webobjects.foundation.NSArray;

import er.calendar2.ERCalendarPrincipal;

public enum Classification implements ICalendarProperty {

  PUBLIC("Public", Clazz.PUBLIC, "PUB"),
  PRIVATE("Privé", Clazz.PRIVATE, "PRI"),
  CONFIDENTIAL("Confidentiel", Clazz.CONFIDENTIAL, "CON");
    
  private String localizedDescription;
  private Clazz rfc2445Value;
  private String zimbraValue;

  private Classification(String localizedDescription, Clazz rfc2445Value, String zimbraValue) {
    this.localizedDescription = localizedDescription;
    this.rfc2445Value = rfc2445Value;
    this.zimbraValue = zimbraValue;
  }
    
  public String localizedDescription() {
    return ERCalendarPrincipal.localizer().localizedStringForKey(localizedDescription);
  }
  
  public Clazz rfc2445Value() {
    return rfc2445Value;
  }
  
  public String zimbraValue() {
    return zimbraValue;
  }
  
  public static NSArray<Classification> clazzes() {
    return new NSArray<Classification>(Classification.values());
  }
  
  private Classification() {
  }
  
}
