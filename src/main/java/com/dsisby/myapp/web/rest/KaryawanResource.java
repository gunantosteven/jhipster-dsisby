package com.dsisby.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dsisby.myapp.domain.Karyawan;
import com.dsisby.myapp.repository.KaryawanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Karyawan.
 */
@RestController
@RequestMapping("/api")
public class KaryawanResource {

    private final Logger log = LoggerFactory.getLogger(KaryawanResource.class);

    @Inject
    private KaryawanRepository karyawanRepository;

    /**
     * POST  /karyawans -> Create a new karyawan.
     */
    @RequestMapping(value = "/karyawans",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Karyawan karyawan) {
        log.debug("REST request to save Karyawan : {}", karyawan);
        karyawanRepository.save(karyawan);
    }

    /**
     * GET  /karyawans -> get all the karyawans.
     */
    @RequestMapping(value = "/karyawans",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Karyawan> getAll() {
        log.debug("REST request to get all Karyawans");
        return karyawanRepository.findAll();
    }

    /**
     * GET  /karyawans/:id -> get the "id" karyawan.
     */
    @RequestMapping(value = "/karyawans/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Karyawan> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Karyawan : {}", id);
        Karyawan karyawan = karyawanRepository.findOne(id);
        if (karyawan == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(karyawan, HttpStatus.OK);
    }

    /**
     * DELETE  /karyawans/:id -> delete the "id" karyawan.
     */
    @RequestMapping(value = "/karyawans/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Karyawan : {}", id);
        karyawanRepository.delete(id);
    }
}
