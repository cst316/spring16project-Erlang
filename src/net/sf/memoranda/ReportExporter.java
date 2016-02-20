/**
 * 
 */
package net.sf.memoranda;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author ty124
 *
 */
public class ReportExporter {
	
	public static void exportReport(Report aReport, String aFilename)
	{
//		try(  PrintWriter out = new PrintWriter( "filename.txt" )  ){
//		    out.println( text );
//		}
		try {
			PrintWriter out = new PrintWriter(aFilename+".html");
			StringBuilder theStringBuilder = new StringBuilder();
			String theProjectString = aReport.getProjectString();
			String theTasksString = aReport.getTasksString();
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
			out.println( theStringBuilder.toString() );
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
