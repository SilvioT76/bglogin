package org.bglogin.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.bglogin.model.dao.IActivityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import org.bglogin.model.entity.Activity;

@Repository
public class ActivityDaoImpl implements IActivityDao {

	/**
	 * LOGGER
	 */
	private Logger LOGGER = Logger.getLogger(ActivityDaoImpl.class);

	@Autowired
	private DataSource dataSource;

	private Activity mappingActivity(ResultSet rs) throws SQLException {
		
		Activity activity;
		activity = new Activity();
		activity.setName(rs.getString("name"));
		activity.setDate(rs.getString("date"));
		activity.setOwner(rs.getString("owner"));
		activity.setId(rs.getString("ID"));
		
		
	return activity;
}

	
	@Override
	public List<Activity> getActivities() {
		
		List<Activity> activities = new ArrayList<Activity>();
		String sql = "SELECT * FROM public.\"ACTIVITIES\"";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement st = conn.createStatement();
			Activity activity = null;
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				activity = mappingActivity(rs);
				activities.add(activity);
			}
			rs.close();
			st.close();
			return activities;
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
	public Activity getActivity(String id) {
		
		String sql = "SELECT * FROM public.\"ACTIVITIES\" where \"ID\" = '" + id +"' ORDER BY \"ID\" ASC";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement st = conn.createStatement();
			Activity activity = null;
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			activity = mappingActivity(rs);
			rs.close();
			st.close();
			return activity;
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
	public void insertActivity(String name, String date, String owner) {
		String sql = "INSERT INTO public.\"ACTIVITIES\" (name,date,owner) "+
				"VALUES ('" + name + "','" + date + "','" + owner + "')";
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
	public int deleteActivity(String id) {
		String sql = "DELETE FROM public.\"ACTIVITIES\" where \"ID\" = '" + id + "'";
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
	
	@Override
	public int updateActivity(String name, String date, String owner, String id) {
		String sql = "UPDATE public.\"ACTIVITIES\" set name = '" + name + "', date = '" + date +"', owner = '" + owner +"' where \"ID\" = '" + id + "'";
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
