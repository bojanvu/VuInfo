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
import com.algebra.vuinfo.adapters.HandballAdapter;
import com.algebra.vuinfo.data.HandballData;
import com.algebra.vuinfo.dialog.ShowDialog;

public class HandballActivity extends Activity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);

		HandballData.LoadData();
		ListView listView = (ListView) findViewById(R.id.sports_listview);
		String[] ids = new String[HandballData.Items.size()];
		for (int i = 0; i < ids.length; i++) {

			ids[i] = Integer.toString(i + 1);
		}

		HandballAdapter adapter = new HandballAdapter(this,
				R.layout.row_all_sports, ids);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {
		case 0:

			ShowDialog.showDialogPhone(this, "Nazovi HRK Borovo",
					"tel:032422775", 45.37452, 18.95514);
			break;
		case 1:

			ShowDialog.showDialogPhone(this, "Nazovi ŽRK Vukovar",
					"tel:032421909", 45.37752, 18.96440);
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
