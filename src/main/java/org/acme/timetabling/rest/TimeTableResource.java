package org.acme.timetabling.rest;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import net.bytebuddy.asm.Advice;
import org.acme.timetabling.domain.Room;
import org.acme.timetabling.domain.TimeTable;
import org.acme.timetabling.domain.Timeslot;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Path("/timeTable")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TimeTableResource {
    @GET
    public TimeTable getTimeTable(){
        Timeslot timeslot = new Timeslot(DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 30));
        Room room;
        return new TimeTable();
    }
}
