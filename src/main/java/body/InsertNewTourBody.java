/*
 * 	Name: Nabil Ridhwanshah Bin Rosli
	Admin No: P2007421
	Class: DIT/FT/2A/01
	Group Number: Group 4 - TAY CHER YEW XAVIER, NABIL RIDHWANSHAH BIN ROSLI 
 * */

package body;

public class InsertNewTourBody {

	int userID;
	int tourDateID;
	int pax;
	String stripe_payment_id;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getTourDateID() {
		return tourDateID;
	}

	public void setTourDateID(int tourDateID) {
		this.tourDateID = tourDateID;
	}

	public int getPax() {
		return pax;
	}

	public void setPax(int pax) {
		this.pax = pax;
	}

	public String getStripe_payment_id() {
		return stripe_payment_id;
	}

	public void setStripe_payment_id(String stripe_payment_id) {
		this.stripe_payment_id = stripe_payment_id;
	}

}
