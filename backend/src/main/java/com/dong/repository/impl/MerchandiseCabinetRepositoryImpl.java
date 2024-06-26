package com.dong.repository.impl;

import com.dong.pojo.Customer;
import com.dong.pojo.MerchandiseCabinet;
import com.dong.pojo.MerchandiseCabinetDetail;
import com.dong.pojo.RelativeParkCard;
import com.dong.repository.MerchandiseCabinetRepository;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class MerchandiseCabinetRepositoryImpl
        implements MerchandiseCabinetRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public List<MerchandiseCabinetDetail> getMerchandiseCabinet(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<MerchandiseCabinetDetail> q = b.createQuery(
                MerchandiseCabinetDetail.class
        );
        Root root = q.from(MerchandiseCabinetDetail.class);
        q.select(root);
        Query query = session.createQuery(q);
        String pageNumber = "";
        int pageSize = 10;
        if (params != null && !params.isEmpty()) {
            if (params.get("page") != null && !params.get("page").isEmpty()) {
                pageNumber = params.get("page");
            }
            if (params.get("pageSize") != null && !params.get("pageSize").isEmpty()) {
                pageSize = Integer.parseInt(params.get("pageSize"));
            } else {
                pageSize = 10;
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
        return query.getResultList();
    }

    @Override
    public MerchandiseCabinet getMerchandiseCabinetById(int var1) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(MerchandiseCabinet.class, var1);
    }

    @Override
    public MerchandiseCabinet addMerchandiseCabinet(MerchandiseCabinet var1) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(var1);
        return var1;
    }

    @Override
    public List<MerchandiseCabinetDetail> getMerchandiseByCustomerId(
            int customerId
    ) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<MerchandiseCabinetDetail> q = b.createQuery(
                MerchandiseCabinetDetail.class
        );
        Root root = q.from(MerchandiseCabinetDetail.class);
        q.select(root);
        q.where(b.equal(root.get("customerId"), customerId));
        Query query = session.createQuery(q);
        return query.getResultList();
    }
}
