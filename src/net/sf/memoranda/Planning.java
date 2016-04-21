package net.sf.memoranda;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;

/**
 * PSP Process class for storing and filling out the planning phase of the PSP.
 * 
 * @author sgarci33
 *
 */
public class Planning {

  private String programmer;
  private String programTitle;
  private String date;

  /**
   * Creates new Planning process class.
   * 
   * @param theAuthor the author of the program
   * @param theTitle  the name of the project
   * @param theDate   the date the project was created
   */
  public Planning(String theAuthor, String theTitle, String theDate) {
    programmer = theAuthor;
    date = theDate;
    programTitle = theTitle;
  }

  public Planning(){}

  public String getDate() {
    return date;
  }

  public String getProgramTitle() {
    return programTitle;
  }

  public String getProgrammer() {
    return programmer;
  }

  public void setDate(String newDate) {
    date = newDate;
  }

  public void setProgramTitle(String newTitle) {
    programTitle = newTitle;
  }

  public void setProgrammer(String newAuthor) {
    programmer = newAuthor;
  }
}
