package com.bitcoin.web.rest;

import com.bitcoin.BitcoinApp;

import com.bitcoin.domain.GiveOrder;
import com.bitcoin.repository.GiveOrderRepository;
import com.bitcoin.service.GiveOrderService;
import com.bitcoin.repository.search.GiveOrderSearchRepository;
import com.bitcoin.service.dto.GiveOrderDTO;
import com.bitcoin.service.mapper.GiveOrderMapper;
import com.bitcoin.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static com.bitcoin.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the GiveOrderResource REST controller.
 *
 * @see GiveOrderResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BitcoinApp.class)
public class GiveOrderResourceIntTest {

    private static final BigDecimal DEFAULT_QUANTITY = new BigDecimal(1);
    private static final BigDecimal UPDATED_QUANTITY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRICE = new BigDecimal(2);

    private static final Double DEFAULT_PERCENT_OF_BALANCE = 1D;
    private static final Double UPDATED_PERCENT_OF_BALANCE = 2D;

    @Autowired
    private GiveOrderRepository giveOrderRepository;

    @Autowired
    private GiveOrderMapper giveOrderMapper;

    @Autowired
    private GiveOrderService giveOrderService;

    @Autowired
    private GiveOrderSearchRepository giveOrderSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGiveOrderMockMvc;

