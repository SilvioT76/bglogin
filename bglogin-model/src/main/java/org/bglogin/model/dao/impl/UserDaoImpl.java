/**
 * 
 */
package org.bglogin.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.bglogin.model.dao.IUserDao;
import org.bglogin.model.entity.Role;
import org.bglogin.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * Implementation of DAO Service for entity User
 * 
 * @author Giuseppe Vincenzi
 *
 */
@Repository
public class UserDaoImpl implements IUserDao {
	/**
	 * LOGGER
	 */
	private Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	/**
	 * DataSource injected by Spring
	 */
	@Autowired
	private DataSource dataSource;

	/**
	 * Utility variable to map join tables
	 */
	private User currentUser;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUserById(int userId) {
		String sql = "SELECT * FROM user LEFT OUTER JOIN user_roles ON user_roles.user_id=user.user_id LEFT OUTER JOIN role ON user_roles.role_id=role.role_id WHERE user.user_id = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			User user = null;
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
	 * Utility method to map a ResultSet in an User bean
	 * 
	 * @param rs
	 *            ResultSet
	 * @return User
	 * @throws SQLException
	 *             exception
	 */
	private User mapping(ResultSet rs) throws SQLException {
		User user;
		if (currentUser == null || rs.getInt("user_id") != currentUser.getUserId()) {
			user = new User();
			user.setUserId(rs.getInt("user_id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			Date creationDate = rs.getDate("creation_date");
			if (creationDate != null) {
				Calendar creationDateCal = Calendar.getInstance();
				creationDateCal.setTime(creationDate);
				user.setCreationDate(creationDateCal);
			}
			Date lastLogin = rs.getDate("last_login");
			if (lastLogin != null) {
				Calendar lastLoginCal = Calendar.getInstance();
				lastLoginCal.setTime(lastLogin);
				user.setLastLogin(lastLoginCal);
			}
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
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM user LEFT OUTER JOIN user_roles ON user_roles.user_id=user.user_id LEFT OUTER JOIN role ON user_roles.role_id=role.role_id";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			User user = null;
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
	public User getUserByUsername(String username) {
		String sql = "SELECT * FROM user LEFT OUTER JOIN user_roles ON user_roles.user_id=user.user_id LEFT OUTER JOIN role ON user_roles.role_id=role.role_id WHERE username = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			User user = null;
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

	@Override
	public void create(String name, String password, String email) {
		String sql = "INSERT INTO public.\"USER\" (username,password,role_id,enabled,email) "+
				"VALUES ('" + name + "','" + password + "','2','true','" + email + "')";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			st.executeUpdate(sql);
			st.close();
			conn.commit();
			conn.close();
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
	public int check(String name, String column) {
		String sql = "SELECT COUNT(username) as controllo from public.\"USER\" where " + column + " = '" + name + "'";
		Connection conn = null;
		int check;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			check = Integer.valueOf(rs.getString("controllo"));
			st.close();
			conn.commit();
			conn.close();
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
		return check;
	}
	
	@Override
	public String recPwd(String name, String email) {
		String sql = "SELECT password from public.\"USER\" where username = '" + name + "' and email = '"+ email + "'";
		Connection conn = null;
		String pwd;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			pwd = rs.getString("password");
			st.close();
			conn.commit();
			conn.close();
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
		return pwd;
	}

	@Override
	public int updatePwd(String name, String pwd, String email) {
		String sql = "UPDATE public.\"USER\" set password = '" + pwd + "' where username = '" + name + "' and email = '" + email +"'";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			int rs = st.executeUpdate(sql);
			st.close();
			conn.commit();
			conn.close();
			return rs;
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
