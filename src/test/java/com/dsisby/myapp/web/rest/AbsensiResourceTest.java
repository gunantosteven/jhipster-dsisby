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
import java.util.List;

import com.dsisby.myapp.Application;
import com.dsisby.myapp.domain.Absensi;
import com.dsisby.myapp.repository.AbsensiRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AbsensiResource REST controller.
 *
 * @see AbsensiResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class AbsensiResourceTest {

    private static final String DEFAULT_NAMA_FILE = "SAMPLE_TEXT";
    private static final String UPDATED_NAMA_FILE = "UPDATED_TEXT";

    @Inject
    private AbsensiRepository absensiRepository;

    private MockMvc restAbsensiMockMvc;

    private Absensi absensi;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AbsensiResource absensiResource = new AbsensiResource();
        ReflectionTestUtils.setField(absensiResource, "absensiRepository", absensiRepository);
        this.restAbsensiMockMvc = MockMvcBuilders.standaloneSetup(absensiResource).build();
    }

    @Before
    public void initTest() {
        absensi = new Absensi();
        absensi.setNamaFile(DEFAULT_NAMA_FILE);
    }

    @Test
    @Transactional
    public void createAbsensi() throws Exception {
        // Validate the database is empty
        assertThat(absensiRepository.findAll()).hasSize(0);

        // Create the Absensi
        restAbsensiMockMvc.perform(post("/api/absensis")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(absensi)))
                .andExpect(status().isOk());

        // Validate the Absensi in the database
        List<Absensi> absensis = absensiRepository.findAll();
        assertThat(absensis).hasSize(1);
        Absensi testAbsensi = absensis.iterator().next();
        assertThat(testAbsensi.getNamaFile()).isEqualTo(DEFAULT_NAMA_FILE);
    }

    @Test
    @Transactional
    public void getAllAbsensis() throws Exception {
        // Initialize the database
        absensiRepository.saveAndFlush(absensi);

        // Get all the absensis
        restAbsensiMockMvc.perform(get("/api/absensis"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(absensi.getId().intValue()))
                .andExpect(jsonPath("$.[0].namaFile").value(DEFAULT_NAMA_FILE.toString()));
    }

    @Test
    @Transactional
    public void getAbsensi() throws Exception {
        // Initialize the database
        absensiRepository.saveAndFlush(absensi);

        // Get the absensi
        restAbsensiMockMvc.perform(get("/api/absensis/{id}", absensi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(absensi.getId().intValue()))
            .andExpect(jsonPath("$.namaFile").value(DEFAULT_NAMA_FILE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAbsensi() throws Exception {
        // Get the absensi
        restAbsensiMockMvc.perform(get("/api/absensis/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAbsensi() throws Exception {
        // Initialize the database
        absensiRepository.saveAndFlush(absensi);

        // Update the absensi
        absensi.setNamaFile(UPDATED_NAMA_FILE);
        restAbsensiMockMvc.perform(post("/api/absensis")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(absensi)))
                .andExpect(status().isOk());

        // Validate the Absensi in the database
        List<Absensi> absensis = absensiRepository.findAll();
        assertThat(absensis).hasSize(1);
        Absensi testAbsensi = absensis.iterator().next();
        assertThat(testAbsensi.getNamaFile()).isEqualTo(UPDATED_NAMA_FILE);
    }

    @Test
    @Transactional
    public void deleteAbsensi() throws Exception {
        // Initialize the database
        absensiRepository.saveAndFlush(absensi);

        // Get the absensi
        restAbsensiMockMvc.perform(delete("/api/absensis/{id}", absensi.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Absensi> absensis = absensiRepository.findAll();
        assertThat(absensis).hasSize(0);
    }
}
