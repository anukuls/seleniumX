package runManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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

	@Override
	public void readyMasterSlaves() {
		// TODO Auto-generated method stub
		//NOTE: Form the connection between hub and the nodes. We can put
		//all that info to the config later, but for now, just use direct
		//hardcoded stuff for the POC
		configureHub();
		configureNodes();
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
	
	private void configureHub() {
		GridHubConfiguration config = new GridHubConfiguration();
		config.setHost("10.32.14.13");
		config.setPort(4444);
		Hub hub = new Hub(config);
		try {
			hub.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		nodeConfiguration.put(RegistrationRequest.HUB_HOST, hub.getHost());
		nodeConfiguration.put(RegistrationRequest.HUB_PORT, hub.getPort());
		nodeConfiguration.put(RegistrationRequest.PORT, 5555);
		
		URL remoteURL = null;
		try {
			remoteURL = new URL("http://" + hub.getHost() + ":" + 5555);
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
	}
	
	private void configureNodes() {
		
	}
	
	public void main(String[] args) {
		// TODO Auto-generated method stub
		readyMasterSlaves();
		getRunConfig();
		executeTestSuites();
		rerunFailures();
		createReportArchive();
		uploadReportToCloud();
		sendEmail();
	}

}
