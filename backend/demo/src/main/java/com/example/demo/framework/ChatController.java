package com.example.demo.framework;


import com.example.demo.app.CustomerManager;
import com.example.demo.app.EmployeeManager;
import com.example.demo.domain.*;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate msg;
    private final CustomerManager customerManager;
    private final EmployeeManager employeeManager;

    public ChatController(SimpMessagingTemplate sm, CustomerManager customerManager, EmployeeManager employeeManager) {
        this.msg = sm;
        this.customerManager = customerManager;
        this.employeeManager = employeeManager;
    }

    @MessageMapping("join_session")
    @SendTo("/topic/login")
    public String onCustomerJoinSession(@Header("simpSessionId") String sessionId, LoginBody loginBody){
        if (loginBody.getType() == UserType.CUSTOMER) {
            return this.customerManager.addOne(sessionId);
        }

        return this.employeeManager.addOne(sessionId);
    }

    @MessageMapping("/customer_message/{sessionId}")
    public void onCustomerMessage(@DestinationVariable String sessionId, ChatMessage message){
        String employeeSessionID = this.customerManager.findRelatedEmployeeSessionForCustomer(sessionId);
        if (employeeSessionID == null){
            return;
        }

        message.setConversationID(sessionId);
        System.out.println("CONV: "+ employeeSessionID);
        this.msg.convertAndSend("/queue/messages/" + employeeSessionID, message);
    }

    @MessageMapping("/employee_message/{sessionID}")
    public void onEmployeeMessage(@DestinationVariable String sessionID, ChatMessage message){
        if (this.customerManager.findOne(sessionID) == null) {
            return;
        }
        System.out.println("OUAI");
        this.msg.convertAndSend("/queue/messages/" + sessionID, message);
    }
}