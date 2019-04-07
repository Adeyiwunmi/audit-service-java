package com.jumia.jpay.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.jpay.audit.domain.AuditLog;
import com.jumia.jpay.audit.integrations.models.AuditLogEvent;
import com.jumia.jpay.audit.util.adapters.AuditLogAdapter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.UUID;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unittest")
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
        auditLogEvent.setActionPerformed("Successful Login Attempt");
        auditLogEvent.setAuditActionId(UUID.randomUUID().toString());
        auditLogEvent.setAuditActionTypeId(UUID.randomUUID().toString());
        auditLogEvent.setAuditDate(123444444);
        auditLogEvent.setAuditDateTime(auditLogEvent.getAuditDate() + 22);
        auditLogEvent.setUserName("Martel Umar");
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
