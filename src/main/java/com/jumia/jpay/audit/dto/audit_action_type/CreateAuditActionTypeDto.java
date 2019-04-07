package com.jumia.jpay.audit.dto.audit_action_type;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
public class CreateAuditActionTypeDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String auditActionId;
    @NotNull
    private boolean hasSensitiveInformation;

    public boolean isHasSensitiveInformation() {
        return hasSensitiveInformation;
    }

    public void setHasSensitiveInformation(boolean hasSensitiveInformation) {
        this.hasSensitiveInformation = hasSensitiveInformation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuditActionId() {
        return auditActionId;
    }

    public void setAuditActionId(String auditActionId) {
        this.auditActionId = auditActionId;
    }
}
