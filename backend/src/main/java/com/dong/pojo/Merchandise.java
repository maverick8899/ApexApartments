/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "merchandise")
@NamedQueries({
    @NamedQuery(name = "Merchandise.findAll", query = "SELECT m FROM Merchandise m"),
    @NamedQuery(name = "Merchandise.findById", query = "SELECT m FROM Merchandise m WHERE m.id = :id"),
    @NamedQuery(name = "Merchandise.findByName", query = "SELECT m FROM Merchandise m WHERE m.name = :name"),
    @NamedQuery(name = "Merchandise.findByNote", query = "SELECT m FROM Merchandise m WHERE m.note = :note"),
    @NamedQuery(name = "Merchandise.findByUnit", query = "SELECT m FROM Merchandise m WHERE m.unit = :unit"),
    @NamedQuery(name = "Merchandise.findByDate", query = "SELECT m FROM Merchandise m WHERE m.date = :date"),
    @NamedQuery(name = "Merchandise.findByActive", query = "SELECT m FROM Merchandise m WHERE m.active = :active")})
public class Merchandise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "note")
    private String note;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "unit")
    private String unit;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "merchandiseId")
    @JsonIgnore

    private Collection<MerchandiseCabinetDetail> merchandiseCabinetDetailCollection;

    public Merchandise() {
    }

    public Merchandise(Integer id) {
        this.id = id;
    }

    public Merchandise(Integer id, String name, String note, String unit) {
        this.id = id;
        this.name = name;
        this.note = note;
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Collection<MerchandiseCabinetDetail> getMerchandiseCabinetDetailCollection() {
        return merchandiseCabinetDetailCollection;
    }

    public void setMerchandiseCabinetDetailCollection(Collection<MerchandiseCabinetDetail> merchandiseCabinetDetailCollection) {
        this.merchandiseCabinetDetailCollection = merchandiseCabinetDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Merchandise)) {
            return false;
        }
        Merchandise other = (Merchandise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dong.pojo.Merchandise[ id=" + id + " ]";
    }
    
}
