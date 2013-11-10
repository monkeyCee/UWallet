package com.enghack.uwallet.login;

import java.util.ArrayList;

import org.jsoup.nodes.Element;

import com.enghack.watcard.Transaction;

public class HTMLParser {
	
	public HTMLParser() {
		// Void Constructor
	}
	
	public ArrayList<Transaction> parseHTML(Element table)
	{
		int count = 0;
		
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		// For all table rows greater than the first one
		// First one has title of each column
		for (Element tr:table.select("tr:gt(1)"))
		{
			// To be modified
			Transaction trans = new Transaction(
					count++,
					Double.parseDouble(spaceFilter(tr.getElementById("oneweb_financial_history_td_amount").text())),
					tr.getElementById("oneweb_financial_history_td_date").text(),
					tr.getElementById("oneweb_financial_history_td_trantype").text(),
					tr.getElementById("oneweb_financial_history_td_terminal").text());
			transactionList.add(trans);
		}
		return transactionList;
	}
	
	public static String spaceFilter(String a)
	{
		return a.replaceAll("\\s+","");
	}
}
