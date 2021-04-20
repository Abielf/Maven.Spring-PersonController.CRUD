package io.zipcoder.crudapp;

import io.zipcoder.crudapp.Person;
import io.zipcoder.crudapp.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/v1/people")
@Controller
public class PersonController {
    PersonService service;

    @Autowired
    public PersonController(PersonService s){this.service=s;}

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person p){
        return new ResponseEntity<>(service.create(p), HttpStatus.CREATED);
    }

    @GetMapping(path="{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id")Long id){
        return new ResponseEntity<>(service.show(id), HttpStatus.OK);
        }

    @GetMapping
    public ResponseEntity<List<Person>> getPersonList(){
        return new ResponseEntity<>(service.showAll(), HttpStatus.OK);}

    @PutMapping(path="{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id")Long id, @RequestBody Person newPerson){
        return new ResponseEntity<>(service.update(id, newPerson), HttpStatus.OK);
    }

    @DeleteMapping(path="{id}")
    public ResponseEntity<Boolean> destroy(@PathVariable("id")Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }

}