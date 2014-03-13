package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class ChessData {

	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "chess.png", "ŠK Vukovar 91",
				"Adresa: Stanka Vraza 19 a",
				"e-mail: sahovski-klub-vukovar91@vu.t-com.hr"));
		Items.add(new SportsConstructor(2, "chess.png", "ŠK Slaven",
				"Adresa: Zmajeva 1", "Telefon: 032444063"));
		Items.add(new SportsConstructor(3, "chess.png", "ŠK Borovo 1937",
				"Adresa: Blage Zadre 2", "Telefon: 032432092"));
		Items.add(new SportsConstructor(4, "chess.png", "ŠK Caissa",
				"Adresa: Kriva Bara 31", "Telefon: 032432092"));

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
