package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
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
	EstimatePanel estimatePanel = new EstimatePanel();
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
		super(new GridBagLayout());
		this.setPreferredSize(_parentPanel.getPreferredSize());
		watch = new StopWatch();
		pspTabs.setPreferredSize(new Dimension(920,670));
		watch.setPreferredSize(new Dimension(300,670));
		DailyItemsPanel dPanel = new DailyItemsPanel(_parentPanel);	
		planPanel = new PlanningPanel(dPanel);						
		ImportDataTimer testing = new ImportDataTimer();
		estimationPanel = new EstimationPanel();
		this.initializeTabs();

		setUpDisplay();
	}
	/**
	 * This method setsup the PSPanel to display the stop watch and
	 * the PSPTabs together
	 */
	public void setUpDisplay(){
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		this.add(pspTabs,c);
		
		c.gridx = 1;
		this.add(watch, c);
	}
	
	
/**
 * This method initializes tabs for the PSP Panel. Each Tab will resemble Development,
 *  and Post mortem forms management for the convenience of Software Engineering Students
 * @return void
 * @author Team Erlang
 * @version 3.0
 */
	private void initializeTabs(){
		planningTabPane.addTab("Planning", web, planPanel,"Edit the plan");		//title, icon, panel, hintText
		planningPanel.add(planningTabPane);
		planningTabPane.addTab("Estimation", web, estimatePanel,"Edit the plan");	
		
		developmentTabPane.addTab("Design", web, designPanel, "Sketch a plan");	//Development/Design
		developmentTabPane.addTab("Coding", web, codingPanel,"Document your code"); //Development/Coding
		developmentPanel.add(developmentTabPane);
		
		postmortemTabPane.addTab("Summary", web, summaryPanel, "Tables");
		postmortemPanel.add(postmortemTabPane);

		pspTabs.addTab("Planning", planningPanel);		//Planning parent tab
		pspTabs.addTab("Development", web, developmentPanel, "Create the project"); //Development parent tab
	    pspTabs.addTab("Postmortem", web, postmortemPanel);		// Summary parent Tab
	    showSummay();
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
