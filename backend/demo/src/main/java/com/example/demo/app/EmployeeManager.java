package com.example.demo.app;

import com.example.demo.domain.ClientsRepository;
import com.example.demo.domain.Employee;
import com.example.demo.domain.EmployeeRepository;
import org.springframework.stereotype.Component;

@Component
public class EmployeeManager {
    private final ClientsRepository clients;
    private final EmployeeRepository employees;

    EmployeeManager(ClientsRepository clients, EmployeeRepository employees) {
        this.clients = clients;
        this.employees = employees;
    }

    public String addOne(String sessionID){
        this.employees.addOne(sessionID);
        return sessionID;
    }

    public Employee findOne(String sessionID) {
        return this.employees.findOne(sessionID);
    }

    public void disconnect(String sessionID){
        this.employees.deleteOne(sessionID);
    }

    public void endChatWithDisconnectedEmployee(String employeeSessionID){
        this.employees.removeChat(employeeSessionID);
    }
}
