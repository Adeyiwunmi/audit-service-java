package com.jumia.jpay.audit.domain;

import com.jumia.jpay.audit.dto.AuditLogDto;
import com.jumia.jpay.audit.util.Constants;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author adeyi.adebolu
 * created on 06/04/2019
 */

@Document(indexName = Constants.AUDIT_INDEX_NAME, type = Constants.AUDIT_INDEX_TYPE)
public class AuditLog {
    @Id
    private String id;
    private String performerName;
    private String actionPerformed;
    private long auditDate;
    private long auditDateTime;
    private String auditActionName;
    private String performedBy;
    private boolean viaAPI;
    private String remoteAddress;
    private String owner;
    private String serviceName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerformerName() {
        return performerName;
    }

    public void setPerformerName(String performerName) {
        this.performerName = performerName;
    }

    public String getActionPerformed() {
        return actionPerformed;
    }

    public void setActionPerformed(String actionPerformed) {
        this.actionPerformed = actionPerformed;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }


    public AuditLogDto convertToDto() {
        AuditLogDto dto = new AuditLogDto();
        dto.setAuditDate(getAuditDate() == 0 ? null : new LocalDate(getAuditDate()).toString("MM/dd/YYYY"));
        dto.setAuditDateTime(getAuditDateTime() == 0 ? null : new LocalDateTime(getAuditDateTime()).toString("MM/dd/YYYY HH:mm:ss"));
        dto.setActionPerformed(getActionPerformed());
        dto.setPerformedBy(getPerformedBy());
        dto.setOwner(getOwner());
        dto.setRemoteAddress(getRemoteAddress());
        dto.setViaAPI(isViaAPI());
        dto.setId(getId());
        dto.setAuditActionName(getAuditActionName());
        return dto;
    }
}
