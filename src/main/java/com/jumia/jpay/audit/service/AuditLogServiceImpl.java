package com.jumia.jpay.audit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumia.jpay.audit.domain.AuditAction;
import com.jumia.jpay.audit.domain.AuditActionType;
import com.jumia.jpay.audit.domain.AuditLog;
import com.jumia.jpay.audit.dto.AuditActionTypeDto;
import com.jumia.jpay.audit.dto.AuditLogDto;
import com.jumia.jpay.audit.dto.EnumeratedEntityDto;
import com.jumia.jpay.audit.dto.QueryFieldDto;
import com.jumia.jpay.audit.reference_data.AuditActionReferenceData;
import com.jumia.jpay.audit.reference_data.AuditActionTypeReferenceData;
import com.jumia.jpay.audit.repositories.AuditActionRepository;
import com.jumia.jpay.audit.repositories.AuditActionTypeRepository;
import com.jumia.jpay.audit.repositories.AuditLogRepository;
import com.jumia.jpay.audit.service.contract.AuditLogService;
import com.jumia.jpay.audit.util.Constants;
import com.jumia.jpay.audit.util.ElasticSearchQueryBuilder;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;

import static com.jumia.jpay.audit.util.ResponseUtil.*;

/**
 * @author adeyi.adebolu
 * created on 05/04/2019
 */
@Service("AuditLogServiceImpl")
public class AuditLogServiceImpl implements AuditLogService {

    private static final Logger logger = LoggerFactory.getLogger(AuditLogServiceImpl.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private ElasticsearchTemplate elasticsearchTemplate;
    private AuditLogRepository auditLogRepository;
    private AuditActionRepository auditActionRepository;
    private AuditActionTypeRepository auditActionTypeRepository;

    @Autowired
    public AuditLogServiceImpl(ElasticsearchTemplate elasticsearchTemplate
            , AuditLogRepository auditLogRepository,
                               AuditActionRepository auditActionRepository,
                               AuditActionTypeRepository auditActionTypeRepository) {
        this.elasticsearchTemplate = elasticsearchTemplate;
        this.auditLogRepository = auditLogRepository;
        this.auditActionRepository = auditActionRepository;
        this.auditActionTypeRepository = auditActionTypeRepository;
    }

    @Override
    public Mono<ResponseEntity> getByTransactionId(String id) {
        try {
            AuditLog auditLog = auditLogRepository.findById(id).orElse(null);
            if (auditLog == null) {
                return NoRecordResponse();
            }
            return OkResponse(auditLog.convertToDto());
        } catch (Exception e) {
            return LogAndReturnError(logger, "An error occurred while getting audit log by Id", e);
        }
    }

    /**
     * @return All AuditTrails full information
     */
    @Override
    public Mono<ResponseEntity> getAuditTrails(String keyword,
                                               String fromDate,
                                               String endDate,
                                               String auditActionId,
                                               int page,
                                               int size,
                                               String sortProperty,
                                               String sorting,
                                               String auditActionTypeId,
                                               HttpServletResponse httpServletResponse) {

        try {
            ArrayList<QueryFieldDto> filterCriteria = new ArrayList<>();
            if (!StringUtils.isEmpty(auditActionId)) {
                filterCriteria.add(new QueryFieldDto(auditActionId, "String", "auditActionId", null, null));
            }
            if (!StringUtils.isEmpty(auditActionId)) {
                filterCriteria.add(new QueryFieldDto(auditActionTypeId, "String", "auditActionTypeId", null, null));
            }
            if (!StringUtils.isEmpty(fromDate) && !StringUtils.isEmpty(endDate)) {
                filterCriteria.add(new QueryFieldDto(null, "DateTime", "auditDate", fromDate, endDate));
            }
            if (!StringUtils.isEmpty(keyword)) {
                filterCriteria.add(new QueryFieldDto(keyword, "Contains", "actionPerformed", null, null));
            }
            //Sorting cannot be null
            if (sortProperty == null || sortProperty.isEmpty() || sorting == null || sorting.isEmpty()) {
                sorting = "DESC";
                sortProperty = "auditDateTime";
            }

            BoolQueryBuilder boolQueryBuilder = ElasticSearchQueryBuilder.boolQueryBuilder(filterCriteria);

            SearchResponse searchResponse = elasticsearchTemplate.getClient().prepareSearch(Constants.AUDIT_INDEX_NAME)
                    .setTypes(Constants.AUDIT_INDEX_TYPE)
                    .setQuery(boolQueryBuilder)
                    .setFrom(page * size)
                    .setSize(size).addSort(sortProperty, (sorting.equalsIgnoreCase("DESC") ? SortOrder.DESC : SortOrder.ASC))
                    .get();

            SearchHits hits = searchResponse.getHits();
            if (hits.totalHits == 0) {
                return NoRecordResponse();
            }

            ArrayList<AuditLogDto> auditLogDtos = new ArrayList<>();

            AuditLog auditTrail = null;
            for (SearchHit hit : hits) {
                auditTrail = OBJECT_MAPPER.readValue(hit.getSourceAsString(), AuditLog.class);
                auditLogDtos.add(auditTrail.convertToDto());
            }

            if (page == 0) {
                httpServletResponse.setHeader("TotalCount", String.valueOf(hits.totalHits));
            }
            return OkResponse(auditLogDtos);
        } catch (IllegalArgumentException e) {
            return Mono.just(new ResponseEntity<>("Invalid Date format , please use yyyy-MM-dd", HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            return LogAndReturnError(logger, "An error occurred while getting audit logs", e);
        }
    }

    @Override
    public Mono<ResponseEntity> getAllAuditActions() {
        try {
            Collection<AuditAction> actions = AuditActionReferenceData.getCachedAuditActions().values();
            if (actions.isEmpty()) {
                return NoRecordResponse();
            }
            ArrayList<EnumeratedEntityDto> enumeratedEntityDtos = new ArrayList<>();
            actions.forEach(auditAction -> {
                enumeratedEntityDtos.add(auditAction.convertToDto());
            });
            return OkResponse(enumeratedEntityDtos);
        } catch (Exception e) {
            return LogAndReturnError(logger, "An error occurred while getting all audit actions", e);
        }
    }

    @Override
    public Mono<ResponseEntity> getAllAuditActionTypes() {
        try {
            Collection<AuditActionType> actionTypes = AuditActionTypeReferenceData.getCachedAuditActionTypes().values();
            if (actionTypes.isEmpty()) {
                return NoRecordResponse();
            }
            ArrayList<AuditActionTypeDto> auditActionTypeDtos = new ArrayList<>();
            actionTypes.forEach(actionType -> {
                auditActionTypeDtos.add(actionType.convertToDto());
            });
            return OkResponse(auditActionTypeDtos);
        } catch (Exception e) {
            return LogAndReturnError(logger, "An error occurred while getting all audit action types", e);
        }
    }
}
