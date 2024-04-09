package tournament;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BracketImpl_Burke<P> extends BracketAbstract<P>
{
	public BracketImpl_Burke(List<P> participantMatchups)
	{
		super(participantMatchups);
	}
	
	@Override
	public int getMaxLevel()
	{
		double logBase2 = (Math.log10(predictions.size())/Math.log10(2));
        int rv = (int)logBase2;
        return rv;
	}

	@Override
	public Set<Set<P>> getGroupings(int level)
	{
		assert(level >= 0 && level <= getMaxLevel()):"Level not in bounds";

        int size = predictions.size();
        Set<Set<P>> setOfSets = new HashSet<Set<P>>();

        if(level == 0)
        {
            for(int index = size - 1; index >= 0; index--)
            {
                Set<P> group0 = new HashSet<P>();
                group0.add(predictions.get(index));
                setOfSets.add(group0);
            }
            //assert(setOfSets.size() == 16);
        }

        else if(level == getMaxLevel())
        {
            setOfSets.add(getParticipants());
            assert(setOfSets.size() == 1);
        }

        else
        {
            double power = (double) level;
            int groupNSetSize = (int) Math.pow(2.0, power);

            Set<Set<P>> groupNSet = new HashSet<Set<P>>();
            int start = (int) Math.pow(2,  getMaxLevel() - level) -1;
            int end = (start * 2) + 1;
            for(int i = start; i < end; i++)
            {
                List<P> insideSet = new ArrayList<P>();
                insideSet = getInsideSet(i, groupNSetSize);
                Set<P> subGroup = new HashSet<P>();
                for(int k = 0; k < insideSet.size(); k++)
                {
                    subGroup.add(insideSet.get(k));
                }
                groupNSet.add(subGroup);
            }
            setOfSets = groupNSet;
        }

        return setOfSets;
	}

	@Override
	public Set<P> getViableParticipants(Set<P> grouping)
	{
		assert(grouping != null);
        assert(grouping.size() > 0);
        assert!((grouping.contains(null)));

        int size = grouping.size();
        int level = (int)(Math.log(size)/Math.log(2));

        Set<Set<P>> tempSet = new HashSet<Set<P>>();
        tempSet = getGroupings(level);

        assert(tempSet.contains(grouping));

        Set<P> viableParticipants = new HashSet<P>();

        if(!(grouping.contains(predictions.get(0))) && predictions.get(0).equals(null))
        {
            return viableParticipants;
        }

        if(!(predictions.contains(null)) && (grouping.contains(predictions.get(0))))
        {
            viableParticipants.add(predictions.get(0));
            return viableParticipants;
        }

        for(int k = (int) Math.pow(2,  getMaxLevel() - level); k < (int) Math.pow(2, (getMaxLevel() - level) + 1); k++)
        {
            boolean win = winOrLose(k);
            if(grouping.contains(predictions.get(k)) && win)
            {
                viableParticipants.add(predictions.get(k));
            }
        }

        return viableParticipants;
	}
	
	@Override
	public void setWinCount(P participant, int winCount)
	{
		assert(participant != null):"Participants can't be null";
        assert(getGroupings(getMaxLevel()).iterator().next().contains(participant));
        assert(winCount >= 0):"Participants winCount cant be < 0";
        assert(winCount <= getMaxLevel()):"winCount cant be higher than levels";

        int index = getParticipantIndex(participant);
        for(int i = 0; i < winCount; i++)
        {
            int parentIndex = getParentIndex(index);
            predictions.remove(parentIndex);
            predictions.add(parentIndex, participant);
            index = parentIndex;

            if(winCount != 4)
            {
                int pointer = 0;
                for(int k = getMaxLevel(); k > winCount; k++)
                {
                    boolean gotLeftChild = false;
                    if(predictions.get(pointer).equals(participant))
                    {
                        predictions.remove(pointer);
                        predictions.add(pointer, null);
                        if(index % 2 == 0)
                        {
                            pointer = leftChild(pointer);
                            gotLeftChild = true;
                        }
                        else
                        {
                            pointer = rightChild(pointer);
                        }
                    }
                    else
                    {
                        break;
                    }
                    if(gotLeftChild)
                    {
                        pointer = leftChild(pointer);
                    }
                    else
                    {
                        pointer = rightChild(pointer);
                    }
                }
            }
        }
	}
	
	//Find two groupings a and b at a lower level such that a U b = grouping with a INT b = empty
/**	private Set<Set<P>> getSubordinateGroupings(Set<P> grouping)
	{
		assert grouping.size() > 1 : "grouping.size() = " + grouping.size() + " <= 1!: grouping = " + grouping;
		throw new RuntimeException("NOT IMPLEMENTED!");
	} **/
	
	private Set<P> getParticipants()
    {
        Set<P> participants = new HashSet<P>();
        for(int i = predictions.size() - 1; i >= 0; i--)
        {
            participants.add(predictions.get(i));
        }
        assert(participants.size() < predictions.size());
        return participants;
    }

	  private List<P> getInsideSet(int indexOfParent, int groupSize)
	    {
	        assert(groupSize % 2 == 0);
	        assert (groupSize > 0);

	        List<P> insideSet = new ArrayList<P>();

	        int childOne = (indexOfParent * 2) + 1;
	        int childTwo = (indexOfParent * 2) + 2;
	        if(groupSize != 2)
	        {
	            insideSet = getInsideSet(childOne, groupSize/2);
	            List<P> recursive = new ArrayList<P>();
	            recursive = getInsideSet(childTwo, groupSize/2);
	            for(int i = 0; i < recursive.size(); i++)
	            {
	                insideSet.add(recursive.get(i));
	            }
	        }
	        else
	        {
	            insideSet.add(predictions.get(childOne));
	            insideSet.add(predictions.get(childTwo));
	        }
	        return insideSet;
	    }
	  
	  private boolean winOrLose(int index)
	    {
	        boolean rv = false;
	        int parentIndex = getParentIndex(index);
	        if(predictions.get(parentIndex).equals(predictions.get(index)));
	        {
	            rv = true;
	        }
	        return rv;
	    }

	    private static int getParentIndex(int childIndex)
	    {
	        int parentIndex = (childIndex -1)/2;
	        return parentIndex;
	    }
	  
	private int getParticipantIndex(P participant)
	{
		assert(predictions.contains(participant));
        int rv = -1;
        int index = predictions.size() - 1;
        while(rv == -1)
        {
            if(predictions.get(index).equals(participant))
            {
                rv = index;
            }
            index--;
        }
        assert(predictions.get(rv).equals(participant));
        return rv;
	}

/**	private Set<P> getGrouping(P member, int level)
	{
		throw new RuntimeException("NOT IMPLEMENTED!");
	} **/
	
	private static int leftChild(int parentIndex)
    {
        int leftChildIndex = (parentIndex * 2) + 1;
        return leftChildIndex;
    }

    private static int rightChild(int parentIndex)
    {
        int rightChildIndex = (parentIndex * 2) + 2;
        return rightChildIndex;
    }
}

