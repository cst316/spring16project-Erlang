package net.st.memoranda.psp.ui;
import java.text.DecimalFormat;
import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.sf.memoranda.util.Local;
import net.st.memoranda.psp.Defect;
import net.st.memoranda.psp.PSPProcess;
import net.st.memoranda.psp.TimerLog.PspStage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * The CodingPanel class' purpose is to display a log Coding, defects and injections.
 * @author Quinten Becker & Tyler Driskill
 * @return void
 * @version 1.1
 */

public class CodingPanel extends JPanel {
    JButton submitButton;	
    JButton removeButton = new JButton();
    
    JLabel lblDefect;
    JLabel lblClass;
    JLabel lblDate;
    JLabel lblNumber;
    JLabel lblType;
    JLabel lblInject;
    JLabel lblRemove;
    JLabel lblFixTime;
    JLabel lblFixRef;
    JLabel lblDescription;
    
    JTextField classTextField;
	JTextField dateTextField;
	JTextField fixTimeTextField;
	JComboBox<String> injectComboBox;
	JComboBox<String> removeComboBox;
	JComboBox<String> timeUnitsComboBox;
    JComboBox<String> statusComboBox;
    JComboBox<String> defectComboBox;
    JTextPane descriptionTextPane;
   
	JTable defectTable = new JTable();
	JScrollPane defectTableScrollPane = null;
	JLabel tableTitleLabel = new JLabel();
	JLabel selectedLabel = new JLabel();
	JLabel selectedDescrptionLabel = new JLabel();
	DefaultTableModel tableModel;
    
    private final String[] defectOptions = {"10 Documentation", "20 Syntax",
			"30 Build Package", "40 Assignment",
			"50 Interface", "60 Checking",
			"80 Function", "90 System",
			"100 Environment"};
    private final String[] statusOptions = {"Not Fixed", "In Progress", "Fixed"};
    private final String[] timeOptions = {"mins", "hours", "days"};
    private final String[] stageOptions = {"Planning", "Design", "Code", "Code Review",
    		                               "Compile", "Test", "Postmortem"};
    private final String[] columnNames = {"Number","Class", "Date", "Type", "Inject", "Remove", 
    		                              "Fix Time (mins)", "Status"};
    private final DecimalFormat decimalFormat = new DecimalFormat("#,##0.0");
    
    private JPanel newDefectPanel = new JPanel();
    private JPanel defectTablePanel = new JPanel();
    
    private PSPProcess pspProcess;
	
	public CodingPanel(PSPProcess aPSPProcess){
		pspProcess = aPSPProcess;
		this.setPreferredSize(new Dimension(1000, 1000));
		setupNewDefectPanel();
		setupDefectTablePanel();
	}
	
