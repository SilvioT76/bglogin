package org.bglogin.services.ds;

public interface IUserDS {
	
	void create(String name, String password, String email);

	int check(String name, String column);

	String recPwd(String name, String email);

	int updatePwd(String name, String pwd, String email);

}
