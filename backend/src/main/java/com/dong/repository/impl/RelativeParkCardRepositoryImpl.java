//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dong.repository.impl;

import com.dong.pojo.RelativeParkCard;
import com.dong.repository.RelativeParkCardRepository;
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
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class RelativeParkCardRepositoryImpl implements RelativeParkCardRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    public RelativeParkCardRepositoryImpl() {
    }

    public List<RelativeParkCard> getRelativeParkCard(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<RelativeParkCard> q = b.createQuery(RelativeParkCard.class);
        Root root = q.from(RelativeParkCard.class);
        q.select(root);
        q.orderBy(new Order[]{b.desc(root.get("id"))});
        Query query = session.createQuery(q);
        return query.getResultList();
    }

    public RelativeParkCard getRelativeParkCardById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(RelativeParkCard.class, id);
    }

    public boolean deleteRelativeParkCard(int id) {
        Session session = ((SessionFactory)Objects.requireNonNull(this.factory.getObject())).getCurrentSession();
        RelativeParkCard c = this.getRelativeParkCardById(id);

        try {
            session.delete(c);
            return true;
        } catch (HibernateException var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public RelativeParkCard addRelativeParkCard(RelativeParkCard r) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(r);
        return r;
    }
}
