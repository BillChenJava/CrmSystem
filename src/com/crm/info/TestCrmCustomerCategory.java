package com.crm.info;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CrmProductCategory entity. @author MyEclipse Persistence Tools
 */

public class TestCrmCustomerCategory implements java.io.Serializable {

	// Fields

	private Integer id;
	private HrEmployee hrEmployee;
	private String customerCategory;
	private Integer parentid;
	private String customerIcon;
	private Integer isdelete;
	private Date deleteTime;
	private Set crmCustomer = new HashSet(0);

	// Constructors

	/** default constructor */
	public TestCrmCustomerCategory() {
	}

	/** full constructor */
	public TestCrmCustomerCategory(HrEmployee hrEmployee, String customerCategory,
			Integer parentid, String customerIcon, Integer isdelete,
			Date deleteTime, Set crmCustomer) {
		this.hrEmployee = hrEmployee;
		this.customerCategory = customerCategory;
		this.parentid = parentid;
		this.customerIcon = customerIcon;
		this.isdelete = isdelete;
		this.deleteTime = deleteTime;
		this.crmCustomer = crmCustomer;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public HrEmployee getHrEmployee() {
		return this.hrEmployee;
	}

	public void setHrEmployee(HrEmployee hrEmployee) {
		this.hrEmployee = hrEmployee;
	}

	public Integer getParentid() {
		return this.parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public Integer getIsdelete() {
		return this.isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public Date getDeleteTime() {
		return this.deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public String getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}

	public String getCustomerIcon() {
		return customerIcon;
	}

	public void setCustomerIcon(String customerIcon) {
		this.customerIcon = customerIcon;
	}

	public Set getCrmCustomer() {
		return crmCustomer;
	}

	public void setCrmCustomer(Set crmCustomer) {
		this.crmCustomer = crmCustomer;
	}
	
}