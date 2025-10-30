package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.entity.Department;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.DepartmentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee, @RequestParam Long departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        employee.setDepartment(department);
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/by-department")
    public List<Employee> getEmployeesByDepartment(@RequestParam String departmentName) {
        return employeeService.getEmployeesByDepartmentName(departmentName);
    }
}
