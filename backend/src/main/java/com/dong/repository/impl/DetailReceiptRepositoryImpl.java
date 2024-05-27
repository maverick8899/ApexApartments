package com.dong.repository.impl;

import com.dong.pojo.Customer;
import com.dong.pojo.DetailReceipt;
import com.dong.pojo.Receipt;
import com.dong.pojo.Service;
import com.dong.pojo.UseService;
import com.dong.repository.CustomerRepository;
import com.dong.repository.DetailReceiptRepository;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import java.util.Map;
import java.util.Set;
import javax.persistence.criteria.Predicate;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

    @Override
    public boolean addOrUpdateDetailReceipt(Map<String, String> params) {
        // {customer_id=1, date=2024-05-02, service_id=1, detail_receipt_quantity=1, detail_receipt_cost=1, receipt_isPay=0}
        try {
            Session session = this.factory.getObject().getCurrentSession();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            CriteriaBuilder b = session.getCriteriaBuilder();
            CriteriaQuery<Object> q = b.createQuery(Object.class);

            Customer c = session.get(Customer.class, Integer.parseInt(params.get("customer_id")));
            Receipt r = new Receipt();

            int i1 = 0;
            double total = 0;
            while (params.containsKey("services[" + i1 + "].service_id")) {
                System.out.println(params.get("services[" + i1 + "].service_id"));
                System.out.println(params.get("services[" + i1 + "].date"));
                System.out.println(params.get("services[" + i1 + "].detail_receipt_cost"));
                System.out.println(params.get("services[" + i1 + "].detail_receipt_quantity"));

                //? receipt_total
                total += (Double.valueOf(params.get("services[" + i1 + "].detail_receipt_cost")));
                i1++;
            }
//            System.out.println("total" + new BigDecimal(total));
//            //? receipt
            boolean isPay = "1".equals(params.get("receipt_isPay"));
            r.setIsPay(isPay);
            r.setTotal(new BigDecimal(total));
            r.setCustomerId(c);
            r.setDate(new Date());
            session.save(r);

//            ////////////////////
            int i2 = 0;
            while (params.containsKey("services[" + i2 + "].service_id")) {
                DetailReceipt dR = new DetailReceipt();
                UseService uS = new UseService();
                Service s = session.get(Service.class, Integer.parseInt(params.get("services[" + i2 + "].service_id")));

                //? use Service
                uS.setDate(formatter.parse(params.get("services[" + i2 + "].date")));
                uS.setCustomerId(c);
                uS.setServiceId(s);
                uS.setActive(Boolean.TRUE);

                //? detail receipt
                dR.setQuantity(Integer.parseInt(params.get("services[" + i2 + "].detail_receipt_quantity")));
                dR.setServiceId(s);
                dR.setReceiptId(r); // Liên kết DetailReceipt với Receipt
                dR.setCost(new BigDecimal(params.get("services[" + i2 + "].detail_receipt_cost")));
                //? If isPay=0 then detail receipt is active
                //? Nếu hóa đơn chưa trả thì active
                dR.setActive(!isPay);
                // Lưu các đối tượng
                session.save(uS); // Lưu Service nếu đây là đối tượng mới
                session.save(dR); // Lưu DetailReceipt
                i2++;
            } 
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public DetailReceipt getDetailReceiptById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(DetailReceipt.class, id);
    }
    
    @Override
    public boolean deleteDetailReceipt(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        DetailReceipt r = this.getDetailReceiptById(id);
        try {
            session.delete(r);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
