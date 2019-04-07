package com.jumia.jpay.audit.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.Objects;

/**
 * @author adeyi.adebolu
 * created on 06/04/2019
 */

@Configuration
public class ElasticSearchConfig {

    @Value("${elasticsearch.cluster-name}")
    private String clusterName;

    @Value("${elasticsearch.cluster-nodes}")
    private String clusterNodes;

    @Bean
    public TransportClientFactoryBean createTransportClientFactoryBean() {
        TransportClientFactoryBean clientFactoryBean = new TransportClientFactoryBean();
        clientFactoryBean.setClientTransportSniff(Boolean.FALSE);
        clientFactoryBean.setClusterName(clusterName);
        clientFactoryBean.setClusterNodes(clusterNodes);
        return clientFactoryBean;
    }

    //  @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(Objects.requireNonNull(createTransportClientFactoryBean().getObject()));
    }
}
