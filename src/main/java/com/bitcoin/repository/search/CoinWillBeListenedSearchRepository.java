package com.bitcoin.repository.search;

import com.bitcoin.domain.CoinWillBeListened;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CoinWillBeListened entity.
 */
public interface CoinWillBeListenedSearchRepository extends ElasticsearchRepository<CoinWillBeListened, Long> {
}
