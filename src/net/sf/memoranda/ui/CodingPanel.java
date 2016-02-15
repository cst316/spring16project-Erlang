package net.sf.memoranda.ui;
import java.util.*;
import java.awt.Dimension;

/**
 * The CodingPanel class' purpose is to display a log Coding, defects and injections.
 * @author Quinten Becker
 * @return void
 * @version 1.0
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

public class CodingPanel extends JPanel {
	
	
	public CodingPanel(){
		//this.setPreferredSize(new Dimension(1000, 1000));
		
		defectLog defect1 = new defectLog();
		this.add(defect1);
		
	}
	
	
	private class defectLog extends JPanel{
		/*Defect Types	
		10 Documentation	60 Checking
		20 Syntax	70 Data
		30 Build, Package	80 Function
		40 Assignment	90 System
		50 Interface	100 Environment*/
		
		public defectLog(){
			//setPreferredSize(new Dimension(1000, 1000));
			//setLayout(null);
				//window to enter description of defect
			JTextPane description_textPane = new JTextPane();
			description_textPane.setBounds(60, 147, 854, 94);
			add(description_textPane);
			
			
			final String[] defectOptions = {"10 Documentation", "20 Syntax",
					"30 Build Package", "40 Assignment",
					"50 Interface", "60 Checking",
					"80 Function", "90 System",
					"100 Environment"};
			
					JTextField project_TextField; //project text field
					JTextField date_TextField;
					JTextField number_TextField;
					JTextField inject_TextField;
					JTextField remove_TextField;
					JTextField fixTime_TextField;
					JTextField fexRef_TextField;
			
			
			
				
					
					//text fields for use in PSP coding log
					
					//project text field
					project_TextField = new JTextField();
					project_TextField.setBounds(60, 70, 70, 19);
					add(project_TextField);
					project_TextField.setColumns(10);
					
					//date text field
					date_TextField = new JTextField();
					date_TextField.setBounds(170, 70, 70, 19);
					add(date_TextField);
					date_TextField.setColumns(10);
					
					//number text field
					number_TextField = new JTextField();
					number_TextField.setBounds(280, 70, 70, 19);
					add(number_TextField);
					number_TextField.setColumns(10);
					
					// inject text field
					inject_TextField = new JTextField();
					inject_TextField.setBounds(500, 70, 70, 19);
					add(inject_TextField);
					inject_TextField.setColumns(10);
					
					// remove text field
					remove_TextField = new JTextField();
					remove_TextField.setBounds(610, 70, 70, 19);
					add(remove_TextField);
					remove_TextField.setColumns(10);
					
					//fix time text Field
					fixTime_TextField = new JTextField();
					fixTime_TextField.setBounds(720, 70, 70, 19);
					add(fixTime_TextField);
					fixTime_TextField.setColumns(10);
					
					//fix ref text Field
					fexRef_TextField = new JTextField();
					fexRef_TextField.setBounds(830, 70, 70, 19);
					add(fexRef_TextField);
					fexRef_TextField.setColumns(10);
					
					//combo box for type of defect
					JComboBox defect_ComboBox = new JComboBox(defectOptions);
					defect_ComboBox.setBounds(390, 70, 98, 19);
					add(defect_ComboBox);
					
					//Labels for use in PSP coding log
					JLabel lbl_Project = new JLabel("Project");
					lbl_Project.setBounds(60, 43, 70, 15);
					add(lbl_Project);
					
					JLabel lbl_Date = new JLabel("Date");
					lbl_Date.setBounds(170, 43, 70, 15);
					add(lbl_Date);
					
					JLabel lbl_Number = new JLabel("Number");
					lbl_Number.setBounds(280, 43, 70, 15);
					add(lbl_Number);
					
					JLabel lbl_Type = new JLabel("Type");
					lbl_Type.setBounds(390, 43, 70, 15);
					add(lbl_Type);
					
					JLabel lbl_Inject = new JLabel("Inject");
					lbl_Inject.setBounds(500, 43, 70, 15);
					add(lbl_Inject);
					
					JLabel lbl_Remove = new JLabel("Remove");
					lbl_Remove.setBounds(610, 43, 70, 15);
					add(lbl_Remove);
					
					JLabel lbl_FixTime = new JLabel("Fix Time");
					lbl_FixTime.setBounds(720, 43, 70, 15);
					add(lbl_FixTime);
					
					JLabel lbl_FixRef = new JLabel("Fix Ref");
					lbl_FixRef.setBounds(830, 43, 70, 15);
					add(lbl_FixRef);
					
					
					
					JLabel lblDescription = new JLabel("Description:");
					lblDescription.setBounds(60, 115, 98, 15);
					add(lblDescription);
		}
		
		
}
		
		
		
	
	
	
}

