package Brogrammers.Schooly.Repository;

import Brogrammers.Schooly.Entity.Instructor;
import Brogrammers.Schooly.Entity.StudAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudAssignRepository extends JpaRepository<StudAssign, Integer> {
    @Query("select i from StudAssign i " +
            "where i.courseID = 1")
    List<StudAssign> search();

    @Query("SELECT i FROM StudAssign i " +
            "WHERE i.studID = 300")
    List<StudAssign> searchStud();
}