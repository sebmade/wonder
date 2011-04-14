package er.calendar2;

import com.webobjects.foundation.NSTimestamp;

import er.calendar2.enums.AlarmAction;
import er.calendar2.enums.AlarmDurationType;

public class Alarm {

  private AlarmAction action;
  private int duration;
  private int repeatCount;
  private boolean isNegativeDuration;
  private String description;
  private boolean isAbsolute;
  private NSTimestamp alarmDate;
  private AlarmDurationType durationType;
  private String emailAddress;
  private String emailSubject;
  
  public Alarm() {
  }

  public AlarmAction action() {
    return action;
  }

  public void setAction(AlarmAction action) {
    this.action = action;
  }

  public int duration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int repeatCount() {
    return repeatCount;
  }

  public void setRepeatCount(int repeatCount) {
    this.repeatCount = repeatCount;
  }

  public boolean isNegativeDuration() {
    return isNegativeDuration;
  }

  public void setNegativeDuration(boolean isNegativeDuration) {
    this.isNegativeDuration = isNegativeDuration;
  }

  public String description() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isAbsolute() {
    return isAbsolute;
  }

  public void setAbsolute(boolean isAbsolute) {
    this.isAbsolute = isAbsolute;
  }

  public NSTimestamp alarmDate() {
    return alarmDate;
  }

  public void setAlarmDate(NSTimestamp alarmDate) {
    this.alarmDate = alarmDate;
  }

  public AlarmDurationType durationType() {
    return durationType;
  }

  public void setDurationType(AlarmDurationType durationType) {
    this.durationType = durationType;
  }

  public String emailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String emailSubject() {
    return emailSubject;
  }

  public void setEmailSubject(String emailSubject) {
    this.emailSubject = emailSubject;
  }
  
}
