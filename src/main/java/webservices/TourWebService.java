package webservices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import dataStructures.Tour;
import dataStructures.Tour.Date;
import utils.DatabaseConnection;
import DAO.TourModel;
import body.InsertNewTourBody;

@Path("/tours")
public class TourWebService {



	// Allows user to get all tours
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTours() {
		DatabaseConnection connection = new DatabaseConnection();
		Tour[] tours = TourModel.getAllTours().query(connection);

		connection.close();

		return Response.status(200).entity(tours).build();
	}

	// Allows user to search for tour by id
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getToursByTourId(@QueryParam("id") String id) {

		if (id == null) {
			HashMap<String, String> errorObject = new HashMap<String, String>();
			errorObject.put("message", "id is missing");
			return Response.status(400).entity(errorObject).build();
		}

		Integer idInInt;

		try {
			idInInt = Integer.parseInt(id);
			// Get tours by Id
			DatabaseConnection connection = new DatabaseConnection();
			Tour[] tours = TourModel.getTourById(idInInt).query(connection);
			connection.close();
			return Response.status(200).entity(tours).build();
		} catch (NumberFormatException nfe) {
			HashMap<String, String> errorObject = new HashMap<String, String>();
			errorObject.put("message", "id is not a number");
			return Response.status(400).entity(errorObject).build();
		}
	}

	// Allows user to register for tour
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public Response insertNewTour(InsertNewTourBody requestBody) {
		DatabaseConnection connection = new DatabaseConnection();
		int affectedRows = TourModel.registerUserForTour(requestBody.getUserID(), requestBody.getTourDateID(),
				requestBody.getPax(), requestBody.getStripe_payment_id()).update(connection);

		connection.close();

		if (affectedRows > 0) {
			return Response.status(Response.Status.OK).entity(new responses.Response(200, affectedRows).buildResponseHashmap()).build();
		}

		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(new responses.InternalServerError("There was an error inserting a new tour").buildResponseHashmap()).build();

	}
	
	@GET
	@Path("/dates")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response insertNewReviewForTour(@QueryParam("id") String id) {

		if (id == null) {
			HashMap<String, String> errorObject = new HashMap<String, String>();
			errorObject.put("message", "id is missing");
			return Response.status(400).entity(errorObject).build();
		}

		Integer idInInt;

		try {
			idInInt = Integer.parseInt(id);
			// Get tours by Id
			DatabaseConnection connection = new DatabaseConnection();
			Tour[] tours = TourModel.getTourById(idInInt).query(connection);
			
			if(tours.length == 0) {
				return Response.status(400).entity(new responses.NotFoundError().buildResponseHashmap()).build();
			}
			
			Tour tour = tours[0];
			
			// Get tour dates
			Date[] tour_dates = TourModel.getTourDates(tour).query(connection);
			
			connection.close();
			
			return Response.status(Response.Status.OK).entity(tour_dates).build();
		} catch (NumberFormatException nfe) {
			HashMap<String, String> errorObject = new HashMap<String, String>();
			errorObject.put("message", "id is not a number");
			return Response.status(400).entity(errorObject).build();
		}
	}

}
