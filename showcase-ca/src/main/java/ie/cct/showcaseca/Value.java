package ie.cct.showcaseca;
/*
 * Value is the unit that will contain the price list, it is defined by animal, weight and price
 */
public class Value {
	
	private String animal;
	private double price;
	private double weight;
	
	 public Value(String animal, double price, double weight) {
		this.animal = animal;
		this.price = price;
		this.weight = weight;
	}
	 
	// Setters and getters
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	

}
