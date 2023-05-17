package org.acme.timetabling.solver;

import org.acme.timetabling.domain.Lesson;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

import java.time.Duration;

public class TimeTableConstraintProvider implements ConstraintProvider {
    // This will define what are your hard and soft constraints, what you need to optimize and what are your business goals etc
    // And how you want to improve your return on investment, employee retention, service quality etc

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                roomConfilct(constraintFactory),
                teacherConfilct(constraintFactory),
                studentGroupConfilct(constraintFactory),
                teacherTimeEfficiency(constraintFactory)
        };
    }



    private Constraint roomConfilct(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lesson.class)
                .join(Lesson.class, Joiners.equal(Lesson::getTimeslot),
                        Joiners.equal(Lesson::getRoom),
                        Joiners.lessThan(Lesson::getId)
                )
                .penalize("Room conflict", HardSoftScore.ONE_HARD);
    }

    private Constraint teacherConfilct(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lesson.class)
                .join(Lesson.class, Joiners.equal(Lesson::getTimeslot),
                        Joiners.equal(Lesson::getTeacher),
                        Joiners.lessThan(Lesson::getId)
                )
                .penalize("Teacher conflict", HardSoftScore.ONE_HARD);
    }

    private Constraint studentGroupConfilct(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lesson.class)
                .join(Lesson.class, Joiners.equal(Lesson::getTimeslot),
                        Joiners.equal(Lesson::getStudentGroup),
                        Joiners.lessThan(Lesson::getId)
                )
                .penalize("Student group conflict", HardSoftScore.ONE_HARD);
    }


    private Constraint teacherTimeEfficiency(ConstraintFactory constraintFactory) {
        return constraintFactory.from(Lesson.class)
                .join(Lesson.class,
                        Joiners.equal(Lesson::getTeacher),
                        Joiners.equal((lesson) -> lesson.getTimeslot().getDayOfWeek()))
                .filter((lesson1, lesson2) -> {
                        Duration between = Duration.between(lesson1.getTimeslot().getEndTime(),
                            lesson2.getTimeslot().getStartTime());
                        return !between.isNegative() && between.compareTo(Duration.ofMinutes(30)) <= 0;
    })
                .reward("Teacher time efficiency", HardSoftScore.ONE_SOFT);

    }
}
