package com.jumia.jpay.audit.integrations;

import com.jumia.jpay.audit.domain.AuditLog;
import com.jumia.jpay.audit.repositories.AuditLogRepository;
import com.jumia.jpay.audit.util.adapters.AuditLogAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NewAuditProcessor {

    private AuditLogRepository auditLogRepository;

    @Autowired
    public void setAuditLogRepository(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    private static Logger logger = LoggerFactory.getLogger(NewAuditProcessor.class);

    public void processAuditEvent(String event) {
        logger.info("received response: " + event);
        try {
            AuditLog auditLog = AuditLogAdapter.convertEventMessageToNewAuditLog(event);
            if (auditLog != null) {
                auditLogRepository.save(auditLog);
                logger.info("Persisted Audit : " + auditLog.getId());
            } else {
                logger.error("Invalid Audit event => {}", event);
            }
        } catch (IOException e) {
            logger.error("IO error occurred while reading audit event", e);
        } catch (Exception ex) {
            logger.error("An error occurred while persisting audit event", ex);
        }
    }
}