package com.techchefs.assignment.controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.techchefs.assignment.entity.Bank;
import com.techchefs.assignment.entity.Transaction;
import com.techchefs.assignment.entity.User;

@Repository
public class BankRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean registerUser(String username, String password) {
		Session session= null;
		boolean isNewUser = false;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from users where username = :username");
			query.setParameter("username", username);
			List<User> result = query.list();
			if (result.isEmpty()) {
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				Integer id =(Integer) session.save(user);
				System.out.println("customer is created With Id::"+id);
				isNewUser = true;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return isNewUser;
	}

	public boolean isValidUser(String username, String password) {
		Session session= null;
		boolean isValidUser = false;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from users where username = :username and password = :password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			List<User> result = query.list();
			if (!result.isEmpty()) {
				isValidUser = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return isValidUser;
	}

	public List<Bank> getAllBanksList() {
		Session session= null;
		List<Bank> list = new ArrayList<Bank>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from banks");
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public Bank getBankDetails(String bankname) {
		Session session= null;
		Bank bank = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from banks where bankname = :bankname");
			query.setParameter("bankname", bankname);
			List<Bank> result = query.list();
			if (!result.isEmpty()) {
				bank = result.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bank;
	}

	public boolean isValidBankUser(String bankname, String username, String password) {
		Session session= null;
		boolean isValidUser = false;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from banks where bankname = :bankname and username = :username and password = :password");
			query.setParameter("bankname", bankname);
			query.setParameter("username", username);
			query.setParameter("password", password);
			List<User> result = query.list();
			if (!result.isEmpty()) {
				isValidUser = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return isValidUser;
	}
	
	
	
	

	public List<Transaction> getTransactionData(String account_number) {
		Session session= null;
		List<Transaction> list = new ArrayList<Transaction>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from transactions where account_number = :account_number");
			query.setParameter("account_number", Integer.parseInt(account_number));
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public User findByUsername(String username) {
		Session session= null;
		User user = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("from users where username = :username");
			query.setParameter("username", username);
			List<User> result = query.list();
			if (!result.isEmpty()) {
				user = result.get(0);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;

	}
	

}
