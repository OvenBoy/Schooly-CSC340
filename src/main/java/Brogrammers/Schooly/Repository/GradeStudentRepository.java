package Brogrammers.Schooly.Repository;

import Brogrammers.Schooly.Entity.gradeStudentView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GradeStudentRepository extends JpaRepository<gradeStudentView, String> {
    @Query("select a from gradeStudentView a " +
            "WHERE a.studID = 9")
    List<gradeStudentView> search();

}