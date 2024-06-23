package com.dong.repository.impl;

import javax.persistence.Query;

import com.dong.pojo.Accounts;
import com.dong.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public Accounts getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Accounts Where username=:username");
        q.setParameter("username", username);

        return (Accounts) q.getSingleResult();
    }

    @Override
    public Accounts addUser(Accounts u) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(u);

        return u;
    }

    @Override
    public boolean authUser(String username, String password) {
        Accounts  u = this.getUserByUsername(username);


        return this.passEncoder.matches(password, u.getPassword());
    }

}
