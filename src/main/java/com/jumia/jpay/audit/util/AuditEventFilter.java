package com.jumia.jpay.audit.util;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.jumia.jpay.audit.domain.AuditActionType;
import com.jumia.jpay.audit.integrations.models.AuditLogEvent;
import com.jumia.jpay.audit.reference_data.AuditActionTypeReferenceData;


public class AuditEventFilter {


    /**
     * Hashes the action performed in an Audit event string
     * If it is an action type that the action performed will contain
     * sensitive information
     */
    public static AuditLogEvent filterNewAuditEvent(AuditLogEvent auditLogEvent) {

        //These are the action types that contain sensitive info
        AuditActionType auditActionType = AuditActionTypeReferenceData.findById(auditLogEvent.getAuditActionTypeId());

         if (auditActionType != null && auditActionType.isHasSensitiveInformation()) {
            auditLogEvent.setActionPerformed(BCrypt.withDefaults().
                    hashToString(12, auditLogEvent.getActionPerformed().toCharArray()));
        }
        return auditLogEvent;
    }
}
