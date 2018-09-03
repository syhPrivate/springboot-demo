package com.test.entity;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int rid;
	private String rname;

	private Set<Module> modules;
	
	public Set<Module> getModules() {
		return modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	@Override
	public String toString() {
		return "Role [rid=" + rid + ", rname=" + rname + ", modules=" + modules + "]";
	}


}
