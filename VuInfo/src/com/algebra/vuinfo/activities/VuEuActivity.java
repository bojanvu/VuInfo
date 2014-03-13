package com.algebra.vuinfo.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.algebra.vuinfo.R;
import com.algebra.vuinfo.models.CustomButton;

public class VuEuActivity extends Activity implements OnClickListener {

	protected final static boolean DEBUGGING_FLAG = false;

	private static String strChoosenLang = "";
	private Button buttonEn, buttonAbout, buttonQuiz, buttonInfo, buttonHr;
	// official colors for flag's azure background and golden stars
	public static final int PANTONE_REFLEX_BLUE = Color.argb(255, 0, 51, 153);
	public static final int PANTONE_YELLOW = Color.argb(255, 255, 204, 0);
	public static final int REQUEST_CODE = 0;
	public static final int RESULT_YES = 1;
	public static final int RESULT_NO = -1;
	private TextView txViewCenter, txViewDesc;

	// IDs for the widgets
	public static final int ID_TV_DESC = 10;
	public static final int ID_BTN_HR = 11;
	public static final int ID_BTN_EN = 12;
	public static final int ID_BTN_ABOUT = 15;
	public static final int ID_BTN_QUIZ = 16;
	public static final int ID_BTN_INFO = 17;
	public static final int ID_LL_CHILD3 = 19;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitleColor(PANTONE_YELLOW);
		setContentView(R.layout.activity_vu_eu);

		ImageView imgStars, imgStars2, imgBkg;

		setTitleColor(PANTONE_YELLOW);
		this.setTitle("Welcome!");

		RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.main_relayout);
		// Center a children in the parent view
		// relativeLayout.setHorizontalGravity(Gravity.CENTER);
		relativeLayout.setBackgroundColor(PANTONE_REFLEX_BLUE);
		// Image stars
		RelativeLayout.LayoutParams relativeLayoutParams;
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		imgStars = new ImageView(this);
		relativeLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,
				imgStars.getId());
		relativeLayout.addView(imgStars, relativeLayoutParams);
		imgStars.setImageResource(R.drawable.stars);

		// Image 2 background stars
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		imgStars2 = new ImageView(this);
		relativeLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,
				imgStars.getId());
		relativeLayout.addView(imgStars2, relativeLayoutParams);
		imgStars2.setAlpha(30);
		imgStars2.setImageResource(R.drawable.stars);

		// Set center text (VU)
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		txViewCenter = new TextView(this);
		relativeLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		relativeLayout.addView(txViewCenter, relativeLayoutParams);
		txViewCenter.setText("VU");
		txViewCenter.setTextColor(PANTONE_YELLOW);
		txViewCenter.setTextSize(50f);
		txViewCenter.setTypeface(Typeface.DEFAULT_BOLD);
		txViewCenter.startAnimation(AnimationUtils.loadAnimation(
				VuEuActivity.this, android.R.anim.fade_in));
		// Text below title bar
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		txViewDesc = new TextView(this);
		txViewDesc.setId(ID_TV_DESC);
		txViewDesc.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		relativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		relativeLayout.addView(txViewDesc, relativeLayoutParams);
		txViewDesc
				.setText("Molimo prvo odaberite jezik\n Please choose language first!");
		txViewDesc.setTextColor(PANTONE_YELLOW);
		txViewDesc.setTextSize(12f);
		txViewDesc.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);

		// ======= 2 buttons for choosing language =======
		// in a separate layout of custom width in dp which is centered in the
		// parent layout buttons have equal weight so they are dispersed equally
		// (except when length of button with longer text override shorter)
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				(int) TypedValue.applyDimension(
						(int) TypedValue.COMPLEX_UNIT_DIP, 320, getResources()
								.getDisplayMetrics()),
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		LinearLayout linLayChild2 = new LinearLayout(this);
		// Place buttons in a row
		linLayChild2.setOrientation(LinearLayout.HORIZONTAL);
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		relativeLayout.addView(linLayChild2, relativeLayoutParams);
		// 1st button
		LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		buttonHr = new CustomButton(this);
		buttonHr.setId(ID_BTN_HR);
		linearLayoutParams.weight = (float) 0.5; // take 50% of layout space
		linLayChild2.addView(buttonHr, linearLayoutParams);
		buttonHr.setOnClickListener(this);
		buttonHr.setText("Hrvatski");
		// Place country flag icon in button, but first resize it to have height
		// same as text
		Drawable dr = getResources().getDrawable(R.drawable.ic_hr);
		// intrinsic values are affected by the target density, so they are NOT
		// the dimensions of the original image.
		float xyScaleFactor = (float) (buttonHr.getTextSize() / (float) (dr
				.getIntrinsicHeight()));
		Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
		Drawable d = new BitmapDrawable(getResources(),
				Bitmap.createScaledBitmap(bitmap,
						(int) (dr.getIntrinsicWidth() * xyScaleFactor),
						(int) (dr.getIntrinsicHeight() * xyScaleFactor), true));
		buttonHr.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);

		// 2nd button
		linearLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		linearLayoutParams.weight = (float) 0.5;
		buttonEn = new CustomButton(this);
		buttonEn.setId(ID_BTN_EN);
		linLayChild2.addView(buttonEn, linearLayoutParams);
		buttonEn.setOnClickListener(this);
		buttonEn.setText("English");
		dr = getResources().getDrawable(R.drawable.ic_uk);
		xyScaleFactor = (float) (buttonEn.getTextSize() / (int) (dr
				.getIntrinsicHeight()));
		bitmap = ((BitmapDrawable) dr).getBitmap();
		d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(
				bitmap, (int) (dr.getIntrinsicWidth() * xyScaleFactor),
				(int) (dr.getIntrinsicHeight() * xyScaleFactor), true));
		buttonEn.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);

		// ========= 3 buttons for choosing further activities ============
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				(int) TypedValue.applyDimension(
						(int) TypedValue.COMPLEX_UNIT_DIP, 320, getResources()
								.getDisplayMetrics()),
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		LinearLayout linLayChild3 = new LinearLayout(this);
		linLayChild3.setId(ID_LL_CHILD3);
		linLayChild3.setOrientation(LinearLayout.HORIZONTAL);
		relativeLayoutParams.addRule(RelativeLayout.BELOW, txViewDesc.getId());
		relativeLayout.addView(linLayChild3, relativeLayoutParams);

		// Left button
		linearLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		buttonAbout = new CustomButton(this);
		buttonAbout.setId(ID_BTN_ABOUT);
		linearLayoutParams.weight = (float) 0.33; // take 1/3 of layout space
		linLayChild3.addView(buttonAbout, linearLayoutParams);
		buttonAbout.setOnClickListener(this);
		buttonAbout.setEnabled(false);
		buttonAbout.setText("...");

		// center button
		linearLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		buttonQuiz = new CustomButton(this);
		buttonQuiz.setId(ID_BTN_QUIZ);
		linearLayoutParams.weight = (float) 0.34;
		linLayChild3.addView(buttonQuiz, linearLayoutParams);
		buttonQuiz.setOnClickListener(this);
		buttonQuiz.setEnabled(false);
		buttonQuiz.setText("...");

		// Right button
		linearLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		buttonInfo = new CustomButton(this);
		buttonInfo.setId(ID_BTN_INFO);
		linearLayoutParams.weight = (float) 0.33;
		linLayChild3.addView(buttonInfo, linearLayoutParams);
		buttonInfo.setOnClickListener(this);
		buttonInfo.setEnabled(false);
		buttonInfo.setText("...");

		// background image world map
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		imgBkg = new ImageView(this);
		relativeLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,
				imgBkg.getId());
		relativeLayout.addView(imgBkg, relativeLayoutParams);
		imgBkg.setAlpha(35);
		imgBkg.setImageResource(R.drawable.world);

	}

	@Override
	public void onClick(View v) {
		Intent i;
		switch (v.getId()) {
		case ID_BTN_HR:
			strChoosenLang = "hr";
			buttonHr.setEnabled(false);
			buttonEn.setEnabled(true);
			txViewCenter.startAnimation(AnimationUtils.loadAnimation(
					VuEuActivity.this, android.R.anim.fade_in));
			buttonAbout.setEnabled(true);
			buttonQuiz.setEnabled(true);
			buttonInfo.setEnabled(true);
			txViewDesc.startAnimation(AnimationUtils.loadAnimation(
					VuEuActivity.this, android.R.anim.fade_out));
			txViewDesc.setTextSize(24f);
			txViewDesc.setText("Odaberite zeljenu opciju!");
			txViewDesc.setTypeface(Typeface.DEFAULT);
			txViewDesc.startAnimation(AnimationUtils.loadAnimation(
					VuEuActivity.this, android.R.anim.fade_in));
			buttonAbout.setText("Opis");
			buttonQuiz.setText("Kviz");
			buttonInfo.setText("Info");
			break;
		case ID_BTN_EN:
			strChoosenLang = "en";
			buttonHr.setEnabled(true);
			buttonEn.setEnabled(false);
			txViewCenter.startAnimation(AnimationUtils.loadAnimation(
					VuEuActivity.this, android.R.anim.fade_in));
			buttonAbout.setEnabled(true);
			buttonQuiz.setEnabled(true);
			buttonInfo.setEnabled(true);
			txViewDesc.startAnimation(AnimationUtils.loadAnimation(
					VuEuActivity.this, android.R.anim.fade_out));
			txViewDesc.setTextSize(24f);
			txViewDesc.setText("Please choose an option!");
			txViewDesc.setTypeface(Typeface.DEFAULT);
			txViewDesc.startAnimation(AnimationUtils.loadAnimation(
					VuEuActivity.this, android.R.anim.fade_in));
			buttonAbout.setText("About");
			buttonQuiz.setText("Quiz");
			buttonInfo.setText("Info");
			break;
		case ID_BTN_INFO:
			txViewCenter.startAnimation(AnimationUtils.loadAnimation(
					VuEuActivity.this, android.R.anim.fade_out));
			i = new Intent(this, EuCountriesActivity.class);
			i.putExtra("MainActivityData", strChoosenLang);
			startActivityForResult(i, REQUEST_CODE);
			break;
		case ID_BTN_ABOUT:
			txViewCenter.startAnimation(AnimationUtils.loadAnimation(
					VuEuActivity.this, android.R.anim.fade_out));

			i = new Intent(this, AboutActivity.class);
			i.putExtra("MainActivityData", strChoosenLang);
			startActivityForResult(i, REQUEST_CODE);
			break;
		case ID_BTN_QUIZ:
			txViewCenter.startAnimation(AnimationUtils.loadAnimation(
					VuEuActivity.this, android.R.anim.fade_out));

			i = new Intent(this, QuizActivity.class);
			i.putExtra("MainActivityData", strChoosenLang);
			startActivityForResult(i, REQUEST_CODE);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE) {
			if (resultCode == RESULT_YES) {
				buttonAbout.setEnabled(true);
				buttonQuiz.setEnabled(true);
				buttonInfo.setEnabled(true);
			} else if (resultCode == RESULT_NO) {
				onStop();
				finish();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#336699")));
		return true;
	}

}