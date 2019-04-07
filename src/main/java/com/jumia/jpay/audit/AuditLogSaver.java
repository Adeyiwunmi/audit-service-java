package com.jumia.jpay.audit;

import com.jumia.jpay.audit.domain.AuditLog;
import com.jumia.jpay.audit.repositories.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author adeyi.adebolu
 * created on 06/04/2019
 */
@Component
public class AuditLogSaver {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public void indexSampleAudit() {
        AuditLog log = new AuditLog();
        log.setId("123");
        log.setActionPerformed("New Action");
        auditLogRepository.save(log);
        log = auditLogRepository.findById("123").orElse(null);
        Logger.getAnonymousLogger().log(Level.SEVERE," log {}", log);
    }


}
