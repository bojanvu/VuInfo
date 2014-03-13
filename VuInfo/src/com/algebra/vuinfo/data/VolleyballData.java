package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class VolleyballData {

	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "volleyball.png",
				"�enski odbojka�ki klub Vukovar",
				"Adresa: 204. Vukovarske brigade bb", "Telefon: 032442528",
				"e-mail: vukovar.zok@gmail.com"));
		Items.add(new SportsConstructor(2, "volleyball.png",
				"Mu�ki odbojka�ki klub Vukovar",
				"Adresa: Trg Dra�ena Petrovi�a bb", "Telefon: 0989218647",
				"e-mail: mokvuk@net.hr"));
		Items.add(new SportsConstructor(3, "volleyball.png",
				"Klub sjede�e odbojke Vukovar", "Adresa: Velebitska 1",
				"Telefon: 0998150666"));

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
