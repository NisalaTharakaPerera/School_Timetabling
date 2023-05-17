package org.acme.timetabling.rest;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.timetabling.domain.Room;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class RoomResource {
    @POST
    public Response add(Room room){
        Room.persist(room);
        return Response.accepted().build();
    }

    @DELETE
    @Path("(roomId)")
    public Response delete(@PathParam("roomId") Long roomId){
        Room room = Room.findById(roomId);
        if (room == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        room.delete();
        return Response.status(Response.Status.OK).build();


    }
}
