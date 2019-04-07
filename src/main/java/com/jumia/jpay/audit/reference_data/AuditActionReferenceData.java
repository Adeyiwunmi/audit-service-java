package com.jumia.jpay.audit.reference_data;

import com.jumia.jpay.audit.domain.AuditAction;
import com.jumia.jpay.audit.repositories.AuditActionRepository;
import com.jumia.jpay.audit.util.SpringContext;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
public class AuditActionReferenceData {

    private static AuditActionRepository auditActionRepository = SpringContext.getBean(AuditActionRepository.class);
    private static ConcurrentHashMap<String, AuditAction> cachedAuditActions = new ConcurrentHashMap<>();

    /**
     * Reads all audit actions in the DB and stores to the cache
     */
    public static void initCache() {
        auditActionRepository.findAll().forEach(auditAction -> {
            cachedAuditActions.put(auditAction.getId(), auditAction);
        });
    }


    /**
     * Looks for audit action firstly in in memory map,
     * If not found, Finds it in database, then if found, puts it  in memory map
     *
     * @param id
     * @return
     */
    public static AuditAction findById(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        AuditAction auditAction;
        auditAction = cachedAuditActions.get(id);
        if (auditAction == null) {
            auditAction = auditActionRepository.findById(id).orElse(null);
            if (auditAction != null) {
                cachedAuditActions.put(id, auditAction);
            }
        }
        return auditAction;
    }

    public static String getAuditActionNameById(String id) {
        AuditAction auditAction = findById(id);
        if (auditAction != null) {
            return auditAction.getName();
        }
        return null;
    }

    public static ConcurrentHashMap<String, AuditAction> getCachedAuditActions() {
        return cachedAuditActions;
    }
}
