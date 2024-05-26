/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *
 * @author MAVERICK
 */
@Data
public class AnswerDTO {

    private int id;
    private int questionId;
    private Short answer;

    @JsonCreator
    public AnswerDTO(@JsonProperty("id") int id, @JsonProperty("answer") Short answer, @JsonProperty("question_id") int questionId) {
        this.id = id;
        this.answer = answer;
        this.questionId = questionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Short getAnswer() {
        return answer;
    }

    public void setAnswer(Short answer) {
        this.answer = answer;
    }
}
