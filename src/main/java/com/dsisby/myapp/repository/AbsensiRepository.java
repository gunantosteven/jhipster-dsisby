package com.dsisby.myapp.repository;

import com.dsisby.myapp.domain.Absensi;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Absensi entity.
 */
public interface AbsensiRepository extends JpaRepository<Absensi,Long>{

}
