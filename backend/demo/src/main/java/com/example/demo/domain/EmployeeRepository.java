package com.example.demo.domain;

import java.util.HashMap;

public interface EmployeeRepository {
    HashMap<String, Employee> findAll();
    Employee findOne(String sessionID);
    void addOne(String sessionID);
    void deleteOne(String sessionID);
    void removeChat(String sessionID);
}
