package com.jumia.jpay.audit;

import com.jumia.jpay.audit.domain.AuditActionType;
import com.jumia.jpay.audit.integrations.models.AuditLogEvent;
import com.jumia.jpay.audit.util.AuditEventFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unittest")
public class AuditLogFilterTests {

    @Mock
    private AuditLogEvent eventWithSensitiveInfo = new AuditLogEvent();

    private AuditLogEvent eventInSensitiveInfo = new AuditLogEvent();

    @Before
    public void initMock() {
        Mockito.when(eventWithSensitiveInfo.getAuditActionType()).
                thenReturn(AuditActionType.fromNameAndSensitiveInformation("Add Credit Card Payment detail", true));

        Mockito.when(eventInSensitiveInfo.getAuditActionType()).
                thenReturn(AuditActionType.fromNameAndSensitiveInformation("Added new Phone number", false));
    }

    @Test
    public void testFilterForEventWithSensitiveInfo(){
        AuditLogEvent auditLogEvent = eventWithSensitiveInfo;
        AuditLogEvent filteredEvent = AuditEventFilter.filterNewAuditEvent(auditLogEvent);
        Assert.assertNotEquals(filteredEvent.getActionPerformed(), auditLogEvent.getActionPerformed());
    }


    @Test
    public void testFilterForEventWithSensitiveInsensitiveInfo(){
        AuditLogEvent auditLogEvent = eventInSensitiveInfo;
        AuditLogEvent filteredEvent = AuditEventFilter.filterNewAuditEvent(auditLogEvent);
        Assert.assertEquals(filteredEvent.getActionPerformed(), auditLogEvent.getActionPerformed());
    }
}
