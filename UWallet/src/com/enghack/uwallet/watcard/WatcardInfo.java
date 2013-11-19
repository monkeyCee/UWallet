package com.enghack.uwallet.watcard;

import java.util.ArrayList;

/**
 * Stores all of the information regarding the holder of 
 * the Watcard. 
 * This includes Student ID, PIN, and all of the previous Transactions
 * Meal plan and flex dollars will be calculated
 * @author Andy
 *
 */
public class WatcardInfo {
	
	// Only have 1 mInstance at a time
	// false for none
	// true for one
	public static boolean mInstance = false;
	
	// List of all of the transactions
	private ArrayList<Transaction> transList;
	
	private String studentID;
	private String PIN;
	
	private double flexBalance;
	private double mealBalance;
	private double otherBalance;
	
	public WatcardInfo(ArrayList<Transaction> list,double mealBalance, double flexBalance,double otherBalance, String studentID, String PIN)
	{
		this.mInstance = true;
		this.transList = list;
		this.flexBalance = flexBalance;
		this.mealBalance = mealBalance;
		this.otherBalance = otherBalance;
		this.studentID = studentID;
		this.PIN = PIN;
	}
	
	/**
	 * Gets the list of transactions
	 * @return
	 */
	public ArrayList<Transaction> getList()
	{
		return this.transList;
	}
	
	/**
	 * Sets the list of transactions
	 * @param list
	 */
	public void setList(ArrayList<Transaction> list)
	{
		this.transList = list;
	}
	
	/**
	 * Set student ID of the Watcard
	 * @param id
	 */
	public void setID(String id)
	{
		this.studentID = id;
	}
	
	/**
	 * Get the student ID of the current Watcard
	 * @return
	 */
	public String getID()
	{
		return this.studentID;
	}
	
	/**
	 * Set the PIN of the Watcard
	 * @param pin
	 */
	public void setPIN(String pin)
	{
		this.PIN = pin;
	}
	
	/**
	 * Get the PIN of the current Watcard
	 * @return
	 */
	public String getPIN()
	{
		return this.PIN;
	}
	
	public double getMealBalance(){
		return this.mealBalance;
	}
	
	public double getFlexBalance(){
		return this.flexBalance;
	}
	
	public void printData()
	{
		for (Transaction transaction: this.transList)
		{
			System.out.println(transaction._amount+" "+transaction._terminal);
		}
		System.out.println("You currently have "+this.mealBalance+" mealplan dollars");
		System.out.println("You currently have "+this.flexBalance+" flex dollars");
	}
	
	
}