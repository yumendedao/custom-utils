package com.yumendedao.example.FactoryAndPaging;

public class User implements IUser {

	int id;
	int name;
	
	public User(int id, int name) {
		super();
		this.id = id;
		this.name = name;
	}

	public void getDetial() {
		System.out.println("id:" + id + ", name:" + name);
	}

	public int getID() {
		return id;
	}

	public int getName() {
		return name;
	}
}
