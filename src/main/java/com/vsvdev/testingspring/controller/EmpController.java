package com.vsvdev.testingspring.controller;

import com.vsvdev.testingspring.dao.Employee;
import com.vsvdev.testingspring.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmpController {

    private final EmployeeRepository repository;

@Autowired
    public EmpController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/empl")
    public List<Employee> getEmpl(){
    List<Employee> employees = new ArrayList<>();
    repository.findAll().forEach(employee -> employees.add(employee));
    return employees;
    }

    @PostMapping("empl/post")
    public ResponseEntity addEmpl(@RequestBody Employee employee){
    repository.save(employee);
    return new ResponseEntity(HttpStatus.OK);
    }
}
