package com.jumia.jpay.audit.util;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.jumia.jpay.audit.integrations.models.AuditLogEvent;


public class AuditEventFilter {


    /**
     * Hashes the action performed in an Audit event string
     * If it is an action type that the action performed will contain
     * sensitive information
     */
    public static AuditLogEvent filterNewAuditEvent(AuditLogEvent auditLogEvent) {

        //These are the action types that contain sensitive info,
        // If there are more the checks would be done here
        if (auditLogEvent.isEditPaymentSettingEvent()
                || auditLogEvent.isAddPaymentSettingEvent()
                || auditLogEvent.isChangePasswordEvent()) {
            auditLogEvent.setActionPerformed(BCrypt.withDefaults().
                    hashToString(12, auditLogEvent.getActionPerformed().toCharArray()));
        }
        return auditLogEvent;
    }
}
