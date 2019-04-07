package com.jumia.jpay.audit.service.contract;

import com.jumia.jpay.audit.dto.enumerated_entity.EnumeratedEntityCreateDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface AuditActionService {
    Mono<ResponseEntity> createAuditAction(EnumeratedEntityCreateDto entityCreateDto);

    Mono<ResponseEntity> getAllAuditActions();
}
