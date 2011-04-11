package er.calendar2.components;

import net.fortuna.ical4j.model.Calendar;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;

import er.extensions.components.ERXStatelessComponent;

public class ERPublishCalendarPage extends ERXStatelessComponent {

  private Calendar iCalObject;

  public ERPublishCalendarPage(WOContext context) {
    super(context);
  }

  public Calendar iCalObject() {
    return iCalObject;
  }

  public void setCalendar(Calendar iCalObject) {
    this.iCalObject = iCalObject;
  }

  @Override
  public void appendToResponse (WOResponse aResponse, WOContext aContext)
  {
    aResponse.setContentEncoding ("UTF-8");
    super.appendToResponse (aResponse, aContext);
    aResponse.setHeader ("text/calendar","content-type");
    aResponse.setContent (iCalObject.toString());
  }
}
