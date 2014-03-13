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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.algebra.vuinfo.R;
import com.algebra.vuinfo.database.Database;
import com.algebra.vuinfo.database.DbQuiz;

public class AboutActivity extends Activity {

	public static final int ID_TX_QUIZ = 1;
	public static final int ID_BTN_DBEDIT = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		ImageView imgStars2, imgBkg;

		String language = "en", titlMsg = "", strQuiz = "", strMainInfo = "";
		// Get chosen language.
		Intent intent = getIntent();
		if (intent.hasExtra("MainActivityData"))
			language = intent.getExtras().getString("MainActivityData");

		if (language.equalsIgnoreCase("en")) {
			titlMsg = "About";
			strQuiz = "How to play QUIZ: \n\tThe aim is to colect 12 stars (points), to collect them by "
					+ "answering less question as possible, in as shortest time in a order to get best result."
					+ "\nSo, select correct answer :) and \"confim\" it! If you don't know the answer, you may"
					+ " check later in Info part of an aplication, just go \"Next\" question and you will get"
					+ " no negative stars for this, just a bigger number of a question count and less for answering!"
					+ " In a case if you don't collect enought of positiv (or negative stars) before, there are"
					+ " total of 60 questions before quiz ends! You aso may quit quiz any time by Quit button."
					+ " For the correct answer you will get 2 stars! if you answer wrong you may choose to get one "
					+ "star minus, or to try again! if you choose to try again and you answer wrong second time you"
					+ " will get 2 stars minus, otherwise you will get 1 star plus."
					+ " If you collect 12 negative stars you are worst of all kids :( ;) :) and quiz will end, but"
					+ " try to colect 12 positive and you may win!"
					+ " first place!\n";

			strMainInfo = "\nIn general: \n\tThis application is made as part of IPA project \"Razvoj lokalnih kapaciteta za mobilno"
					+ " poslovanje (e-MOBIL)\", as a part of programming course for Android applications, and"
					+ " motivated by recent accession of Republic of Croatia into European Union. \nTo be better"
					+ " iformed and know our new national members and thay know us better, application offers some"
					+ " basic, same as some of an imortant, facts about Europian Union as a comunity and about its"
					+ " members alone. An application currently holds only basic data, but can be easly upgaded with"
					+ " all important data. \nIn an Info part of the application by easy navigation over the map or"
					+ " drop menu, you can access to each of 28 countries members of European Union comunty, to view"
					+ " basic facts about chosen country. This par can also be easly upgraded for countries that are"
					+ " curently not enfold or for more data. \nYou also may check, supplement or accquire youre"
					+ " knowledge in a payfull and more fun part of an application by playing offered Quiz, this part"
					+ " also can be easly upgraded with any question for example targeted to specified age or data.\n\n";

		} else if (language.equalsIgnoreCase("hr")) {
			titlMsg = "Opcenito";
			strMainInfo = "\nOva aplikacija je napravljena je u sklopu IPA projekta Razvoj lokalnih kapaciteta za mobilno"
					+ " poslovanje (e-MOBIL), to jest kursa za razvoj Android mobilnih aplikacija, a povodom nedavnog"
					+ " ulaska Republike Hrvatske u Europsku Uniju. \nDa bi se bolje upoznali sa našim novim sugradjanima,"
					+ " kao i oni sa nama, aplikacija Vam nudi osnovne fakte kao i osnovne važne cinjenice o Europskoj"
					+ " Uniji kao zajednici, a isto tako i za svaku državu clanicu ponaosob. Iako aplikacija trenutno"
					+ " sadrži smo neke osnovne informacije lako se može nadograditi sa svim potrebnim podatcima. "
					+ "\nU „Info“ dijelu aplikacije lakom i zanimljivo izvedenom navigacijom ,preko karte ili izbornika,"
					+ " možete brzo pristupiti do svake od 28 zemalja clanica i dobiti osnovne cinjenice o izabranoj"
					+ " državi. A aplikacije se može lako nadograditi i za ostale zemlje koje nisu clanice te nisu"
					+ " trenutno obuhvacene, te dodatne podatke. \nA steceno znanje možete na zabavan nacin isto tako"
					+ " provjeriti i dopuniti, ili stjeci u dijelu aplikacije „Kviz““, koji isto tako moze biti lako"
					+ " podesen za pitanja naprimer ciljana na odredjeni uzrast ili podatke.\n\n";

			strQuiz = "Kako igrati KVIZ: \n\tcilj je sakupiti 12 zvijezdica (poena), sakupiti ih "
					+ "sa sto manje moguce postavljenih pitanja, u sto kracem vremenu, da bi se osvoio sto bolji rezultat."
					+ "\nDakle, odaberite tocan odgovor :) i \"potvrdite\" ga! Ako ne znate odgovor, mozete"
					+ " poslije provjeriti u Info dijelu aplikacije, jednostavno ga prskocite na \"Dalje\" gumb i necete"
					+ " dobiti negativnih poena, jedino vise koristenih pitanja i manje pitanja za odgovaranje!"
					+ " U slucaju da niste sakupili dovoljno pozitivnih (ili negativnih) zvjezdica, kviz"
					+ " ima 60 pitanja za odgovaranje, nakon toga ce zavrsiti! Takodje mozete i sami zavrsiti kviz na"
					+ " gumb Odustani. Za tocno odgovoreno pitanje cete dobiti 2 zvijezdice! Ako odgovorite pogresno biti"
					+ " cete upitani dali zelite 1 negativnu zvijezdicu ili da pokusate odgovoriti ponovo! Ako ste odabrali"
					+ " da pokusate ponovo i odgovorite pogresno i drugi put, dobicete 2 negativne zvijezdice, za tocan"
					+ " odgovor dobicete 1 zvijezdicu. Ako sakupite 12 negativnih zvijezdica najgori ste od sve dijece"
					+ " :( ;) :) kviz ce zavrsioti, ali pokusajte sakupiti 12 pozitivnih i mozete osvojiti prvo mijesto!\n";
		} else
			this.setTitle("Error: Language selection error!");