	private void setupNewDefectPanel() {
		newDefectPanel.setPreferredSize(new Dimension(920, 240));
		newDefectPanel.setLayout(new GridBagLayout());
		
		//setup GridBag constraints
		GridBagConstraints gbCon = new GridBagConstraints();
		gbCon.anchor = GridBagConstraints.FIRST_LINE_START;
		gbCon.insets = new Insets(0,5,0,5);
		gbCon.weightx = 0.5;
		gbCon.weighty = 0.5;
		
		//Labels for use in PSP coding log
		
		lblDefect = new JLabel("Log New Defect:");
		lblDefect.setPreferredSize(new Dimension(200, 15));
		gbCon.gridwidth = 2;
		gbCon.gridheight = 1;
		gbCon.gridx = 0;
		gbCon.gridy = 0;
		newDefectPanel.add(lblDefect, gbCon);
		
		lblClass = new JLabel("Class");
		lblClass.setPreferredSize(new Dimension(100, 15));
		gbCon.gridwidth = 1;
		gbCon.gridx = 0;
		gbCon.gridy = 1;
		newDefectPanel.add(lblClass, gbCon);
		
		lblDate = new JLabel("Date");
		lblDate.setPreferredSize(new Dimension(100, 15));
		gbCon.gridx = 1;
		newDefectPanel.add(lblDate, gbCon);
		
		lblType = new JLabel("Type");
		lblType.setPreferredSize(new Dimension(150, 15));
		gbCon.gridx = 2;
		newDefectPanel.add(lblType, gbCon);
		
		lblInject = new JLabel("Inject");
		lblInject.setPreferredSize(new Dimension(120, 15));
		gbCon.gridx = 3;
		newDefectPanel.add(lblInject, gbCon);
		
		lblRemove = new JLabel("Remove");
		lblRemove.setPreferredSize(new Dimension(120, 15));
		gbCon.gridx = 4;
		newDefectPanel.add(lblRemove, gbCon);
		
		lblFixTime = new JLabel("Fix Time");
		lblFixTime.setPreferredSize(new Dimension(80, 15));
		gbCon.gridx = 5;
		newDefectPanel.add(lblFixTime, gbCon);
		
		lblFixRef = new JLabel("Status");
		lblFixRef.setPreferredSize(new Dimension(100, 15));
		gbCon.gridx = 7;
		newDefectPanel.add(lblFixRef, gbCon);
		
		//class text field
		classTextField = new JTextField();
		classTextField.setPreferredSize(new Dimension(100, 20));
		gbCon.gridy = 2;
		gbCon.gridx = 0;
		newDefectPanel.add(classTextField, gbCon);
		
		//date text field
		dateTextField = new JTextField();
		dateTextField.setPreferredSize(new Dimension(100, 20));
		gbCon.gridx = 1;
		newDefectPanel.add(dateTextField, gbCon);
		
		//combo box for type of defect
		defectComboBox = new JComboBox<String>(defectOptions);
		defectComboBox.setPreferredSize(new Dimension(150, 20));
		gbCon.gridx = 2;
		newDefectPanel.add(defectComboBox, gbCon);
		
		// inject text field
		injectComboBox = new JComboBox<String>(stageOptions);
		injectComboBox.setPreferredSize(new Dimension(120, 20));
		gbCon.gridx = 3;
		newDefectPanel.add(injectComboBox, gbCon);
		
		// remove text field
		removeComboBox = new JComboBox<String>(stageOptions);
		removeComboBox.setPreferredSize(new Dimension(120, 20));
		gbCon.gridx = 4;
		newDefectPanel.add(removeComboBox, gbCon);
		
		//fix time text Field
		fixTimeTextField = new JTextField();
		fixTimeTextField.setPreferredSize(new Dimension(80, 20));
		gbCon.gridx = 5;
		newDefectPanel.add(fixTimeTextField, gbCon);
		
		// remove text field
		timeUnitsComboBox = new JComboBox<String>(timeOptions);
		timeUnitsComboBox.setPreferredSize(new Dimension(70, 20));
		gbCon.gridx = 6;
		newDefectPanel.add(timeUnitsComboBox, gbCon);
		
		//status options combo box
		statusComboBox = new JComboBox<String>(statusOptions);
		statusComboBox.setPreferredSize(new Dimension(100, 20));
		gbCon.gridx = 7;
		newDefectPanel.add(statusComboBox, gbCon);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setPreferredSize(new Dimension(200, 15));
		gbCon.gridy = 3;
		gbCon.gridx = 0;
		gbCon.gridwidth = 2;
		newDefectPanel.add(lblDescription, gbCon);
		
		descriptionTextPane = new JTextPane();
		descriptionTextPane.setPreferredSize(new Dimension(910, 100));
		gbCon.gridy = 4;
		gbCon.gridx = 0;
		gbCon.gridwidth = 8;
		newDefectPanel.add(descriptionTextPane, gbCon);
		
		//submit button
		submitButton = new JButton("Add Defect");
		submitButton.setPreferredSize(new Dimension(120, 20));
		gbCon.gridy = 5;
		gbCon.gridx = 6;
		gbCon.gridwidth = 2;
		gbCon.anchor = GridBagConstraints.FIRST_LINE_END;
		newDefectPanel.add(submitButton, gbCon);
		
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent aEvent) {
				submitButtonClicked();
			}
		});
		
		this.add(newDefectPanel);
	}
	
	private void setupDefectTablePanel() {
		defectTablePanel.setLayout(new GridBagLayout());
		defectTablePanel.setPreferredSize(new Dimension(950, 370));
		
		populateTable();
			
		GridBagConstraints gbCon = new GridBagConstraints();
		gbCon.anchor = GridBagConstraints.PAGE_START;
		gbCon.weightx = 0.5;
		gbCon.weighty = 0.5;
		
		gbCon.gridwidth = 2;
		gbCon.gridheight = 1;
		gbCon.gridx = 0;
		gbCon.gridy = 0;
		
		tableTitleLabel.setText("Defect Log:");
		tableTitleLabel.setPreferredSize(new Dimension(950, 25));
		defectTablePanel.add(tableTitleLabel,gbCon);
		
		gbCon.gridy = 1;
		defectTableScrollPane = new JScrollPane(defectTable);
		defectTableScrollPane.setPreferredSize(new Dimension(950,200));
		defectTablePanel.add(defectTableScrollPane,gbCon);
		
		gbCon.anchor = GridBagConstraints.FIRST_LINE_START;
		gbCon.gridwidth = 1;
		gbCon.gridy = 2;
		gbCon.gridx = 0;
		selectedLabel.setText("Selected Defect Description:");
		selectedLabel.setPreferredSize(new Dimension(250, 20));
		defectTablePanel.add(selectedLabel, gbCon);
		
		gbCon.anchor = GridBagConstraints.FIRST_LINE_END;
		gbCon.gridx = 1;
		removeButton.setPreferredSize(new Dimension(220, 20));
		removeButton.setText("Remove Selected Defect");
		defectTablePanel.add(removeButton, gbCon);
		
		gbCon.anchor = GridBagConstraints.FIRST_LINE_START;
		gbCon.gridwidth = 2;
		gbCon.gridy = 3;
		gbCon.gridx = 0;
		selectedDescrptionLabel.setPreferredSize(new Dimension(950, 100));
		selectedDescrptionLabel.setText("(Nothing Selected)");
		selectedDescrptionLabel.setHorizontalAlignment(JLabel.LEFT);
		selectedDescrptionLabel.setVerticalAlignment(JLabel.TOP);
		selectedDescrptionLabel.setBackground(Color.WHITE);
		selectedDescrptionLabel.setOpaque(true);
		defectTablePanel.add(selectedDescrptionLabel, gbCon);
		
		removeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				removeButtonClicked();
			}
		});
		
		defectTable.getSelectionModel().addListSelectionListener(
				                         new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				tableRowSelected();
			}
		});
		
		this.add(defectTablePanel);
	}
	
	private void populateTable() {
		/*
		 * once we have the PSPProcess class we will need to get saved estimations
		 * and populate the table with those values
		 */
		this.tableModel = new DefaultTableModel(this.columnNames, 0);
		this.defectTable.setModel(this.tableModel);
	}
	
	private void submitButtonClicked() {
	    Defect temp;
	    temp = new Defect();
	
	    temp.setClassName(classTextField.getText());
	    temp.setDate(dateTextField.getText());
	    //increment number for the user
	    if (this.pspProcess.getDefectsSize()< 1) {
	    	temp.setNumber(1);
        } else {
	    	temp.setNumber(this.pspProcess.getAllDefects().get(this.pspProcess.getDefectsSize() - 1).getNumber() + 1);
	    }
	    temp.setInject(PspStage.values()[injectComboBox.getSelectedIndex()]);
	    temp.setRemove(PspStage.values()[removeComboBox.getSelectedIndex()]);
	    try {
	    	double theTimeValue = Double.parseDouble(fixTimeTextField.getText());
	    	String theUnits = timeUnitsComboBox.getSelectedItem().toString();
	    	if (theUnits.equals("hours")) {
	    		theTimeValue = theTimeValue * 60.0;
	    	} else if (theUnits.equals("days")) {
	    		theTimeValue = theTimeValue * 60.0 * 24.0;
	    	}
	    	temp.setFixTime(theTimeValue);
	    } catch (NumberFormatException e) {
	    	JOptionPane.showMessageDialog(null,Local.getString("Inappropriate value "
	    			                      + "for Fix Time, try again"));
	    	return;
	    }
		temp.setStatus(statusComboBox.getSelectedItem().toString());
		temp.setDefectType(defectComboBox.getSelectedItem().toString());
		temp.setDescription(descriptionTextPane.getText());
		this.pspProcess.addDefect(temp);
		Object[] theRow = { temp.getNumber(), temp.getClassName(), temp.getDate(), 
				temp.getDefectType(),temp.getInject(), temp.getRemove(), 
				decimalFormat.format(temp.getFixTime()), temp.getStatus() };
	    tableModel.addRow(theRow);
		clearInputFields();
    }
	
	private void clearInputFields() {
		classTextField.setText("");
		dateTextField.setText("");
		injectComboBox.setSelectedIndex(0);
		removeComboBox.setSelectedIndex(0);
		fixTimeTextField.setText("");
	    statusComboBox.setSelectedIndex(0);
	    defectComboBox.setSelectedIndex(0);
	    descriptionTextPane.setText("");
	}
	
	private void removeButtonClicked() {
		int theRowIndex = defectTable.getSelectedRow();
		if ( theRowIndex >= 0 ) {
			this.pspProcess.removeDefect(theRowIndex);
		    tableModel.removeRow(theRowIndex);
		}
	}
	
	private void tableRowSelected() {
		if ( defectTable.getSelectedRow() >= 0 ) {
		    selectedDescrptionLabel.setText(
		    		this.pspProcess.getAllDefects().get(defectTable.getSelectedRow()).getDescription());
		} else {
			selectedDescrptionLabel.setText("(Nothing Selected)");
		}
	}
}

