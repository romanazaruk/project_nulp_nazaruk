
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Path("/Hockeyclub")
public class HockeyClubServer {

    public static HockeyClub manager = new HockeyClub();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public final HockeyClub getHockeyClub(@PathParam("id") Integer id) {
        return HockeyClub.getHockeyClub().get(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response createTransportation(SeaTransportation transportation) {
        manager.addTransportation(transportation);
        return Response.status(200).entity("Good").build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response deleteTransportation(@PathParam("id") Integer id) {
        manager.getTransportations().remove(id);
        return Response.status(200).entity("Good").build();
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response updateTransportation(@PathParam("id") Integer id,
                                               SeaTransportation transportation) {
        manager.getTransportations().put(transportation.getId(), transportation);
        return Response.status(200).entity("Good").build();
    }

    @GET
    public List<SeaTransportation> getTransportations() {
        return new LinkedList<>(manager.getTransportations().values());
    }
}