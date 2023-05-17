package org.acme.timetabling.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

//@PlanningEntity
@Entity
public class Lesson extends PanacheEntityBase {
    public Lesson(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subject;
    private String teacher;
    private String studentGroup;

    // Initializes/Changes during planning
    @PlanningVariable(valueRangeProviderRefs = "timeslotRange")
    @ManyToOne
    // Every lesson is assigned exactly one timeslot
    // One timeslot has multiple lessons
    private Timeslot timeslot;

    @PlanningVariable(valueRangeProviderRefs = "roomRange")
    @ManyToOne
    private Room room;

    public Lesson(Long id) {
    }

    public Lesson(String subject, String teacher, String studentGroup) {
        this.subject = subject;
        this.teacher = teacher;
        this.studentGroup = studentGroup;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() { return subject; }

    public String getTeacher() {
        return teacher;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public Timeslot getTimeslot() {
        return timeslot;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return subject + "{" + id + "}";
    }
}
