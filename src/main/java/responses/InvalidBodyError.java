package responses;

public class InvalidBodyError extends Response {
	String message;
	
	public InvalidBodyError(String message) {
		super(400, message);
	}
	
	public InvalidBodyError() {
		this("Invalid Body");
	}

}
