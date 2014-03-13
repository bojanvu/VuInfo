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
import com.algebra.vuinfo.adapters.ChessAdapter;
import com.algebra.vuinfo.data.ChessData;
import com.algebra.vuinfo.dialog.ShowDialog;

public class ChessActivity extends Activity implements OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sports);

		ChessData.LoadData();
		ListView listView = (ListView) findViewById(R.id.sports_listview);
		String[] ids = new String[ChessData.Items.size()];
		for (int i = 0; i < ids.length; i++) {

			ids[i] = Integer.toString(i + 1);
		}

		ChessAdapter adapter = new ChessAdapter(this, R.layout.row_all_sports,
				ids);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {
		case 0:

			ShowDialog.showDialogMail(this, "Pošalji mail ŠK Vukovar 91",
					"sahovski-klub-vukovar91@vu.t-com.hr", 45.34236, 19.00911);
			break;
		case 1:

			ShowDialog.showDialogPhone(this, "Nazovi ŠK Slaven",
					"tel:032444063", 45.34943, 19.00333);
			break;
		case 2:

			ShowDialog.showDialogPhone(this, "Nazovi ŠK Borovo 1937",
					"tel:032432092", 45.37599, 18.96297);

			break;
		case 3:

			ShowDialog.showDialogPhone(this, "Nazovi ŠK Caissa",
					"tel:032432092", 45.36946, 18.95384);
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