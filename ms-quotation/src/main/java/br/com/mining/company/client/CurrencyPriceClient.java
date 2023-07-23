package br.com.mining.company.client;

import br.com.mining.company.dto.response.CurrencyPriceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@Path("/last")
@RegisterRestClient
public interface CurrencyPriceClient {

    @GET
    @Path("/{pair}")
    CurrencyPriceResponse getPriceByPair(@PathParam("pair") String pair);

}
