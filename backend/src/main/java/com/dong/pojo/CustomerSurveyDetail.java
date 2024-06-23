/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.pojo;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MAVERICK
 */
@Entity
@Table(name = "customer_survey_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerSurveyDetail.findAll", query = "SELECT c FROM CustomerSurveyDetail c"),
    @NamedQuery(name = "CustomerSurveyDetail.findById", query = "SELECT c FROM CustomerSurveyDetail c WHERE c.id = :id")})
public class CustomerSurveyDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "answer_id", referencedColumnName = "id")
    @ManyToOne
    private Answer answerId;
    @JoinColumn(name = "customer_survey_id", referencedColumnName = "id")
    @ManyToOne
    private CustomerSurvey customerSurveyId;
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @ManyToOne
    private Question questionId;

    public CustomerSurveyDetail() {
    }

    public CustomerSurveyDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Answer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Answer answerId) {
        this.answerId = answerId;
    }

    public CustomerSurvey getCustomerSurveyId() {
        return customerSurveyId;
    }

    public void setCustomerSurveyId(CustomerSurvey customerSurveyId) {
        this.customerSurveyId = customerSurveyId;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
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
        if (!(object instanceof CustomerSurveyDetail)) {
            return false;
        }
        CustomerSurveyDetail other = (CustomerSurveyDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dong.pojo.CustomerSurveyDetail[ id=" + id + " ]";
    }
    
}
