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
import com.dong.pojo.CustomerSurveyDetail;
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
import org.springframework.core.env.Environment;
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

    @Autowired
    private Environment env;

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
        Root cSD = q.from(CustomerSurveyDetail.class);
        Root qs = q.from(Question.class);
        Root cS = q.from(CustomerSurvey.class);
        Root s = q.from(Survey.class);

        List<Predicate> predicates = new ArrayList<>();
        q.multiselect(a.get("answer"),
                qs.get("type"),
                cS.get("date"),
                qs.get("question"),
                qs.get("id"));
        //
        predicates.add(b.equal(cSD.get("answerId"), a.get("id")));
        predicates.add(b.equal(cSD.get("questionId"), qs.get("id")));
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
//        q.groupBy(cS.get("id"));
        Query query = session.createQuery(q);
        List<Object> feedbackMap = new ArrayList<>();
        for (Object item : query.getResultList()) {
            //            System.out.println("####" + item);
            Object[] itemArray = (Object[]) item;
            Map<String, Object> feedbackInfo = new HashMap<>();
            Long discountDateTimestamp = itemArray[2] instanceof Date
                    ? ((Date) itemArray[2]).getTime()
                    : Long.parseLong(itemArray[2].toString());

            feedbackInfo.put("answer", itemArray[0]);
            feedbackInfo.put("type", itemArray[1]);
            feedbackInfo.put("date", parseIntToDate(discountDateTimestamp));
            feedbackInfo.put("question", itemArray[3]);
            feedbackInfo.put("question_id", itemArray[4]);

            feedbackMap.add(feedbackInfo);
        }
//        Map<String, Object> feedbackInfo = new HashMap<>();
//        feedbackInfo.put("quantity", feedbackMap.size());

//        feedbackMap.add(feedbackInfo);
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

            Survey s = new Survey();
            s.setDate(new Date());
            s.setActive(Boolean.TRUE);
            session.save(s);
            for (String key : keys) {
                Question qs = new Question();
                qs.setQuestion(params.get(key));
                qs.setType(key);
                qs.setSurveyId(s);
                session.save(qs);
//                CustomerSurveyDetail cSD = new CustomerSurveyDetail();
//                cSD.setQuestionId(qs);
//                cSD.setSurveyId(s);
//                session.save(cSD);
            }

//            
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean answerSurvey(SurveyDTO params) {
        try {
            Session session = this.factory.getObject().getCurrentSession();
            CriteriaBuilder b = session.getCriteriaBuilder();

            Customer customer = session.get(Customer.class, params.getCustomerId());

            CustomerSurvey cS = new CustomerSurvey();
            cS.setDate(new Date());
            cS.setPersonalOpinion(params.getPersonalOpinion());
            cS.setCustomerId(customer);
            cS.setSurveyId(session.get(Survey.class, params.getSurveyId()));
            session.save(cS);

            for (AnswerDTO answerDTO : params.getAnswer()) {
                System.out.println("# " + answerDTO.getId() + "   " + answerDTO.getQuestionId() + "   " + answerDTO.getAnswer());
                // Answer
                Answer answer = new Answer();
                answer.setAnswer(answerDTO.getAnswer());
                answer.setSurveyId(session.get(Survey.class, params.getSurveyId()));
                session.save(answer);

                // CustomerSurvey
                CustomerSurveyDetail cSD = new CustomerSurveyDetail();
                cSD.setAnswerId(answer); //session.get(Answer.class, answerDTO.getId())
                cSD.setQuestionId(session.get(Question.class, answerDTO.getQuestionId()));
                cSD.setCustomerSurveyId(cS);
                session.save(cSD);
            }
            System.out.println(cS);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            // Optionally, you can throw a custom exception here
        }
        return false;
    }

    @Override
    public List<Object> getPersonalOpinion(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root cS = q.from(CustomerSurvey.class);
        Root cSD = q.from(CustomerSurveyDetail.class);
        Root c = q.from(Customer.class);

        List<Predicate> predicates = new ArrayList<>();
        q.multiselect(c.get("name"), cS.get("personalOpinion"), cS.get("date"));
//        predicates.add(b.equal(cS.get("id"), cS.get("surveyId").get("id")));
        predicates.add(b.equal(cS.get("customerId").get("id"), c.get("id")));

        q.groupBy(c.get("id"));

        q.where(predicates.toArray(Predicate[]::new));
        Query query = session.createQuery(q);
        List<Object> feedbackMap = new ArrayList<>();
        for (Object item : query.getResultList()) {
            //            System.out.println("####" + item);
            Object[] itemArray = (Object[]) item;
            Map<String, Object> feedbackInfo = new HashMap<>();
            Long discountDateTimestamp = itemArray[2] instanceof Date
                    ? ((Date) itemArray[2]).getTime()
                    : Long.parseLong(itemArray[2].toString());

            feedbackInfo.put("customer_name", itemArray[0]);
            feedbackInfo.put("personal_opinion", itemArray[1]);
            feedbackInfo.put("date", parseIntToDate(discountDateTimestamp));

            feedbackMap.add(feedbackInfo);
        }
        return feedbackMap;
    }

    @Override
    public List<Object> getQuestionsBySurvey(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//        Root a = q.from(Answer.class);
//        Root cSD = q.from(CustomerSurveyDetail.class);
        Root qs = q.from(Question.class);
//        Root cS = q.from(CustomerSurvey.class);
        Root s = q.from(Survey.class);

        List<Predicate> predicates = new ArrayList<>();
        q.multiselect(
                qs.get("surveyId").get("id"),
                qs.get("id"), qs.get("question"), s.get("date"));

//        predicates.add(b.equal(cSD.get("questionId"), qs.get("id")));
        predicates.add(b.equal(s.get("active"), 1));
        predicates.add(b.equal(qs.get("surveyId").get("id"), s.get("id")));

        q.where(predicates.toArray(Predicate[]::new));
//        q.groupBy(cS.get("id"));
        Query query = session.createQuery(q);
        List<Object> feedbackMap = new ArrayList<>();
        Map<String, List<Object>> r = new HashMap<>();
        Map<String, Object> r1 = new HashMap<>();
        Map<String, Object> r2 = new HashMap<>();

        for (Object item : query.getResultList()) {
            //            System.out.println("####" + item);
            Object[] itemArray = (Object[]) item;
            Map<String, Object> feedbackInfo = new HashMap<>();
//            feedbackInfo.put("survey_id", itemArray[0]);
            feedbackInfo.put("question_id", itemArray[1]);
            feedbackInfo.put("question_content", itemArray[2]);
//            feedbackInfo.put("date", itemArray[3]);

            r1.put("survey_id", itemArray[0]);
            Long discountDateTimestamp = itemArray[3] instanceof Date
                    ? ((Date) itemArray[3]).getTime()
                    : Long.parseLong(itemArray[3].toString());
            r2.put("date", parseIntToDate(discountDateTimestamp));

            feedbackMap.add(feedbackInfo);
        }
        r.put("questions", feedbackMap);
        List<Object> list = new ArrayList<>();
        list.add(r);
        list.add(r1);
        list.add(r2);

        return list;
    }

}
