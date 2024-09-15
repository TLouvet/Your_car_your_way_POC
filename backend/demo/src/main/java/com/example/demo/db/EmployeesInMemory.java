package com.example.demo.db;

import com.example.demo.domain.Client;
import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class EmployeesInMemory implements EmployeeRepository {
    private final HashMap<String, Employee> employees = new HashMap<String, Employee>();

    public HashMap<String, Employee> findAll(){
        return this.employees;
    }

    public Employee findOne(String sessionID){
        return this.employees.get(sessionID);
    }

    public void addOne(String sessionID){
        this.employees.put(sessionID, new Employee());
    }

    public void deleteOne(String sessionID) {
        this.employees.remove(sessionID);
    }

    public void removeChat(String sessionID){
        this.employees.forEach(($, employee) -> {
            if (employee.chatsWith(sessionID)){
                employee.removeFromChat(sessionID);
            }
        });
    }
}
