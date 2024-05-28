package art.dborg.vetapp.v1.dao;

import art.dborg.vetapp.v1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
