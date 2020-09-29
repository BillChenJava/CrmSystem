package com.crm.tools;

import java.util.List;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.context.annotation.Scope;

import com.crm.info.CrmProductCategory;
import com.crm.info.TestCrmCustomer;
import com.crm.info.TestCrmCustomerCategory;
@Scope("prototype")
public class TestWebTools {
	
	public static void jsonClearCustomer(List<TestCrmCustomer> customers) {
		for (TestCrmCustomer cust : customers) {
			cust.setTestCrmCustomerDetailses(null);
			cust.setTestCrmCustomerCategory(null);
		}
	}
	
	public static void jsonClearCategories(List<TestCrmCustomerCategory> categories) {
		for (TestCrmCustomerCategory category : categories) {
			category.setCrmCustomer(null);
		}

	}
	
	public static String convertJson(Object object) {
		String data = "";
		try {
			data = JSONUtil.serialize(object);
		} catch (JSONException e) {
			e.printStackTrace();
			data = "{}";
		}
		return data;
	}
}
