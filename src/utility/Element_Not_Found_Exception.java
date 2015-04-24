package utility;

public class Element_Not_Found_Exception extends Exception {
	
	private String exception_msg = null;
	
	public Element_Not_Found_Exception() {
		super();
	}

	public Element_Not_Found_Exception(String exception_msg, Exception e) {
		super(exception_msg + e.toString());
		this.exception_msg = exception_msg;
	}
	
	public Element_Not_Found_Exception(Throwable cause) {
		super(cause);
	}
	
	@Override
	public String toString() {
		return exception_msg;
	}
	
	@Override
	public String getMessage() {
		return exception_msg;
	}
	
}

