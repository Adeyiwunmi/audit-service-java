package com.jumia.jpay.audit.unit_tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.jpay.audit.domain.AuditLog;
import com.jumia.jpay.audit.integrations.models.AuditLogEvent;
import com.jumia.jpay.audit.util.AuditLogAdapter;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
public class AuditLogAdapterTests {

    private static final Logger logger = LoggerFactory.getLogger(AuditLogAdapterTests.class);

    @Test
    public void testIfItReturnsValidAuditForValidEventMessage() throws IOException {
        String validAuditEventString = getValidAuditEventString();
        AuditLog auditLog = AuditLogAdapter.convertEventMessageToNewAuditLog(validAuditEventString);
        Assert.assertNotNull(auditLog);
    }

    @Test(expected = IOException.class)
    public void testIfItThrowsIOExceptionForInValidEventMessage() throws IOException {
        String validAuditEventString = "Hello,Audit Service!!";
        AuditLog auditLog = AuditLogAdapter.convertEventMessageToNewAuditLog(validAuditEventString);
        Assert.assertNull(auditLog);
    }

    private String getValidAuditEventString() {
        AuditLogEvent auditLogEvent = new AuditLogEvent();
        auditLogEvent.setActionPerformed("Sample Event action performed");
        auditLogEvent.setAuditActionName("Sample event action name");
        auditLogEvent.setAuditDate(123444444);
        auditLogEvent.setAuditDateTime(auditLogEvent.getAuditDate() + 22);
        auditLogEvent.setPerformedBy("Martel Umar");
        auditLogEvent.setRemoteAddress("127.123.123.12");
        auditLogEvent.setViaAPI(true);
        auditLogEvent.setOwner("User Service");
        try {
            return new ObjectMapper().writeValueAsString(auditLogEvent);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
