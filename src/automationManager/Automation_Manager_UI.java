package automationManager;

//import javax.mail.MessagingException;
//import javax.mail.internet.AddressException;
import javax.swing.*;

import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.web.Hub;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import utility.Read_Files;
import utility.Properties_Utils;
import runManager.Base_Driver;
import runManager.SeleniumGrid_Distributed_Driver;

public class Automation_Manager_UI extends JPanel implements ItemListener { 
	
	JPanel jp = new JPanel();
	JCheckBox suite1;
	JButton btn;
	JButton hubBtn;
	JButton nodeBtn;
	JLabel lbl;
	JLabel lbl1;
	JLabel lbl2;
	JLabel hubLabel;
	JLabel nodeLabel;
	JRadioButton radio1;
	JRadioButton radio2;
	ButtonGroup bg;
	JTextField jtf;
	
	ArrayList<String> suitesArr;
	ArrayList<String> scriptsArr;
	String suite_path;
//	HashMap<String, String[]> suites_hash = new HashMap<String, String[]>();
	HashMap<String, ArrayList<String>> suites_hash = new HashMap<String, ArrayList<String>>();
	ArrayList<String> checked_scripts = new ArrayList<String>();
	
	SeleniumGrid_Distributed_Driver driver = new SeleniumGrid_Distributed_Driver();
	
