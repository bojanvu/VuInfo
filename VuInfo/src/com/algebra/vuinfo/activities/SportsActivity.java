package com.algebra.vuinfo.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.algebra.vuinfo.R;
import com.algebra.vuinfo.adapters.SportsAdapter;
import com.algebra.vuinfo.data.SportsData;

public class SportsActivity extends Activity implements OnItemClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);

		SportsData.LoadData();
		ListView listView = (ListView) findViewById(R.id.sports_listview);
		String[] ids = new String[SportsData.Items.size()];
		for (int i = 0; i < ids.length; i++) {

			ids[i] = Integer.toString(i + 1);
		}

		SportsAdapter adapter = new SportsAdapter(this, R.layout.row_sports,
				ids);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {
		case 0:

			Intent football = new Intent(this, FootballActivity.class);
			startActivity(football);
			break;

		case 1:

			Intent basketball = new Intent(this, BasketballActivity.class);
			startActivity(basketball);
			break;

		case 2:

			Intent volleyball = new Intent(this, VolleyballActivity.class);
			startActivity(volleyball);
			break;

		case 3:

			Intent handball = new Intent(this, HandballActivity.class);
			startActivity(handball);
			break;

		case 4:

			Intent tennis = new Intent(this, TennisActivity.class);
			startActivity(tennis);
			break;

		case 5:

			Intent combatSports = new Intent(this, CombatSportsActivity.class);
			startActivity(combatSports);
			break;

		case 6:

			Intent chess = new Intent(this, ChessActivity.class);
			startActivity(chess);
			break;

		case 7:

			Intent bocce = new Intent(this, BocceActivity.class);
			startActivity(bocce);
			break;

		case 8:

			Intent otherSports = new Intent(this, OtherSportsActivity.class);
			startActivity(otherSports);
			break;

		case 9:

			Intent fitness = new Intent(this, FitnessActivity.class);
			startActivity(fitness);
			break;

		case 10:

			Intent sportsAssociation = new Intent(this,
					SportsAssociationActivity.class);
			startActivity(sportsAssociation);
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
