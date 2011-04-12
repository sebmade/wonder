package er.calendar2;

import java.net.SocketException;
import java.net.URISyntaxException;
import java.text.ParseException;

import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Status;
import er.calendar2.enums.EventStatus;
import er.calendar2.enums.IStatus;
import er.calendar2.enums.Transparency;

public class EREvent extends ERCalendarObject {

  private Transparency transparency;
  private EventStatus status;

  public EREvent(ERCalendar calendar) {
    super(calendar,new VEvent());
    calendar.addEvent(this);
  }

  @Override
  public EventStatus status() {
    return status;
  }

  @Override
  public void setStatus(IStatus status) {
    this.status = (EventStatus)status;
  }

  public Transparency transparency() {
    return transparency;
  }

  public void setTransparency(Transparency transparency) {
    this.transparency = transparency;
  }

  @Override
  public CalendarComponent transformToICalObject() throws SocketException, ParseException, URISyntaxException {
    VEvent event = (VEvent)super.transformToICalObject();
    if (transparency != null) {
      properties().add(transparency.transpObject());
    }
    if (status != null) {
      properties().add(new Status(status.rfc2445Value()));
    }
    return event;
  }

}
