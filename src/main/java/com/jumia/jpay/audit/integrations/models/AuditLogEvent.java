package com.jumia.jpay.audit.integrations.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jumia.jpay.audit.reference_data.AuditActionReferenceData;
import org.apache.commons.lang3.StringUtils;

public class AuditLogEvent {
    private String id;
    private long auditDate;
    private long auditDateTime;
    private String auditActionId;
    private String auditActionTypeId;
    private String userName;
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

    public String getAuditActionId() {
        return auditActionId;
    }

    public void setAuditActionId(String auditActionId) {
        this.auditActionId = auditActionId;
    }

    public String getAuditActionTypeId() {
        return auditActionTypeId;
    }

    public void setAuditActionTypeId(String auditActionTypeId) {
        this.auditActionTypeId = auditActionTypeId;
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

    @JsonIgnore
    public boolean isChangePasswordEvent() {
        return StringUtils.equals(AuditActionReferenceData.USER_ACTION_ID, this.auditActionTypeId);
    }

    @JsonIgnore
    public boolean isAddPaymentSettingEvent() {
        return StringUtils.equals(AuditActionReferenceData.USER_ACTION_ID, this.auditActionTypeId);
    }

    @JsonIgnore
    public boolean isEditPaymentSettingEvent() {
        return StringUtils.equals(AuditActionReferenceData.USER_ACTION_ID, this.auditActionTypeId);
    }
}
