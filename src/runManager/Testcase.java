package runManager;

//Defining interface for Testcase.  Every testScript will implement this interface
//and define the implementation of the methods present inside
public interface Testcase {
	
	public void preScript() throws Exception;
	
	public void main() throws Exception;
	
	public void postScript() throws Exception;

}
