package bigcat;

import utils.MyUtils;

public class BigCat {
	public static final String DEFAULT_NAME = "$$$$";
	public static final int MAX_WEIGHT = 2000;
	
	private String name;
	private BigCatBreed breed;
	private int weight;
	
	public BigCat() {
		this.name = DEFAULT_NAME;
		this.breed = BigCatBreed.HOUSE_CAT;
		this.weight = 0;
	}
	
	public BigCat(String name, BigCatBreed breed, int weight) {
		if (name.trim().isEmpty()) {
			this.name = DEFAULT_NAME;
		} else {
			this.name = MyUtils.properFormat(name);
		}
		
		this.breed = breed;
		
		if (weight >= 0 && weight <= 2000) {
			this.weight = weight;
		} else {
			this.weight = 0;
		}
		
	}
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BigCat other = (BigCat) obj;
		if (breed != other.breed)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	public String getName() {
		return this.name;
	}
	
	public BigCatBreed getCatBreed() {
		return this.breed;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public void setName(String catName) {
		if (catName.trim().isEmpty()) {
			this.name = DEFAULT_NAME;
		} else {
			this.name = MyUtils.properFormat(catName);
		}
		
	}
	
	public void setCatBreed(BigCatBreed aBreed) {
		this.breed = aBreed;
	}
	
	public void setWeight(int aWeight) {
		if (aWeight >= 0 && aWeight <= 2000) {
			this.weight = aWeight;
		} else 
		{
			this.weight = 0;
		}
		
}
	
	public String toString() {
		return "This cat's name is " + this.name + ". Its breed is " + this.breed + " and its weight is " + this.weight + ".";
	}
}

