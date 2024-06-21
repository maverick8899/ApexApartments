package com.dong.repository.impl;

import javax.persistence.Query;

import com.dong.pojo.Accounts;
import com.dong.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Accounts getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Accounts Where username=:username");
        q.setParameter("username", username);

        return (Accounts) q.getSingleResult();
    }

    @Override
    public void addUser(Accounts user) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(user);
    }

}
