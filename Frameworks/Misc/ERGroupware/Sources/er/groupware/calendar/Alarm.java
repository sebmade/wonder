package er.groupware.calendar;

import com.webobjects.foundation.NSTimestamp;

import er.extensions.eof.ERXKey;
import er.groupware.calendar.enums.AlarmAction;
import er.groupware.calendar.enums.AlarmDurationType;

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
  
  public static final ERXKey<AlarmAction> ACTION = new ERXKey<AlarmAction>("action");
  public static final ERXKey<Integer> DURATION = new ERXKey<Integer>("duration");
  public static final ERXKey<Integer> REPEAT_COUNT = new ERXKey<Integer>("repeatCount");
  public static final ERXKey<Boolean> IS_NEGATIVE_DURATION = new ERXKey<Boolean>("isNegativeDuration");
  public static final ERXKey<String> DESCRIPTION = new ERXKey<String>("description");
  public static final ERXKey<Boolean> IS_ABSOLUTE = new ERXKey<Boolean>("isAbsolute");
  public static final ERXKey<NSTimestamp> ALARM_DATE = new ERXKey<NSTimestamp>("alarmDate");
  public static final ERXKey<AlarmDurationType> DURATION_TYPE = new ERXKey<AlarmDurationType>("durationType");
  public static final ERXKey<String> EMAIL_ADDRESS = new ERXKey<String>("emailAddress");
  public static final ERXKey<String> EMAIL_SUBJECT = new ERXKey<String>("emailSubject");
  
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
