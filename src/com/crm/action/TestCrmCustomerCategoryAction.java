package com.crm.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.crm.biz.TestCrmCustomerCategoryBiz;
import com.crm.info.CrmProductCategory;
import com.crm.info.TestCrmCustomerCategory;
import com.crm.tools.Scopes;
import com.crm.tools.TestWebTools;
import com.crm.tools.WebTools;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
@Controller("testCrmCustomerCategoryAction")
public class TestCrmCustomerCategoryAction extends ActionSupport {

	private TestCrmCustomerCategoryBiz testCrmCustomerCategoryBiz;
	

	public void setTestCrmCustomerCategoryBiz(TestCrmCustomerCategoryBiz testCrmCustomerCategoryBiz) {
		this.testCrmCustomerCategoryBiz = testCrmCustomerCategoryBiz;
	}

	public String execute() {
		List<TestCrmCustomerCategory> categories = testCrmCustomerCategoryBiz.findAll();
		Scopes.getRequestMap().put("cates", categories);
		return SUCCESS;
	}

	public String delCate() {
		if (testCrmCustomerCategoryBiz.deleteCate(id)) {
			Scopes.getRequestMap().put("data", "success");
		} else {
			Scopes.getRequestMap().put("data", "failed");
		}
		return "ajax";
	}

	public String getMaxId() {
		int id = testCrmCustomerCategoryBiz.getMaxId() + 1;
		Scopes.getRequestMap().put("data", +id);
		return "ajax";
	}

	private Integer parentid;
	private String customerCategory, customerIcon;
	private int id;

	public void setId(int id) {
		this.id = id;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}

	public void setCustomerIcon(String customerIcon) {
		this.customerIcon = customerIcon;
	}

	public String saveCate() {
		TestCrmCustomerCategory category = new TestCrmCustomerCategory();
		category.setParentid(parentid);
		category.setCustomerCategory(customerCategory);
		category.setCustomerIcon(customerIcon);
		category.setIsdelete(1);
		if (testCrmCustomerCategoryBiz.saveCate(category)) {
			Scopes.getRequestMap().put("data", "success");
		} else {
			Scopes.getRequestMap().put("data", "failed");
		}
		return "ajax";
	}

	public String updateCate() {
		TestCrmCustomerCategory category = new TestCrmCustomerCategory();
		category.setId(id);
		category.setCustomerCategory(customerCategory);
		category.setParentid(parentid);
		category.setCustomerIcon(customerIcon);
		
		if(testCrmCustomerCategoryBiz.updateCate(category)) {
			Scopes.getRequestMap().put("data","success");
		}else {
			Scopes.getRequestMap().put("data","failed");
		}
		return "ajax";
	}
	
	public String ajaxGetCate() {
		List<TestCrmCustomerCategory> categories = testCrmCustomerCategoryBiz.findCate();
		TestWebTools.jsonClearCategories(categories);
		String data = TestWebTools.convertJson(categories);
		Scopes.getRequestMap().put("data", data);
		return "ajax";
	}

	public String ajaxGetChildCate() {
		List<TestCrmCustomerCategory> categories =testCrmCustomerCategoryBiz.findChildCate(parentid);
		TestWebTools.jsonClearCategories(categories);
		String data = TestWebTools.convertJson(categories);
		Scopes.getRequestMap().put("data", data);
		return "ajax";
	}

	public String ajaxGetAllChildCate() {
		List<TestCrmCustomerCategory> categories = testCrmCustomerCategoryBiz.findAllChildCate();
		TestWebTools.jsonClearCategories(categories);
		String data = TestWebTools.convertJson(categories);
		Scopes.getRequestMap().put("data", data);
		return "ajax";
	}
}