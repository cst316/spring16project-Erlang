/**
 * 
 */
package net.sf.memoranda;

import javax.swing.text.Document;

import net.sf.memoranda.Report;
import net.sf.memoranda.ReportSettings;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.CurrentStorage;

/**
 * @author ty124
 *
 */
public class ReportGenerator 
{
	public static Report generateReport( ReportSettings aReportSettings )
	{
		Report theReport = new Report();
		theReport.setProjectString( generateProjectString( aReportSettings ) ); 
		if( aReportSettings.isWithTasks() )
		{
			theReport.setTasksString(generateTasksString( aReportSettings ));
		}
		if( aReportSettings.isWithNotes() )
		{
			theReport.setNotesString(generateNotesString());
		}
		if( aReportSettings.isWithResources() )
		{
			theReport.setResourcesString(generateResourcesString());
		}
		return theReport;
	}
	
	private static String generateProjectString( ReportSettings aReportSettings )
	{
		StringBuilder theProjectStringBuilder = new StringBuilder();
		Project theCurrentProject = CurrentProject.get();
		theProjectStringBuilder.append( "<h1>Project: " + theCurrentProject.getTitle() + "</h1>\n<p>\n" );
		if( aReportSettings.isWithProjectID() )
		{
			theProjectStringBuilder.append( "<b>ID:</b> " + theCurrentProject.getID() + "<br>\n" );
		}
		if( aReportSettings.isWithProjectDates() )
		{
			theProjectStringBuilder.append( "<b>Start Date:</b> " + theCurrentProject.getStartDate().toString() + " " +
					                        "<b>End Date:</b> " + theCurrentProject.getEndDate() + "<br>\n" );
		}
		if( aReportSettings.isWithProjectStatus() )
		{
			String theStatusString = "error"; 
			int theCurrentStatus = theCurrentProject.getStatus();
			switch( theCurrentStatus )
			{
			case 0:
				theStatusString = "Scheduled";
				break;
			case 1:
				theStatusString = "Active";
				break;
			case 2:
				theStatusString = "Completed";
				break;
			case 4:
				theStatusString = "Frozen";
				break;
			case 5:
				theStatusString = "Failed";
				break;
			default:
				//do nothing
			}
			theProjectStringBuilder.append( "<b>Status:</b> "+ theStatusString + "<br>\n" );
		}
		if( aReportSettings.isWithProjectDescription() )
		{
			theProjectStringBuilder.append( "<b>Description:</b> " + theCurrentProject.getDescription() + "<br>\n" );
		}
		theProjectStringBuilder.append("</p>\n");
		return theProjectStringBuilder.toString();
	}
	
	private static String generateTasksString( ReportSettings aReportSettings )
	{
		StringBuilder theTasksStringBuilder = new StringBuilder();
		theTasksStringBuilder.append("<h2>Tasks: ");
		
		TaskList theTaskList = CurrentProject.getTaskList();
		Object [] theTopLevelTasks = theTaskList.getTopLevelTasks().toArray();
		theTasksStringBuilder.append(theTopLevelTasks.length+"</h2>\n<p>\n");
		for( int i = 0; i < theTopLevelTasks.length; i++ )
		{
			Task theTask = (Task)theTopLevelTasks[i];
			theTasksStringBuilder.append(generateSingleTaskString(aReportSettings, theTask, false));
			if( aReportSettings.isWithTaskSubTasks() )
			{
				
				Object [] theSubTasks = theTask.getSubTasks().toArray();
				for( int j = 0; j < theSubTasks.length; j++ )
				{
					Task theSubTask = (Task)theSubTasks[j];
					theTasksStringBuilder.append( "&nbsp&nbsp&nbsp&nbsp<b>" + theTask.getText()+" Sub Task - </b>");
					theTasksStringBuilder.append(generateSingleTaskString(aReportSettings, theSubTask, true));
				}
			}
		}
		theTasksStringBuilder.append("</p>\n");
		return theTasksStringBuilder.toString();
	}
	
