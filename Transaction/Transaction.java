package transaction;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public abstract class Transaction implements Comparable <Transaction> 
{
	public static final int MAX_ID_LENGTH = 4;
	public static final String DEFAULT_ID = "xxxx";
	public static final int DEFAULT_YEAR = 2000;
	public static final int MAX_YEAR = 2030;

	private String transactionId;
	private GregorianCalendar date;
	
	//override of default constructor, creates a default transaction with
	// random id, date of 1/1/2000, and amount 0.0
	public Transaction()
	{
		this.setTransactionId(generateNewId());
		this.setDate("1/1/2000");
		this.setAmount(0.0);
	}
	
	private double amount;

	//receives date as a string and amount of transaction to construct
	// constructs random transaction id and uses received date and amount
	public Transaction(String theDate, double theAmount)
	{
		this.setTransactionId(generateNewId());
		this.setDate(theDate);
		this.setAmount(theAmount);
	}
	//receives id, date and amount of transaction to construct
	public Transaction(String theTransactionId, String theDate, double theAmount)
	{
		this.setTransactionId(theTransactionId);
		this.setDate(theDate);
		this.setAmount(theAmount);
	}
	//receives: nothing, the standard override 
	// returns a textual string representing current Transaction attributes
	public String toString()
	{
		String temp = "";
		DecimalFormat df = new DecimalFormat("0.00");
		temp+= this.getTransactionId() + " " +
				this.getDateString() + " " +
				df.format(this.getAmount());
		return temp;
	}
	//receives: nothing                                                                                      
	// returns the id of the transaction
	public String getTransactionId()
	{
		return this.transactionId;
	}
	//receives: nothing                                                                                       
	//returns date as a GregorianCalendar type value                                              
	public GregorianCalendar getDateCalendar()
	{
		return this.date;
	}

	//receives: nothing                                                                                       
	//returns the date in format mm/dd/yyyy                                                      
	public String getDateString()
	{
		return utils.MyUtils.dateToString(date);
	}
	//receives: nothing                                                                                      
	//returns the amount of this Transaction                                                     
	public double getAmount()
	{
		return this.amount;
	}
	//receives: nothing
	//returns: a 4 character id (random, and we'll assume unique)
	private String generateNewId()
	{ 
		String chars = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String result = "";
		Random rand = new Random();
		for(int i=0; i< MAX_ID_LENGTH; i++)
		{   int randNum = rand.nextInt(chars.length());
		char ch = chars.charAt(randNum);
		result += ch;
		}
		return result;
	}   
	// receives:  transactionId letters/digits case-sensitive                               
	// task: sets this instance's id  to the received id, if valid, otherwise sets to default                       
	public void setTransactionId(String theTransactionId)
	{
		boolean valid = true;
		if(theTransactionId.length() != MAX_ID_LENGTH )
		{
			valid = false;
		}
		if(!utils.MyUtils.isValid(theTransactionId))
		{
			valid = false;
		}
		if(valid)
			this.transactionId = theTransactionId;
		else 
			this.transactionId = DEFAULT_ID;
	}
	//receives: theDate is a valid date as a String form mm/dd/yyyy including /'s                           
	//task: theDate is stored as given date 
	// invalid months, days or years cause default to be used
	// day must be in range 1-31, month must be in range 1-12, 
	//  year must be in range DEFAULT_YEAR-MAX_YEAR
	public void setDate(String theDate)
	{
		boolean valid = true;
		StringTokenizer tokenizer = new StringTokenizer(theDate, "/");
		String temp = tokenizer.nextToken().trim();  // grabs up to "/"
		int month=0, day=1, year=2000;  // default date values
		try {
			month = Integer.parseInt(temp);
			month--;  // zero-based months
			temp = tokenizer.nextToken().trim();
			day = Integer.parseInt(temp);
			temp = tokenizer.nextToken().trim();
			year = Integer.parseInt(temp);
			if(year > MAX_YEAR  || year < DEFAULT_YEAR ||
					month > 11 || month < 0 ||
					day > 31 || day < 1)
			{
				valid = false;	
			}		
		}
		catch(NumberFormatException e) {
			System.out.println("error extracting date from: " + theDate + " using default date 1/1/2000");
			valid = false;
		}
		if(valid)
		{
			this.date = new GregorianCalendar(year, month, day);
		}
		else
		{
			this.date = new GregorianCalendar(DEFAULT_YEAR, 0, 1);
		}

	}
	//receives: amount to set transaction instance's amount to                                                                                        
	//task:  theAmount stored into this transaction's amount                                                     
	public void setAmount(double theAmount)
	{
		this.amount = theAmount;

	}
	// implements the Comparable interface, so must write this method
	// allows Transaction based types to be compared, uses date and id only
	public int compareTo(Transaction transaction)
	{
		// compare dates first
		int result = this.getDateCalendar().compareTo(transaction.getDateCalendar());
		// if dates match (are the same) then compare ids
		if(result == 0)
		{
			result = this.getTransactionId().compareTo(transaction.getTransactionId());

		}
		return result;
	}
	//@Override of standard hashCode, since equals is also overridden
	// uses date and transactionId
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = result * prime + ((date == null) ? 0 : date.hashCode());
		result = result * prime
				+ ((transactionId == null) ? 0 : transactionId.hashCode());
		return result;
	}
	//@Override of equals
	//match by date, then by transactionId these are the only 2 fields used.
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Transaction))
			return false;
		Transaction other = (Transaction) obj;
		if (!date.equals(other.date))
		{
			return false;
		}
		if (!transactionId.equals(other.transactionId))
		{
			return false;
		}
		return true;
	}

}
