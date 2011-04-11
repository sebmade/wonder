package er.calendar2.enums;


import com.webobjects.foundation.NSArray;

public enum CUType {

  INDIVIDUAL("Individu", "IND"),
  GROUP("Groupe", "GRO"),
  RESOURCE("Ressource","RES"),
  ROOM("Salle","ROO");
  
  private String description;
  private String zimbraValue;

  private CUType(String description, String zimbraValue) {
    this.description = description;
    this.zimbraValue = zimbraValue;
  }
    
  public String description() {
    return description;
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
