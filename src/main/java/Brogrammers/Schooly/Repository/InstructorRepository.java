package Brogrammers.Schooly.Repository;

import Brogrammers.Schooly.Entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
}