package er.calendar2;

import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.property.Categories;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Duration;
import net.fortuna.ical4j.model.property.Geo;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Url;
import net.fortuna.ical4j.util.UidGenerator;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimeZone;
import com.webobjects.foundation.NSTimestamp;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.Element.XMLElement;
import com.zimbra.common.soap.MailConstants;
import com.zimbra.cs.mailbox.MailboxVersion;

import er.calendar2.enums.AlarmAction;
import er.calendar2.enums.Classification;
import er.calendar2.enums.FreeBusyStatus;
import er.calendar2.enums.IStatus;
import er.calendar2.enums.Priority;

public abstract class ERCalendarObject {

  private ERCalendar calendar;
  private NSMutableArray<Attendee> attendees;
  private NSMutableArray<Attendee> resources;
  private FreeBusyStatus freeBusyStatus;
  private String categories;
  private Classification classification;
  private String description;
  private NSTimestamp endTime;
  private NSTimestamp startTime;
  private Dur duration;
  private Geo geo;
  private String location;
  private Organizer organizer;
  private Priority priority;
  private String summary;
  private String url;
  protected CalendarComponent calComponent;
  private boolean isFullDay;
  private NSTimeZone timezone;
  private Alarm alarm;

  public ERCalendarObject(ERCalendar calendar, CalendarComponent calComponent) {
    this.calendar = calendar;
    this.calComponent = calComponent;
    this.attendees = new NSMutableArray<Attendee>();
    this.resources = new NSMutableArray<Attendee>();
  }

  public abstract IStatus status();
  public abstract void setStatus(IStatus status);

  protected CalendarComponent calComponent() {
    return calComponent;
  }

  protected PropertyList properties() {
    return calComponent.getProperties();
  }

  public NSArray<Attendee> attendees() {
    return this.attendees;
  }

  public void setAttendees(NSArray<Attendee> attendees) {
    this.attendees = attendees.mutableClone();
  }

  public void addAttendee(Attendee attendee) {
    this.attendees.addObject(attendee);
  }

  public NSArray<Attendee> resources() {
    return this.resources;
  }

  public void setResources(NSArray<Attendee> resources) {
    this.resources = resources.mutableClone();
  }

  public void addResource(Attendee resource) {
    this.resources.addObject(resource);
  }

  public FreeBusyStatus freeBusyStatus() {
    return freeBusyStatus;
  }

  public void setFreeBusyStatus(FreeBusyStatus freeBusyStatus) {
    this.freeBusyStatus = freeBusyStatus;
  }

  public String categories() {
    return categories;
  }

  public void setCategories(String categories) {
    this.categories = categories;
  }

  public Classification classification() {
    return classification;
  }

  public void setClassification(Classification classification) {
    this.classification = classification;
  }

  public String description() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public NSTimestamp endTime() {
    return endTime;
  }

  public void setEndTime(NSTimestamp endTime) {
    this.endTime = endTime;
    properties().add(new DtEnd(new Date(endTime.getTime())));
  }

  public NSTimestamp startTime() {
    return startTime;
  }

  public void setStartTime(NSTimestamp startTime) {
    this.startTime = startTime;
  }

  public Dur duration() {
    return duration;
  }

  public void setDuration(Dur duration) {
    this.duration = duration;
  }

  public Geo geo() {
    return geo;
  }

  public void setGeo(Geo geo) {
    this.geo = geo;
  }

  public String location() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Organizer organizer() {
    return organizer;
  }

  public void setOrganizer(Organizer organizer) {
    this.organizer = organizer;
  }

