package com.jumia.jpay.audit.repositories;

import com.jumia.jpay.audit.domain.AuditActionType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author adeyi.adebolu
 * created on 07/04/2019
 */
@Repository
public interface AuditActionTypeRepository extends ElasticsearchRepository<AuditActionType, String> {
    AuditActionType findByNameAndAuditActionId(String name, String auditActionId);
}
