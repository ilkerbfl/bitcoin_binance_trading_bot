package com.bitcoin.service;

import com.binance.api.client.domain.account.NewOrder;
import com.bitcoin.domain.NewOrderLocale;
import com.bitcoin.service.dto.NewOrderLocaleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing NewOrderLocale.
 */
public interface NewOrderLocaleService {

    /**
     * Save a newOrderLocale.
     *
     * @param newOrderLocaleDTO the entity to save
     * @return the persisted entity
     */
    NewOrderLocaleDTO save(NewOrderLocaleDTO newOrderLocaleDTO);

    /**
     * Get all the newOrderLocales.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NewOrderLocaleDTO> findAll(Pageable pageable);

    /**
     * Get the "id" newOrderLocale.
     *
     * @param id the id of the entity
     * @return the entity
     */
    NewOrderLocaleDTO findOne(Long id);

    /**
     * Delete the "id" newOrderLocale.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the newOrderLocale corresponding to the query.
     *
     * @param query the query of the search
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NewOrderLocaleDTO> search(String query, Pageable pageable);

    NewOrder calculateOrderPropertiesThenPlaceOrder(NewOrderLocale newOrderLocale);
}
