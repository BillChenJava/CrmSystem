package com.crm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.crm.info.TestCrmCustomerCategory;
import com.crm.vobj.TestCustomerCategory;
import com.haha.hibernate.HibernateDao;

/**
 * �ͻ���Ʒ�����
 * 
 * @author ��
 * 
 */
@Repository("testCrmCustomerCategoryDao")
public class TestCrmCustomerCategoryDao extends HibernateDao<TestCrmCustomerCategory, Integer> {

	public int getMaxId() {
		try {
			int id = (Integer) getSession().createCriteria(TestCrmCustomerCategory.class)
					.setProjection(Projections.max("id")).uniqueResult();

			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	public List<TestCustomerCategory> getCustomerCategory() {

		List<TestCrmCustomerCategory> list = this.getSession().createCriteria(TestCrmCustomerCategory.class)
				.add(Restrictions.eq("isdelete", 1)).add(Restrictions.eq("parentid", 0)).list();

		List<TestCustomerCategory> categories = new ArrayList<TestCustomerCategory>();
		for (TestCrmCustomerCategory ccc : list) {
			TestCustomerCategory category = new TestCustomerCategory();
			category.setCustomerCategory(ccc.getCustomerCategory());
			category.setCustomerIcon(ccc.getCustomerIcon());
			category.setDeleteTime(ccc.getDeleteTime());
			category.setHrEmployee(ccc.getHrEmployee());
			category.setIsdelete(ccc.getIsdelete());
			category.setParentid(ccc.getParentid());
			category.categories = this.getSession().createCriteria(TestCrmCustomerCategory.class)
					.add(Restrictions.eq("isdelete", 1)).add(Restrictions.eq("parentid", ccc.getId())).list();
			categories.add(category);
		}
		return categories;
	}

	/**
	 * ajax
	 * 
	 * @param padid
	 * @return
	 */
	public List<TestCrmCustomerCategory> findChildCate(int parentid) {
		List<TestCrmCustomerCategory> list = this.getSession().createCriteria(TestCrmCustomerCategory.class)
				.add(Restrictions.eq("isdelete", 1)).add(Restrictions.eq("parentid", parentid)).list();
		return list;
	}

	/**
	 * ajax��ѯȫ�������
	 * 
	 * @return
	 */
	public List<TestCrmCustomerCategory> findAll() {
		return this.getSession().createCriteria(TestCrmCustomerCategory.class).add(Restrictions.eq("isdelete", 1))
				.add(Restrictions.eq("parentid", 0)).list();
	}

	/**
	 * ajax ��ѯȫ�����ӽڵ�
	 * 
	 * @return
	 */
	public List<TestCrmCustomerCategory> findAllChildCate() {
		return this.getSession().createCriteria(TestCrmCustomerCategory.class).add(Restrictions.eq("isdelete", 1))
				.add(Restrictions.ne("parentid", 0)).list();
	}
}
