package net.sf.memoranda.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.sf.memoranda.util.Local;

public class TimeEstimationPanel extends JPanel {
	JPanel inputPanel;
	JLabel titleLabel;
	JLabel areaLabel;
	JLabel hoursLabel;
	JLabel minsLabel;
	JLabel planningLabel;
	JLabel designLabel;
	JLabel codeLabel;
	JLabel codereviewLabel;
	JLabel compileLabel;
	JLabel testLabel;
	JLabel portmortemLabel;
	JLabel totalTimeTitle;
	JLabel totalTimeText;
	double totalTimeEstimate;
	double[] timeEstimates = new double[7];
	JTextField[][] textFields = new JTextField[7][2];
	
	public TimeEstimationPanel() {
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setLayout(null);
		
		inputPanel = new JPanel();
		inputPanel.setLayout(null);
		inputPanel.setBounds(0, 75, 1000, 280);
		inputPanel.setBackground(Color.LIGHT_GRAY);
		add(inputPanel);
		
		totalTimeEstimate = 0.0;
		
		layoutLabels();
		layoutTextFields();
		addListenersToTextFields();
		updateTotalTimeText();
		
	}
	
	private void layoutLabels() {
		titleLabel = new JLabel("Time Estimation Entries:");
		titleLabel.setBounds(345, 15, 300, 25);
		add(titleLabel);
		areaLabel = new JLabel("Area:");
		areaLabel.setBounds(345, 50, 100, 25);
		add(areaLabel);
		hoursLabel = new JLabel("Hour(s):");
		hoursLabel.setBounds(445, 50, 100, 25);
		add(hoursLabel);
		minsLabel = new JLabel("Min(s):");
		minsLabel.setBounds(555, 50, 100, 25);
		add(minsLabel);
		planningLabel = new JLabel("Planning");
		planningLabel.setBounds(345, 5, 100, 25);
		inputPanel.add(planningLabel);
		designLabel = new JLabel("Design");
		designLabel.setBounds(345, 45, 100, 25);
		inputPanel.add(designLabel);
		codeLabel = new JLabel("Code");
		codeLabel.setBounds(345, 85, 100, 25);
		inputPanel.add(codeLabel);
		codereviewLabel = new JLabel("Code Review");
		codereviewLabel.setBounds(345, 125, 100, 25);
		inputPanel.add(codereviewLabel);
		compileLabel = new JLabel("Compile");
		compileLabel.setBounds(345, 165, 100, 25);
		inputPanel.add(compileLabel);
		testLabel = new JLabel("Test");
		testLabel.setBounds(345, 205, 100, 25);
		inputPanel.add(testLabel);
		portmortemLabel = new JLabel("Postmortem");
		portmortemLabel.setBounds(345, 245, 100, 25);
		inputPanel.add(portmortemLabel);
		totalTimeTitle = new JLabel("Total Estimated Time:");
		totalTimeTitle.setBounds(460, 360, 150, 25);
		add(totalTimeTitle);
		totalTimeText= new JLabel();
		totalTimeText.setBounds(595, 360, 130, 25);
		add(totalTimeText);
	}
	
	private void layoutTextFields() {
		int yIncrement = 40;
		for(int i =0; i <textFields.length; i++) {
			textFields[i][0] = new JTextField();
			textFields[i][1] = new JTextField();
			textFields[i][0].setBounds(445, (5+(yIncrement*i)), 100, 25);
			textFields[i][1].setBounds(555, (5+(yIncrement*i)), 100, 25);
			inputPanel.add(textFields[i][0]);
			inputPanel.add(textFields[i][1]);
		}
	}
	
	private void addListenersToTextFields() {
		DocumentListener theDocListnr = new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				      sumUpTotalTimeEstimate();
				  }
				  public void removeUpdate(DocumentEvent e) {
				      sumUpTotalTimeEstimate();
				  }
				  public void insertUpdate(DocumentEvent e) {
					  sumUpTotalTimeEstimate();
				  } 

				  public void sumUpTotalTimeEstimate() {
					  try {
						  for(int i =0; i <textFields.length; i++) {
							  timeEstimates[i] = 0.0;
							  if(!textFields[i][0].getText().isEmpty()) {
								  timeEstimates[i] = timeEstimates[i] + 
								      (Double.parseDouble(textFields[i][0].getText())*60.0*60.0);
							  }
							  if(!textFields[i][1].getText().isEmpty()) {
								  timeEstimates[i] = timeEstimates[i] + 
								      (Double.parseDouble(textFields[i][1].getText())*60.0);
							  }
						  }
					  } catch (NumberFormatException e) {
						  JOptionPane.showMessageDialog(null,Local.getString("Only numbers are allowed as entry"));
					  }
					  totalTimeEstimate = 0.0;
					  for(int i =0; i <timeEstimates.length; i++) {
						  totalTimeEstimate = totalTimeEstimate + timeEstimates[i];
					  }
					  updateTotalTimeText();
				  }
			  };
		for(int i =0; i <textFields.length; i++) {
			textFields[i][0].getDocument().addDocumentListener(theDocListnr);
			textFields[i][1].getDocument().addDocumentListener(theDocListnr);
		}
	}
	
	private void updateTotalTimeText() {
		totalTimeText.setText(timeToFormattedString(totalTimeEstimate));
	}
	
	//convert time to properly formatted string
	private String timeToFormattedString(double aTimeInSec) {
		int theMins = (int)aTimeInSec / 60;
		int theHours = theMins / 60;
				
		String theMinsString = Integer.toString( theMins % 60 );
		String theHoursString = Integer.toString( theHours );
		String theSecondString = String.format ( "%.1f",( aTimeInSec % 60.0 ) );
				
		if( theMinsString.length() < 2 ) {
			theMinsString = "0" + theMinsString;
		}
				
		if( theHoursString.length() < 2 ) {
		    theHoursString = "0" + theHoursString;
		}
		        
		if( theSecondString.length() < 4 ) {
		    theSecondString = "0" + theSecondString;
	    }
				
		return (theHoursString + ":" + theMinsString + ":" + theSecondString);
	}
}
