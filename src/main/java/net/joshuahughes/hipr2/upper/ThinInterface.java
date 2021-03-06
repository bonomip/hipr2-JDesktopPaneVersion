package net.joshuahughes.hipr2.upper;
//package code.iface.thin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.Serializable;
//import code.iface.highlevel.*;
//import code.iface.common.*;
//import code.iface.kernels.*;
//import code.operator.thin.*;

/**
 * The interface for the thin operator (code.operator.thin.Thin.java).
 *
 * @author Simon Horne.
 */
public class ThinInterface extends SingleBinaryImageInterface implements Serializable{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
ThreeState3x3Kernel kernel;
  TimeTakenInterface timetaken;
  IterationsInterface iterations;
  private long time1, time2;
  private int i;
  /**
   * Sets up the actions to be carried out when the start and stop buttons are pressed.
   */
  public void setOperation(){

    startstop.start.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	//Start the timer
	time1 = System.currentTimeMillis();
	
	int kernelArray [] = new int[9];
	kernelArray = kernel.array();

	binary = 
	  Thin.thin_image(binary,kernelArray,
				iterations.number());
	outputArray = binary.convertToArray();
	updateOutput(createImage(new MemoryImageSource(inputWidth, 
						       inputHeight, 
						       outputArray, 
						       0, inputWidth)),
		     inputWidth,inputHeight);
	
	//Stop the timer					      
	time2 = System.currentTimeMillis();
	timetaken.updateTime(time1,time2);
	updateInput(inputImage);
      }});
    
    startstop.stop.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
      }
    });

  }
  /**
   * Sets up the interface and displays it on the screen.
   */
  public void init(){

    setLook();
    setContainer();

    initialImage("wdg2thr4.gif");
    initialiseImages();

    kernel = new ThreeState3x3Kernel();
    timetaken = new TimeTakenInterface();
    iterations = new IterationsInterface();

    JPanel kernelPanel = kernel.createPanel(getDocumentBase());
    JPanel timetakenPanel = timetaken.createPanel();
    JPanel iterationsPanel = iterations.createPanel();

    setOperation();

    operatorPanel.add(kernelPanel);
    operatorPanel.add(iterationsPanel);
    operatorPanel.add(timetakenPanel);

  }
    /**
   * Used by the browser to obtain details of the applet.
   *
   * @return Brief description of the applet.
   */
  public String getAppletInfo() {
    return "An applet for thinning an image.  Author Simon Horne.";
  }
}
