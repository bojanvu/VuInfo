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
import com.algebra.vuinfo.adapters.SportsAssociationAdapter;
import com.algebra.vuinfo.data.SportsAssociationData;
import com.algebra.vuinfo.dialog.ShowDialog;

public class SportsAssociationActivity extends Activity implements
		OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);

		SportsAssociationData.LoadData();
		ListView listView = (ListView) findViewById(R.id.sports_listview);
		String[] ids = new String[SportsAssociationData.Items.size()];
		for (int i = 0; i < ids.length; i++) {

			ids[i] = Integer.toString(i + 1);
		}

		SportsAssociationAdapter adapter = new SportsAssociationAdapter(this,
				R.layout.row_all_sports, ids);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {
		case 0:

			ShowDialog.showDialogPhone(this, "Nazovi Udruga graðana Viktorija",
					"tel:032410466", 45.33845, 19.01107);
			break;
		case 1:

			ShowDialog.showDialogPhone(this, "Nema podataka za poziv",
					"tel:00000000", 45.35569, 18.99429);
			break;
		case 2:

			ShowDialog.showDialogPhoneMail(this,
					"Nazovi Društvo Hrvatska žena",
					"Pošalji mail Društvo Hrvatska žena", "tel:09816622397",
					"hrvatska-zena.vukovar@vu.t-com.hr", 45.34973, 19.00369);
			break;
		case 3:

			ShowDialog.showDialogTwoPhone(this,
					"Nazovi Društvo za pomoæ (tel)",
					"Nazovi Društvo za pomoæ (mob)", "tel:032417700",
					"tel:0989671407", 45.34611, 18.99879);
			break;
		case 4:

			ShowDialog.showDialogPhoneMail(this,
					"Nazovi Udruga za pomoæ osobama",
					"Pošalji mail Udruga za pomoæ osobama", "tel:032428243",
					"vukovarske-iskrice@gmail.com", 45.34883, 19.00540);
			break;
		case 5:

			ShowDialog.showDialogPhoneMail(this,
					"Nazovi Udruga invalida rada Vukovar",
					"Pošalji mail Udruga invalida rada Vukovar",
					"tel:0917333725", "uir.vukovar@gmail.com", 45.37758,
					18.94955);
			break;
		case 6:

			ShowDialog.showDialogPhone(this, "Nazovi Top Sport",
					"tel:0915985339", 45.35729, 18.99647);
			break;
		case 7:

			ShowDialog.showDialogPhone(this,
					"Nazovi Društveni klub za sport i rekreaciju",
					"tel:098346400", 45.33245, 19.02006);
			break;
		case 8:

			ShowDialog.showDialogPhone(this,
					"Nazovi Sportska udruga Vukovar Tempo", "tel:032428537",
					45.33748, 18.99774);
			break;
		case 9:

			ShowDialog.showDialogPhone(this, "Nazovi Tanga", "tel:032416690",
					45.34265, 18.99195);

			break;
		case 10:

			ShowDialog.showDialogPhone(this,
					"Nazovi Sportska udruga Vuèedolske golubice",
					"tel:032444647", 45.33301, 19.00355);
			break;
		case 11:
			ShowDialog.showDialogMail(this, "Pošalji mail Sonja",
					"dsr.sonja@gmail.com", 45.37911, 18.95602);
			break;
		case 12:

			ShowDialog.showDialogPhone(this,
					"Nazovi Sportsko ribolovna udruga Dunav", "tel:032442237",
					45.35368, 19.00230);
			break;
		case 13:

			ShowDialog.showDialogPhone(this,
					"Nazovi Sportsko ribolovna udruga Borovo Naselje",
					"tel:09985446823", 45.38728, 18.96692);
			break;
		case 14:

			ShowDialog.showDialogPhone(this,
					"Nazovi Zajednica sportskih udruga grada Vukovara",
					"tel:032421866", 45.37752, 18.96440);
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
