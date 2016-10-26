package org.bglogin.model.dao;

import java.util.List;

import org.bglogin.model.entity.NewUser;

public interface INewUserDao {

	NewUser getUserById(String userId);

	List<NewUser> getAllUsers();

	NewUser getUserByUsername(String username);

}
