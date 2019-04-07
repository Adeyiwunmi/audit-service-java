package com.jumia.jpay.audit.integration_tests;

import com.jumia.jpay.audit.domain.AuditLog;
import com.jumia.jpay.audit.reference_data.AuditActionReferenceData;
import com.jumia.jpay.audit.reference_data.AuditActionTypeReferenceData;
import com.jumia.jpay.audit.repositories.AuditLogRepository;
import com.jumia.jpay.audit.service.contract.AuditLogService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.UUID;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unittest")
public class AuditLogServiceIntegrationTests {

    @Autowired
    @Qualifier("AuditLogServiceImpl")
    private AuditLogService auditLogService;
    @Autowired
    private AuditLogRepository auditLogRepository;

    private static final String SAMPLE_VALID_AUDIT_ID = "123";
    private static final String SAMPLE_INVALID_AUDIT_ID = "123900";

    @Before
    public void initTest() {
        saveSampleAudit();
        deleteInvalidAuditIfExists();
    }

    @Test
    public void testForFindingProperAudit() {
        Mono<ResponseEntity> responseEntityMono = auditLogService.getByTransactionId(SAMPLE_VALID_AUDIT_ID);
        Assert.assertNotNull(responseEntityMono);
        Assert.assertNotNull(responseEntityMono.block());
        Assert.assertEquals(200, Objects.requireNonNull(responseEntityMono.block()).getStatusCodeValue());
    }

    @Test
    public void testTestAuditServiceReturnsNoRecordResponseForInvalidId() {
        Mono<ResponseEntity> responseEntityMono = auditLogService.getByTransactionId(SAMPLE_INVALID_AUDIT_ID);
        Assert.assertNotNull(responseEntityMono);
        Assert.assertNotNull(responseEntityMono.block());
        Assert.assertEquals(404, Objects.requireNonNull(responseEntityMono.block()).getStatusCodeValue());
    }

    @After
    public void deleteSampleAudit() {
        auditLogRepository.findById(SAMPLE_VALID_AUDIT_ID).ifPresent(auditLog -> auditLogRepository.delete(auditLog));
    }

    public void saveSampleAudit() {
        AuditLog auditLog = new AuditLog();
        auditLog.setId("1234");
        auditLog.setActionPerformed("Created New Merchant, Category=> Internet Service Providers, Name=> IPNX Nigeria");
        auditLog.setAuditActionId(UUID.randomUUID().toString());
        auditLog.setAuditActionTypeId(UUID.randomUUID().toString());
        auditLog.setViaAPI(false);
        auditLog.setAuditDateTime(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        auditLogRepository.save(auditLog);
    }

    public void deleteInvalidAuditIfExists() {
        auditLogRepository.findById(SAMPLE_INVALID_AUDIT_ID).ifPresent(auditLog -> auditLogRepository.delete(auditLog));
    }
}
