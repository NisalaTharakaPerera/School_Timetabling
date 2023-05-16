package org.acme.timetabling.bootstrap;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import org.acme.timetabling.domain.Lesson;
import org.acme.timetabling.domain.Room;
import org.acme.timetabling.domain.Timeslot;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped // This means there is only one instance of this
public class DemoDataGenerator {

    @Transactional
    public void generateDemoData(@Observes StartupEvent startupEvent){
        List<Timeslot> timeslotList = new ArrayList<>();
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(8,30), LocalTime.of(9,30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(9,30), LocalTime.of(10,30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(10,30), LocalTime.of(11,30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(13,30), LocalTime.of(14,30)));
        timeslotList.add(new Timeslot(DayOfWeek.MONDAY, LocalTime.of(14,30), LocalTime.of(15,30)));

        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(8,30), LocalTime.of(9,30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(9,30), LocalTime.of(10,30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(10,30), LocalTime.of(11,30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(13,30), LocalTime.of(14,30)));
        timeslotList.add(new Timeslot(DayOfWeek.TUESDAY, LocalTime.of(14,30), LocalTime.of(15,30)));
        Timeslot.persist(timeslotList);

        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("Room A"));
        roomList.add(new Room("Room B"));
        roomList.add(new Room("Room C"));
        Room.persist(roomList);

        List<Lesson> lessonList = new ArrayList<>();
        lessonList.add(new Lesson("Math", "A. Turing", "9th grade"));
        lessonList.add(new Lesson("Math", "A. Turing", "9th grade"));
        lessonList.add(new Lesson("Physics", "M. Curie", "9th grade"));
        lessonList.add(new Lesson("Chemistry", "M. Curie", "9th grade"));
        lessonList.add(new Lesson("Biology", "C. Darvin", "9th grade"));
        lessonList.add(new Lesson("History", "I. Jones", "9th grade"));
        lessonList.add(new Lesson("English", "I. Jones", "9th grade"));
        lessonList.add(new Lesson("English", "I. Jones", "9th grade"));
        lessonList.add(new Lesson("Spanish", "P. Cruz", "9th grade"));
        lessonList.add(new Lesson("Spanish", "P. Cruz", "9th grade"));

        lessonList.add(new Lesson("Math", "A. Turing", "10th grade"));
        lessonList.add(new Lesson("Math", "A. Turing", "10th grade"));
        lessonList.add(new Lesson("Math", "A. Turing", "10th grade"));
        lessonList.add(new Lesson("Physics", "M. Curie", "10th grade"));
        lessonList.add(new Lesson("Chemistry", "M. Curie", "10th grade"));
        lessonList.add(new Lesson("French", "M. Curie", "10th grade"));
        lessonList.add(new Lesson("Geography", "C. Darvin", "10th grade"));
        lessonList.add(new Lesson("History", "I. Jones", "10th grade"));
        lessonList.add(new Lesson("English", "P. Cruz", "10th grade"));
        lessonList.add(new Lesson("Spanish", "P. Cruz", "10th grade"));
        Lesson.persist(lessonList);
    }
}
