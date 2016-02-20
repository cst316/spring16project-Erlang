package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.Report;
import net.sf.memoranda.ReportExporter;
import net.sf.memoranda.ReportGenerator;
import net.sf.memoranda.ReportSettings;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.Local;

public class ReportDialog extends JDialog {
	JPanel mPanel = new JPanel(new BorderLayout());
    JPanel areaPanel = new JPanel(new BorderLayout());
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton cancelB = new JButton();
    JButton okB = new JButton();
    Border border1;
    Border border2;
    JPanel dialogTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel header = new JLabel();
    public boolean CANCELLED = true;
    JPanel jPanel8 = new JPanel(new GridBagLayout());
    Border border3;
    Border border4;
    JPanel jPanel2 = new JPanel(new GridLayout(3, 2));
    JTextField todoField = new JTextField();
    Border border8;
    JPanel jPanel4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JPanel jPanel6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel jPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JPanel jPanel3 = new JPanel();
    JLabel projectSetLabel = new JLabel("Include from Project:");
    JLabel otherSetLabel = new JLabel("Other:");
    JCheckBox prjIDCBox = new JCheckBox("Project ID");
    JCheckBox prjDatesCBox = new JCheckBox("Project Dates");
    JCheckBox prjStatusCBox = new JCheckBox("Project Status");
    JCheckBox prjDescriptionCBox = new JCheckBox("Project Description");
    JCheckBox tasksCBox = new JCheckBox("Include Tasks:");
    JCheckBox taskIDCBox = new JCheckBox("Task Ids");
    JCheckBox taskProgressCBox = new JCheckBox("Task Progress");
    JCheckBox taskStatusCBox = new JCheckBox("Task Status");
    JCheckBox taskDatesCBox = new JCheckBox("Task Dates");
    JCheckBox taskSubTaksCBox = new JCheckBox("Sub-Tasks");
    JCheckBox taskPriorityCBox = new JCheckBox("Task Priorty");
    JCheckBox resourcesCBox = new JCheckBox("Include Resources");
    JCheckBox notesCBox = new JCheckBox("Include Notes");
    
