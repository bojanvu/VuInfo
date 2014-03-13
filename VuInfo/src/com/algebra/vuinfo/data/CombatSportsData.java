package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class CombatSportsData {

	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "kickbox.png",
				"Karate klub Vukovar 91", "Adresa: Stanka Vraza 19 a",
				"e-mail: karate.klub.vukovar91@vu.t-com.hr"));
		Items.add(new SportsConstructor(2, "kickbox.png",
				"Taekwondo klub Borovo",
				"Adresa: Vijenac Ruðera Boškoviæa 2/25", "Telefon: 0992125282",
				"e-mail: ddrakulic.vu@gmail.com"));
		Items.add(new SportsConstructor(3, "kickbox.png",
				"Sportski taekwondo klub Dragon",
				"Adresa: Slavonska 11 Lipovaèa", "Telefon: 0977933211"));
		Items.add(new SportsConstructor(4, "kickbox.png",
				"Boksaèki klub Borovo", "Adresa: Domovinskog rata 38/55",
				"e-mail: boksacki.klub.borovo@vu.t-com.hr"));
		Items.add(new SportsConstructor(5, "kickbox.png", "Crow kickbox klub",
				"Adresa: Radnièka 44", "Telefon: 0958505812"));

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