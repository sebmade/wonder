package er.calendar2;

import java.net.SocketException;
import java.net.URI;
import java.text.ParseException;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.BusyType;
import net.fortuna.ical4j.model.property.Categories;
import net.fortuna.ical4j.model.property.Clazz;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Duration;
import net.fortuna.ical4j.model.property.Geo;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.Resources;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Url;
import net.fortuna.ical4j.util.UidGenerator;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSTimestamp;

import er.calendar2.enums.Classification;
import er.calendar2.enums.FreeBusyStatus;
import er.calendar2.enums.IStatus;
import er.calendar2.enums.Priority;

public abstract class ERCalendarObject {

  private ERCalendar calendar;
  private NSMutableArray<Attendee> attendees;
  private NSMutableArray<Resources> resources;
  private FreeBusyStatus freeBusyStatus;
  private String categories;
  private Classification classification;
  private String description;
  private NSTimestamp endTime;
  private NSTimestamp startTime;
  private Dur duration;
  private Geo geo;
  private Location location;
  private Organizer organizer;
  private Priority priority;
  private String summary;
  private URI url;
  protected CalendarComponent calComponent;
  private boolean isFullDay;

  public ERCalendarObject(ERCalendar calendar, CalendarComponent calComponent) {
    this.calendar = calendar;
    this.calComponent = calComponent;
    this.attendees = new NSMutableArray<Attendee>();
    this.resources = new NSMutableArray<Resources>();
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

  public NSArray<Resources> resources() {
    return this.resources;
  }

  public void setResources(NSArray<Resources> resources) {
    this.resources = resources.mutableClone();
  }

  public void addResource(Resources resource) {
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

  public Location location() {
    return location;
  }

  public void setLocation(Location location) {
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

  public URI url() {
    return url;
  }

  public void setUrl(URI url) {
    this.url = url;
  }
  
  public boolean isFullDay() {
    return isFullDay;
  }
  
  public void setIsFullDay(boolean isFullDay) {
    this.isFullDay = isFullDay;
  }

  public CalendarComponent transformToICalObject() throws SocketException, ParseException {
    UidGenerator ug = new UidGenerator("1");
    this.calComponent.getProperties().add(ug.generateUid());

    for (Attendee attendee: attendees) {
      properties().add(attendee);      
    }
    for (Resources resource: resources) {
      properties().add(resource);      
    }
    if (freeBusyStatus != null) {
      properties().add(new BusyType(freeBusyStatus.rfc2445Value()));
    }
    if (categories != null) {
      properties().add(new Categories(categories));
    }
    if (classification != null) {
      properties().add(new Clazz(classification.rfc2445Value()));
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
      properties().add(location);
    }
    if (organizer != null) {
      properties().add(organizer);
    }
    if (priority != null) {
      properties().add(priority.rfc2445Value());
    }
    properties().add(new Summary(summary));
    if (url != null) {
      properties().add(new Url(url));
    }
    return calComponent;
  }

}
