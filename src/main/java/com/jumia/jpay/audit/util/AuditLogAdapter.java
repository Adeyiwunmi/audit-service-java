package com.jumia.jpay.audit.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.jpay.audit.domain.AuditLog;
import com.jumia.jpay.audit.integrations.models.AuditLogEvent;

import java.io.IOException;
import java.util.UUID;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
public class AuditLogAdapter {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static AuditLog convertEventMessageToNewAuditLog(String eventMessage) throws IOException {
        AuditLogEvent auditLogEvent = objectMapper.readValue(eventMessage, AuditLogEvent.class);
        if (auditLogEvent == null) {
            return null;
        }
        AuditLog auditLog = new AuditLog();
        auditLog.setAuditDate(auditLogEvent.getAuditDate());
        auditLog.setAuditDateTime(auditLogEvent.getAuditDateTime());
        auditLog.setActionPerformed(auditLogEvent.getActionPerformed());
        auditLog.setPerformedBy(auditLogEvent.getPerformedBy());
        auditLog.setOwner(auditLogEvent.getOwner());
        auditLog.setRemoteAddress(auditLogEvent.getRemoteAddress());
        auditLog.setViaAPI(auditLogEvent.isViaAPI());
        auditLog.setId(UUID.randomUUID().toString().replace("-", ""));
        auditLog.setAuditActionName(auditLogEvent.getAuditActionName());
        return auditLog;
    }
}
