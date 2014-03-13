package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class HandballData {

	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "handball.png", "HRK Borovo",
				"Adresa: �eljezni�ka 9", "Telefon: 032422775"));
		Items.add(new SportsConstructor(2, "handball.png", "�RK Vukovar",
				"Adresa: Trg Dra�ena Petrovi�a bb", "Telefon: 032421909"));

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
