package co.com.fhhf.deploymentfullapp.service;

import co.com.fhhf.deploymentfullapp.model.Person;
import co.com.fhhf.deploymentfullapp.repo.IPersonRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonServiceImpl implements PersonService{
    
    @Autowired
    private IPersonRepo repo;

    @Override
    public void addPerson(Person person) {
        repo.save(person);
    }

    @Override
    public List<Person> personList() {
        return repo.findAll();
    }
    
}