/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MAVERICK
 */
@Entity
@Table(name = "customer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findById", query = "SELECT c FROM Customer c WHERE c.id = :id"),
    @NamedQuery(name = "Customer.findByName", query = "SELECT c FROM Customer c WHERE c.name = :name"),
    @NamedQuery(name = "Customer.findByAddress", query = "SELECT c FROM Customer c WHERE c.address = :address"),
    @NamedQuery(name = "Customer.findByPhoneNumber", query = "SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email"),
    @NamedQuery(name = "Customer.findByGender", query = "SELECT c FROM Customer c WHERE c.gender = :gender"),
    @NamedQuery(name = "Customer.findByBirthday", query = "SELECT c FROM Customer c WHERE c.birthday = :birthday"),
    @NamedQuery(name = "Customer.findByActive", query = "SELECT c FROM Customer c WHERE c.active = :active")})
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Size(max = 255)
    @Column(name = "phone_number")
    private String phoneNumber;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "gender")
    private String gender;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "customerId")
    private Collection<CustomerSurvey> customerSurveyCollection;
    @OneToMany(mappedBy = "customerId")
    private Collection<MerchandiseCabinetDetail> merchandiseCabinetDetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<UseService> useServiceCollection;
    @OneToMany(mappedBy = "customerId")
    private Collection<Feedback> feedbackCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<RelativeParkCard> relativeParkCardCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId")
    private Collection<Receipt> receiptCollection;
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @ManyToOne
    private Accounts accountId;
    @JoinColumn(name = "merchandise_cabinet_id", referencedColumnName = "id")
    @ManyToOne
    private MerchandiseCabinet merchandiseCabinetId;
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @ManyToOne
    private Room roomId;

    public Customer() {
    }

    public Customer(Integer id) {
        this.id = id;
    }

    public Customer(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @XmlTransient
    public Collection<CustomerSurvey> getCustomerSurveyCollection() {
        return customerSurveyCollection;
    }

    public void setCustomerSurveyCollection(Collection<CustomerSurvey> customerSurveyCollection) {
        this.customerSurveyCollection = customerSurveyCollection;
    }

    @XmlTransient
    public Collection<MerchandiseCabinetDetail> getMerchandiseCabinetDetailCollection() {
        return merchandiseCabinetDetailCollection;
    }

    public void setMerchandiseCabinetDetailCollection(Collection<MerchandiseCabinetDetail> merchandiseCabinetDetailCollection) {
        this.merchandiseCabinetDetailCollection = merchandiseCabinetDetailCollection;
    }

    @XmlTransient
    public Collection<UseService> getUseServiceCollection() {
        return useServiceCollection;
    }

    public void setUseServiceCollection(Collection<UseService> useServiceCollection) {
        this.useServiceCollection = useServiceCollection;
    }

    @XmlTransient
    public Collection<Feedback> getFeedbackCollection() {
        return feedbackCollection;
    }

    public void setFeedbackCollection(Collection<Feedback> feedbackCollection) {
        this.feedbackCollection = feedbackCollection;
    }

    @XmlTransient
    public Collection<RelativeParkCard> getRelativeParkCardCollection() {
        return relativeParkCardCollection;
    }

    public void setRelativeParkCardCollection(Collection<RelativeParkCard> relativeParkCardCollection) {
        this.relativeParkCardCollection = relativeParkCardCollection;
    }

    @XmlTransient
    public Collection<Receipt> getReceiptCollection() {
        return receiptCollection;
    }

    public void setReceiptCollection(Collection<Receipt> receiptCollection) {
        this.receiptCollection = receiptCollection;
    }

    public Accounts getAccountId() {
        return accountId;
    }

    public void setAccountId(Accounts accountId) {
        this.accountId = accountId;
    }

    public MerchandiseCabinet getMerchandiseCabinetId() {
        return merchandiseCabinetId;
    }

    public void setMerchandiseCabinetId(MerchandiseCabinet merchandiseCabinetId) {
        this.merchandiseCabinetId = merchandiseCabinetId;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
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
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dong.pojo.Customer[ id=" + id + " ]";
    }
    
}
