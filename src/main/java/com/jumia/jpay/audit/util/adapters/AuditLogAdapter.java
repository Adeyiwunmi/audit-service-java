package com.jumia.jpay.audit.util.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.jpay.audit.domain.AuditLog;
import com.jumia.jpay.audit.integrations.models.AuditLogEvent;
import com.jumia.jpay.audit.util.AuditEventFilter;

import java.io.IOException;
import java.util.UUID;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
public class AuditLogAdapter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static AuditLog convertEventMessageToNewAuditLog(String eventMessage) throws IOException {
        AuditLogEvent event = objectMapper.readValue(eventMessage, AuditLogEvent.class);
       //An invalid event is either null or has invalid audit action id or has invalid audit action type
        if (event == null) {
            return null;
        }
        AuditLogEvent auditLogEvent = AuditEventFilter.filterNewAuditEvent(event);

        AuditLog auditLog = new AuditLog();
        auditLog.setAuditDate(auditLogEvent.getAuditDate());
        auditLog.setAuditDateTime(auditLogEvent.getAuditDateTime());
        auditLog.setActionPerformed(auditLogEvent.getActionPerformed());
        auditLog.setUserName(auditLogEvent.getUserName());
        auditLog.setRemoteAddress(auditLogEvent.getRemoteAddress());
        auditLog.setViaAPI(auditLogEvent.isViaAPI());
        auditLog.setId(UUID.randomUUID().toString().replace("-", ""));
        auditLog.setAuditActionTypeId(auditLogEvent.getAuditActionTypeId());
        auditLog.setAuditActionId(auditLogEvent.getAuditActionId());
        return auditLog;
    }
}
