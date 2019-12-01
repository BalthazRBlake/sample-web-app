package co.com.fhhf.deploymentfullapp.repo;

/**
 *
 * @author FHHF
 */
import co.com.fhhf.deploymentfullapp.model.Person;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepo extends JpaRepository<Person, Integer>{
    Optional<List<Person>> findPersonBySurname(String surname);
}