package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class GasStationData {

	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "gas_station.png", "INA Mitnica",
				"Adresa: Bana Josipa Jelaèiæa BB", "Radno vrijeme: 06:22h",
				"Telefon: 091/497-1308"));
		Items.add(new SportsConstructor(2, "gas_station.png", "INA Priljevo",
				"Adresa: Priljevo BB", "Radno vrijeme: 00:24",
				"Telefon: 091/497-1309"));
		Items.add(new SportsConstructor(3, "gas_station.png", "INA Borovo",
				"Adresa: Blage Zadre BB", "Radno vrijeme: 06:22",
				"Telefon: 091/497-1293"));
		Items.add(new SportsConstructor(4, "gas_station.png", "INA Petrol",
				"Adresa: Kudeljarska 3", "Radno vrijeme: 06:21",
				"Telefon: 032/432-253"));
		Items.add(new SportsConstructor(5, "gas_station.png", "Vuka Benz",
				"Adresa: Kudeljarska 6", "Radno vrijeme: 06:22",
				"Telefon: 032/432-864"));
		Items.add(new SportsConstructor(6, "gas_station.png", "OMV",
				"Adresa: Kudeljarska 4B", "Radno vrijeme: 06:24",
				"Telefon: 032/432-829"));
		Items.add(new SportsConstructor(7, "gas_station.png", "Lukoil",
				"Adresa: Trg Drvena pijaca BB", "Radno vrijeme: ",
				"Telefon: 032/414-769"));
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
