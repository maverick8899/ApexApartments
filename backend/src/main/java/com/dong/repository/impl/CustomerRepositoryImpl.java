package com.dong.repository.impl;

import com.dong.pojo.Accounts;
import com.dong.pojo.Customer;
import com.dong.repository.CustomerRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Customer> getCustomer(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Customer> q = b.createQuery(Customer.class);
        Root<Customer> root = q.from(Customer.class);
        q.select(root);

        if (params != null && !params.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            String type = params.get("type");
            String kw = params.get("kw");


            if (kw != null && !kw.isEmpty()) {
                switch (type) {
                    case "1": // Search by id
                        try {
                            int id = Integer.parseInt(kw);
                            predicates.add(b.equal(root.get("id"), id));
                        } catch (NumberFormatException e) {
                            // Handle invalid id format
                            predicates.add(b.equal(root.get("id"), -1)); // This will return no results
                        }
                        break;
                    case "2": // Search by name
                        predicates.add(b.like(root.get("name"), "%" + kw + "%"));
                        break;
                    case "3": // Search by phone number
                        predicates.add(b.like(root.get("phoneNumber"), "%" + kw + "%"));
                        break;
                }
            }

            q.where(predicates.toArray(new Predicate[0]));
        }

        q.orderBy(b.desc(root.get("id")));
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Long countCustomer() {
        return null;
    }

    @Override
    public List<Customer> getCustomer() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery q = b.createQuery(Customer.class);
        Root rC = q.from(Customer.class);
        q.select(rC);

//        q.multiselect(rC.get("id"), rC.get("name"));
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    @Override
    public boolean addOrUpdateCustomer(Customer c) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (c.getId() == null) {
                s.save(c);
            } else {
                s.update(c);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        Session session = Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
        return session.get(Customer.class, id);
    }

    @Override
    public boolean deleteCustomer(int id) {
        Session session = Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
        Customer c = this.getCustomerById(id);
        try {
            session.delete(c);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Customer> getCustomersByAccountId(Integer accountId) {
        Session session = this.factory.getObject().getCurrentSession();
        String hql = "FROM Customer c WHERE c.accountId.id = :accountId";
        List<Customer> customers = session.createQuery(hql, Customer.class)
                .setParameter("accountId", accountId)
                .getResultList();
        return customers;
    }

}
