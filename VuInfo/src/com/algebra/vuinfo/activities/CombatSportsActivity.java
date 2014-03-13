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
import com.algebra.vuinfo.adapters.CombatSportsAdapter;
import com.algebra.vuinfo.data.CombatSportsData;
import com.algebra.vuinfo.dialog.ShowDialog;

public class CombatSportsActivity extends Activity implements
		OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);

		CombatSportsData.LoadData();
		ListView listView = (ListView) findViewById(R.id.sports_listview);
		String[] ids = new String[CombatSportsData.Items.size()];
		for (int i = 0; i < ids.length; i++) {

			ids[i] = Integer.toString(i + 1);
		}

		CombatSportsAdapter adapter = new CombatSportsAdapter(this,
				R.layout.row_all_sports, ids);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {
		case 0:

			ShowDialog.showDialogMail(this,
					"Pošalji mail Karate klub Vukovar 91",
					"karate.klub.vukovar91@vu.t-com.hr", 45.34236, 19.00911);
			break;
		case 1:

			ShowDialog.showDialogPhoneMail(this,
					"Nazovi Taekwondo klub Borovo",
					"Pošalji mail Taekwondo klub Borovo", "tel:0992125282",
					"ddrakulic.vu@gmail.com", 45.37968, 18.95665);
			break;
		case 2:

			ShowDialog.showDialogPhone(this,
					"Nazovi Sportski taekwondo klub Dragon", "tel:0977933211",
					45.38674, 18.90776);
			break;
		case 3:

			ShowDialog.showDialogMail(this,
					"Pošalji mail Boksaèki klub Borovo",
					"boksacki.klub.borovo@vu.t-com.hr", 45.37906, 18.95583);
			break;
		case 4:

			ShowDialog.showDialogPhone(this, "Nazovi Crow kickbox klub",
					"tel:0958505812", 45.33960, 18.99762);
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
