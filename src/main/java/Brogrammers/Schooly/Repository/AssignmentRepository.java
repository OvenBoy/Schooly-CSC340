package Brogrammers.Schooly.Repository;

import Brogrammers.Schooly.Entity.Assignment;
import Brogrammers.Schooly.Entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, String> {
    @Query("select i from Assignment i " +
            "where i.courseID = 1")
    List<Assignment> search();


}