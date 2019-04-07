package com.jumia.jpay.audit.reference_data;

import com.jumia.jpay.audit.domain.AuditActionType;
import com.jumia.jpay.audit.repositories.AuditActionTypeRepository;
import com.jumia.jpay.audit.util.SpringContext;
import org.apache.commons.lang.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
public class AuditActionTypeReferenceData {
    private static AuditActionTypeRepository auditActionTypeRepository = SpringContext.getBean(AuditActionTypeRepository.class);
    private static ConcurrentHashMap<String, AuditActionType> cachedAuditActionTypes = new ConcurrentHashMap<>();

    /**
     * Reads all audit actions in the DB and stores to the cache
     */
    public static void initCache() {
        auditActionTypeRepository.findAll().forEach(auditActionType -> {
            cachedAuditActionTypes.put(auditActionType.getId(), auditActionType);
        });
    }


    /**
     * Looks for audit action type firstly in in memory map,
     * If not found, Finds it in database, then if found, puts it in memory map
     *
     * @param id
     * @return
     */
    public static AuditActionType findById(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        AuditActionType auditActionType;
        auditActionType = cachedAuditActionTypes.get(id);
        if (auditActionType == null) {
            auditActionType = auditActionTypeRepository.findById(id).orElse(null);
            if (auditActionType != null) {
                cachedAuditActionTypes.put(id, auditActionType);
            }
        }
        return auditActionType;
    }

    public static String findAuditActionTypeNameById(String id) {
        AuditActionType type = findById(id);
        if (type != null) {
            return type.getName();
        }
        return null;
    }

    public static ConcurrentHashMap<String, AuditActionType> getCachedAuditActionTypes() {
        return cachedAuditActionTypes;
    }
}
