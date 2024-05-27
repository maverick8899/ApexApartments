/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.repository.impl;

import com.dong.DTO.AnswerDTO;
import com.dong.DTO.ReceiptDTO;
import com.dong.DTO.SurveyDTO;
import com.dong.pojo.Answer;
import com.dong.pojo.Customer;
import com.dong.pojo.CustomerSurvey;
import com.dong.pojo.Question;
import com.dong.pojo.Receipt;
import com.dong.pojo.Survey;
import com.dong.repository.SurveyRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 *
 * @author MAVERICK
 */
@Repository
@Transactional
public class SurveyRepositoryImpl implements SurveyRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object> getSurvey(Map<String, String> params) {

        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root a = q.from(Answer.class);
        Root cS = q.from(CustomerSurvey.class);
        Root qs = q.from(Question.class);
        List<Predicate> predicates = new ArrayList<>();
        q.multiselect(a.get("answer"), qs.get("type"));
//        
        predicates.add(b.equal(cS.get("answerId"), a.get("id")));
        predicates.add(b.equal(cS.get("questionId"), qs.get("id")));

        q.where(predicates.toArray(Predicate[]::new));
        
        Query query = session.createQuery(q);
        List<Object> feedbackMap = new ArrayList<>();
        for (Object item : query.getResultList()) {
//            System.out.println("####" + item);
            Object[] itemArray = (Object[]) item;
            Map<String, Object> feedbackInfo = new HashMap<>();

            feedbackInfo.put("id", itemArray[0]);
            feedbackInfo.put("answer", itemArray[1]);

            feedbackMap.add(feedbackInfo);
        }
        return feedbackMap;
    }

    @Override
    public boolean createSurvey(Map<String, String> params) {
        try {
            //
            Session session = this.factory.getObject().getCurrentSession();
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<Question> q = b.createQuery(Question.class);

            Set<String> keys = params.keySet();

            for (String key : keys) {
                Question sD = new Question();
                sD.setQuestion(params.get(key));
                sD.setType(key);

                session.save(sD);
//                System.out.println(key + " ###  " + params.get(key));
//
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Survey answerSurvey(SurveyDTO params) {
        try {
            Session session = this.factory.getObject().getCurrentSession();
            CriteriaBuilder b = session.getCriteriaBuilder();

            Customer customer = session.get(Customer.class, params.getCustomerId());

            Survey survey = new Survey();
            survey.setDate(new Date());
            survey.setPersonalOpinion(params.getPersonalOpinion());
            session.save(survey);

            for (AnswerDTO answerDTO : params.getAnswer()) {
                System.out.println("# " + answerDTO.getId() + "   " + answerDTO.getQuestionId() + "   " + answerDTO.getAnswer());
                // Answer
                Answer answer = new Answer();
                answer.setAnswer(answerDTO.getAnswer());
                session.save(answer);

                // CustomerSurvey
                CustomerSurvey customerSurvey = new CustomerSurvey();
                customerSurvey.setAnswerId(answer);  //session.get(Answer.class, answerDTO.getId())
                customerSurvey.setCustomerId(customer);
                customerSurvey.setQuestionId(session.get(Question.class, answerDTO.getQuestionId()));
                customerSurvey.setSurveyId(survey);
                session.save(customerSurvey);
            }

            return survey;
        } catch (Exception ex) {
            ex.printStackTrace();
            // Optionally, you can throw a custom exception here
        }
        return null;
    }

    @Override
    public Receipt getReceiptById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
