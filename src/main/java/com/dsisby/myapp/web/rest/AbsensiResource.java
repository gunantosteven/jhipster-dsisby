package com.dsisby.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dsisby.myapp.domain.Absensi;
import com.dsisby.myapp.repository.AbsensiRepository;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * REST controller for managing Absensi.
 */
@RestController
@RequestMapping("/api")
public class AbsensiResource {

    private final Logger log = LoggerFactory.getLogger(AbsensiResource.class);

    @Inject
    private AbsensiRepository absensiRepository;

    /**
     * POST  /absensis -> Create a new absensi.
     */
    @RequestMapping(value = "/absensis",
            method = RequestMethod.POST)
    @Timed
    public @ResponseBody void create(
            @RequestParam(value="file", required=true) MultipartFile file) throws IOException  {
        Absensi absensi = new Absensi();
        String fileName=file.getOriginalFilename();
        absensi.setNamaFile(fileName);
        absensi.setFile(file.getBytes());
        log.debug("REST request to save Absensi : {}", absensi);
        
        absensiRepository.save(absensi);
        System.out.println(fileName);
    }

    /**
     * GET  /absensis -> get all the absensis.
     */
    @RequestMapping(value = "/absensis",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Absensi> getAll() {
        log.debug("REST request to get all Absensis");
        return absensiRepository.findAll();
    }

    /**
     * GET  /absensis/:id -> get the "id" absensi.
     */
    @RequestMapping(value = "/absensis/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Absensi> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Absensi : {}", id);
        Absensi absensi = absensiRepository.findOne(id);
        if (absensi == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(absensi, HttpStatus.OK);
    }

    /**
     * DELETE  /absensis/:id -> delete the "id" absensi.
     */
    @RequestMapping(value = "/absensis/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Absensi : {}", id);
        absensiRepository.delete(id);
    }
}
