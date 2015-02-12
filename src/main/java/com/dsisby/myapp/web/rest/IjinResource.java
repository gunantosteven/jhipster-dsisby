package com.dsisby.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dsisby.myapp.domain.Ijin;
import com.dsisby.myapp.repository.IjinRepository;
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
 * REST controller for managing Ijin.
 */
@RestController
@RequestMapping("/api")
public class IjinResource {

    private final Logger log = LoggerFactory.getLogger(IjinResource.class);

    @Inject
    private IjinRepository ijinRepository;

    /**
     * POST  /ijins -> Create a new ijin.
     */
    @RequestMapping(value = "/ijins",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Ijin ijin) {
        log.debug("REST request to save Ijin : {}", ijin);
        ijinRepository.save(ijin);
    }

    /**
     * GET  /ijins -> get all the ijins.
     */
    @RequestMapping(value = "/ijins",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Ijin> getAll() {
        log.debug("REST request to get all Ijins");
        return ijinRepository.findAll();
    }

    /**
     * GET  /ijins/:id -> get the "id" ijin.
     */
    @RequestMapping(value = "/ijins/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Ijin> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Ijin : {}", id);
        Ijin ijin = ijinRepository.findOne(id);
        if (ijin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ijin, HttpStatus.OK);
    }

    /**
     * DELETE  /ijins/:id -> delete the "id" ijin.
     */
    @RequestMapping(value = "/ijins/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Ijin : {}", id);
        ijinRepository.delete(id);
    }
}
