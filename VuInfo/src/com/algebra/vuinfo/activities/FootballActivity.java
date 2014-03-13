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
import com.algebra.vuinfo.adapters.FootballAdapter;
import com.algebra.vuinfo.data.FootballData;
import com.algebra.vuinfo.dialog.ShowDialog;

public class FootballActivity extends Activity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);

		FootballData.LoadData();
		ListView listView = (ListView) findViewById(R.id.sports_listview);
		String[] ids = new String[FootballData.Items.size()];
		for (int i = 0; i < ids.length; i++) {

			ids[i] = Integer.toString(i + 1);
		}

		FootballAdapter adapter = new FootballAdapter(this,
				R.layout.row_all_sports, ids);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {
		case 0:

			ShowDialog.showDialogPhoneMail(this, "Nazovi HNK Vukovar 91",
					"Pošalji mail HNK Vukovar 91", "tel:032441284",
					"info@hnk-vukovar91.com", 45.35334, 18.98286);
			break;
		case 1:

			ShowDialog.showDialogPhone(this, "Nazovi NK Vuteks Sloga",
					"tel:0958589110", 45.32462, 19.00256);
			break;
		case 2:

			ShowDialog.showDialogPhone(this, "Nazovi HNK Radnièki",
					"tel:032432292", 45.37243, 18.95324);
			break;
		case 3:

			ShowDialog.showDialogTwoPhone(this, "Nazovi HNK Mitnica (mob1)",
					"Nazovi HNK Mitnica (mob2)", "tel:098289495",
					"tel:0989061630", 45.33499, 19.01918);
			break;
		case 4:

			ShowDialog.showDialogPhone(this, "Nazovi ŠNK Dunav Sotin",
					"tel:0997091766", 45.28870, 19.10534);
			break;
		case 5:

			ShowDialog.showDialogPhone(this, "Nazovi HNK Borovo",
					"tel:0951977139", 45.37868, 18.96417);
			break;
		case 6:

			ShowDialog.showDialogPhone(this, "Nazovi HNK Lipovaèa",
					"tel:0981905161", 45.38674, 18.90776);
			break;
		case 7:

			ShowDialog.showDialogPhone(this, "Nazovi Nogometna škola Victory",
					"tel:0914103003", 45.37783, 18.96067);
			break;
		case 8:

			ShowDialog.showDialogPhone(this,
					"Nazovi Malonogometni klub Primus", "tel:0914411218",
					45.40756, 18.98534);
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
