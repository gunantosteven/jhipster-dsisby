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
import com.dsisby.myapp.domain.Karyawan;
import com.dsisby.myapp.repository.KaryawanRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the KaryawanResource REST controller.
 *
 * @see KaryawanResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class KaryawanResourceTest {


    private static final Integer DEFAULT_ID_ABSENSI = 0;
    private static final Integer UPDATED_ID_ABSENSI = 1;
    private static final String DEFAULT_NAMA_LENGKAP = "SAMPLE_TEXT";
    private static final String UPDATED_NAMA_LENGKAP = "UPDATED_TEXT";
    private static final String DEFAULT_NICKNAME = "SAMPLE_TEXT";
    private static final String UPDATED_NICKNAME = "UPDATED_TEXT";

    private static final LocalDate DEFAULT_START_WORKING = new LocalDate(0L);
    private static final LocalDate UPDATED_START_WORKING = new LocalDate();
    private static final String DEFAULT_STATUS = "SAMPLE_TEXT";
    private static final String UPDATED_STATUS = "UPDATED_TEXT";
    private static final String DEFAULT_PHONE = "SAMPLE_TEXT";
    private static final String UPDATED_PHONE = "UPDATED_TEXT";

    private static final LocalDate DEFAULT_BIRTHDAY = new LocalDate(0L);
    private static final LocalDate UPDATED_BIRTHDAY = new LocalDate();
    private static final String DEFAULT_TEMPAT_LAHIR = "SAMPLE_TEXT";
    private static final String UPDATED_TEMPAT_LAHIR = "UPDATED_TEXT";
    private static final String DEFAULT_ALAMAT_TINGGAL = "SAMPLE_TEXT";
    private static final String UPDATED_ALAMAT_TINGGAL = "UPDATED_TEXT";
    private static final String DEFAULT_NAMA_KELUARGA = "SAMPLE_TEXT";
    private static final String UPDATED_NAMA_KELUARGA = "UPDATED_TEXT";
    private static final String DEFAULT_HP_KELUARGA = "SAMPLE_TEXT";
    private static final String UPDATED_HP_KELUARGA = "UPDATED_TEXT";
    private static final String DEFAULT_HUBUNGAN_KELUARGA = "SAMPLE_TEXT";
    private static final String UPDATED_HUBUNGAN_KELUARGA = "UPDATED_TEXT";

    private static final Integer DEFAULT_JUMLAH_ANAK = 0;
    private static final Integer UPDATED_JUMLAH_ANAK = 1;

    @Inject
    private KaryawanRepository karyawanRepository;

    private MockMvc restKaryawanMockMvc;

    private Karyawan karyawan;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        KaryawanResource karyawanResource = new KaryawanResource();
        ReflectionTestUtils.setField(karyawanResource, "karyawanRepository", karyawanRepository);
        this.restKaryawanMockMvc = MockMvcBuilders.standaloneSetup(karyawanResource).build();
    }

    @Before
    public void initTest() {
        karyawan = new Karyawan();
        karyawan.setIdAbsensi(DEFAULT_ID_ABSENSI);
        karyawan.setNamaLengkap(DEFAULT_NAMA_LENGKAP);
        karyawan.setNickname(DEFAULT_NICKNAME);
        karyawan.setStartWorking(DEFAULT_START_WORKING);
        karyawan.setStatus(DEFAULT_STATUS);
        karyawan.setPhone(DEFAULT_PHONE);
        karyawan.setBirthday(DEFAULT_BIRTHDAY);
        karyawan.setTempatLahir(DEFAULT_TEMPAT_LAHIR);
        karyawan.setAlamatTinggal(DEFAULT_ALAMAT_TINGGAL);
        karyawan.setNamaKeluarga(DEFAULT_NAMA_KELUARGA);
        karyawan.setHpKeluarga(DEFAULT_HP_KELUARGA);
        karyawan.setHubunganKeluarga(DEFAULT_HUBUNGAN_KELUARGA);
        karyawan.setJumlahAnak(DEFAULT_JUMLAH_ANAK);
    }

    @Test
    @Transactional
    public void createKaryawan() throws Exception {
        // Validate the database is empty
        assertThat(karyawanRepository.findAll()).hasSize(0);

        // Create the Karyawan
        restKaryawanMockMvc.perform(post("/api/karyawans")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(karyawan)))
                .andExpect(status().isOk());

        // Validate the Karyawan in the database
        List<Karyawan> karyawans = karyawanRepository.findAll();
        assertThat(karyawans).hasSize(1);
        Karyawan testKaryawan = karyawans.iterator().next();
        assertThat(testKaryawan.getIdAbsensi()).isEqualTo(DEFAULT_ID_ABSENSI);
        assertThat(testKaryawan.getNamaLengkap()).isEqualTo(DEFAULT_NAMA_LENGKAP);
        assertThat(testKaryawan.getNickname()).isEqualTo(DEFAULT_NICKNAME);
        assertThat(testKaryawan.getStartWorking()).isEqualTo(DEFAULT_START_WORKING);
        assertThat(testKaryawan.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testKaryawan.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testKaryawan.getBirthday()).isEqualTo(DEFAULT_BIRTHDAY);
        assertThat(testKaryawan.getTempatLahir()).isEqualTo(DEFAULT_TEMPAT_LAHIR);
        assertThat(testKaryawan.getAlamatTinggal()).isEqualTo(DEFAULT_ALAMAT_TINGGAL);
        assertThat(testKaryawan.getNamaKeluarga()).isEqualTo(DEFAULT_NAMA_KELUARGA);
        assertThat(testKaryawan.getHpKeluarga()).isEqualTo(DEFAULT_HP_KELUARGA);
        assertThat(testKaryawan.getHubunganKeluarga()).isEqualTo(DEFAULT_HUBUNGAN_KELUARGA);
        assertThat(testKaryawan.getJumlahAnak()).isEqualTo(DEFAULT_JUMLAH_ANAK);
    }

    @Test
    @Transactional
    public void getAllKaryawans() throws Exception {
        // Initialize the database
        karyawanRepository.saveAndFlush(karyawan);

        // Get all the karyawans
        restKaryawanMockMvc.perform(get("/api/karyawans"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(karyawan.getId().intValue()))
                .andExpect(jsonPath("$.[0].idAbsensi").value(DEFAULT_ID_ABSENSI))
                .andExpect(jsonPath("$.[0].namaLengkap").value(DEFAULT_NAMA_LENGKAP.toString()))
                .andExpect(jsonPath("$.[0].nickname").value(DEFAULT_NICKNAME.toString()))
                .andExpect(jsonPath("$.[0].startWorking").value(DEFAULT_START_WORKING.toString()))
                .andExpect(jsonPath("$.[0].status").value(DEFAULT_STATUS.toString()))
                .andExpect(jsonPath("$.[0].phone").value(DEFAULT_PHONE.toString()))
                .andExpect(jsonPath("$.[0].birthday").value(DEFAULT_BIRTHDAY.toString()))
                .andExpect(jsonPath("$.[0].tempatLahir").value(DEFAULT_TEMPAT_LAHIR.toString()))
                .andExpect(jsonPath("$.[0].alamatTinggal").value(DEFAULT_ALAMAT_TINGGAL.toString()))
                .andExpect(jsonPath("$.[0].namaKeluarga").value(DEFAULT_NAMA_KELUARGA.toString()))
                .andExpect(jsonPath("$.[0].hpKeluarga").value(DEFAULT_HP_KELUARGA.toString()))
                .andExpect(jsonPath("$.[0].hubunganKeluarga").value(DEFAULT_HUBUNGAN_KELUARGA.toString()))
                .andExpect(jsonPath("$.[0].jumlahAnak").value(DEFAULT_JUMLAH_ANAK));
    }

    @Test
    @Transactional
    public void getKaryawan() throws Exception {
        // Initialize the database
        karyawanRepository.saveAndFlush(karyawan);

        // Get the karyawan
        restKaryawanMockMvc.perform(get("/api/karyawans/{id}", karyawan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(karyawan.getId().intValue()))
            .andExpect(jsonPath("$.idAbsensi").value(DEFAULT_ID_ABSENSI))
            .andExpect(jsonPath("$.namaLengkap").value(DEFAULT_NAMA_LENGKAP.toString()))
            .andExpect(jsonPath("$.nickname").value(DEFAULT_NICKNAME.toString()))
            .andExpect(jsonPath("$.startWorking").value(DEFAULT_START_WORKING.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.birthday").value(DEFAULT_BIRTHDAY.toString()))
            .andExpect(jsonPath("$.tempatLahir").value(DEFAULT_TEMPAT_LAHIR.toString()))
            .andExpect(jsonPath("$.alamatTinggal").value(DEFAULT_ALAMAT_TINGGAL.toString()))
            .andExpect(jsonPath("$.namaKeluarga").value(DEFAULT_NAMA_KELUARGA.toString()))
            .andExpect(jsonPath("$.hpKeluarga").value(DEFAULT_HP_KELUARGA.toString()))
            .andExpect(jsonPath("$.hubunganKeluarga").value(DEFAULT_HUBUNGAN_KELUARGA.toString()))
            .andExpect(jsonPath("$.jumlahAnak").value(DEFAULT_JUMLAH_ANAK));
    }

    @Test
    @Transactional
    public void getNonExistingKaryawan() throws Exception {
        // Get the karyawan
        restKaryawanMockMvc.perform(get("/api/karyawans/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKaryawan() throws Exception {
        // Initialize the database
        karyawanRepository.saveAndFlush(karyawan);

        // Update the karyawan
        karyawan.setIdAbsensi(UPDATED_ID_ABSENSI);
        karyawan.setNamaLengkap(UPDATED_NAMA_LENGKAP);
        karyawan.setNickname(UPDATED_NICKNAME);
        karyawan.setStartWorking(UPDATED_START_WORKING);
        karyawan.setStatus(UPDATED_STATUS);
        karyawan.setPhone(UPDATED_PHONE);
        karyawan.setBirthday(UPDATED_BIRTHDAY);
        karyawan.setTempatLahir(UPDATED_TEMPAT_LAHIR);
        karyawan.setAlamatTinggal(UPDATED_ALAMAT_TINGGAL);
        karyawan.setNamaKeluarga(UPDATED_NAMA_KELUARGA);
        karyawan.setHpKeluarga(UPDATED_HP_KELUARGA);
        karyawan.setHubunganKeluarga(UPDATED_HUBUNGAN_KELUARGA);
        karyawan.setJumlahAnak(UPDATED_JUMLAH_ANAK);
        restKaryawanMockMvc.perform(post("/api/karyawans")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(karyawan)))
                .andExpect(status().isOk());

        // Validate the Karyawan in the database
        List<Karyawan> karyawans = karyawanRepository.findAll();
        assertThat(karyawans).hasSize(1);
        Karyawan testKaryawan = karyawans.iterator().next();
        assertThat(testKaryawan.getIdAbsensi()).isEqualTo(UPDATED_ID_ABSENSI);
        assertThat(testKaryawan.getNamaLengkap()).isEqualTo(UPDATED_NAMA_LENGKAP);
        assertThat(testKaryawan.getNickname()).isEqualTo(UPDATED_NICKNAME);
        assertThat(testKaryawan.getStartWorking()).isEqualTo(UPDATED_START_WORKING);
        assertThat(testKaryawan.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testKaryawan.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testKaryawan.getBirthday()).isEqualTo(UPDATED_BIRTHDAY);
        assertThat(testKaryawan.getTempatLahir()).isEqualTo(UPDATED_TEMPAT_LAHIR);
        assertThat(testKaryawan.getAlamatTinggal()).isEqualTo(UPDATED_ALAMAT_TINGGAL);
        assertThat(testKaryawan.getNamaKeluarga()).isEqualTo(UPDATED_NAMA_KELUARGA);
        assertThat(testKaryawan.getHpKeluarga()).isEqualTo(UPDATED_HP_KELUARGA);
        assertThat(testKaryawan.getHubunganKeluarga()).isEqualTo(UPDATED_HUBUNGAN_KELUARGA);
        assertThat(testKaryawan.getJumlahAnak()).isEqualTo(UPDATED_JUMLAH_ANAK);
    }

    @Test
    @Transactional
    public void deleteKaryawan() throws Exception {
        // Initialize the database
        karyawanRepository.saveAndFlush(karyawan);

        // Get the karyawan
        restKaryawanMockMvc.perform(delete("/api/karyawans/{id}", karyawan.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Karyawan> karyawans = karyawanRepository.findAll();
        assertThat(karyawans).hasSize(0);
    }
}
