package net.sf.memoranda.ui;
import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.sf.memoranda.Defect;
import net.sf.memoranda.util.Local;

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
	//buttons
    JButton submitButton;	
    //labels
    JLabel lbl_Defect;
    JLabel lbl_Class;
    JLabel lbl_Date;
    JLabel lbl_Number;
    JLabel lbl_Type;
    JLabel lbl_Inject;
    JLabel lbl_Remove;
    JLabel lbl_FixTime;
    JLabel lbl_FixRef;
    JLabel lblDescription;
	//text fields
    JTextField class_TextField;
	JTextField date_TextField;
	JTextField inject_TextField;
	JTextField remove_TextField;
	JTextField fixTime_TextField;
    JComboBox<String> status_ComboBox;
    JComboBox<String> defect_ComboBox;
    JTextPane description_textPane;
   
	JTable defect_Table = new JTable();
	JScrollPane defectTable_ScrollPane = null;
	JLabel tableTitle_Label = new JLabel();
	JLabel selected_Label = new JLabel();
	JLabel selectedDescrption_Label = new JLabel();
	JButton removeButton = new JButton();
	DefaultTableModel tableModel;
    
    private final String[] defectOptions = {"10 Documentation", "20 Syntax",
			"30 Build Package", "40 Assignment",
			"50 Interface", "60 Checking",
			"80 Function", "90 System",
			"100 Environment"};
    private final String[] statusOptions = {"Not Fixed", "In Progress", "Fixed"};
    private final String[] columnNames = {"Number","Class", "Date", "Type", "Inject", "Remove", 
    		                              "Fix Time", "Status"};
    Object[][] data = {};
    
    JPanel newDefect_Panel = new JPanel();
    JPanel defectTable_Panel = new JPanel();
    
    Vector<Defect> defects = new Vector<Defect>();
	
	public CodingPanel(){
		this.setPreferredSize(new Dimension(1000, 1000));
		setupNewDefectPanel();
		setupDefectTablePanel();
	}
	
	private void setupNewDefectPanel() {
		newDefect_Panel.setPreferredSize(new Dimension(820, 220));
		newDefect_Panel.setLayout(new GridBagLayout());
		
		//setup GridBag constraints
		GridBagConstraints gbCon = new GridBagConstraints();
		gbCon.anchor = GridBagConstraints.FIRST_LINE_START;
		gbCon.insets = new Insets(0,5,0,5);
		gbCon.weightx = 0.5;
		gbCon.weighty = 0.5;
		
		//Labels for use in PSP coding log
		
		lbl_Defect = new JLabel("Log New Defect:");
		lbl_Defect.setPreferredSize(new Dimension(200, 15));
		gbCon.gridwidth = 2;
		gbCon.gridheight = 1;
		gbCon.gridx = 0;
		gbCon.gridy = 0;
		newDefect_Panel.add(lbl_Defect, gbCon);
		
		lbl_Class = new JLabel("Class");
		lbl_Class.setPreferredSize(new Dimension(100, 15));
		gbCon.gridwidth = 1;
		gbCon.gridx = 0;
		gbCon.gridy = 1;
		newDefect_Panel.add(lbl_Class, gbCon);
		
		lbl_Date = new JLabel("Date");
		lbl_Date.setPreferredSize(new Dimension(100, 15));
		gbCon.gridx = 1;
		newDefect_Panel.add(lbl_Date, gbCon);
		
		lbl_Type = new JLabel("Type");
		lbl_Type.setPreferredSize(new Dimension(150, 15));
		gbCon.gridx = 2;
		newDefect_Panel.add(lbl_Type, gbCon);
		
		lbl_Inject = new JLabel("Inject");
		lbl_Inject.setPreferredSize(new Dimension(100, 15));
		gbCon.gridx = 3;
		newDefect_Panel.add(lbl_Inject, gbCon);
		
		lbl_Remove = new JLabel("Remove");
		lbl_Remove.setPreferredSize(new Dimension(100, 15));
		gbCon.gridx = 4;
		newDefect_Panel.add(lbl_Remove, gbCon);
		
		lbl_FixTime = new JLabel("Fix Time");
		lbl_FixTime.setPreferredSize(new Dimension(100, 15));
		gbCon.gridx = 5;
		newDefect_Panel.add(lbl_FixTime, gbCon);
		
		lbl_FixRef = new JLabel("Status");
		lbl_FixRef.setPreferredSize(new Dimension(100, 15));
		gbCon.gridx = 6;
		newDefect_Panel.add(lbl_FixRef, gbCon);
		
		//class text field
		class_TextField = new JTextField();
		class_TextField.setPreferredSize(new Dimension(100, 20));
		gbCon.gridy = 2;
		gbCon.gridx = 0;
		newDefect_Panel.add(class_TextField, gbCon);
		
		//date text field
		date_TextField = new JTextField();
		date_TextField.setPreferredSize(new Dimension(100, 20));
		gbCon.gridx = 1;
		newDefect_Panel.add(date_TextField, gbCon);
		
		//combo box for type of defect
		defect_ComboBox = new JComboBox<String>(defectOptions);
		defect_ComboBox.setPreferredSize(new Dimension(150, 20));
		gbCon.gridx = 2;
		newDefect_Panel.add(defect_ComboBox, gbCon);
		
		// inject text field
		inject_TextField = new JTextField();
		inject_TextField.setPreferredSize(new Dimension(100, 20));
		gbCon.gridx = 3;
		newDefect_Panel.add(inject_TextField, gbCon);
		
		// remove text field
		remove_TextField = new JTextField();
		remove_TextField.setPreferredSize(new Dimension(100, 20));
		gbCon.gridx = 4;
		newDefect_Panel.add(remove_TextField, gbCon);
		
		//fix time text Field
		fixTime_TextField = new JTextField();
		fixTime_TextField.setPreferredSize(new Dimension(100, 20));
		gbCon.gridx = 5;
		newDefect_Panel.add(fixTime_TextField, gbCon);
		
		//status options combo box
		status_ComboBox = new JComboBox<String>(statusOptions);
		status_ComboBox.setPreferredSize(new Dimension(100, 20));
		gbCon.gridx = 6;
		newDefect_Panel.add(status_ComboBox, gbCon);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setPreferredSize(new Dimension(200, 15));
		gbCon.gridy = 3;
		gbCon.gridx = 0;
		gbCon.gridwidth = 2;
		newDefect_Panel.add(lblDescription, gbCon);
		
		description_textPane = new JTextPane();
		description_textPane.setPreferredSize(new Dimension(810, 100));
		gbCon.gridy = 4;
		gbCon.gridx = 0;
		gbCon.gridwidth = 7;
		newDefect_Panel.add(description_textPane, gbCon);
		
		//submit button
		submitButton = new JButton("Add Defect");
		submitButton.setPreferredSize(new Dimension(120, 20));
		gbCon.gridy = 5;
		gbCon.gridx = 5;
		gbCon.gridwidth = 2;
		gbCon.anchor = GridBagConstraints.FIRST_LINE_END;
		newDefect_Panel.add(submitButton, gbCon);
		
		submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				submitButtonClicked();
			}
		});
		
		this.add(newDefect_Panel);
	}
	
	private void setupDefectTablePanel() {
		defectTable_Panel.setLayout(new GridBagLayout());
		defectTable_Panel.setPreferredSize(new Dimension(950, 370));
		
		populateTable();
			
		GridBagConstraints gbCon = new GridBagConstraints();
		gbCon.anchor = GridBagConstraints.PAGE_START;
		gbCon.weightx = 0.5;
		gbCon.weighty = 0.5;
		
		gbCon.gridwidth = 2;
		gbCon.gridheight = 1;
		gbCon.gridx = 0;
		gbCon.gridy = 0;
		
		tableTitle_Label.setText("Defect Log:");
		tableTitle_Label.setPreferredSize(new Dimension(950, 25));
		defectTable_Panel.add(tableTitle_Label,gbCon);
		
		gbCon.gridy = 1;
		defectTable_ScrollPane = new JScrollPane(defect_Table);
		defectTable_ScrollPane.setPreferredSize(new Dimension(950,200));
		defectTable_Panel.add(defectTable_ScrollPane,gbCon);
		
		gbCon.anchor = GridBagConstraints.FIRST_LINE_START;
		gbCon.gridwidth = 1;
		gbCon.gridy = 2;
		gbCon.gridx = 0;
		selected_Label.setText("Selected Defect Description:");
		selected_Label.setPreferredSize(new Dimension(250, 20));
		defectTable_Panel.add(selected_Label, gbCon);
		
		gbCon.anchor = GridBagConstraints.FIRST_LINE_END;
		gbCon.gridx = 1;
		removeButton.setPreferredSize(new Dimension(220, 20));
		removeButton.setText("Remove Selected Defect");
		defectTable_Panel.add(removeButton, gbCon);
		
		gbCon.anchor = GridBagConstraints.FIRST_LINE_START;
		gbCon.gridwidth = 2;
		gbCon.gridy = 3;
		gbCon.gridx = 0;
		selectedDescrption_Label.setPreferredSize(new Dimension(950, 100));
		selectedDescrption_Label.setText("(Nothing Selected)");
		selectedDescrption_Label.setHorizontalAlignment(JLabel.LEFT);
		selectedDescrption_Label.setVerticalAlignment(JLabel.TOP);
		selectedDescrption_Label.setBackground(Color.WHITE);
		selectedDescrption_Label.setOpaque(true);
		defectTable_Panel.add(selectedDescrption_Label, gbCon);
		
		removeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				removeButtonClicked();
			}
		});
		
		defect_Table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				tableRowSelected();
			}
		});
		
		this.add(defectTable_Panel);
	}
	
	private void populateTable() {
		/*
		 * once we have the PSPProcess class we will need to get saved estimations
		 * and populate the table with those values
		 */
		this.tableModel = new DefaultTableModel(this.columnNames, 0);
		this.defect_Table.setModel(this.tableModel);
	}
	
	private void submitButtonClicked() {
	    Defect temp;
	    temp = new Defect();
	
	    temp.setClassName(class_TextField.getText());
	    temp.setDate(date_TextField.getText());
	    //increment number for the user
	    if (this.defects.size() < 1) {
	    	temp.setNumber(1);
	    } 
	    else {
	    	temp.setNumber(this.defects.get(this.defects.size() - 1).getNumber() + 1);
	    }
	    temp.setInject(inject_TextField.getText());
	    temp.setRemove(remove_TextField.getText());
	    try {
	    	temp.setFixTime(Integer.parseInt(fixTime_TextField.getText()));
	    } catch (NumberFormatException e) {
	    	JOptionPane.showMessageDialog(null,Local.getString("Inappropriate value for Fix Time, try again"));
	    	return;
	    }
		temp.setStatus(status_ComboBox.getSelectedItem().toString());
		temp.setDefectType(defect_ComboBox.getSelectedItem().toString());
		temp.setDescription(description_textPane.getText());
		defects.addElement(temp);
		Object[] theRow = { temp.getNumber(), temp.getClassName(), temp.getDate(), temp.getDefectType(),
		    		        temp.getInject(), temp.getRemove(), temp.getFixTime(), temp.getStatus() };
	    tableModel.addRow(theRow);
		clearInputFields();
    }
	
	private void clearInputFields() {
		class_TextField.setText("");
		date_TextField.setText("");
		inject_TextField.setText("");
		remove_TextField.setText("");
		fixTime_TextField.setText("");
	    status_ComboBox.setSelectedIndex(0);
	    defect_ComboBox.setSelectedIndex(0);
	    description_textPane.setText("");
	}
	
	private void removeButtonClicked() {
		int theRowIndex = defect_Table.getSelectedRow();
		if ( theRowIndex >= 0 ) {
			defects.remove(theRowIndex);
		    tableModel.removeRow(theRowIndex);
		}
	}
	
	private void tableRowSelected() {
		if ( defect_Table.getSelectedRow() >= 0 ) {
		    selectedDescrption_Label.setText(defects.get(defect_Table.getSelectedRow()).getDescription());
		}
		else {
			selectedDescrption_Label.setText("(Nothing Selected)");
		}
	}
}

