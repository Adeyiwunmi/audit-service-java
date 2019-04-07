package com.jumia.jpay.audit.service;

import com.jumia.jpay.audit.domain.AuditAction;
import com.jumia.jpay.audit.dto.enumerated_entity.EnumeratedEntityCreateDto;
import com.jumia.jpay.audit.dto.enumerated_entity.EnumeratedEntityDto;
import com.jumia.jpay.audit.reference_data.AuditActionReferenceData;
import com.jumia.jpay.audit.repositories.AuditActionRepository;
import com.jumia.jpay.audit.service.contract.AuditActionService;
import com.jumia.jpay.audit.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static com.jumia.jpay.audit.util.ResponseUtil.LogAndReturnError;
import static com.jumia.jpay.audit.util.ResponseUtil.NoRecordResponse;
import static com.jumia.jpay.audit.util.ResponseUtil.OkResponse;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
@Service
public class AuditActionServiceImpl implements AuditActionService {

    private static final Logger logger = LoggerFactory.getLogger(AuditActionTypeServiceImpl.class);

    private AuditActionRepository auditActionRepository;

    @Autowired
    public void setAuditActionRepository(AuditActionRepository auditActionRepository) {
        this.auditActionRepository = auditActionRepository;
    }

    @Override
    public Mono<ResponseEntity> createAuditAction(EnumeratedEntityCreateDto entityCreateDto) {
        try {
            AuditAction actionWithName = auditActionRepository.findByName(entityCreateDto.getName());
            if (actionWithName != null) {
                return ResponseUtil.BadRequestResponse("There is an audit action with the same name");
            }
            AuditAction auditAction = new AuditAction();
            auditAction.setName(entityCreateDto.getName());
            auditAction.setId(UUID.randomUUID().toString());
            auditActionRepository.save(auditAction);
            AuditActionReferenceData.getCachedAuditActions().put(auditAction.getId(), auditAction);
            return ResponseUtil.OkResponse(auditAction.convertToDto());
        } catch (Exception e) {
            return ResponseUtil.LogAndReturnError(logger, "An error occurred while creating audit action", e);
        }
    }

    @Override
    public Mono<ResponseEntity> getAllAuditActions() {
        try {
            Collection<AuditAction> actions = AuditActionReferenceData.getCachedAuditActions().values();
            if (actions.isEmpty()) {
                return NoRecordResponse();
            }
            ArrayList<EnumeratedEntityDto> enumeratedEntityDtos = new ArrayList<>();
            actions.forEach(auditAction -> {
                enumeratedEntityDtos.add(auditAction.convertToDto());
            });
            return OkResponse(enumeratedEntityDtos);
        } catch (Exception e) {
            return LogAndReturnError(logger, "An error occurred while getting all audit actions", e);
        }
    }
}

