package com.jumia.jpay.audit.dto;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
public class AuditActionTypeDto extends EnumeratedEntityDto {
    private String auditActionId;
    private String auditActionName;

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
