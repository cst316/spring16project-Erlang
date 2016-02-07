package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;


import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
/**
 * initializes the PSPPanel to display different PSP Panes
 * @author Carlos
 *
 */
public class PSPPanel extends JPanel {
	
	//Tabs for PSP implementation
	JTabbedPane pspTabs = new JTabbedPane();
	
	PlanningPanel planPanel = null;
	DesignPanel designPanel = new DesignPanel();
	
		//PlanPanel planPanel = new PlanPanel();	//create unique panel later		
	ImageIcon web = new ImageIcon(
			net.sf.memoranda.ui.AppFrame.class
			.getResource("resources/icons/web.png"));
	/**
	 * 
	 * @param parent used to initialize planning panel tab
	 */
	public PSPPanel(DailyItemsPanel parent){
		planPanel = new PlanningPanel(parent);
		this.setLayout(new FlowLayout());
		this.initializeTabs();
	}
/**
 * This method initializes tabs for the PSP Panel. Each Tab will resemble Planning,
 * Design, and Defect form management for the convenience of Software Engineering Students
 * @return void
 * @author Carlos
 * @version 1.0
 */
	private void initializeTabs(){
	pspTabs.addTab("Planning", web, planPanel,"Edit the plan");		//title, icon, panel, hintText
	
	pspTabs.addTab("Design", web, designPanel,"Sketch a plan");
	
	//this.add(pspTabs, BorderLayout.CENTER);
	this.add(pspTabs);
	
	pspTabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	
	}
	
	

}
