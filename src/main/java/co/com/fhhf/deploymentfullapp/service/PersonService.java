package co.com.fhhf.deploymentfullapp.service;

/**
 *
 * @author FHHF
 */
import co.com.fhhf.deploymentfullapp.model.Person;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PersonService {
    
    public List<Person> personList();
    
    //Save a person either inserting or updatiing
    public void savePerson(Person person);
    
    @Query("SELECT person FROM Person person WHERE person.idPerson = :idPerson")
    @Transactional(readOnly = true)
    public Person findById(@Param("id") Integer id);
    
    public List<Person> findBySurname(String surname);
       
    public void deletePerson(Person person);
}