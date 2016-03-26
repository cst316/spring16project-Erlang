package net.sf.memoranda.ui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ImportDataTimer extends JApplet {
	private String[] tabs = { "Planning", "Design", "estimation", "Coding"};

  private JTextField t = new JTextField(15);

  private JComboBox dropDown = new JComboBox();

  private JButton b = new JButton("Select");


  
  
  /* 
  
  private int count = 0;

  public void init() {
    for (int i = 0; i < 4; i++)
      c.addItem(description[count++]);
    t.setEditable(false);
    b.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (count < description.length)
          c.addItem(description[count++]);
      }
    });
    c.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        t.setText("index: " + c.getSelectedIndex() + "   "
            + ((JComboBox) e.getSource()).getSelectedItem());
      }
    });
    
  Container cp = getContentPane();
    cp.setLayout(new FlowLayout());
    cp.add(t);
    cp.add(c);
    cp.add(b);
  }

 
 // public void main() {
 //   run(new ImportDataTimer(), 200, 125);
 // }

  public static void run(JApplet applet, int width, int height) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(applet);
    frame.setSize(width, height);
    applet.init();
    applet.start();
    frame.setVisible(true);
  }
  */
} ///:~
