package responses;

public class NotFoundError extends Response {
	String message;
	
	public NotFoundError(String message) {
		super(404, message);
	}
	
	public NotFoundError() {
		this("Not Found");
	}

}
