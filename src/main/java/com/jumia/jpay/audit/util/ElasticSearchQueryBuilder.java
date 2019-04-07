package com.jumia.jpay.audit.util;

import com.jumia.jpay.audit.dto.QueryFieldDto;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.joda.time.LocalDate;

import java.util.ArrayList;

/**
 * @author adeyi.adebolu
 * created on 05/04/2019
 */

public class ElasticSearchQueryBuilder {

    /**
     * @param propertyValues
     * @return
     */
    public static BoolQueryBuilder boolQueryBuilder(ArrayList<QueryFieldDto> propertyValues) {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        for (QueryFieldDto value : propertyValues) {
            String propertyType = value.getType();
            String propertyName = value.getProperty();
            Object propertyValue = value.getValue();
            MatchQueryBuilder matchQuery;
            RangeQueryBuilder rangeQuery;
            if (propertyType.equalsIgnoreCase("DateTime") || propertyType.equalsIgnoreCase("Date")) {
                LocalDate from = new LocalDate(value.getFrom());
                LocalDate to = new LocalDate(value.getTo());
                rangeQuery = QueryBuilders.rangeQuery(propertyName).gte(from.toDate().getTime()).lte(to.toDate().getTime());
                boolQueryBuilder.must(rangeQuery);
            } else if (propertyType.equalsIgnoreCase("Boolean")) {
                matchQuery = QueryBuilders.matchQuery(propertyName, propertyValue);
                boolQueryBuilder.must(matchQuery);
            } else if (propertyType.equalsIgnoreCase("List")) {
                String ids[] = (String[]) propertyValue;
                boolQueryBuilder.must(QueryBuilders.termsQuery(propertyName, ids));
            } else if (propertyType.equalsIgnoreCase("Contains")) {
                boolQueryBuilder.must(QueryBuilders.queryStringQuery(propertyValue + "*")
                        .field(propertyName)
                        .lenient(true)
                        .escape(true)
                        .analyzeWildcard(false)
                        .fuzziness(Fuzziness.ZERO)
                        .defaultOperator(Operator.AND)
                        .boost(2.0f));
            } else {
                matchQuery = QueryBuilders.matchQuery(propertyName, propertyValue);
                boolQueryBuilder.must(matchQuery);
            }
        }
        return boolQueryBuilder;
    }
}
