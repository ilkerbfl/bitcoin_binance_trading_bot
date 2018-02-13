package com.bitcoin.repository.search;

import com.bitcoin.domain.StrategyOfCoin;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StrategyOfCoin entity.
 */
public interface StrategyOfCoinSearchRepository extends ElasticsearchRepository<StrategyOfCoin, Long> {
}
