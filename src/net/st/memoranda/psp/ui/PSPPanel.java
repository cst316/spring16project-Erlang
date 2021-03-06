package net.st.memoranda.psp.ui;

//Testing 
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import net.sf.memoranda.ui.AppFrame;
import net.sf.memoranda.ui.DailyItemsPanel;
import net.sf.memoranda.ui.ImportDataTimer;
import net.sf.memoranda.ui.PlanningPanel;
import net.sf.memoranda.ui.WorkPanel;
import net.st.memoranda.psp.PSPProcess;
import net.st.memoranda.psp.Planning;
import net.st.memoranda.psp.SummaryObject;

/**
 * initializes the PSPPanel to display different PSP Panes
 * @author Carlos
 * @version 2.0
 */
public class PSPPanel extends JPanel {
	
	PSPProcess pspProcess = new PSPProcess();
	 
	//Tabs for PSP implementation
	JTabbedPane pspTabs = new JTabbedPane();
	JTabbedPane planningTabPane = new JTabbedPane();
	JTabbedPane developmentTabPane = new JTabbedPane();
	JTabbedPane postmortemTabPane = new JTabbedPane(); 

	JPanel planningPanel = new JPanel(); 
	JPanel developmentPanel = new JPanel();
	JPanel postmortemPanel = new JPanel();
	
	TimeEstimationPanel timeEstimationPanel = new TimeEstimationPanel(pspProcess);
	Planning planPanel = null;
	DesignPanel designPanel = new DesignPanel();

	CodeEstimationPanel codeEstimationPanel = null;
	CodingPanel codingPanel = new CodingPanel(pspProcess);
	SummaryPanel summaryPanel = new SummaryPanel(pspProcess);
	StopWatch watch;
	
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
		watch = new StopWatch(pspProcess);
		pspTabs.setPreferredSize(new Dimension(920,670));
		watch.setPreferredSize(new Dimension(300,670));
		DailyItemsPanel dPanel = new DailyItemsPanel(_parentPanel);	
		planPanel = new Planning();						
		ImportDataTimer testing = new ImportDataTimer();
//		estimationPanel = new EstimationPanel();
		codeEstimationPanel = new CodeEstimationPanel(pspProcess);

		this.initializeTabs();
		
		this.add(watch, BorderLayout.EAST);
		this.add(pspTabs, BorderLayout.CENTER);
		
	}

	
/**
 * This method initializes tabs for the PSP Panel. Each Tab will resemble Development,
 *  and Post mortem forms management for the convenience of Software Engineering Students
 * @return void
 * @author Carlos 
 * @version 1.0 

 * @author Team Erlang

 * @version 3.0

 */
	private void initializeTabs(){
		planningTabPane.addTab("Time Estimation", web, timeEstimationPanel, "Make Time Estimations");
		planningTabPane.addTab("Planning Notes", web, planPanel,"Edit the plan");	
		planningTabPane.addTab("Planning", web, planPanel,"Edit the plan");		//title, icon, panel, hintText

		planningPanel.add(planningTabPane);
	
		developmentTabPane.addTab("Design", web, designPanel, "Sketch a plan");	//Development/Design
		developmentTabPane.addTab("Code Estimation", web, codeEstimationPanel,"Edit the plan");//title, icon, panel, hintText
		developmentTabPane.addTab("Coding", web, codingPanel,"Document your code"); //Development/Coding
		developmentPanel.add(developmentTabPane);
		
		postmortemTabPane.addTab("Summary", web, summaryPanel, "Tables");
		postmortemPanel.add(postmortemTabPane);
		
		JScrollPane planScroller = new JScrollPane(planningPanel);
		JScrollPane devScroller = new JScrollPane(developmentPanel);
		JScrollPane postScroller = new JScrollPane(postmortemPanel);
		
		pspTabs.add("Planning",planScroller);
		pspTabs.add("Development",devScroller);
		pspTabs.add("Postmortem",postScroller);

	    this.add(pspTabs);
	}	
	
}
