/**
 * 
 */
package org.bglogin.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.bglogin.model.dao.INewUserDao;
import org.bglogin.model.entity.Role;
import org.bglogin.model.entity.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * Implementation of DAO Service for entity NewUser
 * 
 * @author Giuseppe Vincenzi
 *
 */
@Repository
public class NewUserDaoImpl implements INewUserDao {
	/**
	 * LOGGER
	 */
	private Logger LOGGER = Logger.getLogger(NewUserDaoImpl.class);

	/**
	 * DataSource injected by Spring
	 */
	@Autowired
	private DataSource dataSource;

	/**
	 * Utility variable to map join tables
	 */
	private NewUser currentUser;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NewUser getUserById(String userId) {
		String sql = "SELECT * FROM user LEFT OUTER JOIN user_roles ON user_roles.user_id=user.user_id LEFT OUTER JOIN role ON user_roles.role_id=role.role_id WHERE user.user_id = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			NewUser user = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = mapping(rs);
			}
			rs.close();
			ps.close();
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
	}

	/**
	 * Utility method to map a ResultSet in an NewUser bean
	 * 
	 * @param rs
	 *            ResultSet
	 * @return NewUser
	 * @throws SQLException
	 *             exception
	 */
	private NewUser mapping(ResultSet rs) throws SQLException {
		NewUser user;
		if (currentUser == null || rs.getString("user_id") != currentUser.getUserId()) {
			user = new NewUser();
			user.setUserId(rs.getString("user_id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			
			currentUser = user;
		} else {
			user = currentUser;
		}

		Role role = new Role();
		role.setRoleId(rs.getInt("role_id"));
		role.setRole(rs.getString("role"));
		user.getRoles().add(role);

		return user;
	}

	
	
	@Override
	public List<NewUser> getAllUsers() {
		List<NewUser> users = new ArrayList<NewUser>();
		String sql = "SELECT * FROM user LEFT OUTER JOIN user_roles ON user_roles.user_id=user.user_id LEFT OUTER JOIN role ON user_roles.role_id=role.role_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			NewUser user = null;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = mapping(rs);
				if (!users.contains(user)) {
					users.add(user);
				}
			}
			rs.close();
			ps.close();
			return users;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
	}

	@Override
	public NewUser getUserByUsername(String username) {
		String sql = "SELECT * FROM public.\"USER\" WHERE username = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			NewUser user = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = mapping(rs);
			}
			rs.close();
			ps.close();
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LOGGER.error(e);
				}
			}
		}
	}
	
	

}
