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
import com.algebra.vuinfo.adapters.OtherSportsAdapter;
import com.algebra.vuinfo.data.OtherSportsData;
import com.algebra.vuinfo.dialog.ShowDialog;

public class OtherSportsActivity extends Activity implements
		OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);

		OtherSportsData.LoadData();
		ListView listView = (ListView) findViewById(R.id.sports_listview);
		String[] ids = new String[OtherSportsData.Items.size()];
		for (int i = 0; i < ids.length; i++) {

			ids[i] = Integer.toString(i + 1);
		}

		OtherSportsAdapter adapter = new OtherSportsAdapter(this,
				R.layout.row_all_sports, ids);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {
		case 0:

			ShowDialog.showDialogPhoneMail(this,
					"Nazovi Hrvatski veslaèki klub Vukovar",
					"Pošalji mail Hrvatski veslaèki klub Vukovar",
					"tel:098436509", "hvk-vukovar@optinet.hr", 45.35394,
					19.00304);
			break;
		case 1:

			ShowDialog.showDialogPhoneMail(this,
					"Nazovi Gimnastièki klub Vukovar",
					"Pošalji mail Gimnastièki klub Vukovar", "tel:0913050775",
					"gimnastika.vu@gmail.com", 45.37752, 18.96440);
			break;
		case 2:

			ShowDialog.showDialogTwoPhone(this,
					"Nazovi Hrvatski kuglaèki klub Vukovar (mob1)",
					"Nazovi Hrvatski kuglaèki klub Vukovar (mob2)",
					"tel:09816622397", "tel:0989229692", 45.33531, 19.00186);
			break;
		case 3:

			ShowDialog.showDialogPhone(this, "Nazovi Atletski klub Maraton",
					"tel:098588377", 45.34032, 19.00723);
			break;
		case 4:

			ShowDialog.showDialogPhoneMail(this,
					"Nazovi Streljaèki klub Griè 7",
					"Pošalji mail Streljaèki klub Griè 7", "tel:032414555",
					"gric7.vukovar@gmail.com", 45.35415, 18.99911);
			break;
		case 5:

			ShowDialog.showDialogPhoneMail(this,
					"Nazovi Sportski moto klub Golubica",
					"Pošalji mail Sportski moto klub Golubica",
					"tel:0951977139", "golubicavukovar@gmail.com", 45.37670,
					18.96063);
			break;
		case 6:

			ShowDialog.showDialogMail(this,
					"Pošalji mail Sportski biciklistièki klub Vukovar",
					"e-mail: jare1906@hotmail.com", 45.34799, 19.00507);
			break;
		case 7:

			ShowDialog.showDialogPhone(this,
					"Nazovi Klub podvodnih aktivnosti Vukovar",
					"tel:0981965192", 45.34075, 19.00834);
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
