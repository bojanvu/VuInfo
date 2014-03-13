package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class RestaurantData {
	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "restaurant.png", "Restoran Nada",
				"Adresa: Matice Hrvatske 3", "Telefon: 032/430-315"));
		Items.add(new SportsConstructor(2, "restaurant.png", "Restoran Mornar",
				"Adresa: Dunavski prilaz 2", "Telefon: 032/441-788"));
		Items.add(new SportsConstructor(3, "restaurant.png", "Restoran Dom",
				"Adresa: Blage Zadre 2", "Telefon: 032/422-621",
				"e-mail: borovo.standard@borovo.hr"));
		Items.add(new SportsConstructor(4, "restaurant.png", "Bistro Sims",
				"Adresa: 12 Redarstvenika 1", "Telefon: 098/938-5925",
				"e-mail: dejan.savic.sims@gmail.com"));
		Items.add(new SportsConstructor(5, "restaurant.png", "Restoran Pleter",
				"Adresa: Andrije Hebranga 9", "Telefon: 032/300-300",
				"e-mail: restoranpleter@gmail.com"));
		Items.add(new SportsConstructor(6, "restaurant.png", "Restoran Lav",
				"Adresa: J.J. Strossmayera 18", "Telefon: 032/445-100",
				"e-mail: info@hotel-lav.hr"));
		Items.add(new SportsConstructor(7, "restaurant.png", "Restoran Vrške",
				"Adresa: Parobrodarska 3", "Telefon: 032/412-829",
				"e-mail: restoranvrske@gmail.com"));
		Items.add(new SportsConstructor(8, "restaurant.png", "Restoran Ðani",
				"Adresa: J.J. Strossmayera 2", "Telefon: 032/441-911"));
		Items.add(new SportsConstructor(9, "restaurant.png", "Restoran Mak",
				"Adresa: Nikole Tesle 13", "Telefon: 032/444-445"));
		Items.add(new SportsConstructor(10, "restaurant.png", "Stari Toranj",
				"Adresa: Trg R. Hrvatske bb", "Telefon: 032/412-829"));
		Items.add(new SportsConstructor(11, "restaurant.png",
				"Restoran Dunavska golubica", "Adresa: Dunavska šetnica 1",
				"Telefon: 032/445-433", "e-mail: dunavska.golubica@maart.hr"));
		Items.add(new SportsConstructor(12, "restaurant.png",
				"Restoran Konoba Megaron", "Adresa: Dalmatinska 3",
				"Telefon: 032/414-410", "e-mail: info@konoba-megaron.hr"));
		Items.add(new SportsConstructor(13, "restaurant.png",
				"Mexican bar Tequila", "Adresa: Stjepana Radiæa 72",
				"Telefon: 095/829-1982"));
		Items.add(new SportsConstructor(14, "restaurant.png", "Restoran Ban",
				"Adresa: B.J.Jelaèiæa 146", "Telefon: 032/412-933"));

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