    public ReportDialog(Frame frame, String title) {
        super(frame, title, true);
        try {
            jbInit();            
            pack();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }
    
    void jbInit() throws Exception {
	this.setResizable(false);
	this.setSize(new Dimension(430,300));
        border1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        border2 = BorderFactory.createEtchedBorder(Color.white, new Color(142, 142, 142));
        TitledBorder theTitledBorder = new TitledBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0), 
        Local.getString("Enter desired name of .html file:"), TitledBorder.LEFT, TitledBorder.BELOW_TOP);
        Font theBorderTitleFont = new Font(theTitledBorder.getTitleFont().getName(), Font.BOLD+Font.ITALIC, theTitledBorder.getTitleFont().getSize() );
        theTitledBorder.setTitleFont(theBorderTitleFont);
        border3 = theTitledBorder;
        border4 = BorderFactory.createEmptyBorder(0, 5, 0, 5);
        border8 = BorderFactory.createEtchedBorder(Color.white, 
            new Color(178, 178, 178));
        cancelB.setMaximumSize(new Dimension(100, 26));
        cancelB.setMinimumSize(new Dimension(100, 26));
        cancelB.setPreferredSize(new Dimension(100, 26));
        cancelB.setText(Local.getString("Cancel"));
        cancelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelB_actionPerformed(e);
            }
        });
        okB.setMaximumSize(new Dimension(100, 26));
        okB.setMinimumSize(new Dimension(100, 26));
        okB.setPreferredSize(new Dimension(100, 26));
        okB.setText(Local.getString("Ok"));
        okB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okB_actionPerformed(e);
            }
        });
        
        this.getRootPane().setDefaultButton(okB);
        mPanel.setBorder(border1);
        areaPanel.setBorder(border2);
        dialogTitlePanel.setBackground(Color.WHITE);
        dialogTitlePanel.setBorder(border4);
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("Export HTML Project Report"));
        header.setIcon(new ImageIcon(net.sf.memoranda.ui.TaskDialog.class.getResource(
            "resources/icons/reports.png")));
        
        GridBagLayout gbLayout = (GridBagLayout) jPanel8.getLayout();
        jPanel8.setBorder(border3);
				
        todoField.setBorder(border8);
        todoField.setPreferredSize(new Dimension(375, 24));
        GridBagConstraints gbCon = new GridBagConstraints();
        gbCon.gridwidth = GridBagConstraints.REMAINDER;
        gbCon.weighty = 1;
        gbLayout.setConstraints(todoField,gbCon);
        
        gbCon = new GridBagConstraints();
        gbCon.gridwidth = GridBagConstraints.REMAINDER;
        gbCon.weighty = 1;
        gbCon.anchor = GridBagConstraints.WEST;

        gbCon = new GridBagConstraints();
        gbCon.gridwidth = GridBagConstraints.REMAINDER;
        gbCon.weighty = 3;
              

        getContentPane().add(mPanel);
        mPanel.add(areaPanel, BorderLayout.CENTER);
        mPanel.add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.add(okB, null);
        buttonsPanel.add(cancelB, null);
        this.getContentPane().add(dialogTitlePanel, BorderLayout.NORTH);
        dialogTitlePanel.add(header, null);
        areaPanel.add(jPanel8, BorderLayout.NORTH);
        jPanel8.add(todoField, null);
        jPanel3.setLayout(new GridBagLayout());
        
        tasksCBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	includeTasks_actionPerformed(e);
            }
        });
        
        areaPanel.add(jPanel3, BorderLayout.CENTER);
        
        //set font of section headers
        Font theSectionTitleFont = new Font(projectSetLabel.getFont().getName(), Font.BOLD+Font.ITALIC, projectSetLabel.getFont().getSize());
        projectSetLabel.setFont(theSectionTitleFont);
        tasksCBox.setFont(theSectionTitleFont);
        tasksCBox.setHorizontalTextPosition(SwingConstants.LEFT);
        otherSetLabel.setFont(theSectionTitleFont);
        
        //set check-boxes and labels properly into the GridBagLayout
        GridBagConstraints theConsts = new GridBagConstraints();
        theConsts.fill = GridBagConstraints.HORIZONTAL;
        theConsts.gridx = 0;
        theConsts.gridy = 0;
        theConsts.ipady=0;
        theConsts.gridwidth = 2;
        jPanel3.add(projectSetLabel, theConsts);
        theConsts.gridx = 0;
        theConsts.gridy = 1;
        theConsts.gridwidth = 1;
        theConsts.insets = new Insets(0,0,0,0);
        jPanel3.add(prjIDCBox, theConsts);
        theConsts.gridx = 1;
        theConsts.gridy = 1;
        jPanel3.add(prjDatesCBox, theConsts);
        theConsts.gridx = 2;
        theConsts.gridy = 1;
        jPanel3.add(prjStatusCBox, theConsts); 
        theConsts.gridx = 3;
        theConsts.gridy = 1;
        jPanel3.add(prjDescriptionCBox, theConsts);
        theConsts.gridx = 0;
        theConsts.gridy = 2;
        theConsts.gridwidth = 2;
        theConsts.insets = new Insets(8,0,0,0);
        jPanel3.add(tasksCBox, theConsts);
        theConsts.gridx = 0;
        theConsts.gridy = 3;
        theConsts.gridwidth = 1;
        theConsts.insets = new Insets(0,0,0,0);
        jPanel3.add(taskIDCBox, theConsts);
        theConsts.gridx = 1;
        theConsts.gridy = 3;
        jPanel3.add(taskProgressCBox, theConsts);
        theConsts.gridx = 2;
        theConsts.gridy = 3;
        jPanel3.add(taskStatusCBox, theConsts);
        theConsts.gridx = 3;
        theConsts.gridy = 3;
        jPanel3.add(taskDatesCBox, theConsts);
        theConsts.gridx = 0;
        theConsts.gridy = 4;
        jPanel3.add(taskSubTaksCBox, theConsts);
        theConsts.gridx = 1;
        theConsts.gridy = 4;
        jPanel3.add(taskPriorityCBox, theConsts);
        theConsts.gridx = 0;
        theConsts.gridy = 5;
        theConsts.insets = new Insets(8,0,0,0);
        jPanel3.add(otherSetLabel, theConsts);
        theConsts.gridx = 0;
        theConsts.gridy = 6;
        theConsts.gridwidth = 2;
        theConsts.insets = new Insets(0,0,0,0);
        jPanel3.add( notesCBox, theConsts);
        theConsts.gridx = 2;
        theConsts.gridy = 6;
        theConsts.anchor = GridBagConstraints.PAGE_END;
        jPanel3.add(resourcesCBox, theConsts);
        
        //set default state of check-boxes
        prjIDCBox.setSelected(true);
        prjDatesCBox.setSelected(true);
        prjStatusCBox.setSelected(true);
        prjDescriptionCBox.setSelected(true);
        tasksCBox.setSelected(true);
        taskIDCBox.setSelected(true);
        taskProgressCBox.setSelected(true); 
        taskStatusCBox.setSelected(true);
        taskDatesCBox.setSelected(true);
        taskSubTaksCBox.setSelected(true);
        taskPriorityCBox.setSelected(true);
        resourcesCBox.setSelected(false);
        notesCBox.setSelected(false);
        
    }
	
    void okB_actionPerformed(ActionEvent e) {
	    CANCELLED = false;
	    createReport();
        this.dispose();
    }

    void cancelB_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    void includeTasks_actionPerformed(ActionEvent e) {
        if( tasksCBox.isSelected() ) {
            taskIDCBox.setEnabled(true);
            taskProgressCBox.setEnabled(true);
            taskDatesCBox.setEnabled(true);
            taskSubTaksCBox.setEnabled(true);
            taskPriorityCBox.setEnabled(true);
            taskStatusCBox.setEnabled(true);
        }
        else {
        	taskIDCBox.setEnabled(false);
            taskProgressCBox.setEnabled(false);
            taskDatesCBox.setEnabled(false);
            taskSubTaksCBox.setEnabled(false);
            taskPriorityCBox.setEnabled(false);
            taskStatusCBox.setEnabled(false);
        }
    }
    
    void createReport() {
    	ReportSettings theReportSettings = new ReportSettings(); 
    	theReportSettings.setWithProjectID(prjIDCBox.isSelected());
    	theReportSettings.setWithProjectDates(prjDatesCBox.isSelected());
    	theReportSettings.setWithProjectStatus(prjStatusCBox.isSelected());
    	theReportSettings.setWithProjectDescription(prjDescriptionCBox.isSelected());
    	theReportSettings.setWithTasks(tasksCBox.isSelected());
        theReportSettings.setWithTaskText(true);
    	theReportSettings.setWithTaskIDs(taskIDCBox.isSelected());
        theReportSettings.setWithTaskDates(taskDatesCBox.isSelected());
        theReportSettings.setWithTaskProgress(taskProgressCBox.isSelected());
        theReportSettings.setWithTaskPriority(taskPriorityCBox.isSelected());
        theReportSettings.setWithTaskStatus(taskStatusCBox.isSelected());
        theReportSettings.setWithTaskSubTasks(taskSubTaksCBox.isSelected());
        theReportSettings.setWithNotes(notesCBox.isSelected());
        theReportSettings.setWithResources(resourcesCBox.isSelected());
    	Report theReport = ReportGenerator.generateReport(theReportSettings);
    	String theFileName = todoField.getText().split("-|\\.")[0];
    	ReportExporter.exportReport(theReport, theFileName);
    }
}
