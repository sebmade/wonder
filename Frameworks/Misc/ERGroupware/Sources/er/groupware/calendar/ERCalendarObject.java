package er.groupware.calendar;

import java.math.BigDecimal;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
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
import com.webobjects.foundation.NSTimestamp;
import com.zimbra.common.service.ServiceException;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.Element.XMLElement;
import com.zimbra.common.soap.MailConstants;
import com.zimbra.cs.zclient.ZAlarm;
import com.zimbra.cs.zclient.ZAlarm.ZTriggerType;
import com.zimbra.cs.zclient.ZDateTime;
import com.zimbra.cs.zclient.ZInvite;
import com.zimbra.cs.zclient.ZInvite.ZAttendee;
import com.zimbra.cs.zclient.ZInvite.ZClass;
import com.zimbra.cs.zclient.ZInvite.ZComponent.ZReply;
import com.zimbra.cs.zclient.ZInvite.ZFreeBusyStatus;
import com.zimbra.cs.zclient.ZInvite.ZOrganizer;

import er.extensions.eof.ERXKey;
import er.extensions.validation.ERXValidationException;
import er.groupware.calendar.enums.AlarmAction;
import er.groupware.calendar.enums.AlarmDurationType;
import er.groupware.calendar.enums.AttendeeRole;
import er.groupware.calendar.enums.CUType;
import er.groupware.calendar.enums.Classification;
import er.groupware.calendar.enums.FreeBusyStatus;
import er.groupware.calendar.enums.IStatus;
import er.groupware.calendar.enums.ParticipantStatus;
import er.groupware.calendar.enums.Priority;

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
  private boolean isFullDay;
  private TimeZone timezone;
  private NSMutableArray<Alarm> alarms;
  private String uid;
  private NSTimestamp lastModifiedDate;
  private String parentId;

  public static final ERXKey<ERCalendar> CALENDAR = new ERXKey<ERCalendar>("calendar");
  public static final ERXKey<Attendee> ATTENDEES = new ERXKey<Attendee>("attendees");
  public static final ERXKey<Attendee> RESOURCES = new ERXKey<Attendee>("resources");
  public static final ERXKey<FreeBusyStatus> FREE_BUSY_STATUS = new ERXKey<FreeBusyStatus>("freeBusyStatus");
  public static final ERXKey<String> CATEGORIES = new ERXKey<String>("categories");
  public static final ERXKey<Classification> CLASSIFICATION = new ERXKey<Classification>("classification");
  public static final ERXKey<String> DESCRIPTION = new ERXKey<String>("description");
  public static final ERXKey<NSTimestamp> END_TIME = new ERXKey<NSTimestamp>("endTime");
  public static final ERXKey<NSTimestamp> START_TIME = new ERXKey<NSTimestamp>("startTime");
  public static final ERXKey<Dur> DURATION = new ERXKey<Dur>("duration");
  public static final ERXKey<Geo> GEO = new ERXKey<Geo>("geo");
  public static final ERXKey<String> LOCATION = new ERXKey<String>("location");
  public static final ERXKey<Organizer> ORGANIZER = new ERXKey<Organizer>("organizer");
  public static final ERXKey<Priority> PRIORITY = new ERXKey<Priority>("priority");
  public static final ERXKey<String> SUMMARY = new ERXKey<String>("summary");
  public static final ERXKey<String> URL = new ERXKey<String>("url");
  public static final ERXKey<Boolean> IS_FULL_DAY = new ERXKey<Boolean>("isFullDay");
  public static final ERXKey<TimeZone> TIMEZONE = new ERXKey<TimeZone>("timezone");
  public static final ERXKey<Alarm> ALARMS = new ERXKey<Alarm>("alarms");
  public static final ERXKey<String> UID = new ERXKey<String>("uid");
  public static final ERXKey<NSTimestamp> LAST_MODIFIED_DATE = new ERXKey<NSTimestamp>("lastModifiedDate");
  public static final ERXKey<String> PARENT_ID = new ERXKey<String>("parentId");

  protected ERCalendarObject() {
    
  }
  
  public ERCalendarObject(ERCalendar calendar, CalendarComponent calComponent) {
    this.calendar = calendar;
    this.attendees = new NSMutableArray<Attendee>();
    this.resources = new NSMutableArray<Attendee>();
    this.alarms = new NSMutableArray<Alarm>();
  }

  public abstract IStatus status();
  public abstract void setStatus(IStatus status);

  /*
  protected CalendarComponent calComponent() {
    return calComponent;
  }

  protected PropertyList properties() {
    return calComponent.getProperties();
  }
  */

  public String uid() {
    return uid;
  }
  
  public void setUid(String uid) {
    this.uid = uid;
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

  public TimeZone timezone() {
    return timezone;
  }

  public void setTimezone(TimeZone timezone) {
    this.timezone = timezone;
  }

  public NSArray<Alarm> alarms() {
    return alarms.immutableClone();
  }

  public void setAlarm(NSArray<Alarm> alarms) {
    this.alarms = alarms.mutableClone();
  }
  
  public void addToAlarms(Alarm alarm) {
    this.alarms.addObject(alarm);
  }
  
  public NSTimestamp lastModifiedDate() {
    return lastModifiedDate;
  }
  
  public void setLastModifiedDate(NSTimestamp lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }
  
  public String parentId() {
    return parentId;
  }
  
  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public static CalendarComponent transformToICalObject(ERCalendarObject calendarObject, CalendarComponent calComponent) throws SocketException, ParseException, URISyntaxException {
    UidGenerator ug = new UidGenerator("1");
    calendarObject.uid = ug.generateUid().getValue();
    calComponent.getProperties().add(calendarObject.uid);

    for (Attendee attendee: calendarObject.attendees) {
      calComponent.getProperties().add(attendee);      
    }
    for (Attendee resource: calendarObject.resources) {
      calComponent.getProperties().add(resource);      
    }
    if (calendarObject.freeBusyStatus != null) {
      calComponent.getProperties().add(calendarObject.freeBusyStatus.rfc2445Value());
    }
    if (calendarObject.categories != null) {
      calComponent.getProperties().add(new Categories(calendarObject.categories));
    }
    if (calendarObject.classification != null) {
      calComponent.getProperties().add(calendarObject.classification.rfc2445Value());
    }
    calComponent.getProperties().add(new Description(calendarObject.description));
    if (calendarObject.endTime != null) {
      if (calendarObject.isFullDay) {
        calComponent.getProperties().add(new DtEnd(new Date(calendarObject.endTime.getTime())));
      } else {        
        calComponent.getProperties().add(new DtEnd(new DateTime(calendarObject.endTime.getTime())));
      }
    }
    if (calendarObject.startTime != null) {
      if (calendarObject.isFullDay) {
        calComponent.getProperties().add(new DtStart(new Date(calendarObject.startTime.getTime())));
      } else {        
        calComponent.getProperties().add(new DtStart(new DateTime(calendarObject.startTime.getTime())));
      }
    }
    if (calendarObject.duration != null) {
      calComponent.getProperties().add(new Duration(calendarObject.duration));
    }
    if (calendarObject.geo != null) {
      calComponent.getProperties().add(calendarObject.geo);
    }
    if (calendarObject.location != null) {
      calComponent.getProperties().add(new Location(calendarObject.location));
    }
    if (calendarObject.organizer != null) {
      calComponent.getProperties().add(calendarObject.organizer);
    }
    if (calendarObject.priority != null) {
      calComponent.getProperties().add(calendarObject.priority.rfc2445Value());
    }
    calComponent.getProperties().add(new Summary(calendarObject.summary));
    if (calendarObject.url != null) {
      calComponent.getProperties().add(new Url(new URI(calendarObject.url)));
    }
    return calComponent;
  }

  public static void transformFromZimbraResponse(Element e, ERCalendarObject newObject) throws ServiceException {
    List<Element> alarms = e.listElements(MailConstants.A_CAL_ALARM);
    for (Element xmlAlarm: alarms) {
      Alarm newAlarm = new Alarm();
      ZAlarm zAlarm = new ZAlarm(xmlAlarm);
      newAlarm.setAction(AlarmAction.getByZimbraValue(zAlarm.getAction()));
      if (zAlarm.getTriggerType().equals(ZTriggerType.RELATIVE)) {
        newAlarm.setAbsolute(false);
        newAlarm.setDuration(zAlarm.getTriggerRelated().getMins());
      } else  {
        newAlarm.setAbsolute(true);
        //newAlarm.setAlarmDate(endTime);
      }
      newAlarm.setDescription(zAlarm.getDescription());
      List<ZInvite.ZAttendee> alarmAttendees = zAlarm.getAttendees();
      if (alarmAttendees != null) {
        for (ZInvite.ZAttendee alarmAttendee: alarmAttendees) {
          newAlarm.setEmailAddress(alarmAttendee.getAddress());
        }
      }
      newAlarm.setEmailSubject(zAlarm.getSummary());
      newAlarm.setRepeatCount(zAlarm.getRepeatCount());
      newObject.addToAlarms(newAlarm);
    }
    
    ZOrganizer zOrg = new ZOrganizer(e.getOptionalElement(MailConstants.E_CAL_ORGANIZER));
    Organizer organizer = new Organizer();
    organizer.setEmailAddress(zOrg.getEmailAddress().getAddress());
    organizer.setLdapUrl(zOrg.getDirectoryUrl());
    organizer.setName(zOrg.getPersonalName());
    organizer.setSentBy(zOrg.getSentBy());
    organizer.setUrl(zOrg.getUrl());
    newObject.setOrganizer(organizer);
    
    List<Element> attendees = e.listElements(MailConstants.E_CAL_ATTENDEE);
    for (Element attendee: attendees) {
      ZAttendee zAttendee = new ZAttendee(attendee);
      Attendee newAttendee = new Attendee();
      newAttendee.setEmailAddress(zAttendee.getAddress());
      newAttendee.setCutype(CUType.getByZimbraValue(zAttendee.getCalendarUserType()));
      //newAttendee.setDelegatedFrom(zAttendee.getDelegatedFrom());
      //newAttendee.setDelegatedTo(zAttendee.getDelegatedTo());
      newAttendee.setLdapUrl(zAttendee.getDirectoryUrl());
      newAttendee.setMemberOf(zAttendee.getMember());
      newAttendee.setPartStat(ParticipantStatus.getByZimbraValue(zAttendee.getParticipantStatus()));
      newAttendee.setName(zAttendee.getPersonalName());
      newAttendee.setRole(AttendeeRole.getByZimbraValue(zAttendee.getRole()));
      newAttendee.setSentBy(zAttendee.getSentBy());
      newAttendee.setUrl(zAttendee.getUrl());
      newObject.addAttendee(newAttendee);
    }
    
    newObject.setClassification(Classification.getByZimbraValue(e.getAttribute(MailConstants.A_CAL_CLASS, ZClass.PUB.name())));
    newObject.setFreeBusyStatus(FreeBusyStatus.getByZimbraValue(e.getAttribute(MailConstants.A_APPT_FREEBUSY, ZFreeBusyStatus.B.name())));
    newObject.setIsFullDay(e.getAttributeBool(MailConstants.A_CAL_ALLDAY, false));
    newObject.setSummary(e.getAttribute(MailConstants.A_NAME, null));
    newObject.setLocation(e.getAttribute(MailConstants.A_CAL_LOCATION, null));

    Iterator<Element> catIter = e.elementIterator(MailConstants.E_CAL_CATEGORY);
    if (catIter.hasNext()) {
      List<String> categories = new ArrayList<String>();
      for (; catIter.hasNext(); ) {
        String cat = catIter.next().getTextTrim();
        categories.add(cat);
      }
      newObject.setCategories(categories.toString());
    }

    Iterator<Element> cmtIter = e.elementIterator(MailConstants.E_CAL_COMMENT);
    if (cmtIter.hasNext()) {
      List<String> comments = new ArrayList<String>();
      for (; cmtIter.hasNext(); ) {
        String cmt = cmtIter.next().getTextTrim();
        comments.add(cmt);
      }
      newObject.setDescription(comments.toString());
    }

    Iterator<Element> cnIter = e.elementIterator(MailConstants.E_CAL_CONTACT);
    if (cnIter.hasNext()) {
      List<String> contacts = new ArrayList<String>();
      for (; cnIter.hasNext(); ) {
        String cn = cnIter.next().getTextTrim();
        contacts.add(cn);
      }
    }

    Element geoElem = e.getOptionalElement(MailConstants.E_CAL_GEO);
    if (geoElem != null) {
      com.zimbra.cs.mailbox.calendar.Geo zGeo = com.zimbra.cs.mailbox.calendar.Geo.parse(geoElem);
      newObject.setGeo(new Geo(new BigDecimal(zGeo.getLatitude()), new BigDecimal(zGeo.getLongitude())));
    }

    newObject.setUrl(e.getAttribute(MailConstants.A_CAL_URL, null));

    newObject.setPriority(Priority.getByZimbraValue(e.getAttribute(MailConstants.A_CAL_PRIORITY, "0")));

    ArrayList<ZReply> mReplies = new ArrayList<ZReply>();
    Element repliesEl = e.getOptionalElement(MailConstants.E_CAL_REPLIES);
    if (repliesEl != null) {
      for (Element replyEl : repliesEl.listElements(MailConstants.E_CAL_REPLY)) {
        mReplies.add(new ZReply(replyEl));
      }
    }

    Element startEl = e.getOptionalElement(MailConstants.E_CAL_START_TIME);
    if (startEl != null) {
      ZDateTime zDate = new ZDateTime(startEl);
      newObject.setStartTime(new NSTimestamp(zDate.getDate()));
      newObject.setTimezone(TimeZone.getTimeZone(zDate.getTimeZone().getID()));
    }

    Element endEl = e.getOptionalElement(MailConstants.E_CAL_END_TIME);
    if (endEl != null) {
      ZDateTime zDate = new ZDateTime(endEl);
      newObject.setEndTime(new NSTimestamp(zDate.getDate()));
      newObject.setTimezone(TimeZone.getTimeZone(zDate.getTimeZone().getID()));
    }
    
    for (Element el: e.listElements(MailConstants.E_CAL_XPROP)) {
      String xPropName = el.getAttribute(MailConstants.A_NAME);
      String xPropValue = el.getAttribute(MailConstants.A_VALUE);
      if ("X-RELATED-TO".equals(xPropName)) {
        newObject.setParentId(xPropValue);
      }
    }
    
  }

  public static Element transformToZimbraObject(ERCalendarObject calendarObject) {

    Element inviteComponent = new XMLElement(MailConstants.E_INVITE_COMPONENT);
    inviteComponent.addAttribute(MailConstants.A_APPT_FREEBUSY, calendarObject.freeBusyStatus.zimbraValue());
    inviteComponent.addAttribute(MailConstants.A_CAL_CLASS, calendarObject.classification.zimbraValue());
    if (calendarObject.isFullDay) {
      inviteComponent.addAttribute(MailConstants.A_CAL_ALLDAY, 1);
    } else {
      inviteComponent.addAttribute(MailConstants.A_CAL_ALLDAY, 0);
    }
    inviteComponent.addAttribute(MailConstants.A_CAL_RULE_XNAME_NAME, calendarObject.summary);

    Element dateDebut = inviteComponent.addElement(MailConstants.E_CAL_START_TIME);
    dateDebut.addAttribute(MailConstants.E_CAL_TZ, calendarObject.timezone.getID());
    dateDebut.addAttribute(MailConstants.A_CAL_DATETIME, new DateTime(calendarObject.startTime.getTime()).toString());

    Element dateFin = inviteComponent.addElement(MailConstants.E_CAL_END_TIME);
    dateFin.addAttribute(MailConstants.E_CAL_TZ, calendarObject.timezone.getID());
    dateFin.addAttribute(MailConstants.A_CAL_DATETIME,new DateTime(calendarObject.endTime.getTime()).toString());

    inviteComponent.addAttribute(MailConstants.A_CAL_LOCATION, calendarObject.location);

    if (calendarObject.organizer != null) {
      Element organisateur = inviteComponent.addElement(MailConstants.E_CAL_ORGANIZER);
      organisateur.addAttribute(MailConstants.A_ADDRESS, calendarObject.organizer.emailAddress());
      organisateur.addAttribute(MailConstants.A_DISPLAY, calendarObject.organizer.name());
    }

    for (Attendee attendee: calendarObject.attendees) {
      if (attendee.emailAddress() == null) {
        // TODO: localization for the error message
        throw new ERXValidationException("for zimbra servers, emailAddress or url must be specified", attendee, attendee.emailAddress());
      }
      Element xmlAttendee = inviteComponent.addElement(MailConstants.E_CAL_ATTENDEE);
      xmlAttendee.addAttribute(MailConstants.A_ADDRESS, attendee.emailAddress());
      xmlAttendee.addAttribute(MailConstants.A_DISPLAY, attendee.name());
      xmlAttendee.addAttribute(MailConstants.A_CAL_CUTYPE, attendee.cutype().zimbraValue().toString());
    }

    for (Attendee resource: calendarObject.resources) {
      if (resource.emailAddress() == null) {
        // TODO: localization for the error message
        throw new ERXValidationException("for zimbra servers, emailAddress or url must be specified", resource, resource.emailAddress());
      }
      Element xmlResource = inviteComponent.addElement(MailConstants.E_CAL_ATTENDEE);
      xmlResource.addAttribute(MailConstants.A_ADDRESS, resource.emailAddress());                
      xmlResource.addAttribute(MailConstants.A_DISPLAY, resource.name());
      xmlResource.addAttribute(MailConstants.A_CAL_CUTYPE, resource.cutype().zimbraValue().toString());
    }

    inviteComponent.addAttribute("category", calendarObject.categories);

    for (Alarm alarm: calendarObject.alarms) {
      Element alarme = inviteComponent.addElement(MailConstants.E_CAL_ALARM);
      alarme.addAttribute(MailConstants.A_CAL_ALARM_ACTION, alarm.action().toString());
      Element declencheur = alarme.addElement(MailConstants.E_CAL_ALARM_TRIGGER);
      if (alarm.isAbsolute()) {
        Element abs = declencheur.addElement(MailConstants.E_CAL_ALARM_ABSOLUTE);
        abs.addAttribute(MailConstants.A_DATE, new DateTime(alarm.alarmDate().getTime()).toString());
      } else {
        Element rel = declencheur.addElement(MailConstants.E_CAL_ALARM_RELATIVE);   
        if (alarm.isNegativeDuration()) {
          rel.addAttribute(MailConstants.A_CAL_DURATION_NEGATIVE, "1");
        } else {
          rel.addAttribute(MailConstants.A_CAL_DURATION_NEGATIVE, "0");        
        }
        rel.addAttribute(MailConstants.A_CAL_ALARM_RELATED, "START");
        if (alarm.durationType() != null) {
          rel.addAttribute(alarm.durationType().zimbraValue(), alarm.duration());          
        } else {
          rel.addAttribute(AlarmDurationType.MINUTES.zimbraValue(), alarm.duration());
        }
      }
      alarme.addAttribute(MailConstants.E_CAL_ALARM_DESCRIPTION, alarm.description());
      Element repeat = alarme.addElement(MailConstants.E_CAL_ALARM_REPEAT);
      repeat.addAttribute(MailConstants.A_CAL_ALARM_COUNT, alarm.repeatCount());
      if (alarm.action().equals(AlarmAction.EMAIL)) {
        alarme.addAttribute(MailConstants.E_CAL_ALARM_SUMMARY,alarm.emailSubject());
        Element xmlAttendee = alarme.addElement(MailConstants.E_CAL_ATTENDEE);
        xmlAttendee.addAttribute(MailConstants.A_ADDRESS, alarm.emailSubject());
      }
    }

    inviteComponent.addAttribute(MailConstants.E_FRAG, calendarObject.description);

    if (calendarObject.parentId() != null) {
      Element relatedTo = inviteComponent.addElement(MailConstants.E_CAL_XPROP);
      relatedTo.addAttribute(MailConstants.A_NAME,"X-RELATED-TO");
      relatedTo.addAttribute(MailConstants.A_VALUE, calendarObject.parentId());
    }
    
    return inviteComponent;
  }
  
  public boolean isEnded() {
    NSTimestamp now = new NSTimestamp();
    if (now.after(this.endTime())) {
      return true;
    }
    return false;
  }

}
