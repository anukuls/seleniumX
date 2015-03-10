package runManager;

import java.net.MalformedURLException;
import java.net.URL;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;


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

	@Override
	public void getRunConfig() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeTestSuites() {
		// TODO Auto-generated method stub
		
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
		
		DesiredCapabilities firefox = DesiredCapabilities.firefox();
		firefox.setBrowserName("*firefox");
		FirefoxProfile profile = new ProfilesIni().getProfile("Selenium");
		firefox.setCapability(FirefoxDriver.PROFILE, profile);
		firefox.setCapability("seleniumProtocol", SeleniumProtocol.Selenium);
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
		nodeConfiguration.put(RegistrationRequest.MAX_SESSION, 1);
		nodeConfiguration.put(RegistrationRequest.CLEAN_UP_CYCLE, 2000);
		nodeConfiguration.put(RegistrationRequest.REMOTE_HOST, remoteURL);
		nodeConfiguration.put(RegistrationRequest.MAX_INSTANCES, 1);
		
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeleniumGrid_Distributed_Driver grid = new SeleniumGrid_Distributed_Driver();
		grid.readyMasterSlaves();
//		getRunConfig();
//		executeTestSuites();
//		rerunFailures();
//		createReportArchive();
//		uploadReportToCloud();
//		sendEmail();
	}

}