  public Priority priority() {
    return priority;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public String summary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String url() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isFullDay() {
    return isFullDay;
  }

  public void setIsFullDay(boolean isFullDay) {
    this.isFullDay = isFullDay;
  }

  public NSTimeZone timezone() {
    return timezone;
  }

  public void setTimezone(NSTimeZone timezone) {
    this.timezone = timezone;
  }

  public Alarm alarm() {
    return alarm;
  }

  public void setAlarm(Alarm alarm) {
    this.alarm = alarm;
  }

  public CalendarComponent transformToICalObject() throws SocketException, ParseException, URISyntaxException {
    UidGenerator ug = new UidGenerator("1");
    this.calComponent.getProperties().add(ug.generateUid());

    for (Attendee attendee: attendees) {
      properties().add(attendee);      
    }
    for (Attendee resource: resources) {
      properties().add(resource);      
    }
    if (freeBusyStatus != null) {
      properties().add(freeBusyStatus.rfc2445Value());
    }
    if (categories != null) {
      properties().add(new Categories(categories));
    }
    if (classification != null) {
      properties().add(classification.rfc2445Value());
    }
    properties().add(new Description(description));
    if (endTime != null) {
      if (isFullDay) {
        properties().add(new DtEnd(new Date(endTime.getTime())));
      } else {        
        properties().add(new DtEnd(new DateTime(endTime.getTime())));
      }
    }
    if (startTime != null) {
      if (isFullDay) {
        properties().add(new DtStart(new Date(startTime.getTime())));
      } else {        
        properties().add(new DtStart(new DateTime(startTime.getTime())));
      }
    }
    if (duration != null) {
      properties().add(new Duration(duration));
    }
    if (geo != null) {
      properties().add(geo);
    }
    if (location != null) {
      properties().add(new Location(location));
    }
    if (organizer != null) {
      properties().add(organizer);
    }
    if (priority != null) {
      properties().add(priority.rfc2445Value());
    }
    properties().add(new Summary(summary));
    if (url != null) {
      properties().add(new Url(new URI(url)));
    }
    return calComponent;
  }

  public Element transformToZimbraObject() {

    Element inviteComponent = new XMLElement(MailConstants.E_INVITE_COMPONENT);
    inviteComponent.addAttribute(MailConstants.A_APPT_FREEBUSY, freeBusyStatus.zimbraValue());
    inviteComponent.addAttribute(MailConstants.A_CAL_CLASS, classification.zimbraValue());
    if (isFullDay) {
      inviteComponent.addAttribute(MailConstants.A_CAL_ALLDAY, 1);
    } else {
      inviteComponent.addAttribute(MailConstants.A_CAL_ALLDAY, 0);
    }
    inviteComponent.addAttribute(MailConstants.A_CAL_RULE_XNAME_NAME, summary);

    Element dateDebut = inviteComponent.addElement(MailConstants.E_CAL_START_TIME);
    dateDebut.addAttribute(MailConstants.E_CAL_TZ, timezone.getID());
    dateDebut.addAttribute(MailConstants.A_CAL_DATETIME, new DateTime(startTime.getTime()).toString());

    Element dateFin = inviteComponent.addElement(MailConstants.E_CAL_START_TIME);
    dateFin.addAttribute(MailConstants.E_CAL_TZ, timezone.getID());
    dateFin.addAttribute(MailConstants.A_CAL_DATETIME,new DateTime(endTime.getTime()).toString());

    inviteComponent.addAttribute(MailConstants.A_CAL_LOCATION, location);

    Element organisateur = inviteComponent.addElement(MailConstants.E_CAL_ORGANIZER);
    organisateur.addAttribute(MailConstants.A_ADDRESS, organizer.emailAddress());
    organisateur.addAttribute(MailConstants.A_DISPLAY, organizer.name());

    for (Attendee attendee: attendees) {
      Element xmlAttendee = inviteComponent.addElement(MailConstants.E_CAL_ATTENDEE);
      xmlAttendee.addAttribute(MailConstants.A_ADDRESS, attendee.emailAddress());
      xmlAttendee.addAttribute(MailConstants.A_DISPLAY, attendee.name());
      xmlAttendee.addAttribute(MailConstants.A_CAL_CUTYPE, attendee.cutype().zimbraValue());
    }

    // cutype (calendar user type) = INDividual, GROup, RESource, ROOm, UNKnown
    for (Attendee resource: resources) {
      Element xmlResource = inviteComponent.addElement(MailConstants.E_CAL_ATTENDEE);
      xmlResource.addAttribute(MailConstants.A_ADDRESS, resource.emailAddress());
      xmlResource.addAttribute(MailConstants.A_DISPLAY, resource.name());
      xmlResource.addAttribute(MailConstants.A_CAL_CUTYPE, resource.cutype().zimbraValue());
    }

    inviteComponent.addAttribute("category", categories);

    Element alarme = inviteComponent.addElement(MailConstants.E_CAL_ALARM);
    alarme.addAttribute(MailConstants.A_CAL_ALARM_ACTION, alarm.action().toString());
    Element declencheur = alarme.addElement(MailConstants.E_CAL_ALARM_TRIGGER);
    if (alarm.isAbsolute()) {
      Element abs = declencheur.addElement(MailConstants.E_CAL_ALARM_ABSOLUTE);
      abs.addAttribute(MailConstants.A_DATE, new DateTime(alarm.alarmDate().getTime()).toString());
    } else {
      Element rel = declencheur.addElement(MailConstants.E_CAL_ALARM_RELATIVE);   
      if (alarm.isNegativeDuration()) {
        rel.addAttribute(MailConstants.A_CAL_DURATION_NEGATIVE, "0");
      } else {
        rel.addAttribute(MailConstants.A_CAL_DURATION_NEGATIVE, "1");        
      }
      rel.addAttribute(MailConstants.A_CAL_ALARM_RELATED, "START");
      rel.addAttribute(alarm.durationType().zimbraValue(), alarm.duration());
    }
    alarme.addAttribute(MailConstants.E_CAL_ALARM_DESCRIPTION, alarm.description());
    Element repeat = alarme.addElement(MailConstants.E_CAL_ALARM_REPEAT);
    repeat.addAttribute(MailConstants.A_CAL_ALARM_COUNT, alarm.repeatCount());
    if (alarm.action().equals(AlarmAction.EMAIL)) {
      alarme.addAttribute(MailConstants.E_CAL_ALARM_SUMMARY, alarm.emailSubject());
      Element xmlAttendee = alarme.addElement(MailConstants.E_CAL_ATTENDEE);
      xmlAttendee.addAttribute(MailConstants.A_ADDRESS, alarm.emailSubject());
    }

    inviteComponent.addAttribute(MailConstants.E_FRAG, description);

    return inviteComponent;
  }

}
