package Brogrammers.Schooly.Repository;

import Brogrammers.Schooly.Entity.Take;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TakeRepository extends JpaRepository<Take, Integer> {
    @Query("select s.id from  Student s")
    List<Integer> searchStudID();

    @Query("select c.courseID from  Course c")
    List<Integer> searchCourseID();

}