package Brogrammers.Schooly.Repository;


import Brogrammers.Schooly.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("select s from Student s " +
            "where lower(s.fName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(s.lName) like lower(concat('%', :searchTerm, '%'))")
    List<Student> search(@Param("searchTerm") String searchTerm);

    @Query("select s from Student s inner join Take t on t.studID = s.id" +
            " where t.courseID = 1")
    List<Student>searchStudentByCourseID();
}