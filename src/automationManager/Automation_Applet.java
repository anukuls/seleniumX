package automationManager;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import utility.Read_Files;

public class Automation_Applet extends JPanel implements ItemListener {
	
	JPanel jp = new JPanel();
	JCheckBox suite1;
	JButton btn;
	JLabel lbl;
	JLabel lbl1;
	JTextField jtf;
	
	ArrayList<String> suitesArr;
	ArrayList<String> scriptsArr;
	String suite_path;
	  
	public Automation_Applet(){
		
		suitesArr = Read_Files.readDir();
		System.out.println(suitesArr);
		
		jp.setLayout(new GridLayout(15,50));
		lbl = new JLabel("Welcome to the Automation Manager.  Please select the suites to be executed");
		jp.add(lbl);
		
		lbl1 = new JLabel();
		
		for (String s : suitesArr) {
			suite1 = new JCheckBox(s);
			suite1.setName(s);
			jp.add(suite1);
			lbl1.setText("Select Test Scripts");
			jp.add(lbl1);
			suite1.addItemListener(new ItemListener() {
				
				ArrayList<JCheckBox> arrCBox = new ArrayList<JCheckBox>();
				ArrayList<JButton> jbtns = new ArrayList<JButton>();
				Map<String, JButton> jbuttons = new HashMap<String, JButton>();
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					// TODO Auto-generated method stub
					JCheckBox check = (JCheckBox)e.getSource();
					String checkboxName = check.getName();

					String workingDir = System.getProperty("user.dir");
				    String path = workingDir+"\\src\\testScripts";
				    String suite_path = path + "\\" + checkboxName;
					scriptsArr = Read_Files.readFilesForFolder(suite_path);
					
					if (check.isSelected()) {						
						btn = new JButton();
						jbuttons.put(checkboxName, btn);
						System.out.println("jbutton hash map: " + jbuttons);												
						
						for (String btnkey : jbuttons.keySet()) {
							System.out.println("kutte");
							jp.remove(jbuttons.get(btnkey));	
							jp.revalidate();
							jp.repaint();
						}
						
						for (String x : scriptsArr) {
							
//							jp.remove(btn);
							
														
							suite1 = new JCheckBox(x);
							arrCBox.add(suite1);							
							jp.add(suite1);														
							
//							Component[] cmps = jp.getComponents();
//							for (Component cmp : cmps) {
//								System.out.println(cmp.getClass());
//							}

							jp.add(btn);
							btn.setText("Execute");							

//							Component cmp = btn.getParent();
//							System.out.println("buttons parent is:");
//							System.out.println(cmp);
//							if (cmp == null){
								
//							}
							
							jp.revalidate();
							jp.repaint();							
						}																			
					}
					else
					{
						System.out.println("number of checkboxes are:");
						System.out.println(arrCBox.size());
//						jp.remove(lbl1);
						for (JCheckBox c : arrCBox) {
							jp.remove(c);													
						}	
												
//						JButton b = jbuttons.remove(btn);
//						jp.remove(btn);
						
						for (String btnkey : jbuttons.keySet()) {
							jp.remove(jbuttons.get(btnkey));								
						}
						
//						for (JButton b : jbtns) {
//							jp.remove(b);
//						}
//						btn.hide();
						jp.revalidate();
						jp.repaint();
					}						
				}
			});
		}
		

//		System.out.println("button get text:");
//		System.out.println(btn.getText());
		
//		if (btn.getText() != "Execute") {
//		}
		
//		jp.revalidate();
//		jp.repaint();
		
		setLayout(new BorderLayout());
        add(jp, BorderLayout.WEST);
        add(jp, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        
	}
    
    public static void main(String s[]) {
        JFrame frame = new JFrame("Automation Manager");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setContentPane(new Automation_Applet());
        
        frame.pack();
        frame.setVisible(true);
   }

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

}