    private GiveOrder giveOrder;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GiveOrderResource giveOrderResource = new GiveOrderResource(giveOrderService);
        this.restGiveOrderMockMvc = MockMvcBuilders.standaloneSetup(giveOrderResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GiveOrder createEntity(EntityManager em) {
        GiveOrder giveOrder = new GiveOrder()
            .quantity(DEFAULT_QUANTITY)
            .price(DEFAULT_PRICE)
            .percentOfBalance(DEFAULT_PERCENT_OF_BALANCE);
        return giveOrder;
    }

    @Before
    public void initTest() {
        giveOrderSearchRepository.deleteAll();
        giveOrder = createEntity(em);
    }

    @Test
    @Transactional
    public void createGiveOrder() throws Exception {
        int databaseSizeBeforeCreate = giveOrderRepository.findAll().size();

        // Create the GiveOrder
        GiveOrderDTO giveOrderDTO = giveOrderMapper.toDto(giveOrder);
        restGiveOrderMockMvc.perform(post("/api/give-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giveOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the GiveOrder in the database
        List<GiveOrder> giveOrderList = giveOrderRepository.findAll();
        assertThat(giveOrderList).hasSize(databaseSizeBeforeCreate + 1);
        GiveOrder testGiveOrder = giveOrderList.get(giveOrderList.size() - 1);
        assertThat(testGiveOrder.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testGiveOrder.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testGiveOrder.getPercentOfBalance()).isEqualTo(DEFAULT_PERCENT_OF_BALANCE);

        // Validate the GiveOrder in Elasticsearch
        GiveOrder giveOrderEs = giveOrderSearchRepository.findOne(testGiveOrder.getId());
        assertThat(giveOrderEs).isEqualToComparingFieldByField(testGiveOrder);
    }

    @Test
    @Transactional
    public void createGiveOrderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = giveOrderRepository.findAll().size();

        // Create the GiveOrder with an existing ID
        giveOrder.setId(1L);
        GiveOrderDTO giveOrderDTO = giveOrderMapper.toDto(giveOrder);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGiveOrderMockMvc.perform(post("/api/give-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giveOrderDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GiveOrder in the database
        List<GiveOrder> giveOrderList = giveOrderRepository.findAll();
        assertThat(giveOrderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllGiveOrders() throws Exception {
        // Initialize the database
        giveOrderRepository.saveAndFlush(giveOrder);

        // Get all the giveOrderList
        restGiveOrderMockMvc.perform(get("/api/give-orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(giveOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].percentOfBalance").value(hasItem(DEFAULT_PERCENT_OF_BALANCE.doubleValue())));
    }

    @Test
    @Transactional
    public void getGiveOrder() throws Exception {
        // Initialize the database
        giveOrderRepository.saveAndFlush(giveOrder);

        // Get the giveOrder
        restGiveOrderMockMvc.perform(get("/api/give-orders/{id}", giveOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(giveOrder.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.percentOfBalance").value(DEFAULT_PERCENT_OF_BALANCE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingGiveOrder() throws Exception {
        // Get the giveOrder
        restGiveOrderMockMvc.perform(get("/api/give-orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGiveOrder() throws Exception {
        // Initialize the database
        giveOrderRepository.saveAndFlush(giveOrder);
        giveOrderSearchRepository.save(giveOrder);
        int databaseSizeBeforeUpdate = giveOrderRepository.findAll().size();

        // Update the giveOrder
        GiveOrder updatedGiveOrder = giveOrderRepository.findOne(giveOrder.getId());
        // Disconnect from session so that the updates on updatedGiveOrder are not directly saved in db
        em.detach(updatedGiveOrder);
        updatedGiveOrder
            .quantity(UPDATED_QUANTITY)
            .price(UPDATED_PRICE)
            .percentOfBalance(UPDATED_PERCENT_OF_BALANCE);
        GiveOrderDTO giveOrderDTO = giveOrderMapper.toDto(updatedGiveOrder);

        restGiveOrderMockMvc.perform(put("/api/give-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giveOrderDTO)))
            .andExpect(status().isOk());

        // Validate the GiveOrder in the database
        List<GiveOrder> giveOrderList = giveOrderRepository.findAll();
        assertThat(giveOrderList).hasSize(databaseSizeBeforeUpdate);
        GiveOrder testGiveOrder = giveOrderList.get(giveOrderList.size() - 1);
        assertThat(testGiveOrder.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testGiveOrder.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testGiveOrder.getPercentOfBalance()).isEqualTo(UPDATED_PERCENT_OF_BALANCE);

        // Validate the GiveOrder in Elasticsearch
        GiveOrder giveOrderEs = giveOrderSearchRepository.findOne(testGiveOrder.getId());
        assertThat(giveOrderEs).isEqualToComparingFieldByField(testGiveOrder);
    }

    @Test
    @Transactional
    public void updateNonExistingGiveOrder() throws Exception {
        int databaseSizeBeforeUpdate = giveOrderRepository.findAll().size();

        // Create the GiveOrder
        GiveOrderDTO giveOrderDTO = giveOrderMapper.toDto(giveOrder);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGiveOrderMockMvc.perform(put("/api/give-orders")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(giveOrderDTO)))
            .andExpect(status().isCreated());

        // Validate the GiveOrder in the database
        List<GiveOrder> giveOrderList = giveOrderRepository.findAll();
        assertThat(giveOrderList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteGiveOrder() throws Exception {
        // Initialize the database
        giveOrderRepository.saveAndFlush(giveOrder);
        giveOrderSearchRepository.save(giveOrder);
        int databaseSizeBeforeDelete = giveOrderRepository.findAll().size();

        // Get the giveOrder
        restGiveOrderMockMvc.perform(delete("/api/give-orders/{id}", giveOrder.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean giveOrderExistsInEs = giveOrderSearchRepository.exists(giveOrder.getId());
        assertThat(giveOrderExistsInEs).isFalse();

        // Validate the database is empty
        List<GiveOrder> giveOrderList = giveOrderRepository.findAll();
        assertThat(giveOrderList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchGiveOrder() throws Exception {
        // Initialize the database
        giveOrderRepository.saveAndFlush(giveOrder);
        giveOrderSearchRepository.save(giveOrder);

        // Search the giveOrder
        restGiveOrderMockMvc.perform(get("/api/_search/give-orders?query=id:" + giveOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(giveOrder.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].percentOfBalance").value(hasItem(DEFAULT_PERCENT_OF_BALANCE.doubleValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GiveOrder.class);
        GiveOrder giveOrder1 = new GiveOrder();
        giveOrder1.setId(1L);
        GiveOrder giveOrder2 = new GiveOrder();
        giveOrder2.setId(giveOrder1.getId());
        assertThat(giveOrder1).isEqualTo(giveOrder2);
        giveOrder2.setId(2L);
        assertThat(giveOrder1).isNotEqualTo(giveOrder2);
        giveOrder1.setId(null);
        assertThat(giveOrder1).isNotEqualTo(giveOrder2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GiveOrderDTO.class);
        GiveOrderDTO giveOrderDTO1 = new GiveOrderDTO();
        giveOrderDTO1.setId(1L);
        GiveOrderDTO giveOrderDTO2 = new GiveOrderDTO();
        assertThat(giveOrderDTO1).isNotEqualTo(giveOrderDTO2);
        giveOrderDTO2.setId(giveOrderDTO1.getId());
        assertThat(giveOrderDTO1).isEqualTo(giveOrderDTO2);
        giveOrderDTO2.setId(2L);
        assertThat(giveOrderDTO1).isNotEqualTo(giveOrderDTO2);
        giveOrderDTO1.setId(null);
        assertThat(giveOrderDTO1).isNotEqualTo(giveOrderDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(giveOrderMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(giveOrderMapper.fromId(null)).isNull();
    }
}
