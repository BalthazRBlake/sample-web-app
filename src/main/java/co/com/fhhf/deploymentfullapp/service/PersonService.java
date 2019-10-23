package co.com.fhhf.deploymentfullapp.service;

import co.com.fhhf.deploymentfullapp.model.Person;
import java.util.List;

public interface PersonService {
    public List<Person> personList();
    public void addPerson(Person person);
}