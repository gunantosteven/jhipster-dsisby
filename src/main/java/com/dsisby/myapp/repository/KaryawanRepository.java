package com.dsisby.myapp.repository;

import com.dsisby.myapp.domain.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Karyawan entity.
 */
public interface KaryawanRepository extends JpaRepository<Karyawan,Long>{

}
