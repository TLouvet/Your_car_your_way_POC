package com.example.demo.app;

import com.example.demo.domain.Client;
import com.example.demo.domain.ClientsRepository;
import com.example.demo.domain.EmployeeRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerManager {

    private final ClientsRepository clients;
    private final EmployeeRepository employees;

    CustomerManager(ClientsRepository clients, EmployeeRepository employees) {
        this.clients = clients;
        this.employees = employees;
    }

    public String addOne(String sessionID){
        this.clients.addOne(sessionID);
        return sessionID;
    }

    public Client findOne(String sessionID){
        return this.clients.findOne(sessionID);
    }

    public void disconnect(String sessionID){
        this.clients.deleteOne(sessionID);
    }

    public void endChatWithDisconnectedEmployee(String employeeSessionID){
        this.clients.removeChat(employeeSessionID);
    }

    public String findRelatedEmployeeSessionForCustomer(String customerSessionID){
        Client client = this.clients.findOne(customerSessionID);
        if (client == null){
            return null;
        }

        if (client.getChatsWith() == null) {
            // Pour ce POC, on fait la supposition qu'il y a toujours un employé
            String employeeSessionID = this.employees.findAll().keySet().toArray(new String[0])[0]; // Demeter pas content
            client.setChatsWith(employeeSessionID);
            this.employees.findOne(employeeSessionID).addToChat(customerSessionID);
        }

        return client.getChatsWith();
    }
}
