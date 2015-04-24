package runManager;

//Defining interface for Testcase.  Every testScript will implement this interface
//and define the implementation of the methods present inside
public class Testcase {
	
	public void preScript() throws Exception {
		throw new Exception("preScript not defined");
	}
	
	public void preScript(String browser) throws Exception {
		throw new Exception("preScript not defined");
	}
	
	public void preScript(String browser, String port, String node) throws Exception {
		throw new Exception("preScript not defined");
	}
	
	public void main() throws Exception {
		throw new Exception("main not defined");
	}
	
	public void postScript() throws Exception {
		throw new Exception("postScript not defined");
	}

}
