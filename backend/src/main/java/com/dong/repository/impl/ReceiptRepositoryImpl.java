package com.dong.repository.impl;

import com.dong.DTO.ReceiptDTO;
import com.dong.pojo.DetailReceipt;
import com.dong.pojo.Receipt;
import com.dong.pojo.Service;
import com.dong.pojo.UseService;
import com.dong.repository.ReceiptRepository;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
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
public class ReceiptRepositoryImpl implements ReceiptRepository {

  @Autowired
  private Environment env;

  @Autowired
  private LocalSessionFactoryBean factory;

  @Override
  public Receipt getReceiptById(int id) {
    Session session = this.factory.getObject().getCurrentSession();
    return session.get(Receipt.class, id);
  }

  @Override
  public boolean addOrUpdateReceipt(Receipt r) {
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
  public List<ReceiptDTO> getReceipt(Map<String, String> params) {
    //? isPay ở đây là selection chứ không phải là giá trị isPay trong db
    int isPay = 0;
    int type = 0;
    String kw = "";
    if (
      params.get("kw") != null &&
      !params.get("kw").isEmpty() &&
      params.get("type") != null &&
      !params.get("type").isEmpty()
    ) {
      type = Integer.parseInt(params.get("type"));
      kw = params.get("kw");
    }

    Session session = this.factory.getObject().getCurrentSession();
    //        Session session = this.factory.getObject().getCurrentSession();
    CriteriaBuilder b = session.getCriteriaBuilder();
    CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
    List<Predicate> predicates = new ArrayList<>();

    Root r = q.from(Receipt.class);
    Root rD = q.from(DetailReceipt.class);
    Root s = q.from(Service.class);
    Root uS = q.from(UseService.class);

    //        q.select(r);
    q.multiselect(
      r.get("id"),
      r.get("total"),
      r.get("date"),
      r.get("isPay"),
      r.get("customerId").get("id"),
      r.get("customerId").get("name"),
      r.get("customerId").get("email"),
      rD.get("quantity"),
      s.get("id"),
      s.get("name"),
      s.get("description"),
      s.get("unit"),
      uS.get("date"),
      //                b.sum(r.get("cost"))
      rD.get("cost")
    );

    if (type != 0 && !kw.isEmpty() && kw != null) {
      if (type == 1) {
        predicates.add(b.equal(r.get("customerId").get("id"), kw));
      }
      if (type == 2) {
        predicates.add(
          b.like(r.get("customerId").get("name"), String.format("%%%s%%", kw))
        );
      }
      if (type == 3) {
        predicates.add(b.equal(r.get("id"), kw));
        predicates.add(b.equal(uS.get("active"), 1));
        predicates.add(b.equal(r.get("id"), rD.get("receiptId")));
        predicates.add(b.equal(rD.get("serviceId").get("id"), s.get("id")));
        predicates.add(b.equal(s.get("id"), uS.get("serviceId")));

        q.where(predicates.toArray(Predicate[]::new));
        q.groupBy(r.get("id"), r.get("customerId").get("id"), s.get("id"));
        q.orderBy(b.asc(r.get("id")));
      }
      if (type == 4) {
        predicates.add(
          b.equal(b.function("DAY", Integer.class, r.get("date")), kw)
        );
      }
      if (type == 5) {
        predicates.add(
          b.equal(b.function("MONTH", Integer.class, r.get("date")), kw)
        );
      }
      if (type == 6) {
        predicates.add(
          b.equal(b.function("YEAR", Integer.class, r.get("date")), kw)
        );
      }
    }

    if (params.get("isPay") != null) {
      isPay = Integer.parseInt(params.get("isPay"));
    }

    if (isPay == 2) {
      predicates.add(b.equal(r.get("isPay"), 0));
    } else if (isPay == 1) {
      predicates.add(b.equal(r.get("isPay"), 1));
    }

    if (type != 3) {
      //* join receipt to receipt detail
      predicates.add(b.equal(r.get("id"), rD.get("receiptId")));
      //* join receipt detail to service
      predicates.add(b.equal(rD.get("serviceId").get("id"), s.get("id")));
      //
      //        //* join service to use_service
      predicates.add(b.equal(s.get("id"), uS.get("serviceId")));

      //        //? recepit is not pay yet
      //        predicates.add(b.equal(rD.get("active"), 1));
      q.where(predicates.toArray(Predicate[]::new));
      q.groupBy(r.get("id"));

      q.orderBy(b.asc(r.get("id")));
    }
    Query query = session.createQuery(q);
    String pageNumber = "";
    int pageSize = 0;
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
    List<ReceiptDTO> invoices = new ArrayList<>();

    for (Object item : query.getResultList()) {
      if (item instanceof Object[]) {
        Object[] itemArray = (Object[]) item;
        Long dateTimestamp = itemArray[2] instanceof Date
          ? ((Date) itemArray[2]).getTime()
          : Long.parseLong(itemArray[2].toString());
        Long discountDateTimestamp = itemArray[12] instanceof Date
          ? ((Date) itemArray[12]).getTime()
          : Long.parseLong(itemArray[12].toString());

        invoices.add(
          new ReceiptDTO(
            itemArray[0], // receiptId
            itemArray[1], // receiptDate
            parseIntToDate(dateTimestamp),
            itemArray[3], //isPay customerId
            itemArray[4], // customerName
            itemArray[5], // customerEmail
            itemArray[6], // receiptDetailQuantity
            itemArray[7], // serviceId
            itemArray[8], // serviceName
            itemArray[9], // serviceDescription
            itemArray[10], // serviceUnit
            itemArray[11], // receiptDetailCost
            parseIntToDate(discountDateTimestamp), // discountDescription (giả sử là discountDescription)
            itemArray[13] // receiptDetailCost
          )
        );
      }
    }
    return invoices;
  }

  public Object parseIntToDate(Long timestamp) {
    //        long timestamp = 1640970000000L; // Giá trị timestamp dạng long
    Instant instant = Instant.ofEpochMilli(timestamp); // Chuyển timestamp sang Instant
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Định dạng thời gian mong muốn
    formatter.setTimeZone(TimeZone.getDefault()); // Cài đặt múi giờ mặc định
    Date date = Date.from(instant); // Chuyển Instant sang Date
    String formattedDate = formatter.format(date); // Định dạng Date thành chuỗi
    System.out.println("Date: " + formattedDate); // In ra ngày đã định dạng
    return formattedDate;
  }

  @Override
  public boolean deleteReceipt(int id) {
    Session session = this.factory.getObject().getCurrentSession();
    Receipt r = this.getReceiptById(id);
    try {
      session.delete(r);
      return true;
    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean payment(Map<String, String> params) {
    Session session = this.factory.getObject().getCurrentSession();
    Receipt r = this.getReceiptById(Integer.parseInt(params.get("receiptId")));
    try {
      r.setIsPay(Boolean.TRUE);
      session.update(r);
      return true;
    } catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }
  }
}
