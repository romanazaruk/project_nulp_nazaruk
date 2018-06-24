package ua.lviv.iot.rest.service;

import ua.lviv.iot.Good;
import ua.lviv.iot.persistence.dao.GoodDao;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Path("/HockeyClubs")
@SessionScoped
public class HockeyclubService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private GoodDao dao;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Good getGood(@PathParam("id") Integer id) {
        return dao.findById(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public  Response createGood(Good good) {
        dao.persist(good);
        return Response.status(200).entity("ua.lviv.iot.Good").build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public  Response deleteGood(@PathParam("id") Integer id) {

        return Response.status(200).entity("ua.lviv.iot.Good").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public  Response updateGood(Good good) {
    dao.update(good);
        return Response.status(200).entity("ua.lviv.iot.Good").build();
    }

    @GET
    @Path("/work")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
    public String isWork(){
        return "YES";
    }


}