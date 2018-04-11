
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Path("/HockeyClubs")
public class HockeyclubService {

    public static Map<Integer,Good> goodMap = new HashMap<>();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public final Good getGood(@PathParam("id") Integer id) {
        return goodMap.get(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response createGood(Good good) {
        goodMap.put(good.getId(), good);
        return Response.status(200).entity("Good").build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response deleteGood(@PathParam("id") Integer id) {
        goodMap.remove(id);
        return Response.status(200).entity("Good").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public final Response updateGood(Good good) {
        goodMap.put(good.getId(), good);
        return Response.status(200).entity("Good").build();
    }



}