package com.jumia.jpay.audit.service.contract;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;

/**
 * @author adeyi.adebolu
 * created on 05/04/2019
 */
public interface AuditLogService {
    Mono<ResponseEntity> getByTransactionId(@PathVariable String id);

    Mono<ResponseEntity> getAuditTrails(String keyword,
                                        String fromDate,
                                        String endDate,
                                        String auditActionId,
                                        int page,
                                        int size,
                                        String sortProperty,
                                        String sorting,
                                        String auditActionTypeId,
                                        String userName,
                                        HttpServletResponse httpServletResponse);

    Mono<ResponseEntity> getAllAuditActions();

    Mono<ResponseEntity> getAllAuditActionTypes();
}
