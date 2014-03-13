package com.algebra.vuinfo.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.algebra.vuinfo.database.Database;
import com.algebra.vuinfo.models.Country;
import com.algebra.vuinfo.models.CustomButton;

public class EuCountryActivity extends Activity {

	private String received, country, language;
	public static final int DARK_BLUE = Color.argb(255, 0, 25, 50);
	public static final int DARK_BLUE2 = Color.argb(255, 0, 51, 100);
	private Database mDatabase;
	private Country mCountry;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// Get information about selected language and country
		Intent in = getIntent();
		if (in.hasExtra("CountriesActivityData")) {
			received = in.getExtras().getString("CountriesActivityData");
			String[] recArray = received.split(",");
			country = recArray[0];
			// Log.d("CustomButton.java msg:", "--->Obtained h: " +country
			// +"<---");
			// country =country.replaceAll("\n", "");//.replaceAll("\\W","");
			language = recArray[1];
		}

		// Get selected country data from the database
		mDatabase = new Database(this, language);
		mCountry = new Country();
		mCountry = mDatabase.dbHelper.getCountryByDomain(mDatabase.dbHelper
				.getDomainById("1"));
		String[] rowName = { mCountry.getCountryName(),
				mCountry.getCountryOfficialName(),
				mCountry.getCountryCapital(), mCountry.getOfficialLanguages(),
				mCountry.getCountryStatus(), mCountry.getCountryCurrency(),
				mCountry.getCountryCode(), mCountry.getCountryDomain(),
				mCountry.getCountryCallingCode(),
				mCountry.getCountryTimeZones(),
				mCountry.getCountryPopulation(),
				mCountry.getPopulationPerDensity(),
				mCountry.getPopulationPerNumber(),
				mCountry.getPopulationPerArea(), mCountry.getCountryArea(),
				mCountry.getPerWaterArea(), mCountry.getCountryGdp(),
				mCountry.getGdpPerPerson(), mCountry.getCountryPresident(),
				mCountry.getCountryPrimeMinister(),
				mCountry.getCountryIndependance() };

		mCountry = mDatabase.dbHelper.getCountryByDomain(country);
		String[] row = { mCountry.getCountryName(),
				mCountry.getCountryOfficialName(),
				mCountry.getCountryCapital(), mCountry.getOfficialLanguages(),
				mCountry.getCountryStatus(), mCountry.getCountryCurrency(),
				mCountry.getCountryCode(), mCountry.getCountryDomain(),
				mCountry.getCountryCallingCode(),
				mCountry.getCountryTimeZones(),
				mCountry.getCountryPopulation(),
				mCountry.getPopulationPerDensity(),
				mCountry.getPopulationPerNumber(),
				mCountry.getPopulationPerArea(), mCountry.getCountryArea(),
				mCountry.getPerWaterArea(), mCountry.getCountryGdp(),
				mCountry.getGdpPerPerson(), mCountry.getCountryPresident(),
				mCountry.getCountryPrimeMinister(),
				mCountry.getCountryIndependance() };

		// Custom Fonts
		Typeface fontTFcolumn1 = Typeface.createFromAsset(getAssets(),
				"fonts/Parisienne-Regular.ttf");
		Typeface fontTFcolumn2 = Typeface.createFromAsset(getAssets(),
				"fonts/Pacifico.ttf");

		RelativeLayout relativeLayout = new RelativeLayout(this);
		RelativeLayout.LayoutParams relativeLayoutParams;
		relativeLayout.setVerticalScrollBarEnabled(true);

		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayoutParams.height = 78;

		this.setTitle(mCountry.getCountryName());
		this.setTitleColor(VuEuActivity.PANTONE_YELLOW);

		super.onCreate(savedInstanceState);

		// background color
		relativeLayout.setBackgroundColor(VuEuActivity.PANTONE_REFLEX_BLUE);

