package com.jumia.jpay.audit.service;

import com.jumia.jpay.audit.domain.AuditActionType;
import com.jumia.jpay.audit.dto.AuditActionTypeDto;
import com.jumia.jpay.audit.dto.audit_action_type.CreateAuditActionTypeDto;
import com.jumia.jpay.audit.reference_data.AuditActionTypeReferenceData;
import com.jumia.jpay.audit.repositories.AuditActionTypeRepository;
import com.jumia.jpay.audit.service.contract.AuditActionService;
import com.jumia.jpay.audit.service.contract.AuditActionTypeService;
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
public class AuditActionTypeServiceImpl implements AuditActionTypeService {

    private static final Logger logger = LoggerFactory.getLogger(AuditActionTypeServiceImpl.class);

    private AuditActionTypeRepository auditActionTypeRepository;

    @Autowired
    public void setAuditActionTypeRepository(AuditActionTypeRepository auditActionTypeRepository) {
        this.auditActionTypeRepository = auditActionTypeRepository;
    }

    @Override
    public Mono<ResponseEntity> createAuditActionType(CreateAuditActionTypeDto createAuditActionTypeDto) {
        try {
            AuditActionType typeWithSameNameAndActionId = auditActionTypeRepository.findByNameAndAuditActionId(
                    createAuditActionTypeDto.getName(), createAuditActionTypeDto.getAuditActionId());
            if (typeWithSameNameAndActionId != null) {
                return ResponseUtil.BadRequestResponse("There is an audit action type with same name and action");
            }
            AuditActionType auditActionType = new AuditActionType();
            auditActionType.setId(UUID.randomUUID().toString());
            auditActionType.setAuditActionId(createAuditActionTypeDto.getAuditActionId());
            auditActionType.setName(createAuditActionTypeDto.getName());
            auditActionType.setHasSensitiveInformation(createAuditActionTypeDto.isHasSensitiveInformation());
            //save and cache
            auditActionTypeRepository.save(auditActionType);
            AuditActionTypeReferenceData.getCachedAuditActionTypes().put(auditActionType.getId(), auditActionType);
            return ResponseUtil.OkResponse(auditActionType.convertToDto());
        } catch (Exception e) {
            return ResponseUtil.LogAndReturnError(logger, "An error occurred while creating action type", e);
        }
    }


    @Override
    public Mono<ResponseEntity> getAllAuditActionTypes() {
        try {
            Collection<AuditActionType> actionTypes = AuditActionTypeReferenceData.getCachedAuditActionTypes().values();
            if (actionTypes.isEmpty()) {
                return NoRecordResponse();
            }
            ArrayList<AuditActionTypeDto> auditActionTypeDtos = new ArrayList<>();
            actionTypes.forEach(actionType -> {
                auditActionTypeDtos.add(actionType.convertToDto());
            });
            return OkResponse(auditActionTypeDtos);
        } catch (Exception e) {
            return LogAndReturnError(logger, "An error occurred while getting all audit action types", e);
        }
    }

}
