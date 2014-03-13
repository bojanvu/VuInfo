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
import com.algebra.vuinfo.adapters.TennisAdapter;
import com.algebra.vuinfo.data.TennisData;
import com.algebra.vuinfo.dialog.ShowDialog;

public class TennisActivity extends Activity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);

		TennisData.LoadData();
		ListView listView = (ListView) findViewById(R.id.sports_listview);
		String[] ids = new String[TennisData.Items.size()];
		for (int i = 0; i < ids.length; i++) {

			ids[i] = Integer.toString(i + 1);
		}

		TennisAdapter adapter = new TennisAdapter(this,
				R.layout.row_all_sports, ids);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {
		case 0:

			ShowDialog.showDialogTwoPhone(this,
					"Nazovi Teniski klub Borovo Naselje (tel)",
					"Nazovi Teniski klub Borovo Naselje (mob)",
					"tel:032423113", "tel:0912572576", 45.37868, 18.96417);
			break;
		case 1:

			ShowDialog.showDialogPhone(this, "Nazovi Teniski Klub Vukovar",
					"tel:0991941855", 45.34075, 19.00834);
			break;
		case 2:

			ShowDialog.showDialogPhone(this,
					"Nazovi Stolnoteniski klub Vukovar 91", "tel:0995720639",
					45.34462, 19.00385);
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
