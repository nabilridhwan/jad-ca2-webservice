/*
 * 	Name: Nabil Ridhwanshah Bin Rosli
	Admin No: P2007421
	Class: DIT/FT/2A/01
	Group Number: Group 4 - TAY CHER YEW XAVIER, NABIL RIDHWANSHAH BIN ROSLI 
 * */

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
