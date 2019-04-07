package com.jumia.jpay.audit.dto;

import com.jumia.jpay.audit.dto.enumerated_entity.EnumeratedEntityDto;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
public class AuditActionTypeDto extends EnumeratedEntityDto {
    private String auditActionId;
    private String auditActionName;
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

    public String getAuditActionName() {
        return auditActionName;
    }

    public void setAuditActionName(String auditActionName) {
        this.auditActionName = auditActionName;
    }
}
