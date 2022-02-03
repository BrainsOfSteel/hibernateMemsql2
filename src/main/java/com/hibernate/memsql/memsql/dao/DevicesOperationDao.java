package com.hibernate.memsql.memsql.dao;

import com.hibernate.memsql.memsql.model.Devices;
import com.hibernate.memsql.memsql.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class DevicesOperationDao {
    public List<Devices> getAllDevices(){
        List<Devices> devices = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //create a Session
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Devices> cr = cb.createQuery(Devices.class);
            Root<Devices> root = cr.from(Devices.class);
            cr.select(root);
            Query<Devices> query = session.createQuery(cr);
            devices = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return devices;
    }
}
