package er.calendar2.enums;


import com.webobjects.foundation.NSArray;

// TODO: alarme Facebook? IM? Appel d'un service par URL?

public enum AlarmAction {

  DISPLAY("Message","DISPLAY"),
  AUDIO("Son","AUDIO"),
  EMAIL("Courriel", "EMAIL"),
  PROCEDURE("Script","PROCEDURE");
    
  private String description;
  private String zimbraValue;

  private AlarmAction(String description, String zimbraValue) {
    this.description = description;
    this.zimbraValue = zimbraValue;
  }
  
  public String description() {
    return description;
  }
  
  public String zimbraValue() {
    return zimbraValue;
  }
  
  public static NSArray<AlarmAction> actions() {
    return new NSArray<AlarmAction>(AlarmAction.values());
  }
  
  private AlarmAction() {
  }
  
}
