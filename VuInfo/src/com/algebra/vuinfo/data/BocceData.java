package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class BocceData {

	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "bocce.png", "BK Mitnica",
				"Adresa: Lijeva Bara 19 a", "Telefon: 032410493",
				"Telefon: 0915418529"));
		Items.add(new SportsConstructor(2, "bocce.png", "BK Trpinjska cesta",
				"Adresa: Hrvatskog zrakoplovstva 47/3", "Telefon: 098866165",
				"Telefon: 0989929013"));
		Items.add(new SportsConstructor(3, "bocce.png", "BK Borovo",
				"Adresa: Trg Dražena Petroviæa bb", "Telefon: 032421252"));
		Items.add(new SportsConstructor(4, "bocce.png", "BK Dunav Sotin",
				"Adresa: Mirogojska bb Sotin", "Telefon: 0989799282"));

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
