package com.algebra.vuinfo.models;

public class SportsConstructor {

	public int Id;
	public String IconFile;
	public String Name;
	public String Address;
	public String Mail;
	public String Phone;

	public SportsConstructor(int id, String iconFile, String name) {

		this.Id = id;
		this.IconFile = iconFile;
		this.Name = name;

	}

	public SportsConstructor(int id, String iconFile, String name,
			String address) {

		this.Id = id;
		this.IconFile = iconFile;
		this.Name = name;
		this.Address = address;

	}

	public SportsConstructor(int id, String iconFile, String name,
			String address, String mail) {

		this.Id = id;
		this.IconFile = iconFile;
		this.Name = name;
		this.Address = address;
		this.Mail = mail;

	}

	public SportsConstructor(int id, String iconFile, String name,
			String address, String mail, String phone) {

		this.Id = id;
		this.IconFile = iconFile;
		this.Name = name;
		this.Address = address;
		this.Mail = mail;
		this.Phone = phone;

	}
}