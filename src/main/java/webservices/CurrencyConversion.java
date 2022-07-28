package webservices;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import body.CurrencyApiBody;

@Path("/currency")
public class CurrencyConversion {
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCurrencies() {
		Client client = ClientBuilder.newClient();
		
		String restUrl = "https://api.apilayer.com/exchangerates_data/latest?symbols=USD%2CEUR&base=SGD";
		
		WebTarget target = client.target(restUrl);
		
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		
		HashMap<String, String> h = new HashMap<String, String>();
		
		h.put("apikey", "m4nEJVld876oMcySYvrKV5MMLfZUNhW8");
		
		Response resp = invocationBuilder.header("apikey", "m4nEJVld876oMcySYvrKV5MMLfZUNhW8").get();
		
		if(resp.getStatus() == Response.Status.OK.getStatusCode()) {
			CurrencyApiBody body = resp.readEntity(CurrencyApiBody.class);
			
			return Response.status(Response.Status.OK).entity(body).build();
		}
		
		return Response.status(Response.Status.OK).entity(new responses.InternalServerError().buildResponseHashmap()).build();
	}

}
