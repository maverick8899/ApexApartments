//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.repository.impl;

import com.dong.pojo.Merchandise;
import com.dong.repository.MerchandiseRepository;
import java.util.Objects;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class MerchandiseRepositoryImpl implements MerchandiseRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    public MerchandiseRepositoryImpl() {
    }

    public Merchandise getMerchandiseById(int id) {
        Session session = ((SessionFactory)Objects.requireNonNull(this.factory.getObject())).getCurrentSession();
        return (Merchandise)session.get(Merchandise.class, id);
    }

    public boolean addOrUpdateMerchandise(Merchandise r) {
        Session s = this.factory.getObject().getCurrentSession();

        try {
            if (r.getId() == null) {
                s.save(r);
            } else {
                s.update(r);
            }

            return true;
        } catch (HibernateException var4) {
            var4.printStackTrace();
            return false;
        }
    }
}
