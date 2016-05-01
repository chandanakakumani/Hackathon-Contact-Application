package users.example.com.hackathoncontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "BusinessCard";

	// Contacts table name
	private static final String TABLE_BUSINESSCARD = "BusinessCardTable";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_DESIGNATION = "designation";
	private static final String KEY_COMPANY_NAME = "company_name";
	private static final String KEY_PHONE_MOBILE = "phone_number_mobile";
	private static final String KEY_PHONE_WORK = "phone_number_work";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_WEB_ADDRESS = "web_address";
	private static final String KEY_STREET_ADDRESS = "street_address";
	private static final String KEY_CITY = "city";
	private static final String KEY_STATE = "state";
	private static final String KEY_POSTALCODE = "postalcode";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase database) {
		String CREATE_BUSINESSCARD_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_BUSINESSCARD + "(" + KEY_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
				+ KEY_DESIGNATION + " TEXT," + KEY_COMPANY_NAME + " TEXT,"
				+ KEY_PHONE_MOBILE + " TEXT," + KEY_PHONE_WORK + " TEXT,"
				+ KEY_EMAIL + " TEXT," + KEY_WEB_ADDRESS + " TEXT,"
				+ KEY_STREET_ADDRESS + " TEXT," + KEY_CITY + " TEXT,"
				+ KEY_STATE + " TEXT," + KEY_POSTALCODE + " TEXT"
				+ " )";
		database.execSQL(CREATE_BUSINESSCARD_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUSINESSCARD);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All Create, Read, Update, Delete Operations
	 */

	// Adding new BusinessCard
	public long addBusinessCard(BusinessCard businesscard) {

		SQLiteDatabase database = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, businesscard.get_name());
		values.put(KEY_DESIGNATION, businesscard.get_designation());
		values.put(KEY_COMPANY_NAME, businesscard.get_company_name());
		values.put(KEY_PHONE_MOBILE, businesscard.get_phone_number_mobile());
		values.put(KEY_PHONE_WORK, businesscard.get_phone_number_work());
		values.put(KEY_EMAIL, businesscard.get_email());
		values.put(KEY_WEB_ADDRESS, businesscard.get_web_address());
		values.put(KEY_STREET_ADDRESS, businesscard.get_street_address());
		values.put(KEY_CITY, businesscard.get_city());
		values.put(KEY_STATE, businesscard.get_state());
		values.put(KEY_POSTALCODE, businesscard.get_postalcode());

		// Inserting Row
		long pos=database.insert(TABLE_BUSINESSCARD, null, values);
		System.out.println(pos);
		
		// Closing database connection
		database.close();
		System.out.println(pos);
	 	return pos;
	}

	// Getting single BusinessCard
	public BusinessCard getBusinessCard(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_BUSINESSCARD, new String[] { KEY_ID,
				KEY_NAME, KEY_DESIGNATION, KEY_COMPANY_NAME, KEY_PHONE_MOBILE,
				KEY_PHONE_WORK, KEY_EMAIL, KEY_WEB_ADDRESS, KEY_STREET_ADDRESS,
				KEY_CITY, KEY_STATE, KEY_POSTALCODE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		BusinessCard businesscard = new BusinessCard(Integer.parseInt(cursor
				.getString(0)), cursor.getString(1), cursor.getString(2),
				cursor.getString(3), cursor.getString(4), cursor.getString(5),
				cursor.getString(6), cursor.getString(7), cursor.getString(8),
				cursor.getString(9), cursor.getString(10),
				cursor.getString(11));

		return businesscard;
	}

	// Getting All BusinessCard
	public List<BusinessCard> getAllBusinessCards() {
		List<BusinessCard> businesscardList = new ArrayList<BusinessCard>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_BUSINESSCARD;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				BusinessCard businesscard = new BusinessCard();
				businesscard.set_id(Integer.parseInt(cursor.getString(0)));
				businesscard.set_name(cursor.getString(1));
				businesscard.set_designation(cursor.getString(2));
				businesscard.set_company_name(cursor.getString(3));
				businesscard.set_phone_number_mobile(cursor.getString(4));
				businesscard.set_phone_number_work(cursor.getString(5));
				businesscard.set_email(cursor.getString(6));
				businesscard.set_web_address(cursor.getString(7));
				businesscard.set_street_address(cursor.getString(8));
				businesscard.set_city(cursor.getString(9));
				businesscard.set_state(cursor.getString(10));
				businesscard.set_postalcode(cursor.getString(11));

				// Adding BusinessCard to list
				businesscardList.add(businesscard);
			} while (cursor.moveToNext());
		}

		// return BusinessCard list
		return businesscardList;
	}

	// Updating single BusinessCard
	public int updateContact(BusinessCard businesscard) {
		SQLiteDatabase database = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, businesscard.get_name());
		values.put(KEY_DESIGNATION, businesscard.get_designation());
		values.put(KEY_COMPANY_NAME, businesscard.get_company_name());
		values.put(KEY_PHONE_MOBILE, businesscard.get_phone_number_mobile());
		values.put(KEY_PHONE_WORK, businesscard.get_phone_number_work());
		values.put(KEY_EMAIL, businesscard.get_email());
		values.put(KEY_WEB_ADDRESS, businesscard.get_web_address());
		values.put(KEY_STREET_ADDRESS, businesscard.get_street_address());
		values.put(KEY_CITY, businesscard.get_city());
		values.put(KEY_STATE, businesscard.get_state());
		values.put(KEY_POSTALCODE, businesscard.get_postalcode());

		// updating row
		return database.update(TABLE_BUSINESSCARD, values, KEY_ID + " = ?",
				new String[] { String.valueOf(businesscard.get_id()) });
	}

	// Deleting single BusinessCard using id
	public void deleteBusinessCard(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_BUSINESSCARD, KEY_ID + "=" + id, null);
		db.close();
	}

	// Getting BusinessCard Count
	public int getBusinessCardsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_BUSINESSCARD;
		SQLiteDatabase database = this.getReadableDatabase();
		Cursor cursor = database.rawQuery(countQuery, null);
		cursor.close();

		return cursor.getCount();
	}

	public int getLastPosition() {

		SQLiteDatabase database = this.getReadableDatabase();
		String countQuery = "SELECT  * FROM " + TABLE_BUSINESSCARD;
		Cursor cursor = database.rawQuery(countQuery, null);

		cursor.moveToLast();

		cursor.close();
		return cursor.getPosition();
	}

}
