package org.bglogin.services.ds.impl;

import org.bglogin.model.dao.IUserDao;
import org.bglogin.services.ds.IUserDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDSImpl implements IUserDS{

	@Autowired 
	private IUserDao userDao;
	
	@Override
	public void create(String name, String password, String email) {
		
		userDao.create(name,password,email);		
		
	}
	
	@Override
	public int check(String name, String column) {
		
		int check = userDao.check(name, column);		
		return check;
	}
	
	@Override
	public String recPwd(String name, String email) {
		
		String pwd = userDao.recPwd(name, email);		
		return pwd;
	}
	
	@Override
	public int updatePwd(String name, String pwd, String email) {
		
		int rows = userDao.updatePwd(name, pwd, email);		
		return rows;
	}
	
}
