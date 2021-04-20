package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonService {
    private PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public Iterable<Person> index() {
        return repository.findAll();
    }

    public Person show(Long id) { return repository.findOne(id); }

    public List<Person> showAll(){
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
        }

    public Person create(Person baker) {
        return repository.save(baker);
    }

    public Person update(Long id, Person newBakerData) {
        Person originalBaker = repository.findOne(id);
        originalBaker.setFirstName(newBakerData.getFirstName());
        originalBaker.setLastName(newBakerData.getLastName());
        return repository.save(originalBaker);
    }

    public Boolean delete(Long id) {
        repository.delete(id);
        return true;
    }
    
}
