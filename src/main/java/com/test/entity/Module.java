package com.test.entity;

import java.io.Serializable;
import java.util.Set;

public class Module implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mid;
	private String mname;
	private Set<Role> roles;
	
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	@Override
	public String toString() {
		return "Module [mid=" + mid + ", mname=" + mname + ", roles=" + roles + "]";
	}
	
}
