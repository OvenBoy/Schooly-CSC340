package data.repository;

import data.entity.Stu_Assignments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepo extends JpaRepository<Stu_Assignments, String> {

}
