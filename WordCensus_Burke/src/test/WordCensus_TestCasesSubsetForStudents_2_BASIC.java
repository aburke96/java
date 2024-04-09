package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

import census.WordCensus;
import census.WordCensusImpl_Burke;

public class WordCensus_TestCasesSubsetForStudents_2_BASIC extends WordCensus_TestCasesSubsetForStudents_1_ENVIRONMENT
{
	private static Set<String> getSet(String... strings)
	{
		return new HashSet<String>(Arrays.asList(strings));
	}
	
	@Points(20)
	@Test(timeout=2000)
	public void oneWord_getCountTest() {
		final String WORD = "aardvark";
		Scanner scanner = new Scanner(WORD);
		List<String> wordListLowercase = getWordListLowercase(scanner);
		WordCensus wordCensus = getWordCensus(wordListLowercase);

		assertEquals(1, wordCensus.getCount(WORD));
	}
	
	@Points(20)
	@Test(timeout=2000)
	public void oneWord_getDistinctWordsTest() {
		final String WORD = "aardvark";
		Scanner scanner = new Scanner(WORD);
		List<String> wordListLowercase = getWordListLowercase(scanner);
		WordCensus wordCensus = getWordCensus(wordListLowercase);
		
		Set<String> expectedDistinctWords = getSet(WORD);
		Set<String> actualDistinctWords = wordCensus.getDistinctWords();
		assertEquals(expectedDistinctWords, actualDistinctWords);
	}

	@Points(20)
	@Test(timeout=2000)
	public void twoWord_getCountTest()
	{
		final String AARDVARK = "aardvark";
		final String TURTLE = "turtle";
		final String GIRAFFE = "giraffe";
		final String WORDS = AARDVARK + " " + AARDVARK + " " + TURTLE;
		
		final int EXPECTED_AARDVARK_COUNT = 2;
		final int EXPECTED_TURTLE_COUNT = 1;
		final int EXPECTED_GIRAFFE_COUNT = 0;
		
		Scanner scanner = new Scanner(WORDS);
		List<String> wordListLowercase = getWordListLowercase(scanner);
		WordCensus wordCensus = getWordCensus(wordListLowercase);
		assertEquals(EXPECTED_AARDVARK_COUNT, wordCensus.getCount(AARDVARK));
		assertEquals(EXPECTED_TURTLE_COUNT, wordCensus.getCount(TURTLE));
		assertEquals(EXPECTED_GIRAFFE_COUNT, wordCensus.getCount(GIRAFFE));
	}

	@Points(20)
	@Test(timeout=2000)
	public void twoWord_getDistinctWordsTest()
	{
		final String AARDVARK = "aardvark";
		final String TURTLE = "turtle";
		final String GIRAFFE = "giraffe";
		final String WORDS = AARDVARK + " " + AARDVARK + " " + TURTLE;
		
		Scanner scanner = new Scanner(WORDS);
		List<String> wordListLowercase = getWordListLowercase(scanner);
		WordCensus wordCensus = getWordCensus(wordListLowercase);
		
		Set<String> EXPECTED_DISTINCT_WORDS = getSet(AARDVARK, TURTLE);
		Set<String> actualDistinctWords = wordCensus.getDistinctWords();
		assertEquals(EXPECTED_DISTINCT_WORDS, actualDistinctWords);
	}
	
	@Points(20)
	@Test(timeout=2000)
	public void caseSensitivityTest() {
		final String a = "a";
		final String A = "A";
		final String b = "b";
		final String B = "B";
		final String c = "c";
		final String C = "C";
		final String d = "d";
		final String D = "D";
		final String e = "e";
		final String E = "E";
		List<String> wordList = Arrays.asList(new String[] {a, A, A, b, b, b, B, B, B, B, c, C, C, d, d, d, D, D, D, D, e, e, e, e, e});
		WordCensus wordCensus = getWordCensus(wordList);

		final int EXPECTED_a_COUNT = 1;
		final int EXPECTED_A_COUNT = 2;
		final int EXPECTED_b_COUNT = 3;
		final int EXPECTED_B_COUNT = 4;
		final int EXPECTED_c_COUNT = 1;
		final int EXPECTED_C_COUNT = 2;
		final int EXPECTED_d_COUNT = 3;
		final int EXPECTED_D_COUNT = 4;
		final int EXPECTED_e_COUNT = 5;
		final int EXPECTED_E_COUNT = 0;

		assertEquals(EXPECTED_a_COUNT, wordCensus.getCount(a));
		assertEquals(EXPECTED_A_COUNT, wordCensus.getCount(A));
		assertEquals(EXPECTED_b_COUNT, wordCensus.getCount(b));
		assertEquals(EXPECTED_B_COUNT, wordCensus.getCount(B));
		assertEquals(EXPECTED_c_COUNT, wordCensus.getCount(c));
		assertEquals(EXPECTED_C_COUNT, wordCensus.getCount(C));
		assertEquals(EXPECTED_d_COUNT, wordCensus.getCount(d));
		assertEquals(EXPECTED_D_COUNT, wordCensus.getCount(D));
		assertEquals(EXPECTED_e_COUNT, wordCensus.getCount(e));
		assertEquals(EXPECTED_E_COUNT, wordCensus.getCount(E));
	}
	
	@Points(20)
	@Test(timeout=2000)
	public void threeDistinctWord_getWordWithRankTest()
	{
		final String AARDVARK = "aardvark";
		final String GIRAFFE = "giraffe";
		final String TURTLE = "turtle";
		
		List<String> wordList = Arrays.asList(new String[] {GIRAFFE, AARDVARK, TURTLE});
		
		final String EXPECTED_WORD_RANK_1 = AARDVARK;
		final String EXPECTED_WORD_RANK_2 = GIRAFFE;
		final String EXPECTED_WORD_RANK_3 = TURTLE;
		
		WordCensus wordCensus = getWordCensus(wordList);
		assertEquals(EXPECTED_WORD_RANK_1, wordCensus.getWordWithRank(1));
		assertEquals(EXPECTED_WORD_RANK_2, wordCensus.getWordWithRank(2));
		assertEquals(EXPECTED_WORD_RANK_3, wordCensus.getWordWithRank(3));
	}

	//********************** SUPPORT *******************************************************************************************	
	protected static List<String> getWordListLowercase(Scanner wordScanner)
	{
		List<String> wordList = new ArrayList<String>();
		while(wordScanner.hasNext())
		{
			wordList.add(wordScanner.next().toLowerCase());
		}
		return wordList;
	}
	
	protected WordCensus getWordCensus(List<String> wordList)
	{
		return new WordCensusImpl_Burke(wordList);
	}
}
