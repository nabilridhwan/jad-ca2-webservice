package webservices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import dataStructures.Tour;
import utils.DatabaseConnection;
import DAO.TourModel;

@Path("/tours")
public class TourWebService {
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTours() {
		DatabaseConnection connection = new DatabaseConnection();
		Tour[] tours = TourModel.getAllTours().query(connection);
		
		connection.close();
		
		return Response.status(200).entity(tours).build();
	}
	
	// WIP!
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getToursByTourId(@QueryParam("id") String id) {
		
		
		if(id == null) {
			HashMap<String, String> errorObject = new HashMap<String, String>();
			errorObject.put("message", "id is missing");
			return Response.status(400).entity(errorObject).build();
		}
		
		Integer idInInt;
		
		try {
			idInInt = Integer.parseInt(id);
			// Get tours by Id
			DatabaseConnection connection = new DatabaseConnection();
			Tour[] tours =  TourModel.getTourById(idInInt).query(connection);
			connection.close();
			return Response.status(200).entity(tours).build();
		}catch(NumberFormatException nfe) {
			HashMap<String, String> errorObject = new HashMap<String, String>();
			errorObject.put("message", "id is not a number");
			return Response.status(400).entity(errorObject).build();
		}
	}

}
