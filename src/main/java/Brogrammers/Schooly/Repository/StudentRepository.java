package Brogrammers.Schooly.Repository;


import Brogrammers.Schooly.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}