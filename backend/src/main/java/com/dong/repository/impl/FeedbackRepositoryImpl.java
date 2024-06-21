package com.dong.repository.impl;

import com.dong.pojo.Customer;
import com.dong.pojo.DetailReceipt;
import com.dong.pojo.Feedback;
import com.dong.pojo.Receipt;
import com.dong.repository.FeedbackRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class FeedbackRepositoryImpl implements FeedbackRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public List<Object> getFeedback(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root f = q.from(Feedback.class);
        //        q.select(f);
        q.multiselect(
                f.get("id"),
                f.get("title"),
                f.get("content"),
                f.get("customerId").get("id"),
                f.get("customerId").get("name")
        );

        List<Object> feedbackMap = new ArrayList<>();
        Query query = session.createQuery(q);

        String pageNumber = "";
        int pageSize = 0;
        if (params != null && !params.isEmpty()) {
            if (params.get("page") != null
                    & !params.get("page").isEmpty()) {
                pageNumber = params.get("page");
            }
            if (params.get("pageSize") != null
                    & !params.get("pageSize").isEmpty()) {
                pageSize = Integer.parseInt(params.get("pageSize"));
            }  else{
                pageSize=10;
            }
        }
        if (pageNumber != null && !pageNumber.isEmpty()) {
            int page = Integer.parseInt(pageNumber);
            // 15, 3, 5
            //            int pageSize = this.env.getProperty("PAGE_SIZE", Integer.class);
            int start = (page - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }

        int i = 0;
        for (Object item : query.getResultList()) {
            //
            Object[] feedback = (Object[]) item;
            Map<String, Object> feedbackInfo = new HashMap<>();

            feedbackInfo.put("feedback_id", feedback[0]);
            feedbackInfo.put("feedback_title", feedback[1]);
            feedbackInfo.put("feedback_content", feedback[2]);
            feedbackInfo.put("customer_id", feedback[3]);
            feedbackInfo.put("customer_name", feedback[4]);

            feedbackMap.add(feedbackInfo);
            i++;
        }
//        Map<String, Object> feedbackInfo1 = new HashMap<>();
//        feedbackInfo1.put("quantity", i);
//        feedbackMap.add(feedbackInfo1);

        return feedbackMap;
    }

    @Override
    public Feedback createFeedback(Map<String, String> params) {
        try {
            Session session = this.factory.getObject().getCurrentSession();
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<Feedback> q = b.createQuery(Feedback.class);
            Feedback f = new Feedback();

            Customer c = session.get(
                    Customer.class,
                    Integer.parseInt(params.get("customer_id"))
            );
            f.setContent(params.get("feedback_content"));
            f.setTitle(params.get("feedback_title"));
            f.setCustomerId(c);
            session.save(f);

            return f;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
