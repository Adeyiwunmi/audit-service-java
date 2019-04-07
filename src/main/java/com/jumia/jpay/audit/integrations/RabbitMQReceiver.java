package com.jumia.jpay.audit.integrations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {
    private NewAuditProcessor newAuditProcessor;

    @Autowired
    public void setNewAuditProcessor(NewAuditProcessor newAuditProcessor) {
        this.newAuditProcessor = newAuditProcessor;
    }

    public void receiveMessage(String message) {
        newAuditProcessor.processAuditEvent(message);
    }
}