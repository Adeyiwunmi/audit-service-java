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


    //There is possibility if having more
    public static final String USER_LOGIN_ID = "1";
    public static final String USER_PASSWORD_CHANGE_ID = "2";
    public static final String NEW_PAYMENT_SETTING_ID = "3";
    public static final String EDIT_PAYMENT_SETTING_ID = "4";
    public static final String EDIT_MERCHANT_DETAILS_ID = "5";
    public static final String NEW_MERCHANT_CREATED_ID = "6";


    public static void load() {
        loadForIdNameAndAuditAction(USER_LOGIN_ID, "USER LOGIN", AuditActionReferenceData.USER_ACTION_ID);
        loadForIdNameAndAuditAction(USER_PASSWORD_CHANGE_ID, "PASSWORD CHANGE", AuditActionReferenceData.USER_ACTION_ID);
        loadForIdNameAndAuditAction(NEW_PAYMENT_SETTING_ID, "NEW PAYMENT SETTING", AuditActionReferenceData.PAYMENT_SETTINGS_ID);
        loadForIdNameAndAuditAction(EDIT_PAYMENT_SETTING_ID, "EDIT PAYMENT SETTING", AuditActionReferenceData.PAYMENT_SETTINGS_ID);
        loadForIdNameAndAuditAction(EDIT_MERCHANT_DETAILS_ID, "EDIT MERCHANT DETAILS", AuditActionReferenceData.MERCHANT_ACTION_ID);
        loadForIdNameAndAuditAction(NEW_MERCHANT_CREATED_ID, "NEW MERCHANT CREATED", AuditActionReferenceData.MERCHANT_ACTION_ID);
    }

    private static void loadForIdNameAndAuditAction(String id, String name, String auditActionId) {
        AuditActionType auditActionType = auditActionTypeRepository.findById(id).orElse(null);
        if (auditActionType == null) {
            auditActionType = new AuditActionType();
            auditActionType.setId(id);
        }
        auditActionType.setName(name);
        auditActionTypeRepository.save(auditActionType);
    }

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
