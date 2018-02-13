package com.bitcoin.repository.search;

import com.bitcoin.domain.NewOrderLocale;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the NewOrderLocale entity.
 */
public interface NewOrderLocaleSearchRepository extends ElasticsearchRepository<NewOrderLocale, Long> {
}
