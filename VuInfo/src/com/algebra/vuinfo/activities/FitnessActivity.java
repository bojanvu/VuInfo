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
import com.algebra.vuinfo.adapters.FitnessAdapter;
import com.algebra.vuinfo.data.FitnessData;
import com.algebra.vuinfo.dialog.ShowDialog;

public class FitnessActivity extends Activity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);

		FitnessData.LoadData();
		ListView listView = (ListView) findViewById(R.id.sports_listview);
		String[] ids = new String[FitnessData.Items.size()];
		for (int i = 0; i < ids.length; i++) {

			ids[i] = Integer.toString(i + 1);
		}

		FitnessAdapter adapter = new FitnessAdapter(this,
				R.layout.row_all_sports, ids);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {
		case 0:

			ShowDialog.showDialogPhoneMail(this, "Nazovi Fitnes centar VEVU",
					"Pošalji mail Fitnes centar VEVU", "tel:0914446921",
					"fitnes.vevu.hr@gmail.com", 45.35752, 18.99612);
			break;
		case 1:

			ShowDialog.showDialogPhone(this,
					"Nazovi Fitness centar na Mitnici", "tel:098346400",
					45.33245, 19.02006);
			break;
		case 2:

			ShowDialog.showDialogPhone(this, "Nazovi Tanga", "tel:032416690",
					45.34265, 18.99195);
			break;
		case 3:

			ShowDialog.showDialogPhone(this, "Nazovi Top Sport",
					"tel:0915985339", 45.35729, 18.99647);
			break;
		case 4:

			ShowDialog.showDialogMail(this, "Pošalji mail Sonja",
					"dsr.sonja@gmail.com", 45.37911, 18.95602);
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
