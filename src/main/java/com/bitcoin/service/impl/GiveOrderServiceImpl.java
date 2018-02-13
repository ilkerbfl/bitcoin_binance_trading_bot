package com.bitcoin.service.impl;

import com.bitcoin.service.GiveOrderService;
import com.bitcoin.domain.GiveOrder;
import com.bitcoin.repository.GiveOrderRepository;
import com.bitcoin.repository.search.GiveOrderSearchRepository;
import com.bitcoin.service.dto.GiveOrderDTO;
import com.bitcoin.service.mapper.GiveOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing GiveOrder.
 */
@Service
@Transactional
public class GiveOrderServiceImpl implements GiveOrderService{

    private final Logger log = LoggerFactory.getLogger(GiveOrderServiceImpl.class);

    private final GiveOrderRepository giveOrderRepository;

//    private final GiveOrderMapper giveOrderMapper;

    private final GiveOrderSearchRepository giveOrderSearchRepository;

    public GiveOrderServiceImpl(GiveOrderRepository giveOrderRepository,  GiveOrderSearchRepository giveOrderSearchRepository) {
        this.giveOrderRepository = giveOrderRepository;
//        this.giveOrderMapper = giveOrderMapper;
        this.giveOrderSearchRepository = giveOrderSearchRepository;
    }

    /**
     * Save a giveOrder.
     *
     * @param giveOrderDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public GiveOrderDTO save(GiveOrderDTO giveOrderDTO) {
        log.debug("Request to save GiveOrder : {}", giveOrderDTO);
//        GiveOrder giveOrder = giveOrderMapper.toEntity(giveOrderDTO);
//        giveOrder = giveOrderRepository.save(giveOrder);
//        GiveOrderDTO result = giveOrderMapper.toDto(giveOrder);
//        giveOrderSearchRepository.save(giveOrder);
//        return result;
        return null;
    }

    /**
     * Get all the giveOrders.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GiveOrderDTO> findAll(Pageable pageable) {
//        log.debug("Request to get all GiveOrders");
//        return giveOrderRepository.findAll(pageable)
//            .map(giveOrderMapper::toDto);
        return null;
    }

    /**
     * Get one giveOrder by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public GiveOrderDTO findOne(Long id) {
//        log.debug("Request to get GiveOrder : {}", id);
//        GiveOrder giveOrder = giveOrderRepository.findOne(id);
//        return giveOrderMapper.toDto(giveOrder);
        return null;
    }

    /**
     * Delete the giveOrder by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GiveOrder : {}", id);
        giveOrderRepository.delete(id);
        giveOrderSearchRepository.delete(id);
    }

    /**
     * Search for the giveOrder corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GiveOrderDTO> search(String query, Pageable pageable) {
//        log.debug("Request to search for a page of GiveOrders for query {}", query);
//        Page<GiveOrder> result = giveOrderSearchRepository.search(queryStringQuery(query), pageable);
//        return result.map(giveOrderMapper::toDto);
        return null;
    }
}