	private static String generateSingleTaskString( ReportSettings aReportSettings, Task aTask, boolean isSubTask )
	{
		StringBuilder theTaskStringBuilder = new StringBuilder();
		theTaskStringBuilder.append( "<b>" + aTask.getText() + ":</b><br>\n" );
		if( aReportSettings.isWithTaskIDs() )
		{
			if( isSubTask )
			{
				theTaskStringBuilder.append("&nbsp&nbsp&nbsp&nbsp");
			}
			theTaskStringBuilder.append("<b>ID:</b> "+ aTask.getID() + "<br>\n" );
		}
		
		if( aReportSettings.isWithTaskDates() )
		{
			if( isSubTask )
			{
				theTaskStringBuilder.append("&nbsp&nbsp&nbsp&nbsp");
			}
			theTaskStringBuilder.append( "<b>Start Date:</b> " + aTask.getStartDate().toString() + " " +
                                          "<b>End Date:</b> " + aTask.getEndDate() + "<br>\n" );
		}
		if( aReportSettings.isWithTaskPriority() )
		{
			String thePriorityString = "error"; 
			int thePriority = aTask.getPriority();
			switch( thePriority )
			{
			case 0:
				thePriorityString = "Lowest";
				break;
			case 1:
				thePriorityString = "Low";
				break;
			case 2:
				thePriorityString = "Normal";
				break;
			case 3:
				thePriorityString = "High";
				break;
			case 4:
				thePriorityString = "Highest";
				break;
			}
			if( isSubTask )
			{
				theTaskStringBuilder.append("&nbsp&nbsp&nbsp&nbsp");
			}
			theTaskStringBuilder.append( "<b>The Priority:</b>" + thePriorityString + "<br>\n" );
		}
		if( aReportSettings.isWithTaskProgress() )
		{
			if( isSubTask )
			{
				theTaskStringBuilder.append("&nbsp&nbsp&nbsp&nbsp");
			}
			theTaskStringBuilder.append( "<b>The Progress:</b> " +aTask.getProgress() + "<br>\n");
		}
		if( aReportSettings.isWithTaskStatus() )
		{
			String theStatusString = "error"; 
			int theCurrentStatus = aTask.getStatus(new CalendarDate());
			switch( theCurrentStatus )
			{
			case 0:
				theStatusString = "Scheduled";
				break;
			case 1:
				theStatusString = "Active";
				break;
			case 2:
				theStatusString = "Completed";
				break;
			case 4:
				theStatusString = "Frozen";
				break;
			case 5:
				theStatusString = "Failed";
				break;
			case 6:
				theStatusString = "Locked";
				break;
			case 7:
				theStatusString = "Deadline";
				break;
			default:
				//do nothing
			}
			if( isSubTask )
			{
				theTaskStringBuilder.append("&nbsp&nbsp&nbsp&nbsp");
			}
			theTaskStringBuilder.append( "<b>Status:</b> "+ theStatusString + "<br>\n" );
		}
		theTaskStringBuilder.append("<br>\n");
		return theTaskStringBuilder.toString();
	}
	
	private static String generateNotesString() 
	{
		StringBuilder theNotesStringBuilder = new StringBuilder();
		theNotesStringBuilder.append("<h2>Notes:</h2>\n<p>");
		Object [] theNotes = CurrentProject.getNoteList().getAllNotes().toArray();
		int theNotesLength = theNotes.length;
		if( theNotesLength > 0 )
		{
			for( int i = 0; i < theNotes.length; i++ )
			{
				Note theNote = (Note)theNotes[i];
				theNotesStringBuilder.append("<b>"+(i+1)+")</b> "+theNote.getDate()+" -- "+ theNote.getTitle() +":<br>\n");
	            Document doc = CurrentStorage.get().openNote(theNote);
	            try {
	                String txt = doc.getText(0, doc.getLength());
	                theNotesStringBuilder.append(txt+"<br>\n");
	            }
	            catch (Exception ex) {
	                ex.printStackTrace();
	                theNotesStringBuilder.append("Failed Getting Note"+"<br>\n");
	            }
				
			}
		}
		else
		{
			theNotesStringBuilder.append("No Notes/n");
		}
		theNotesStringBuilder.append("<br>\n");
		return theNotesStringBuilder.toString();
	}
	
	private static String generateResourcesString() 
	{
		StringBuilder theResourcesStringBuilder = new StringBuilder();
		theResourcesStringBuilder.append("<h2>Resources:</h2>\n<p>");
		Object [] theResources = CurrentProject.getResourcesList().getAllResources().toArray();
		int theRescLength = theResources.length;
		if( theRescLength > 0 )
		{
			for( int i = 0; i < theRescLength; i++ )
			{
				Resource theResource = (Resource)theResources[i];
				theResourcesStringBuilder.append("<b>"+(i+1)+")</b> ");
				if( theResource.isInetShortcut() )
				{
					theResourcesStringBuilder.append("Internet Resource:");
				}
				else
				{
				    theResourcesStringBuilder.append("Local File:");
				}
				theResourcesStringBuilder.append(theResource.getPath()+"<br>\n");
			}
		}
		else 
		{
			theResourcesStringBuilder.append("No Resources\n");
		}
		theResourcesStringBuilder.append("<br>\n");
		return theResourcesStringBuilder.toString();
	}
}

