package er.calendar2.enums;


import com.webobjects.foundation.NSArray;

public enum Transparency {

  OPAQUE("Les événements affectent la disponibilité","OPAQUE"),
  TRANSPARENT("Les événements n'affectent pas la disponibilité","TRANSPARENT");
  
  private String description;
  private String rfc2445Value;

  private Transparency(String description, String rfc2445Value) {
    this.description = description;
    this.rfc2445Value = rfc2445Value;
  }
    
  public String description() {
    return description;
  }
  
  public String rfc2445Value() {
    return rfc2445Value;
  }
  
  public static NSArray<Transparency> triggers() {
    return new NSArray<Transparency>(Transparency.values());
  }
  
  private Transparency() {
  }
  
}
