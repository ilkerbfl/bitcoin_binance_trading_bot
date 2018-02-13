package com.bitcoin.web.rest;

import com.bitcoin.BitcoinApp;

import com.bitcoin.domain.StrategyOfCoin;
import com.bitcoin.repository.StrategyOfCoinRepository;
import com.bitcoin.service.StrategyOfCoinService;
import com.bitcoin.repository.search.StrategyOfCoinSearchRepository;
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
 * Test class for the StrategyOfCoinResource REST controller.
 *
 * @see StrategyOfCoinResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BitcoinApp.class)
public class StrategyOfCoinResourceIntTest {

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final BigDecimal DEFAULT_SUM_VALUE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUM_VALUE = new BigDecimal(2);

    private static final String DEFAULT_COIN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COIN_NAME = "BBBBBBBBBB";

    @Autowired
    private StrategyOfCoinRepository strategyOfCoinRepository;

    @Autowired
    private StrategyOfCoinService strategyOfCoinService;

    @Autowired
    private StrategyOfCoinSearchRepository strategyOfCoinSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStrategyOfCoinMockMvc;

    private StrategyOfCoin strategyOfCoin;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StrategyOfCoinResource strategyOfCoinResource = new StrategyOfCoinResource(strategyOfCoinService);
        this.restStrategyOfCoinMockMvc = MockMvcBuilders.standaloneSetup(strategyOfCoinResource)
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
    public static StrategyOfCoin createEntity(EntityManager em) {
        StrategyOfCoin strategyOfCoin = new StrategyOfCoin()
            .quantity(DEFAULT_QUANTITY)
            .sumValue(DEFAULT_SUM_VALUE)
            .coinName(DEFAULT_COIN_NAME);
        return strategyOfCoin;
    }

    @Before
    public void initTest() {
        strategyOfCoinSearchRepository.deleteAll();
        strategyOfCoin = createEntity(em);
    }

