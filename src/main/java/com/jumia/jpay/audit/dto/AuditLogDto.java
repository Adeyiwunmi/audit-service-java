package com.jumia.jpay.audit.dto;

public class AuditLogDto {
    private String id;
    private String auditDate;
    private String auditDateTime;
    private String auditActionId;
    private String auditActionName;
    private String userName;
    private String actionPerformed;
    private boolean viaAPI;
    private String remoteAddress;
    private String owner;
    private String auditActionTypeId;
    private String auditActionTypeName;


    public String getAuditActionName() {
        return auditActionName;
    }

    public void setAuditActionName(String auditActionName) {
        this.auditActionName = auditActionName;
    }

    public String getAuditActionTypeName() {
        return auditActionTypeName;
    }

    public void setAuditActionTypeName(String auditActionTypeName) {
        this.auditActionTypeName = auditActionTypeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditDateTime() {
        return auditDateTime;
    }

    public void setAuditDateTime(String auditDateTime) {
        this.auditDateTime = auditDateTime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActionPerformed() {
        return actionPerformed;
    }

    public void setActionPerformed(String actionPerformed) {
        this.actionPerformed = actionPerformed;
    }

    public boolean getViaAPI() {
        return viaAPI;
    }

    public void setViaAPI(boolean viaAPI) {
        this.viaAPI = viaAPI;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }


    public String getAuditActionId() {
        return auditActionId;
    }

    public void setAuditActionId(String auditActionId) {
        this.auditActionId = auditActionId;
    }

    public boolean isViaAPI() {
        return viaAPI;
    }

    public String getAuditActionTypeId() {
        return auditActionTypeId;
    }

    public void setAuditActionTypeId(String auditActionTypeId) {
        this.auditActionTypeId = auditActionTypeId;
    }
}
