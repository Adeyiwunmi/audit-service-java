package com.jumia.jpay.audit.service;

import com.jumia.jpay.audit.domain.AuditLog;
import com.jumia.jpay.audit.repositories.AuditLogRepository;
import com.jumia.jpay.audit.service.contract.AuditLogService;
import com.jumia.jpay.audit.util.ResponseUtil;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author adeyi.adebolu
 * created on 06/04/2019
 */
@ActiveProfiles("unittest")
@Service("TestAuditLogServiceImpl")
public class TestAuditLogServiceImpl implements AuditLogService {

    @MockBean
    private AuditLogRepository auditLogRepository;


    @PostConstruct
    public void initRepositoryBehavior() {
        Mockito.when(auditLogRepository.findById("001")).thenReturn(Optional.of(new AuditLog()));
    }

    @Override
    public Mono<ResponseEntity> getByTransactionId(String id) {
        AuditLog auditLog = auditLogRepository.findById(id).orElse(null);
        if (auditLog != null) {
            return ResponseUtil.OkResponse(auditLog.convertToDto());
        } else {
            return ResponseUtil.NoRecordResponse();
        }
    }

    @Override
    public Mono<ResponseEntity> getAuditTrails(String keyword,
                                               String fromDate,
                                               String endDate,
                                               String auditActionId,
                                               int page,
                                               int size,
                                               String sortProperty,
                                               String sorting,
                                               String auditActionTypeId,
                                               String userName, HttpServletResponse httpServletResponse) {
        return null;
    }

    @Override
    public Mono<ResponseEntity> getAllAuditActions() {
        return null;
    }

    @Override
    public Mono<ResponseEntity> getAllAuditActionTypes() {
        return null;
    }
}
