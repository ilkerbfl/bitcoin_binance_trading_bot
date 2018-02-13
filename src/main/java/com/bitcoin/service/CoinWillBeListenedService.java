package com.bitcoin.service;

import com.bitcoin.service.dto.CoinWillBeListenedDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CoinWillBeListened.
 */
public interface CoinWillBeListenedService {

    /**
     * Save a coinWillBeListened.
     *
     * @param coinWillBeListenedDTO the entity to save
     * @return the persisted entity
     */
    CoinWillBeListenedDTO save(CoinWillBeListenedDTO coinWillBeListenedDTO);

    /**
     * Get all the coinWillBeListeneds.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CoinWillBeListenedDTO> findAll(Pageable pageable);

    /**
     * Get the "id" coinWillBeListened.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CoinWillBeListenedDTO findOne(Long id);

    /**
     * Delete the "id" coinWillBeListened.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the coinWillBeListened corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CoinWillBeListenedDTO> search(String query, Pageable pageable);
}
