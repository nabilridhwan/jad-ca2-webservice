/*
 * 	Name: Nabil Ridhwanshah Bin Rosli
	Admin No: P2007421
	Class: DIT/FT/2A/01
	Group Number: Group 4 - TAY CHER YEW XAVIER, NABIL RIDHWANSHAH BIN ROSLI 
 * */

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
