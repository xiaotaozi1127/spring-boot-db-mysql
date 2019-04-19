package springboot.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.example.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
