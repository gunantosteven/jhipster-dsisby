package com.dsisby.myapp.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.joda.time.LocalDate;
import java.util.List;

import com.dsisby.myapp.Application;
import com.dsisby.myapp.domain.Ijin;
import com.dsisby.myapp.repository.IjinRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the IjinResource REST controller.
 *
 * @see IjinResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class IjinResourceTest {


    private static final LocalDate DEFAULT_TANGGAL_IJIN = new LocalDate(0L);
    private static final LocalDate UPDATED_TANGGAL_IJIN = new LocalDate();

    private static final LocalDate DEFAULT_DARI = new LocalDate(0L);
    private static final LocalDate UPDATED_DARI = new LocalDate();

    private static final LocalDate DEFAULT_SAMPAI = new LocalDate(0L);
    private static final LocalDate UPDATED_SAMPAI = new LocalDate();
    private static final String DEFAULT_ALASAN = "SAMPLE_TEXT";
    private static final String UPDATED_ALASAN = "UPDATED_TEXT";

    @Inject
    private IjinRepository ijinRepository;

    private MockMvc restIjinMockMvc;

    private Ijin ijin;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        IjinResource ijinResource = new IjinResource();
        ReflectionTestUtils.setField(ijinResource, "ijinRepository", ijinRepository);
        this.restIjinMockMvc = MockMvcBuilders.standaloneSetup(ijinResource).build();
    }

    @Before
    public void initTest() {
        ijin = new Ijin();
        ijin.setTanggalIjin(DEFAULT_TANGGAL_IJIN);
        ijin.setDari(DEFAULT_DARI);
        ijin.setSampai(DEFAULT_SAMPAI);
        ijin.setAlasan(DEFAULT_ALASAN);
    }

    @Test
    @Transactional
    public void createIjin() throws Exception {
        // Validate the database is empty
        assertThat(ijinRepository.findAll()).hasSize(0);

        // Create the Ijin
        restIjinMockMvc.perform(post("/api/ijins")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ijin)))
                .andExpect(status().isOk());

        // Validate the Ijin in the database
        List<Ijin> ijins = ijinRepository.findAll();
        assertThat(ijins).hasSize(1);
        Ijin testIjin = ijins.iterator().next();
        assertThat(testIjin.getTanggalIjin()).isEqualTo(DEFAULT_TANGGAL_IJIN);
        assertThat(testIjin.getDari()).isEqualTo(DEFAULT_DARI);
        assertThat(testIjin.getSampai()).isEqualTo(DEFAULT_SAMPAI);
        assertThat(testIjin.getAlasan()).isEqualTo(DEFAULT_ALASAN);
    }

    @Test
    @Transactional
    public void getAllIjins() throws Exception {
        // Initialize the database
        ijinRepository.saveAndFlush(ijin);

        // Get all the ijins
        restIjinMockMvc.perform(get("/api/ijins"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(ijin.getId().intValue()))
                .andExpect(jsonPath("$.[0].tanggalIjin").value(DEFAULT_TANGGAL_IJIN.toString()))
                .andExpect(jsonPath("$.[0].dari").value(DEFAULT_DARI.toString()))
                .andExpect(jsonPath("$.[0].sampai").value(DEFAULT_SAMPAI.toString()))
                .andExpect(jsonPath("$.[0].alasan").value(DEFAULT_ALASAN.toString()));
    }

    @Test
    @Transactional
    public void getIjin() throws Exception {
        // Initialize the database
        ijinRepository.saveAndFlush(ijin);

        // Get the ijin
        restIjinMockMvc.perform(get("/api/ijins/{id}", ijin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(ijin.getId().intValue()))
            .andExpect(jsonPath("$.tanggalIjin").value(DEFAULT_TANGGAL_IJIN.toString()))
            .andExpect(jsonPath("$.dari").value(DEFAULT_DARI.toString()))
            .andExpect(jsonPath("$.sampai").value(DEFAULT_SAMPAI.toString()))
            .andExpect(jsonPath("$.alasan").value(DEFAULT_ALASAN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIjin() throws Exception {
        // Get the ijin
        restIjinMockMvc.perform(get("/api/ijins/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIjin() throws Exception {
        // Initialize the database
        ijinRepository.saveAndFlush(ijin);

        // Update the ijin
        ijin.setTanggalIjin(UPDATED_TANGGAL_IJIN);
        ijin.setDari(UPDATED_DARI);
        ijin.setSampai(UPDATED_SAMPAI);
        ijin.setAlasan(UPDATED_ALASAN);
        restIjinMockMvc.perform(post("/api/ijins")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ijin)))
                .andExpect(status().isOk());

        // Validate the Ijin in the database
        List<Ijin> ijins = ijinRepository.findAll();
        assertThat(ijins).hasSize(1);
        Ijin testIjin = ijins.iterator().next();
        assertThat(testIjin.getTanggalIjin()).isEqualTo(UPDATED_TANGGAL_IJIN);
        assertThat(testIjin.getDari()).isEqualTo(UPDATED_DARI);
        assertThat(testIjin.getSampai()).isEqualTo(UPDATED_SAMPAI);
        assertThat(testIjin.getAlasan()).isEqualTo(UPDATED_ALASAN);
    }

    @Test
    @Transactional
    public void deleteIjin() throws Exception {
        // Initialize the database
        ijinRepository.saveAndFlush(ijin);

        // Get the ijin
        restIjinMockMvc.perform(delete("/api/ijins/{id}", ijin.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Ijin> ijins = ijinRepository.findAll();
        assertThat(ijins).hasSize(0);
    }
}
