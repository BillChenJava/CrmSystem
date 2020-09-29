package com.crm.biz;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.TestCrmCustomerCategoryDao;
import com.crm.dao.TestCrmCustomerDao;
import com.crm.info.TestCrmCustomer;
import com.crm.info.TestCrmCustomerCategory;

@Transactional
@Service("testCrmCustomerBiz")
public class TestCrmCustomerBiz {
	private TestCrmCustomerDao testCrmCustomerDao;
	
	private TestCrmCustomerCategoryDao testCrmCustomerCategoryDao;

	public void setTestCrmCustomerCategoryDao(TestCrmCustomerCategoryDao testCrmCustomerCategoryDao) {
		this.testCrmCustomerCategoryDao = testCrmCustomerCategoryDao;
	}

	public void setTestCrmCustomerDao(TestCrmCustomerDao testCrmCustomerDao) {
		this.testCrmCustomerDao = testCrmCustomerDao;
	}

	public List<TestCrmCustomer> findAll() {
		return testCrmCustomerDao.findBy("isdelete", 1);
	}

	public List<TestCrmCustomer> findbyCateId(int cateid) {
		return testCrmCustomerDao.findAll(cateid);
	}

	public TestCrmCustomer findOne(int id){
		return testCrmCustomerDao.get(id);
	}
	public void addOne(TestCrmCustomer tcc) {
		try {
			testCrmCustomerDao.save(tcc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void advanceDel(int id) {
		TestCrmCustomer customer= testCrmCustomerDao.get(id);
		customer.setIsdelete(0);
		customer.setDeleteTime(new Date());
		
		try {
			testCrmCustomerDao.save(customer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<TestCrmCustomerCategory> findAllCateglist(){
		return testCrmCustomerCategoryDao.findAll();
	}
	
	public boolean update(TestCrmCustomer testCrmCustomer) {
		try {
			TestCrmCustomer old =testCrmCustomerDao.get(testCrmCustomer.getCustomerId());
			old.setTestCrmCustomerCategory(testCrmCustomer.getTestCrmCustomerCategory());
			old.setCustomerName(testCrmCustomer.getCustomerName());
			old.setAddress(testCrmCustomer.getAddress());
			old.setTel(testCrmCustomer.getTel());
			old.setSite(testCrmCustomer.getSite());
			old.setRemarks(testCrmCustomer.getRemarks());
			old.setDescripe(testCrmCustomer.getDescripe());
			testCrmCustomerDao.save(old);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
