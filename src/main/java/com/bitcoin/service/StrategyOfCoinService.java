package com.bitcoin.service;

import com.bitcoin.domain.StrategyOfCoin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing StrategyOfCoin.
 */
public interface StrategyOfCoinService {

    /**
     * Save a strategyOfCoin.
     *
     * @param strategyOfCoin the entity to save
     * @return the persisted entity
     */
    StrategyOfCoin save(StrategyOfCoin strategyOfCoin);

    /**
     * Get all the strategyOfCoins.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StrategyOfCoin> findAll(Pageable pageable);

    /**
     * Get the "id" strategyOfCoin.
     *
     * @param id the id of the entity
     * @return the entity
     */
    StrategyOfCoin findOne(Long id);

    /**
     * Delete the "id" strategyOfCoin.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the strategyOfCoin corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StrategyOfCoin> search(String query, Pageable pageable);
}
