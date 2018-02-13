package com.bitcoin.web.rest;

import com.bitcoin.BitcoinApp;

import com.bitcoin.domain.CoinWillBeListened;
import com.bitcoin.repository.CoinWillBeListenedRepository;
import com.bitcoin.service.CoinWillBeListenedService;
import com.bitcoin.repository.search.CoinWillBeListenedSearchRepository;
import com.bitcoin.service.dto.CoinWillBeListenedDTO;
import com.bitcoin.service.mapper.CoinWillBeListenedMapper;
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

/**
 * Test class for the CoinWillBeListenedResource REST controller.
 *
 * @see CoinWillBeListenedResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BitcoinApp.class)
public class CoinWillBeListenedResourceIntTest {

    private static final String DEFAULT_SYMBOL = "AAAAAAAAAA";
    private static final String UPDATED_SYMBOL = "BBBBBBBBBB";

    @Autowired
    private CoinWillBeListenedRepository coinWillBeListenedRepository;

    @Autowired
    private CoinWillBeListenedMapper coinWillBeListenedMapper;

    @Autowired
    private CoinWillBeListenedService coinWillBeListenedService;

    @Autowired
    private CoinWillBeListenedSearchRepository coinWillBeListenedSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCoinWillBeListenedMockMvc;

    private CoinWillBeListened coinWillBeListened;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CoinWillBeListenedResource coinWillBeListenedResource = new CoinWillBeListenedResource(coinWillBeListenedService);
        this.restCoinWillBeListenedMockMvc = MockMvcBuilders.standaloneSetup(coinWillBeListenedResource)
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
    public static CoinWillBeListened createEntity(EntityManager em) {
        CoinWillBeListened coinWillBeListened = new CoinWillBeListened()
            .symbol(DEFAULT_SYMBOL);
        return coinWillBeListened;
    }

    @Before
    public void initTest() {
        coinWillBeListenedSearchRepository.deleteAll();
        coinWillBeListened = createEntity(em);
    }

    @Test
    @Transactional
    public void createCoinWillBeListened() throws Exception {
        int databaseSizeBeforeCreate = coinWillBeListenedRepository.findAll().size();

        // Create the CoinWillBeListened
        CoinWillBeListenedDTO coinWillBeListenedDTO = coinWillBeListenedMapper.toDto(coinWillBeListened);
        restCoinWillBeListenedMockMvc.perform(post("/api/coin-will-be-listeneds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coinWillBeListenedDTO)))
            .andExpect(status().isCreated());

        // Validate the CoinWillBeListened in the database
        List<CoinWillBeListened> coinWillBeListenedList = coinWillBeListenedRepository.findAll();
        assertThat(coinWillBeListenedList).hasSize(databaseSizeBeforeCreate + 1);
        CoinWillBeListened testCoinWillBeListened = coinWillBeListenedList.get(coinWillBeListenedList.size() - 1);
        assertThat(testCoinWillBeListened.getSymbol()).isEqualTo(DEFAULT_SYMBOL);

        // Validate the CoinWillBeListened in Elasticsearch
        CoinWillBeListened coinWillBeListenedEs = coinWillBeListenedSearchRepository.findOne(testCoinWillBeListened.getId());
        assertThat(coinWillBeListenedEs).isEqualToComparingFieldByField(testCoinWillBeListened);
    }

    @Test
    @Transactional
    public void createCoinWillBeListenedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = coinWillBeListenedRepository.findAll().size();

        // Create the CoinWillBeListened with an existing ID
        coinWillBeListened.setId(1L);
        CoinWillBeListenedDTO coinWillBeListenedDTO = coinWillBeListenedMapper.toDto(coinWillBeListened);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoinWillBeListenedMockMvc.perform(post("/api/coin-will-be-listeneds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coinWillBeListenedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CoinWillBeListened in the database
        List<CoinWillBeListened> coinWillBeListenedList = coinWillBeListenedRepository.findAll();
        assertThat(coinWillBeListenedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCoinWillBeListeneds() throws Exception {
        // Initialize the database
        coinWillBeListenedRepository.saveAndFlush(coinWillBeListened);

        // Get all the coinWillBeListenedList
        restCoinWillBeListenedMockMvc.perform(get("/api/coin-will-be-listeneds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coinWillBeListened.getId().intValue())))
            .andExpect(jsonPath("$.[*].symbol").value(hasItem(DEFAULT_SYMBOL.toString())));
    }

    @Test
    @Transactional
    public void getCoinWillBeListened() throws Exception {
        // Initialize the database
        coinWillBeListenedRepository.saveAndFlush(coinWillBeListened);

        // Get the coinWillBeListened
        restCoinWillBeListenedMockMvc.perform(get("/api/coin-will-be-listeneds/{id}", coinWillBeListened.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(coinWillBeListened.getId().intValue()))
            .andExpect(jsonPath("$.symbol").value(DEFAULT_SYMBOL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCoinWillBeListened() throws Exception {
        // Get the coinWillBeListened
        restCoinWillBeListenedMockMvc.perform(get("/api/coin-will-be-listeneds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCoinWillBeListened() throws Exception {
        // Initialize the database
        coinWillBeListenedRepository.saveAndFlush(coinWillBeListened);
        coinWillBeListenedSearchRepository.save(coinWillBeListened);
        int databaseSizeBeforeUpdate = coinWillBeListenedRepository.findAll().size();

        // Update the coinWillBeListened
        CoinWillBeListened updatedCoinWillBeListened = coinWillBeListenedRepository.findOne(coinWillBeListened.getId());
        // Disconnect from session so that the updates on updatedCoinWillBeListened are not directly saved in db
        em.detach(updatedCoinWillBeListened);
        updatedCoinWillBeListened
            .symbol(UPDATED_SYMBOL);
        CoinWillBeListenedDTO coinWillBeListenedDTO = coinWillBeListenedMapper.toDto(updatedCoinWillBeListened);

        restCoinWillBeListenedMockMvc.perform(put("/api/coin-will-be-listeneds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coinWillBeListenedDTO)))
            .andExpect(status().isOk());

        // Validate the CoinWillBeListened in the database
        List<CoinWillBeListened> coinWillBeListenedList = coinWillBeListenedRepository.findAll();
        assertThat(coinWillBeListenedList).hasSize(databaseSizeBeforeUpdate);
        CoinWillBeListened testCoinWillBeListened = coinWillBeListenedList.get(coinWillBeListenedList.size() - 1);
        assertThat(testCoinWillBeListened.getSymbol()).isEqualTo(UPDATED_SYMBOL);

        // Validate the CoinWillBeListened in Elasticsearch
        CoinWillBeListened coinWillBeListenedEs = coinWillBeListenedSearchRepository.findOne(testCoinWillBeListened.getId());
        assertThat(coinWillBeListenedEs).isEqualToComparingFieldByField(testCoinWillBeListened);
    }

    @Test
    @Transactional
    public void updateNonExistingCoinWillBeListened() throws Exception {
        int databaseSizeBeforeUpdate = coinWillBeListenedRepository.findAll().size();

        // Create the CoinWillBeListened
        CoinWillBeListenedDTO coinWillBeListenedDTO = coinWillBeListenedMapper.toDto(coinWillBeListened);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCoinWillBeListenedMockMvc.perform(put("/api/coin-will-be-listeneds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(coinWillBeListenedDTO)))
            .andExpect(status().isCreated());

        // Validate the CoinWillBeListened in the database
        List<CoinWillBeListened> coinWillBeListenedList = coinWillBeListenedRepository.findAll();
        assertThat(coinWillBeListenedList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCoinWillBeListened() throws Exception {
        // Initialize the database
        coinWillBeListenedRepository.saveAndFlush(coinWillBeListened);
        coinWillBeListenedSearchRepository.save(coinWillBeListened);
        int databaseSizeBeforeDelete = coinWillBeListenedRepository.findAll().size();

        // Get the coinWillBeListened
        restCoinWillBeListenedMockMvc.perform(delete("/api/coin-will-be-listeneds/{id}", coinWillBeListened.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean coinWillBeListenedExistsInEs = coinWillBeListenedSearchRepository.exists(coinWillBeListened.getId());
        assertThat(coinWillBeListenedExistsInEs).isFalse();

        // Validate the database is empty
        List<CoinWillBeListened> coinWillBeListenedList = coinWillBeListenedRepository.findAll();
        assertThat(coinWillBeListenedList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCoinWillBeListened() throws Exception {
        // Initialize the database
        coinWillBeListenedRepository.saveAndFlush(coinWillBeListened);
        coinWillBeListenedSearchRepository.save(coinWillBeListened);

        // Search the coinWillBeListened
        restCoinWillBeListenedMockMvc.perform(get("/api/_search/coin-will-be-listeneds?query=id:" + coinWillBeListened.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coinWillBeListened.getId().intValue())))
            .andExpect(jsonPath("$.[*].symbol").value(hasItem(DEFAULT_SYMBOL.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoinWillBeListened.class);
        CoinWillBeListened coinWillBeListened1 = new CoinWillBeListened();
        coinWillBeListened1.setId(1L);
        CoinWillBeListened coinWillBeListened2 = new CoinWillBeListened();
        coinWillBeListened2.setId(coinWillBeListened1.getId());
        assertThat(coinWillBeListened1).isEqualTo(coinWillBeListened2);
        coinWillBeListened2.setId(2L);
        assertThat(coinWillBeListened1).isNotEqualTo(coinWillBeListened2);
        coinWillBeListened1.setId(null);
        assertThat(coinWillBeListened1).isNotEqualTo(coinWillBeListened2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoinWillBeListenedDTO.class);
        CoinWillBeListenedDTO coinWillBeListenedDTO1 = new CoinWillBeListenedDTO();
        coinWillBeListenedDTO1.setId(1L);
        CoinWillBeListenedDTO coinWillBeListenedDTO2 = new CoinWillBeListenedDTO();
        assertThat(coinWillBeListenedDTO1).isNotEqualTo(coinWillBeListenedDTO2);
        coinWillBeListenedDTO2.setId(coinWillBeListenedDTO1.getId());
        assertThat(coinWillBeListenedDTO1).isEqualTo(coinWillBeListenedDTO2);
        coinWillBeListenedDTO2.setId(2L);
        assertThat(coinWillBeListenedDTO1).isNotEqualTo(coinWillBeListenedDTO2);
        coinWillBeListenedDTO1.setId(null);
        assertThat(coinWillBeListenedDTO1).isNotEqualTo(coinWillBeListenedDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(coinWillBeListenedMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(coinWillBeListenedMapper.fromId(null)).isNull();
    }
}
