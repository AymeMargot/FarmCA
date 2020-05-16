package ie.cct.showcaseca;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowCaseCaController {
	
	// Declaring my animal list
	ArrayList<Animal> farmStock;
	// Declaring my list price will define the price of each animal according to the animal weight
	MarketPrice priceList; 
	
	public ShowCaseCaController() {		
		farmStock = new ArrayList<Animal>();
		priceList = new MarketPrice();
	}
	
	/*
	 *  1.- Add Animal into the list
	 */
	
	@PostMapping("add-animal")
	public SuccessResponse addAnimal(@RequestBody Animal animal) {	
		
		// Validating the data enter by the user, only cow, pig and chicken type are allowed
		if(animal.isAllowed()) {
			
			farmStock.add(animal);	// Adding animal on the farm stock
			return new SuccessResponse("Animal "+ animal.getType() + ", "+ animal.getWeight()+ " Added");
		}
		else 
			return new SuccessResponse("Only pig, cow and chicken are allowed into the farm, please verify type");
	}
		
	/*
	 *	2.- Calculate the average weight of each type of animal
	 */
	
	@GetMapping("average-by-type")
	public SuccessResponse averageByType(@RequestParam(required=true) String type) {
		
		// Validating the data entered by the user
		if(!type.equals("cow") && !type.equals("pig") && !type.equals("chicken"))
			return (new SuccessResponse("Only pig, chicken and cow are allowed, please try again"));
		
		double sumWeight=0; // in this variable will store the sum of each animal weight
		int qty=0; // this variable will count the animals with the type the user entered
		if(farmStock.size() == 0) // when the farm stock is empty
			throw new RuntimeException("No animal in the farm stock"); // throw an exception error
		
		else {			
			
			for(int i=0;i< farmStock.size();i++) {//Verifying every animal into the farm stock
				// comparing if there are animals into the farm with the same type the user entered
				if(type.equals(farmStock.get(i).getType())) // get a type the user entered from the farm
					sumWeight += farmStock.get(i).getWeight(); // adding the weight whenever the animal is the same type
					qty++; // getting the amount of animals
				}			
			}
			
			if(qty == 0)// in case of any animal the same type was found
				throw new RuntimeException("No animal "+ type+" in the stock");
			else
				return (new SuccessResponse("The average weight of "+ type + " is "+ sumWeight/qty));
	}
	/*
	 * 3.- How many animals of each type can be sold 
	 */
	
	@GetMapping("animals-sold")
	public SuccessResponse AnimalsToBeSold() {	
		
		if(farmStock.size() == 0) // when the farm stock is empty
			throw new RuntimeException("No animal in the list");
		
		// declaring  counters for every type of animal
		int cows =0;
		int pigs = 0;
		int chickens = 0;
		
		for(int i=0;i < farmStock.size();i++) {
			
			// checking if the animal has the right weight to be consider on sealing 
			if(priceList.isSold(farmStock.get(i).getType(),farmStock.get(i).getWeight())) {// isSold is a function which validate the weight matching with the market price
				
				if("cow".equals(farmStock.get(i).getType())) // detecting the type of animal in order to increase the right counter
					cows++;
				else if("pig".equals(farmStock.get(i).getType()))
						pigs++;
					else if("chicken".equals(farmStock.get(i).getType()))
							chickens++;
				
			}			
		}		
		return (new SuccessResponse("cows: "+cows+" pigs: "+pigs+" chicken: "+chickens));
	}
	
	/*
	 * 4.- What is the current value of the full farm stock: That is, the price of all the animals
that can be sold right now.
	 */
	
	@GetMapping("full-price-farm")
	public SuccessResponse fullValue() {
		
		if(farmStock.size() == 0) // when the list is empty
			throw new RuntimeException("No animal in the farm");
		// defining counters for every type of animal
		double totalPrice = 0;
		
		for(int i=0;i < farmStock.size();i++) {
			// checking if the animal has the right weight to be consider on sealing, getPrice event gets the price if the animal has the right weight 
			totalPrice += priceList.getPrice(farmStock.get(i).getType(), farmStock.get(i).getWeight());			
		}		
		return (new SuccessResponse("The current value of the full farm stock is: "+totalPrice+ "includes only cows,pigs and chickens"));
	}
	
	/*
	 * 5.- What is the current value of the farm assuming the price of each animal is set by a
parameter in the HTTP request. This is an example:
- http://localhost:8080/currentValue?cow=350&pig=120&chicken=1
	 */
	@GetMapping("currentValue")
	public SuccessResponse fullValueFarm(@RequestParam(required=true) double cow, @RequestParam(required=true) double pig, @RequestParam(required=true) double chicken) {
		return (new SuccessResponse("The current value of the full farm stock is: "+ (cow+pig+chicken)));
	}
	
}
