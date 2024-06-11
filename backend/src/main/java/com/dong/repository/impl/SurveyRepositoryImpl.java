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
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
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
        int type = 0;
        String kw = "";
        if (params.get("kw") != null
                && !params.get("kw").isEmpty()
                && params.get("type") != null
                && !params.get("type").isEmpty()) {
            type = Integer.parseInt(params.get("type"));
            kw = params.get("kw");
        }
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root a = q.from(Answer.class);
        Root cS = q.from(CustomerSurvey.class);
        Root qs = q.from(Question.class);
        Root s = q.from(Survey.class);

        List<Predicate> predicates = new ArrayList<>();
        q.multiselect(a.get("answer"), qs.get("type"),
                s.get("date"));
//        
        predicates.add(b.equal(cS.get("answerId"), a.get("id")));
        predicates.add(b.equal(cS.get("questionId"), qs.get("id")));
        if (type == 1) {
            predicates.add(
                    b.equal(b.function("MONTH", Integer.class, s.get("date")), kw)
            );
        }
        if (type == 2) {
            predicates.add(
                    b.equal(b.function("YEAR", Integer.class, s.get("date")), kw)
            );
        }
        q.where(predicates.toArray(Predicate[]::new));
        q.groupBy(cS.get("id"));
        Query query = session.createQuery(q);
        List<Object> feedbackMap = new ArrayList<>();
        for (Object item : query.getResultList()) {
//            System.out.println("####" + item);
            Object[] itemArray = (Object[]) item;
            Map<String, Object> feedbackInfo = new HashMap<>();
            Long discountDateTimestamp = itemArray[2] instanceof Date ? ((Date) itemArray[2]).getTime() : Long.parseLong(itemArray[2].toString());

            feedbackInfo.put("answer", itemArray[0]);
            feedbackInfo.put("type", itemArray[1]);
            feedbackInfo.put("date", parseIntToDate(discountDateTimestamp));

            feedbackMap.add(feedbackInfo);
        }
        Map<String, Object> feedbackInfo = new HashMap<>();
        feedbackInfo.put("quantity", feedbackMap.size());

        feedbackMap.add(feedbackInfo);
        return feedbackMap;
    }

    public Object parseIntToDate(Long timestamp) {
//        long timestamp = 1640970000000L; // Giá trị timestamp dạng long
        Instant instant = Instant.ofEpochMilli(timestamp); // Chuyển timestamp sang Instant
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Định dạng thời gian mong muốn
        formatter.setTimeZone(TimeZone.getDefault()); // Cài đặt múi giờ mặc định
        Date date = Date.from(instant); // Chuyển Instant sang Date
        String formattedDate = formatter.format(date); // Định dạng Date thành chuỗi
        System.out.println("Date: " + formattedDate); // In ra ngày đã định dạng
        return formattedDate;
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

    @Override
    public List<Object> getPersonalOpinion(Map<String, String> params) {
        int type = 0;
        String kw = "";
        if (params.get("kw") != null
                && !params.get("kw").isEmpty()
                && params.get("type") != null
                && !params.get("type").isEmpty()) {
            type = Integer.parseInt(params.get("type"));
            kw = params.get("kw");
        }
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root s = q.from(Survey.class);
        Root cS = q.from(CustomerSurvey.class);
        Root c = q.from(Customer.class);

        List<Predicate> predicates = new ArrayList<>();
        q.multiselect(c.get("name"), s.get("personalOpinion"), s.get("date"));
//        
        if (type == 1) {
            predicates.add(
                    b.equal(b.function("MONTH", Integer.class, s.get("date")), kw)
            );
        }
        if (type == 2) {
            predicates.add(
                    b.equal(b.function("YEAR", Integer.class, s.get("date")), kw)
            );
        }
        predicates.add(b.equal(s.get("id"), cS.get("surveyId").get("id")));
        predicates.add(b.equal(cS.get("customerId").get("id"), c.get("id")));

        q.groupBy(s.get("id"));

        q.where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);
        List<Object> feedbackMap = new ArrayList<>();
        for (Object item : query.getResultList()) {
//            System.out.println("####" + item);
            Object[] itemArray = (Object[]) item;
            Map<String, Object> feedbackInfo = new HashMap<>();
            Long discountDateTimestamp = itemArray[2] instanceof Date ? ((Date) itemArray[2]).getTime() : Long.parseLong(itemArray[2].toString());
            
            feedbackInfo.put("customer_name", itemArray[0]);
            feedbackInfo.put("personal_opinion", itemArray[1]);
            feedbackInfo.put("date", parseIntToDate(discountDateTimestamp));
            
            feedbackMap.add(feedbackInfo);
        }
        return feedbackMap;
    }

}
