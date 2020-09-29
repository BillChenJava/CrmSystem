package com.crm.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.crm.biz.TestCrmCustomerBiz;
import com.crm.biz.TestCrmCustomerCategoryBiz;
import com.crm.info.CrmProduct;
import com.crm.info.CrmProductCategory;
import com.crm.info.TestCrmCustomer;
import com.crm.info.TestCrmCustomerCategory;
import com.crm.tools.Scopes;
import com.crm.tools.TestWebTools;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Controller("testCrmCustomerAction")
public class TestCrmCustomerAction extends ActionSupport {

	public TestCrmCustomerCategoryBiz testCrmCustomerCategoryBiz;

	public TestCrmCustomerBiz testCrmCustomerBiz;

	private int id;
	private Integer customerId;
	private TestCrmCustomerCategory testCrmCustomerCategory;
	private String customerName;
	private String address;
	private String tel;
	private String site;
	private String remarks;
	private String descripe;
	private Date creatDate;
	private Integer isdelete;
	private Date deleteTime;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public TestCrmCustomerCategory getTestCrmCustomerCategory() {
		return testCrmCustomerCategory;
	}

	public void setTestCrmCustomerCategory(TestCrmCustomerCategory testCrmCustomerCategory) {
		this.testCrmCustomerCategory = testCrmCustomerCategory;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDescripe() {
		return descripe;
	}

	public void setDescripe(String descripe) {
		this.descripe = descripe;
	}

	public Date getCreatDate() {
		return creatDate;
	}

	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public TestCrmCustomerAction() {
	}

	public TestCrmCustomerCategoryBiz getTestCrmCustomerCategoryBiz() {
		return testCrmCustomerCategoryBiz;
	}

	public TestCrmCustomerBiz getTestCrmCustomerBiz() {
		return testCrmCustomerBiz;
	}

	public void setTestCrmCustomerBiz(TestCrmCustomerBiz testCrmCustomerBiz) {
		this.testCrmCustomerBiz = testCrmCustomerBiz;
	}

	public void setTestCrmCustomerCategoryBiz(TestCrmCustomerCategoryBiz testCrmCustomerCategoryBiz) {
		this.testCrmCustomerCategoryBiz = testCrmCustomerCategoryBiz;
	}

	public String execute() {
		Scopes.getRequestMap().put("categories", testCrmCustomerCategoryBiz.getCustomerCategories());
		Scopes.getRequestMap().put("customerlist", testCrmCustomerBiz.findbyCateId(id));

		return SUCCESS;
	}

	public String findOne() {
		List<TestCrmCustomerCategory> findAllCateglist = testCrmCustomerBiz.findAllCateglist();
		Scopes.getRequestMap().put("categs", findAllCateglist);
		//get customer type
		if(customerId>0) {
			
			TestCrmCustomer findOne = testCrmCustomerBiz.findOne(customerId);
			Scopes.getRequestMap().put("oneCus", findOne);
			String parentNameString = findOne.getTestCrmCustomerCategory().getParentid() + "";
			for (TestCrmCustomerCategory testCrmCustomerCategory : findAllCateglist) {
				Integer id2 = testCrmCustomerCategory.getId();
				Integer parentid = findOne.getTestCrmCustomerCategory().getParentid();
				
				if (parentid.intValue() == id2.intValue()) {
					parentNameString = testCrmCustomerCategory.getCustomerCategory();
				}
			}
			Scopes.getRequestMap().put("parentNameString", parentNameString);
			return "finddone";
		}
		return "toadd";
	}

	public String addOne() {
		TestCrmCustomer tcc = new TestCrmCustomer();
		TestCrmCustomerCategory tccc = new TestCrmCustomerCategory();
		tccc.setId(cateid);
		tcc.setCustomerId(customerId);
		tcc.setTestCrmCustomerCategory(tccc);
		tcc.setCustomerName(customerName);
		tcc.setAddress(address);
		tcc.setTel(tel);
		tcc.setSite(site);
		tcc.setRemarks(remarks);
		tcc.setDescripe(descripe);
		tcc.setCreatDate(creatDate);
		tcc.setIsdelete(1);
		tcc.setDeleteTime(deleteTime);
		
		testCrmCustomerBiz.addOne(tcc);
		
		return "add";
	}
	
	private int cateid;
	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public int getCateid() {
		return cateid;
	}

	public void setCateid(int cateid) {
		this.cateid = cateid;
	}

	public String ajaxGetCustomer() {
		List<TestCrmCustomer> customers = testCrmCustomerBiz.findbyCateId(cateid);
		
		TestWebTools.jsonClearCustomer(customers);
		String data = TestWebTools.convertJson(customers);
		Scopes.getRequestMap().put("data", data);
		return "ajax";
	}
	
	public String advanceDel() {
		testCrmCustomerBiz.advanceDel(customerId);
		customerId=0;
		return "delfalse";
	}
	
	public TestCrmCustomer testCrmCustomer;
	
	
	public void setTestCrmCustomer(TestCrmCustomer testCrmCustomer) {
		this.testCrmCustomer = testCrmCustomer;
	}

	public String updateOne() {
		testCrmCustomerBiz.update(testCrmCustomer);
		return "update";
	}
}
