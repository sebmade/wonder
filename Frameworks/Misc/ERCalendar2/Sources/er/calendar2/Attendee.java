package er.calendar2;

import er.calendar2.enums.AttendeeRole;
import er.calendar2.enums.CUType;
import er.calendar2.enums.ParticipantStatus;

public class Attendee extends Contact {

  private AttendeeRole role;
  private ParticipantStatus partStat;
  private boolean rsvp;
  private CUType cutype;
  private String memberOf; // http://tools.ietf.org/html/rfc2445#section-4.2.11
  private Contact delegatedFrom;
  private Contact delegatedTo;
  
  public Attendee() {
    
  }

  public AttendeeRole role() {
    return role;
  }

  public void setRole(AttendeeRole role) {
    this.role = role;
  }

  public ParticipantStatus partStat() {
    return partStat;
  }

  public void setPartStat(ParticipantStatus partStat) {
    this.partStat = partStat;
  }

  public boolean isRsvp() {
    return rsvp;
  }

  public void setRsvp(boolean rsvp) {
    this.rsvp = rsvp;
  }

  public CUType cutype() {
    return cutype;
  }

  public void setCutype(CUType cutype) {
    this.cutype = cutype;
  }

  public String memberOf() {
    return memberOf;
  }

  public void setMemberOf(String memberOf) {
    this.memberOf = memberOf;
  }

  public Contact delegatedFrom() {
    return delegatedFrom;
  }

  public void setDelegatedFrom(Contact delegatedFrom) {
    this.delegatedFrom = delegatedFrom;
  }

  public Contact delegatedTo() {
    return delegatedTo;
  }

  public void setDelegatedTo(Contact delegatedTo) {
    this.delegatedTo = delegatedTo;
  }
    
}
