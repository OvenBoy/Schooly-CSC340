package Brogrammers.Schooly.Repository;

import Brogrammers.Schooly.Entity.AssignmentStudentview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AssignmentStudentviewRepository extends JpaRepository<AssignmentStudentview, Integer> {

}