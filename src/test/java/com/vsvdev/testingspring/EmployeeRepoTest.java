package com.vsvdev.testingspring;

import com.vsvdev.testingspring.dao.Employee;
import com.vsvdev.testingspring.dao.EmployeeRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
//@ExtendWith(SpringExtension.class)
public class EmployeeRepoTest {

    @Autowired
    private EmployeeRepository repository;


    @Test
    @Order(1)
    public void findAll(){
        assertTrue(!repository.findAll().iterator().hasNext());
       repository.save(new Employee("Tom", "Jonson2", "dsad@"));
        repository.save(new Employee("Toms", "Jonson3", "dsad@21"));
        repository.save(new Employee("Tomsye", "Jonson42", "dsad@3"));
        assertTrue(StreamSupport.stream(repository.findAll().spliterator(), false).count()==3);
        assertTrue(repository.findById(1).getFirstName().equals("Tom"));

    }
}
