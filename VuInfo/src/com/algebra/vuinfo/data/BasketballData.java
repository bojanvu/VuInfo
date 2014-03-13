package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class BasketballData {

	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "basketball.png", "HKK Vukovar",
				"Adresa: Samoborska 14 a", "Telefon: 032450767",
				"e-mail: hkkv@net.hr"));
		Items.add(new SportsConstructor(2, "basketball.png", "KK Borovo",
				"Adresa: Trg Dražena Petroviæa bb", "Telefon: 0998071192"));

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
