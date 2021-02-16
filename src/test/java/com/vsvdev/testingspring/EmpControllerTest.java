package com.vsvdev.testingspring;

import com.vsvdev.testingspring.controller.EmpController;
import com.vsvdev.testingspring.dao.Employee;
import com.vsvdev.testingspring.dao.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class EmpControllerTest {
    @InjectMocks
    private EmpController controller;

    @Mock
    private EmployeeRepository repository;

    @Test
    public void testAddEmployee(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Employee employee = new Employee("Tom", "Jonson", "dsad@");
        employee.setId(1);
        when(repository.save(any(Employee.class))).thenReturn(employee);
        Employee empToRequest = new Employee("Sam", "Wu", "spme@ada");
        ResponseEntity<Object> responseEntity = controller.addEmpl(empToRequest);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    @Test
    public void testFindAll(){
        Employee emp1 = new Employee("Joe", "Dou", "dou@");
        Employee emp2 = new Employee("John", "Smith", "agent@");
        List<Employee> employees = new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
        when(repository.findAll()).thenReturn(employees);

        List<Employee> emp = controller.getEmpl();
        assertThat(emp.size()).isEqualTo(2);
        assertThat(emp.get(0).getFirstName()).isEqualTo(emp1.getFirstName());
        assertThat(emp.get(1).getLastName()).isEqualTo(emp2.getLastName());
    }
}
