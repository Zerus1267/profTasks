package com.example.tasks.task_four;

import com.example.tasks.task_one.Address;
import com.example.tasks.task_one.Manager;
import com.example.tasks.task_one.Programmer;
import com.example.tasks.task_one.Worker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class WorkerDAOImpl implements WorkerDAO {

    public WorkerDAOImpl(){
        HibernateUtil.createSessionFactory(new Configuration().configure().addAnnotatedClass(Worker.class)
                .addAnnotatedClass(Programmer.class)
                .addAnnotatedClass(Manager.class)
                .addAnnotatedClass(Address.class));
    }

    @Override
    public void saveWorkers(List<Worker> workers) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            workers.forEach(session::save);
            tx.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }
    }

    @Override
    public List<Worker> getAllWorkers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from Worker ", Worker.class).getResultList();
    }
}
