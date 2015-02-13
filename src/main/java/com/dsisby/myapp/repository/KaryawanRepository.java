package com.dsisby.myapp.repository;

import com.dsisby.myapp.domain.Karyawan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Spring Data JPA repository for the Karyawan entity.
 */
public interface KaryawanRepository extends JpaRepository<Karyawan,Long>{
}
