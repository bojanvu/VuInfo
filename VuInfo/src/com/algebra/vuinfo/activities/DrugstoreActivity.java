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
import com.algebra.vuinfo.adapters.DrugstoreAdapter;
import com.algebra.vuinfo.data.DrugstoreData;
import com.algebra.vuinfo.dialog.ShowDialog;

public class DrugstoreActivity extends Activity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);

		DrugstoreData.LoadData();
		ListView listView = (ListView) findViewById(R.id.sports_listview);
		String[] ids = new String[DrugstoreData.Items.size()];
		for (int i = 0; i < ids.length; i++) {

			ids[i] = Integer.toString(i + 1);
		}

		DrugstoreAdapter adapter = new DrugstoreAdapter(this,
				R.layout.row_all_sports, ids);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0:

			ShowDialog.showDialogPhone(this, "Nazovite Joukhadar Vukovar 1",
					"tel:032/443-100", 45.342871, 19.002589);
			break;
		case 1:

			ShowDialog.showDialogPhone(this, "Nazovite Joukhadar Vukovar 2",
					"tel:032/443-300", 45.353201, 19.001344);
			break;

		case 2:

			ShowDialog.showDialogPhone(this, "Nazovite Joukhadar Vukovar 3",
					"tel:032/423-000", 45.37589, 18.963515);
			break;

		case 3:

			ShowDialog.showDialogPhone(this, "Nazovite Joukhadar Vukovar 4",
					"tel: 032/423-091", 45.379493, 18.956026);
			break;

		case 4:

			ShowDialog.showDialogPhone(this, "Nazovite Ljekarna Vaše zdravlje",
					"tel:032/441-936", 45.349205, 19.002138);
			break;

		case 5:

			ShowDialog.showDialogPhone(this, "Nazovite Ljekarna Štrkalj",
					"tel:032/441-441", 45.353201, 18.999413);
			break;

		case 6:

			ShowDialog.showDialogPhone(this, "Nazovite Ljekarna Kaleniæ",
					"tel:", 45.34652, 19.006902);
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
