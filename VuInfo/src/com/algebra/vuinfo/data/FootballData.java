package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class FootballData {

	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "football.png", "HNK Vukovar 91",
				"Adresa: 204. Vukovarske brigade bb", "Telefon: 032441284",
				"e-mail: info@hnk-vukovar91.com"));
		Items.add(new SportsConstructor(2, "football.png", "NK Vuteks Sloga",
				"Adresa: Sajmište bb", "Telefon: 0958589110"));
		Items.add(new SportsConstructor(3, "football.png", "HNK Radnièki",
				"Adresa: Kudeljarska 6/A", "Telefon/fax: 032432292"));
		Items.add(new SportsConstructor(4, "football.png", "HNK Mitnica",
				"Adresa: Prosina 56", "Telefon: 098289495",
				"Telefon: 0989061630"));
		Items.add(new SportsConstructor(5, "football.png", "ŠNK Dunav Sotin",
				"Adresa: Bana Josipa Jelaèiæa bb Sotin", "Telefon: 0997091766"));
		Items.add(new SportsConstructor(6, "football.png", "HNK Borovo",
				"Adresa: Sportska bb", "Telefon: 0951977139"));
		Items.add(new SportsConstructor(7, "football.png", "HNK Lipovaèa",
				"Adresa: Slavonska bb", "Telefon: 0981905161"));
		Items.add(new SportsConstructor(8, "football.png",
				"Nogometna škola Victory", "Adresa: Brune Bušiæa 25/82",
				"Telefon: 0914103003"));
		Items.add(new SportsConstructor(9, "football.png",
				"Malonogometni klub Primus", "Adresa: Ive Lole Ribara 26",
				"Telefon: 0914411218"));
	}

	public static SportsConstructor GetbyId(int id) {

		for (SportsConstructor item : Items) {
			if (item.Id == id) {
				return item;
			}
		}
		return null;
	}
}
