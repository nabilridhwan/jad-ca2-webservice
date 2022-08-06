/*
 * 	Name: Nabil Ridhwanshah Bin Rosli
	Admin No: P2007421
	Class: DIT/FT/2A/01
	Group Number: Group 4 - TAY CHER YEW XAVIER, NABIL RIDHWANSHAH BIN ROSLI 
 * */

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
