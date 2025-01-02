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
	
	
	public static boolean newUser(String newusername,String newpassword,int main) {
        try (BufferedReader reader = new BufferedReader(new FileReader("userinfo.txt"))){
            String line ;
            while ((line = reader.readLine())!= null) {
                if (line.startsWith(newusername + ":")) {
                    System.out.println("Username already exists. Please try a different one.");
                    main=0;
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
            System.out.println("Please proceed to log in.");
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
			System.out.println("Thank you for your fishes. Transaction success.");
			sellhistory(username,fishtype,totalprice);
		}
		else if(comfirm==0) {
			System.out.println("Thank you. Transaction cancelled.");
			
		}
		
	}
		

	public static void buying(String username) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter the type of fish you are buying.");
		System.out.println("1. Freshwater Fish");
		System.out.print("2. Saltwater Fish");
		int type = input.nextInt();
		input.nextLine();
		
		while (type != 1 && type != 2) {
            System.out.println("Invalid Choice. Please try again.");
            System.out.println("1. Freshwater Fish");
            System.out.println("2. Saltwater Fish");
            type = input.nextInt();
            input.nextLine();
        }
		
		System.out.print("Please enter the quantity of fish to buy: ");
        int quantityBuy = input.nextInt();
        input.nextLine();
		
        String[] speciesname = new String[quantityBuy];
        double[][] fishData = new double[quantityBuy][2];
        double totalprice =0.0;
		double priceperkg = 0.0;
		String fishtype = "";
		int species = 0;
        
        for (int i =0; i<quantityBuy;i++) {
			
		
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
					priceperkg = 28.8;
					break;
				case 2:
					speciesname[i]= "Catfish";
					priceperkg = 3.8;
					break;
				case 3:
					speciesname[i]= "Carp";
					priceperkg = 28.8;
					break;
				case 4:
					speciesname[i]= "Snakehead Murrel";
					priceperkg = 29.8;
					break;
				case 5:
					speciesname[i]= "Rohu";
					priceperkg = 12.8;
					break;
				default :
					System.out.println("Unknown Species. Sorry, we do no sell this fish.");
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
					priceperkg = 32.8;
					break;
				case 2:
					speciesname[i]= "Red Snapper";
					priceperkg = 48.8;
					break;
				case 3:
					speciesname[i]= "Black Pomfret";
					priceperkg = 38.8;
					break;
				case 4:
					speciesname[i]= "Tuna";
					priceperkg = 28.8;
					break;
				case 5:
					speciesname[i]= "Golden Trevally";
					priceperkg = 12.8;
					break;
				default :
					System.out.println("Unknown species. Sorry, we do no sell this fish.");
				}           
			}
			
			System.out.print("Please enter the weight of your fish in kg #" + (i+1) + ": ");
			fishData[i][0] = input.nextDouble();
			input.nextLine();
	        if (fishData[i][0]>=5.0) {
				System.out.print("Do you require different size of fish?(No=0; Yes=1)");
				int sizerequire = input.nextInt();
				if (sizerequire==1) {
					priceperkg = FishSize(priceperkg);
				}
				else {
					priceperkg=priceperkg+0.0;
				}
			}
			double price = fishData[i][0] * priceperkg;

	        fishData[i][1]= price;
	        totalprice += price;
	        
	        fishData[i][1]= Math.round(fishData[i][1]*100.0)/100.0;
	        System.out.println("The price for fish #" + (i + 1) + " (" +speciesname[i]+", weight: " + fishData[i][0] + "kg) is: RM" + fishData[i][1]);
        }
        
        if (totalprice>=200.0) {
        	totalprice= Discount(totalprice);
        }
        else {
        	totalprice = totalprice+0.0;
        }
        System.out.println("You are buying " + quantityBuy + " " + fishtype + " with a total price of RM" + Math.round(totalprice*100.0)/100.0);
        System.out.println("Are you sure to buy them? (No=0; Yes=1)");
        int confirm = input.nextInt();
        input.nextLine();
        
        if (confirm == 1) {
            System.out.println("Thank you for your fishes.");
            buyhistory(username, fishtype, totalprice);
        } else {
            System.out.println("Transaction cancelled.");
        }

	}
	
	public static double FishSize(double priceperkg) {
		
		Scanner sc = new Scanner(System.in);
		
		int size = 0;
		String sizeDesc ="";
		
		System.out.println ("Enter size of the fish : ");
		System.out.println ("1. Normal ");
		System.out.println ("2. Medium ");
		System.out.println ("3. Large ");
		size = sc.nextInt();
		sc.nextLine();
		
		if (size ==1) {
			sizeDesc = "Small Fish";
			priceperkg= priceperkg*1.0;
			System.out.println(sizeDesc+" will not charge you more.");
		}
		else if (size==2) {
			sizeDesc = "Medium Fish";
			priceperkg= priceperkg*1.15;
			System.out.println(sizeDesc+" will charge you 15% more.");
		}
		else if (size==3) {
			sizeDesc = "Large Fish";
			priceperkg= priceperkg*1.3;
			System.out.println(sizeDesc+" will charge you 30% more.");
		}
		else {
			System.out.println ("Invalid Size");

		}
		System.out.println("You have selected "+ sizeDesc);
		return priceperkg;
	}
	
	public static void sellhistory(String username,String fishtype,double totalprice) {
		try (FileWriter writer = new FileWriter("history.txt", true)) {
            writer.write(username + "'s Sales History:\n");
            writer.write("Fish Type: " + fishtype + "\n");
            
            writer.write("Total Price: RM" + totalprice + "\n");
            writer.write("----------------------------------\n");
            System.out.println("Sales history saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving sales history: " );
            e.printStackTrace();
        }
	}
	
	public static void buyhistory(String username,String fishtype, double totalprice) {
		try (FileWriter writer = new FileWriter("buyhistory.txt", true)) {
            writer.write(username + "'s Buying History:\n");
            writer.write("Fish Type: " + fishtype + "\n");
            
            writer.write("Total Price: RM" + totalprice + "\n");
            writer.write("----------------------------------\n");
            System.out.println("Buying history saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving sales history: " );
            e.printStackTrace();
        }
	}
	
	public static double Discount (double totalprice) {
	
		if (totalprice >= 200) {
			totalprice = totalprice *0.9;
			System.out.println("You are eligible for 10% off! ");
		}
	
	return totalprice;

	}
	
	public static void readsellhistory() {
		try {
			File sellhistory = new File("history.txt");
			Scanner reader = new Scanner(sellhistory);
			while(reader.hasNextLine()) {
				String data = reader.nextLine();
				System.out.println(data);
			}
			reader.close();
		}catch(FileNotFoundException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
	}
	
	public static void readbuyhistory() {
		try {
			File sellhistory = new File("buyhistory.txt");
			Scanner reader = new Scanner(sellhistory);
			while(reader.hasNextLine()) {
				String data = reader.nextLine();
				System.out.println(data);
			}
			reader.close();
		}catch(FileNotFoundException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner input = new Scanner(System.in);
		int again = 1;
		int main = 0;
		String username ="";
		int historyrequest = 0;
		
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
		    		newUser(newusername,newpassword,main);
		    		again=0;
		    	
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
	        	System.out.println("1. Sell 2. Buy 3.History 4.Exit");
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
	        	else if (action ==3) {
	        		System.out.println("Are you requesting Sell/Buy History?");
	        		System.out.println("1. Sell");
	        		System.out.println("2. Buy");
	        		historyrequest = input.nextInt();
	        		input.nextLine();
	        		if (historyrequest == 1) {
	        			readsellhistory();
	        		}else if (historyrequest == 2) {
	        			readbuyhistory();
	        		}
	        		
	        	}
	        	else if (action==4) {
	        		System.out.println("Thank you. Goodbye!");
	        		main=0;
	        	}
	        }
	        
	        System.out.print("Do you want to perform another action? (No =0; Yes=1)");
	        again = input.nextInt();
	        input.nextLine();
	        
		}while(again == 1);
	}
}
