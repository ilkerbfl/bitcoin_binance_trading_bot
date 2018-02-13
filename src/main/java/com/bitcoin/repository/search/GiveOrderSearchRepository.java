package com.bitcoin.repository.search;

import com.bitcoin.domain.GiveOrder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the GiveOrder entity.
 */
public interface GiveOrderSearchRepository extends ElasticsearchRepository<GiveOrder, Long> {
}
