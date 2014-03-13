package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class SportsData {

	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "football.png", "Nogomet"));
		Items.add(new SportsConstructor(2, "basketball.png", "Košarka"));
		Items.add(new SportsConstructor(3, "volleyball.png", "Odbojka"));
		Items.add(new SportsConstructor(4, "handball.png", "Rukomet"));
		Items.add(new SportsConstructor(5, "tennis.png", "Tenis"));
		Items.add(new SportsConstructor(6, "kickbox.png", "Borilaèki sportovi"));
		Items.add(new SportsConstructor(7, "chess.png", "Šah"));
		Items.add(new SportsConstructor(8, "bocce.png", "Boæanje"));
		Items.add(new SportsConstructor(9, "sports.png", "Ostali Sportovi"));
		Items.add(new SportsConstructor(10, "fitness.png", "Fitnes klubovi"));
		Items.add(new SportsConstructor(11, "sports_association.png",
				"Sportske udruge"));

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