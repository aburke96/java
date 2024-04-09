package rental;

import java.text.DecimalFormat;
// Note this is an abstract class, only used for deriving other classes
public abstract class Rental {
	public static final String DEFAULT_ID = "0000";
	public static final double MAX_COST_PER_DAY = 99999.0;
	private String id;
	private double costPerDay;
	
	//set this instance to default values
	public Rental()
	{
		this.id = DEFAULT_ID;
		this.costPerDay = 0.0;
	}
	//set this instance to default values then try setters for validity
	public Rental(String aId, double aCostPerDay)
	{
		this.id = DEFAULT_ID;
		this.costPerDay = 0.0;
		this.setCostPerDay(aCostPerDay);
		this.setId(aId);
	}
	//override of toString
	public String toString()
	{
		DecimalFormat df = new DecimalFormat("###,##0.00");
		String formatCost = df.format(this.costPerDay);
		String ret = "";
		ret += "Id: " + this.id + " cost per day:" + formatCost + " ";
		return ret;
	}
	//returns this instance's id
	public String getId()
	{
		return this.id;
	}
	//returns this instance's cost per day
	public double getCostPerDay()
	{
		return this.costPerDay;
	}
	
	// does not setId if aId is INVALID
	// receives : id to set value to
	// returns : nothing
	// if aId is valid then modify this instance to aId, otherwise no change
	public void setId(String aId)
	{
		int len = aId.length();
		boolean valid = utils.MyUtils.isValid(aId);
		if(len == 4 && valid)
			this.id = aId;
		
	}
	
	// does not change costPerDay if out of range!!!
	// receives: aCost - value to change this instance's cost to
	// returns: nothing
	// task:" if aCost is in range, modify this instance's cost otherwise no change
	public void setCostPerDay(double aCost)
	{	
		if(aCost >=0.0 && aCost <= MAX_COST_PER_DAY)
		{
			this.costPerDay = aCost;
		}
	}
	
	//override of equals - tests that both id and cost match
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(!( obj instanceof Rental))
		{
			return false;
		}
		Rental rental = (Rental) obj;
		if(! this.getId().equals(rental.getId())) 
		{
			return false;
		}
		double diff = 0.0001;
		double sub = Math.abs(this.getCostPerDay()-rental.getCostPerDay());
		if(sub > diff)
		{
			return false;
		}
		return true;
	}
	
	//override of hashCode - required due to equals override, uses both id and cost
	// as does equals so that 2 hashcodes match, then equals is also true
	public int hashCode()
	{
		int mult = 19;
		int res = 31;
		res = res * mult + this.getId().hashCode();
		res = res * mult + (int)(this.getCostPerDay()); // note convert double to int
		return res;		
	}

}
