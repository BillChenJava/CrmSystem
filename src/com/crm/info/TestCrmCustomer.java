package com.crm.info;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TestCrmCustomer implements java.io.Serializable {
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
	private Set testCrmCustomerDetailses = new HashSet(0);
	
	public TestCrmCustomer() {}
	
	public TestCrmCustomer(TestCrmCustomerCategory testCrmCustomerCategory,String customerName,
			String address,String tel,String site,String remarks,String descripe,Date creatDate,
			Integer isdelete,Date deleteTime,Set testCrmCustomerDetailses
			) {
		this.testCrmCustomerCategory = testCrmCustomerCategory;
		this.customerName = customerName;
		this.address = address;
		this.tel = tel;
		this.site = site;
		this.remarks = remarks;
		this.descripe = descripe;
		this.creatDate = creatDate;
		this.isdelete = isdelete;
		this.deleteTime = deleteTime;
		this.testCrmCustomerDetailses = testCrmCustomerDetailses;
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

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public Set getTestCrmCustomerDetailses() {
		return testCrmCustomerDetailses;
	}

	public void setTestCrmCustomerDetailses(Set testCrmCustomerDetailses) {
		this.testCrmCustomerDetailses = testCrmCustomerDetailses;
	}
}
