package com.algebra.vuinfo.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.algebra.vuinfo.models.Country;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database {

	public static final String DB_NAME = "database.db";
	public static final int DB_VERSION = 1;
	public static String COUNTRY_TABLE;
	// underscore first for an auto-increment (1, 2, 3 ....)
	public static final String COUNTRY_ID = "_id";// id_";
	public static final String COUNTRY_NAME = "country_name";
	public static final String COUNTRY_OFFICIAL_NAME = "country_official_name";
	public static final String COUNTRY_STATUS = "country_status";
	public static final String COUNTRY_DOMAIN = "country_domain";
	public static final String OFFICIAL_LANGUAGES = "official_languages";
	public static final String COUNTRY_CURRENCY = "country_currency";
	public static final String COUNTRY_TIME_ZONES = "country_time_zones";
	public static final String COUNTRY_CAPITAL = "country_capital";
	public static final String COUNTRY_CODE = "country_code";
	public static final String COUNTRY_CALLING_CODE = "country_calling_code";
	public static final String COUNTRY_AREA = "country_area";
	public static final String PER_WATER_AREA = "per_water_area";
	public static final String COUNTRY_POPULATION = "country_population";
	public static final String POPULATION_PER_AREA = "population_per_area";
	public static final String POPULATION_PER_NUMBER = "population_per_number";
	public static final String POPULATION_PER_DENSITY = "population_per_density";
	public static final String COUNTRY_GDP = "country_gdp";
	public static final String GDP_PER_PERSON = "gdp_per_person";
	public static final String COUNTRY_PRESIDENT = "country_president";
	public static final String COUNTRY_PRIME_MINISTER = "country_prime_minister";
	public static final String COUNTRY_INDEPENDANCE = "country_independance";

	private Context mContext;
	private SQLiteDatabase db;

	// za pistupiti objektu(to jest unutarnjoj klasi)
	// iz instance Database klase
	public DbHelper dbHelper;

	// Path if the database needs to be copied from asset folder

	// because Context.getFilesDir().getPath() we don't use this
	private String DB_PATH = "/data/data/" + "com.algebra.vuinfo"
			+ "/databases/" + DB_NAME;

	// Do we need an external database?
	private final static boolean EXTERNAL_DB = true;

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 *            original code: super(context, DB_NAME, null, 1);
	 *            this.myContext = context;
	 */
	public Database(Context context, String language) {
		// baza se veze na svaki aktivity posebno, kontext je (povezuje)aktivity
		mContext = context;
		// DB_PATH = context.getFilesDir().getPath() + "com.algebra.vuinfo" +
		// "/databases/"
		// + DB_NAME;

		if (language.equalsIgnoreCase("hr"))
			COUNTRY_TABLE = "zemlje";
		else if (language.equalsIgnoreCase("en"))
			COUNTRY_TABLE = "countries";
		else
			Log.d("Database.java constructor msg:",
					"Error: language not selected");

		// instanciramo takodje i unutarnju klasu
		dbHelper = new DbHelper();
	}

	public class DbHelper extends SQLiteOpenHelper {

		public DbHelper() {
			// konstruktor ostavljamo prazan a podaci su uzeti iz glavne klase
			// njegova napisana klasa zahteva kontekst koji prosledjujemo
			super(mContext, DB_NAME, null, DB_VERSION);
		}

		/**
		 * Creates database, we use currently to get base from assets folder
		 **/
		@Override
		public void onCreate(SQLiteDatabase db) {
			// Do we have an external database, if not we create new database
			if (EXTERNAL_DB) {
				try {
					createDataBase();
				} catch (IOException ioe) {
					throw new Error(
							"Error: Unable to create database: method createDataBase caused IOException");
				}

			} else {
				// normalan pristup bazi podataka (nije objektan pristup)
				String countrySql = "CREATE TABLE " + COUNTRY_TABLE + " ("
						+ COUNTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ COUNTRY_NAME + " TEXT, " + COUNTRY_OFFICIAL_NAME
						+ " TEXT, " + COUNTRY_STATUS + " TEXT, "
						+ COUNTRY_DOMAIN + " TEXT, " + OFFICIAL_LANGUAGES
						+ " TEXT, " + COUNTRY_CURRENCY + " TEXT, "
						+ COUNTRY_TIME_ZONES + " TEXT, " + COUNTRY_CAPITAL
						+ " TEXT);";
				// ispisuje poruku u LogCat o kreiranju baze
				Log.d("Database.java msg:", "--->onCreate with: " + db
						+ " <---");
				db.execSQL(countrySql); // kreira tablicu u databazi
			}
		}

		@Override
		// koristi se kod kompleksnih baza
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// ubaciti dialog da podseti da ce baza biti potpuno izbrisana
			db.execSQL("DROP TABLE IF EXIST " + COUNTRY_TABLE);

		}

		public boolean deleteDB() {
			SQLiteDatabase checkDB = null;

			// ubaciti dialog da podseti da ce baza biti potpuno izbrisana
			// db.execSQL("DROP TABLE IF EXIST " + COUNTRY_TABLE);
			// db.delete(COUNTRY_TABLE, "1", new String[] {});
			mContext.deleteDatabase(DB_PATH);
			try {
				checkDB = SQLiteDatabase.openDatabase(DB_PATH, null,
						SQLiteDatabase.OPEN_READONLY);
			} catch (SQLiteException e) {
				// database does't exist.
			}

			if (checkDB != null) {
				checkDB.close();
			}

			return checkDB != null ? true : false;
		}

		/**
		 * Inserts country into database ----- we don't need this anymore
		 **/
		public void insertCountry(Country country) {
			// dali je baza kreirana, ako nije kreiraj, isto tako i za tablicu,
			// i otvori je
			// za pisanje (samo jedan objekt moze da pise u bazu u isto vreme)
			db = dbHelper.getWritableDatabase();
			// isto kao i bundle, cuva neke podatke (cv = null - brise podatke)
			ContentValues cv = new ContentValues();
			// ime stupca i vrednost
			cv.put(COUNTRY_NAME, country.getCountryName());
			cv.put(COUNTRY_OFFICIAL_NAME, country.getCountryOfficialName());
			cv.put(COUNTRY_STATUS, country.getCountryStatus());
			cv.put(COUNTRY_DOMAIN, country.getCountryDomain());
			cv.put(OFFICIAL_LANGUAGES, country.getOfficialLanguages());
			cv.put(COUNTRY_CURRENCY, country.getCountryCurrency());
			cv.put(COUNTRY_TIME_ZONES, country.getCountryTimeZones());
			cv.put(COUNTRY_CAPITAL, country.getCountryCapital());
			cv.put(COUNTRY_CODE, country.getCountryCode());
			cv.put(COUNTRY_CALLING_CODE, country.getCountryCallingCode());
			cv.put(COUNTRY_AREA, country.getCountryArea());
			cv.put(PER_WATER_AREA, country.getPerWaterArea());
			cv.put(COUNTRY_POPULATION, country.getCountryPopulation());
			cv.put(POPULATION_PER_AREA, country.getPopulationPerArea());
			cv.put(POPULATION_PER_NUMBER, country.getPopulationPerNumber());
			cv.put(POPULATION_PER_DENSITY, country.getPopulationPerDensity());
			cv.put(COUNTRY_GDP, country.getCountryGdp());
			cv.put(GDP_PER_PERSON, country.getGdpPerPerson());
			cv.put(COUNTRY_PRESIDENT, country.getCountryPresident());
			cv.put(COUNTRY_PRIME_MINISTER, country.getCountryPrimeMinister());
			cv.put(COUNTRY_INDEPENDANCE, country.getCountryIndependance());

			db.insert(COUNTRY_TABLE, null, cv);
			db.close();
		}

		/**
		 * Returns array list of (Country objects) all countries in the database
		 **/
		public ArrayList<Country> getAllCountries() {
			ArrayList<Country> items = new ArrayList<Country>();
			db = dbHelper.getReadableDatabase();
			Cursor c = db.query(COUNTRY_TABLE, null, null, null, null, null,
					null);

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				Country country = new Country();
				// dolnji kod uzima ime kolone, ako zelimo prikazati tablicu sa
				// imenima colona
				// country.setCountryName(c.getColumnName(c.getColumnIndex(COUNTRY_NAME)));

				// getColumnName uzima vrednost te kolone a ne samo ime kolone
				country.setCountryName(c.getString(c
						.getColumnIndex(COUNTRY_NAME)));
				country.setCountryCapital(c.getString(c
						.getColumnIndex(COUNTRY_CAPITAL)));
				country.setCountryCurrency(c.getString(c
						.getColumnIndex(COUNTRY_CURRENCY)));
				country.setCountryDomain(c.getString(c
						.getColumnIndex(COUNTRY_DOMAIN)));
				country.setCountryId(c.getString(c.getColumnIndex(COUNTRY_ID)));
				country.setCountryOfficialName(c.getString(c
						.getColumnIndex(COUNTRY_OFFICIAL_NAME)));
				country.setCountryStatus(c.getString(c
						.getColumnIndex(COUNTRY_STATUS)));
				country.setCountryTimeZones(c.getString(c
						.getColumnIndex(COUNTRY_TIME_ZONES)));
				country.setOfficialLanguages(c.getString(c
						.getColumnIndex(OFFICIAL_LANGUAGES)));
				country.setCountryCode(c.getString(c
						.getColumnIndex(COUNTRY_CODE)));
				country.setCountryCallingCode(c.getString(c
						.getColumnIndex(COUNTRY_CALLING_CODE)));
				country.setCountryArea(c.getString(c
						.getColumnIndex(COUNTRY_AREA)));
				country.setPerWaterArea(c.getString(c
						.getColumnIndex(PER_WATER_AREA)));
				country.setCountryPopulation(c.getString(c
						.getColumnIndex(COUNTRY_POPULATION)));
				country.setPopulationPerArea(c.getString(c
						.getColumnIndex(POPULATION_PER_AREA)));
				country.setPopulationPerNumber(c.getString(c
						.getColumnIndex(POPULATION_PER_NUMBER)));
				country.setPopulationPerDensity(c.getString(c
						.getColumnIndex(POPULATION_PER_DENSITY)));
				country.setCountryGdp(c.getString(c.getColumnIndex(COUNTRY_GDP)));
				country.setGdpPerPerson(c.getString(c
						.getColumnIndex(GDP_PER_PERSON)));
				country.setCountryPresident(c.getString(c
						.getColumnIndex(COUNTRY_PRESIDENT)));
				country.setCountryPrimeMinister(c.getString(c
						.getColumnIndex(COUNTRY_PRIME_MINISTER)));
				country.setCountryIndependance(c.getString(c
						.getColumnIndex(COUNTRY_INDEPENDANCE)));
				items.add(country);
			}
			db.close();
			return items;
		}

		/**
		 * Count total number of rows in a table and returns int value.
		 **/
		public int getRowCount() {
			String selectQuery = "SELECT _id FROM countries";
			db = dbHelper.getReadableDatabase();
			Cursor c = db.rawQuery(selectQuery, null);
			// move to first row
			c.moveToFirst();
			// count how many rows
			int total = c.getCount();
			db.close();
			return total;
		}

		/**
		 * count total number of empty rows in a table -NEISPROBANO
		 **/
		public int getEmptyRowCount(boolean empty) {
			db = dbHelper.getReadableDatabase();
			// Cursor to an empty row
			Cursor c = db.rawQuery("select _id from " + COUNTRY_TABLE
					+ " where country_name IS NULL OR TRIM(app_salutation)=''",
					null);
			c.moveToNext(); // Move to next but empty or not ????
			int total = c.getCount();
			db.close();
			return total;
		}

		/**
		 * returns _id value of row chosen by row value of country_domain column
		 **/
		public int getIdByDomain(String domain) {
			db = dbHelper.getReadableDatabase();
			Cursor c = db.rawQuery("select _id from " + COUNTRY_TABLE
					+ " where country_domain IS " + domain, null);
			int id = c.getColumnIndex("_id");
			db.close();
			return id;
		}

		/**
		 * returns country_domain column value of a row chosen by _id value.
		 **/
		public String getDomainById(String id) {
			// get number of rows in database before we open database (only one
			// method can open database at the time)
			int rows = getRowCount();
			db = dbHelper.getReadableDatabase();
			String domain = "Error: getDomainById - id not found!";
			int iId = Integer.parseInt(id);
			if (rows >= iId) {
				Cursor c = db.rawQuery("select country_domain from "
						+ COUNTRY_TABLE + " where _id IS " + id, null);
				for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
					domain = c.getString(c.getColumnIndex(COUNTRY_DOMAIN));
					if (domain.length() > 2)
						break;
				}
			}
			db.close();
			return domain;
		}

		/**
		 * Returns cursor pointed on row selected by domain value
		 **/
		public Cursor getCursorData(String arg) {
			return db.rawQuery("SELECT * FROM '" + COUNTRY_TABLE
					+ "' WHERE country_domain = '" + arg + "' ", null);
		}

		/**
		 * Returns Country (row) chosen by value of country_domein
		 **/
		public Country getCountryByDomain(String countryDomain) {
			Country country = new Country();
			db = dbHelper.getReadableDatabase();
			Cursor c = getCursorData(countryDomain);
			if (c != null && c.moveToFirst()) {
				country.setCountryName(c.getString(c
						.getColumnIndex(COUNTRY_NAME)));
				country.setCountryCapital(c.getString(c
						.getColumnIndex(COUNTRY_CAPITAL)));
				country.setCountryCurrency(c.getString(c
						.getColumnIndex(COUNTRY_CURRENCY)));
				country.setCountryDomain(c.getString(c
						.getColumnIndex(COUNTRY_DOMAIN)));
				country.setCountryId(c.getString(c.getColumnIndex(COUNTRY_ID)));
				country.setCountryOfficialName(c.getString(c
						.getColumnIndex(COUNTRY_OFFICIAL_NAME)));
				country.setCountryStatus(c.getString(c
						.getColumnIndex(COUNTRY_STATUS)));
				country.setCountryTimeZones(c.getString(c
						.getColumnIndex(COUNTRY_TIME_ZONES)));
				country.setOfficialLanguages(c.getString(c
						.getColumnIndex(OFFICIAL_LANGUAGES)));
				country.setCountryCode(c.getString(c
						.getColumnIndex(COUNTRY_CODE)));
				country.setCountryCallingCode(c.getString(c
						.getColumnIndex(COUNTRY_CALLING_CODE)));
				country.setCountryArea(c.getString(c
						.getColumnIndex(COUNTRY_AREA)));
				country.setPerWaterArea(c.getString(c
						.getColumnIndex(PER_WATER_AREA)));
				country.setCountryPopulation(c.getString(c
						.getColumnIndex(COUNTRY_POPULATION)));
				country.setPopulationPerArea(c.getString(c
						.getColumnIndex(POPULATION_PER_AREA)));
				country.setPopulationPerNumber(c.getString(c
						.getColumnIndex(POPULATION_PER_NUMBER)));
				country.setPopulationPerDensity(c.getString(c
						.getColumnIndex(POPULATION_PER_DENSITY)));
				country.setCountryGdp(c.getString(c.getColumnIndex(COUNTRY_GDP)));
				country.setGdpPerPerson(c.getString(c
						.getColumnIndex(GDP_PER_PERSON)));
				country.setCountryPresident(c.getString(c
						.getColumnIndex(COUNTRY_PRESIDENT)));
				country.setCountryPrimeMinister(c.getString(c
						.getColumnIndex(COUNTRY_PRIME_MINISTER)));
				country.setCountryIndependance(c.getString(c
						.getColumnIndex(COUNTRY_INDEPENDANCE)));
			}
			db.close();
			return country;
		}

		// ========== 3 methods for handling external database=================
		/**
		 * Creates a empty database on the system and rewrites it with your own
		 * database.
		 **/
		public void createDataBase() throws IOException {
			boolean dbExist = checkDataBase();
			SQLiteDatabase db_Read = null;
			if (dbExist) {
				// do nothing - database already exist
			} else {
				Log.d("DbQuiz.java msg: ", "Creating empty database ...");
				// By calling this method and empty database will be created
				// into the default system path
				// of your application so we are gonna be able to overwrite that
				// database with our database.
				// this.getReadableDatabase(); <--- posible problem if used like
				// this on some devices
				db_Read = this.getReadableDatabase();
				db_Read.close();
				try {
					Log.d("DbQuiz.java msg: ", "... copying data from assets.");
					copyDataBase();
				} catch (IOException e) {
					throw new Error("Error: copying database fail!");
				}
			}
		}

		/**
		 * Check if the database ( we check if file exist for easier handlin-no
		 * exception handling required) exists in desired path
		 **/
		private boolean checkDataBase() {
			File dbFile = new File(DB_PATH);
			return dbFile.exists();
		}

		/**
		 * Copy the database from assets
		 **/
		private void copyDataBase() throws IOException {
			// Open local db as the input stream
			InputStream mInput = mContext.getAssets().open(DB_NAME);
			// Path to the just created empty db
			String outFileName = DB_PATH;
			// Open the empty db as the output stream
			OutputStream mOutput = new FileOutputStream(outFileName);
			// Transfer bytes from the inputfile to the outputfile
			byte[] mBuffer = new byte[1024];
			int mLength;
			while ((mLength = mInput.read(mBuffer)) > 0) {
				mOutput.write(mBuffer, 0, mLength);
			}
			// Close the streams
			mOutput.flush();
			mOutput.close();
			mInput.close();
		}
	}
}
