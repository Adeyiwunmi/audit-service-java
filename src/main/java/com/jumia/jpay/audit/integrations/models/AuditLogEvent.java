package com.jumia.jpay.audit.integrations.models;

public class AuditLogEvent {
    private String id;
    private long auditDate;
    private long auditDateTime;
    private String auditActionName;
    private String performedBy;
    private String actionPerformed;
    private boolean viaAPI;
    private String remoteAddress;
    private String owner;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(long auditDate) {
        this.auditDate = auditDate;
    }

    public long getAuditDateTime() {
        return auditDateTime;
    }

    public void setAuditDateTime(long auditDateTime) {
        this.auditDateTime = auditDateTime;
    }

    public String getAuditActionName() {
        return auditActionName;
    }

    public void setAuditActionName(String auditActionName) {
        this.auditActionName = auditActionName;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public String getActionPerformed() {
        return actionPerformed;
    }

    public void setActionPerformed(String actionPerformed) {
        this.actionPerformed = actionPerformed;
    }

    public boolean isViaAPI() {
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
