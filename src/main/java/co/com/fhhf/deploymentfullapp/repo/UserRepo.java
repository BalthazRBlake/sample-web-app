package co.com.fhhf.deploymentfullapp.repo;

import java.util.Optional;

import co.com.fhhf.deploymentfullapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author FHHF
 */
public interface UserRepo extends JpaRepository<User, Integer>{
    
    Optional<User> findByUserName(String userName);
}
