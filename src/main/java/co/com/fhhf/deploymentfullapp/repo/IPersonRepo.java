package co.com.fhhf.deploymentfullapp.repo;

/**
 *
 * @author FHHF
 */
import co.com.fhhf.deploymentfullapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepo extends JpaRepository<Person, Integer>{
}