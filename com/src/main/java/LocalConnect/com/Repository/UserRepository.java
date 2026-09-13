package LocalConnect.com.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import LocalConnect.com.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
