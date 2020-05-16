package ie.cct.showcaseca;
/*
 * This class contains the animals will be added into the farm stock
 */
public class Animal {
	
	private String type;
	private double weight;
			
	public Animal(String type, double weight) {		
		this.type = type;
		this.weight = weight;		
	}
	
	public Animal() {				
	}
	
	// Declaring setters and getters
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(float weight) {
		this.weight = weight;
	} 	
	
	// In order to control the animal type, is needed to validate the type of animal allowed
	public boolean isAllowed() {
		
		// the function will return true whenever the animal is cow, pig or chicken
		return (this.getType().equals("cow") || this.getType().equals("pig") || this.getType().equals("chicken"));
	}

	
	
}
