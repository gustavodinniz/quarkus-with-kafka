package br.com.mining.company.client;

import br.com.mining.company.dto.OpportunityDTO;
import io.quarkus.oidc.token.propagation.reactive.AccessTokenRequestReactiveFilter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@ApplicationScoped
@RegisterRestClient
@Path("/opportunities")
@RegisterProvider(AccessTokenRequestReactiveFilter.class)
public interface ReportRestClient {

    @GET
    @Path("/data")
    List<OpportunityDTO> retrieveOpportunitiesData();
}
