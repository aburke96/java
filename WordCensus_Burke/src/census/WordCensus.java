package census;

import java.util.Set;

public interface WordCensus {
	//pre: nothing
	//part of post: rv >= 0
	public int getCount(String word);
	//part of pre: i > 0
	//part of post: i < getDistinctWords().size() ==> getCount(getWordWithRank(i)) >= getCount(getWordWithRank(i + 1))
	//part of post: [((i < j) && (word_i == getWordWithRank(i)) && (word_j == getWordWithRank(j))
	         // && (getCount(word_i) == getCount(word_j))] ==> (getWordWithRank(i) < getWordWithRank(j))
	public String getWordWithRank(int i);
	//part of post: rv != null
	public Set<String> getDistinctWords();
}
