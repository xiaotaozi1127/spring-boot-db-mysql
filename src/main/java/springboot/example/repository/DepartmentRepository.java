package springboot.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.example.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
