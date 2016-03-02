package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import net.sf.memoranda.util.Local;

public class EstimationPanel extends JPanel {
	
	JLabel descTitleLabel = new JLabel();
	JLabel sizeTitleLabel = new JLabel();
	JLabel linesLabel = new JLabel();
	JToolBar titleBar = new JToolBar();
	JTextField descriptionTextF = new JTextField();
	JTextField estSizeTextF = new JTextField();
	JButton addButton = new JButton();
	
	String[] columnNames = {"Module Description","Estimated Size"};
	Object[][] data = null;
	JTable estimationTable = new JTable(data,columnNames);
	
	public EstimationPanel() {
		this.setPreferredSize(new Dimension(1000, 1000));
		try {
			this.setLayout(new GridBagLayout());
			
			descTitleLabel.setText("Module Description:");
			descTitleLabel.setPreferredSize(new Dimension(500, 25));
			GridBagConstraints gbCon = new GridBagConstraints();
			gbCon.gridwidth = 2;
			gbCon.gridheight = 1;
			gbCon.gridx = 0;
			gbCon.gridy = 0;
			this.add(descTitleLabel,gbCon);
			
			sizeTitleLabel.setText("Estimated Size:");
			sizeTitleLabel.setPreferredSize(new Dimension(125, 25));
			gbCon.gridwidth = 1;
			gbCon.gridheight = 1;
			gbCon.gridx = 2;
			gbCon.gridy = 0;
			this.add(sizeTitleLabel,gbCon);
			
			descriptionTextF.setPreferredSize(new Dimension(500, 75));
			gbCon.fill = GridBagConstraints.NORTH;
			gbCon.gridwidth = 2;
			gbCon.gridheight = 2;
			gbCon.gridx = 0;
			gbCon.gridy = 1;
			this.add(descriptionTextF,gbCon);
			
			estSizeTextF.setPreferredSize(new Dimension(125, 25));
			gbCon.gridwidth = 1;
			gbCon.gridheight = 1;
			gbCon.gridx = 2;
			gbCon.gridy = 1;
			this.add(estSizeTextF,gbCon);
			
			linesLabel.setText("lines");
			linesLabel.setPreferredSize(new Dimension(50, 25));
			gbCon.gridwidth = 1;
			gbCon.gridheight = 1;
			gbCon.gridx = 3;
			gbCon.gridy = 1;
			this.add(linesLabel,gbCon);
			
			addButton.setPreferredSize(new Dimension(175, 40));
			addButton.setText("Add Estimation");
			gbCon.gridwidth = 2;
			gbCon.gridheight = 1;
			gbCon.gridx = 2;
			gbCon.gridy = 2;
			this.add(addButton,gbCon);
			
			estimationTable.setPreferredSize(new Dimension(675,400));
			gbCon.gridwidth = 4;
			gbCon.gridheight = 1;
			gbCon.gridx = 0;
			gbCon.gridy = 3;
			this.add(estimationTable,gbCon);
			
		} catch (Exception ex) {
			new ExceptionDialog(ex);
		}
	}
}
