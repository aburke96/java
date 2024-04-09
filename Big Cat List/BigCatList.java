package bigcatlist;

import bigcat.BigCat;
import bigcat.BigCatBreed;

public interface BigCatList {

	public static final int MAX_SIZE = 15; // max # allowed in the list
	
	//receives: nothing
	//returns: number of BigCat instances currently in the list
	//  Example use
	//  BigCatList list = new BigCatListImpl();  // create empty list of BigCat instances
	//  assertTrue(list.getSize() == 0);   // size should be 0
	//  boolean result = list.add(new BigCat("Tonga", BigCatType.BOBCAT, 104));
	//  assertTrue(list.getSize() == 1);
	public int getSize();

	// receives: a BigCat instance that is NOT null
	// returns:  true if received bigcat instance matches any BigCat in this list,
	//          assumes equals is overloaded in BigCat class
	//          returns false if received BigCat is not currently in this list
	public boolean contains(BigCat bigcat);

	//receives: a BigCat instance that is NOT null
	//task:   BigCat is added to this list if not already contained in this list
	//  	  and there is room in this list to add the bigcat
	//      (big cats must be unique so no two matching cats should be in the list)
	//returns:  true if BigCat was added, false if no room in the list or duplicate
	// precondition: BigCat class overrides equals method (and thus hashCode also)
	//      Example use:
	//        BigCatList list = new BigCatListImpl();
	//        boolean result = list.add(new BigCat("Silly Sally",BigCatBreed.LION,234));
	//        assertTrue(result);
	public boolean add(BigCat bigCat);

	// receives: a position (int) in this big cat list
	// returns: the BigCat in the list at given position.
	//       uses zero-based positions, so 0 is the position of the first big cat in the list
	//       returns null if received position is out of range (0 to less than size of list.)
	//  Example use:
	//        BigCatList list = new BigCatListImpl();
	//        BigCat cat2 = new BigCat("Simba", BigCatBreed.CHEETAH, 200));
	//        boolean result = list.add(cat2);
	// 		  BigCat cat1 = list.get(0); 
	//        assertTrue(cat1.equals(cat2));
	public BigCat get(int position);

	// receives: a BigCat instance (not null)
	// returns:  the position of received BigCat in the list (0 based positioning)
	//       (uses overloaded equals when matching)
	//       returns -1 if received BigCat is not found in current list at any position
	//        BigCatList list = new BigCatListImpl();
	//        BigCat cat2 = new BigCat("Yellow Mellow",  BigCatBreed.BOBCAT, 200));

	//        boolean result = list.add(cat2);
	//		  int position = list2.find(cat2);
	//	      assertTrue(position == 0);
	public int find(BigCat bigCat);


}



