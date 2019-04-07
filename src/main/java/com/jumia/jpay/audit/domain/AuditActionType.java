package com.jumia.jpay.audit.domain;

import com.jumia.jpay.audit.dto.AuditActionTypeDto;
import com.jumia.jpay.audit.reference_data.AuditActionReferenceData;
import com.jumia.jpay.audit.util.Constants;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
@Document(indexName = Constants.AUDIT_INDEX_NAME, type = Constants.AUDIT_ACTION_TYPE_TYPE_NAME)
public class AuditActionType extends EnumeratedEntity {
    private String auditActionId;
    private boolean hasSensitiveInformation;

    public boolean isHasSensitiveInformation() {
        return hasSensitiveInformation;
    }

    public void setHasSensitiveInformation(boolean hasSensitiveInformation) {
        this.hasSensitiveInformation = hasSensitiveInformation;
    }

    public String getAuditActionId() {
        return auditActionId;
    }

    public void setAuditActionId(String auditActionId) {
        this.auditActionId = auditActionId;
    }

    public AuditActionTypeDto convertToDto() {
        AuditActionTypeDto dto = new AuditActionTypeDto();
        dto.setId(getId());
        dto.setName(getName());
        dto.setHasSensitiveInformation(isHasSensitiveInformation());
        dto.setAuditActionName(AuditActionReferenceData.getAuditActionNameById(getAuditActionId()));
        dto.setAuditActionId(getAuditActionId());
        return dto;
    }

    public static AuditActionType fromNameAndSensitiveInformation(String name, boolean hasSensitiveInfo) {
        AuditActionType auditActionType = new AuditActionType();
        auditActionType.setName(name);
        auditActionType.setHasSensitiveInformation(hasSensitiveInfo);
        return auditActionType;
    }
}
