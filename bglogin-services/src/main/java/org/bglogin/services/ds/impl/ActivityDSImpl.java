package org.bglogin.services.ds.impl;


import java.util.List;

import org.bglogin.model.dao.IActivityDao;
import org.bglogin.model.entity.Activity;
import org.bglogin.services.ds.IActivityDS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActivityDSImpl implements IActivityDS{

	@Autowired
	private IActivityDao activityDao;
	
	@Override
	public int delete(String id) {
		
		int rows = activityDao.deleteActivity(id);
		return rows;
	}
	
	@Override
	public Activity check(String id) {
		
		Activity act = activityDao.getActivity(id);
		return act;
	}
	
	@Override
	public void insert(String name, String date, String owner) {
		
		activityDao.insertActivity(name, date, owner);
	}
	
	@Override
	public int update(String name, String date, String owner, String id) {
		
		int rows = activityDao.updateActivity(name, date, owner, id);
		return rows;
	}
	
	@Override
	public List<Activity> getList() {
		
		List<Activity> atts = activityDao.getActivities();
		return atts;
	}
}
