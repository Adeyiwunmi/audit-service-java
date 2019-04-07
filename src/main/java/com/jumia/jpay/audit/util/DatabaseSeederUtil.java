package com.jumia.jpay.audit.util;

import com.jumia.jpay.audit.reference_data.AuditActionReferenceData;
import com.jumia.jpay.audit.reference_data.AuditActionTypeReferenceData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
@Component
public class DatabaseSeederUtil {

    public static final Logger logger = LoggerFactory.getLogger(DatabaseSeederUtil.class);


    public void doApplicationInitialisation() {
        initReferenceData();
        initCache();
    }

    private void initReferenceData() {
        logger.info("Loading Reference Data ==> AuditActions");
        AuditActionReferenceData.load();
        logger.info("Loading Reference Data ==> AuditActionTypes");
        AuditActionTypeReferenceData.load();
    }

    private void initCache() {
        logger.info("Initializing Cache ==> AuditActions");
        AuditActionReferenceData.initCache();
        logger.info("Initializing Cache ==> AuditActionTypes");
        AuditActionTypeReferenceData.initCache();
    }
}
