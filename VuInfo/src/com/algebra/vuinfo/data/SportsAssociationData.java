package com.algebra.vuinfo.data;

import java.util.ArrayList;

import com.algebra.vuinfo.models.SportsConstructor;

public class SportsAssociationData {

	public static ArrayList<SportsConstructor> Items;

	public static void LoadData() {

		Items = new ArrayList<SportsConstructor>();
		Items.add(new SportsConstructor(1, "sports_association.png",
				"Udruga gra�ana Viktorija", "Adresa: Miroslava Krle�e 66",
				"Telefon: 032410466"));
		Items.add(new SportsConstructor(2, "sports_association.png",
				"Sportsko ribolovna udruga Dea Sport",
				"Adresa: Trg �rtava fa�izma 9"));
		Items.add(new SportsConstructor(3, "sports_association.png",
				"Dru�tvo Hrvatska �ena", "Adresa: Dr. Franje Tu�mana 8",
				"Telefon: 032442304",
				"e-mail: hrvatska-zena.vukovar@vu.t-com.hr"));
		Items.add(new SportsConstructor(4, "sports_association.png",
				"Dru�tvo za pomo� mentalno retardiranim osobama Vukovar",
				"Adresa: Petra Preradovi�a 40", "Telefon: 032417700",
				"Telefon: 0989671407"));
		Items.add(new SportsConstructor(
				5,
				"sports_association.png",
				"Udruga za pomo� osobama s mentalnom retardacijom - Vukovarske iskrice",
				"Adresa: Ljudevita Gaja 12", "Telefon: 032428243",
				"e-mail: vukovarske-iskrice@gmail.com"));
		Items.add(new SportsConstructor(6, "sports_association.png",
				"Udruga invalida rada Vukovar", "Adresa: Velebitska 16 a",
				"Telefon: 0917333725", "e-mail: uir.vukovar@gmail.com"));
		Items.add(new SportsConstructor(7, "sports_association.png",
				"Udruga za sport i rekreaciju Top Sport",
				"Adresa: �upanijska 44", "Telefon: 0915985339"));
		Items.add(new SportsConstructor(8, "sports_association.png",
				"Dru�tveni klub za sport i rekreaciju", "Adresa: Prosina 101",
				"Telefon: 098346400"));
		Items.add(new SportsConstructor(9, "sports_association.png",
				"Sportska udruga Vukovar Tempo",
				"Adresa: Dragutina Renari�a 10", "Telefon: 032428537"));
		Items.add(new SportsConstructor(10, "sports_association.png",
				"Udruga za sport i rekreaciju Tanga", "Adresa: Nova ulica 57",
				"Telefon: 032416690"));
		Items.add(new SportsConstructor(11, "sports_association.png",
				"Sportska udruga Vu�edolske golubice",
				"Adresa: Sajmi�te 113 a", "Telefon: 032444647"));
		Items.add(new SportsConstructor(12, "sports_association.png",
				"Dru�tvo za sportsku rekreaciju Sonja",
				"Adresa: Domovinskog rata 6/29", "e-mail: dsr.sonja@gmail.com"));
		Items.add(new SportsConstructor(13, "sports_association.png",
				"Sportsko ribolovna udruga Dunav", "Adresa: Parobrodska bb",
				"Telefon: 032442237"));
		Items.add(new SportsConstructor(14, "sports_association.png",
				"Sportsko ribolovna udruga Borovo Naselje",
				"Adresa: Jana Bate 3", "Telefon: 09985446823"));
		Items.add(new SportsConstructor(15, "sports_association.png",
				"Zajednica sportskih udruga grada Vukovara",
				"Adresa: Trg Dra�ena Petrovi�a bb", "Telefon: 032421866"));

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
