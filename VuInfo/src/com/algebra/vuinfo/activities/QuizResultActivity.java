package com.algebra.vuinfo.activities;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.algebra.vuinfo.R;
import com.algebra.vuinfo.database.DbQuiz;
import com.algebra.vuinfo.models.CustomButton;
import com.algebra.vuinfo.models.QuizResult;

public class QuizResultActivity extends Activity implements OnClickListener {
	private Button btnEnd, btnAgain;
	public static final int RESULT_YES = 1;
	public static final int RESULT_NO = -1;
	private static final int ID_BTN_AGAIN = 10;
	private static final int ID_BTN_END = 11;
	private static final int ID_BTN_TXRES = 12;
	private static final int ID_EDTX_IN = 13;
	private static final int ID_TXVIEW_MSG1 = 14;
	private static final int ID_IMG_STARC1 = 222;
	private String language = "";
	private Dialog tsDialog;
	private DbQuiz qDatabase;
	private EditText edtxInput;
	private long timeElapsed;
	private ArrayList<QuizResult> mResults;
	private String name = "";
	private RelativeLayout relativeLayout;
	private RelativeLayout.LayoutParams relativeLayoutParams;
	private TextView txResults;
	private int questions = 0;
	boolean firstRunFlag = false;
	String strMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String msgEnd = "", msgAgain = "", msgTv = "";

		// get received data
		Intent i = getIntent();
		if (i.hasExtra("QuizActivityData")) {
			String strRecv = i.getExtras().getString("QuizActivityData");
			String[] recArray = strRecv.split(",");
			language = recArray[0];
			questions = Integer.parseInt(recArray[1]);
			timeElapsed = Long.parseLong(recArray[2]);

		}
		// set according to language
		if (language.equalsIgnoreCase("en")) {
			this.setTitle("Quiz Results");
			msgTv = "Top Results:";
			msgEnd = "Home";
			msgAgain = "Try again!";
			if (questions > 0)
				strMsg = "You won 12 stars by answering " + questions
						+ " questions in " + getTimeString(timeElapsed)
						+ " minutes.\nEnter your name:";
			else
				strMsg = "12 negative for " + questions + " questions in "
						+ getTimeString(timeElapsed)
						+ " minutes, you are worst of all kids!";
		} else if (language.equalsIgnoreCase("hr")) {
			this.setTitle("Rezultati kviza");
			msgTv = "Najbolji Rezultati:";
			msgEnd = "Pocetna";
			msgAgain = "Igraj ponovo!";
			if (questions > 0)
				strMsg = "Osvojili ste 12 zvjezdica za postavljenih "
						+ questions + " pitanja u "
						+ getTimeString(timeElapsed) + " minuta.\nUnesite ime:";
			else
				strMsg = "12 negativnih za postavljenih " + questions
						+ " pitanja u " + getTimeString(timeElapsed)
						+ " minuta, najgori si od sve dijece!";
		} else
			this.setTitle("Error: Language selection error!");

		this.setTitleColor(VuEuActivity.PANTONE_YELLOW);

