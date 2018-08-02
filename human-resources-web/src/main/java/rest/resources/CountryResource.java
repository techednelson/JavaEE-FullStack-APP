package rest.resources;

import model.Country;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rest.boundary.CountryBoundary;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/countries")
public class CountryResource {

    private final Logger logger = LogManager.getLogger(CountryResource.class.getName());

    @Inject
    private CountryBoundary boundary;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCountries() {

        logger.info("Starting web service call getAllCountries");
        List<Country> countryList = boundary.getAllCountries();

        return Response.ok(countryList).build();
    }
}
