package com.crm.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.crm.info.TestCrmCustomer;
import com.haha.hibernate.HibernateDao;

@Repository("testCrmCustomerDao")
public class TestCrmCustomerDao extends HibernateDao<TestCrmCustomer, Integer> {
	
	public List<TestCrmCustomer> findAll(int id) {
		if(id<1) {
			return getSession().createCriteria(TestCrmCustomer.class).add(Restrictions.eq("isdelete",1)).list();
		}else {
			return getSession().createCriteria(TestCrmCustomer.class).add(Restrictions.eq("isdelete",1)).add(Restrictions.eq("testCrmCustomerCategory.id",id)).list();
		}
	}
	
}
