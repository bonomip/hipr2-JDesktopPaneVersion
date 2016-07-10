package net.joshuahughes.hipr2.upper;
//package code.iface.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

public class ThresholdInterface implements Serializable{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JPanel p;
  private JCheckBox checkbox;

  public boolean getBoolean(){
    return checkbox.isSelected();
  }

  public JPanel createPanel(){
    p = new JPanel();
    checkbox = new JCheckBox("Threshold");
    p.setOpaque(false);
    checkbox.setOpaque(false);
    p.add(checkbox);
    return p;
  }

}
