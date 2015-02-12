package com.dsisby.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Absensi.
 */
@Entity
@Table(name = "T_ABSENSI")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Absensi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nama_file")
    private String namaFile;
    
    @Column(columnDefinition = "LONGBLOB")
    private byte[] file;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Absensi absensi = (Absensi) o;

        if (id != null ? !id.equals(absensi.id) : absensi.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Absensi{" +
                "id=" + id +
                ", namaFile='" + namaFile + "'" +
                '}';
    }
}
