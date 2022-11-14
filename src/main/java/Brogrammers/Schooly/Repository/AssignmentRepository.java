package Brogrammers.Schooly.Repository;

import Brogrammers.Schooly.Entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, String> {
    @Query("select i from Assignment i " +
            "where i.courseID = 1")
    List<Assignment> search();

    @Query("select a " +
            "FROM Assignment a JOIN Course c " +
            "ON a.courseID = c.courseID")
    List<Assignment> nameSearch();

    @Query("select new Assignment (a.name, a.dueDate, a.courseID) " +
            "FROM Assignment a")
    List<Assignment> testSearch();

}