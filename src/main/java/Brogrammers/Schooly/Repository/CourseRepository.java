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

    @Query("select c.name from Course c where c.courseID not in (select c.courseID\n" +
            "from Course c inner join Instructor i on c.courseID = i.courseID)")
    List<String> searchInstructorFreeCourseName();

    @Query("select c.courseID from Course c where c.courseID not in (select c.courseID\n" +
            "from Course c inner join Instructor i on c.courseID = i.courseID)")
    List<Integer> searchInstructorFreeCourseID();

    @Query("select c.name from Course c where c.courseID = :id")
    String searchByID(@Param("id") Integer id);

    @Query("select c.courseID from Course c where c.name = :name")
    Integer searchByName(@Param("name") String name);


}