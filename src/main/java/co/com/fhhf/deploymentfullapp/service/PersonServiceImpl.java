package co.com.fhhf.deploymentfullapp.service;

/**
 *
 * @author FHHF
 */
import co.com.fhhf.deploymentfullapp.model.Person;
import co.com.fhhf.deploymentfullapp.repo.IPersonRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private IPersonRepo repo;

    @Override
    public void savePerson(Person person) {
        repo.save(person);
    }

    @Override
    public List<Person> personList() {
        return repo.findAll();
    }

    @Override
    public Person findById(Integer id) {
        try{
            return repo.findById(id).get();
        }catch(Throwable t){
            //t.printStackTrace();
            Person p = new Person();
            p.setSurname("Not Found");
            return p;
        }
    }

    @Override
    public void deletePerson(Person person) {
        repo.delete(person);
    }

    @Override
    public List<Person> findPersonBySurname(String surname) {
        Optional<List<Person>> people = repo.findPersonBySurname(surname);
        return people.get();
    }

}
