package Brogrammers.Schooly.Repository;

import Brogrammers.Schooly.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("select c from Course c " +
            "where lower(c.name) like lower(concat('%', :searchTerm, '%')) ")
    List<Course> search(@Param("searchTerm") String searchTerm);

}