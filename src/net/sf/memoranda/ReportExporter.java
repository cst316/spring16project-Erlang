/**
 * 
 */
package net.sf.memoranda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import net.sf.memoranda.util.Local;

/**
 * @author ty124
 *
 */
public class ReportExporter {
	
	public static void exportReport(Report aReport, String aFilename)
	{
		try {
			File desktopFile = new File(System.getProperty("user.home"), "/Desktop/"+aFilename+".html"); 
			writeReportToFile( aReport, desktopFile.getPath() );
			JOptionPane.showMessageDialog(null,Local.getString("Document was created successfully in your /Desktop folder"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			try {
				writeReportToFile( aReport, aFilename+"html" );
				JOptionPane.showMessageDialog(null,Local.getString("Document was created successfully in the Memoranda folder"));
			} catch (FileNotFoundException exc) {
				exc.printStackTrace();
				JOptionPane.showMessageDialog(null,Local.getString("Failed to create file to write report to"));
			}
			
		}
	}
	
	private static void writeReportToFile(Report aReport, String aFilename) throws FileNotFoundException 
	{
		PrintWriter out = new PrintWriter(aFilename);
		StringBuilder theStringBuilder = new StringBuilder();
		String theProjectString = aReport.getProjectString();
		String theTasksString = aReport.getTasksString();
		String theNotesString = aReport.getNotesString();
		String theResourcesString = aReport.getResourcesString();
		if( theProjectString == null )
		{
			theStringBuilder.append("<p>Error Exporting Report</p>");
		}
		else
		{
			theStringBuilder.append(theProjectString);
		}
		if( theTasksString != null)
		{
			theStringBuilder.append(theTasksString);
		}
		if( theNotesString != null )
		{
			theStringBuilder.append(theNotesString);
		}
		if( theResourcesString != null )
		{
			theStringBuilder.append(theResourcesString);
		}
		out.println( theStringBuilder.toString() );
		out.close();
	}

}
