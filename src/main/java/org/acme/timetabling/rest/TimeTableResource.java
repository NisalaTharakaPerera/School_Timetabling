package org.acme.timetabling.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import net.bytebuddy.asm.Advice;
import org.acme.timetabling.domain.Lesson;
import org.acme.timetabling.domain.Room;
import org.acme.timetabling.domain.TimeTable;
import org.acme.timetabling.domain.Timeslot;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.api.solver.SolverStatus;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collections;

@Path("/timeTable")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TimeTableResource {

    private static final long SINGLETON_TIME_TABLE_ID = 1L;
    @Inject
    SolverManager<TimeTable, Long> solverManager;

    @Inject
    ScoreManager<TimeTable> scoreManager;
    @GET
    public TimeTable getTimeTable(){




//        Timeslot timeslot = new Timeslot(DayOfWeek.MONDAY, LocalTime.of(8, 30), LocalTime.of(9, 30));
//        Room room = new Room("Room A");
//        Lesson lesson = new Lesson("Math", "A. Turing", "9th grade");
//        return new TimeTable(Collections.singletonList(timeslot), Collections.singletonList(room), Collections.singletonList(lesson));
        SolverStatus solverStatus = solverManager.getSolverStatus(SINGLETON_TIME_TABLE_ID);
        TimeTable timeTable = findById(SINGLETON_TIME_TABLE_ID);
        scoreManager.updateScore(timeTable);
        timeTable.setSolverStatus(solverStatus);
        return timeTable;

    }

    @POST
    @Path("/solve")
    public void solve(){
        solverManager.solveAndListen(SINGLETON_TIME_TABLE_ID,
                this::findById,
                this::save);
    }

    @Transactional
    protected TimeTable findById(Long Id){
        return new TimeTable(
                Timeslot.listAll(),
                Room.listAll(),
                Lesson.listAll());
    }
    @Transactional
    protected void save(TimeTable timeTable){
        for (Lesson lesson: timeTable.getLessonList()){
            Lesson attachedLesson = Lesson.findById(lesson.getId());
            attachedLesson.setTimeslot(lesson.getTimeslot());
            attachedLesson.setRoom(lesson.getRoom());
        }

    }
}
