package com.dong.repository.impl;

import com.dong.pojo.Customer;
import com.dong.pojo.MerchandiseCabinet;
import com.dong.pojo.MerchandiseCabinetDetail;
import com.dong.pojo.RelativeParkCard;
import com.dong.repository.MerchandiseCabinetRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Transactional
public class MerchandiseCabinetRepositoryImpl implements MerchandiseCabinetRepository {
    @Autowired
    private LocalSessionFactoryBean factory;


    @Override
    public List<MerchandiseCabinetDetail> getMerchandiseCabinet(Map<String, String> var1) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<MerchandiseCabinetDetail> q = b.createQuery(MerchandiseCabinetDetail.class);
        Root root = q.from(MerchandiseCabinetDetail.class);
        q.select(root);
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public MerchandiseCabinet getMerchandiseCabinetById(int var1) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(MerchandiseCabinet.class, var1);    }

    @Override
    public MerchandiseCabinet addMerchandiseCabinet(MerchandiseCabinet var1) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(var1);
        return var1;
    }

    @Override
    public List<MerchandiseCabinetDetail> getMerchandiseByCustomerId(int customerId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<MerchandiseCabinetDetail> q = b.createQuery(MerchandiseCabinetDetail.class);
        Root root = q.from(MerchandiseCabinetDetail.class);
        q.select(root);
        q.where(b.equal(root.get("customerId"),customerId));
        Query query = session.createQuery(q);
        return query.getResultList();    }
}
