/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.pojo;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MAVERICK
 */
@Entity
@Table(name = "customer_survey")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerSurvey.findAll", query = "SELECT c FROM CustomerSurvey c"),
    @NamedQuery(name = "CustomerSurvey.findById", query = "SELECT c FROM CustomerSurvey c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerSurvey.findByDate", query = "SELECT c FROM CustomerSurvey c WHERE c.date = :date"),
    @NamedQuery(name = "CustomerSurvey.findByPersonalOpinion", query = "SELECT c FROM CustomerSurvey c WHERE c.personalOpinion = :personalOpinion")})
public class CustomerSurvey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Size(max = 255)
    @Column(name = "personal_opinion")
    private String personalOpinion;
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne
    private Customer customerId;
    @JoinColumn(name = "survey_id", referencedColumnName = "id")
    @ManyToOne
    private Survey surveyId;
    @OneToMany(mappedBy = "customerSurveyId")
    private Collection<CustomerSurveyDetail> customerSurveyDetailCollection;

    public CustomerSurvey() {
    }

    public CustomerSurvey(Integer id) {
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

    public String getPersonalOpinion() {
        return personalOpinion;
    }

    public void setPersonalOpinion(String personalOpinion) {
        this.personalOpinion = personalOpinion;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Survey getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Survey surveyId) {
        this.surveyId = surveyId;
    }

    @XmlTransient
    public Collection<CustomerSurveyDetail> getCustomerSurveyDetailCollection() {
        return customerSurveyDetailCollection;
    }

    public void setCustomerSurveyDetailCollection(Collection<CustomerSurveyDetail> customerSurveyDetailCollection) {
        this.customerSurveyDetailCollection = customerSurveyDetailCollection;
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
        if (!(object instanceof CustomerSurvey)) {
            return false;
        }
        CustomerSurvey other = (CustomerSurvey) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dong.pojo.CustomerSurvey[ id=" + id + " ]";
    }
    
}
