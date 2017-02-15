package com.student.demo.dao;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.student.demo.pojo.User;
import com.student.demo.util.HibernateUtil;

@Repository
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public User getUserByUsernameAndPassword(String username, String password)	{
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
		
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			
			Query query = session.createQuery("from User where username=:username and password=:password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			
			User user = (User) query.uniqueResult();			
			
			return user;
			
		}
		catch (Exception e){
			return null;
		}
		finally	{
			session.close();
		}
		
		
	}

	public List<User> findAll() {
		
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			
			Query query = session.createQuery("from User");
			
			return query.list();
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
		finally	{
			session.close();
		}
		
	}

	public void insert(User user) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			session.save(user);
			
			session.getTransaction().commit();	
			
		}
		catch (Exception e){
			if (session.getTransaction() != null)	{
				session.getTransaction().rollback();
			}
		}
		finally	{
			session.close();
		}
		
		
	}

	public void delete(Integer id) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.createQuery("delete from User where id=:param");
			query.setParameter("param", id);
			query.executeUpdate();
			
			session.getTransaction().commit();	
			
		}
		catch (Exception e){
			if (session.getTransaction() != null)	{
				session.getTransaction().rollback();
			}
		}
		finally	{
			session.close();
		}
	}


	public User findById(Integer id) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			User user = (User) session.get(User.class, id);
			
			return user;
		}
		catch (Exception e){
			return null;
		}
		finally	{
			session.close();
		}
		
		
	}

	public void update(User user) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = null;
		
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			session.update(user);
			
			session.getTransaction().commit();	
			
		}
		catch (Exception e){
			if (session.getTransaction() != null)	{
				session.getTransaction().rollback();
			}
		}
		finally	{
			session.close();
		}
		
	}
	
	

}
