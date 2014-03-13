package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class OtherSportsData {

	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "sports.png",
				"Hrvatski veslaèki klub Vukovar", "Adresa: Parobrodska 5",
				"Telefon: 098436509", "e-mail: hvk-vukovar@optinet.hr"));
		Items.add(new SportsConstructor(2, "sports.png",
				"Gimnastièki klub Vukovar", "Adresa: Trg Dražena Petroviæa bb",
				"Telefon: 0913050775", "e-mail: gimnastika.vu@gmail.com"));
		Items.add(new SportsConstructor(3, "sports.png",
				"Hrvatski kuglaèki klub Vukovar", "Adresa: Zrinska 51",
				"Telefon: 09816622397", "Telefon: 0989229692"));
		Items.add(new SportsConstructor(4, "sports.png",
				"Atletski klub Maraton", "Adresa: Lijeva Bara 4",
				"Telefon: 098588377"));
		Items.add(new SportsConstructor(5, "sports.png",
				"Streljaèki klub Griè 7", "Adresa: Dr. Antuna Bauera Paje bb",
				"Telefon: 032414555", "e-mail: gric7.vukovar@gmail.com"));
		Items.add(new SportsConstructor(6, "sports.png",
				"Sportski moto klub Golubica",
				"Adresa: Kralja Zvonimira 78/16", "Telefon: 0951984131",
				"e-mail: golubicavukovar@gmail.com"));
		Items.add(new SportsConstructor(7, "sports.png",
				"Sportski biciklistièki klub Vukovar",
				"Adresa: Stjepana Radiæa 6", "e-mail: jare1906@hotmail.com"));
		Items.add(new SportsConstructor(8, "sports.png",
				"Klub podvodnih aktivnosti Vukovar",
				"Adresa: Lijeva Bara 19 a", "Telefon: 0981965192"));
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
