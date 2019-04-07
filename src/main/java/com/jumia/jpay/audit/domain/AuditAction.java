package com.jumia.jpay.audit.domain;

import com.jumia.jpay.audit.util.Constants;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
@Document(indexName = Constants.AUDIT_INDEX_NAME, type = Constants.AUDIT_ACTION_TYPE_NAME)
public class AuditAction extends EnumeratedEntity {
}
