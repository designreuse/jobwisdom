package com.ucs.security.face;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import com.ucs.security.pojo.URLResource;
import com.ucs.security.pojo.Users;

public interface SecurityTestInterface {

	boolean getinput();

	boolean geoutput();
	//@RolesAllowed("ROLE_ADMIN")//拥有ROLE_ADMIN权限的用户才可进入此方法
	boolean addreport_admin();
	//@RolesAllowed("ROLE_ADMIN")
	boolean deletereport_admin();
	
	Users findbyUsername(String name);
	//@RolesAllowed("ROLE_USER")
	void user_login();

	List<URLResource> findResource();

}
