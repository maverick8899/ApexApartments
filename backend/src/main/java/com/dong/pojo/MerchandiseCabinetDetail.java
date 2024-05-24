/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "merchandise_cabinet_detail")
@NamedQueries({
    @NamedQuery(name = "MerchandiseCabinetDetail.findAll", query = "SELECT m FROM MerchandiseCabinetDetail m"),
    @NamedQuery(name = "MerchandiseCabinetDetail.findById", query = "SELECT m FROM MerchandiseCabinetDetail m WHERE m.id = :id"),
    @NamedQuery(name = "MerchandiseCabinetDetail.findByQuantity", query = "SELECT m FROM MerchandiseCabinetDetail m WHERE m.quantity = :quantity"),
    @NamedQuery(name = "MerchandiseCabinetDetail.findByDateReceive", query = "SELECT m FROM MerchandiseCabinetDetail m WHERE m.dateReceive = :dateReceive"),
    @NamedQuery(name = "MerchandiseCabinetDetail.findByIsReceive", query = "SELECT m FROM MerchandiseCabinetDetail m WHERE m.isReceive = :isReceive")})
public class MerchandiseCabinetDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity")
    private Float quantity;
    @Column(name = "date_receive")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReceive;
    @Column(name = "is_receive")
    private Boolean isReceive;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne

    private Customer customerId;
    @JoinColumn(name = "merchandise_id", referencedColumnName = "id")
    @ManyToOne

    private Merchandise merchandiseId;

    public MerchandiseCabinetDetail() {
    }

    public MerchandiseCabinetDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Date getDateReceive() {
        return dateReceive;
    }

    public void setDateReceive(Date dateReceive) {
        this.dateReceive = dateReceive;
    }

    public Boolean getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(Boolean isReceive) {
        this.isReceive = isReceive;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Merchandise getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(Merchandise merchandiseId) {
        this.merchandiseId = merchandiseId;
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
        if (!(object instanceof MerchandiseCabinetDetail)) {
            return false;
        }
        MerchandiseCabinetDetail other = (MerchandiseCabinetDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dong.pojo.MerchandiseCabinetDetail[ id=" + id + " ]";
    }
    
}
