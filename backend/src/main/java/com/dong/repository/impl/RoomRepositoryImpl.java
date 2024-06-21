package com.dong.repository.impl;

import com.dong.pojo.Customer;
import com.dong.pojo.Room;
import com.dong.repository.RoomRepository;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoomRepositoryImpl implements RoomRepository {

  @Autowired
  private LocalSessionFactoryBean factory;

  @Autowired
  private Environment env;

  @Override
  public List<Room> getRoom(Map<String, String> params) {
    Session s = this.factory.getObject().getCurrentSession();
    Query query = s.createQuery("FROM Room");
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
    return query.getResultList();
  }

  @Override
  public Room getRoomById(int id) {
    Session s = this.factory.getObject().getCurrentSession();
    return s.get(Room.class, id);
  }

  @Override
  public boolean addOrUpdateRoom(Room r) {
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
  public boolean deleteRoom(int id) {
    Session session = Objects
      .requireNonNull(this.factory.getObject())
      .getCurrentSession();
    Room c = this.getRoomById(id);
    try {
      session.delete(c);
      return true;
    } catch (HibernateException ex) {
      ex.printStackTrace();
      return false;
    }
  }
}
