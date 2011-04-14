package er.calendar2;

public abstract class Contact {

  private String emailAddress;
  private String url;
  private String name;
  private String sentBy;
  private String ldapUrl;
  private String language;
  
  public String emailAddress() {
    return emailAddress;
  }
  
  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }
  
  public String url() {
    return url;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  public String name() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String sentBy() {
    return sentBy;
  }
  
  public void setSentBy(String sentBy) {
    this.sentBy = sentBy;
  }
  
  public String ldapUrl() {
    return ldapUrl;
  }
  
  public void setLdapUrl(String ldapUrl) {
    this.ldapUrl = ldapUrl;
  }
  
  public String language() {
    return language;
  }
  
  public void setLanguage(String language) {
    this.language = language;
  }
   
}
