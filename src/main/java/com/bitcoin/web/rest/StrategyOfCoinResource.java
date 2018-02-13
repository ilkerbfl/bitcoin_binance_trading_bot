package com.bitcoin.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bitcoin.domain.StrategyOfCoin;
import com.bitcoin.service.StrategyOfCoinService;
import com.bitcoin.web.rest.errors.BadRequestAlertException;
import com.bitcoin.web.rest.util.HeaderUtil;
import com.bitcoin.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing StrategyOfCoin.
 */
@RestController
@RequestMapping("/api")
public class StrategyOfCoinResource {

    private final Logger log = LoggerFactory.getLogger(StrategyOfCoinResource.class);

    private static final String ENTITY_NAME = "strategyOfCoin";

    private final StrategyOfCoinService strategyOfCoinService;

    public StrategyOfCoinResource(StrategyOfCoinService strategyOfCoinService) {
        this.strategyOfCoinService = strategyOfCoinService;
    }

    /**
     * POST  /strategy-of-coins : Create a new strategyOfCoin.
     *
     * @param strategyOfCoin the strategyOfCoin to create
     * @return the ResponseEntity with status 201 (Created) and with body the new strategyOfCoin, or with status 400 (Bad Request) if the strategyOfCoin has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/strategy-of-coins")
    @Timed
    public ResponseEntity<StrategyOfCoin> createStrategyOfCoin(@RequestBody StrategyOfCoin strategyOfCoin) throws URISyntaxException {
        log.debug("REST request to save StrategyOfCoin : {}", strategyOfCoin);
        if (strategyOfCoin.getId() != null) {
            throw new BadRequestAlertException("A new strategyOfCoin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StrategyOfCoin result = strategyOfCoinService.save(strategyOfCoin);
        return ResponseEntity.created(new URI("/api/strategy-of-coins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /strategy-of-coins : Updates an existing strategyOfCoin.
     *
     * @param strategyOfCoin the strategyOfCoin to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated strategyOfCoin,
     * or with status 400 (Bad Request) if the strategyOfCoin is not valid,
     * or with status 500 (Internal Server Error) if the strategyOfCoin couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/strategy-of-coins")
    @Timed
    public ResponseEntity<StrategyOfCoin> updateStrategyOfCoin(@RequestBody StrategyOfCoin strategyOfCoin) throws URISyntaxException {
        log.debug("REST request to update StrategyOfCoin : {}", strategyOfCoin);
        if (strategyOfCoin.getId() == null) {
            return createStrategyOfCoin(strategyOfCoin);
        }
        StrategyOfCoin result = strategyOfCoinService.save(strategyOfCoin);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, strategyOfCoin.getId().toString()))
            .body(result);
    }

    /**
     * GET  /strategy-of-coins : get all the strategyOfCoins.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of strategyOfCoins in body
     */
    @GetMapping("/strategy-of-coins")
    @Timed
    public ResponseEntity<List<StrategyOfCoin>> getAllStrategyOfCoins(Pageable pageable) {
        log.debug("REST request to get a page of StrategyOfCoins");
        Page<StrategyOfCoin> page = strategyOfCoinService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/strategy-of-coins");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /strategy-of-coins/:id : get the "id" strategyOfCoin.
     *
     * @param id the id of the strategyOfCoin to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the strategyOfCoin, or with status 404 (Not Found)
     */
    @GetMapping("/strategy-of-coins/{id}")
    @Timed
    public ResponseEntity<StrategyOfCoin> getStrategyOfCoin(@PathVariable Long id) {
        log.debug("REST request to get StrategyOfCoin : {}", id);
        StrategyOfCoin strategyOfCoin = strategyOfCoinService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(strategyOfCoin));
    }

    /**
     * DELETE  /strategy-of-coins/:id : delete the "id" strategyOfCoin.
     *
     * @param id the id of the strategyOfCoin to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/strategy-of-coins/{id}")
    @Timed
    public ResponseEntity<Void> deleteStrategyOfCoin(@PathVariable Long id) {
        log.debug("REST request to delete StrategyOfCoin : {}", id);
        strategyOfCoinService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/strategy-of-coins?query=:query : search for the strategyOfCoin corresponding
     * to the query.
     *
     * @param query the query of the strategyOfCoin search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/strategy-of-coins")
    @Timed
    public ResponseEntity<List<StrategyOfCoin>> searchStrategyOfCoins(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of StrategyOfCoins for query {}", query);
        Page<StrategyOfCoin> page = strategyOfCoinService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/strategy-of-coins");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
