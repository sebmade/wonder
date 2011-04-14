package er.calendar2.enums;


import net.fortuna.ical4j.model.property.Action;

import com.webobjects.foundation.NSArray;
import com.zimbra.cs.mailbox.calendar.Alarm;

// TODO: alarme Facebook? IM? Appel d'un service par URL?

public enum AlarmAction {

  DISPLAY("Message", Action.DISPLAY, Alarm.Action.DISPLAY),
  AUDIO("Son", Action.AUDIO, Alarm.Action.AUDIO),
  EMAIL("Courriel", Action.EMAIL, Alarm.Action.EMAIL),
  PROCEDURE("Script", Action.PROCEDURE, Alarm.Action.PROCEDURE);
    
  private String description;
  private Action rfc2445Value;
  private Alarm.Action zimbraValue;

  private AlarmAction(String description, Action rfc2445Value, Alarm.Action zimbraValue) {
    this.description = description;
    this.zimbraValue = zimbraValue;
    this.rfc2445Value = rfc2445Value;
  }
  
  public String localizedDescription() {
    return description;
  }

  public Object rfc2445Value() {
    return rfc2445Value;
  }
  
  public Alarm.Action zimbraValue() {
    return zimbraValue;
  }
  
  public static NSArray<AlarmAction> actions() {
    return new NSArray<AlarmAction>(AlarmAction.values());
  }
  
  private AlarmAction() {
  }

  
}
