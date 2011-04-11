package er.calendar2;

import net.fortuna.ical4j.model.component.VToDo;

import com.webobjects.foundation.NSTimestamp;

import er.calendar2.enums.IStatus;
import er.calendar2.enums.TaskStatus;

public class ERTask extends ERCalendarObject {

  private NSTimestamp completedOn;
  private NSTimestamp dueDate;
  private int percentComplete;
  private TaskStatus status;

  public ERTask(ERCalendar calendar) {
    super(calendar,new VToDo());
    calendar.addTask(this);
  }

  @Override
  public TaskStatus status() {
    return status;
  }

  @Override
  public void setStatus(IStatus status) {
    this.status = (TaskStatus)status;
  }

  public NSTimestamp completedOn() {
    return completedOn;
  }

  public void setCompletedOn(NSTimestamp completedOn) {
    this.completedOn = completedOn;
  }

  public NSTimestamp dueDate() {
    return dueDate;
  }

  public void setDueDate(NSTimestamp dueDate) {
    this.dueDate = dueDate;
  }
  
  public int percentComplete() {
    return percentComplete;
  }
  
  public void setPercentComplete(int percentComplete) {
    this.percentComplete = percentComplete;
  }
  
}
