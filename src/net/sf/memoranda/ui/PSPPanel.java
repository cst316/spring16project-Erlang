package net.sf.memoranda.ui;
//Testing 
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import net.sf.memoranda.SummaryObject;
/**
 * initializes the PSPPanel to display different PSP Panes
 * @author Carlos
 *
 */
public class PSPPanel extends JPanel {
	
	//Tabs for PSP implementation
	JTabbedPane pspTabs = new JTabbedPane();
	JTabbedPane planningTabPane = new JTabbedPane();
	JTabbedPane developmentTabPane = new JTabbedPane();
	JTabbedPane postmortemTabPane = new JTabbedPane(); 

	JPanel planningPanel = new JPanel();
	JPanel developmentPanel = new JPanel();
	JPanel postmortemPanel = new JPanel();
	
	TimeEstimationPanel timeEstimationPanel = new TimeEstimationPanel();
	PlanningPanel planPanel = null;
	DesignPanel designPanel = new DesignPanel();
	CodeEstimationPanel codeEstimationPanel = null;
	CodingPanel codingPanel = new CodingPanel();
	SummaryPanel summaryPanel = new SummaryPanel();
	
	
	Object rowSummary[][] = { { " "," ", " ", " "," "},
            {"Summary ", " ", " ", " "," "},
            {"Minute/LOC ", " ", " ", " "," "}, 
            {"LOC/Hour ", " ", " ", " "," "},
            {"Defect/KLOC ", " ", " ", " "," "},
            {"Yield ", " ", " ", " "," "},
            {"A/FR ", " ", " ", " "," "},
            {"Program size (LOC) ", " ", " ", " "," "},
            {"Total New & Change ", " ", " ", " "," "},
            {"Maximum Size ", " ", " ", " "," "},
            {"Minimum Size ", " ", " ", " "," "},
            {" ", " ", " ", " "," "},
            {"Timein Phase (min.)","Plan", "Actual", "To Date", "To Date %"},
            {"Planning ", " ", " ", " "," "},
            {"Design ", " ", " ", " "," "},
            {"Code ", " ", " ", " "," "},
            {"Code Review ", " ", " ", " "," "},
            {"Compile ", " ", " ", " "," "},
            {"Test ", " ", " ", " "," "},
            {"Postmortem ", " ", " ", " "," "},
            {"  Total ", " ", " ", " "," "},
            {"Maximum Size ", " ", " ", " "," "},
            {"Minimum Size ", " ", " ", " "," "},
            {" ", " ", " ", " "," "},
            {"Defect Injected ","Plan", "Actual", "To Date", "To Date %"},
            {"Planning ", " ", " ", " "," "},
            {"Design ", " ", " ", " "," "},
            {"Code ", " ", " ", " "," "},
            {"Code Review ", " ", " ", " "," "},
            {"Compile ", " ", " ", " "," "},
            {"Test ", " ", " ", " "," "},
            {"  Total", " ", " ", " "," "}, 
            {" ", " ", " ", " "," "},
            {"Defect Removed ","Plan", "Actual", "To Date", "To Date %"},
            {"Planning ", " ", " ", " "," "},
            {"Design ", " ", " ", " "," "},
            {"Code ", " ", " ", " "," "},
            {"Code Review ", " ", " ", " "," "},
            {"Compile ", " ", " ", " "," "},
            {"Test ", " ", " ", " "," "},
            {"  Total ", " ", " ", " "," "}};
	Object columnSummary[] = { " ", "Plan", "Actual", "To Date", "To Date %"};

	
			
	ImageIcon web = new ImageIcon(
			net.sf.memoranda.ui.AppFrame.class
			.getResource("resources/icons/web.png"));
	/**
	 * 
	 * @param parent used to initialize planning panel tab
	 */
	public PSPPanel(DailyItemsPanel parent){
		planPanel = new PlanningPanel(parent);
		codeEstimationPanel = new CodeEstimationPanel();
		this.setLayout(new FlowLayout());
		this.initializeTabs();
	}
	
	
/**
 * This method initializes tabs for the PSP Panel. Each Tab will resemble Development,
 *  and Post mortem forms management for the convenience of Software Engineering Students
 * @return void
<<<<<<< HEAD
 * @author Carlos 
 * @version 1.0 
=======
 * @author Team Erlang
 * @version 2.0
>>>>>>> 0fd68408fa1526ba53fa6eaaaa080bf25b84c429
 */
	private void initializeTabs(){
		planningTabPane.addTab("Time Estimation", web, timeEstimationPanel, "Make Time Estimations");
		planningTabPane.addTab("Planning Notes", web, planPanel,"Edit the plan");	
		planningPanel.add(planningTabPane);
			
		
		developmentTabPane.addTab("Design", web, designPanel, "Sketch a plan");	//Development/Design
		developmentTabPane.addTab("Code Estimation", web, codeEstimationPanel,"Edit the plan");//title, icon, panel, hintText
		developmentTabPane.addTab("Coding", web, codingPanel,"Document your code"); //Development/Coding
		developmentPanel.add(developmentTabPane);
		
		postmortemTabPane.addTab("Summary", web, summaryPanel, "Tables");
		postmortemPanel.add(postmortemTabPane);

		pspTabs.addTab("Planning", planningPanel);		//Planning parent tab
		pspTabs.addTab("Development", web, developmentPanel, "Create the project"); //Development parent tab
	    pspTabs.addTab("Postmortem", web, postmortemPanel);		// Summary parent Tab
	    showSummay();
	    
	    this.add(pspTabs);
	}	
	
	
	/**
	 * Method creates a table on the summary tab 
	 */
	public void showSummay(){
		  //Future button to import data
		JButton b = new JButton("Click to import info");
			summaryPanel.add(b);
		   b.setSize(50,250);
		    b.setVisible(true);
		  
		 
		
	    

 //SummaryObject
	  SummaryObject x = new SummaryObject();
	  x.getDefectInjectedCompile();
	  Tables tableSummary = new Tables(rowSummary, columnSummary);	
	  JScrollPane scrollPane = new JScrollPane(tableSummary);
	  JPanel panel = new JPanel();
	  panel.add(scrollPane);
	  summaryPanel.add(new JScrollPane(panel));
	  scrollPane.setSize(500,1500);
	  summaryPanel.setVisible(true);	

	}
	
	
}