		relativeLayout = new RelativeLayout(this);
		// image (pattern) for background
		Bitmap bmp = BitmapFactory.decodeResource(getResources(),
				R.drawable.bkgquiz);
		BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
		bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT,
				Shader.TileMode.REPEAT);
		bitmapDrawable.setAlpha(127);
		relativeLayout.setBackgroundDrawable(bitmapDrawable);

		// Text
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				(int) TypedValue.applyDimension(
						(int) TypedValue.COMPLEX_UNIT_DIP, 320, getResources()
								.getDisplayMetrics()),
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		txResults = new TextView(this);
		txResults.setId(ID_BTN_TXRES);
		txResults.setTextColor(VuEuActivity.PANTONE_YELLOW);
		txResults.setTextSize(25f);
		txResults.setText(msgTv);
		txResults.setShadowLayer(1.5f, 0.5f, 0.5f,
				VuEuActivity.PANTONE_REFLEX_BLUE);
		relativeLayout.addView(txResults, relativeLayoutParams);

		// Buttons
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
		btnEnd = new CustomButton(this);
		btnEnd.setId(ID_BTN_END);
		linearLayoutParams.weight = (float) 0.5; // take 50% of layout space
		linLayChild2.addView(btnEnd, linearLayoutParams);
		btnEnd.setOnClickListener(this);
		btnEnd.setText(msgEnd);
		// 2nd button
		linearLayoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		linearLayoutParams.weight = (float) 0.5;
		btnAgain = new CustomButton(this);
		btnAgain.setId(ID_BTN_AGAIN);
		linLayChild2.addView(btnAgain, linearLayoutParams);
		btnAgain.setOnClickListener(this);
		btnAgain.setText(msgAgain);
		btnEnd.setOnClickListener(this);
		btnAgain.setOnClickListener(this);

		// Set vertical and horizontal scrolling
		ScrollView vScroll = new ScrollView(this);
		HorizontalScrollView hScroll = new HorizontalScrollView(this);
		hScroll.setHorizontalScrollBarEnabled(true);
		hScroll.addView(relativeLayout);
		hScroll.setFillViewport(true);
		vScroll.addView(hScroll);
		vScroll.setFillViewport(true);
		setContentView(vScroll);

		showDialog(1);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.quiz_result, menu);
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#336699")));
		return true;
	}

	@Override
	protected void onStop() {
		// Reset flag
		firstRunFlag = false;
		super.onStop();
	}

	@Override
	public void onClick(View v) {
		Intent getBack = new Intent();
		switch (v.getId()) {
		case ID_BTN_END:
			// INTENT OR RETURN(CLOSE)
			setResult(RESULT_NO, getBack);
			onStop();
			finish();
			break;
		case ID_BTN_AGAIN:
			setResult(RESULT_YES, getBack);
			onStop();
			finish();
			break;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		RelativeLayout relativeLayout;
		RelativeLayout.LayoutParams relativeLayoutParams;
		TextView txViewMsg;

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
		ImageView imgStarC1 = new ImageView(this);
		imgStarC1.setId(ID_IMG_STARC1);
		relativeLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		imgStarC1.setImageResource(R.drawable.europe);
		relativeLayout.addView(imgStarC1, relativeLayoutParams);

		// get user name for storing results
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		edtxInput = new EditText(this);
		edtxInput.setId(ID_EDTX_IN);
		// respond when text is entered
		relativeLayoutParams.addRule(RelativeLayout.BELOW, imgStarC1.getId());
		edtxInput.setBackgroundColor(VuEuActivity.PANTONE_YELLOW);
		edtxInput.setImeOptions(EditorInfo.IME_ACTION_DONE);
		relativeLayout.addView(edtxInput, relativeLayoutParams);
		// text
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		txViewMsg = new TextView(this);
		txViewMsg.setId(ID_TXVIEW_MSG1);
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_TOP,
				edtxInput.getId());
		relativeLayout.addView(txViewMsg, relativeLayoutParams);
		txViewMsg.setText(strMsg);
		txViewMsg.setGravity(Gravity.BOTTOM);
		txViewMsg.setTextColor(VuEuActivity.PANTONE_YELLOW);

		tsDialog.setContentView(relativeLayout);
		tsDialog.show();
		// Exit if enter key is pressed
		View.OnKeyListener onSoftKeyboardDonePress = new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
					// code to hide the soft keyboard
					// EditTexst.clearFocus();
					// EditTexst.requestFocus(EditText.FOCUS_DOWN);
					name = edtxInput.getText().toString();
					addTable();
					tsDialog.dismiss();
				}
				return false;
			}
		};
		edtxInput.setOnKeyListener(onSoftKeyboardDonePress);
		return super.onCreateDialog(id);
	}

	/**
	 * Method returns integer value of place in the list based on sorting by two
	 * parameters, if new value is equal as an old one, it will be placed after
	 * the old value. it is checked 1st parameter and if equal than second too.
	 * 
	 * @param timeElaps
	 *            long integer, elapsed time value in milliseconds.
	 * @param questAnsw
	 *            integer number of answered questions
	 **/
	protected int findPlace(long timeElaps, int questAnsw) {
		int place = 1;
		// loop through the list
		for (int j = 0; j < mResults.size(); j++) {
			long tempTime = mResults.get(j).getQuizTime();
			// check if value is bigger (more time elapsed)
			if (tempTime < timeElaps)
				place++;
			// or equal, then check how many questions are answered
			else if (tempTime == timeElaps) {
				if (mResults.get(j).getAnswersCount() <= questAnsw)
					place++;
				else
					j = mResults.size(); // break
			}
		}
		return place;
	}

	/**
	 * Method returns string time in format h:mm:ss or mm:ss
	 * 
	 * @param time
	 *            long integer, time value in milliseconds.
	 **/
	private String getTimeString(long time) {
		String strTime = "";
		int hours = (int) (time / 3600000);
		int minutes = (int) (time - hours * 3600000) / 60000;
		int seconds = (int) (time - hours * 3600000 - minutes * 60000) / 1000;
		if (hours > 0)
			strTime = hours + ":";
		else
			strTime = "";
		if (minutes < 9)
			strTime = strTime + "0" + minutes + ":";
		else
			strTime = strTime + minutes + ":";
		if (seconds < 9)
			strTime = strTime + "0" + seconds;
		else
			strTime = strTime + seconds;
		return strTime;
	}

	/**
	 * Add table layout to display results
	 **/
	protected void addTable() {
		// Check if flag is set
		if (!firstRunFlag) {
			firstRunFlag = true;
			// Database for results
			qDatabase = new DbQuiz(this, "result");
			// qDatabase.dbQuizHelper.createDataBase();

			// get already stored (old) results - array of QuizResult objects
			mResults = qDatabase.dbQuizHelper.getAllResults();
			// get new result data into QuizResult object
			QuizResult newResult = new QuizResult();
			// check if name is entered
			if (name.length() > 0)
				newResult.setPlayerName(name);
			else
				newResult.setPlayerName("badBoy");
			newResult.setPlace(findPlace(timeElapsed, questions));
			newResult.setQuizTime(timeElapsed);
			newResult.setAnswersCount(questions);

			// Update table - insert old results with new result in correct
			// place, loop from 1st to current place, insert current and loop
			// for the rest, but don't store more than 100 results.
			ArrayList<QuizResult> newResults = new ArrayList<QuizResult>();
			for (int j = 0; j < newResult.getPlace() - 1; j++) {
				newResults.add(mResults.get(j));
			}
			newResults.add(newResult);
			for (int j = newResult.getPlace() + 1; j < mResults.size(); j++) {
				newResults.add(mResults.get(j));
				if (j == 99)
					break;
			}
			// delete old table and make new with fresh data
			qDatabase.dbQuizHelper.deleteTable("result");
			qDatabase.dbQuizHelper.insertResults(newResults);

			// table layout for displaying database data
			relativeLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			relativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
			relativeLayoutParams.addRule(RelativeLayout.BELOW,
					txResults.getId());
			TableLayout tLayout = new TableLayout(this);
			tLayout.setGravity(Gravity.CENTER);
			tLayout.setBackgroundResource(R.drawable.scroll);
			tLayout.setPadding((int) TypedValue.applyDimension(
					(int) TypedValue.COMPLEX_UNIT_DIP, 70, getResources()
							.getDisplayMetrics()), (int) TypedValue
					.applyDimension((int) TypedValue.COMPLEX_UNIT_DIP, 110,
							getResources().getDisplayMetrics()),
					(int) TypedValue.applyDimension(
							(int) TypedValue.COMPLEX_UNIT_DIP, 70,
							getResources().getDisplayMetrics()),
					(int) TypedValue.applyDimension(
							(int) TypedValue.COMPLEX_UNIT_DIP, 110,
							getResources().getDisplayMetrics()));

			TableRow trow = new TableRow(this);
			TextView text1 = new TextView(this);
			TextView text2 = new TextView(this);
			TextView text3 = new TextView(this);
			TextView text4 = new TextView(this);

			text1.setText("Place ");
			trow.addView(text1);
			text2.setText("Name ");
			trow.addView(text2);
			text3.setText("Time ");
			trow.addView(text3);
			text4.setText("Answered");
			trow.addView(text4);
			tLayout.addView(trow);

			// display first 8 places
			mResults = qDatabase.dbQuizHelper.getAllResults();
			for (int j = 0; j < mResults.size() && j < 5; j++) {
				trow = new TableRow(this);
				text1 = new TextView(this);
				text1.setGravity(Gravity.RIGHT);
				text2 = new TextView(this);
				text3 = new TextView(this);
				text4 = new TextView(this);
				text4.setGravity(Gravity.CENTER);

				try {
					text1.setText(mResults.get(j).getPlace() + ". ");
					trow.addView(text1);
					text2.setText(mResults.get(j).getPlayerName() + ", ");
					trow.addView(text2);
					text3.setText(getTimeString(mResults.get(j).getQuizTime())
							+ ", ");
					trow.addView(text3);
					text4.setText(mResults.get(j).getAnswersCount() + " ");
					trow.addView(text4);
					tLayout.addView(trow);
				} catch (Exception e) {
				}
			}

			// display current result if above 8 place
			if (newResult.getPlace() > 7) {
				if (newResult.getPlace() > 8) {
					trow = new TableRow(this);
					text1 = new TextView(this);
					text2 = new TextView(this);
					text3 = new TextView(this);
					text4 = new TextView(this);
					text1.setText(".....");
					trow.addView(text1);
					text2.setText(".....");
					trow.addView(text2);
					text3.setText(".....");
					trow.addView(text3);
					text4.setText(".....");
					trow.addView(text4);
					tLayout.addView(trow);
				}
				trow = new TableRow(this);
				text1 = new TextView(this);
				text2 = new TextView(this);
				text3 = new TextView(this);
				text4 = new TextView(this);
				text1.setText(newResult.getPlace() + ". ");
				trow.addView(text1);
				text2.setText(newResult.getPlayerName() + ", ");
				trow.addView(text2);
				text3.setText(getTimeString(newResult.getQuizTime()) + ", ");
				trow.addView(text3);
				text4.setText(newResult.getAnswersCount() + " ");
				trow.addView(text4);
				tLayout.addView(trow);
			}
			relativeLayout.addView(tLayout, relativeLayoutParams);
		}
	}
}
