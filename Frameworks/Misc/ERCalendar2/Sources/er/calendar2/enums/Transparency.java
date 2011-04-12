package er.calendar2.enums;


import net.fortuna.ical4j.model.property.Transp;

import com.webobjects.foundation.NSArray;

import er.extensions.foundation.ERXProperties;
import er.extensions.localization.ERXLocalizer;

public enum Transparency  {

  OPAQUE("calendar.transparency.opaque", Transp.OPAQUE),
  TRANSPARENT("calendar.transparency.transparent", Transp.TRANSPARENT);
  
  private String localizedDescription;
  private Transp transpObject;
  ERXLocalizer l = ERXLocalizer.localizerForLanguages(ERXProperties.arrayForKey("er.extensions.ERXLocalizer.availableLanguages"));
  
  private Transparency(String description, Transp transpObject) {
    this.localizedDescription = description;
    this.transpObject = transpObject;
  }
    
  public String localizedDescription() {
    return l.localizedStringForKey(localizedDescription);
  }
  
  public Transp transpObject() {
    return transpObject;
  }
    
  public static NSArray<Transparency> transparencies() {
    return new NSArray<Transparency>(Transparency.values());
  }
  
  private Transparency() {
  }
  
}
