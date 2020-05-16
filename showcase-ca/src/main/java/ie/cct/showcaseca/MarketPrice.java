package ie.cct.showcaseca;
/*
 *  This class will contain the market prices for cow, pig or chicken
 *  According the weight is assign a price
 */
import java.util.ArrayList;

public class MarketPrice {
	
	ArrayList<Value> marketValues;

	public MarketPrice() {
		
		marketValues = new ArrayList<Value>();
		
		marketValues.add(new Value("cow",500,300)); // Adding Value Price, type, weight, price
		marketValues.add(new Value("pig",250,100));
		marketValues.add(new Value("chicken",5,0.5));
	}
	
	// The following function will return true whenever the weight of the animal type entered is in the list price market 
	public boolean isSold(String type,double weight) {
		
		boolean find=false;
		int i=0;
		while(!find && i < marketValues.size()) { // Looking for the animal by type in all list
			
			// If the animal has the correct weight, established in the price list, it will return true
			if(type.equals(marketValues.get(i).getAnimal()) && (weight > marketValues.get(i).getWeight() ||  weight == marketValues.get(i).getWeight()))
				find=true;
			i++;
		}		
		return find;
	}
	
	//this function will return the price as long as the weight of the animal is defined in the price list, otherwise it will return 0
	public double getPrice(String type,double weight) {
		
		double price=0;
		int i=0;
		while(price == 0 && i < marketValues.size()) {
			
			if(type.equals(marketValues.get(i).getAnimal()) && (weight > marketValues.get(i).getWeight() ||  weight == marketValues.get(i).getWeight()))
				price = marketValues.get(i).getPrice();
			i++;
		}		
		return price;
	}

}

