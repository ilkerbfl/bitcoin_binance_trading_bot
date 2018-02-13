package com.bitcoin.web.rest;

import com.binance.api.client.domain.OrderSide;
import com.binance.api.client.domain.OrderType;
import com.binance.api.client.domain.TimeInForce;
import com.bitcoin.BitcoinApp;

import com.bitcoin.domain.NewOrderLocale;
import com.bitcoin.repository.NewOrderLocaleRepository;
import com.bitcoin.service.NewOrderLocaleService;
import com.bitcoin.repository.search.NewOrderLocaleSearchRepository;
import com.bitcoin.service.dto.NewOrderLocaleDTO;
import com.bitcoin.service.mapper.NewOrderLocaleMapper;
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
import java.util.List;

import static com.bitcoin.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.bitcoin.domain.enumeration.OrderSideLocale;
import com.bitcoin.domain.enumeration.OrderTypeLocale;
import com.bitcoin.domain.enumeration.TimeInForceLocale;
/**
 * Test class for the NewOrderLocaleResource REST controller.
 *
 * @see NewOrderLocaleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BitcoinApp.class)
public class NewOrderLocaleResourceIntTest {

    private static final String DEFAULT_SYMBOL = "AAAAAAAAAA";
    private static final String UPDATED_SYMBOL = "BBBBBBBBBB";

    private static final OrderSide DEFAULT_SIDE = OrderSide.BUY;
    private static final OrderSide UPDATED_SIDE = OrderSide.SELL;

    private static final OrderType DEFAULT_TYPE = OrderType.LIMIT;
    private static final OrderType UPDATED_TYPE = OrderType.MARKET;

    private static final TimeInForce DEFAULT_TIME_IN_FORCE = TimeInForce.GTC;
    private static final TimeInForce UPDATED_TIME_IN_FORCE = TimeInForce.IOC;

    private static final String DEFAULT_QUANTITY = "AAAAAAAAAA";
    private static final String UPDATED_QUANTITY = "BBBBBBBBBB";

    private static final String DEFAULT_PRICE = "AAAAAAAAAA";
    private static final String UPDATED_PRICE = "BBBBBBBBBB";

    private static final String DEFAULT_NEW_CLIENT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_NEW_CLIENT_ORDER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_STOP_PRICE = "AAAAAAAAAA";
    private static final String UPDATED_STOP_PRICE = "BBBBBBBBBB";

    private static final String DEFAULT_ICEBERG_QTY = "AAAAAAAAAA";
    private static final String UPDATED_ICEBERG_QTY = "BBBBBBBBBB";

    private static final Long DEFAULT_RECV_WINDOW = 1L;
    private static final Long UPDATED_RECV_WINDOW = 2L;

    private static final Long DEFAULT_TIMESTAMP = 1L;
    private static final Long UPDATED_TIMESTAMP = 2L;

    @Autowired
    private NewOrderLocaleRepository newOrderLocaleRepository;

    @Autowired
    private NewOrderLocaleMapper newOrderLocaleMapper;

    @Autowired
    private NewOrderLocaleService newOrderLocaleService;

    @Autowired
    private NewOrderLocaleSearchRepository newOrderLocaleSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNewOrderLocaleMockMvc;

    private NewOrderLocale newOrderLocale;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NewOrderLocaleResource newOrderLocaleResource = new NewOrderLocaleResource(newOrderLocaleService);
        this.restNewOrderLocaleMockMvc = MockMvcBuilders.standaloneSetup(newOrderLocaleResource)
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
    public static NewOrderLocale createEntity(EntityManager em) {
        NewOrderLocale newOrderLocale = new NewOrderLocale()
            .symbol(DEFAULT_SYMBOL)
//            .side(DEFAULT_SIDE)
//            .type(DEFAULT_TYPE)
//            .timeInForce(DEFAULT_TIME_IN_FORCE)
            .quantity(DEFAULT_QUANTITY)
            .price(DEFAULT_PRICE)
            .newClientOrderId(DEFAULT_NEW_CLIENT_ORDER_ID)
            .stopPrice(DEFAULT_STOP_PRICE)
            .icebergQty(DEFAULT_ICEBERG_QTY)
            .recvWindow(DEFAULT_RECV_WINDOW)
            .timestamp(DEFAULT_TIMESTAMP);
        return newOrderLocale;
    }

    @Before
    public void initTest() {
        newOrderLocaleSearchRepository.deleteAll();
        newOrderLocale = createEntity(em);
    }

    @Test
    @Transactional
    public void createNewOrderLocale() throws Exception {
        int databaseSizeBeforeCreate = newOrderLocaleRepository.findAll().size();

        // Create the NewOrderLocale
        NewOrderLocaleDTO newOrderLocaleDTO = newOrderLocaleMapper.toDto(newOrderLocale);
        restNewOrderLocaleMockMvc.perform(post("/api/new-order-locales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(newOrderLocaleDTO)))
            .andExpect(status().isCreated());

        // Validate the NewOrderLocale in the database
        List<NewOrderLocale> newOrderLocaleList = newOrderLocaleRepository.findAll();
        assertThat(newOrderLocaleList).hasSize(databaseSizeBeforeCreate + 1);
        NewOrderLocale testNewOrderLocale = newOrderLocaleList.get(newOrderLocaleList.size() - 1);
        assertThat(testNewOrderLocale.getSymbol()).isEqualTo(DEFAULT_SYMBOL);
//        assertThat(testNewOrderLocale.getSide()).isEqualTo(DEFAULT_SIDE);
//        assertThat(testNewOrderLocale.getType()).isEqualTo(DEFAULT_TYPE);
//        assertThat(testNewOrderLocale.getTimeInForce()).isEqualTo(DEFAULT_TIME_IN_FORCE);
        assertThat(testNewOrderLocale.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testNewOrderLocale.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testNewOrderLocale.getNewClientOrderId()).isEqualTo(DEFAULT_NEW_CLIENT_ORDER_ID);
        assertThat(testNewOrderLocale.getStopPrice()).isEqualTo(DEFAULT_STOP_PRICE);
        assertThat(testNewOrderLocale.getIcebergQty()).isEqualTo(DEFAULT_ICEBERG_QTY);
        assertThat(testNewOrderLocale.getRecvWindow()).isEqualTo(DEFAULT_RECV_WINDOW);
        assertThat(testNewOrderLocale.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);

        // Validate the NewOrderLocale in Elasticsearch
        NewOrderLocale newOrderLocaleEs = newOrderLocaleSearchRepository.findOne(testNewOrderLocale.getId());
        assertThat(newOrderLocaleEs).isEqualToComparingFieldByField(testNewOrderLocale);
    }

    @Test
    @Transactional
    public void createNewOrderLocaleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = newOrderLocaleRepository.findAll().size();

        // Create the NewOrderLocale with an existing ID
        newOrderLocale.setId(1L);
        NewOrderLocaleDTO newOrderLocaleDTO = newOrderLocaleMapper.toDto(newOrderLocale);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNewOrderLocaleMockMvc.perform(post("/api/new-order-locales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(newOrderLocaleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NewOrderLocale in the database
        List<NewOrderLocale> newOrderLocaleList = newOrderLocaleRepository.findAll();
        assertThat(newOrderLocaleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNewOrderLocales() throws Exception {
        // Initialize the database
        newOrderLocaleRepository.saveAndFlush(newOrderLocale);

        // Get all the newOrderLocaleList
        restNewOrderLocaleMockMvc.perform(get("/api/new-order-locales?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(newOrderLocale.getId().intValue())))
            .andExpect(jsonPath("$.[*].symbol").value(hasItem(DEFAULT_SYMBOL.toString())))
            .andExpect(jsonPath("$.[*].side").value(hasItem(DEFAULT_SIDE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].timeInForce").value(hasItem(DEFAULT_TIME_IN_FORCE.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.toString())))
            .andExpect(jsonPath("$.[*].newClientOrderId").value(hasItem(DEFAULT_NEW_CLIENT_ORDER_ID.toString())))
            .andExpect(jsonPath("$.[*].stopPrice").value(hasItem(DEFAULT_STOP_PRICE.toString())))
            .andExpect(jsonPath("$.[*].icebergQty").value(hasItem(DEFAULT_ICEBERG_QTY.toString())))
            .andExpect(jsonPath("$.[*].recvWindow").value(hasItem(DEFAULT_RECV_WINDOW.intValue())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.intValue())));
    }

    @Test
    @Transactional
    public void getNewOrderLocale() throws Exception {
        // Initialize the database
        newOrderLocaleRepository.saveAndFlush(newOrderLocale);

        // Get the newOrderLocale
        restNewOrderLocaleMockMvc.perform(get("/api/new-order-locales/{id}", newOrderLocale.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(newOrderLocale.getId().intValue()))
            .andExpect(jsonPath("$.symbol").value(DEFAULT_SYMBOL.toString()))
            .andExpect(jsonPath("$.side").value(DEFAULT_SIDE.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.timeInForce").value(DEFAULT_TIME_IN_FORCE.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.toString()))
            .andExpect(jsonPath("$.newClientOrderId").value(DEFAULT_NEW_CLIENT_ORDER_ID.toString()))
            .andExpect(jsonPath("$.stopPrice").value(DEFAULT_STOP_PRICE.toString()))
            .andExpect(jsonPath("$.icebergQty").value(DEFAULT_ICEBERG_QTY.toString()))
            .andExpect(jsonPath("$.recvWindow").value(DEFAULT_RECV_WINDOW.intValue()))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingNewOrderLocale() throws Exception {
        // Get the newOrderLocale
        restNewOrderLocaleMockMvc.perform(get("/api/new-order-locales/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNewOrderLocale() throws Exception {
        // Initialize the database
        newOrderLocaleRepository.saveAndFlush(newOrderLocale);
        newOrderLocaleSearchRepository.save(newOrderLocale);
        int databaseSizeBeforeUpdate = newOrderLocaleRepository.findAll().size();

        // Update the newOrderLocale
        NewOrderLocale updatedNewOrderLocale = newOrderLocaleRepository.findOne(newOrderLocale.getId());
        // Disconnect from session so that the updates on updatedNewOrderLocale are not directly saved in db
        em.detach(updatedNewOrderLocale);
        updatedNewOrderLocale
            .symbol(UPDATED_SYMBOL)
//            .side(UPDATED_SIDE)
//            .type(UPDATED_TYPE)
//            .timeInForce(UPDATED_TIME_IN_FORCE)
            .quantity(UPDATED_QUANTITY)
            .price(UPDATED_PRICE)
            .newClientOrderId(UPDATED_NEW_CLIENT_ORDER_ID)
            .stopPrice(UPDATED_STOP_PRICE)
            .icebergQty(UPDATED_ICEBERG_QTY)
            .recvWindow(UPDATED_RECV_WINDOW)
            .timestamp(UPDATED_TIMESTAMP);
        NewOrderLocaleDTO newOrderLocaleDTO = newOrderLocaleMapper.toDto(updatedNewOrderLocale);

        restNewOrderLocaleMockMvc.perform(put("/api/new-order-locales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(newOrderLocaleDTO)))
            .andExpect(status().isOk());

        // Validate the NewOrderLocale in the database
        List<NewOrderLocale> newOrderLocaleList = newOrderLocaleRepository.findAll();
        assertThat(newOrderLocaleList).hasSize(databaseSizeBeforeUpdate);
        NewOrderLocale testNewOrderLocale = newOrderLocaleList.get(newOrderLocaleList.size() - 1);
        assertThat(testNewOrderLocale.getSymbol()).isEqualTo(UPDATED_SYMBOL);
//        assertThat(testNewOrderLocale.getSide()).isEqualTo(UPDATED_SIDE);
//        assertThat(testNewOrderLocale.getType()).isEqualTo(UPDATED_TYPE);
//        assertThat(testNewOrderLocale.getTimeInForce()).isEqualTo(UPDATED_TIME_IN_FORCE);
        assertThat(testNewOrderLocale.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testNewOrderLocale.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testNewOrderLocale.getNewClientOrderId()).isEqualTo(UPDATED_NEW_CLIENT_ORDER_ID);
        assertThat(testNewOrderLocale.getStopPrice()).isEqualTo(UPDATED_STOP_PRICE);
        assertThat(testNewOrderLocale.getIcebergQty()).isEqualTo(UPDATED_ICEBERG_QTY);
        assertThat(testNewOrderLocale.getRecvWindow()).isEqualTo(UPDATED_RECV_WINDOW);
        assertThat(testNewOrderLocale.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);

        // Validate the NewOrderLocale in Elasticsearch
        NewOrderLocale newOrderLocaleEs = newOrderLocaleSearchRepository.findOne(testNewOrderLocale.getId());
        assertThat(newOrderLocaleEs).isEqualToComparingFieldByField(testNewOrderLocale);
    }

    @Test
    @Transactional
    public void updateNonExistingNewOrderLocale() throws Exception {
        int databaseSizeBeforeUpdate = newOrderLocaleRepository.findAll().size();

        // Create the NewOrderLocale
        NewOrderLocaleDTO newOrderLocaleDTO = newOrderLocaleMapper.toDto(newOrderLocale);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNewOrderLocaleMockMvc.perform(put("/api/new-order-locales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(newOrderLocaleDTO)))
            .andExpect(status().isCreated());

        // Validate the NewOrderLocale in the database
        List<NewOrderLocale> newOrderLocaleList = newOrderLocaleRepository.findAll();
        assertThat(newOrderLocaleList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteNewOrderLocale() throws Exception {
        // Initialize the database
        newOrderLocaleRepository.saveAndFlush(newOrderLocale);
        newOrderLocaleSearchRepository.save(newOrderLocale);
        int databaseSizeBeforeDelete = newOrderLocaleRepository.findAll().size();

        // Get the newOrderLocale
        restNewOrderLocaleMockMvc.perform(delete("/api/new-order-locales/{id}", newOrderLocale.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean newOrderLocaleExistsInEs = newOrderLocaleSearchRepository.exists(newOrderLocale.getId());
        assertThat(newOrderLocaleExistsInEs).isFalse();

        // Validate the database is empty
        List<NewOrderLocale> newOrderLocaleList = newOrderLocaleRepository.findAll();
        assertThat(newOrderLocaleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchNewOrderLocale() throws Exception {
        // Initialize the database
        newOrderLocaleRepository.saveAndFlush(newOrderLocale);
        newOrderLocaleSearchRepository.save(newOrderLocale);

        // Search the newOrderLocale
        restNewOrderLocaleMockMvc.perform(get("/api/_search/new-order-locales?query=id:" + newOrderLocale.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(newOrderLocale.getId().intValue())))
            .andExpect(jsonPath("$.[*].symbol").value(hasItem(DEFAULT_SYMBOL.toString())))
            .andExpect(jsonPath("$.[*].side").value(hasItem(DEFAULT_SIDE.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].timeInForce").value(hasItem(DEFAULT_TIME_IN_FORCE.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.toString())))
            .andExpect(jsonPath("$.[*].newClientOrderId").value(hasItem(DEFAULT_NEW_CLIENT_ORDER_ID.toString())))
            .andExpect(jsonPath("$.[*].stopPrice").value(hasItem(DEFAULT_STOP_PRICE.toString())))
            .andExpect(jsonPath("$.[*].icebergQty").value(hasItem(DEFAULT_ICEBERG_QTY.toString())))
            .andExpect(jsonPath("$.[*].recvWindow").value(hasItem(DEFAULT_RECV_WINDOW.intValue())))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewOrderLocale.class);
        NewOrderLocale newOrderLocale1 = new NewOrderLocale();
        newOrderLocale1.setId(1L);
        NewOrderLocale newOrderLocale2 = new NewOrderLocale();
        newOrderLocale2.setId(newOrderLocale1.getId());
        assertThat(newOrderLocale1).isEqualTo(newOrderLocale2);
        newOrderLocale2.setId(2L);
        assertThat(newOrderLocale1).isNotEqualTo(newOrderLocale2);
        newOrderLocale1.setId(null);
        assertThat(newOrderLocale1).isNotEqualTo(newOrderLocale2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NewOrderLocaleDTO.class);
        NewOrderLocaleDTO newOrderLocaleDTO1 = new NewOrderLocaleDTO();
        newOrderLocaleDTO1.setId(1L);
        NewOrderLocaleDTO newOrderLocaleDTO2 = new NewOrderLocaleDTO();
        assertThat(newOrderLocaleDTO1).isNotEqualTo(newOrderLocaleDTO2);
        newOrderLocaleDTO2.setId(newOrderLocaleDTO1.getId());
        assertThat(newOrderLocaleDTO1).isEqualTo(newOrderLocaleDTO2);
        newOrderLocaleDTO2.setId(2L);
        assertThat(newOrderLocaleDTO1).isNotEqualTo(newOrderLocaleDTO2);
        newOrderLocaleDTO1.setId(null);
        assertThat(newOrderLocaleDTO1).isNotEqualTo(newOrderLocaleDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(newOrderLocaleMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(newOrderLocaleMapper.fromId(null)).isNull();
    }
}
