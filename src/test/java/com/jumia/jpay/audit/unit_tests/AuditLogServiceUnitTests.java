package com.jumia.jpay.audit.unit_tests;

import com.jumia.jpay.audit.service.contract.AuditLogService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author adeyi.adebolu
 * created on 06/04/2019
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("unittest")
public class AuditLogServiceUnitTests {

    @Autowired
    @Qualifier("TestAuditLogServiceImpl")
    public AuditLogService auditLogService;



    @Test()
    public void testTestAuditLogServiceReturnsProperResponseForValidId() {
        Mono<ResponseEntity> responseEntityMono = auditLogService.getByTransactionId("001");
        Assert.assertNotNull(responseEntityMono);
        Assert.assertNotNull(responseEntityMono.block());
        Assert.assertEquals(200, Objects.requireNonNull(responseEntityMono.block()).getStatusCodeValue());
    }

    @Test
    public void testTestAuditServiceReturnsNoRecordResponseForInvalidId() {
        Mono<ResponseEntity> responseEntityMono = auditLogService.getByTransactionId("1123");
        Assert.assertNotNull(responseEntityMono);
        Assert.assertNotNull(responseEntityMono.block());
        Assert.assertEquals(404, Objects.requireNonNull(responseEntityMono.block()).getStatusCodeValue());
    }
}
