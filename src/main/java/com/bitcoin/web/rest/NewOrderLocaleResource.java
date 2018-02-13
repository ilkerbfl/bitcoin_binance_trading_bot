package com.bitcoin.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bitcoin.service.NewOrderLocaleService;
import com.bitcoin.web.rest.errors.BadRequestAlertException;
import com.bitcoin.web.rest.util.HeaderUtil;
import com.bitcoin.web.rest.util.PaginationUtil;
import com.bitcoin.service.dto.NewOrderLocaleDTO;
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
 * REST controller for managing NewOrderLocale.
 */
@RestController
@RequestMapping("/api")
public class NewOrderLocaleResource {

    private final Logger log = LoggerFactory.getLogger(NewOrderLocaleResource.class);

    private static final String ENTITY_NAME = "newOrderLocale";

    private final NewOrderLocaleService newOrderLocaleService;

    public NewOrderLocaleResource(NewOrderLocaleService newOrderLocaleService) {
        this.newOrderLocaleService = newOrderLocaleService;
    }

    /**
     * POST  /new-order-locales : Create a new newOrderLocale.
     *
     * @param newOrderLocaleDTO the newOrderLocaleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new newOrderLocaleDTO, or with status 400 (Bad Request) if the newOrderLocale has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/new-order-locales")
    @Timed
    public ResponseEntity<NewOrderLocaleDTO> createNewOrderLocale(@RequestBody NewOrderLocaleDTO newOrderLocaleDTO) throws URISyntaxException {
        log.debug("REST request to save NewOrderLocale : {}", newOrderLocaleDTO);
        if (newOrderLocaleDTO.getId() != null) {
            throw new BadRequestAlertException("A new newOrderLocale cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NewOrderLocaleDTO result = newOrderLocaleService.save(newOrderLocaleDTO);
        return ResponseEntity.created(new URI("/api/new-order-locales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /new-order-locales : Updates an existing newOrderLocale.
     *
     * @param newOrderLocaleDTO the newOrderLocaleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated newOrderLocaleDTO,
     * or with status 400 (Bad Request) if the newOrderLocaleDTO is not valid,
     * or with status 500 (Internal Server Error) if the newOrderLocaleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/new-order-locales")
    @Timed
    public ResponseEntity<NewOrderLocaleDTO> updateNewOrderLocale(@RequestBody NewOrderLocaleDTO newOrderLocaleDTO) throws URISyntaxException {
        log.debug("REST request to update NewOrderLocale : {}", newOrderLocaleDTO);
        if (newOrderLocaleDTO.getId() == null) {
            return createNewOrderLocale(newOrderLocaleDTO);
        }
        NewOrderLocaleDTO result = newOrderLocaleService.save(newOrderLocaleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, newOrderLocaleDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /new-order-locales : get all the newOrderLocales.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of newOrderLocales in body
     */
    @GetMapping("/new-order-locales")
    @Timed
    public ResponseEntity<List<NewOrderLocaleDTO>> getAllNewOrderLocales(Pageable pageable) {
        log.debug("REST request to get a page of NewOrderLocales");
        Page<NewOrderLocaleDTO> page = newOrderLocaleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/new-order-locales");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /new-order-locales/:id : get the "id" newOrderLocale.
     *
     * @param id the id of the newOrderLocaleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the newOrderLocaleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/new-order-locales/{id}")
    @Timed
    public ResponseEntity<NewOrderLocaleDTO> getNewOrderLocale(@PathVariable Long id) {
        log.debug("REST request to get NewOrderLocale : {}", id);
        NewOrderLocaleDTO newOrderLocaleDTO = newOrderLocaleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(newOrderLocaleDTO));
    }

    /**
     * DELETE  /new-order-locales/:id : delete the "id" newOrderLocale.
     *
     * @param id the id of the newOrderLocaleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/new-order-locales/{id}")
    @Timed
    public ResponseEntity<Void> deleteNewOrderLocale(@PathVariable Long id) {
        log.debug("REST request to delete NewOrderLocale : {}", id);
        newOrderLocaleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/new-order-locales?query=:query : search for the newOrderLocale corresponding
     * to the query.
     *
     * @param query the query of the newOrderLocale search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/new-order-locales")
    @Timed
    public ResponseEntity<List<NewOrderLocaleDTO>> searchNewOrderLocales(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of NewOrderLocales for query {}", query);
        Page<NewOrderLocaleDTO> page = newOrderLocaleService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/new-order-locales");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
