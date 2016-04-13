package net.sf.memoranda.ui;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;////////Delete
import javax.swing.JPanel;


/**
 * Creates a Summary tab that is used in the PSP icon
 *
 */
public class SummaryPanel extends JPanel implements Observer{
	
	public SummaryPanel(){
        this.setPreferredSize(new Dimension(1000, 1000));
        new JPanel();
	}

	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	
		
	}
}


