package org.bglogin.model.entity;

public class Activity {

	public String name, date, owner, id;
	
		public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

		public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public Activity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

		public Activity(String name, String date, String owner) {
		super();
		this.name = name;
		this.date = date;
		this.owner = owner;
	}
		
		public Activity(String name, String date, String owner, String id) {
		super();
		this.name = name;
		this.date = date;
		this.owner = owner;
		this.id = id;
	}


}
