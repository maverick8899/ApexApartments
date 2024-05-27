
package com.dong.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MAVERICK
 */
@Entity
@Table(name = "relative_park_card")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelativeParkCard.findAll", query = "SELECT r FROM RelativeParkCard r"),
    @NamedQuery(name = "RelativeParkCard.findById", query = "SELECT r FROM RelativeParkCard r WHERE r.id = :id"),
    @NamedQuery(name = "RelativeParkCard.findByDescription", query = "SELECT r FROM RelativeParkCard r WHERE r.description = :description"),
    @NamedQuery(name = "RelativeParkCard.findByDateCreate", query = "SELECT r FROM RelativeParkCard r WHERE r.dateCreate = :dateCreate"),
    @NamedQuery(name = "RelativeParkCard.findByExpiry", query = "SELECT r FROM RelativeParkCard r WHERE r.expiry = :expiry"),
    @NamedQuery(name = "RelativeParkCard.findByActive", query = "SELECT r FROM RelativeParkCard r WHERE r.active = :active"),
    @NamedQuery(name = "RelativeParkCard.findByCost", query = "SELECT r FROM RelativeParkCard r WHERE r.cost = :cost")})
public class RelativeParkCard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_create")
    @Temporal(TemporalType.DATE)
    private Date dateCreate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiry")
    @Temporal(TemporalType.DATE)
    private Date expiry;
    @Column(name = "active")
    private Boolean active;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost")
    private BigDecimal cost;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customerId;

    public RelativeParkCard() {
    }

    public RelativeParkCard(Integer id) {
        this.id = id;
    }

    public RelativeParkCard(Integer id, Date dateCreate, Date expiry, BigDecimal cost) {
        this.id = id;
        this.dateCreate = dateCreate;
        this.expiry = expiry;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
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
        if (!(object instanceof RelativeParkCard)) {
            return false;
        }
        RelativeParkCard other = (RelativeParkCard) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dong.pojo.RelativeParkCard[ id=" + id + " ]";
    }
    
}
