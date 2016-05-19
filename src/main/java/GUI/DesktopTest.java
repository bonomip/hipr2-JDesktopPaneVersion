package GUI;

//Java core packages   
import java.awt.*;   
import java.awt.event.*;   
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;




//Java extension packages   
import javax.swing.*;

import NewObject.GUITotInternal;   

public class DesktopTest extends JFrame {   
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JDesktopPane theDesktop;   

// set up GUI   
public DesktopTest()   
{   
   super( "HIPR2 Multi-Tableau" );   
   // create menu bar, menu and menu item   
   JMenuBar bar = new JMenuBar();   
   JMenu addMenu = new JMenu( "Add" );   
   JMenuItem newFrame = new JMenuItem( "New HIPR2 Tableau" );   

   addMenu.add( newFrame );   
   //bar.add( addMenu );   
      
   JMenuItem loadFrame = new JMenuItem( "Load HIPR2 Tableau" );   
   addMenu.add( loadFrame );   
   
   bar.add( addMenu );
   
   JMenuItem aboutItem = new JMenuItem("About...");
	aboutItem.setMnemonic('A');
	addMenu.add(aboutItem);
	aboutItem.addActionListener(
		new ActionListener() {
			public void actionPerformed(ActionEvent event){
				JOptionPane.showMessageDialog(getContentPane()
						, "Progetto creato da Nicola Landro, 2015", "About"
						,JOptionPane.INFORMATION_MESSAGE);			
			}
		} 
	);
   
   setJMenuBar( bar );   

   // set up desktop   
   theDesktop = new JDesktopPane();   
   getContentPane().add( theDesktop );   
   
   loadFrame.addActionListener(
		   new ActionListener() {
			   public void actionPerformed( ActionEvent event ) {
				   ObjectInputStream stream;
				try {
					stream = new ObjectInputStream(new FileInputStream("test.t"));
					GUITotInternal g = (GUITotInternal) stream.readObject();
					stream.close();
					
					g.setupMenuBar();
					
					JInternalFrame frame = g.getFrame();
		            
		            // attach panel to internal frame content pane   
		            //Container container = frame.getContentPane();     

		            // set size internal frame to size of its contents   
		            frame.pack();   

		            // attach internal frame to desktop and show it   
		            theDesktop.add( frame );   
		            frame.setVisible( true ); 
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				   
			   }
		   }
	);
   
   
   // set up listener for newFrame menu item   
   newFrame.addActionListener(   

      // anonymous inner class to handle menu item event   
      new ActionListener() {   

         // display new internal window   
         public void actionPerformed( ActionEvent event ) {   

            // create internal frame   
            JInternalFrame frame = new GUITotInternal().getFrame();
            
            // attach panel to internal frame content pane   
            //Container container = frame.getContentPane();     

            // set size internal frame to size of its contents   
            frame.pack();   

            // attach internal frame to desktop and show it   
            theDesktop.add( frame );   
            frame.setVisible( true );   
         }   

      }  // end anonymous inner class   

   ); // end call to addActionListener   

   setSize( 600, 440 ); 

   setVisible( true );   

}  // end constructor   

// execute application   
public static void main( String args[] )   
{        
   DesktopTest application = new DesktopTest();   

   application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
   
   application.setExtendedState(JFrame.MAXIMIZED_BOTH);
}
//public static void setFrame(JInternalFrame f){
//	Container container = f.getContentPane();
//	theDesktop.add(container);
//	container.setVisible(true);
//}

public static JInternalFrame setFrame(String name ,Container container){
	JInternalFrame f = new JInternalFrame(name, true, true, true, true);
	//f.setContentPane(container);
	f.getContentPane().add(container);
	//Container c = f.getContentPane(); 
	f.pack();
	theDesktop.add(f);
	f.setVisible(true);
	return f;
}
public static JInternalFrame setFrame(String name ,JPanel container){
	JInternalFrame f = new JInternalFrame(name, true, true, true, true);
	//f.setContentPane(container);
	f.getContentPane().add(container);
	//Container c = f.getContentPane(); 
	f.pack();
	theDesktop.add(f);
	f.setVisible(true);
	return f;
}
//quello che uso
public static JInternalFrame setFrame(String name ,JFrame container){
	JInternalFrame f = new JInternalFrame(name, true, true, true, true);
	//f.setContentPane(container);
	f.getContentPane().add(container.getContentPane());
	//Container c = f.getContentPane(); 
	f.pack();
	theDesktop.add(f);
	f.setVisible(true);
	return f;
}
public static void addFrame(JInternalFrame frame){
	theDesktop.add(frame);
}

}  // end class DesktopTest   


