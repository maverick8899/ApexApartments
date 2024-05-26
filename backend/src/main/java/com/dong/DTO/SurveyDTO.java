/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Array;
import java.util.List;


/**
 *
 * @author MAVERICK
 */

public class SurveyDTO {

    private int customerId;
    private List<AnswerDTO> answers;
    private String personalOpinion;
    
    @JsonCreator
    public SurveyDTO(@JsonProperty("customer_id") int customerId,
                     @JsonProperty("answers") List<AnswerDTO> answers,
                     @JsonProperty("personal_opinion") String personalOpinion) {
        this.customerId = customerId;
        this.answers = answers;
        this.personalOpinion = personalOpinion;
    }
    // public SurveyDTO(int customerId, List<AnswerDTO> answers, String personalOpinion) {
    //     this.customerId = customerId;
    //     this.answers = answers;
    //     this.personalOpinion = personalOpinion;
    // }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<AnswerDTO> getAnswer() {
        return answers;
    }

    public void setAnswer(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public String getPersonalOpinion() {
        return personalOpinion;
    }

    public void setPersonalOpinion(String personalOpinion) {
        this.personalOpinion = personalOpinion;
    }

    
}
