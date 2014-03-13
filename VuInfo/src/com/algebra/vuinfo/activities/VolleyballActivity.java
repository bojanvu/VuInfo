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
import com.algebra.vuinfo.adapters.VolleyballAdapter;
import com.algebra.vuinfo.data.VolleyballData;
import com.algebra.vuinfo.dialog.ShowDialog;

public class VolleyballActivity extends Activity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);

		VolleyballData.LoadData();
		ListView listView = (ListView) findViewById(R.id.sports_listview);
		String[] ids = new String[VolleyballData.Items.size()];
		for (int i = 0; i < ids.length; i++) {

			ids[i] = Integer.toString(i + 1);
		}

		VolleyballAdapter adapter = new VolleyballAdapter(this,
				R.layout.row_all_sports, ids);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {
		case 0:

			ShowDialog.showDialogPhoneMail(this, "Nazovi ŽOK Vukovar",
					"Pošalji mail ŽOK Vukovar", "tel:032442528",
					"vukovar.zok@gmail.com", 45.35334, 18.98286);
			break;
		case 1:

			ShowDialog.showDialogPhoneMail(this, "Nazovi MOK Vukovar",
					"Pošalji mail MOK Vukovar", "tel:0989218647",
					"mokvuk@net.hr", 45.37752, 18.96440);
			break;
		case 2:

			ShowDialog.showDialogPhone(this,
					"Nazovi Klub sjedeæe odbojke Vukovar", "tel:0998150666",
					45.38154, 18.95134);
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