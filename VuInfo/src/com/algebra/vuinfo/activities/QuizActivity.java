package com.algebra.vuinfo.activities;

import java.io.IOException;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.algebra.vuinfo.R;
import com.algebra.vuinfo.database.DbQuiz;
import com.algebra.vuinfo.models.CustomButton;
import com.algebra.vuinfo.models.Question;

public class QuizActivity extends Activity implements OnClickListener {

	private String btnYesMsg, btnNoMsg, btnExitMsg, btnConfMsg, btnNextMsg;

	private String language = "hr";// Default language
	public Typeface fontTypeFace;
	private TextView[] textView;
	private Button btnExit, btnConf;
	private Button btnNext;
	private ImageView[] imgStar = new ImageView[12];
	private ImageView[] imgStarPlus = new ImageView[12];
	private LinearLayout ll;

	public static final int REQUEST_CODE = 0;

	private static final int ID_RADIO_GROUP = 39;
	private static final int ID_BTN_YES = 40;
	private static final int ID_BTN_NO = 41;
	private static final int ID_TXVIEW_MSG1 = 42;
	private static final int ID_TXVIEW_MSG2 = 43;
	private static final int ID_IMG_STARC1 = 44;
	private static final int ID_IMG_STARC2 = 45;
	private static final int ID_LL = 46;
	private static final int ID_BTN_EXIT = 47;
	private static final int ID_BTN_CONF = 48;
	private static final int ID_BTN_NEXT = 49;
	private static final int ID_LL_RLCHILD = 50;

	// see for this (maybe array list instead)
	public static int[] intQuestionNo = new int[100];
	private static int[] intQuestions = new int[3];
	private static int intAnsweredQuestCount;
	private int intPoints, intCheckedRBtn, intCorrAnsw, idBtnEnable;
	private int answeredFlag = 0;

	private RadioGroup radioGroup;
	private final RadioButton[] radioButtonArr = new RadioButton[3];
	private RadioButton radioButton;

	private Dialog tsDialog;
	private DbQuiz qDatabase;
	private Question mQuestion;

	private RelativeLayout.LayoutParams relativeLayoutParams;
	private RelativeLayout relativeLayout;
	private LinearLayout.LayoutParams linearLayoutParams;

	private Chronometer mChronometer;
	private long mLastClickTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Get chosen language.
		Intent intent = getIntent();
		if (intent.hasExtra("MainActivityData"))
			language = intent.getExtras().getString("MainActivityData");

		// Set widgets text according to chosen language.
		if (language.equalsIgnoreCase("en")) {
			this.setTitle("Quiz");
			btnYesMsg = "Try again";
			btnNoMsg = "Next question";
			btnExitMsg = "Quit";
			btnConfMsg = "Confirm";
			btnNextMsg = "Next";
		} else if (language.equalsIgnoreCase("hr")) {
			this.setTitle("Kviz");
			btnYesMsg = "Pokusaj ponovo";
			btnNoMsg = "Slijedece pitanje";
			btnExitMsg = "Odustani";
			btnConfMsg = "Potvrdi";
			btnNextMsg = "Dalje";
		} else
			this.setTitle("Error: Language selection error!");

		this.setTitleColor(VuEuActivity.PANTONE_YELLOW);
		// Copy database file from assets if it doesn't exist.
		try {
			qDatabase = new DbQuiz(this, language);
			qDatabase.dbQuizHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error(
					"Error: Unable to create database: method createDataBase caused IOException");
		}

		// Custom fonts
		fontTypeFace = Typeface.createFromAsset(getAssets(),
				"fonts/PatrickHand-Regular.ttf");

