package com.dong.repository.impl;

import com.dong.pojo.Customer;
import com.dong.pojo.Service;
import com.dong.pojo.UseService;
import com.dong.repository.ServiceRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
@org.springframework.stereotype.Service
@Transactional
public class ServiceRepositoryImpl implements ServiceRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public List<Service> getServicesByIdCustomer(int customerId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Service> q = b.createQuery(Service.class);
        Root rS= q.from(Service.class);
        Root rUs= q.from(UseService.class);
        q.select(rS);
        q.where(b.equal(rS.get("id"),rUs.get("serviceId")));
        q.where(b.equal(rUs.get("customerId"), customerId));
        Query query = session.createQuery(q);
        return query.getResultList();
    }
    @Override
    public boolean addOrUpdateService(Service r) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (r.getId() == null) {
                s.save(r);
            } else {
                s.update(r);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Service> getServices() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Service ");
        return q.getResultList();
    }
    @Override
    public Service getServiceById(int id) {
        Session session = Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
        return session.get(Service.class, id);
    }
    @Override
    public boolean deleteSer(int id) {
        Session session = Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
        Service c = this.getServiceById(id);
        try {
            session.delete(c);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
