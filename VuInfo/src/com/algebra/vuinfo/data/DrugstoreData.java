package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class DrugstoreData {

	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "drugstore.png",
				"Joukhadar Vukovar 1", "Adresa: Sajmište 10",
				"Radno vrijeme: 00:24h", "Telefon: 032/443-100"));
		Items.add(new SportsConstructor(2, "drugstore.png",
				"Joukhadar Vukovar 2", "Adresa: J.J. Strossmayera 5a",
				"Radno vrijeme: 00:24", "Telefon: 032/443-300"));
		Items.add(new SportsConstructor(3, "drugstore.png",
				"Joukhadar Vukovar 3", "Adresa: Blage Zadre 13",
				"Radno vrijeme: 00:24", "Telefon: 032/423-000"));
		Items.add(new SportsConstructor(4, "drugstore.png",
				"Joukhadar Vukovar 4", "Adresa: Ul. Domovinskog rata 1",
				"Radno vrijeme: 00:24", "Telefon: 032/423-091"));
		Items.add(new SportsConstructor(5, "drugstore.png",
				"Ljekarna Vaše zdravlje", "Adresa: Ul. Ivana Meštroviæa 19",
				"Radno vrijeme: 00:24", "Telefon: 032/441-936"));
		Items.add(new SportsConstructor(6, "drugstore.png", "Ljekarna Štrkalj",
				"Adresa: Ul. K. A. Stjepinca 8", "Radno vrijeme: 00:24",
				"Telefon: 032/441-441"));
		Items.add(new SportsConstructor(7, "drugstore.png", "Ljekarna Kaleniæ",
				"Adresa: Stjepana Radiæa", "Radno vrijeme: 00:24", "Telefon: "));

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
