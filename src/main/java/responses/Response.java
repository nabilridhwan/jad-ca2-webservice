/*
 * 	Name: Nabil Ridhwanshah Bin Rosli
	Admin No: P2007421
	Class: DIT/FT/2A/01
	Group Number: Group 4 - TAY CHER YEW XAVIER, NABIL RIDHWANSHAH BIN ROSLI 
 * */

package responses;

import java.util.HashMap;

public class Response {
	
	private boolean error = false;
	private Object entity;
	private int status;
	
	public Response(int status, Object entity) {
		if(status >= 400) {
			error = true;
		}
		
		this.status = status;
		this.entity = entity;
		
		
	}
	
	public Response() {
		this(200, "OK");
	}
	
	public HashMap<String, Object> buildResponseHashmap(){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		rtn.put("error", this.error);
		rtn.put("data", entity);
		rtn.put("status", status);
		
		return rtn;
	}

}
