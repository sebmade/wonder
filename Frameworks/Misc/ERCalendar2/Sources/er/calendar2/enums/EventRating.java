package er.calendar2.enums;


import com.webobjects.foundation.NSArray;

public enum EventRating {

  ONE_STAR("*"),
  TWO_STARS("**"),
  THREE_STARS("***"),
  FOUR_STARS("**"),
  FIVE_STARS("**");
    
  private String description;

  private EventRating(String description) {
    this.description = description;
  }
  
  public String description() {
    return description;
  }
  
  public static NSArray<EventRating> triggers() {
    return new NSArray<EventRating>(EventRating.values());
  }
  
  private EventRating() {
  }
  
}
