package com.enghack.watcard;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DataBaseHandler contains all methods for manipulating SQLite database
 * @author Seikun
 */
// Keep this Class in reserve for use of databases
public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "transactionsManager";

	// Contacts table name
	private static final String TABLE_TRANSACTIONS = "transactions";

	// Contacts Table Columns names
	private static final String KEY_ID = "_id";
	private static final String KEY_AMOUNT = "amount";
	private static final String KEY_DATE = "date";
	private static final String KEY_TRANS_TYPE = "trans_type";
	private static final String KEY_TERMINAL = "terminal";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TRANSACTIONS_TABLE = "CREATE TABLE " + TABLE_TRANSACTIONS
				+ "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_AMOUNT
				+ " REAL, " + KEY_DATE + " TEXT, " + KEY_TRANS_TYPE + " TEXT, "
				+ KEY_TERMINAL + " TEXT" + ")";
		db.execSQL(CREATE_TRANSACTIONS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new transaction
	public void addTransaction(Transaction transaction) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_AMOUNT, transaction.getAmount()); // Transaction Amount
		values.put(KEY_DATE, transaction.getDate()); // Transaction Date
		values.put(KEY_TRANS_TYPE, transaction.getTransType()); // Transaction
																// Trans-Type
		values.put(KEY_TERMINAL, transaction.getTerminal()); // Transaction
																// Terminal

		// Inserting Row
		db.insert(TABLE_TRANSACTIONS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single transaction
	public Transaction getTransaction(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_TRANSACTIONS, new String[] { KEY_ID,
				KEY_AMOUNT, KEY_DATE, KEY_TRANS_TYPE, KEY_TERMINAL }, KEY_ID
				+ "=?", new String[] { String.valueOf(id) }, null, null, null,
				null);
		if (cursor != null)
			cursor.moveToFirst();

		/*Transaction transaction = new Transaction(Integer.parseInt(cursor
				.getString(0)), Double.parseDouble(cursor.getString(1)),
				Integer.parseInt(cursor.getString(2)), cursor.getString(3), cursor.getString(4));
		// return transaction
		return transaction;*/
		return null;
	}

	// Getting All Transactions
	/*public List<Transaction> getAllTransactions() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_TRANSACTIONS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Transaction transaction = new Transaction();
				transaction.setID(Integer.parseInt(cursor.getString(0)));
				transaction.setAmount(Double.parseDouble(cursor.getString(1)));
				transaction.setDate(Integer.parseInt(cursor.getString(2)));
				transaction.setTransType(cursor.getString(3));
				transaction.setTerminal(cursor.getString(4));
				// Adding transaction to list
				transactionList.add(transaction);
			} while (cursor.moveToNext());
		}

		// return transaction list
		return transactionList;
	}*/
	
	// Deleting All Transactions
	public void deleteAllTransactions(){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_TRANSACTIONS, null, null);
	}

	// Deleting single Transaction
	public void deleteTransaction(Transaction transaction) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_TRANSACTIONS, KEY_ID + " = ?",
				new String[] { String.valueOf(transaction.getID()) });
		db.close();
	}

	// Getting transactions Count
	public int getTransactionsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_TRANSACTIONS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}