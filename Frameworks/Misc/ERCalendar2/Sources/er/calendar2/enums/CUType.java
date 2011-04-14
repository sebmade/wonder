package er.calendar2.enums;

import net.fortuna.ical4j.model.parameter.CuType;

import com.webobjects.foundation.NSArray;
import com.zimbra.cs.mailbox.calendar.IcalXmlStrMap;

import er.calendar2.ERCalendarPrincipal;

public enum CUType implements ICalendarProperty {

  INDIVIDUAL("Individu", CuType.INDIVIDUAL, IcalXmlStrMap.CUTYPE_INDIVIDUAL),
  GROUP("Groupe", CuType.GROUP, IcalXmlStrMap.CUTYPE_GROUP),
  RESOURCE("Ressource", CuType.RESOURCE, IcalXmlStrMap.CUTYPE_RESOURCE),
  ROOM("Salle", CuType.ROOM, IcalXmlStrMap.CUTYPE_ROOM),
  UNKNOW("Salle", CuType.UNKNOWN, IcalXmlStrMap.CUTYPE_UNKNOWN);

  private String localizedDescription;
  private CuType rfc2445Value;
  private String zimbraValue;

  private CUType(String localizedDescription, CuType rfc2445Value, String zimbraValue) {
    this.localizedDescription = localizedDescription;
    this.rfc2445Value = rfc2445Value;
    this.zimbraValue = zimbraValue;
  }

  public String localizedDescription() {
    return ERCalendarPrincipal.localizer().localizedStringForKey(localizedDescription);
  }

  public CuType rfc2445Value() {
    return rfc2445Value;
  }

  public String zimbraValue() {
    return zimbraValue;
  }

  public static NSArray<CUType> types() {
    return new NSArray<CUType>(CUType.values());
  }

  private CUType() {
  }

}
