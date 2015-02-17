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
 * A Karyawan.
 */
@Entity
@Table(name = "T_KARYAWAN")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Karyawan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_absensi")
    private Integer idAbsensi;

    @Column(name = "nama_lengkap")
    private String namaLengkap;

    @Column(name = "nickname")
    private String nickname;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    @Column(name = "start_working", nullable = false)
    private LocalDate startWorking;

    @Column(name = "status")
    private String status;

    @Column(name = "phone")
    private String phone;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "tempat_lahir")
    private String tempatLahir;

    @Column(name = "alamat_tinggal")
    private String alamatTinggal;

    @Column(name = "nama_keluarga")
    private String namaKeluarga;

    @Column(name = "hp_keluarga")
    private String hpKeluarga;

    @Column(name = "hubungan_keluarga")
    private String hubunganKeluarga;

    @Column(name = "jumlah_anak")
    private Integer jumlahAnak;
    
    @Column(name = "status_kerja")
    private String statusKerja;
    
    @Column(name = "ktp")
    private String ktp;
    
    @Column(name = "email")
    private String email;
    
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    @Column(name = "tanggal_resign", nullable = false)
    private LocalDate tanggalResign;

    @OneToMany(mappedBy = "karyawan")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Ijin> ijins = new HashSet<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }
   
    public String getStatusKerja() {
        return statusKerja;
    }

    public void setStatusKerja(String statusKerja) {
        this.statusKerja = statusKerja;
    }

    public LocalDate getTanggalResign() {
        return tanggalResign;
    }

    public void setTanggalResign(LocalDate tanggalResign) {
        this.tanggalResign = tanggalResign;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdAbsensi() {
        return idAbsensi;
    }

    public void setIdAbsensi(Integer idAbsensi) {
        this.idAbsensi = idAbsensi;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getStartWorking() {
        return startWorking;
    }

    public void setStartWorking(LocalDate startWorking) {
        this.startWorking = startWorking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getAlamatTinggal() {
        return alamatTinggal;
    }

    public void setAlamatTinggal(String alamatTinggal) {
        this.alamatTinggal = alamatTinggal;
    }

    public String getNamaKeluarga() {
        return namaKeluarga;
    }

    public void setNamaKeluarga(String namaKeluarga) {
        this.namaKeluarga = namaKeluarga;
    }

    public String getHpKeluarga() {
        return hpKeluarga;
    }

    public void setHpKeluarga(String hpKeluarga) {
        this.hpKeluarga = hpKeluarga;
    }

    public String getHubunganKeluarga() {
        return hubunganKeluarga;
    }

    public void setHubunganKeluarga(String hubunganKeluarga) {
        this.hubunganKeluarga = hubunganKeluarga;
    }

    public Integer getJumlahAnak() {
        return jumlahAnak;
    }

    public void setJumlahAnak(Integer jumlahAnak) {
        this.jumlahAnak = jumlahAnak;
    }

    public Set<Ijin> getIjins() {
        return ijins;
    }

    public void setIjins(Set<Ijin> ijins) {
        this.ijins = ijins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Karyawan karyawan = (Karyawan) o;

        if (id != null ? !id.equals(karyawan.id) : karyawan.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Karyawan{" +
                "id=" + id +
                ", idAbsensi='" + idAbsensi + "'" +
                ", namaLengkap='" + namaLengkap + "'" +
                ", nickname='" + nickname + "'" +
                ", startWorking='" + startWorking + "'" +
                ", status='" + status + "'" +
                ", phone='" + phone + "'" +
                ", birthday='" + birthday + "'" +
                ", tempatLahir='" + tempatLahir + "'" +
                ", alamatTinggal='" + alamatTinggal + "'" +
                ", namaKeluarga='" + namaKeluarga + "'" +
                ", hpKeluarga='" + hpKeluarga + "'" +
                ", hubunganKeluarga='" + hubunganKeluarga + "'" +
                ", jumlahAnak='" + jumlahAnak + "'" +
                '}';
    }
}
