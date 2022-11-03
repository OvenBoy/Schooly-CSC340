package Brogrammers.Schooly.Repository;

import Brogrammers.Schooly.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}