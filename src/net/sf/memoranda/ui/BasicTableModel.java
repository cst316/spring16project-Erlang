/*
 * This class implements examples shown on the "How to Use Tables" section
 * of "The Java Tutorials". It can be found here:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
 */

package net.sf.memoranda.ui;

import javax.swing.table.AbstractTableModel;

public class BasicTableModel extends AbstractTableModel {
	private String[] columnNames;
    private Object[][] data; 
    
    public BasicTableModel(String[] aColumnNames, Object[][] aData) {
    	super();
    	this.columnNames = aColumnNames;
    	this.data = aData;
    }

	public int getColumnCount() {
	    return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
        return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
	    return data[row][col];
	}

	public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
	}

	public void setValueAt(Object value, int row, int col) {
	    data[row][col] = value;
		fireTableCellUpdated(row, col);
	}
}
