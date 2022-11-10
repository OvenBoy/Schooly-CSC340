package Brogrammers.Schooly.Repository;

import Brogrammers.Schooly.Entity.Instructor;
import Brogrammers.Schooly.Entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {
    @Query("select i from StudentCourse i " +
            "where lower(i.fName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(i.lName) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(i.name) like lower(concat('%', :searchTerm, '%') ) " +
            "or lower(i.studID) like lower(concat('%', :searchTerm, '%') ) " +
            "or lower(i.courseID) like lower(concat('%', :searchTerm, '%') )")
    List<StudentCourse> search(@Param("searchTerm") String searchTerm);
}