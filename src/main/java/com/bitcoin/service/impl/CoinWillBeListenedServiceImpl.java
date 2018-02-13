package com.bitcoin.service.impl;

import com.bitcoin.service.CoinWillBeListenedService;
import com.bitcoin.domain.CoinWillBeListened;
import com.bitcoin.repository.CoinWillBeListenedRepository;
import com.bitcoin.repository.search.CoinWillBeListenedSearchRepository;
import com.bitcoin.service.dto.CoinWillBeListenedDTO;
import com.bitcoin.service.mapper.CoinWillBeListenedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing CoinWillBeListened.
 */
@Service
@Transactional
public class CoinWillBeListenedServiceImpl implements CoinWillBeListenedService{

    private final Logger log = LoggerFactory.getLogger(CoinWillBeListenedServiceImpl.class);

    private final CoinWillBeListenedRepository coinWillBeListenedRepository;

    private final CoinWillBeListenedMapper coinWillBeListenedMapper;

    private final CoinWillBeListenedSearchRepository coinWillBeListenedSearchRepository;

    public CoinWillBeListenedServiceImpl(CoinWillBeListenedRepository coinWillBeListenedRepository, CoinWillBeListenedMapper coinWillBeListenedMapper, CoinWillBeListenedSearchRepository coinWillBeListenedSearchRepository) {
        this.coinWillBeListenedRepository = coinWillBeListenedRepository;
        this.coinWillBeListenedMapper = coinWillBeListenedMapper;
        this.coinWillBeListenedSearchRepository = coinWillBeListenedSearchRepository;
    }

    /**
     * Save a coinWillBeListened.
     *
     * @param coinWillBeListenedDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CoinWillBeListenedDTO save(CoinWillBeListenedDTO coinWillBeListenedDTO) {
        log.debug("Request to save CoinWillBeListened : {}", coinWillBeListenedDTO);
        CoinWillBeListened coinWillBeListened = coinWillBeListenedMapper.toEntity(coinWillBeListenedDTO);
        coinWillBeListened = coinWillBeListenedRepository.save(coinWillBeListened);
        CoinWillBeListenedDTO result = coinWillBeListenedMapper.toDto(coinWillBeListened);
        coinWillBeListenedSearchRepository.save(coinWillBeListened);
        return result;
    }

    /**
     * Get all the coinWillBeListeneds.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CoinWillBeListenedDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CoinWillBeListeneds");
        return coinWillBeListenedRepository.findAll(pageable)
            .map(coinWillBeListenedMapper::toDto);
    }

    /**
     * Get one coinWillBeListened by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CoinWillBeListenedDTO findOne(Long id) {
        log.debug("Request to get CoinWillBeListened : {}", id);
        CoinWillBeListened coinWillBeListened = coinWillBeListenedRepository.findOne(id);
        return coinWillBeListenedMapper.toDto(coinWillBeListened);
    }

    /**
     * Delete the coinWillBeListened by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CoinWillBeListened : {}", id);
        coinWillBeListenedRepository.delete(id);
        coinWillBeListenedSearchRepository.delete(id);
    }

    /**
     * Search for the coinWillBeListened corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CoinWillBeListenedDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CoinWillBeListeneds for query {}", query);
        Page<CoinWillBeListened> result = coinWillBeListenedSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(coinWillBeListenedMapper::toDto);
    }
}
