package net.sf.memoranda.ui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
/**
 * initializes the PSPPanel to display different PSP Panes
 * @author Carlos
 * @version 2.0
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
	
	PlanningPanel planPanel = null;
	DesignPanel designPanel = new DesignPanel();
	EstimationPanel estimationPanel = null;
	CodingPanel codingPanel = new CodingPanel(); 
	SummaryPanel summaryPanel = new SummaryPanel();
	StopWatch watch;

	
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
	public PSPPanel(WorkPanel _parentPanel){
		super(new BorderLayout());
		this.setPreferredSize(_parentPanel.getPreferredSize());
		watch = new StopWatch();
		pspTabs.setPreferredSize(new Dimension(920,670));
		watch.setPreferredSize(new Dimension(300,670));
		DailyItemsPanel dPanel = new DailyItemsPanel(_parentPanel);	
		planPanel = new PlanningPanel(dPanel);						
		ImportDataTimer testing = new ImportDataTimer();
		estimationPanel = new EstimationPanel();
		this.initializeTabs();
		
		this.add(watch, BorderLayout.EAST);
		this.add(pspTabs, BorderLayout.CENTER);
		
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
<<<<<<< HEAD
 * @version 3.0
=======
 * @version 2.0
>>>>>>> 0fd68408fa1526ba53fa6eaaaa080bf25b84c429
>>>>>>> master
 */
	private void initializeTabs(){

		
		planningTabPane.addTab("Planning", web, planPanel,"Edit the plan");		//title, icon, panel, hintText

		planningPanel.add(planningTabPane);
			
		
		developmentTabPane.addTab("Design", web, designPanel, "Sketch a plan");	//Development/Design
		developmentTabPane.addTab("Coding", web, codingPanel,"Document your code"); //Development/Coding
		developmentPanel.add(developmentTabPane);
		
		postmortemTabPane.addTab("Summary", web, summaryPanel, "Tables");
		postmortemPanel.add(postmortemTabPane);
		
		JScrollPane planScroller = new JScrollPane(planningPanel);
		JScrollPane devScroller = new JScrollPane(developmentPanel);
		
		pspTabs.add("Planning",planScroller);
		pspTabs.add("Development",devScroller);
	    pspTabs.addTab("Postmortem", web, postmortemPanel);		// Summary parent Tab
	    showSummay();

	    this.add(pspTabs);
	}	
	
	
	/**
	 * Method creates a table on the summary tab 
	 */
	public void showSummay(){
		/*  Future button to import data
		JButton b = new JButton("Click for import info");
			summaryPanel.add(b);
		   b.setSize(50,250);
		    b.setVisible(true);
		  */

	  Tables tableSummary = new Tables(rowSummary, columnSummary);	
	  JScrollPane scrollPane = new JScrollPane(tableSummary);
	  JPanel panel = new JPanel();
	  panel.add(scrollPane);
	  summaryPanel.add(new JScrollPane(panel));
	  scrollPane.setSize(500,1500);
	  summaryPanel.setVisible(true);	
	}
	
	
}
