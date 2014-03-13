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
import com.algebra.vuinfo.adapters.GasStationAdapter;
import com.algebra.vuinfo.data.GasStationData;
import com.algebra.vuinfo.dialog.ShowDialog;

public class GasStationActivity extends Activity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);
		GasStationData.LoadData();
		ListView listView = (ListView) findViewById(R.id.sports_listview);
		String[] ids = new String[GasStationData.Items.size()];
		for (int i = 0; i < ids.length; i++) {

			ids[i] = Integer.toString(i + 1);
		}

		GasStationAdapter adapter = new GasStationAdapter(this,
				R.layout.row_all_sports, ids);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0:

			ShowDialog.showDialogPhone(this, "Nazovite Ina Mitnica",
					"tel:091497130", 45.340473, 19.018425);
			break;
		case 1:

			ShowDialog.showDialogPhone(this, "Nazovite Ina Priljevo",
					"tel:0914971309", 45.366393, 18.977762);
			break;

		case 2:

			ShowDialog.showDialogPhone(this, "Nazovite INA Borovo",
					"tel:0914971293", 45.375031, 18.953902);
			break;

		case 3:

			ShowDialog.showDialogPhone(this, "Nazovite Ina Petrol",
					"tel:032432253", 45.3728, 18.953301);
			break;

		case 4:

			ShowDialog.showDialogPhone(this, "Nazovite Vuka Benz",
					"tel:032432864", 45.372845, 18.953172);
			break;

		case 5:

			ShowDialog.showDialogPhone(this, "Nazovite OMV", "tel:032432829",
					45.369665, 18.960253);
			break;

		case 6:

			ShowDialog.showDialogPhone(this, "Nazovite Lukoil",
					"tel:032414769", 45.349661, 18.999915);
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
