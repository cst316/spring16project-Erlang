package net.sf.memoranda.ui;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * This class demonstrates how to load an Image from an external file
 * Source: https://docs.oracle.com/javase/tutorial/2d/images/examples/LoadImageApp.java 
 */
public class ExportedImage extends Component {
          
    BufferedImage image;

    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

    public ExportedImage() {
      image = null;
    }

    public Dimension getPreferredSize() {
        if (image == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(image.getWidth(null), image.getHeight(null));
       }
    }
    
    public  void checkExported(){
    	try {
     	   image = ImageIO.read(new File("src/net/sf/memoranda/ui/resources/SketchImage.png"));
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "No Exported File Found!");
        }
    }
  
}
