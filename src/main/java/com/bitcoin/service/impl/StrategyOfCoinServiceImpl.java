package com.bitcoin.service.impl;

import com.bitcoin.service.StrategyOfCoinService;
import com.bitcoin.domain.StrategyOfCoin;
import com.bitcoin.repository.StrategyOfCoinRepository;
import com.bitcoin.repository.search.StrategyOfCoinSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing StrategyOfCoin.
 */
@Service
@Transactional
public class StrategyOfCoinServiceImpl implements StrategyOfCoinService{

    private final Logger log = LoggerFactory.getLogger(StrategyOfCoinServiceImpl.class);

    private final StrategyOfCoinRepository strategyOfCoinRepository;

    private final StrategyOfCoinSearchRepository strategyOfCoinSearchRepository;

    public StrategyOfCoinServiceImpl(StrategyOfCoinRepository strategyOfCoinRepository, StrategyOfCoinSearchRepository strategyOfCoinSearchRepository) {
        this.strategyOfCoinRepository = strategyOfCoinRepository;
        this.strategyOfCoinSearchRepository = strategyOfCoinSearchRepository;
    }

    /**
     * Save a strategyOfCoin.
     *
     * @param strategyOfCoin the entity to save
     * @return the persisted entity
     */
    @Override
    public StrategyOfCoin save(StrategyOfCoin strategyOfCoin) {
        log.debug("Request to save StrategyOfCoin : {}", strategyOfCoin);
        StrategyOfCoin result = strategyOfCoinRepository.save(strategyOfCoin);
        strategyOfCoinSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the strategyOfCoins.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StrategyOfCoin> findAll(Pageable pageable) {
        log.debug("Request to get all StrategyOfCoins");
        return strategyOfCoinRepository.findAll(pageable);
    }

    /**
     * Get one strategyOfCoin by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public StrategyOfCoin findOne(Long id) {
        log.debug("Request to get StrategyOfCoin : {}", id);
        return strategyOfCoinRepository.findOne(id);
    }

    /**
     * Delete the strategyOfCoin by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StrategyOfCoin : {}", id);
        strategyOfCoinRepository.delete(id);
        strategyOfCoinSearchRepository.delete(id);
    }

    /**
     * Search for the strategyOfCoin corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StrategyOfCoin> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of StrategyOfCoins for query {}", query);
        Page<StrategyOfCoin> result = strategyOfCoinSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
