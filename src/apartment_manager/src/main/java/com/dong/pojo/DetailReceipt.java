/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "detail_receipt")
@NamedQueries({
    @NamedQuery(name = "DetailReceipt.findAll", query = "SELECT d FROM DetailReceipt d"),
    @NamedQuery(name = "DetailReceipt.findById", query = "SELECT d FROM DetailReceipt d WHERE d.id = :id"),
    @NamedQuery(name = "DetailReceipt.findByActive", query = "SELECT d FROM DetailReceipt d WHERE d.active = :active"),
    @NamedQuery(name = "DetailReceipt.findByQuantity", query = "SELECT d FROM DetailReceipt d WHERE d.quantity = :quantity"),
    @NamedQuery(name = "DetailReceipt.findByCost", query = "SELECT d FROM DetailReceipt d WHERE d.cost = :cost")})
public class DetailReceipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "active")
    private Boolean active;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private float quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost")
    private BigDecimal cost;
    @JoinColumn(name = "receipt_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Receipt receiptId;
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore

    private Service serviceId;

    public DetailReceipt() {
    }

    public DetailReceipt(Integer id) {
        this.id = id;
    }

    public DetailReceipt(Integer id, float quantity, BigDecimal cost) {
        this.id = id;
        this.quantity = quantity;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Receipt getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Receipt receiptId) {
        this.receiptId = receiptId;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
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
        if (!(object instanceof DetailReceipt)) {
            return false;
        }
        DetailReceipt other = (DetailReceipt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dong.pojo.DetailReceipt[ id=" + id + " ]";
    }
    
}
