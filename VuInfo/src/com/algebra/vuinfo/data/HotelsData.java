package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class HotelsData {
	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "hotel.png", "Hotel Lav",
				"Adresa: J.J. Strossmayera 18", "Telefon: 032445100",
				"e-mail: info@hotel-lav.hr"));
		Items.add(new SportsConstructor(2, "hotel.png", "Villa Bonaca",
				"Adresa: Priljevo 31", "Telefon: 032450670"));
		Items.add(new SportsConstructor(3, "hotel.png", "Villa Rosa",
				"Adresa: Josipa Rukavine 2b", "Telefon: 091/520-4036",
				"e-mail: villarosavukovar@gmail.com"));
		Items.add(new SportsConstructor(4, "hotel.png", "Hostel Borovo",
				"Adresa: Dr. Ante Starčevića bb", "Telefon: 099/3322-900",
				"e-mail: hostel.borovo@borovo.hr"));
		Items.add(new SportsConstructor(5, "hotel.png", "Prenoćište Zara",
				"Adresa: Nikole Andrića 16", "Telefon: 098/1766-550",
				"e-mail: zaravukovar@gmail.com"));
		Items.add(new SportsConstructor(6, "hotel.png", "Apartman Jasna",
				"Adresa: A.M.Reljkovića 33", "Telefon: 091/892-1531",
				"e-mail: burazorjasna@googlemail.com"));
		Items.add(new SportsConstructor(7, "hotel.png", "Villa Vanda",
				"Adresa: Dalmatinska 5", "Telefon: 032/414-410",
				"e-mail: info@konoba-megaron.hr"));
		Items.add(new SportsConstructor(8, "hotel.png", "Apartmani Martini",
				"Adresa: J.J. Strossmayera 19", "Telefon: 032/414-410",
				"e-mail: apartments.martini@gmail.com"));

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
