package com.hibernate.memsql.memsql.dao;

import com.hibernate.memsql.memsql.model.User;
import com.hibernate.memsql.memsql.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserOperationDao {
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        //open a Session
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);
            cr.select(root);
            Query<User> query = session.createQuery(cr);
            users = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public String updateUser(String name, String newName){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction tx = null;
        Session session = null;
        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Query q=session.createQuery("update User set name=:newName where name=:name");
            q.setParameter("name",name);
            q.setParameter("newName",newName);
            int status = q.executeUpdate();
            System.out.println("Status = "+status);
            String su = "abc";
            su.substring(0);
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

    public String saveUser(Integer id, String name, String email){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Transaction tx = null;
        Session session = null;
        try{
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            User user = new User(id, name, email);
            session.save(user);
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
