package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class TennisData {

	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "tennis.png",
				"Teniski klub Borovo Naselje", "Adresa: Sportska bb",
				"Telefon: 032423113", "Telefon: 0912572576"));
		Items.add(new SportsConstructor(2, "tennis.png",
				"Teniski Klub Vukovar", "Adresa: Lijeva Bara 19 a",
				"Telefon: 0991941855"));
		Items.add(new SportsConstructor(3, "tennis.png",
				"Stolnoteniski klub Vukovar 91", "Adresa: Trg Matije Gupca bb",
				"Telefon: 0995720639"));

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
