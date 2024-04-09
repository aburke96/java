package transactionlist;

import java.util.GregorianCalendar;

import transaction.Transaction;

public interface TransactionList {
	public static final int MAX_SIZE = 100000;

	//receives: nothing
	// returns:  number transactions in the list
	public int getSize();  

	//receives: nothing
	//returns:  true if this TransactionList instance contains received transaction false if not in list.
	// uses equals method and assumes it is overloaded for Transaction instances
	public boolean contains(Transaction transaction);

	// receives: nothing
	// returns: adds transaction to list if not full (at MAX_SIZE) and 
	//       if not in list already 
	//       returns true if added, false if not
	//       list remains sorted by date at all times (most recent to least recent date)
	public boolean add(Transaction transaction);

	//receives: nothing
	// returns:  the transaction in the list at given position. 
	//       uses zero-based positions, so 0 is the position of the first transaction in the list
	//       returns null if received position is out of range for this transaction list instance.
	//  Example use:  
	//        TransactionList tList = new TransactionListImpl("transactions.txt");    
	// 		  Transaction t1 = list.get(0);
	//        assertTrue(t1!=null);
	public Transaction get(int position);

	//receives: nothing
	// returns:  the position of received transaction in the list 
	//       (uses equals method which is overloaded when matching)
	//       returns -1 if received transaction is not found in current list at any position
	//        TransactionList list = new TransactionListImpl("transaction.txt");
	//        Deposit d2 = new Deposit("aabb","3/15/2017", 100.00).
	//		  int position = list.find(d2);
	//	      assertTrue(position != -1);
	public int find(Transaction transaction);

	//receives: nothing
	// returns: returns a String of all transactions in current list instance that occurred on given date
	// each transaction is separated by a newline character. Returns an empty string
	// if no transactions occurred on received date.
	public String getTransactionListByDate(GregorianCalendar date);


	//receives: amount to test for at or above
	// returns: returns a String of all transactions in current list instance that have an 
	// amount at or above the received amount
	// each transaction is separated by a newline character. Returns an empty string
	// if no transactions are at or above the received amount
	public String getTransactionListAtAboveAmount(double amt);

	// receives: nothing
	// returns:  transaction  in this list instance that matches given transaction, removes it from list
	//       list remains sorted by date after removal. (most recent to least recent)
	//       if transaction is not in the list instance, returns null
	public Transaction remove(Transaction transaction);

	//receives: nothing
	// task: all transactions removed from this list instance, 
	public void clear();


}
