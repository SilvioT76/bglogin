package org.bglogin.model.dao;

import java.util.List;

import org.bglogin.model.entity.Activity;

public interface IActivityDao {

	public void insertActivity(String name, String date, String owner);

	List<Activity> getActivities();

	int deleteActivity(String id);

	Activity getActivity(String id);

	int updateActivity(String name, String date, String owner, String id);

}
