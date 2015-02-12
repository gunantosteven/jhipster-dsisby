package com.dsisby.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.dsisby.myapp.domain.util.CustomLocalDateSerializer;
import com.dsisby.myapp.domain.util.ISO8601LocalDateDeserializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Ijin.
 */
@Entity
@Table(name = "T_IJIN")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ijin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    @Column(name = "tanggal_ijin", nullable = false)
    private LocalDate tanggalIjin;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    @Column(name = "dari", nullable = false)
    private LocalDate dari;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    @Column(name = "sampai", nullable = false)
    private LocalDate sampai;

    @Column(name = "alasan")
    private String alasan;

    @ManyToOne
    private Karyawan karyawan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTanggalIjin() {
        return tanggalIjin;
    }

    public void setTanggalIjin(LocalDate tanggalIjin) {
        this.tanggalIjin = tanggalIjin;
    }

    public LocalDate getDari() {
        return dari;
    }

    public void setDari(LocalDate dari) {
        this.dari = dari;
    }

    public LocalDate getSampai() {
        return sampai;
    }

    public void setSampai(LocalDate sampai) {
        this.sampai = sampai;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public Karyawan getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(Karyawan karyawan) {
        this.karyawan = karyawan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Ijin ijin = (Ijin) o;

        if (id != null ? !id.equals(ijin.id) : ijin.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Ijin{" +
                "id=" + id +
                ", tanggalIjin='" + tanggalIjin + "'" +
                ", dari='" + dari + "'" +
                ", sampai='" + sampai + "'" +
                ", alasan='" + alasan + "'" +
                '}';
    }
}
