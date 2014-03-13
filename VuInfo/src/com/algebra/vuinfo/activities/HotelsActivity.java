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
import com.algebra.vuinfo.adapters.HotelsAdapter;
import com.algebra.vuinfo.data.HotelsData;
import com.algebra.vuinfo.dialog.ShowDialog;

public class HotelsActivity extends Activity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);

		HotelsData.LoadData();
		ListView listView = (ListView) findViewById(R.id.sports_listview);
		String[] ids = new String[HotelsData.Items.size()];
		for (int i = 0; i < ids.length; i++) {

			ids[i] = Integer.toString(i + 1);
		}

		HotelsAdapter adapter = new HotelsAdapter(this,
				R.layout.row_all_sports, ids);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0:

			ShowDialog.showDialogPhoneMail(this, "Nazovite Hotel Lav",
					"Pošalji mail Hotel Lav", "tel:032/445-100",
					"info@hotel-lav.hr", 45.353653, 19.001366);
			break;
		case 1:

			ShowDialog.showDialogPhone(this, "Nazovite Villa Bonaca",
					"tel:032/450-670", 45.362383, 18.985595);
			break;

		case 2:

			ShowDialog.showDialogPhoneMail(this, "Nazovite Villa Rosa",
					"Pošalji mail Villa Rosa", "tel:091/520-4036",
					"villarosavukovar@gmail.com", 45.3502, 19.005657);
			break;

		case 3:

			ShowDialog.showDialogPhoneMail(this, "Nazovite Hostel Borovo",
					"Pošalji mail Hostel Borovo", "tel: 099/3322-900",
					"hostel.borovo@borovo.hr", 45.377443, 18.96394);
			break;

		case 4:

			ShowDialog.showDialogPhoneMail(this, "Nazovite Prenoæište Zara",
					"Pošalji mail Prenoæište Zara", "tel:098/1766-550",
					"zaravukovar@gmail.com", 45.348179, 19.003297);
			break;

		case 5:

			ShowDialog.showDialogPhoneMail(this, "Nazovite Apartman Jasna",
					"Pošalji mail Apartman Jasna", "tel:091/892-1531",
					"burazorjasna@googlemail.com", 45.348481, 18.988663);
			break;

		case 6:

			ShowDialog.showDialogPhoneMail(this, "Nazovite Villa Vanda",
					"Pošalji mail Villa Vanda", "tel:032/414-410",
					"info@konoba-megaron.hr", 45.343836, 19.005121);
			break;

		case 7:

			ShowDialog.showDialogPhoneMail(this, "Nazovite Apartmani Martini",
					"Pošalji mail Apartmani Martini", "tel:098/951-0349",
					"apartments.martini@gmail.com", 45.353653, 19.001366);
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