		// ====== Display images of country flag and coat of the arms ======
		// get width of the screen (used for displaying images at portion of
		// obtained width)
		int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
		ImageView imgView1 = new ImageView(this);
		// R.id (view identifier - does not have to be unique in this view's
		// hierarchy, must be a positive number - not 0) of view, 2 times length
		// of the rows with displayed data (which have id's from 1 to 2*n +1)
		// plus 10 to ensure they do not overlap.
		final int ID_IMGV1 = row.length * 2 + 10;
		imgView1.setId(ID_IMGV1);
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,
				imgView1.getId());
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
				imgView1.getId());
		relativeLayout.addView(imgView1, relativeLayoutParams);
		// Displays in 1st portion of the screen, values chosen to little bit
		// overlap image 2 over image 1.
		imgView1.getLayoutParams().width = (int) (width * 0.65);
		imgView1.getLayoutParams().height = (int) (width * 0.45);
		imgView1.setImageDrawable(getResources().getDrawable(
				getResources().getIdentifier("drawable/" + country + "1",
						"drawable", getPackageName())));

		ImageView imgView2 = new ImageView(this);
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
				imgView1.getId());
		relativeLayoutParams
				.addRule(RelativeLayout.ALIGN_TOP, imgView1.getId());
		relativeLayout.addView(imgView2, relativeLayoutParams);
		imgView2.getLayoutParams().width = (int) (width * 0.45);
		imgView2.getLayoutParams().height = (int) (width * 0.45);
		imgView2.setImageDrawable(getResources().getDrawable(
				getResources().getIdentifier("drawable/" + country + "2",
						"drawable", getPackageName())));

		// ======= Data for filling text views =====================
		int rowsNo = row.length;// Numbers of rows
		TextView[] textView = new TextView[rowsNo * 2];
		// Print country data
		int i = 0;// counter for textView array
		int id;// for setting row's 1st view identifier used for alignment row's
				// 2nd view and next row view
		for (int j = 0; j < rowsNo; j++) {
			// 1st TextView in a row
			textView[i] = new TextView(this);
			relativeLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			textView[i].setId(i + 1);
			textView[i].setTextColor(VuEuActivity.PANTONE_YELLOW);
			textView[i].setTypeface(fontTFcolumn1);
			textView[i].setTextSize(22f);
			textView[i].setText(rowName[j]);
			if (i != 0)
				id = textView[i - 2].getId();
			else
				id = ID_IMGV1;
			relativeLayoutParams.addRule(RelativeLayout.BELOW, id);
			if ((j & 1) != 0) {
				textView[i].setBackgroundColor(VuEuActivity.PANTONE_YELLOW);
				textView[i].setTextColor(VuEuActivity.PANTONE_REFLEX_BLUE);
			}
			relativeLayout.addView(textView[i], relativeLayoutParams);

			// 2nd TextView in a row
			i++;
			textView[i] = new TextView(this);
			relativeLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.MATCH_PARENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			textView[i].setTextColor(VuEuActivity.PANTONE_YELLOW);
			textView[i].setText(row[j]);
			textView[i].setTypeface(fontTFcolumn2);
			textView[i].setTextSize(17f);
			relativeLayoutParams.addRule(RelativeLayout.RIGHT_OF,
					textView[i - 1].getId());
			relativeLayoutParams.addRule(RelativeLayout.ALIGN_TOP,
					textView[i - 1].getId());
			if ((j & 1) != 0) {
				textView[i].setBackgroundColor(VuEuActivity.PANTONE_YELLOW);
				textView[i].setTextColor(VuEuActivity.PANTONE_REFLEX_BLUE);
			}
			relativeLayout.addView(textView[i], relativeLayoutParams);
			i++;
		}

		// Add a button
		Button btnReturn = new CustomButton(this);
		if (language.equalsIgnoreCase("en"))
			btnReturn.setText("Home");
		else
			btnReturn.setText("Pocetak");
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		btnReturn.setLayoutParams(relativeLayoutParams);
		relativeLayout.addView(btnReturn);
		OnClickListener l = new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent getBack = new Intent();
				setResult(VuEuActivity.RESULT_YES, getBack);
				onStop();
				finish();
			}
		};
		btnReturn.setOnClickListener(l);

		// add vertical and horizontal scrolling
		ScrollView vScroll = new ScrollView(this);
		HorizontalScrollView hScroll = new HorizontalScrollView(this);
		hScroll.setHorizontalScrollBarEnabled(true);
		hScroll.addView(relativeLayout);
		hScroll.setFillViewport(true);
		vScroll.addView(hScroll);
		vScroll.setFillViewport(true);
		setContentView(vScroll);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#336699")));
		return true;
	}
}
