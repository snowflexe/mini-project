import java.util.Scanner;

public class Process {
	
	public static void FishSize(String size,String type) {
		
		Scanner sc = new Scanner(System.in);
		
		String sizeDesc ="";
		
		System.out.println ("Enter size of the fish : ");
		size = sc.nextLine();
		System.out.println ("Enter type of the fish : ");
		type = sc.nextLine();
		
		
		if (size == "small" || size == "Small" ) {
			
			sizeDesc = "Small Fish";
			
		}
		else if (size == "medium") {
			sizeDesc = "Medium Fish";
		}
		else if (size == "large") {
			sizeDesc = "large Fish";
		}
		else {
			System.out.println ("Theres only small/medium/large");

		}
		
	}
	public static float FishCalc() {
		Scanner sc = new Scanner(System.in);
		
		float weight=0;
		double price=0;
		String type, typeDesc = "";

		System.out.println ("Enter average weight for per fish (kg) : ");
		weight = sc.nextFloat();
		System.out.println ("Enter type of the fish : ");
		type = sc.nextLine();
		
		if (weight >= 0.2 && weight <= 1) {
		    price = 5;
		    
			switch (type) {
			case "Freshwater Fish":
				typeDesc = "Freshwater Fish";
				
				break;
			case "Deep-sea Fish":
				typeDesc = "Deep-sea Fish";
				price = price + (price*0.1);
				break;
			}
		}
		else if (weight >= 1 && weight <= 5) {
		    price = 10;
		    
			switch (type) {
			case "Freshwater Fish":
				typeDesc = "Freshwater Fish";
				
				break;
			case "Deep-sea Fish":
				typeDesc = "Deep-sea Fish";
				price = price + (price*0.2);
				break;
			}
		}
		else if (weight >= 5 && weight <= 10) {
			price = 20;
			
			switch (type) {
			case "Freshwater Fish":
				typeDesc = "Freshwater Fish";
				
				break;
			case "Deep-sea Fish":
				typeDesc = "Deep-sea Fish";
				price = price + (price*0.3);
				break;
			}
		}
		
		return weight;
	}
	
	public static float Total_Weight(float TotalWeight,float weight) {
		Scanner sc = new Scanner(System.in);
		
		int tolFish;

		System.out.println ("Enter total amount of fish : ");
		tolFish = sc.nextInt();
		
		TotalWeight = tolFish*weight;
		
		return TotalWeight;
		
		
	}
	
	public static float PriceCalc (float TotalPrice,float TotalWeight,float price) {


		TotalPrice = TotalWeight*price;
		
		return TotalPrice;
	
	}
	
	public static double Discount (double TotalPrice) {
		Scanner sc = new Scanner(System.in);
		
	if (TotalPrice >= 200) {
		TotalPrice = TotalPrice *0.9;
		System.out.print("You got discount for 10% off! ");
	}
	
	return TotalPrice;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
