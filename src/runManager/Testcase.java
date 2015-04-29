package runManager;

//Defining class for Testcase.  Every testScript will extend this class
public class Testcase {
	
	public void preScript() throws Exception {
		throw new Exception("preScript not defined");
	}
	
	public void preScript(String browser) throws Exception {
		throw new Exception("preScript not defined");
	}
	
	public void preScript(String browser, String node, String port) throws Exception {
		throw new Exception("preScript not defined");
	}
	
	public void main() throws Exception {
		throw new Exception("main not defined");
	}
	
	public void postScript() throws Exception {
		throw new Exception("postScript not defined");
	}

}
