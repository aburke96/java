package census;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class WordCensusImpl_Burke implements WordCensus {
	
	private Set <String> wordSet;
	private Map <String, Integer> wordToCountMap;
	
	// Pre: wordList must not be null
	// Post: constructor initializes the wordToCountMap and 
	// wordSet objects based on the given wordList parameter.
	public WordCensusImpl_Burke(List<String> wordList) {
	this.wordToCountMap = new HashMap<>();
		
		for(String word : wordList) {
			if(wordToCountMap.containsKey(word)) {
				int oldCount = wordToCountMap.get(word);
				wordToCountMap.put(word, oldCount + 1);
			} else {
				wordToCountMap.put(word, 1);
			}
		}
		this.wordSet = new HashSet<String>(wordList);
	}
	
	// Pre: wordToCountMap object must not be null
	// Post: returns the count of the given word parameter 
	// from the wordToCountMap object
	@Override
	public int getCount(String word) {
		if(this.wordToCountMap.containsKey(word)) {
			return wordToCountMap.get(word);
		} else {
			return 0;
		}
	}
	
	// Pre: wordSet object must not be null
	// Post: returns a set of distinct words from the wordSet object
	public Set<String> getDistinctWords() 
	{
		return this.wordSet;
	}

	// Pre: i must be a positive integer
	// Pre: wordToCountMap object must not be null
	// Post: returns the word with the given rank 
	// based on the count of each word in the wordToCountMap object
	// Post: if multiple words with the same count at the given rank, 
	// the method returns the first word based on lexicographic order
	@Override
	public String getWordWithRank(int i) {
		assert(i > 0);
		String retVal = "";
		if(wordToCountMap.size() == 0)
		{
			return retVal;
		}
		else if(wordToCountMap.size() == 1)
		{
			retVal = wordToCountMap.keySet().iterator().next();
			return retVal;
		}

		LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
		this.wordToCountMap.entrySet()
	    .stream()
	    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
	    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		
		ArrayList<String> equalCountStringsAtRank = new ArrayList<String>();
		
		Collection<Integer> wordToCountMapValues = wordToCountMap.values();
		
		Set<Integer>wordToCountMapValuesSet = new HashSet<>(wordToCountMapValues);
		
		int numEntriesBeforeRank = 0;
		if(wordToCountMapValuesSet.size() == 1) {
			for(Map.Entry<String, Integer> entry : sortedMap.entrySet()) 
			{
				equalCountStringsAtRank.add(entry.getKey());
			}
		}
		else
		{
			int numEntries = 0;
			int valueToBreakTies = 0;
		    for(Map.Entry<String, Integer> entry : sortedMap.entrySet()) 
		    {
		    	numEntries += 1;
		    	if(numEntries == i)
		    	{
		    		numEntriesBeforeRank = 0;
		    		valueToBreakTies = entry.getValue();
		    		for(Map.Entry<String, Integer> subEntry : sortedMap.entrySet())
		    		{
		    			if(subEntry.getValue() == valueToBreakTies)
		    			{
		    				equalCountStringsAtRank.add(subEntry.getKey());
		    			}
		    			else if(subEntry.getValue() < valueToBreakTies)
		    			{
		    				break;
		    			} else {
		    				numEntriesBeforeRank += 1;
		    			}
		    		}
		    		break;
		    	}
		    }    
		}
	    
	    if(equalCountStringsAtRank.size() == 1)
	    {
	    	return equalCountStringsAtRank.get(0);
	    }
	   
	    Collections.sort(equalCountStringsAtRank);
	    retVal = equalCountStringsAtRank.get(i - numEntriesBeforeRank - 1);
		
		return retVal;
	}
}
