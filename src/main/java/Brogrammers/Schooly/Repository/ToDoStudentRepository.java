package Brogrammers.Schooly.Repository;

import Brogrammers.Schooly.Entity.ToDoStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoStudentRepository extends JpaRepository<ToDoStudent, String> {


}