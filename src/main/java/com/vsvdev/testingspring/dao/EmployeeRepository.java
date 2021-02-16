package com.vsvdev.testingspring.dao;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findById(long id);
}
