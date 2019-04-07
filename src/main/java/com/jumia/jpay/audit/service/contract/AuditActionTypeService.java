package com.jumia.jpay.audit.service.contract;

import com.jumia.jpay.audit.dto.audit_action_type.CreateAuditActionTypeDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface AuditActionTypeService {
    Mono<ResponseEntity> createAuditActionType(CreateAuditActionTypeDto createAuditActionTypeDto);

    Mono<ResponseEntity> getAllAuditActionTypes();
}
