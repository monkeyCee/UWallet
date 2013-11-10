package com.enghack.watcard;

/**
 * Template for each transaction object, standard get/set methods
 * @author Seikun
 */

public class Transaction {

	// private variables
	int _id;
	double _amount;
	String _date;
	String _trans_type;
	String _terminal;

	// Empty constructor
	public Transaction() {

	}

	// constructor
	public Transaction(int id, double amount, String date, String trans_type,
			String terminal) {
		this._id = id;
		this._amount = amount;
		this._date = date;
		this._trans_type = trans_type;
		this._terminal = terminal;
	}

	// constructor
	public Transaction(double amount, String date, String trans_type,
			String terminal) {
		this._amount = amount;
		this._date = date;
		this._trans_type = trans_type;
		this._terminal = terminal;
	}

	// getting id
	public int getID() {
		return this._id;
	}

	// setting id
	public void setID(int id) {
		this._id = id;
	}

	// getting amount
	public double getAmount() {
		return this._amount;
	}

	// setting amount
	public void setAmount(double amount) {
		this._amount = amount;
	}

	// getting date
	public String getDate() {
		return this._date;
	}

	// setting date
	public void setDate(String date) {
		this._date = date;
	}

	// getting trans-type
	public String getTransType() {
		return this._trans_type;
	}

	// setting trans-type
	public void setTransType(String trans_type) {
		this._trans_type = trans_type;
	}

	// getting terminal
	public String getTerminal() {
		return this._terminal;
	}

	// setting terminal
	public void setTerminal(String terminal) {
		this._terminal = terminal;
	}
}