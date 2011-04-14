package er.calendar2;

import java.net.SocketException;
import java.net.URISyntaxException;
import java.text.ParseException;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VToDo;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.zimbra.common.soap.Element;
import com.zimbra.common.soap.Element.XMLElement;
import com.zimbra.common.soap.MailConstants;

public class ERCalendar {

  private net.fortuna.ical4j.model.Calendar calendar;
  private NSMutableArray<EREvent> events;
  private NSMutableArray<ERTask> tasks;
  private ProdId productId;
  protected Version version;
  protected CalScale scale;

  public ERCalendar() {
    calendar = new net.fortuna.ical4j.model.Calendar();
    productId = new ProdId("-//Project Wonder//ERCalendar2//EN");
    version = Version.VERSION_2_0;
    scale = CalScale.GREGORIAN;
    events = new NSMutableArray<EREvent>();
    tasks = new NSMutableArray<ERTask>();
  }

  public ProdId productId() {
    return productId;
  }

  public void setProductId(ProdId productId) {
    this.productId = productId;
  }

  public NSArray<EREvent> events() {
    return events.immutableClone();
  }

  public void setEvents(NSArray<EREvent> events) {
    this.events = events.mutableClone();
  }

  public void addEvent(EREvent event) {
    this.events.addObject(event);
  }

  public NSArray<ERTask> tasks() {
    return tasks.immutableClone();
  }

  public void setTasks(NSArray<ERTask> tasks) {
    this.tasks = tasks.mutableClone();
  }

  public void addTask(ERTask task) {
    this.tasks.addObject(task);
  }

  public Calendar transformToICalObject() throws SocketException, ParseException, URISyntaxException {
    calendar.getProperties().add(productId());
    calendar.getProperties().add(version);
    calendar.getProperties().add(scale);
    for (EREvent event: events()) {
      VEvent vEvent = (VEvent)event.transformToICalObject();
      calendar.getComponents().add(vEvent);
    }
    for (ERTask task: tasks()) {
      VToDo vTodo = (VToDo)task.transformToICalObject();
      calendar.getComponents().add(vTodo);
    }
    return this.calendar;
  }
  
  public XMLElement transformToZimbraObject() throws SocketException, ParseException, URISyntaxException {
    XMLElement invitation = new XMLElement(MailConstants.E_INVITE);
    
    for (EREvent event: events()) {
      Element vEvent = (Element)event.transformToZimbraObject();
      invitation.addElement(vEvent);
    }
    for (ERTask task: tasks()) {
      Element vTodo = (Element)task.transformToZimbraObject();
      invitation.addElement(vTodo);
    }
    return invitation;
  }

}
