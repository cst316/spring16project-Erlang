package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.text.DateFormat;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import javax.swing.JTabbedPane;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLDocument;


import net.sf.memoranda.History;
import net.sf.memoranda.Note;
import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.CurrentNote;
import net.sf.memoranda.ui.htmleditor.HTMLEditor;
import net.sf.memoranda.util.Util;
import net.sf.memoranda.util.Context;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.HTMLFileExport;
import net.sf.memoranda.util.HTMLFileImport;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Configuration;
import net.st.memoranda.psp.PSPProcess;
import net.st.memoranda.psp.Planning;


/*$Id: EditorPanel.java,v 1.21 2006/06/28 22:58:31 alexeya Exp $*/
public class PlanningPanel extends JPanel {
	
	GridBagLayout gridLayout = new GridBagLayout();
	GridBagConstraints gconstraints = new GridBagConstraints();

	JPanel jpanel1 = new JPanel();

	JToolBar titleBar = new JToolBar();
	
	JToolBar authorBar = new JToolBar();
	
	JToolBar dateBar = new JToolBar();

	JLabel titleLabel = new JLabel();
	
	JLabel authorLabel = new JLabel();
	
	JLabel dateLabel = new JLabel();
	
	JLabel alertLabel = new JLabel();

	public JTextField titleField = new JTextField();
	
	JTextField authorField = new JTextField();
	
	JButton saveB = new JButton();
	
	//private PSPProcess pspProcess;

	//DailyItemsPanel parentPanel = null;

	public PlanningPanel(DailyItemsPanel parent, PSPProcess pspProcess) {
        this.setPreferredSize(new Dimension(1000, 1000));
			jbInit();
	}



	void jbInit(){

		if (!Configuration.get("DISABLE_L10N").equals("yes")) {
			net.sf.memoranda.ui.htmleditor.util.Local.setMessages(Local
					.getMessages());
		}
		
		gconstraints.anchor = GridBagConstraints.NORTHWEST;
		gconstraints.fill = GridBagConstraints.BOTH;

		jpanel1.setLayout(gridLayout);
		titleBar.setLayout(gridLayout);
		
		
		titleLabel.setFont(new java.awt.Font("Dialog", 1, 10));
		titleLabel.setText(Local.getString("Program") + "  ");
		gconstraints.gridx = 0;
		gconstraints.gridy = 0;
		titleBar.add(titleLabel, gconstraints);
		
		titleField.setText("Enter name of program here");
		gconstraints.gridx = 1;
		gconstraints.weightx = 1;
		titleBar.add(titleField, gconstraints);
		
		authorLabel.setFont(new java.awt.Font("Dialog", 1, 10));
		authorLabel.setText(Local.getString("Programmer") + "  ");
		gconstraints.gridx = 0;
		gconstraints.gridy = 0;
		authorBar.add(authorLabel, gconstraints);
		
		authorField.setText("Your name");
		gconstraints.gridx = 1;
		authorBar.add(authorField, gconstraints);
		
		dateLabel.setFont(new java.awt.Font("Dialog", 1, 10));
		dateLabel.setText("Date: " + CurrentDate.get().getFullDateString());
		gconstraints.gridx = 0;
		gconstraints.gridy = 0;
		dateBar.add(dateLabel, gconstraints);
		
		
		gconstraints.gridx = 0;
		gconstraints.gridy = 0;
		titleBar.setFloatable(false);
		jpanel1.add(titleBar, gconstraints);
		
		gconstraints.gridy = 1;
		authorBar.setFloatable(false);
		jpanel1.add(authorBar, gconstraints);
		
		gconstraints.gridy = 2;
		dateBar.setFloatable(false);
		jpanel1.add(dateBar, gconstraints);
		
		
		saveB.setText("Save");
		saveB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveButtonClicked();
			}
		});
		saveB.setPreferredSize(new Dimension(50, 50));
		gconstraints.gridy = 3;
		jpanel1.add(saveB, gconstraints);
		//jpanel2.setLayout(gridLayout);
		
		alertLabel.setText("");
		gconstraints.gridy = 4;
		jpanel1.add(alertLabel, gconstraints);
		
		this.setLayout(gridLayout);
		
		gconstraints.gridx = 0;
		gconstraints.gridy = 0;
		gconstraints.fill = GridBagConstraints.NONE;
		gconstraints.weightx = 0;
		gconstraints.weighty = 1;

		jpanel1.setPreferredSize(new Dimension(500,250));
		this.add(jpanel1, gconstraints);
	}
	
	private void saveButtonClicked(){

			String theDate = CurrentDate.get().getFullDateString();
			String theName = authorField.getText();
			String theProgram = titleField.getText();
			
			if(theName.length() == 0 || theProgram.length() == 0){
				alertLabel.setText("<html><font color='red'>One or both of the fields cannot be empty</font></html>");
			}else
				alertLabel.setText("Information saved");
	}
}