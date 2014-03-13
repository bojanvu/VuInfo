package com.algebra.vuinfo.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.algebra.vuinfo.R;
import com.algebra.vuinfo.adapters.RestaurantAdapter;
import com.algebra.vuinfo.data.RestaurantData;
import com.algebra.vuinfo.dialog.ShowDialog;

public class RestaurantActivity extends Activity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);

		RestaurantData.LoadData();
		ListView listView = (ListView) findViewById(R.id.sports_listview);
		String[] ids = new String[RestaurantData.Items.size()];
		for (int i = 0; i < ids.length; i++) {

			ids[i] = Integer.toString(i + 1);
		}

		RestaurantAdapter adapter = new RestaurantAdapter(this,
				R.layout.row_all_sports, ids);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0:

			ShowDialog.showDialogPhone(this, "Nazovite Restoran Nada",
					"tel:032/430-315", 45.362594, 18.973128);
			break;
		case 1:

			ShowDialog.showDialogPhone(this, "Nazovite Restoran Mornar",
					"tel:032/441-788", 45.368896, 18.974823);
			break;

		case 2:

			ShowDialog.showDialogPhoneMail(this, "Nazovite Restoran Dom",
					"Pošalji mail Restoran Dom", "tel:032/422-621",
					"borovo.standard@borovo.hr", 45.376177, 18.963021);
			break;

		case 3:

			ShowDialog.showDialogPhoneMail(this, "Nazovite Bistro Sims",
					"Pošalji mail Bistro Sims", "tel: 098/938-5925",
					"dejan.savic.sims@gmail.com", 45.3758, 18.951691);
			break;

		case 4:

			ShowDialog.showDialogPhoneMail(this, "Nazovite Restoran Pleter",
					"Pošalji mail Restoran Pleter", "tel:032/300-300",
					"restoranpleter@gmail.com", 45.357076, 18.995422);
			break;

		case 5:

			ShowDialog.showDialogPhoneMail(this, "Nazovite Restoran Lav",
					"Pošalji mail Restoran Lav", "tel:032/445-100",
					"info@hotel-lav.hr", 45.353683, 19.001387);
			break;

		case 6:

			ShowDialog.showDialogPhoneMail(this, "Nazovite Restoran Vrške",
					"Pošalji mail Restoran Vrške", "tel:032/412-829",
					"restoranvrske@gmail.com", 45.35397, 19.003104);
			break;

		case 7:

			ShowDialog.showDialogPhone(this, "Nazovite Restoran Ðani",
					"tel:032/441-911", 45.35207, 19.002267);
			break;

		case 8:

			ShowDialog.showDialogPhone(this, "Nazovite Restoran Mak",
					"tel:032/444-445", 45.349657, 19.001645);

			break;

		case 9:

			ShowDialog.showDialogPhone(this, "Nazovite Restoran Stari Toranj",
					"tel:032/412-829", 45.35112, 19.00471);
			break;

		case 10:

			ShowDialog.showDialogPhoneMail(this,
					"Nazovite Restoran Dunavska golubica",
					"Pošalji mail Restoran Dunavska golubica",
					"tel:032/445-433", "dunavska.golubica@maart.hr", 45.350788,
					19.006083);
			break;

		case 11:

			ShowDialog.showDialogPhoneMail(this,
					"Nazovite Restoran Konoba Megaron",
					"Pošalji mail Konoba Megaron", "tel:032/414-410",
					"info@konoba-megaron.hr", 45.344198, 19.0051);
			break;

		case 12:

			ShowDialog.showDialogPhone(this, "Nazovite Mexican bar Tequila",
					"tel:095/829-1982", 45.344756, 19.008254);
			break;

		case 13:

			ShowDialog.showDialogPhone(this, "Nazovite Restoran Ban",
					"tel:032/412-933", 45.338587, 19.021515);
			break;

		}

	}

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#336699")));
		return true;
	}
}
