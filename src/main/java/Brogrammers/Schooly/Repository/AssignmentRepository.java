package Brogrammers.Schooly.Repository;

import Brogrammers.Schooly.Entity.Assignment;
import Brogrammers.Schooly.Entity.AssignmentId;
import Brogrammers.Schooly.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, AssignmentId> {
    @Query("SELECT a.courseID, a.dueDate FROM Assignment a")
    List<Assignment> search();

    //@Query("SELECT new Users(e.id, e.email, e.firstname, e.surname) FROM Users e")
    //List<Users> findByQuery();
}