package com.jumia.jpay.audit.repositories;

import com.jumia.jpay.audit.domain.AuditAction;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditActionRepository extends ElasticsearchRepository<AuditAction, String> {
    AuditAction findByName(String name);
}
