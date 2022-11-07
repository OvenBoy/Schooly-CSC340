package Brogrammers.Schooly.Repository;

import Brogrammers.Schooly.Entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    @Query("select i from Instructor i " +
            "where lower(i.fName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(i.lName) like lower(concat('%', :searchTerm, '%'))")
    List<Instructor> search(@Param("searchTerm") String searchTerm);
}