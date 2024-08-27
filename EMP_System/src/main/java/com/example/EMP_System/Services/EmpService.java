package com.example.EMP_System.Services;

import com.example.EMP_System.Model.Employee;
import com.example.EMP_System.Repository.EmpRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class EmpService {

    @Autowired
    EmpRepo repo;


    public List<Employee> getEmp()
    {
        return repo.findAll();
    }

    public void addEmp(Employee employee) {
        repo.save(employee);
    }

    public void update(@ModelAttribute Employee employee) {

        if (employee.getEmp_id() != 0 && repo.existsById(employee.getEmp_id())) {
            repo.save(employee); // Update if ID exists
        } else {
            // Handle the case where employee ID is invalid or not found
            throw new EntityNotFoundException("Employee not found with ID: " + employee.getEmp_id());
        }
    }

    public Employee getEmpById(int id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void deleteEmployee(int id) {
        repo.deleteById(id);
    }

    public List<Employee> searchEmployees(String query) {
        return repo.findByNameContainingIgnoreCaseOrDepartmentContainingIgnoreCase(query, query);
    }
}
