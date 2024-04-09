package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

import census.WordCensus;

public class WordCensus_TestCasesSubsetForStudents_3_ADVANCED extends WordCensus_TestCasesSubsetForStudents_2_BASIC
{
	@Points(5)
	@Test(timeout=60000)
	public void warAndPeaceTest_60sec()
	{
		warAndPeaceTest();
	}
	
	@Points(10)
	@Test(timeout=15000)
	public void warAndPeaceTest_15sec()
	{
		warAndPeaceTest();
	}
	
	@Points(20)
	@Test(timeout=2000)
	public void warAndPeaceTest_2sec()
	{
		warAndPeaceTest();
	}
	
	public void warAndPeaceTest() {
		Scanner warAndPeaceScanner = null;
		try {
			warAndPeaceScanner = getWarAndPeaceScanner();
		} catch (IOException e) {
			fail("War and Peace not read!");
		}
		List<String> wordListLowercase = getWordListLowercase(warAndPeaceScanner);
		WordCensus warAndPeaceCensus = getWordCensus(wordListLowercase);
		assertEquals(288, warAndPeaceCensus.getCount("war"));
		assertEquals(109, warAndPeaceCensus.getCount("peace"));
		assertEquals(22063, warAndPeaceCensus.getCount("and"));
		assertEquals(10454, warAndPeaceCensus.getCount("a"));
		assertEquals(34341, warAndPeaceCensus.getCount("the"));
		assertEquals(0, warAndPeaceCensus.getCount("tomato"));
		assertEquals(1612, warAndPeaceCensus.getCount("an"));

		assertEquals(21642, warAndPeaceCensus.getDistinctWords().size());
	}
	
	//********************** SUPPORT *******************************************************************************************
	protected Scanner getWarAndPeaceScanner() throws IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("resources/WarAndPeace.txt");
		assert input != null : "input is null! : Check that the resources folder is on the classpath, the file name is correct, and the file is in the resources folder";
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
		Scanner warAndPeaceScanner = new Scanner(bufferedReader);
		warAndPeaceScanner.useDelimiter("(" + warAndPeaceScanner.delimiter().pattern() + "|[.!,?\"\'])+");
		return warAndPeaceScanner;
	}
}
