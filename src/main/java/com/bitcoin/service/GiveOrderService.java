package com.bitcoin.service;

import com.bitcoin.service.dto.GiveOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing GiveOrder.
 */
public interface GiveOrderService {

    /**
     * Save a giveOrder.
     *
     * @param giveOrderDTO the entity to save
     * @return the persisted entity
     */
    GiveOrderDTO save(GiveOrderDTO giveOrderDTO);

    /**
     * Get all the giveOrders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GiveOrderDTO> findAll(Pageable pageable);

    /**
     * Get the "id" giveOrder.
     *
     * @param id the id of the entity
     * @return the entity
     */
    GiveOrderDTO findOne(Long id);

    /**
     * Delete the "id" giveOrder.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the giveOrder corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<GiveOrderDTO> search(String query, Pageable pageable);
}
