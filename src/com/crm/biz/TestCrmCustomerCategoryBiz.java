package com.crm.biz;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.TestCrmCustomerCategoryDao;
import com.crm.info.CrmProductCategory;
import com.crm.info.HrEmployee;
import com.crm.info.TestCrmCustomerCategory;
import com.crm.tools.Scopes;
import com.crm.vobj.TestCustomerCategory;

@Transactional
@Service("testCrmCustomerCategoryBiz")
public class TestCrmCustomerCategoryBiz {

	private TestCrmCustomerCategoryDao testCrmCustomerCategoryDao;

	public void setTestCrmCustomerCategoryDao(TestCrmCustomerCategoryDao testCrmCustomerCategoryDao) {
		this.testCrmCustomerCategoryDao = testCrmCustomerCategoryDao;
	}

	public List<TestCrmCustomerCategory> findAll() {
		return testCrmCustomerCategoryDao.findBy("isdelete", 1);
	}

	public List<TestCustomerCategory> getCustomerCategories() {
		return testCrmCustomerCategoryDao.getCustomerCategory();
	}

	public boolean deleteCate(int id) {
		try {
			TestCrmCustomerCategory category = testCrmCustomerCategoryDao.get(id);
			category.setIsdelete(0);
			category.setDeleteTime(new Timestamp(new Date().getTime()));
			category.setHrEmployee((HrEmployee) Scopes.getSessionMap().get("emp"));
			testCrmCustomerCategoryDao.save(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public int getMaxId() {
		return testCrmCustomerCategoryDao.getMaxId();
	}

	public boolean saveCate(TestCrmCustomerCategory category) {
		try {
			testCrmCustomerCategoryDao.save(category);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateCate(TestCrmCustomerCategory category) {
		TestCrmCustomerCategory old = testCrmCustomerCategoryDao.get(category.getId());
		old.setParentid(category.getParentid());
		old.setCustomerCategory(category.getCustomerCategory());
		old.setCustomerIcon(category.getCustomerIcon());

		try {
			testCrmCustomerCategoryDao.save(old);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public List<TestCrmCustomerCategory> findCate() {
		return testCrmCustomerCategoryDao.findAll();
	}

	public List<TestCrmCustomerCategory> findChildCate(Integer parentid) {
		return testCrmCustomerCategoryDao.findChildCate(parentid);
	}

	public List<TestCrmCustomerCategory> findAllChildCate() {
		return testCrmCustomerCategoryDao.findAllChildCate();
	}
	
}