		this.setTitle(titlMsg);
		this.setTitleColor(VuEuActivity.PANTONE_YELLOW);

		RelativeLayout relativeLayout = new RelativeLayout(this);
		// children in the parent view
		relativeLayout.setBackgroundColor(VuEuActivity.PANTONE_REFLEX_BLUE);

		// image
		RelativeLayout.LayoutParams relativeLayoutParams;
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		imgStars2 = new ImageView(this);
		relativeLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		relativeLayout.addView(imgStars2, relativeLayoutParams);
		imgStars2.setAlpha(30);
		imgStars2.setImageResource(R.drawable.stars);

		// background image world map
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		imgBkg = new ImageView(this);
		relativeLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		relativeLayout.addView(imgBkg, relativeLayoutParams);
		imgBkg.setAlpha(35);
		imgBkg.setImageResource(R.drawable.world);

		// text below title bar
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		TextView txViewDesc = new TextView(this);
		txViewDesc.setId(10);
		relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		relativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		relativeLayout.addView(txViewDesc, relativeLayoutParams);
		txViewDesc.setText(strMainInfo);
		txViewDesc.setTextColor(VuEuActivity.PANTONE_YELLOW);
		txViewDesc.setTextSize(9 * getResources().getDisplayMetrics().density);
		txViewDesc.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
		// text below text
		relativeLayoutParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		TextView txViewQuiz = new TextView(this);
		txViewQuiz.setId(ID_TX_QUIZ);
		relativeLayoutParams.addRule(RelativeLayout.BELOW, txViewDesc.getId());
		relativeLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		relativeLayout.addView(txViewQuiz, relativeLayoutParams);
		txViewQuiz.setText(strQuiz);
		txViewQuiz.setTextColor(VuEuActivity.PANTONE_YELLOW);
		txViewQuiz.setTextSize(9 * getResources().getDisplayMetrics().density);
		// show this view first
		txViewQuiz.setFocusable(true);
		txViewQuiz.requestFocus();
		// ============== DEBUG CODE ============================
		if (VuEuActivity.DEBUGGING_FLAG) {

			final Database mDatabase;
			final DbQuiz qDatabase;
			mDatabase = new Database(this, "hr");
			qDatabase = new DbQuiz(this, "hr");

			// Edit database button
			relativeLayoutParams = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			relativeLayoutParams.addRule(RelativeLayout.BELOW,
					txViewQuiz.getId());
			Button buttonEditDB = new Button(this);
			buttonEditDB.setId(ID_BTN_DBEDIT);
			relativeLayout.addView(buttonEditDB, relativeLayoutParams);

			OnClickListener l = new OnClickListener() {

				@Override
				public void onClick(View v) {
					String msg;
					if (VuEuActivity.DEBUGGING_FLAG) {
						boolean del = mDatabase.dbHelper.deleteDB();
						boolean del2 = qDatabase.dbQuizHelper.deleteDB();
						// Show message if databases are deleted.
						msg = "";
						if (!del)
							msg = "mDatabase deleted";
						else
							msg = "mDatabase NOT deleted";
						if (!del2)
							msg = msg + "\nqDatabase deleted";
						else
							msg = msg + "\nqDatabase NOT deleted";
						Toast toast = Toast.makeText(getApplicationContext(),
								msg, Toast.LENGTH_SHORT);
						toast.show();
					}
				}
			};
			buttonEditDB.setOnClickListener(l);
			buttonEditDB.setText("Delete all databases");
			// =======================================================

			// add vertical scrolling
			ScrollView vScroll = new ScrollView(this);
			vScroll.addView(relativeLayout);
			vScroll.setFillViewport(true);
			setContentView(vScroll);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.about, menu);

		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#336699")));
		return true;

	}

}
