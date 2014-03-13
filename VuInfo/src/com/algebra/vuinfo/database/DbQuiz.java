package com.algebra.vuinfo.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.algebra.vuinfo.models.Question;
import com.algebra.vuinfo.models.QuizResult;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbQuiz {

	// Are we going to use an external database?
	private final static boolean EXTERNAL_DB = true;
	// Path if the database needs to be copied from asset folder
	private String DB_PATH = "/data/data/" + "com.algebra.vuinfo"
			+ "/databases/" + DB_NAME;

	public static final String DB_NAME = "dbquiz.db";
	public static final int DB_VERSION = 1;
	String language;
	public static String DBQUIZ_TABLE;
	public static final String DBQUIZ_ID = "_id";
	public static final String QUESTION = "pitanje";
	public static final String ANSWER1 = "to_odg";
	public static final String ANSWER2 = "neto_odg1";
	public static final String ANSWER3 = "neto_odg2";
	// table result
	public static String DBQUIZ_RESULT_TABLE = "result";
	public static final String DBQUIZ_PLACE = "_id";
	public static final String DBQUIZ_RESULT_TIME = "time";
	public static final String DBQUIZ_RESULT_COUNT = "count";
	public static final String DBQUIZ_RESULT_NAME = "name";

	private Context mContext;
	private SQLiteDatabase db;
	public DbQuizHelper dbQuizHelper;

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 *            original code: super(context, DB_NAME, null, 1);
	 *            this.myContext = context;
	 * @param language
	 *            string language that will determine which table to open. "en"
	 *            for database in English or "hr" in Croatian.
	 */
	public DbQuiz(Context context, String language) {
		// tie base to the activity
		mContext = context;

		if (language.equalsIgnoreCase("hr"))
			DBQUIZ_TABLE = "piokviz";
		if (language.equalsIgnoreCase("en"))
			DBQUIZ_TABLE = "qaaquiz";
		if (language.equalsIgnoreCase("result"))
			DBQUIZ_TABLE = "result";
		else
			Log.d("DbQuiz.java msg:", "Error: language not selected");

		// DB_PATH = context.getFilesDir().getPath() + "com.algebra.vuinfo" +
		// "/databases/"
		// + DB_NAME;
		// Instancing inner class
		dbQuizHelper = new DbQuizHelper();
	}

	/**
	 * Inner class for approach to object from instance of DbQuiz class.
	 * */
	public class DbQuizHelper extends SQLiteOpenHelper {

		public DbQuizHelper() {
			// The constructor is empty and all data are from main class
			// but we hand in context required by abstract class extended
			super(mContext, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// Do we have an external database, if not we create new database
			if (EXTERNAL_DB) {
				try {
					dbQuizHelper.createDataBase();
				} catch (IOException ioe) {
					throw new Error(
							"Error: Unable to create database: method createDataBase caused IOException");
				}
				// } else {
				// non object approach
				String questionSql = "CREATE TABLE " + DBQUIZ_RESULT_TABLE
						+ " (" + DBQUIZ_PLACE
						+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ DBQUIZ_RESULT_TIME + " INTEGER, "
						+ DBQUIZ_RESULT_COUNT + " INTEGER, "
						+ DBQUIZ_RESULT_NAME + " TEXT);";
				// print message that base is created and create it.
				Log.d("DbQuiz.java msg:", "--->onCreate with: " + db + " <---");
				db.execSQL(questionSql);
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// ubaciti dialog da podseti da ce baza biti potpuno izbrisana
			db.execSQL("DROP TABLE IF EXIST " + DBQUIZ_RESULT_TABLE);

		}

		/**
		 * Method that delete database. It check if database still exist and
		 * return "false" if does not exist( it is deleted) or "true" if
		 * database exist (it is NOT deleted).
		 * */
		public boolean deleteDB() {
			SQLiteDatabase checkDB = null;

			mContext.deleteDatabase(DB_PATH);
			try {
				checkDB = SQLiteDatabase.openDatabase(DB_PATH, null,
						SQLiteDatabase.OPEN_READONLY);
			} catch (SQLiteException e) {
				Log.d("DbQuiz.java msg:",
						"Catched SQLite Exception: Cannot open deleted database!");
			}
			if (checkDB != null) {
				checkDB.close();
			}
			return checkDB != null ? true : false;
		}

		/**
		 * Method that puts question into database.
		 * 
		 * @param question
		 *            class that holds data for one question (one database row)
		 * */
		public void insertQuestion(Question question) {
			// Is it database created, if not create it, and same for the table,
			// then open them for write.
			// NOTE: only one object can write at a time.
			db = dbQuizHelper.getWritableDatabase();
			// Same as bundle, keeping data (cv = null - erase data)
			ContentValues cv = new ContentValues();
			// Name of column and a value.
			cv.put(QUESTION, question.getQuestion());
			cv.put(ANSWER1, question.getQuestionAnswCorr());
			cv.put(ANSWER2, question.getQuestionAnswInc1());
			cv.put(ANSWER3, question.getQuestionAnswInc2());
			db.insert(DBQUIZ_TABLE, null, cv);
			db.close();
		}

		/**
		 * Method retrieve all questions from the database, and return them as
		 * array of questions.
		 **/
		public ArrayList<Question> getAllQuestions() {
			ArrayList<Question> items = new ArrayList<Question>();
			db = dbQuizHelper.getReadableDatabase();
			Cursor c = db.query(DBQUIZ_TABLE, null, null, null, null, null,
					null);

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				Question question = new Question();
				question.setQuestionId(c.getString(c.getColumnIndex(DBQUIZ_ID)));
				question.setQuestion(c.getString(c.getColumnIndex(QUESTION)));
				question.setQuestionAnswCorr(c.getString(c
						.getColumnIndex(ANSWER1)));
				question.setQuestionAnswInc1(c.getString(c
						.getColumnIndex(ANSWER2)));
				question.setQuestionAnswInc2(c.getString(c
						.getColumnIndex(ANSWER3)));
				items.add(question);
			}
			db.close();
			return items;
		}

		/**
		 * Method that count total number of rows in a table and returs it as an
		 * integer.- NEISPROBANO
		 * */
		public int getRowCount() {
			String selectQuery = "SELECT _id FROM " + DBQUIZ_TABLE;
			db = dbQuizHelper.getReadableDatabase();
			Cursor c = db.rawQuery(selectQuery, null);
			// Move to first row.
			c.moveToFirst();
			// Count how many rows.
			int total = c.getCount();
			db.close();
			return total;
		}

		/**
		 * Method that return cursor set to a row selected by argument value.
		 * 
		 * @param arg
		 *            integer _id value of a desired row.
		 * */
		public Cursor getCursorData(int arg) {
			return db.rawQuery("SELECT * FROM " + DBQUIZ_TABLE + " WHERE "
					+ DBQUIZ_ID + " = '" + arg + "' ", null);
		}

		/**
		 * Method that returns Question object with values vrom database
		 * selected by _id.
		 * 
		 * @param questionId
		 *            integer _id value of a desired question.
		 **/
		public Question getQuestionById(int questionId) {
			Question question = new Question();
			db = dbQuizHelper.getReadableDatabase();

			Cursor c = getCursorData(questionId);
			if (c != null && c.moveToFirst()) {
				question.setQuestion(c.getString(c.getColumnIndex(QUESTION)));
				question.setQuestionAnswCorr(c.getString(c
						.getColumnIndex(ANSWER1)));
				question.setQuestionAnswInc1(c.getString(c
						.getColumnIndex(ANSWER2)));
				question.setQuestionAnswInc2(c.getString(c
						.getColumnIndex(ANSWER3)));
				question.setQuestionId(c.getString(c.getColumnIndex(DBQUIZ_ID)));
			}
			db.close();
			return question;
		}

		// ========== 3 methods for handling external database=================
		/**
		 * Creates a empty database on the system and rewrites it with your own
		 * database from assets folder.
		 * */
		public void createDataBase() throws IOException {

			boolean dbExist = checkDataBase();
			SQLiteDatabase db_Read = null;
			if (dbExist) {
				// do nothing - database already exist
			} else {
				Log.d("DbQuiz.java msg: ", "Creating empty database ...");
				// By calling this method and empty database will be created
				// into the default system path of your application, so we will
				// be able to overwrite that database with our database.
				// this.getReadableDatabase();
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
		 * Check if the database (we check only if file exist for easier
		 * handling-no exception handling required) exists in desired path.
		 * */
		private boolean checkDataBase() {
			File dbFile = new File(DB_PATH);
			return dbFile.exists();
		}

		/**
		 * Copy database from assets folder.
		 * */
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

		/**
		 * Method retrieve all results from the database, and return them as
		 * array of results.
		 **/
		public ArrayList<QuizResult> getAllResults() {
			ArrayList<QuizResult> items = new ArrayList<QuizResult>();
			db = dbQuizHelper.getReadableDatabase();
			try {
				Cursor c = db.query(DBQUIZ_RESULT_TABLE, null, null, null,
						null, null, null);

				for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
					QuizResult result = new QuizResult();
					result.setAnswersCount(c.getInt(c
							.getColumnIndex(DBQUIZ_RESULT_COUNT)));
					result.setPlace(c.getInt(c.getColumnIndex(DBQUIZ_PLACE)));
					result.setPlayerName(c.getString(c
							.getColumnIndex(DBQUIZ_RESULT_NAME)));
					result.setQuizTime(c.getInt(c
							.getColumnIndex(DBQUIZ_RESULT_TIME)));
					items.add(result);
				}
			} catch (Exception e) {
				// fail
				Log.d("DbQuiz.java", "Database table " + DBQUIZ_RESULT_TABLE
						+ " doesn't exist!");
				// create table if not exists mytable (col1 type, col2 type);
				// but it our case it should be created first time application
				// is run
			}
			db.close();
			return items;
		}

		/**
		 * Method that puts question into database.
		 * 
		 * @param question
		 *            class that holds data for one question (one database row)
		 * */
		public void insertResults(ArrayList<QuizResult> results) {
			// Is it database created, if not create it, and same for the table,
			// then open them for write.
			// NOTE: only one object can write at a time.
			db = dbQuizHelper.getWritableDatabase();
			for (int i = 0; i < results.size(); i++) {
				// Same as bundle, keeping data (cv = null - erase data)
				ContentValues cv = new ContentValues();
				// Name of column and a value.
				// cv.put(DBQUIZ_PLACE, results.get(i).getPlace());
				cv.put(DBQUIZ_RESULT_TIME, results.get(i).getQuizTime());
				cv.put(DBQUIZ_RESULT_COUNT, results.get(i).getAnswersCount());
				cv.put(DBQUIZ_RESULT_NAME, results.get(i).getPlayerName());
				long row = db.insert(DBQUIZ_TABLE, null, cv);
				Log.d("DbQuiz.java", "inserted row: #" + row + "\n");
			}
			db.close();
		}

		/**
		 * Method that delete specified table
		 * */
		public void deleteTable(String tableToDelete) {
			db = dbQuizHelper.getWritableDatabase();
			db.delete(tableToDelete, null, null);
			db.close();
		}
	}
}
