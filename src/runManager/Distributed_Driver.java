package runManager;

public interface Distributed_Driver {

	public void readyMasterSlaves();
	
	public void getRunConfig();
	
	public void executeTestSuites();
	
	public void rerunFailures();
	
	public void createReportArchive();
	
	public void uploadReportToCloud();
	
	public void sendEmail();
	
}
