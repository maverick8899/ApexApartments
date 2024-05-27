package com.dong.repository.impl;

import com.dong.pojo.Customer;
import com.dong.pojo.Room;
import com.dong.repository.RoomRepository;
import java.util.List;
import java.util.Objects;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoomRepositoryImpl implements RoomRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public List<Room> getRoom() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Room");

        return q.getResultList();
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
        Session session = Objects.requireNonNull(this.factory.getObject()).getCurrentSession();
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
