package com.algebra.vuinfo.activities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.algebra.vuinfo.R;
import com.algebra.vuinfo.database.Database;
import com.algebra.vuinfo.models.Country;
import com.algebra.vuinfo.models.CustomButton;
import com.algebra.vuinfo.models.CustomShapeDrawable;

public class EuCountriesActivity extends Activity implements OnTouchListener,
		OnClickListener {

	private ImageView imgViewEU2, imgViewEU1, imgViewEU3;
	private TextView txViewCountryName;
	private Bitmap mask;
	Drawable drawable;
	Drawable drawableOr, drawableScaled;
	Bitmap bitmap;
	private String language = "en";// Default language
	private static String domain = "";
	public static final int REQUEST_CODE = 0;

	private Database mDatabase;
	private ArrayList<Country> mCountries;
	private Button btnDialog, btnShow;
	// Widgets IDs:
	private static final int ID_IVEU1 = 1;
	private static final int ID_IVEU2 = 2;
	private static final int ID_TV_CNAME = 3;
	private static final int ID_BTN_DIALOG = 11;
	private static final int ID_BTN_SHOW = 12;
	private final ArrayList<String> arrConNames = new ArrayList<String>();
	private final ArrayList<String> arrOffNames = new ArrayList<String>();
	private final ArrayList<String> arrDomains = new ArrayList<String>();
	private String tvcnMsg, bdMsg, bsMsg, toastMsg, notEUMsg1, notEUMsg2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Get chosen language.
		Intent i = getIntent();
		if (i.hasExtra("MainActivityData")) {
			language = i.getExtras().getString("MainActivityData");
		}

		// Set widgets according to chosen language.
		if (language.equalsIgnoreCase("en")) {
			this.setTitle("Countries of Europe");
			tvcnMsg = "Choose Country on Map or";
			bdMsg = "Choose from list";
			bsMsg = "View Details";
			toastMsg = "Plase Choose Country!";
			notEUMsg1 = "Choose EU country";
			notEUMsg2 = "Not a member of EU";
		} else if (language.equalsIgnoreCase("hr")) {
			this.setTitle("Zemlje Europe");
			tvcnMsg = "Odaberite zemlju na mapi ili";
			bdMsg = "Odaberite iz liste";
			bsMsg = "Detaljnije";
			toastMsg = "Molimo odaberite zemlju!";
			notEUMsg1 = "Odaberite zemlju clanicu EU";
			notEUMsg2 = "Nije clanica EU";
		} else
			this.setTitle("Error: Language selection error!");

		this.setTitleColor(VuEuActivity.PANTONE_YELLOW);
		// Load custom fonts
		Typeface fontTF = Typeface.createFromAsset(getAssets(),
				"fonts/Pacifico.ttf");

		// Copy database file from assets if it doesn't exist.
		try {
			mDatabase = new Database(this, "hr");
			mDatabase.dbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error(
					"Error: Unable to create database: method createDataBase caused IOException");
		}

		// get data for chosen country
		mDatabase = new Database(this, language);
		// Country mCountry = new Country();
		mCountries = mDatabase.dbHelper.getAllCountries();

		// start from 1 not 0 because first row contains name of column not
		// data
		for (int c = 1; c < mCountries.size(); c++) {
			arrConNames.add((mCountries.get(c)).getCountryName());
		}
		for (int c = 1; c < mCountries.size(); c++) {
			arrOffNames.add((mCountries.get(c)).getCountryOfficialName());
		}
		for (int c = 1; c < mCountries.size(); c++) {
			arrDomains.add((mCountries.get(c)).getCountryDomain());
		}

		// Background color
		RelativeLayout relativeLayout = new RelativeLayout(this);
		relativeLayout.setBackgroundColor(VuEuActivity.PANTONE_REFLEX_BLUE);

		// Display selected country
		RelativeLayout.LayoutParams relativeLayoutParams;
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		txViewCountryName = new TextView(this);
		txViewCountryName.setId(ID_TV_CNAME);
		txViewCountryName.setTextColor(VuEuActivity.PANTONE_YELLOW);
		txViewCountryName.setTypeface(fontTF);
		txViewCountryName.setTextSize((float) 25);
		// txViewCountryName.setTextScaleX((float) 1.4);
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,
				txViewCountryName.getId());
		relativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL,
				txViewCountryName.getId());
		relativeLayout.addView(txViewCountryName, relativeLayoutParams);
		txViewCountryName.setGravity(Gravity.CENTER_HORIZONTAL);
		if (tvcnMsg != null)
			txViewCountryName.setText(tvcnMsg);

		// Image that we use for mapping (256color bitmap for easier color pick)
		imgViewEU1 = new ImageView(this);
		imgViewEU1.setId(ID_IVEU1);
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayoutParams.addRule(RelativeLayout.BELOW,
				txViewCountryName.getId());
		relativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		// relativeLayout.addView(imgViewEU1, relativeLayoutParams);
		// we don't need to add view since we don't show it anyway and we can
		// use like this to obtain bitmap

		// 2nd image that is shown
		imgViewEU2 = new ImageView(this);
		imgViewEU2.setId(ID_IVEU2);
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayoutParams.addRule(RelativeLayout.BELOW,
				txViewCountryName.getId());
		relativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		relativeLayout.addView(imgViewEU2, relativeLayoutParams);
		imgViewEU2.setImageResource(R.drawable.eu);
		// adjust (resize) view according to image (scaled) size.
		imgViewEU2.setAdjustViewBounds(true);
		imgViewEU2.setOnTouchListener(this);

		// preload the 256 color image for mapping from resources
		BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
		// NOTICE: possible problems, because ARGB_4444 is sometimes stored as
		// ARGB_8888, but it doesn't work with RGB_565.
		bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_4444;
		Context appContext = this.getApplicationContext();
		mask = BitmapFactory.decodeResource(appContext.getResources(),
				R.drawable.eubitmap, bitmapOptions);
		// place it into the identical view as shown image and make it invisible
		imgViewEU1.setImageBitmap(mask);
		ImageView map = imgViewEU1;
		// imgViewEU1.setVisibility(View.INVISIBLE);
		// get it from the view into drawable from which we will pick color at
		// position
		map.setDrawingCacheEnabled(true);
		drawable = map.getDrawable();
		// Set listener on the shown image on which will pick position of event

		// Block of code for a button for which we created custom dialog to act
		// similar to spinner widget
		btnDialog = new CustomButton(this);
		btnDialog.setId(ID_BTN_DIALOG);
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				(int) TypedValue.applyDimension(
						(int) TypedValue.COMPLEX_UNIT_DIP, 300, getResources()
								.getDisplayMetrics()),
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayoutParams.addRule(RelativeLayout.BELOW, imgViewEU2.getId());
		relativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		btnDialog.setOnClickListener(this);
		relativeLayout.addView(btnDialog, relativeLayoutParams);
		if (bdMsg != null)
			btnDialog.setText(bdMsg);
		btnDialog.setTextColor(VuEuActivity.PANTONE_YELLOW);
		btnDialog.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);

		// To obtain position of button on the screen
		int x = btnDialog.getLeft();
		int y = btnDialog.getTop();
		Log.d("EuCountriesActivity.java msg:", "--->x and y: " + x + " and "
				+ y + "<---");

		// and button to submit selected country to view details
		btnShow = new CustomButton(this);
		btnShow.setId(ID_BTN_SHOW);
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				(int) TypedValue.applyDimension(
						(int) TypedValue.COMPLEX_UNIT_DIP, 150, getResources()
								.getDisplayMetrics()),// RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayoutParams.addRule(RelativeLayout.BELOW, btnDialog.getId());
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_LEFT,
				btnDialog.getId());
		btnShow.setOnClickListener(this);
		if (bsMsg != null)
			btnShow.setText(bsMsg);
		relativeLayout.addView(btnShow, relativeLayoutParams);
		btnShow.setTextColor(VuEuActivity.PANTONE_YELLOW);

		// image view to display country flag of sellected country
		imgViewEU3 = new ImageView(this);
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayoutParams.addRule(RelativeLayout.RIGHT_OF, btnShow.getId());
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_TOP, btnShow.getId());
		relativeLayout.addView(imgViewEU3, relativeLayoutParams);

		// add vertical scrolling
		ScrollView vScroll = new ScrollView(this);
		vScroll.addView(relativeLayout);
		vScroll.setFillViewport(true);
		setContentView(vScroll);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#336699")));
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// color definitions
		int touchColor = 0;
		final int al = -3355512;// Albania
		final int am = -16724924;// Armenia
		final int at = -1170876;// Austria
		final int az = -1153536;// Azerbedjan
		final int ba = -1162104;// BiH
		final int be = -1179512;// Belgium
		final int bg = -7811892;// Bulgaria
		final int by = -5627392;// Belarus
		final int ch = -12320632;// Switzerland
		final int cy = -10092476;// Cyprus
		final int cz = -7846912;// Czech
		final int de = -1135924;// Germany
		final int dk = -1144764;// Denmark
		final int ee = -14496564;// Estonia
		final int es = -1144696;// Spain
		final int fi = -3372988;// Finland
		final int fr = -7829504;// France
		final int ge = -7803256;// Georgia
		final int gr = -10083840;// Greece
		final int hr = -5618620;// Croatia
		final int hu = -12285816;// Hungary
		final int ie = -12303360;// Ireland
		final int is = -3346944;// Iceland
		final int it = -5618484;// Italy
		final int lu = -16711936;// Luxemburg
		final int lt = -1170944;// Lithuania
		final int lv = -1127356;// Latvia
		final int md = -1153332;// Moldova
		final int me = -65536;// MonteNegro
		final int mk = -1153468;// Macedonia
		final int mt = -16759740;// Malta
		final int nl = -5575032;// Netherland
		final int no = -12320564;// Norway
		final int pl = -16759808;// Poland
		final int pt = -256;// Portugal
		final int ro = -1127288;// Romania
		final int rs = -3399168;// Serbia
		final int ru = -1;// Russia
		final int se = -10048956;// Sweden
		final int si = -1118584;// Slovenia
		final int sk = -7864252;// Slovakia
		final int tr = -3351092;// Turkey
		final int ua = -5592372;// Ukraine
		final int uk = -7864320;// UnitedKingdom

		touchColor = getPixelColor(v, event);

		// Click on an image
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			switch (touchColor) {
			case is:
				respondOnAction("is", null);
				break;
			case it:
				respondOnAction("it", null);
				break;
			case at:
				respondOnAction("at", null);
				break;
			case am:
				respondOnAction("am", null);
				break;
			case de:
				respondOnAction("de", null);
				break;
			case pt:
				respondOnAction("pt", null);
				break;
			case es:
				respondOnAction("es", null);
				break;
			case fr:
				respondOnAction("fr", null);
				break;
			case ie:
				respondOnAction("ie", null);
				break;
			case uk:
				respondOnAction("uk", null);
				break;
			case be:
				respondOnAction("be", null);
				break;
			case nl:
				respondOnAction("nl", null);
				break;
			case lu:
				respondOnAction("lu", null);
				break;
			case dk:
				respondOnAction("dk", null);
				break;
			case se:
				respondOnAction("se", null);
				break;
			case no:
				respondOnAction("no", null);
				break;
			case fi:
				respondOnAction("fi", null);
				break;
			case ch:
				respondOnAction("ch", null);
				break;
			case pl:
				respondOnAction("pl", null);
				break;
			case cz:
				respondOnAction("cz", null);
				break;
			case sk:
				respondOnAction("sk", null);
				break;
			case hu:
				respondOnAction("hu", null);
				break;
			case si:
				respondOnAction("si", null);
				break;
			case hr:
				respondOnAction("hr", null);
				break;
			case ba:
				respondOnAction("ba", null);
				break;
			case rs:
				respondOnAction("rs", null);
				break;
			case bg:
				respondOnAction("bg", null);
				break;
			case ro:
				respondOnAction("ro", null);
				break;
			case md:
				respondOnAction("md", null);
				break;
			case ua:
				respondOnAction("ua", null);
				break;
			case by:
				respondOnAction("by", null);
				break;
			case lt:
				respondOnAction("lt", null);
				break;
			case lv:
				respondOnAction("lv", null);
				break;
			case ee:
				respondOnAction("ee", null);
				break;
			case ge:
				respondOnAction("ge", null);
				break;
			case az:
				respondOnAction("az", null);
				break;
			case ru:
				respondOnAction("ru", null);
				break;
			case mk:
				respondOnAction("mk", null);
				break;
			case gr:
				respondOnAction("gr", null);
				break;
			case cy:
				respondOnAction("cy", null);
				break;
			case me:
				respondOnAction("me", null);
				break;
			case al:
				respondOnAction("al", null);
				break;
			case mt:
				respondOnAction("mt", null);
				break;
			case tr:
				respondOnAction("tr", null);
				break;
			case 0:
				imgViewEU2.setImageResource(R.drawable.eu);
				break;
			}
		}
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			switch (touchColor) {
			case al:
				imgViewEU2.setImageResource(R.drawable.al);
				break;
			case am:
				imgViewEU2.setImageResource(R.drawable.am);
				break;
			case at:
				imgViewEU2.setImageResource(R.drawable.at);
				break;
			case az:
				imgViewEU2.setImageResource(R.drawable.az);
				break;
			case ba:
				imgViewEU2.setImageResource(R.drawable.ba);
				break;
			case be:
				imgViewEU2.setImageResource(R.drawable.be);
				break;
			case bg:
				imgViewEU2.setImageResource(R.drawable.bg);
				break;
			case by:
				imgViewEU2.setImageResource(R.drawable.by);
				break;
			case ch:
				imgViewEU2.setImageResource(R.drawable.ch);
				break;
			case cy:
				imgViewEU2.setImageResource(R.drawable.cy);
				break;
			case cz:
				imgViewEU2.setImageResource(R.drawable.cz);
				break;
			case de:
				imgViewEU2.setImageResource(R.drawable.de);
				break;
			case dk:
				imgViewEU2.setImageResource(R.drawable.dk);
				break;
			case ee:
				imgViewEU2.setImageResource(R.drawable.ee);
				break;
			case es:
				imgViewEU2.setImageResource(R.drawable.es);
				break;
			case fi:
				imgViewEU2.setImageResource(R.drawable.fi);
				break;
			case fr:
				imgViewEU2.setImageResource(R.drawable.fr);
				break;
			case ge:
				imgViewEU2.setImageResource(R.drawable.ge);
				break;
			case gr:
				imgViewEU2.setImageResource(R.drawable.gr);
				break;
			case hr:
				imgViewEU2.setImageResource(R.drawable.hr);
				break;
			case hu:
				imgViewEU2.setImageResource(R.drawable.hu);
				break;
			case ie:
				imgViewEU2.setImageResource(R.drawable.ie);
				break;
			case is:
				imgViewEU2.setImageResource(R.drawable.is);
				break;
			case it:
				imgViewEU2.setImageResource(R.drawable.it);
				break;
			case lu:
				imgViewEU2.setImageResource(R.drawable.lu);
				break;
			case lt:
				imgViewEU2.setImageResource(R.drawable.lt);
				break;
			case lv:
				imgViewEU2.setImageResource(R.drawable.lv);
				break;
			case md:
				imgViewEU2.setImageResource(R.drawable.md);
				break;
			case me:
				imgViewEU2.setImageResource(R.drawable.me);
				break;
			case mk:
				imgViewEU2.setImageResource(R.drawable.mk);
				break;
			case mt:
				imgViewEU2.setImageResource(R.drawable.mt);
				break;
			case nl:
				imgViewEU2.setImageResource(R.drawable.nl);
				break;
			case no:
				imgViewEU2.setImageResource(R.drawable.no);
				break;
			case pl:
				imgViewEU2.setImageResource(R.drawable.pl);
				break;
			case pt:
				imgViewEU2.setImageResource(R.drawable.pt);
				break;
			case ro:
				imgViewEU2.setImageResource(R.drawable.ro);
				break;
			case rs:
				imgViewEU2.setImageResource(R.drawable.rs);
				break;
			case ru:
				imgViewEU2.setImageResource(R.drawable.ru);
				break;
			case se:
				imgViewEU2.setImageResource(R.drawable.se);
				break;
			case si:
				imgViewEU2.setImageResource(R.drawable.si);
				break;
			case sk:
				imgViewEU2.setImageResource(R.drawable.sk);
				break;
			case tr:
				imgViewEU2.setImageResource(R.drawable.tr);
				break;
			case ua:
				imgViewEU2.setImageResource(R.drawable.ua);
				break;
			case uk:
				imgViewEU2.setImageResource(R.drawable.uk);
				break;
			}
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			imgViewEU2.setImageResource(R.drawable.eu);
		}
		if (event.getAction() == MotionEvent.ACTION_CANCEL) {
			imgViewEU2.setImageResource(R.drawable.eu);
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case ID_BTN_DIALOG:
			openTSDialog(v);
			break;
		case ID_BTN_SHOW:
			Intent i = new Intent(this, EuCountryActivity.class);
			if (domain == null) {
				Toast toast = Toast
						.makeText(this, toastMsg, Toast.LENGTH_SHORT);
				toast.show();
			} else if (domain.length() == 0) {
				if (bdMsg == null)
					bdMsg = "Error: language selection error!";
				Toast toast = Toast
						.makeText(this, toastMsg, Toast.LENGTH_SHORT);
				toast.show();
			} else {
				i.putExtra("CountriesActivityData", domain + "," + language);
				startActivityForResult(i, REQUEST_CODE);
			}
			break;
		}
	}

	/**
	 * Sets one of the arguments which is hand in as a null (if both are hand in
	 * it will be set to first), these arguments are values of selected country:
	 * 
	 * @param cDomain
	 *            string value of selected country domain or null,
	 * @param cName
	 *            string value of selected country name or null. <br>
	 * <br>
	 * 
	 *            method also sets country name for display in top textview and
	 *            spinner below the picture, and also image to highlight
	 *            selected country on the map.
	 * */
	public void respondOnAction(String cDomain, String cName) {
		String cOffName = "";
		// check if we have argument
		if (cDomain == null && cName == null) {
			Log.d("EuCountriesActivity.java msg:",
					"--->Error: respondOnAction arguments null<---");
			return;
		} else if (cDomain == null) {
			for (int c = 0; c < arrOffNames.size(); c++) {
				if (cName.trim().equals(arrConNames.get(c))) {
					cDomain = arrDomains.get(c);
					cOffName = arrOffNames.get(c);
				}
			}
		} else if (cName == null) {
			for (int c = 0; c < arrDomains.size(); c++) {
				if (cDomain.trim().equals(arrDomains.get(c))) {
					cName = arrConNames.get(c);
					cOffName = arrOffNames.get(c);
				}
			}
		}
		// display image showing position of the country on the map
		imgViewEU2.setImageResource(getResources().getIdentifier(
				"drawable/" + cDomain, "drawable", getPackageName()));
		// Country not found, it is not a EU country, so set appropriate
		// messages for display n text view on the top and in spinner button and
		// reset domain used for displaying details (btnShow)
		if (cName == null) {
			cName = notEUMsg1;
			cOffName = notEUMsg2;
			cDomain = null;
		}
		// display country flag if it is EU country
		if (cDomain != null) {
			drawableOr = getResources().getDrawable(
					getResources().getIdentifier("drawable/" + cDomain + "3",
							"drawable", getPackageName()));
			imgViewEU3.setPadding(
					(int) TypedValue.applyDimension(
							(int) TypedValue.COMPLEX_UNIT_DIP, 75,
							getResources().getDisplayMetrics())
							- (drawableOr.getIntrinsicWidth() / 2), 0, 0, 0);
			imgViewEU3.setImageDrawable(drawableOr);
			imgViewEU3.setVisibility(View.VISIBLE);
		} else
			imgViewEU3.setVisibility(View.INVISIBLE);

		txViewCountryName.setText(cOffName);
		btnDialog.setText(cName);

		domain = cDomain;
		return;
	}

	/**
	 * Returns integer value of the color of touched pixel.
	 * */
	public int getPixelColor(View v, MotionEvent event) {
		int touchColor = 0;

		float[] point_coords = getPointerCoords((ImageView) v, event);
		int origX = (int) point_coords[0];
		int origY = (int) point_coords[1];
		// check to not exit image bounds
		if (origX < 0)
			origX = 0;
		if (origY < 0)
			origY = 0;
		if (origX > mask.getWidth())
			origX = mask.getWidth() - 1;
		if (origY > mask.getHeight())
			origY = mask.getHeight() - 1;
		touchColor = mask.getPixel(origX, origY);

		// ============== DEBUG CODE ============================
		// if (MainActivity.DEBUGGING_FLAG) {
		// txViewCountryName
		// .setText("color is:" + language + ":" + touchColor);
		// txViewCountryName.setBackgroundColor(touchColor);
		// }
		// ======================================================

		return touchColor;
	}

	/**
	 * if we return from opened activity with chosen to go to start screen
	 **/
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE) {
			if (resultCode == VuEuActivity.RESULT_YES) {
				// exit this too
				Intent getBack = new Intent();
				setResult(VuEuActivity.RESULT_YES, getBack);
				onStop();
				finish();
			} else if (resultCode == VuEuActivity.RESULT_NO) {
				// nothing stay here
			}
		}
	}

	/**
	 * Creates custom (transparent) dialog when button is pressed to act like
	 * spinner widget.
	 **/
	public void openTSDialog(View view) {
		showDialog(ID_BTN_DIALOG);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case ID_BTN_DIALOG:
			final Dialog tsDialog = new Dialog(this);
			tsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			// We want this completely transparent because not whole area is
			// clickable only portion that is textview placed into the listview.
			tsDialog.getWindow().setBackgroundDrawable(
					new ColorDrawable(android.graphics.Color.TRANSPARENT));
			ListView counryListView = new ListView(this);
			final StableArrayAdapter adapter = new StableArrayAdapter(this,
					android.R.layout.simple_list_item_1, arrConNames);
			// NOTE: ArrayAdapter can handle data based on Arrays or
			// java.util.List., SimpleCursorAdapter can handle database related
			// data.
			counryListView.setAdapter(adapter);
			counryListView
					.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent,
								final View view, int position, long id) {
							final String item = (String) parent
									.getItemAtPosition(position);
							respondOnAction(null, item);
							tsDialog.dismiss();
						}
					});

			counryListView.scrollBy(3, 3);
			tsDialog.setContentView(counryListView);
			tsDialog.show();
			break;
		}
		return super.onCreateDialog(id);
	}

	/**
	 * Method that return float array (x and y) coordinates of the touched pixel
	 * of the image hand in by following arguments:
	 * 
	 * @param view
	 *            the image on which will be performed event,
	 * @param e
	 *            event returned by listener.
	 * */
	private final float[] getPointerCoords(ImageView view, MotionEvent e) {
		final int index = e.getActionIndex();
		final float[] coords = new float[] { e.getX(index), e.getY(index) };
		Matrix matrix = new Matrix();
		view.getImageMatrix().invert(matrix);
		matrix.postTranslate(view.getScrollX(), view.getScrollY());
		matrix.mapPoints(coords);
		return coords;
	}

	/**
	 * Internal class for a custom adapter for dialog's listView
	 * */
	private class StableArrayAdapter extends ArrayAdapter<String> {
		HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

		public StableArrayAdapter(Context context, int textViewResourceId,
				List<String> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mIdMap.put(objects.get(i), i);
			}
		}

		@Override
		public long getItemId(int position) {
			String item = getItem(position);
			return mIdMap.get(item);
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			TextView tv = (TextView) super.getView(position, convertView,
					parent);
			tv.setLayoutParams(new ListView.LayoutParams(
			// AbsListView.LayoutParams.MATCH_PARENT,
					(int) TypedValue.applyDimension(
							(int) TypedValue.COMPLEX_UNIT_DIP, 300,
							getResources().getDisplayMetrics()),
					AbsListView.LayoutParams.MATCH_PARENT));// WRAP_CONTENT));
			// textview?? is wrap content in listview and this won't cover
			// equally - above doesn't work
			// tv.setWidth(40); // without this gravity doesn't work (if length
			// is not fixed)
			// tv.setBackgroundColor(Color.argb(80, 255, 204, 0)); // 0, 51, 153
			// radius for rounded textview
			float radi = 20;
			// first drawable in the layer of two dravables
			float[] radii = new float[] { radi, radi, radi, radi, radi, radi,
					radi, radi };
			// RectF inset = new RectF(12, 12, 12, 12);
			RoundRectShape rect = new RoundRectShape(radii, null, null);
			// ShapeDrawable bg = new ShapeDrawable(rect);
			CustomShapeDrawable cbg = new CustomShapeDrawable(rect,
					Color.TRANSPARENT, Color.argb(200, 255, 255, 0), true, 2);
			cbg.getPaint().setColor(Color.argb(180, 255, 204, 0));
			// second drawable in the layer
			// RoundRectShape rect2 = new RoundRectShape(radii, null, null);
			ShapeDrawable obg = new ShapeDrawable(rect);
			obg.getPaint().setColor(Color.argb(80, 255, 204, 0));

			Drawable[] layers = new Drawable[2];
			layers[0] = cbg;
			layers[1] = obg;
			LayerDrawable layerDrawable = new LayerDrawable(layers);
			tv.setBackgroundDrawable(layerDrawable);
			// text formating
			tv.setTextSize(25f);
			tv.setTextColor(Color.BLUE);
			tv.setGravity(Gravity.CENTER);
			// add image of the country flag beside text
			String strDomain = arrDomains.get(position) + "3";
			drawableOr = getResources().getDrawable(
					getResources().getIdentifier("drawable/" + strDomain,
							"drawable", getPackageName()));
			tv.setCompoundDrawablesWithIntrinsicBounds(drawableOr, null, null,
					null);
			return tv;
		}

	}

}