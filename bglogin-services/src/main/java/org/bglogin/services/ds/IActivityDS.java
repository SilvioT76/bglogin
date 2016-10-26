package org.bglogin.services.ds;

import java.util.List;

import org.bglogin.model.entity.Activity;

public interface IActivityDS {

	void insert(String name, String date, String owner);

	public List<Activity> getList();

	int delete(String id);

	Activity check(String id);

	int update(String name, String date, String owner, String id);
}
