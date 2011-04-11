package er.calendar2.enums;


import com.webobjects.foundation.NSArray;

public enum Classification {

  PUBLIC("Public","PUB"),
  PRIVATE("Privé","PRI"),
  CONFIDENTIAL("Confidentiel","CON");
    
  private String description;
  private String rfc2445Value;

  private Classification(String description, String rfc2445Value) {
    this.description = description;
    this.rfc2445Value = rfc2445Value;
  }
    
  public String description() {
    return description;
  }
  
  public String rfc2445Value() {
    return rfc2445Value;
  }
  
  public static NSArray<Classification> clazzes() {
    return new NSArray<Classification>(Classification.values());
  }
  
  private Classification() {
  }
  
}
