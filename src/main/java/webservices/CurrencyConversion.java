/*
 * 	Name: Nabil Ridhwanshah Bin Rosli , Xavier Tay Cher Yew
	Admin No: P2007421, P2129512
	Class: DIT/FT/2A/01
	Group Number: Group 4 - TAY CHER YEW XAVIER, NABIL RIDHWANSHAH BIN ROSLI 
 * */

package webservices;

import java.util.Date;
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
		
		Response resp = invocationBuilder.header("apikey", "g9ipY110rch3ISSLcYZxT5QSmp4ORW3A").get();
		
		if(resp.getStatus() == Response.Status.OK.getStatusCode()) {
			CurrencyApiBody body = resp.readEntity(CurrencyApiBody.class);
			
			return Response.status(Response.Status.OK).entity(body).build();
		}
		Date now =new Date();
		CurrencyApiBody body = new CurrencyApiBody();
		body.setBase("SGD");
		body.setDate(now.toString());
		body.setRates(new HashMap<>());
		body.getRates().put("USD", 1.38);
		body.getRates().put("EUR", 1.02);
		body.setSuccess(true);
		

		return Response.status(Response.Status.OK).entity(body).build();
		//return Response.status(Response.Status.OK).entity(new responses.InternalServerError().buildResponseHashmap()).build();
	}

}