		relativeLayout = new RelativeLayout(this);
		// image (pattern) for background
		Bitmap bmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.bkgquiz);
		BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
		bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT,
				Shader.TileMode.REPEAT);
		bitmapDrawable.setAlpha(40);
		relativeLayout.setBackgroundDrawable(bitmapDrawable);

		// Layout for displaying stars (current result)
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayoutParams.addRule(Gravity.CENTER);
		ll = new LinearLayout(this);
		ll.setId(ID_LL);
		ll.setOrientation(LinearLayout.HORIZONTAL);
		relativeLayout.addView(ll, relativeLayoutParams);
		// Images of stars for displaying current result
		displayStars(0);

		// za sada samo hr baza(tablica) za kviz postoji
		qDatabase = new DbQuiz(this, language);
		mQuestion = new Question();
		// Fill array with random numbers from 1 to how many row in database
		int rowsCount = qDatabase.dbQuizHelper.getRowCount();
		intQuestionNo = getRandom(rowsCount, 1, rowsCount);

		addToLayout();

		addListenerOnRadioButtons();

		addButtons();
		btnNext.setOnClickListener(this);
		btnExit.setOnClickListener(this);
		btnConf.setOnClickListener(this);

		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);// WRAP_CONTENT);
		// relativeLayoutParams.addRule(Gravity.CENTER);
		mChronometer = new Chronometer(this);
		// mChronometer.setId();
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		relativeLayout.addView(mChronometer, relativeLayoutParams);
		// mChronometer.setPadding(80, 0, 0, 0);
		mChronometer.setTextSize(24f);
		mChronometer.setTextColor(VuEuActivity.PANTONE_REFLEX_BLUE);
		mChronometer.start();

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
		getMenuInflater().inflate(R.menu.quiz, menu);
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#336699")));
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		// If we run out of question
		if (intAnsweredQuestCount + 1 > 59) {
			Intent h = new Intent(this, QuizResultActivity.class);
			mChronometer.stop();
			long time = getElapsedTime();
			h.putExtra("QuizActivityData", language + ","
					+ (intAnsweredQuestCount + 1) + "," + time);
			intAnsweredQuestCount = 0;
			startActivityForResult(h, REQUEST_CODE);
		}

		// uncheck radio buttons
		((RadioGroup) findViewById(ID_RADIO_GROUP)).clearCheck();

		// Don't get new questions if we want to try to answer again
		if (answeredFlag == 0)
			intQuestions = getRandom(3, 1, 3);

		// Display which question # is this
		textView[0].setText(String.valueOf(intAnsweredQuestCount + 1) + ": ");

		// Check if we have enough stars (12 positive or negative) to finish
		if (intPoints > 11) {
			Intent h = new Intent(this, QuizResultActivity.class);
			mChronometer.stop();
			long time = getElapsedTime();
			h.putExtra("QuizActivityData", language + ","
					+ (intAnsweredQuestCount + 1) + "," + time);
			startActivityForResult(h, REQUEST_CODE);
		}
		if (intPoints < -11) {
			Intent h = new Intent(this, QuizResultActivity.class);
			mChronometer.stop();
			long time = getElapsedTime();
			h.putExtra("QuizActivityData", language + ","
					+ -(intAnsweredQuestCount + 1) + "," + time);
			startActivityForResult(h, REQUEST_CODE);
		}

		displayStars(intPoints);
		int currentQ = intQuestionNo[intAnsweredQuestCount];
		mQuestion = qDatabase.dbQuizHelper.getQuestionById(currentQ);
		if (intQuestions[0] == 1) {
			intCorrAnsw = 1;
		} else if (intQuestions[1] == 1) {
			intCorrAnsw = 2;
		} else if (intQuestions[2] == 1) {
			intCorrAnsw = 3;
		}

		// index starts from 0 while columns starts from 1, so ...
		int x, xx, xxx;
		if (intQuestions[0] == 0)
			x = 0;
		else
			x = intQuestions[0] - 1;
		if (intQuestions[1] == 0)
			xx = 0;
		else
			xx = intQuestions[1] - 1;
		if (intQuestions[2] == 0)
			xxx = 0;
		else
			xxx = intQuestions[2] - 1;
		// Get answers 1,2,3 into string array to easier fill A,B,C in a random
		// order
		String[] strArrAnswABC = { mQuestion.getQuestionAnswCorr(),
				mQuestion.getQuestionAnswInc1(),
				mQuestion.getQuestionAnswInc2() };
		String[] quest = { mQuestion.getQuestion(), strArrAnswABC[x],
				strArrAnswABC[xx], strArrAnswABC[xxx] };
		for (int i = 1, j = 0; i < 8; i = i + 2) {
			textView[i].setText(quest[j]);
			textView[i].setTextColor(Color.rgb(255, 75, 00));
			j++;
		}
	}

	// nije jos nista radjeno
	@Override
	protected void onPause() {
		super.onPause();

		// Store values between instances
		SharedPreferences preferences = getPreferences(MODE_PRIVATE);

		// Put the values from the UI
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt("qNo1", intQuestionNo[0]); // value to store
		// editor.putBoolean(“TandC”, blnTandC); // value to store

		// Commit to storage
		editor.commit();
	}

	@Override
	public void onClick(View v) {

		// mis-clicking prevention (button debouncing), using threshold of 1000
		// ms
		if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
			return;
		}
		mLastClickTime = SystemClock.elapsedRealtime();

		// End quiz chosen, go to main screen
		if (v.getId() == ID_BTN_EXIT) {
			onStop();
			finish();
		}
		// Next choosen: 0 points, answered question++
		if (v.getId() == ID_BTN_NEXT) {
			intAnsweredQuestCount++;
			// Populate with a new question
			onResume();
		}

		if (v.getId() == ID_BTN_CONF) {
			// Check first is it radio button chosen...
			if (intCheckedRBtn != 0) {
				// is it answer first time confirmed, if yes ...
				if (answeredFlag == 0) {
					// ... check is it answer correct, if yes add 2 points,
					// question++, and display thumb up
					if (intCorrAnsw == intCheckedRBtn) {
						intPoints = intPoints + 2;
						intAnsweredQuestCount++;
						openTSDialog(v, 1);
						intCheckedRBtn = 0;
						onResume();
						// intQuestions = getRandom(3, 1, 3);
					} else {
						// answer not correct, so
						// sets flag ...
						answeredFlag = 1;
						// ... open dialog with options
						openTSDialog(v, 2);
					}
				} else {
					// second try, so reset flag, check answer
					answeredFlag = 0;
					if (intCorrAnsw == intCheckedRBtn) {
						intAnsweredQuestCount++;
						intPoints = intPoints + 1;
						openTSDialog(v, 3);
						radioButton = (RadioButton) findViewById(idBtnEnable);
						radioButton.setEnabled(true);
						btnNext.setEnabled(true);
						intCheckedRBtn = 0;
						onResume();
					} else {
						intAnsweredQuestCount++;
						intPoints = intPoints - 2;
						intCheckedRBtn = 0;
						openTSDialog(v, 4);
						radioButton = (RadioButton) findViewById(idBtnEnable);
						radioButton.setEnabled(true);
						btnNext.setEnabled(true);
						onResume();
					}
				}
			} else {
				openTSDialog(v, 5);
			}
		}

		switch (v.getId()) {
		// Try again
		case ID_BTN_YES:
			// clears/reset checked button and continue
			idBtnEnable = radioGroup.getCheckedRadioButtonId();
			radioButton = (RadioButton) findViewById(idBtnEnable);
			radioButton.setEnabled(false);
			radioButton.setChecked(false);
			intCheckedRBtn = 0;
			btnNext.setEnabled(false);
			tsDialog.dismiss();
			break;
		case ID_BTN_NO:
			// flag stays 0, -1 point, and next question
			intPoints = intPoints - 1;
			intAnsweredQuestCount++;
			answeredFlag = 0;
			intCheckedRBtn = 0;
			tsDialog.dismiss();
			onResume();
		}
	}

	/**
	 * Test if integer array contains value and returns true/false.
	 * 
	 * @param array
	 *            of an integer to test
	 * @param key
	 *            integer to test
	 * */
	public boolean contains(final int[] array, final int key) {
		for (final int i : array) {
			if (i == key) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns integer array of a random numbers with unique values within a
	 * range.
	 * 
	 * @param length
	 *            of an integer array to return
	 * @param from
	 *            smallest integer in a range
	 * @param to
	 *            largest integer in a range
	 * */
	public int[] getRandom(final int length, final int from, final int to) {
		int[] intTemp = new int[length];
		int[] intTempWorkSet = new int[length * 10];
		// get random # in a range 10 times more than a length of
		// returned array, hopefully to get all needed unique values
		// for the range.
		for (int i = 0; i < intTempWorkSet.length; i++) {
			intTempWorkSet[i] = from + (int) (Math.random() * to);
		}
		// load first number and from there check to not populate with any
		// duplicate number
		intTemp[0] = intTempWorkSet[0];
		for (int i = 1, j = 1; j < intTemp.length; i++) {
			// so check if new number is already in, and if it is not, place it
			boolean ex = contains(intTemp, intTempWorkSet[i]);
			if (!ex) {
				intTemp[j] = intTempWorkSet[i];
				j++;
			}
			// check if we run out of work set of temporary random numbers
			// if yes re-populate and reset a counter
			if (i == intTempWorkSet.length - 1) {
				for (int a = 0; a < intTempWorkSet.length; a++)
					intTempWorkSet[a] = from + (int) (Math.random() * to);
				i = 0;
			}
		}
		return intTemp;
	}

	/**
	 * Shows transparent dialog with appropriate content.
	 * 
	 * @param view
	 *            or context hand in to from where dialog is open
	 * @param id
	 *            of dialog to open, integer value 1 to 5
	 * */
	public void openTSDialog(View view, int id) {
		showDialog(id);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		RelativeLayout relativeLayout;
		// RelativeLayout.LayoutParams relativeLayoutParams;
		TextView txViewMsg;
		String strMsg;
		if (language.equalsIgnoreCase("hr"))
			strMsg = "Dali zelite probati ponovo?";
		else
			strMsg = "Do you want to try again?";
		int intResId = R.drawable.star_thumb_down;
		// Check what (which dialog) message to display.
		if (id == 1) {
			id = 6;
			if (language.equalsIgnoreCase("hr"))
				strMsg = "Cestitamo, osvojili ste 2 zvjezdice!!";
			else
				strMsg = "Congratulation, you won 2 stars!!";
			intResId = R.drawable.star_thumbs_up;
		} else if (id == 3) {
			id = 6;
			if (language.equalsIgnoreCase("hr"))
				strMsg = "Cestitamo, osvojili ste 1 zvjezdicu!";
			else
				strMsg = "Congratulation, you won 1 stars!";
			intResId = R.drawable.star_thumb_up;
		} else if (id == 4) {
			id = 6;
			if (language.equalsIgnoreCase("hr"))
				strMsg = "Pogresan odgovor";
			else
				strMsg = "Wrong answer";
			intResId = R.drawable.star_thumbs_down;
		} else if (id == 5) {
			id = 6;
			if (language.equalsIgnoreCase("hr"))
				strMsg = "Dodaberite jedno dugme";
			else
				strMsg = "Choose one button";
			intResId = R.drawable.star_white_256;
		}

		switch (id) {
		case 6:
			tsDialog = new Dialog(this);
			tsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			tsDialog.getWindow().setBackgroundDrawable(
					new ColorDrawable(android.graphics.Color.TRANSPARENT));
			// layout
			relativeLayout = new RelativeLayout(this);
			// image
			relativeLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			ImageView imgStarC1 = new ImageView(this);
			imgStarC1.setId(ID_IMG_STARC1);
			relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			relativeLayout.addView(imgStarC1, relativeLayoutParams);
			imgStarC1.setImageResource(intResId);
			// text
			relativeLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			txViewMsg = new TextView(this);
			txViewMsg.setId(ID_TXVIEW_MSG1);
			relativeLayoutParams.addRule(RelativeLayout.BELOW,
					imgStarC1.getId());
			relativeLayout.addView(txViewMsg, relativeLayoutParams);
			txViewMsg.setText(strMsg);
			txViewMsg.setTextColor(VuEuActivity.PANTONE_YELLOW);
			tsDialog.setContentView(relativeLayout);
			tsDialog.show();
			// show dialog for 3 seconds and close
			new CountDownTimer(3000, 1000) {
				@Override
				public void onTick(long millisUntilFinished) {
					// TODO Auto-generated method stub
				}

				@Override
				public void onFinish() {
					tsDialog.dismiss();
				}
			}.start();
			break;
		case 2:
			tsDialog = new Dialog(this);
			tsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			tsDialog.getWindow().setBackgroundDrawable(
					new ColorDrawable(android.graphics.Color.TRANSPARENT));
			tsDialog.setCanceledOnTouchOutside(false);

			// layout
			relativeLayout = new RelativeLayout(this);
			// image
			relativeLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			ImageView imgStarC2 = new ImageView(this);
			imgStarC2.setId(ID_IMG_STARC2);
			relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
			relativeLayout.addView(imgStarC2, relativeLayoutParams);
			imgStarC2.setImageResource(intResId);
			// text
			relativeLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			txViewMsg = new TextView(this);
			txViewMsg.setId(ID_TXVIEW_MSG2);
			relativeLayoutParams.addRule(RelativeLayout.BELOW,
					imgStarC2.getId());
			relativeLayout.addView(txViewMsg, relativeLayoutParams);
			txViewMsg.setText(strMsg);
			txViewMsg.setTextColor(VuEuActivity.PANTONE_YELLOW);
			// buttons
			relativeLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			final Button btnYes = new Button(this);
			btnYes.setId(ID_BTN_YES);
			relativeLayoutParams.addRule(RelativeLayout.BELOW,
					txViewMsg.getId());
			relativeLayout.addView(btnYes, relativeLayoutParams);
			btnYes.setText(btnYesMsg);
			btnYes.setTextColor(VuEuActivity.PANTONE_YELLOW);
			btnYes.setOnClickListener(this);
			relativeLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			final Button btnNo = new Button(this);
			btnNo.setId(ID_BTN_NO);
			relativeLayoutParams.addRule(RelativeLayout.ALIGN_TOP,
					btnYes.getId());
			relativeLayoutParams.addRule(RelativeLayout.RIGHT_OF,
					btnYes.getId());
			relativeLayout.addView(btnNo, relativeLayoutParams);
			btnNo.setText(btnNoMsg);
			btnNo.setTextColor(VuEuActivity.PANTONE_YELLOW);
			btnNo.setOnClickListener(this);
			tsDialog.setContentView(relativeLayout);
			tsDialog.show();
			break;
		}
		return super.onCreateDialog(id);
	}

	/**
	 * Add radio group with 3 radio buttons (IDs start from 20) to the layout.
	 * Also, add listener that sets variable "checkedRBtn" to the integer value
	 * 1, 2, or 3, depending on selected button of the radio group.
	 * */
	public void addListenerOnRadioButtons() {
		radioGroup = new RadioGroup(this);
		radioGroup.setId(ID_RADIO_GROUP);
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayoutParams.addRule(Gravity.CENTER);
		// put below questions (NOTE: only first text view in a row have ID)
		relativeLayoutParams.addRule(RelativeLayout.BELOW, textView[6].getId());
		relativeLayout.addView(radioGroup, relativeLayoutParams);
		radioGroup.setOrientation(RadioGroup.HORIZONTAL);
		// add left margin and smaller bottom margin to separate from other
		// buttons
		int px = (int) TypedValue.applyDimension(
				(int) TypedValue.COMPLEX_UNIT_DIP, 80, getResources()
						.getDisplayMetrics());
		relativeLayoutParams.setMargins(px, 0, 0, px / 4);
		radioGroup.setLayoutParams(relativeLayoutParams);
		// add buttons
		for (int i = 0; i < radioButtonArr.length; i++) {
			radioButtonArr[i] = new RadioButton(this);
			radioButtonArr[i].setId(20 + i);
			radioButtonArr[i].setTextColor(VuEuActivity.PANTONE_REFLEX_BLUE);
			radioButtonArr[i].setTypeface(Typeface.DEFAULT, Typeface.BOLD);
			radioButtonArr[i].setPadding(radioButtonArr[i].getPaddingLeft(),
					radioButtonArr[i].getPaddingTop(),
					radioButtonArr[i].getPaddingRight() + 35,
					radioButtonArr[i].getPaddingBottom());
			radioGroup.addView(radioButtonArr[i]);
		}
		radioButtonArr[0].setText("A");
		radioButtonArr[1].setText("B");
		radioButtonArr[2].setText("C");
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// get selected radio button from radioGroup
				int selectedId = radioGroup.getCheckedRadioButtonId();
				// find the radiobutton by returned id

				radioButton = (RadioButton) findViewById(selectedId);
				// check if not null because clearCheck sets this to null (it
				// run method twice i think)
				if (radioButton != null) {
					String s = (String) radioButton.getText();
					if (s.equals("A"))
						intCheckedRBtn = 1;
					else if (s.equals("B"))
						intCheckedRBtn = 2;
					else if (s.equals("C"))
						intCheckedRBtn = 3;
				}
			}
		});
	}

	/**
	 * Method that will display stars based on hand in parameter
	 * 
	 * @param result
	 *            integer value, range that will be displayed is from -12 to 12,
	 *            if over range, it will display these values.
	 **/
	private void displayStars(int result) {
		if (result > 12)
			result = 12;
		if (result < -12)
			result = -12;
		// 0 points
		if (result == 0) {
			for (int i = 0; i < imgStar.length; i++) {
				imgStar[i] = new ImageView(this);
				imgStar[i].setImageResource(R.drawable.star_black_256);
				imgStar[i].setAlpha(80);
			}
			for (int i = 0; i < imgStarPlus.length; i++) {
				imgStarPlus[i] = new ImageView(this);
				imgStarPlus[i].setImageResource(R.drawable.star_black_256);
				imgStarPlus[i].setAlpha(80);
			}
			// negative
		} else if (result < 0) {
			for (int i = 0; i < imgStar.length + result; i++) {
				imgStar[i] = new ImageView(this);
				imgStar[i].setImageResource(R.drawable.star_black_256);
				imgStar[i].setAlpha(80);
			}
			for (int i = imgStar.length + result; i < imgStar.length; i++) {
				imgStar[i] = new ImageView(this);
				imgStar[i].setImageResource(R.drawable.star_blue_256);
			}
			for (int i = 0; i < imgStarPlus.length; i++) {
				imgStarPlus[i] = new ImageView(this);
				imgStarPlus[i].setImageResource(R.drawable.star_black_256);
				imgStarPlus[i].setAlpha(80);
			}
			// positive
		} else {
			for (int i = 0; i < imgStar.length; i++) {
				imgStar[i] = new ImageView(this);
				imgStar[i].setImageResource(R.drawable.star_black_256);
				imgStar[i].setAlpha(80);
			}
			for (int i = 0; i < result; i++) {
				imgStarPlus[i] = new ImageView(this);
				imgStarPlus[i].setImageResource(R.drawable.star_gold_256);
			}
			for (int i = result + 1; i < imgStarPlus.length; i++) {
				imgStarPlus[i] = new ImageView(this);
				imgStarPlus[i].setImageResource(R.drawable.star_black_256);
				imgStarPlus[i].setAlpha(80);
			}
		}

		// Parameters for images of stars inside linear layout
		linearLayoutParams = new LinearLayout.LayoutParams(
				(int) TypedValue.applyDimension(
						(int) TypedValue.COMPLEX_UNIT_DIP, 14, getResources()
								.getDisplayMetrics()),
				(int) TypedValue.applyDimension(
						(int) TypedValue.COMPLEX_UNIT_DIP, 14, getResources()
								.getDisplayMetrics()));
		ll.removeAllViews();
		for (int i = 0; i < imgStar.length; i++) {
			ll.addView(imgStar[i], linearLayoutParams);
		}
		TextView tx = new TextView(this);
		tx.setText("-|+");
		tx.setTextColor(VuEuActivity.PANTONE_REFLEX_BLUE);
		tx.setTextSize(14);
		ll.setPadding(0, 24, 0, 12);
		ll.addView(tx);
		for (int i = 0; i < imgStarPlus.length; i++) {
			ll.addView(imgStarPlus[i], linearLayoutParams);
		}
	}

	/**
	 * Add TextViews to layout, total 8 TextViews added with IDs from 1 to 8
	 * */
	void addToLayout() {
		int rowNo = 4;
		String temp = String.valueOf(intAnsweredQuestCount + 1) + ": ";
		String[] rowName = { temp, "a) ", "b) ", "c) " };
		String[] row = new String[rowNo];
		textView = new TextView[row.length * 2];

		int i = 0;// counter for textView array
		for (int j = 0; j < row.length; j++) {
			// 1st TextView in a row
			textView[i] = new TextView(this);
			relativeLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			// set view identifier for use for alignment. The identifier does
			// not have to be unique in this view's hierarchy, should be a
			// positive number(not 0)
			textView[i].setId(i + 1);
			textView[i].setTextColor(VuEuActivity.PANTONE_REFLEX_BLUE);
			textView[i].setTypeface(fontTypeFace);
			textView[i].setText(rowName[j]);
			textView[i].setTextSize(20);
			textView[i].setSingleLine(false);
			if (i == 0)
				textView[i].setLines(6);
			else
				textView[i].setLines(2);
			int px = (int) TypedValue.applyDimension(
					(int) TypedValue.COMPLEX_UNIT_DIP, 15, getResources()
							.getDisplayMetrics());
			relativeLayoutParams.setMargins(px, 0, 0, 0);

			int id;
			if (i != 0)
				id = textView[i - 2].getId();
			else
				id = ID_LL; // if first set below result(stars) display

			relativeLayoutParams.addRule(RelativeLayout.BELOW, id);
			relativeLayout.addView(textView[i], relativeLayoutParams);
			// 2nd TextView in a row
			i++;
			textView[i] = new TextView(this);
			relativeLayoutParams = new RelativeLayout.LayoutParams(
					(int) TypedValue.applyDimension(
							(int) TypedValue.COMPLEX_UNIT_DIP, 280,
							getResources().getDisplayMetrics()),
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			textView[i].setText(row[j]);
			relativeLayoutParams.addRule(RelativeLayout.RIGHT_OF,
					textView[i - 1].getId());
			relativeLayoutParams.addRule(RelativeLayout.ALIGN_TOP,
					textView[i - 1].getId());
			textView[i].setTextSize(20);
			textView[i].setTextColor(Color.rgb(255, 75, 00));
			textView[i].setTypeface(fontTypeFace);
			relativeLayout.addView(textView[i], relativeLayoutParams);
			i++;
		}
	}

	/**
	 * Add 3 buttons
	 **/
	public void addButtons() {
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				(int) TypedValue.applyDimension(
						(int) TypedValue.COMPLEX_UNIT_DIP, 320, getResources()
								.getDisplayMetrics()),
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout linLayRLChild = new LinearLayout(this);
		linLayRLChild.setId(ID_LL_RLCHILD);
		// place buttons in a row
		linLayRLChild.setOrientation(LinearLayout.HORIZONTAL);
		relativeLayoutParams.addRule(RelativeLayout.BELOW, radioGroup.getId());
		relativeLayout.addView(linLayRLChild, relativeLayoutParams);
		// Left button
		linearLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		btnExit = new CustomButton(this);
		btnExit.setId(ID_BTN_EXIT);
		// take 10% or more(depends on text) layout space
		linearLayoutParams.weight = (float) 0.1;
		linLayRLChild.addView(btnExit, linearLayoutParams);
		btnExit.setOnClickListener(this);
		btnExit.setText(btnExitMsg);
		btnExit.setTextColor(VuEuActivity.PANTONE_REFLEX_BLUE);

		// Center button
		linearLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		btnConf = new CustomButton(this);
		btnConf.setId(ID_BTN_CONF);
		linearLayoutParams.weight = (float) 0.60;
		linLayRLChild.addView(btnConf, linearLayoutParams);
		btnConf.setOnClickListener(this);
		btnConf.setText(btnConfMsg);
		btnConf.setTypeface(btnConf.getTypeface(), Typeface.BOLD);
		btnConf.setTextColor(Color.rgb(255, 75, 00));
		btnConf.getBackground().setColorFilter(
				new LightingColorFilter(0xFFFFFFFF, 0x000000AA));

		// Right button
		linearLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		btnNext = new CustomButton(this);
		btnNext.setId(ID_BTN_NEXT);
		linearLayoutParams.weight = (float) 0.3;
		linLayRLChild.addView(btnNext, linearLayoutParams);
		btnNext.setOnClickListener(this);
		btnNext.setText(btnNextMsg);
		btnNext.setTextColor(VuEuActivity.PANTONE_REFLEX_BLUE);

	}

	private long getElapsedTime() {
		// String chronoText = mChronometer.getText().toString();
		// return chronoText;
		long chronoTime = SystemClock.elapsedRealtime()
				- mChronometer.getBase();
		return chronoTime;

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE) {
			if (resultCode == QuizResultActivity.RESULT_YES) {
				// reset result, timer, questions order
				intPoints = 0;
				intAnsweredQuestCount = 0;
				Bundle savedInstanceState = new Bundle();
				onCreate(savedInstanceState);
			} else if (resultCode == QuizResultActivity.RESULT_NO) {
				onStop();
				finish();
			}
		}
	}
}
