package com.jumia.jpay.audit.repositories;

import com.jumia.jpay.audit.domain.AuditLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author adeyi.adebolu
 * created on 06/04/2019
 */
@Repository
public interface AuditLogRepository extends ElasticsearchRepository<AuditLog, String> {
}
