
package com.dong.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MAVERICK
 */
@Entity
@Table(name = "use_service")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UseService.findAll", query = "SELECT u FROM UseService u"),
    @NamedQuery(name = "UseService.findById", query = "SELECT u FROM UseService u WHERE u.id = :id"),
    @NamedQuery(name = "UseService.findByDate", query = "SELECT u FROM UseService u WHERE u.date = :date"),
    @NamedQuery(name = "UseService.findByActive", query = "SELECT u FROM UseService u WHERE u.active = :active")})
public class UseService implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "active")
    private Boolean active;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customerId;
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Service serviceId;

    public UseService() {
    }

    public UseService(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }
    @PrePersist
    protected void onCreate() {
        if (this.date == null) {
            this.date = new Date();
        }
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
        if (!(object instanceof UseService)) {
            return false;
        }
        UseService other = (UseService) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dong.pojo.UseService[ id=" + id + " ]";
    }
    
}
