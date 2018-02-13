package com.bitcoin.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.bitcoin.service.GiveOrderService;
import com.bitcoin.web.rest.errors.BadRequestAlertException;
import com.bitcoin.web.rest.util.HeaderUtil;
import com.bitcoin.web.rest.util.PaginationUtil;
import com.bitcoin.service.dto.GiveOrderDTO;
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
 * REST controller for managing GiveOrder.
 */
@RestController
@RequestMapping("/api")
public class GiveOrderResource {

    private final Logger log = LoggerFactory.getLogger(GiveOrderResource.class);

    private static final String ENTITY_NAME = "giveOrder";

    private final GiveOrderService giveOrderService;

    public GiveOrderResource(GiveOrderService giveOrderService) {
        this.giveOrderService = giveOrderService;
    }

    /**
     * POST  /give-orders : Create a new giveOrder.
     *
     * @param giveOrderDTO the giveOrderDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new giveOrderDTO, or with status 400 (Bad Request) if the giveOrder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/give-orders")
    @Timed
    public ResponseEntity<GiveOrderDTO> createGiveOrder(@RequestBody GiveOrderDTO giveOrderDTO) throws URISyntaxException {
        log.debug("REST request to save GiveOrder : {}", giveOrderDTO);
        if (giveOrderDTO.getId() != null) {
            throw new BadRequestAlertException("A new giveOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GiveOrderDTO result = giveOrderService.save(giveOrderDTO);
        return ResponseEntity.created(new URI("/api/give-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /give-orders : Updates an existing giveOrder.
     *
     * @param giveOrderDTO the giveOrderDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated giveOrderDTO,
     * or with status 400 (Bad Request) if the giveOrderDTO is not valid,
     * or with status 500 (Internal Server Error) if the giveOrderDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/give-orders")
    @Timed
    public ResponseEntity<GiveOrderDTO> updateGiveOrder(@RequestBody GiveOrderDTO giveOrderDTO) throws URISyntaxException {
        log.debug("REST request to update GiveOrder : {}", giveOrderDTO);
        if (giveOrderDTO.getId() == null) {
            return createGiveOrder(giveOrderDTO);
        }
        GiveOrderDTO result = giveOrderService.save(giveOrderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, giveOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /give-orders : get all the giveOrders.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of giveOrders in body
     */
    @GetMapping("/give-orders")
    @Timed
    public ResponseEntity<List<GiveOrderDTO>> getAllGiveOrders(Pageable pageable) {
        log.debug("REST request to get a page of GiveOrders");
        Page<GiveOrderDTO> page = giveOrderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/give-orders");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /give-orders/:id : get the "id" giveOrder.
     *
     * @param id the id of the giveOrderDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the giveOrderDTO, or with status 404 (Not Found)
     */
    @GetMapping("/give-orders/{id}")
    @Timed
    public ResponseEntity<GiveOrderDTO> getGiveOrder(@PathVariable Long id) {
        log.debug("REST request to get GiveOrder : {}", id);
        GiveOrderDTO giveOrderDTO = giveOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(giveOrderDTO));
    }

    /**
     * DELETE  /give-orders/:id : delete the "id" giveOrder.
     *
     * @param id the id of the giveOrderDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/give-orders/{id}")
    @Timed
    public ResponseEntity<Void> deleteGiveOrder(@PathVariable Long id) {
        log.debug("REST request to delete GiveOrder : {}", id);
        giveOrderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/give-orders?query=:query : search for the giveOrder corresponding
     * to the query.
     *
     * @param query the query of the giveOrder search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/give-orders")
    @Timed
    public ResponseEntity<List<GiveOrderDTO>> searchGiveOrders(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of GiveOrders for query {}", query);
        Page<GiveOrderDTO> page = giveOrderService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/give-orders");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