    @Test
    @Transactional
    public void createStrategyOfCoin() throws Exception {
        int databaseSizeBeforeCreate = strategyOfCoinRepository.findAll().size();

        // Create the StrategyOfCoin
        restStrategyOfCoinMockMvc.perform(post("/api/strategy-of-coins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(strategyOfCoin)))
            .andExpect(status().isCreated());

        // Validate the StrategyOfCoin in the database
        List<StrategyOfCoin> strategyOfCoinList = strategyOfCoinRepository.findAll();
        assertThat(strategyOfCoinList).hasSize(databaseSizeBeforeCreate + 1);
        StrategyOfCoin testStrategyOfCoin = strategyOfCoinList.get(strategyOfCoinList.size() - 1);
        assertThat(testStrategyOfCoin.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testStrategyOfCoin.getSumValue()).isEqualTo(DEFAULT_SUM_VALUE);
        assertThat(testStrategyOfCoin.getCoinName()).isEqualTo(DEFAULT_COIN_NAME);

        // Validate the StrategyOfCoin in Elasticsearch
        StrategyOfCoin strategyOfCoinEs = strategyOfCoinSearchRepository.findOne(testStrategyOfCoin.getId());
        assertThat(strategyOfCoinEs).isEqualToComparingFieldByField(testStrategyOfCoin);
    }

    @Test
    @Transactional
    public void createStrategyOfCoinWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = strategyOfCoinRepository.findAll().size();

        // Create the StrategyOfCoin with an existing ID
        strategyOfCoin.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStrategyOfCoinMockMvc.perform(post("/api/strategy-of-coins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(strategyOfCoin)))
            .andExpect(status().isBadRequest());

        // Validate the StrategyOfCoin in the database
        List<StrategyOfCoin> strategyOfCoinList = strategyOfCoinRepository.findAll();
        assertThat(strategyOfCoinList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStrategyOfCoins() throws Exception {
        // Initialize the database
        strategyOfCoinRepository.saveAndFlush(strategyOfCoin);

        // Get all the strategyOfCoinList
        restStrategyOfCoinMockMvc.perform(get("/api/strategy-of-coins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(strategyOfCoin.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].sumValue").value(hasItem(DEFAULT_SUM_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].coinName").value(hasItem(DEFAULT_COIN_NAME.toString())));
    }

    @Test
    @Transactional
    public void getStrategyOfCoin() throws Exception {
        // Initialize the database
        strategyOfCoinRepository.saveAndFlush(strategyOfCoin);

        // Get the strategyOfCoin
        restStrategyOfCoinMockMvc.perform(get("/api/strategy-of-coins/{id}", strategyOfCoin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(strategyOfCoin.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.sumValue").value(DEFAULT_SUM_VALUE.intValue()))
            .andExpect(jsonPath("$.coinName").value(DEFAULT_COIN_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStrategyOfCoin() throws Exception {
        // Get the strategyOfCoin
        restStrategyOfCoinMockMvc.perform(get("/api/strategy-of-coins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStrategyOfCoin() throws Exception {
        // Initialize the database
        strategyOfCoinService.save(strategyOfCoin);

        int databaseSizeBeforeUpdate = strategyOfCoinRepository.findAll().size();

        // Update the strategyOfCoin
        StrategyOfCoin updatedStrategyOfCoin = strategyOfCoinRepository.findOne(strategyOfCoin.getId());
        // Disconnect from session so that the updates on updatedStrategyOfCoin are not directly saved in db
        em.detach(updatedStrategyOfCoin);
        updatedStrategyOfCoin
            .quantity(UPDATED_QUANTITY)
            .sumValue(UPDATED_SUM_VALUE)
            .coinName(UPDATED_COIN_NAME);

        restStrategyOfCoinMockMvc.perform(put("/api/strategy-of-coins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStrategyOfCoin)))
            .andExpect(status().isOk());

        // Validate the StrategyOfCoin in the database
        List<StrategyOfCoin> strategyOfCoinList = strategyOfCoinRepository.findAll();
        assertThat(strategyOfCoinList).hasSize(databaseSizeBeforeUpdate);
        StrategyOfCoin testStrategyOfCoin = strategyOfCoinList.get(strategyOfCoinList.size() - 1);
        assertThat(testStrategyOfCoin.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testStrategyOfCoin.getSumValue()).isEqualTo(UPDATED_SUM_VALUE);
        assertThat(testStrategyOfCoin.getCoinName()).isEqualTo(UPDATED_COIN_NAME);

        // Validate the StrategyOfCoin in Elasticsearch
        StrategyOfCoin strategyOfCoinEs = strategyOfCoinSearchRepository.findOne(testStrategyOfCoin.getId());
        assertThat(strategyOfCoinEs).isEqualToComparingFieldByField(testStrategyOfCoin);
    }

    @Test
    @Transactional
    public void updateNonExistingStrategyOfCoin() throws Exception {
        int databaseSizeBeforeUpdate = strategyOfCoinRepository.findAll().size();

        // Create the StrategyOfCoin

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStrategyOfCoinMockMvc.perform(put("/api/strategy-of-coins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(strategyOfCoin)))
            .andExpect(status().isCreated());

        // Validate the StrategyOfCoin in the database
        List<StrategyOfCoin> strategyOfCoinList = strategyOfCoinRepository.findAll();
        assertThat(strategyOfCoinList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteStrategyOfCoin() throws Exception {
        // Initialize the database
        strategyOfCoinService.save(strategyOfCoin);

        int databaseSizeBeforeDelete = strategyOfCoinRepository.findAll().size();

        // Get the strategyOfCoin
        restStrategyOfCoinMockMvc.perform(delete("/api/strategy-of-coins/{id}", strategyOfCoin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean strategyOfCoinExistsInEs = strategyOfCoinSearchRepository.exists(strategyOfCoin.getId());
        assertThat(strategyOfCoinExistsInEs).isFalse();

        // Validate the database is empty
        List<StrategyOfCoin> strategyOfCoinList = strategyOfCoinRepository.findAll();
        assertThat(strategyOfCoinList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchStrategyOfCoin() throws Exception {
        // Initialize the database
        strategyOfCoinService.save(strategyOfCoin);

        // Search the strategyOfCoin
        restStrategyOfCoinMockMvc.perform(get("/api/_search/strategy-of-coins?query=id:" + strategyOfCoin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(strategyOfCoin.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].sumValue").value(hasItem(DEFAULT_SUM_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].coinName").value(hasItem(DEFAULT_COIN_NAME.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StrategyOfCoin.class);
        StrategyOfCoin strategyOfCoin1 = new StrategyOfCoin();
        strategyOfCoin1.setId(1L);
        StrategyOfCoin strategyOfCoin2 = new StrategyOfCoin();
        strategyOfCoin2.setId(strategyOfCoin1.getId());
        assertThat(strategyOfCoin1).isEqualTo(strategyOfCoin2);
        strategyOfCoin2.setId(2L);
        assertThat(strategyOfCoin1).isNotEqualTo(strategyOfCoin2);
        strategyOfCoin1.setId(null);
        assertThat(strategyOfCoin1).isNotEqualTo(strategyOfCoin2);
    }
}
