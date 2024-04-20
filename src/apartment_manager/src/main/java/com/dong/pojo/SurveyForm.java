/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "survey_form")
@NamedQueries({
    @NamedQuery(name = "SurveyForm.findAll", query = "SELECT s FROM SurveyForm s"),
    @NamedQuery(name = "SurveyForm.findById", query = "SELECT s FROM SurveyForm s WHERE s.id = :id"),
    @NamedQuery(name = "SurveyForm.findByLink", query = "SELECT s FROM SurveyForm s WHERE s.link = :link"),
    @NamedQuery(name = "SurveyForm.findByIsSurvey", query = "SELECT s FROM SurveyForm s WHERE s.isSurvey = :isSurvey")})
public class SurveyForm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "link")
    private String link;
    @Column(name = "is_survey")
    private Boolean isSurvey;

    public SurveyForm() {
    }

    public SurveyForm(Integer id) {
        this.id = id;
    }

    public SurveyForm(Integer id, String link) {
        this.id = id;
        this.link = link;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getIsSurvey() {
        return isSurvey;
    }

    public void setIsSurvey(Boolean isSurvey) {
        this.isSurvey = isSurvey;
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
        if (!(object instanceof SurveyForm)) {
            return false;
        }
        SurveyForm other = (SurveyForm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dong.pojo.SurveyForm[ id=" + id + " ]";
    }
    
}
