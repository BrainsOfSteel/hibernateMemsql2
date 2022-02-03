package com.hibernate.memsql.memsql.dao;

import com.hibernate.memsql.memsql.model.Devices;
import com.hibernate.memsql.memsql.model.User;
import com.hibernate.memsql.memsql.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class CommonDaoOperation {
    public String addUserAndDevice(String userName, String email, Integer userId, Integer deviceId, String modelNumber, String deviceName){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction tx = null;
        Session session = null;
        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            User user = new User(userId, userName, email);
            Devices devices = new Devices(deviceId, deviceName, modelNumber);
            session.save(user);
            session.save(devices);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            if(tx != null){
                tx.rollback();
            }
            return "FAILED";
        }
        finally {
            if(session != null){
                session.close();
            }
        }
        return "SUCCESS";
    }


}
