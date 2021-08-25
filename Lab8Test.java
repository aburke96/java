package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import bigcat.BigCat;
import bigcat.BigCatBreed;
import bigcat.BigCatUtilsImpl;
import bigcatlist.BigCatList;
import bigcatlist.BigCatListImpl;

class Lab8Test {
	static String sName = "";  // used for student name
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		sName = utils.MyUtils.getNameFromStudent();
		System.out.println("Test for " + sName + " self-certified by " + sName);
		System.out.println("Begin Lab #8 Testing for " + sName + " begins..");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

		System.out.println("End of Lab #8 testing for " + sName + " .......");
	}
	@Test
	public void test()
	{
		testConstructors();
		testAccessorsAndModifiers();
		testEqualsAndHashCode();
		testBigCatUtils();
		testAddContains();
		testFindGet();
		
	}

	public void testConstructors()
	{
		System.out.println("\n****Test Constructors *****");
		BigCat cat1 = new BigCat();
		System.out.println("Default cat1: " + cat1);
		assertEquals(BigCat.DEFAULT_NAME, cat1.getName());
		assertEquals(BigCatBreed.HOUSE_CAT, cat1.getCatBreed());
		assertEquals(0, cat1.getWeight());
		BigCat cat2 = new BigCat("  ", BigCatBreed.CHEETAH, -5);
		assertEquals(BigCat.DEFAULT_NAME, cat2.getName(), "Name should default -- all blanks");
		assertEquals(BigCatBreed.CHEETAH, cat2.getCatBreed(), "Breed should be CHEETAH");
		assertEquals(0, cat2.getWeight(), "cat's weight should be 0 as -5 is not allowed");
		BigCat cat3 = new BigCat(" princess preTTY  ANNE ", BigCatBreed.BOBCAT,75);
		assertEquals("Princess Pretty Anne", cat3.getName(), "Name of cat should be properly formatted");
		assertEquals(BigCatBreed.BOBCAT, cat3.getCatBreed());
		assertEquals(75, cat3.getWeight());

		cat3 = new BigCat("  crazy  chief  ", BigCatBreed.CHEETAH, BigCat.MAX_WEIGHT+1);
		assertEquals("Crazy Chief", cat3.getName(),"name should match Crazy Chief");
		assertEquals(0, cat3.getWeight());
		assertEquals(BigCatBreed.CHEETAH, cat3.getCatBreed());
		System.out.println("Original cat3: " + cat3);

		cat3 = new BigCat(" TomMY  ", BigCatBreed.CHEETAH, 165);
		assertEquals("Tommy", cat3.getName(),"name should match Tommy");
		assertEquals(165, cat3.getWeight());
		assertEquals(BigCatBreed.CHEETAH, cat3.getCatBreed());
		String str = "";

		str = cat3.toString();  // NOTE toString() has to be added to BigCat class
		String res = "Tommy";
		assertEquals(0, utils.MyUtils.numberLines(res));
		assertTrue(str.contains(res));
		res = "165";
		assertTrue(str.contains(res));
		res = "CHEETAH";
		assertTrue(str.contains(res));

		System.out.println("Newer modified cat3: " + cat3);
		System.out.println("****End of Constructors *****");
	}

	public void testAccessorsAndModifiers() {
		System.out.println("\n****Test Accessors/Modifiers *****");
		BigCat cat3 = new BigCat(" JoEY SMARTY   ", BigCatBreed.LION,175);
		System.out.println("cat3: " + cat3);
		assertEquals("Joey Smarty", cat3.getName());
		assertEquals(BigCatBreed.LION, cat3.getCatBreed());
		assertEquals(175, cat3.getWeight());
		System.out.println("\nTesting setCatBreed()");
		cat3.setCatBreed(BigCatBreed.TIGER);
		assertEquals(BigCatBreed.TIGER, cat3.getCatBreed());
		cat3.setCatBreed(BigCatBreed.HOUSE_CAT);
		assertEquals(BigCatBreed.HOUSE_CAT, cat3.getCatBreed());
		cat3.setCatBreed(BigCatBreed.TIGER);
		assertEquals(BigCatBreed.TIGER, cat3.getCatBreed());

		System.out.println("\nTesting setName()");
		cat3.setName(" ");
		assertEquals(BigCat.DEFAULT_NAME, cat3.getName());
		cat3.setName("");
		assertEquals(BigCat.DEFAULT_NAME, cat3.getName());
		cat3.setName("    	SPORT   ");
		assertEquals("Sport", cat3.getName());
		cat3.setName("CK ");
		assertEquals("Ck", cat3.getName());

		System.out.println("\nTesting setWeight()");
		cat3.setWeight(-400);
		assertEquals(0, cat3.getWeight());
		cat3.setWeight(1);
		assertEquals(1, cat3.getWeight());
		cat3.setWeight(BigCat.MAX_WEIGHT + 1);
		assertEquals(0, cat3.getWeight());
		cat3.setWeight(BigCat.MAX_WEIGHT);
		assertEquals(BigCat.MAX_WEIGHT, cat3.getWeight());

		String result = cat3.toString();
		assertTrue(result.contains(BigCat.MAX_WEIGHT + "")); // convert to string max weight
		assertTrue(result.contains("Ck"));
		assertTrue(result.contains("TIGER"));
		System.out.println("cat3: " + cat3);
		System.out.println("****End of Accessors/ModifiersTest *****");

	}
	public void testEqualsAndHashCode()
	{
		System.out.println("\n-------------Now testing methods equals and hashCode overrides in BigCat class");
		BigCat pet1 = new BigCat("Prince GEORGE",  BigCatBreed.LION, 300);
		BigCat pet2 = new BigCat("Tiny Teri", BigCatBreed.BOBCAT, 150);
		BigCat pet3 = new BigCat("Quick Sam",  BigCatBreed.CHEETAH, 180);
		BigCat pet4 = new BigCat("Prince GEORGE",  BigCatBreed.LION, 300);
		BigCat pet5 = new BigCat("Tiny Teri", BigCatBreed.BOBCAT, 150);
		BigCat pet6 = new BigCat("Quick Sam",  BigCatBreed.CHEETAH, 180);

		boolean result = pet1.equals(pet4);
		assertTrue(result);
		result = pet4.equals(pet1);
		assertTrue(result);
		result = pet2.equals(pet5);
		assertTrue(result);
		result = pet3.equals(pet6);
		assertTrue(result);
		int hc = pet1.hashCode();
		int hc2 = pet4.hashCode();
		assertEquals(hc, hc2);
		System.out.println("pet1 hash: " + pet1.hashCode() + "\npet4 hash: " + pet4.hashCode());
		assertEquals(pet2.hashCode(), pet5.hashCode());
		assertEquals(pet3.hashCode(), pet6.hashCode());
		BigCat pet7 = new BigCat();
		BigCat pet8 = new BigCat();
		result = pet7.equals(pet8);
		assertTrue(result);
		pet7.setName("Big Chief");
		assertFalse(pet7.equals(pet8));
		pet8.setName("big chief");
		assertEquals(pet7, pet8);
		pet7.setCatBreed(BigCatBreed.CHEETAH);
		assertFalse(pet7.equals(pet8));
		assertFalse(pet8.equals(pet7));
		assertFalse(pet7.hashCode() == pet8.hashCode());
		pet8.setCatBreed(BigCatBreed.CHEETAH);
		assertEquals(pet7, pet8);
		assertEquals(pet8, pet7);
		assertEquals(pet7.hashCode(), pet8.hashCode());
		pet7.setWeight(250);
		assertFalse(pet7.equals(pet8));
		assertFalse(pet8.equals(pet7));
		assertFalse(pet7.hashCode() == pet8.hashCode());
		pet8.setWeight(252);
		assertFalse(pet7.equals(pet8));
		assertFalse(pet8.equals(pet7));
		assertFalse(pet7.hashCode() == pet8.hashCode());
		pet8.setWeight(250);
		assertEquals(pet7, pet8);
		assertEquals(pet8, pet7);
		assertEquals(pet7.hashCode(), pet8.hashCode());
		System.out.println("pet7 hash: " + pet7.hashCode() + "\npet8 hash: " + pet8.hashCode());
		System.out.println("Now testing methods equals and hashCode overrides in BigCat class");
	}


	public void testBigCatUtils() {
		System.out.println("****Test Utils read/write *****");
		Scanner infile = null;
		PrintWriter outFile = null;
		String fileName = "";
		int catCount = 0;
		int expCount = 0;
		fileName = "bigcat1.txt";
		System.out.println("\n------------- Input file: " + fileName + "---------------");
		expCount = 1;
		catCount = 0;
		try {
			infile = new Scanner(new File(fileName));
			System.out.println("Opened successfully file: " + fileName + " for reading.\n");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error, cannot open file: " + fileName);
			assertTrue(false); // error caused on purpose as file is not found
			return;
		}
		while(infile.hasNext())
		{
			BigCat cat = BigCatUtilsImpl.readFromScanner(infile);
			if(cat != null)
			{
				catCount++;
				System.out.println("Just read cat # "+ catCount + " " + cat);
			}
		}
		assertEquals(expCount, catCount);
		fileName = "bigCat8.txt";
		String outputFile = "bigCat8Out.txt";
		System.out.println("\n------------- Input file: " + fileName + "---------------");
		System.out.println("\n------------- Output file: " + outputFile + "---------------\n");

		expCount = 8;
		catCount = 0;
		try {
			infile = new Scanner(new File(fileName));
			System.out.println("Opened successfully file: " + fileName + " for reading.\n");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error, cannot open file: " + fileName);
			assertTrue(false); // error caused on purpose as file is not found
			return;
		}
		try {
			outFile = new PrintWriter(new FileWriter(outputFile));
			System.out.println("Opened successfully file: " + outputFile + " for writing to.\n");
		}
		catch(IOException e)
		{
			System.out.println("Cannot open output file: " + outputFile + " exiting.");
			infile.close();
			assertTrue(false); // error caused on purpose as file is not found
			return;
		}
		while(infile.hasNext())
		{
			BigCat cat = BigCatUtilsImpl.readFromScanner(infile);
			if(cat != null)
			{
				catCount++;
				System.out.println("Just read cat # "+ catCount + " " + cat);
				BigCatUtilsImpl.writeToFile(outFile, cat);
			}
		}
		outFile.close();
		infile.close();
		assertEquals(expCount, catCount);

		fileName = "bigCat8Out.txt";
		expCount = 8;
		catCount = 0;
		try {
			infile = new Scanner(new File(fileName));
			System.out.println("\nOpened successfully file: " + fileName + " for reading.\n");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error, cannot open file: " + fileName);
			assertTrue(false); // error caused on purpose as file is not found
			return;
		}
		while(infile.hasNext())
		{
			BigCat cat = BigCatUtilsImpl.readFromScanner(infile);
			if(cat != null)
			{
				catCount++;
				System.out.println("Just read cat # "+ catCount + " " + cat + " from " + outputFile);
			}
		}
		infile.close();
		assertEquals(expCount, catCount);
		fileName = "bigCatEmpty.txt";
		System.out.println("\n------------- Input file: " + fileName + "---------------");

		expCount = 0;
		catCount = 0;
		try {
			infile = new Scanner(new File(fileName));
			System.out.println("Opened successfully file: " + fileName + " for reading.\n");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error, cannot open file: " + fileName);
			assertTrue(false); // error caused on purpose as file is not found
			return;
		}
		while(infile.hasNext())
		{
			BigCat cat = BigCatUtilsImpl.readFromScanner(infile);
			if(cat != null)
			{
				catCount++;
				System.out.println("Just read cat # "+ catCount + " " + cat);

			}
		}
		assertEquals(expCount, catCount);
		// TODO Auto-generated method stub
		fileName = "bigCatError.txt";
		System.out.println("\n------------- Input file: " + fileName + "---------------\n");


		expCount = 0;
		catCount = 0;
		try {
			infile = new Scanner(new File(fileName));
			System.out.println("Opened successfully file: " + fileName + " for reading.\n");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error, cannot open file: " + fileName);
			assertTrue(false); // error caused on purpose as file is not found
			return;
		}
		while(infile.hasNext())
		{
			BigCat cat = BigCatUtilsImpl.readFromScanner(infile);
			if(cat != null)
			{
				catCount++;
				System.out.println("Just read cat # "+ catCount + " " + cat);
			}
		}
		assertEquals(expCount, catCount);
		System.out.println("\n****End of Utils Test *****");
	}
	
	public void testAddContains() 
	{
		System.out.println("Now testing methods add/contains in BigCatList interface");
		BigCatList list = new BigCatListImpl();
		BigCat pet1 = new BigCat("Prince GEORGE",  BigCatBreed.LION, 300);
		BigCat pet2 = new BigCat("Tiny Teri", BigCatBreed.BOBCAT, 150);
		BigCat pet3 = new BigCat("Quick Sam",  BigCatBreed.CHEETAH, 180);
		assertEquals(0, list.getSize());
		int expNum = 0, retNum = 0;
		assertFalse(list.contains(pet1));
		assertFalse(list.contains(pet2));
		boolean result = list.add(pet1);
		assertTrue(result);
		assertEquals(1, list.getSize());
		assertTrue(list.contains(pet1));

		String retString = list.toString();
		assertTrue(retString.contains("LION"));
		assertTrue(retString.contains("Prince George"));
		assertTrue(retString.contains("300"));

		assertFalse(list.contains(pet2));
		assertFalse(list.contains(pet3));
		assertTrue(list.add(pet2));
		assertEquals(2,  list.getSize());
		assertTrue(list.contains(pet2));
		assertFalse(list.contains(pet3));
		assertTrue(list.contains(pet1));
		assertTrue(list.add(pet3));
		assertTrue(list.getSize()==3);
		assertFalse(list.add(pet1));
		assertFalse(list.add(pet2));
		assertFalse(list.add(pet3));

		BigCat pet4 = new BigCat("prince George", BigCatBreed.LION, 300);
		BigCat pet5 = new BigCat("Sophia marie", BigCatBreed.TIGER, 200);
		assertFalse(list.add(pet4));
		assertTrue(list.contains(pet4));
		expNum = 3;
		retNum = list.getSize();
		assertEquals(expNum, retNum);
		expNum = 3;
		retString = list.toString();
		retNum = utils.MyUtils.numberLines(retString);
		assertEquals(expNum, retNum);
		System.out.println("here is the current list, should have " + expNum + " BigCats\n" + list);
		assertTrue(list.add(pet5));
		expNum = 4;
		assertEquals(expNum, list.getSize());
		retString = list.toString();
		retNum = utils.MyUtils.numberLines(retString);
		assertEquals(expNum, retNum);
		retString = list.toString();
		assertTrue(retString.contains("LION"));
		assertTrue(retString.contains("TIGER"));
		assertTrue(retString.contains("Prince George"));
		assertTrue(retString.contains("Sophia Marie"));
		assertTrue(retString.contains("300"));
		assertTrue(retString.contains("200"));
		for(int i=0; i<11; i++)
		{
			BigCat aCat = new BigCat("", BigCatBreed.LION, i+400);
			list.add(aCat);
		}
		assertEquals(BigCatList.MAX_SIZE, list.getSize());
		assertFalse(list.add(new BigCat("TestFull", BigCatBreed.CHEETAH, 200)));
		System.out.println("Current Big Cats in list: \n" + list);
		System.out.println("END testing methods add/contains in BigCatList interface");
	}
	public void testFindGet() {
		System.out.println("\n-----------------------Now testing find() and get() in BigCatListImpl ");
		BigCat b1 = new BigCat("Prince GEORGE",  BigCatBreed.LION, 300);
		BigCat b2 = new BigCat("Tiny Teri", BigCatBreed.BOBCAT, 180);
		BigCat b3 = new BigCat("Quick Sam",  BigCatBreed.CHEETAH, 180);
		BigCat b4 = new BigCat("Freddy Fender", BigCatBreed.HOUSE_CAT, 9);	
		BigCat b5 = new BigCat("Alley Cat", BigCatBreed.HOUSE_CAT, 6);
		BigCatList list = new BigCatListImpl();  // create an empty list
		assertEquals(null, list.get(0));
		assertEquals(null, list.get(3));
		assertEquals(null, list.get(-1));
		assertEquals(null, list.get(-111));
		assertEquals(null, list.get(100));
		assertTrue(list.add(b4));
		assertEquals(b4, list.get(0));
		assertEquals(0, list.find(b4));
		assertTrue(list.add(b1));
		assertEquals(1, list.find(b1));
		assertTrue(list.add(b3));
		System.out.println("Current List of Big Cats\n" + list);
		assertEquals(2, list.find(b3));
		assertEquals(3, list.getSize());
		assertEquals(b3, list.get(2));
		assertEquals(b4, list.get(0));
		assertEquals(b1, list.get(1));
		assertEquals(null, list.get(8));
		assertEquals(null, list.get(15));
		assertEquals(null, list.get(-3));
		assertEquals(null, list.get(100));
		System.out.println(b4  + " at position: " + 0);
		System.out.println(b1 + "  at position: " + 1);
		System.out.println(b3  + " at position: " + 2);

		assertTrue(list.add(b5));
		System.out.println(b5  + " at position: " + 3);
		list.add(b5);
		list.add(b2);
		list.add(new BigCat("Biggie", BigCatBreed.LION, 300));
		System.out.println("Current List of Big Cats\n" + list);
		
		list = new BigCatListImpl();
		
		// try to add 1 more than allowed, should only add MAX_SIZE
		for(int i=0; i< BigCatList.MAX_SIZE+1; i++)
		{
			String name = "cat";
			name = name + ("" + i);
			list.add(new BigCat(name, BigCatBreed.LION, (200 + i*3)));
		}
		int numCats = list.getSize();
		assertEquals(BigCatList.MAX_SIZE, numCats);
		BigCat cat1 = list.get(15);
		assertEquals(null, cat1);
		cat1 = list.get(-1);
		assertEquals(null, cat1);
		
		cat1 = new BigCat("cat14", BigCatBreed.LION, 242);
		BigCat cat2 = list.get(14);
		assertEquals(cat1, cat2);
		System.out.println("End Testing find/get methods ");	
	}
}