	public Automation_Manager_UI() {
		suitesArr = Read_Files.readDir();
		System.out.println(suitesArr);
		
		jp.setLayout(new GridLayout(15,50));
		lbl = new JLabel("Welcome to the Automation Manager.  Please choose the mode of execution");
		lbl2 = new JLabel();
		jp.add(lbl);
		
		radio1 = new JRadioButton("Single Machine Mode");
		radio2 = new JRadioButton("Multi Machine Mode");
		bg = new ButtonGroup();
		bg.add(radio1);
		bg.add(radio2);
		jp.add(radio1);
		
		setLayout(new BorderLayout());
        add(jp, BorderLayout.WEST);
        add(jp, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
		
		radio1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == 1) 
				{
					lbl2.setText("Choose Scripts to execute");
					jp.add(lbl2);
					jp.revalidate();
					jp.repaint();
				}
				
				System.out.println("Single mode radio state is: " + e.getStateChange());
				
				JRadioButton rad = (JRadioButton)e.getSource();
				if(rad.isSelected()) {
					initUI();
				}				
			}
			
		});
		
		
		jp.add(radio2);
		radio2.addItemListener(new ItemListener() {
			JButton hubBtn = new JButton("Start Hub");
			JButton nodeBtn = new JButton("Register Node");
			Hub hub;
			SelfRegisteringRemote remote;
			
			@Override			
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
//				initUI();
				hubLabel = new JLabel();
				nodeLabel = new JLabel();
				
				if(driver.isHub() && e.getStateChange() == 1) 
				{
					hubLabel.setText("I am the hub...Start me!!");
					jp.add(hubLabel);
					jp.add(hubBtn);
					hubBtn.setName("Start Hub");
					hubBtn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(hubBtn.getText().equals("Start Hub"))
							{
								hub = driver.startHub();
								hubLabel.setText("Hub successfully started.  Register nodes with me!!!");
								hubBtn.setText("Stop Hub");
								hubBtn.setName("Stop Hub");
								//TODO: First wait for nodes to register with hub, and then present scripts to be executed
								//TODO: After the node registers with hub, present a button to launch the scripts in remote mode based 
								//on the configuration defined in runConfig.properties, rather than giving user option to select which 
								//script to execute.  This is because there might be a lot of scripts and user wont have the time to select 
								//which script to be run on which machine.  Script selection is fine when running in Base Driver mode on a single
								//machine.  So, do not call initUI method, rather implement a method which triggers run based on config defined
								//in runConfig.properties on each of the defined nodes.
//								lbl2.setText("Choose Scripts to execute");
//								jp.add(lbl2);
//								jp.revalidate();
//								jp.repaint();
//								initUI();
								
								//TODO: Show the batch script execute button only when determined that nodes are registered with hub
								executeBatchScripts();
							}
							else
							{
								driver.stopHub(hub);
								hubLabel.setText("Hub successfully stopped.  Feel free to start me again!!!");
								hubBtn.setText("Start Hub");
								hubBtn.setName("Start Hub");
								//TODO: On stopping hub, remove checkboxes for label - "Choose scripts to execute"
							}							
						}
					});
					jp.revalidate();
					jp.repaint();
				}
				else if(driver.isNode() && e.getStateChange() == 1)
				{
					nodeLabel.setText("I am the node...Register me with the hub!!");
					jp.add(nodeLabel);
					jp.add(nodeBtn);
					nodeBtn.setName("Register Node");
					nodeBtn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(nodeBtn.getText().equals("Register Node"))
							{
								remote = driver.registerNode();
								nodeLabel.setText("Node successfully registered with hub!!!");
								nodeBtn.setText("Unregister Node");
								nodeBtn.setName("Unregister Node");
							}
							else
							{
								driver.unregisterNode(remote);
								nodeLabel.setText("Node successfully unregistered with hub..Register again to run your tests!!!");
								nodeBtn.setText("Register Node");
								nodeBtn.setName("Register Node");
							}							
						}
					});
					jp.revalidate();
					jp.repaint();
				}	
			}
			
		});
		//TODO: 1. Provide radio option for single machine mode or grid mode - done
		//2. If single machine mode chosen, call initUI, and let the base driver take its seat - done
		//3. If multi mode chosen, do not call initUI, determine from config whether machine under execution is a hub or a node - done
		//4. If hub, present a message and a button saying I am a HUB, start me.  Also, present message saying register nodes with this hub - done
		//5. Logon to the node machines, invoke the automation manager. Choose multi mode, and register nodes with the hub - done
		//6. Once registration is complete, invoke the initUI method to select the scripts
		//7. On press of execute, start executing scripts on the nodes specified in the scripts. Pick that information from another config file
	}
	
	public void initUI() {
		lbl1 = new JLabel();
		for (String s : suitesArr) {
			suite1 = new JCheckBox(s);
			suite1.setName(s);
			jp.add(suite1);
			lbl1.setText("Select Test Scripts");
			jp.add(lbl1);
			suite1.addItemListener(this);
		}	
	}
	
	public void deleteAllButtons() {
		Component[] cmps = jp.getComponents();
		for (Component cmp : cmps) {
			System.out.println(cmp.getClass().toString());
			System.out.println(cmp.getName());
			
			if (cmp.getClass().toString().equals("class javax.swing.JButton") && cmp.getName().toString().equals("Execute")) {
				jp.remove(cmp);
			}
			else
			{
				
			}
		}
		jp.revalidate();
		jp.repaint();
	}
	
	public void deleteScriptsForSuite(String suite_name) {
		Component[] cmps = jp.getComponents();
		String name = suite_name + "" + suite_name;
		for (Component cmp : cmps) {
			if (cmp.getClass().toString().equals("class javax.swing.JCheckBox") && cmp.getName().toString().equals(name)) {
				jp.remove(cmp);
			}
		}
	}
	
	public void executeBatchScripts() {
		JLabel batchLabel = new JLabel();
		JButton batchBtn = new JButton();
		batchBtn.setName("batchButton");
		jp.add(batchLabel);
		batchLabel.setText("Execute scripts defined in runConfig.properties!!");
		jp.add(batchBtn);
		batchBtn.setText("Start Parallel Run");
		batchBtn.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent e) {
				String workingDir = System.getProperty("user.dir");
			    final String runConfigPath = workingDir+"\\src\\config\\runConfig.properties";
			    
			    //TODO: Form a testng.xml in SeleniumGrid_Distributed_Driver.java file such as this:
			    //			<suite name="Selenium TestNG Suite" parallel="tests" thread-count="5">
				//
				//			    <test name="Selenium TestNG - 1">
				//			        <parameter name="browser" value="firefox" />
				//			        <parameter name="port" value="5555" />
				//					<parameter name="node" value="10.32.14.14">
				//			        <classes>
				//			            <class name="com.test.testng.Google" />
				//			        </classes>
				//			    </test>
				//				
				//			    <test name="Selenium TestNG - 2">
				//			        <parameter name="browser" value="chrome" />
				//			        <parameter name="port" value="6666" />
				//					<parameter name="node" value="10.32.14.15">
				//			        <classes>
				//			            <class name="com.test.testng.Google" />
				//			        </classes>
				//			    </test>
				//
				//			</suite>
			    driver.main(runConfigPath);
			    //TODO: Do the above by calling the main method of SeleniumGrid_Distributed_Driver class (just like we call Base_Driver.main for single run mode)
			}
		});
	}
	
	public HashMap<String,ArrayList<String>> getSuiteScriptHash() {
		HashMap<String, ArrayList<String>> suite_script_hash = new HashMap<String, ArrayList<String>>();
		
		suite_script_hash = suites_hash;
		System.out.println("Final suite script hash is: " + suite_script_hash);
		//TODO: form suite script hash here, and return
		
		
		return suite_script_hash;
	}
	
	
	public String getParentSuite(String script){
		String workingDir = System.getProperty("user.dir");
		String parent_suite = null;
		for (String x :suitesArr){
			
			String suite_path = workingDir +"\\src\\testScripts\\"+x; 
			ArrayList<String> scripts = Read_Files.readFilesForFolder(suite_path);
			for(String y : scripts){
				if (y.equals(script)){
					parent_suite = x;
					break;
				}
			}
		}
		return parent_suite;
	}
	

	public void addExecuteButton() {
		Component[] cmps = jp.getComponents();
		for (Component cmp : cmps) {
			if (cmp.getClass().toString().equals("class javax.swing.JCheckBox") && suitesArr.contains(cmp.getName().toString())) {
				JCheckBox cbox = new JCheckBox();
				cbox = (JCheckBox)cmp;
				if (cbox.isSelected()) {
					btn = new JButton("Execute");
					btn.setName("Execute");
					jp.add(btn);
					btn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							
							HashMap<String, ArrayList<String>> args = new HashMap<String, ArrayList<String>>();
//							String[] test = {"testScripts.suiteC.TestNG_POC", "testScripts.suiteC.TestNG_POC2"};
//							String[] test1 = {"testScripts.suiteD.TestNG_Stub"};
//							args.put("SuiteC", test);
//							args.put("SuiteD", test1);
							
							//TODO: get the selected suites and scripts, and form a hashmap
							args = getSuiteScriptHash();
							
//							try {
							Base_Driver.main(args);
//							} catch (AddressException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							} catch (MessagingException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
						}
					});
					jp.repaint();
					jp.revalidate();
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Automation Manager");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setContentPane(new Automation_Manager_UI());
        
        frame.pack();
        frame.setVisible(true);

	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		deleteAllButtons();
		
		JCheckBox check = (JCheckBox)e.getSource();
		String checkboxName = check.getName();
//		String[] scripts = {};
		ArrayList<String> scripts = new ArrayList<String>();
		
		String workingDir = System.getProperty("user.dir");
	    String path = workingDir+"\\src\\testScripts";
	    String suite_path = path + "\\" + checkboxName;
		scriptsArr = Read_Files.readFilesForFolder(suite_path);
		
		if (check.isSelected()) 
		{
			suites_hash.put(checkboxName, scripts);
			for (String x : scriptsArr) {
				String name = checkboxName + "" + checkboxName;
				suite1 = new JCheckBox(x);
				suite1.setName(name);							
				jp.add(suite1);	
				suite1.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent arg0) {
						// TODO Auto-generated method stub
						JCheckBox script_check = (JCheckBox)arg0.getSource();
						String name = script_check.getText();
						String parent = getParentSuite(name);
						
						System.out.println("Parent suite is: " + parent);
						//NOTE: Need to move this checked_scripts var outside the event since it resets the array list 
						//everytime a checkbox is checked
//						ArrayList<String> checked_scripts = new ArrayList<String>();
//						String[] checked_array = suites_hash.get(parent);
						ArrayList<String> checked_array = suites_hash.get(parent);
						System.out.println("checked array is: " + checked_array);
//						Collections.addAll(checked_scripts , checked_array);
						
						if (script_check.isSelected()){
							//NOTE: Need to remove .java from file names since TestNG does not like .java 
							//in the class tag
							checked_scripts.add("testScripts."+parent+"."+name.substring(0, name.length() - 5));
							System.out.println("checked script is: " + checked_scripts);
//							checked_array = (String[])checked_scripts.toArray();
							
							suites_hash.get(parent).add("testScripts."+parent+"."+name.substring(0, name.length() - 5));
//							suites_hash.put(parent, checked_array);
//							suites_hash.put(parent, checked_scripts);
							System.out.println("suite script hash is: " + suites_hash);
						}
						else {
							checked_scripts.remove("testScripts."+parent+"."+name.substring(0, name.length() - 5));
							System.out.println("checked script on removing is: " + checked_scripts);
//							checked_array = (String[])checked_scripts.toArray();
//							suites_hash.put(parent, checked_array);
//							suites_hash.put(parent, checked_scripts);
							suites_hash.get(parent).remove("testScripts."+parent+"."+name.substring(0, name.length() - 5)); 
							System.out.println("suite script hash on removing is: " + suites_hash);
							
						}
					}
					
				});
			}
		}
		else
		{
			suites_hash.remove(checkboxName);
			deleteScriptsForSuite(checkboxName);
		}
				
		addExecuteButton();
	}

}
