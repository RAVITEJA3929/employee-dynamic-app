package com.dynamic.employee.config;

import com.dynamic.employee.entity.Employee;
import com.dynamic.employee.entity.User;
import com.dynamic.employee.repository.EmployeeRepository;
import com.dynamic.employee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create sample users
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@company.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("employee").isEmpty()) {
            User employee = new User();
            employee.setUsername("employee");
            employee.setEmail("emp@company.com");
            employee.setPassword(passwordEncoder.encode("emp123"));
            employee.setRole("EMPLOYEE");
            userRepository.save(employee);
        }

        // Create sample employees
        if (employeeRepository.count() == 0) {
            Employee[] employees = {
                createEmployee("John Doe", "john.doe@company.com", "Engineering", "Senior Developer", 85000.0, "ACTIVE"),
                createEmployee("Jane Smith", "jane.smith@company.com", "HR", "HR Manager", 75000.0, "ACTIVE"),
                createEmployee("Mike Johnson", "mike@company.com", "Sales", "Sales Executive", 65000.0, "ACTIVE"),
                createEmployee("Sarah Wilson", "sarah@company.com", "Marketing", "Marketing Lead", 70000.0, "ACTIVE"),
                createEmployee("David Brown", "david@company.com", "Engineering", "Junior Developer", 55000.0, "ACTIVE")
            };
            employeeRepository.saveAll(Arrays.asList(employees));
        }
    }

    private Employee createEmployee(String name, String email, String dept, String position, double salary, String status) {
        Employee emp = new Employee();
        emp.setName(name);
        emp.setEmail(email);
        emp.setDepartment(dept);
        emp.setPosition(position);
        emp.setSalary(BigDecimal.valueOf(salary));
        emp.setStatus(status);
        emp.setHireDate(LocalDate.now().minusDays(30));
        emp.setPhone("123-456-7890");
        emp.setSkills("Java, Spring, Docker");
        return emp;
    }
}
