package org.bglogin.model.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity bean User for table user
 * @author Giuseppe Vincenzi
 *
 */
public class NewUser {
	/**
	 * Column user_id
	 */
	private String userId;
	
	/**
	 * Column username
	 */
	private String username;
	
	/**
	 * Column password: encrypted value
	 */
	private String password;
	
	/**
	 * Mapped by join table user_roles
	 */
	private Set<Role> roles;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		if(roles == null){
			setRoles(new HashSet<Role>(0));
		}
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + Integer.valueOf(userId);
//		result = prime * result + ((username == null) ? 0 : username.hashCode());
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see java.lang.Object#equals(java.lang.Object)
//	 */
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		NewUser other = (NewUser) obj;
//		if (userId != other.userId)
//			return false;
//		if (username == null) {
//			if (other.username != null)
//				return false;
//		} else if (!username.equals(other.username))
//			return false;
//		return true;
//	}
}
