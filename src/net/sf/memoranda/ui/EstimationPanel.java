package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import net.sf.memoranda.util.Local;

public class EstimationPanel extends JPanel {
	
	JPanel newEstPanel = new JPanel();
	JLabel descTitleLabel = new JLabel();
	JLabel sizeTitleLabel = new JLabel();
	JLabel linesLabel = new JLabel();
	JToolBar titleBar = new JToolBar();
	JTextArea descriptionTextA = new JTextArea();
	JTextField estSizeTextF = new JTextField();
	JButton addButton = new JButton();
	
	JPanel estTablePanel = new JPanel();
	String[] columnNames = {"Module Description","Estimated Size"};
	Object[][] data = {};
	JTable estimationTable = new JTable(data,columnNames);
	JScrollPane estTableSP = null;
	JLabel tableTitleLabel = new JLabel();
	JLabel estTotalLabel = new JLabel();
	final String estTotalPrefix = "Total Estimated Size: ";
	JButton removeButton = new JButton();
	
	public EstimationPanel() {
		this.setPreferredSize(new Dimension(1000, 1000));
		
		setupNewEstimationPanel();
		setupEstimationTablePanel();
	}
	
	private void setupNewEstimationPanel() {
		newEstPanel.setPreferredSize(new Dimension(715,100));
		newEstPanel.setLayout(new GridBagLayout());
		
		descTitleLabel.setText("New Module Description:");
		descTitleLabel.setPreferredSize(new Dimension(500, 25));
		GridBagConstraints gbCon = new GridBagConstraints();
		gbCon.anchor = GridBagConstraints.PAGE_START;
		gbCon.insets = new Insets(0,5,0,5);
		gbCon.weightx = 0.5;
		gbCon.weighty = 0.5;
		gbCon.gridwidth = 2;
		gbCon.gridheight = 1;
		gbCon.gridx = 0;
		gbCon.gridy = 0;
		newEstPanel.add(descTitleLabel,gbCon);
		
		sizeTitleLabel.setText("Estimated Size:");
		sizeTitleLabel.setPreferredSize(new Dimension(125, 25));
		gbCon.anchor = GridBagConstraints.FIRST_LINE_START;
		gbCon.gridwidth = 1;
		gbCon.gridheight = 1;
		gbCon.gridx = 2;
		gbCon.gridy = 0;
		newEstPanel.add(sizeTitleLabel,gbCon);
		
		descriptionTextA.setPreferredSize(new Dimension(500, 75));
		descriptionTextA.setLineWrap(true);
		descriptionTextA.setWrapStyleWord(true);
		gbCon.anchor = GridBagConstraints.PAGE_START;
		gbCon.gridwidth = 2;
		gbCon.gridheight = 2;
		gbCon.gridx = 0;
		gbCon.gridy = 1;
		newEstPanel.add(descriptionTextA,gbCon);
		
		estSizeTextF.setPreferredSize(new Dimension(125, 25));
		gbCon.anchor = GridBagConstraints.FIRST_LINE_START;
		gbCon.gridwidth = 1;
		gbCon.gridheight = 1;
		gbCon.gridx = 2;
		gbCon.gridy = 1;
		newEstPanel.add(estSizeTextF,gbCon);
		
		linesLabel.setText("lines");
		linesLabel.setPreferredSize(new Dimension(50, 25));
		gbCon.gridwidth = 1;
		gbCon.gridheight = 1;
		gbCon.gridx = 3;
		gbCon.gridy = 1;
		newEstPanel.add(linesLabel,gbCon);
		
		addButton.setPreferredSize(new Dimension(175, 40));
		addButton.setText("Add Estimation");
		gbCon.gridwidth = 2;
		gbCon.gridheight = 1;
		gbCon.gridx = 2;
		gbCon.gridy = 2;
		newEstPanel.add(addButton,gbCon);
		
		this.add(newEstPanel);
	}
	
	private void setupEstimationTablePanel() {
		estTablePanel.setLayout(new GridBagLayout());
		estTablePanel.setPreferredSize(new Dimension(750, 450));
			
		GridBagConstraints gbCon = new GridBagConstraints();
		gbCon.anchor = GridBagConstraints.PAGE_START;
		gbCon.weightx = 0.5;
		gbCon.weighty = 0.5;
		
		gbCon.gridwidth = 2;
		gbCon.gridheight = 1;
		gbCon.gridx = 0;
		gbCon.gridy = 0;
		
		tableTitleLabel.setText("Estimations:");
		tableTitleLabel.setPreferredSize(new Dimension(750, 25));
		estTablePanel.add(tableTitleLabel,gbCon);
		
		gbCon.gridy = 1;
		estTableSP = new JScrollPane(estimationTable);
		estTableSP.setPreferredSize(new Dimension(750,400));
		estTablePanel.add(estTableSP,gbCon);
		
		gbCon.anchor = GridBagConstraints.FIRST_LINE_START;
		gbCon.gridwidth = 1;
		gbCon.gridy = 2;
		removeButton.setPreferredSize(new Dimension(235, 25));
		removeButton.setText("Remove Selected Estimation");
		estTablePanel.add(removeButton,gbCon);
		
		gbCon.anchor = GridBagConstraints.FIRST_LINE_END;
		gbCon.gridx = 1;
		gbCon.insets = new Insets(0,5,0,5);
		estTotalLabel.setText(estTotalPrefix + "-");
		estTotalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		estTotalLabel.setPreferredSize(new Dimension(370, 25));
		estTablePanel.add(estTotalLabel,gbCon);
			
		this.add(estTablePanel);
	}
}
