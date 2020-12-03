package library.security.repository;

import library.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUsernameAndPassword(String username, String password);
    public boolean existsByUsernameAndPassword(String user,String password);
    public User findByUsername(String username);
}