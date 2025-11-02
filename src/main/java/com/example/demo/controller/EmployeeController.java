package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDTO dto) {
        logger.info("Creating employee: {}", dto.getName());
        Employee emp = new Employee(dto.getName(), dto.getSalary());
        Employee saved = service.create(emp);
        return ResponseEntity.created(URI.create("/api/employees/" + saved.getId())).body(saved);
    }

    @GetMapping
    public List<Employee> list() {
        logger.info("Fetching all employees");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id) {
        logger.info("Fetching employee id={}", id);
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @Valid @RequestBody EmployeeDTO dto) {
        logger.info("Updating employee id={}", id);
        Employee emp = new Employee(dto.getName(), dto.getSalary());
        return service.update(id, emp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Deleting employee id={}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
