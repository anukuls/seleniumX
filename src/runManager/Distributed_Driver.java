package runManager;

public interface Distributed_Driver {

	public void readyMasterSlaves();
	
	public void getRunConfig();
	
	public void executeTestSuites(String run_config);
	
	public void rerunFailures();
	
	public void createReportArchive();
	
	public void uploadReportToCloud();
	
	public void sendEmail();
	
}
