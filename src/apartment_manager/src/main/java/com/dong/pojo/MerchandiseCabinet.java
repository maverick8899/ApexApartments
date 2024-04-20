/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "merchandise_cabinet")
@NamedQueries({
    @NamedQuery(name = "MerchandiseCabinet.findAll", query = "SELECT m FROM MerchandiseCabinet m"),
    @NamedQuery(name = "MerchandiseCabinet.findById", query = "SELECT m FROM MerchandiseCabinet m WHERE m.id = :id"),
    @NamedQuery(name = "MerchandiseCabinet.findByName", query = "SELECT m FROM MerchandiseCabinet m WHERE m.name = :name"),
    @NamedQuery(name = "MerchandiseCabinet.findByDescription", query = "SELECT m FROM MerchandiseCabinet m WHERE m.description = :description"),
    @NamedQuery(name = "MerchandiseCabinet.findByActive", query = "SELECT m FROM MerchandiseCabinet m WHERE m.active = :active")})
public class MerchandiseCabinet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "merchandiseCabinetId")
    @JsonIgnore

    private Collection<Customer> customerCollection;

    public MerchandiseCabinet() {
    }

    public MerchandiseCabinet(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
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
        if (!(object instanceof MerchandiseCabinet)) {
            return false;
        }
        MerchandiseCabinet other = (MerchandiseCabinet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dong.pojo.MerchandiseCabinet[ id=" + id + " ]";
    }
    
}
