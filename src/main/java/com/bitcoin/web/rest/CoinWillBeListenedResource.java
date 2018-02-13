package com.bitcoin.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bitcoin.service.CoinWillBeListenedService;
import com.bitcoin.web.rest.errors.BadRequestAlertException;
import com.bitcoin.web.rest.util.HeaderUtil;
import com.bitcoin.web.rest.util.PaginationUtil;
import com.bitcoin.service.dto.CoinWillBeListenedDTO;
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
 * REST controller for managing CoinWillBeListened.
 */
@RestController
@RequestMapping("/api")
public class CoinWillBeListenedResource {

    private final Logger log = LoggerFactory.getLogger(CoinWillBeListenedResource.class);

    private static final String ENTITY_NAME = "coinWillBeListened";

    private final CoinWillBeListenedService coinWillBeListenedService;

    public CoinWillBeListenedResource(CoinWillBeListenedService coinWillBeListenedService) {
        this.coinWillBeListenedService = coinWillBeListenedService;
    }

    /**
     * POST  /coin-will-be-listeneds : Create a new coinWillBeListened.
     *
     * @param coinWillBeListenedDTO the coinWillBeListenedDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new coinWillBeListenedDTO, or with status 400 (Bad Request) if the coinWillBeListened has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/coin-will-be-listeneds")
    @Timed
    public ResponseEntity<CoinWillBeListenedDTO> createCoinWillBeListened(@RequestBody CoinWillBeListenedDTO coinWillBeListenedDTO) throws URISyntaxException {
        log.debug("REST request to save CoinWillBeListened : {}", coinWillBeListenedDTO);
        if (coinWillBeListenedDTO.getId() != null) {
            throw new BadRequestAlertException("A new coinWillBeListened cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CoinWillBeListenedDTO result = coinWillBeListenedService.save(coinWillBeListenedDTO);
        return ResponseEntity.created(new URI("/api/coin-will-be-listeneds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /coin-will-be-listeneds : Updates an existing coinWillBeListened.
     *
     * @param coinWillBeListenedDTO the coinWillBeListenedDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated coinWillBeListenedDTO,
     * or with status 400 (Bad Request) if the coinWillBeListenedDTO is not valid,
     * or with status 500 (Internal Server Error) if the coinWillBeListenedDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/coin-will-be-listeneds")
    @Timed
    public ResponseEntity<CoinWillBeListenedDTO> updateCoinWillBeListened(@RequestBody CoinWillBeListenedDTO coinWillBeListenedDTO) throws URISyntaxException {
        log.debug("REST request to update CoinWillBeListened : {}", coinWillBeListenedDTO);
        if (coinWillBeListenedDTO.getId() == null) {
            return createCoinWillBeListened(coinWillBeListenedDTO);
        }
        CoinWillBeListenedDTO result = coinWillBeListenedService.save(coinWillBeListenedDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, coinWillBeListenedDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /coin-will-be-listeneds : get all the coinWillBeListeneds.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of coinWillBeListeneds in body
     */
    @GetMapping("/coin-will-be-listeneds")
    @Timed
    public ResponseEntity<List<CoinWillBeListenedDTO>> getAllCoinWillBeListeneds(Pageable pageable) {
        log.debug("REST request to get a page of CoinWillBeListeneds");
        Page<CoinWillBeListenedDTO> page = coinWillBeListenedService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/coin-will-be-listeneds");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /coin-will-be-listeneds/:id : get the "id" coinWillBeListened.
     *
     * @param id the id of the coinWillBeListenedDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the coinWillBeListenedDTO, or with status 404 (Not Found)
     */
    @GetMapping("/coin-will-be-listeneds/{id}")
    @Timed
    public ResponseEntity<CoinWillBeListenedDTO> getCoinWillBeListened(@PathVariable Long id) {
        log.debug("REST request to get CoinWillBeListened : {}", id);
        CoinWillBeListenedDTO coinWillBeListenedDTO = coinWillBeListenedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(coinWillBeListenedDTO));
    }

    /**
     * DELETE  /coin-will-be-listeneds/:id : delete the "id" coinWillBeListened.
     *
     * @param id the id of the coinWillBeListenedDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/coin-will-be-listeneds/{id}")
    @Timed
    public ResponseEntity<Void> deleteCoinWillBeListened(@PathVariable Long id) {
        log.debug("REST request to delete CoinWillBeListened : {}", id);
        coinWillBeListenedService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/coin-will-be-listeneds?query=:query : search for the coinWillBeListened corresponding
     * to the query.
     *
     * @param query the query of the coinWillBeListened search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/coin-will-be-listeneds")
    @Timed
    public ResponseEntity<List<CoinWillBeListenedDTO>> searchCoinWillBeListeneds(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of CoinWillBeListeneds for query {}", query);
        Page<CoinWillBeListenedDTO> page = coinWillBeListenedService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/coin-will-be-listeneds");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
