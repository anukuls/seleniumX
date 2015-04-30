package runManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.net.InetAddress;
import java.net.UnknownHostException;

import utility.Properties_Utils;

import org.ini4j.Reg;
import org.openqa.grid.common.GridRole;
import org.openqa.grid.common.RegistrationRequest;
import org.openqa.grid.common.SeleniumProtocol;
import org.openqa.grid.internal.utils.GridHubConfiguration;
import org.openqa.grid.internal.utils.SelfRegisteringRemote;
import org.openqa.grid.web.Hub;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class SeleniumGrid_Distributed_Driver implements Distributed_Driver {
	
	public boolean isHub() {
		boolean hub_flag = false;
		
		String ipAddress = getSystemIPAddress();
		
		//NOTE: Read hub and node information from config
		String workingDir = System.getProperty("user.dir");
		String path = workingDir+"\\src\\config\\grid.properties";
		String hub_ip = Properties_Utils.get_property(path,"hub");
		
		if(ipAddress.equals(hub_ip)) {
			hub_flag = true;
			System.out.println("I am the hub...!!");
		}
		else
		{
			System.out.println("I am not the hub...!!");
		}
		
		return hub_flag;
	}
	
	public boolean isNode() {
		boolean node_flag = false;
		
		String ipAddress = getSystemIPAddress();
		
		//NOTE: Read hub and node information from config
		String workingDir = System.getProperty("user.dir");
		String path = workingDir+"\\src\\config\\grid.properties";
		
		String node_ips = Properties_Utils.get_property(path, "nodes");
		
		if(ipAddress.equals(node_ips)) {
			node_flag = true;
			System.out.println("I am the node...!!");
		}
		else
		{
			System.out.println("I am not the node...!!");
		}
		
		return node_flag;
	}
	
	public Hub startHub() {
		//NOTE: Read hub and node information from config
		String workingDir = System.getProperty("user.dir");
		String path = workingDir+"\\src\\config\\grid.properties";
		String hub_ip = Properties_Utils.get_property(path,"hub");
		Hub hub = configureHub(hub_ip);
		return hub;
	}
	
	public void stopHub(Hub hub) {
		//NOTE: Read hub and node information from config
//		String workingDir = System.getProperty("user.dir");
//		String path = workingDir+"\\src\\config\\grid.properties";
//		String hub_ip = Properties_Utils.get_property(path,"hub");
		try {
			hub.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SelfRegisteringRemote registerNode() {
		String workingDir = System.getProperty("user.dir");
		String path = workingDir+"\\src\\config\\grid.properties";
		String hub_ip = Properties_Utils.get_property(path,"hub");
		String node_ip = Properties_Utils.get_property(path, "nodes");
		SelfRegisteringRemote remote = configureNodes(hub_ip, node_ip, 5555);
		return remote;
	}
	
	public void unregisterNode(SelfRegisteringRemote remote) {
		try {
			remote.stopRemoteServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void readyMasterSlaves() {
		// TODO Auto-generated method stub
		
		//Store the nodes in grid.properties as comma separated values, e.g.
		//nodes=10.32.14.14,10.32.14.15. Split the combined string, and iterate 
		//through the array, and configure nodes on respective systems accordingly
		
		//TODO: Configure node ports in the properties file as well
//		String node_ips = Properties_Utils.get_property(path, "nodes");
//		
//		if(ipAddress == hub_ip) {
//			System.out.println("I am the hub...!!");
//			configureHub(hub_ip);
//		}
//		else if(ipAddress == node_ips)
//		{
//			System.out.println("I am the node...!!");
//			configureNodes(hub_ip, node_ips, 5555);
//		}
//		else
//		{
//			System.out.println("I am a nobody :( ...!!");
//		}
				
	}

	private static String getSystemIPAddress() {
		// TODO Auto-generated method stub
		String ipAddr = null;
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ipAddr = addr.getHostAddress();
			System.out.println(ipAddr);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ipAddr;
	}
	
	
	//	<suite name="Selenium TestNG Suite" parallel="tests" thread-count="5">
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
    //  </suite>
	public TestNG createDistributedTestNGXML(String run_config_path) {
		
		System.out.println(run_config_path);
		
		//Create an instance on TestNG
		TestNG myTestNG = new TestNG();
		
		//Create an instance of XML Suite and assign a name for it.
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName("Distributed Test Suites");
		mySuite.setParallel("tests");
		mySuite.setThreadCount(4);
		
		//<suite>
		
		//Create a list of XmlTests and add the Xmltest you created earlier to it.
		List<XmlTest> myTests = new ArrayList<XmlTest>();
		
		//In distributed mode, we will iterate on the node_ips, and put the scripts as class tags
		Set<Object> node_ips = Properties_Utils.get_property_keySet(run_config_path);
		
		for (Object node_ip:node_ips) {
			
			String ip = (String)node_ip;
			
			//Create an instance of XmlTest and assign a name for it.
			XmlTest myTest = new XmlTest(mySuite);
			myTest.setName(ip);
			
			//<suite>
			//   <test name="10.32.14.14">
			
			//Add params like browser, port and node ip
			Map<String,String> testngParams = new HashMap<String,String> ();
			//TODO: Pick browser info from another config file, name it grid_driver_config.properties
			testngParams.put("browser", "chrome");
			testngParams.put("port", "5555");
			testngParams.put("node", ip);
			myTest.setParameters(testngParams);
			
			//			<suite name="Selenium TestNG Suite" parallel="tests" thread-count="5">
			//
			//			    <test name="Selenium TestNG - 1">
			//			        <parameter name="browser" value="firefox" />
			//			        <parameter name="port" value="5555" />
			//					<parameter name="node" value="10.32.14.14">
			
			List<XmlClass> myClasses = new ArrayList<XmlClass>();
			String script_list = Properties_Utils.get_property(run_config_path, ip);
			System.out.println("Script List is :" + script_list);
			
			String[] scripts = script_list.split(",");
			System.out.println("Script List after split is:" + scripts);
			for (String script : scripts) {				
				
				System.out.println("Script List after split is:" + script);
				//Create a list which can contain the classes that you want to run.				
				myClasses.add(new XmlClass(script));
			}
			
			//			<suite name="Selenium TestNG Suite" parallel="tests" thread-count="5">
			//
			//			    <test name="Selenium TestNG - 1">
			//			        <parameter name="browser" value="firefox" />
			//			        <parameter name="port" value="5555" />
			//					<parameter name="node" value="10.32.14.14">
			//			        <classes>
			//			            <class name="com.test.testng.Google" />
			//			        </classes>
			
			//Assign that to the XmlTest Object created earlier.
			myTest.setXmlClasses(myClasses);
			
			myTests.add(myTest);
			
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
			
		}
		
		//add the list of tests to your Suite.
		mySuite.setTests(myTests);
		
		//Add the suite to the list of suites.
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		mySuites.add(mySuite);
		
		System.out.println(mySuites);
		
		
//		TestListenerAdapter	tla = new TestListenerAdapter();
//
//		List<Class> listenerClass = new ArrayList<Class>();
//		listenerClass.add(utility.Custom_Reporter.class);
//				
//		myTestNG.setListenerClasses(listenerClass);
//		myTestNG.addListener(listenerClass);
				
		//Set the list of Suites to the testNG object you created earlier.
		myTestNG.setXmlSuites(mySuites);
		
		return myTestNG;
	}

	@Override
	public void getRunConfig() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeTestSuites(String run_config_path) {
		// TODO Auto-generated method stub
		//TODO: 1. Objective is to create a distributed testng xml
		//2. 
		
		TestNG myTestNGSuites = createDistributedTestNGXML(run_config_path);
		
		List<Class> listenerClass = new ArrayList<Class>();
		listenerClass.add(utility.CustomReportListener.class);
		myTestNGSuites.setListenerClasses(listenerClass);
		
		//invoke run() - this will run your testng xml as a total suite.
		myTestNGSuites.run();		
	}

	@Override
	public void rerunFailures() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createReportArchive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uploadReportToCloud() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmail() {
		// TODO Auto-generated method stub
		
	}
	
	private static Hub configureHub(String hubIP) {
		GridHubConfiguration config = new GridHubConfiguration();
		config.setHost(hubIP);
		config.setPort(4444);
		Hub hub = new Hub(config);
		try {
			hub.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return hub;
	}
	
	//NOTE: Pass the hub object as argument to this method
	private static SelfRegisteringRemote configureNodes(String hubIP, String nodeIP, int nodePort) {
		RegistrationRequest req = new RegistrationRequest();
		req.setRole(GridRole.NODE);
		
		//TODO: Pick this value from runConfig and accordingly set browser capability
//		System.setProperty("webdriver.chrome.driver", "C:/Users/Smoke/git/seleniumX/drivers/chromedriver.exe");
//		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		
//		ChromeOptions options = new ChromeOptions();
//		options.setBinary("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
//		
//		chrome.setCapability(ChromeOptions.CAPABILITY, options);
		
//		System.out.println("Started chrome profile");
		
		DesiredCapabilities firefox = DesiredCapabilities.firefox();
		firefox.setBrowserName("*firefox");
		FirefoxProfile profile = new ProfilesIni().getProfile("Selenium");
		firefox.setCapability(FirefoxDriver.PROFILE, profile);
		firefox.setCapability("seleniumProtocol", SeleniumProtocol.Selenium);
		
//		chrome.setCapability(, profile);
//		chrome.setCapability("seleniumProtocol", SeleniumProtocol.Selenium);
		req.addDesiredCapability(firefox);
		
		Map<String, Object> nodeConfiguration = new HashMap<String,Object>();
		nodeConfiguration.put(RegistrationRequest.AUTO_REGISTER, true);
		nodeConfiguration.put(RegistrationRequest.HUB_HOST, hubIP);
		nodeConfiguration.put(RegistrationRequest.HUB_PORT, 4444);
		nodeConfiguration.put(RegistrationRequest.PORT, nodePort);
		
		URL remoteURL = null;
		try {
			remoteURL = new URL("http://" + nodeIP + ":" + nodePort);
//			remoteURL = new URL("http://10.32.14.14:5555");			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		nodeConfiguration.put(RegistrationRequest.PROXY_CLASS, "org.openqa.grid.selenium.proxy.DefaultRemoteProxy");
		nodeConfiguration.put(RegistrationRequest.MAX_SESSION, 2);
		nodeConfiguration.put(RegistrationRequest.CLEAN_UP_CYCLE, 2000);
		nodeConfiguration.put(RegistrationRequest.REMOTE_HOST, remoteURL);
		nodeConfiguration.put(RegistrationRequest.MAX_INSTANCES, 2);
		
		req.setConfiguration(nodeConfiguration);
		
		SelfRegisteringRemote remote = new SelfRegisteringRemote(req);
		try {
			remote.startRemoteServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		remote.startRegistrationProcess();
		return remote;
	}
	
	public void main(String run_config_path) {
		// TODO Auto-generated method stub
		SeleniumGrid_Distributed_Driver grid = new SeleniumGrid_Distributed_Driver();
//		grid.readyMasterSlaves();
		
//		getRunConfig();
		executeTestSuites(run_config_path);
//		rerunFailures();
//		createReportArchive();
//		uploadReportToCloud();
//		sendEmail();
	}

}
