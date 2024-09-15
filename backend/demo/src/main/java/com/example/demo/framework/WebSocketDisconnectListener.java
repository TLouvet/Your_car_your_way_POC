package com.example.demo.framework;

 import com.example.demo.app.CustomerManager;
 import com.example.demo.app.EmployeeManager;
 import com.example.demo.domain.Employee;
 import org.springframework.context.ApplicationListener;
 import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
 import org.springframework.stereotype.Component;
 import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketDisconnectListener implements ApplicationListener<SessionDisconnectEvent> {

    private CustomerManager clientManager;
    private EmployeeManager employeeManager;

    WebSocketDisconnectListener(CustomerManager customerManager, EmployeeManager employeeManager){
        this.clientManager = customerManager;
        this.employeeManager = employeeManager;
    }

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        handleDisconnection(sessionId);
    }

    private void handleDisconnection(String sessionId) {
        Employee employee = this.employeeManager.findOne(sessionId);

        if (employee == null){
            this.clientManager.disconnect(sessionId);
        } else {
            this.employeeManager.disconnect(sessionId);
            this.clientManager.endChatWithDisconnectedEmployee(sessionId);
        }
    }
}
