package responses;

public class InternalServerError extends Response {
	String message;
	
	public InternalServerError(String message) {
		super(500, message);
	}
	
	public InternalServerError() {
		this("Internal Server Error");
	}

}
