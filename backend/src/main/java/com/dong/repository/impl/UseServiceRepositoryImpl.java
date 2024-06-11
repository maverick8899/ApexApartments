/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dong.repository.impl;

import com.dong.pojo.Customer;
import com.dong.pojo.Feedback;
import com.dong.pojo.Service;
import com.dong.pojo.UseService;
import com.dong.repository.UseServiceRepository;
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
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author MAVERICK
 */
@Repository
@Transactional
public class UseServiceRepositoryImpl implements UseServiceRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<UseService> getUseServicesByIdCustomer(int customerId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<UseService> q = b.createQuery(UseService.class);
        Root uS = q.from(UseService.class);
        q.select(uS);

        q.where(b.equal(uS.get("customerId"), customerId));
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public boolean UpdateUseService(Map<String, String> params) {
        try {
            Session session = this.factory.getObject().getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<UseService> query = cb.createQuery(UseService.class);
            Root<UseService> root = query.from(UseService.class);

            // Xây dựng điều kiện truy vấn
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("customerId").get("id"), Integer.parseInt(params.get("customer_id"))));
            predicates.add(cb.equal(root.get("active"), Boolean.TRUE));

            query.where(predicates.toArray(Predicate[]::new));

            // Thực hiện truy vấn để lấy danh sách các đối tượng UseService
            List<UseService> useServices = session.createQuery(query).getResultList();

            // Kiểm tra nếu danh sách useServices không rỗng
            if (useServices != null && !useServices.isEmpty()) {
                // Duyệt qua danh sách và cập nhật thuộc tính
                for (UseService useService : useServices) {
                    useService.setActive(Boolean.FALSE);
                    session.update(useService);
                }
                return true;
            } else {
                // Đối tượng không tồn tại
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Object> getUseServices(Map<String, String> params) {
        int type = 0;
        String kw = "";
        if (params.get("kw") != null
                && !params.get("kw").isEmpty()
                && params.get("type") != null
                && !params.get("type").isEmpty()) {
            type = Integer.parseInt(params.get("type"));
            kw = params.get("kw");
        }
        List<Predicate> predicates = new ArrayList<>();

        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root uS = q.from(UseService.class);
        Root s = q.from(Service.class);

        //        q.select(f);
        q.multiselect(
                uS.get("id"),
                uS.get("date"),
                uS.get("customerId").get("id"),
                uS.get("customerId").get("name"),
                uS.get("customerId").get("email"),
                s.get("name"),
                s.get("description"),
                s.get("unit"),
                s.get("id")
        );

        if (type == 3) {
            predicates.add(b.equal(uS.get("customerId").get("id"), kw));
//                    q.groupBy( s.get("id"));

        }
        predicates.add(b.equal(uS.get("serviceId").get("id"), s.get("id")));
        predicates.add(b.equal(uS.get("active"), 1));

        q.where(predicates.toArray(Predicate[]::new));
        if (type != 3) {
            q.groupBy(uS.get("customerId").get("id"));
            q.orderBy(b.asc(uS.get("customerId").get("id")));
        }

        Query query = session.createQuery(q);
        List<Object> feedbackMap = new ArrayList<>();
        int i = 0;

        for (Object item : query.getResultList()) {
            //
            Object[] feedback = (Object[]) item;
            Map<String, Object> feedbackInfo = new HashMap<>();

            feedbackInfo.put("useService_id", feedback[0]);
            feedbackInfo.put("useService_date", feedback[1]);
            feedbackInfo.put("customer_id", feedback[2]);
            feedbackInfo.put("customer_name", feedback[3]);
            feedbackInfo.put("customer_email", feedback[4]);
            feedbackInfo.put("service_name", feedback[5]);
            feedbackInfo.put("service_description", feedback[6]);
            feedbackInfo.put("service_unit", feedback[7]);
            feedbackInfo.put("service_id", feedback[8]);

            feedbackMap.add(feedbackInfo);
            i++;
        }
        return feedbackMap;
    }

}
