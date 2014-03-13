package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class FitnessData {

	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "fitness.png",
				"Fitnes centar - Veleuèilište Lavoslav Ružièka",
				"Adresa: Županijska 50", "Telefon: 0914446921",
				"e-mail: fitnes.vevu.hr@gmail.com"));
		Items.add(new SportsConstructor(2, "fitness.png",
				"Fitness centar na Mitnici", "Adresa: Prosina 101",
				"Telefon: 098346400"));
		Items.add(new SportsConstructor(3, "fitness.png",
				"Tanga - udruga za sport i rekreaciju",
				"Adresa: Nova ulica 57", "Telefon: 032416690"));
		Items.add(new SportsConstructor(4, "fitness.png",
				"Top Sport - udruga za sport i rekreaciju",
				"Adresa: Županijska 44", "Telefon: 0915985339"));
		Items.add(new SportsConstructor(5, "fitness.png",
				"Sonja - društvo za sportsku rekreaciju",
				"Adresa: Domovinskog rata 6/29", "e-mail: dsr.sonja@gmail.com"));
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
