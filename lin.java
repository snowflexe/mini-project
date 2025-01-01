import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class lin {
	
	public static boolean checkUser(String username,String password) {
		
		try (BufferedReader reader = new BufferedReader(new FileReader("userinfo.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(username + ":" + password)) {
                	//yes
                    return true;
                }

            }
		}
        catch (FileNotFoundException e) {
        System.out.println("No users registered yet. Please register first.");
        }
		catch(IOException e) {
			System.out.println("An error occurred.");
            e.printStackTrace();
		}
		//no
		return false;
	}
	
	
	public static boolean newUser(String newusername,String newpassword) {
        try (BufferedReader reader = new BufferedReader(new FileReader("userinfo.txt"))){
            String line ;
            while ((line = reader.readLine())!= null) {
                if (line.startsWith(newusername + ":")) {
                    System.out.println("Username already exists. Please try a different one.");
                    return false;
                }
            }
        } 
        catch (FileNotFoundException e) {
            //Proceed with registration.
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
        
        
        try (FileWriter writer = new FileWriter("userinfo.txt", true)){
            writer.write(newusername + ":" + newpassword + "\n");
            System.out.println("User registered successfully!");
            System.out.println("Please restart the program to log in.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
        return true;
    }
	
	public static void selling(String username) {
		
		Scanner input = new Scanner(System.in);
		
		double fishWeight = 0.0;
		double priceperkg = 0.0;
		double totalprice = 0.0;
		String fishtype = "";
		int species = 0;
		
		String[] speciesname = new String[10];
		
		System.out.println("Please enter the type of fish you are selling.");
		System.out.println("1. Freshwater Fish");
		System.out.println("2. Saltwater Fish");
		int type = input.nextInt();
		input.nextLine();
		
		while (type != 1 && type != 2) {
            System.out.println("Invalid Choice. Please try again.");
            System.out.println("1. Freshwater Fish");
            System.out.println("2. Saltwater Fish");
            type = input.nextInt();
            input.nextLine();
        }
		
		System.out.print("Please enter the quantity of your fish.");
		int quantitySell = input.nextInt();
		input.nextLine();
		
		for (int i =0; i<quantitySell;i++) {
			System.out.print("Please enter the weight of your fish in kg #" + (i+1) + ": ");
			fishWeight = input.nextDouble();
			input.nextLine();
		
			if (type ==1) {
				fishtype ="Freshwater Fish";
				System.out.println("Please enter the species of your fish.");
				System.out.println("1. Tilapla");
				System.out.println("2. Catfish");
				System.out.println("3. Carp");
				System.out.println("4. Snakehead Murrel");
				System.out.println("5. Rohu");
				species = input.nextInt();
				switch(species) {
				case 1 :
					speciesname[i]= "Tilapha";
					priceperkg = 20.0;
					break;
				case 2:
					speciesname[i]= "Catfish";
					priceperkg = 3.0;
					break;
				case 3:
					speciesname[i]= "Carp";
					priceperkg = 20.0;
					break;
				case 4:
					speciesname[i]= "Snakehead Murrel";
					priceperkg = 20.0;
					break;
				case 5:
					speciesname[i]= "Rohu";
					priceperkg = 9.5;
					break;
				default :
					System.out.println("Unknown species. Default price is RM 15.0/kg.");
                    priceperkg = 15.0;
                    speciesname[i]= "Unknown Species";
				}
                    
                
			}
			else if (type ==2) {
				fishtype ="Saltwater Fish";
				System.out.println("Please enter the species of your fish.");
				System.out.println("1. Spanish Mackerel");
				System.out.println("2. Red Snapper");
				System.out.println("3. Black Pomfret");
				System.out.println("4. Tuna");
				System.out.println("5. Golden Trevally");
				species = input.nextInt();
				switch(species) {
				case 1 :
					speciesname[i]= "Spanish Mackerel";
					priceperkg = 29.0;
					break;
				case 2:
					speciesname[i]= "Red Snapper";
					priceperkg = 40.0;
					break;
				case 3:
					speciesname[i]= "Black Pomfret";
					priceperkg = 30.0;
					break;
				case 4:
					speciesname[i]= "Tuna";
					priceperkg = 20.0;
					break;
				case 5:
					speciesname[i]= "Golden Trevally";
					priceperkg = 9.5;
					break;
				default :
					System.out.println("Unknown species. Default price is RM 15.0/kg.");
                    priceperkg = 15.0;
                    speciesname[i]= "Unknown Species";
				}           
			}
			
			double priceForFish = fishWeight * priceperkg;
	        totalprice += priceForFish;
	        System.out.println("The price for fish #" + (i + 1) + " (" +speciesname[i]+", weight: " + fishWeight + "kg) is: RM" + priceForFish);
	        
		}
		
		System.out.println("You are selling " + quantitySell+ " "+ fishtype +" fishes with the total price : RM"+ totalprice);
		System.out.println("Are you sure to sell them? (No=0;Yes=1 )");
		int comfirm = input.nextInt();
		input.nextLine();
		
		if (comfirm==1) {
			System.out.println("Thank you for your fishes.");
			savehistory(username,totalprice);
		}
		else if(comfirm==0) {
			System.out.println("Thank you.");
			
		}
		
	}
		

	public static void buying(String username) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter the type of fish you are buying.");
		System.out.println("1. Freshwater Fish");
		System.out.print("2. Saltwater Fish");
		int type = input.nextInt();
		input.nextLine();
		
		FishCalc(type);
	}
	
	public static void FishSize(String size,String type) {
		
		Scanner sc = new Scanner(System.in);
		
		String sizeDesc ="";
		
		System.out.println ("Enter size of the fish : ");
		size = sc.nextLine();
		System.out.println ("Enter type of the fish : ");
		type = sc.nextLine();
		
		
		if (size.equalsIgnoreCase("small")) {
			
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
	
	public static void savehistory(String username,Double totalprice) {
		try (FileWriter writer = new FileWriter("history.txt", true)) {
            writer.write(username + "'s Sales History:\n");
            writer.write("Total Price: RM" + totalprice + "\n");
            writer.write("----------------------------------\n");
            System.out.println("Sales history saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving sales history: " );
            e.printStackTrace();
        }
	}
	
	public static double FishCalc(int type) {
		Scanner input = new Scanner(System.in);
		
		double weight=0.0;
		double price=0.0;
		String typeDesc = "";

		System.out.println ("Enter average weight for per fish (kg) : ");
		weight = input.nextDouble();
		input.nextLine();
		
		if (weight >= 0.2 && weight <= 1) {
		    price = 5;
		    
			switch (type) {
			case 1:
				typeDesc = "Freshwater Fish";
				break;
			case 2:
				typeDesc = "Saltwater Fish";
				break;
			}
		}
		else if (weight >= 1 && weight <= 5) {
		    price = 10;
		    
			switch (type) {
			case 1:
				typeDesc = "Freshwater Fish";
				
				break;
			case 2:
				typeDesc = "Saltwater Fish";
				break;
			}
		}
		else if (weight >= 5 && weight <= 10) {
			price = 20;
			
			switch (type) {
			case 1:
				typeDesc = "Freshwater Fish";
				
				break;
			case 2:
				typeDesc = "Saltwater Fish";
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

		Scanner input = new Scanner(System.in);
		int again = 1;
		int main = 0;
		String username ="";
		
		do{
			System.out.println("Welcome to the System!");
	        System.out.println("1. Register 2. Login");
	        System.out.print("Enter your choice: ");
	        int choice = input.nextInt();
	        input.nextLine();
			
	        if (choice == 1) {
		        	System.out.print("Enter your Username: ");
		    		String newusername = input.nextLine();
		    		System.out.print("Enter your Password: ");
		    		String newpassword = input.nextLine();
		    		newUser(newusername,newpassword);
		    		again=0;
		    		main=1;
		    	
	        }
	        else if(choice ==2) {
	        		System.out.print("Enter your Username: ");
	        		username = input.nextLine();
	        		System.out.print("Enter your Password: ");
	        		String password = input.nextLine();
	        		
	        		if(checkUser(username,password)) {
	        			System.out.println("Welcome back, " + username + "!");
	        			again=0;
	        			main=1;
	        		}
	        		else {
	        			System.out.println("Invalid username or password. Please try again.");
	        			again=1;
	        			main=0;
	        		}
	        		
	        }
	        else {
	        		System.out.print("Invalid Choice. Please try again.");
	        		again=1;
	        		main=0;
			}
	        
	        while(main ==1) {
	        	System.out.println("1. Sell 2. Buy");
	        	System.out.print("Are you selling or buying Fishes? ");
	        	int action = input.nextInt();
	        	
	        	if (action ==1) {
	        		selling(username);
	        		System.out.print("Do you have another trade to make? (No=0; Yes=1)");
        			main = input.nextInt();
        			input.nextLine();
	        	}
	        	else if(action ==2) {
	        		buying(username);
	        		System.out.print("Do you have another trade to make? (No=0; Yes=1)");
        			main = input.nextInt();
        			input.nextLine();
	        	}
	        }
	        
		}while(again == 1);
	}
}
