package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.net.URI;
import java.util.List;

@Tag(name = "Employee API", description = "CRUD Operations for Employee")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new Employee")
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDTO dto) {
        logger.info("Creating employee: {}", dto.getName());
        Employee emp = new Employee(dto.getName(), dto.getSalary());
        Employee saved = service.create(emp);
        return ResponseEntity.created(URI.create("/api/employees/" + saved.getId())).body(saved);
    }

    @Operation(summary = "Get all employees")
    @GetMapping
    public List<Employee> list() {
        logger.info("Fetching all employees");
        return service.getAll();
    }

    @Operation(summary = "Get an employee by ID")
    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id) {
        logger.info("Fetching employee id={}", id);
        return service.getById(id);
    }

    @Operation(summary = "Update employee by ID")
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @Valid @RequestBody EmployeeDTO dto) {
        logger.info("Updating employee id={}", id);
        Employee emp = new Employee(dto.getName(), dto.getSalary());
        return service.update(id, emp);
    }

    @Operation(summary = "Delete employee by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Deleting employee id={}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
