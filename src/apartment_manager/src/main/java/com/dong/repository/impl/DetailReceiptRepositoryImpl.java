package com.dong.repository.impl;

import com.dong.pojo.DetailReceipt;
import com.dong.pojo.Receipt;
import com.dong.repository.DetailReceiptRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class DetailReceiptRepositoryImpl implements DetailReceiptRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public List<DetailReceipt> getDetailReceipt() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<DetailReceipt> q = b.createQuery(DetailReceipt.class);
        Root rDr = q.from(DetailReceipt.class);
        q.select(rDr);
        Query query = session.createQuery(q);
        return query.getResultList();
    }
}
