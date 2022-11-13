package Brogrammers.Schooly.Repository;

import Brogrammers.Schooly.Entity.ToDoStudent;
import Brogrammers.Schooly.Entity.gradeStudentView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoStudentRepository extends JpaRepository<ToDoStudent, String> {


}