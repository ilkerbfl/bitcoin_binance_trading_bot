package com.bitcoin.service.impl;

import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.NewOrder;
import com.bitcoin.Utility.Utility;
import com.bitcoin.domain.NewOrderLocale;
import com.bitcoin.domain.StrategyOfCoin;
import com.bitcoin.repository.NewOrderLocaleRepository;
import com.bitcoin.repository.StrategyOfCoinRepository;
import com.bitcoin.repository.search.NewOrderLocaleSearchRepository;
import com.bitcoin.service.NewOrderLocaleService;
import com.bitcoin.service.dto.NewOrderLocaleDTO;
import com.bitcoin.service.mapper.NewOrderLocaleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing NewOrderLocale.
 */
@Service
@Transactional
public class NewOrderLocaleServiceImpl implements NewOrderLocaleService{

    private final Logger log = LoggerFactory.getLogger(NewOrderLocaleServiceImpl.class);

    private final NewOrderLocaleRepository newOrderLocaleRepository;

    private final NewOrderLocaleMapper newOrderLocaleMapper;

    private final NewOrderLocaleSearchRepository newOrderLocaleSearchRepository;

    private final StrategyOfCoinRepository strategyOfCoinRepository;

    public NewOrderLocaleServiceImpl(NewOrderLocaleRepository newOrderLocaleRepository, NewOrderLocaleMapper newOrderLocaleMapper, NewOrderLocaleSearchRepository newOrderLocaleSearchRepository, StrategyOfCoinRepository strategyOfCoinRepository) {
        this.newOrderLocaleRepository = newOrderLocaleRepository;
        this.newOrderLocaleMapper = newOrderLocaleMapper;
        this.newOrderLocaleSearchRepository = newOrderLocaleSearchRepository;
        this.strategyOfCoinRepository = strategyOfCoinRepository;
    }

    /**
     * Save a newOrderLocale.
     *
     * @param newOrderLocaleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NewOrderLocaleDTO save(NewOrderLocaleDTO newOrderLocaleDTO) {
        log.debug("Request to save NewOrderLocale : {}", newOrderLocaleDTO);
        NewOrderLocale newOrderLocale = newOrderLocaleMapper.toEntity(newOrderLocaleDTO);
        newOrderLocale = newOrderLocaleRepository.save(newOrderLocale);
        NewOrderLocaleDTO result = newOrderLocaleMapper.toDto(newOrderLocale);
        newOrderLocaleSearchRepository.save(newOrderLocale);
        return result;
    }

    /**
     * Get all the newOrderLocales.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NewOrderLocaleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NewOrderLocales");
        return newOrderLocaleRepository.findAll(pageable)
            .map(newOrderLocaleMapper::toDto);
    }

    /**
     * Get one newOrderLocale by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public NewOrderLocaleDTO findOne(Long id) {
        log.debug("Request to get NewOrderLocale : {}", id);
        NewOrderLocale newOrderLocale = newOrderLocaleRepository.findOne(id);
        return newOrderLocaleMapper.toDto(newOrderLocale);
    }

    /**
     * Delete the newOrderLocale by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NewOrderLocale : {}", id);
        newOrderLocaleRepository.delete(id);
        newOrderLocaleSearchRepository.delete(id);
    }

    /**
     * Search for the newOrderLocale corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NewOrderLocaleDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of NewOrderLocales for query {}", query);
        Page<NewOrderLocale> result = newOrderLocaleSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(newOrderLocaleMapper::toDto);
    }


    //En azından symbol orderside ve price kesin girilmiş olmalı
    public NewOrder calculateOrderPropertiesThenPlaceOrder(NewOrderLocale newOrderLocale) {
        StrategyOfCoin strategy = strategyOfCoinRepository.findByCoinName(newOrderLocale.getSymbol());
        if(strategy==null){
            return null;
        }
        BigDecimal temp= null;
        BigDecimal priceDecimal= Utility.convertStringToBigDecimalRoundAsStringLength(newOrderLocale.getPrice());
        switch (newOrderLocale.getSide()){
            case BUY:
                temp=priceDecimal.add(strategy.getSumValue());
                newOrderLocale.setSide(OrderSide.SELL);
                break;
            case SELL:
                temp=priceDecimal.subtract(strategy.getSumValue());
                newOrderLocale.setSide(OrderSide.BUY);
                break;
        }
        newOrderLocale.setQuantity(String.valueOf(strategy.getQuantity()));
        newOrderLocale.setPrice(temp.toString());
        newOrderLocale.setPrice(Utility.scaleString(newOrderLocale.getPrice(),strategy.getScale()));
        fillOrderDefaultPart(newOrderLocale);
        return placeOrderSaveIfSucceed(newOrderLocale);
    }


    private NewOrder placeOrderSaveIfSucceed(NewOrderLocale newOrderLocale) {
        NewOrder newOrder=new NewOrder(newOrderLocale.getSymbol(),newOrderLocale.getSide(),newOrderLocale.getType(),newOrderLocale.getTimeInForce(),newOrderLocale.getQuantity(),newOrderLocale.getPrice());
      return newOrder;
    }

    private void fillOrderDefaultPart(NewOrderLocale newOrderLocale) {
        newOrderLocale.setType(OrderType.LIMIT);
        newOrderLocale.setTimeInForce(TimeInForce.GTC);
    }
}
