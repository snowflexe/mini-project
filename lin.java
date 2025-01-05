import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class lin {
	
	//intro output to the system JiaYin
	public static void intro() {
        System.out.printf ("----------------------------------\n");
        System.out.println ("Welcome to The Pekan Fishermen's Market!");
        System.out.printf ("----------------------------------\n");
	}
	
	//login Lin +Iman
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
	
	//register Lin+Iman
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
	
	//sharvesh veiw user
	public static void viewUserAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("userinfo.txt"))) {
            String line;
            System.out.println("Registered Users:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line.split(":")[0]); // Print only the username
            }
        } catch (FileNotFoundException e) {
            System.out.println("No users registered yet.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading user accounts.");
            e.printStackTrace();
        }
    }

    //sharvesh  View sales history
    public static void viewSalesHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("history.txt"))) {
            String line;
            System.out.println("Sales History:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No sales history found.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading sales history.");
            e.printStackTrace();
        }
    }
	
	//when the fish is catch Lin
	public static int dateCatch(){
		
		Scanner input = new Scanner(System.in);
		
		int again =1;
		int fresh =0;
		
		while(again==1) {
			System.out.println("What date is today?");
			System.out.println("Example: 12 January 2024. Date = 12");
			int todaydate = input.nextInt();
			input.nextLine();
			
			System.out.println("When did you catch the fish?");
			System.out.println("Example: 12 January 2024. Date = 12");
			int datecatch = input.nextInt();
			input.nextLine();
			
			int timecatch = todaydate - datecatch;
			
			
			if (timecatch>=4&&timecatch<7) {
				fresh =0;
				again=0;
			}
			else if (timecatch>=7) {
				fresh =2;
				again=0;
			}
			else if (timecatch<4&& timecatch>=0){
				fresh =1;
				again=0;
			}
			else {
				System.out.println("Wow, you can see through the future.Please enter a valid date.");
				again=1;
			}
		}
		return fresh;
	}
	
	// main process selling Lin
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
			
			int fresh = dateCatch();
			
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
			
			if (fresh==0) {
				System.out.println("Your fish is not fresh enough. This fish's price will be deducted 15%");
				priceForFish = priceForFish*0.85;
			}
			else if (fresh ==1) {
				System.out.println("Your fish is still fresh.");
			}
			else if(fresh ==2) {
				System.out.println("Sorry, your fish is not fresh anymore. We could not accept this.");
				priceForFish = priceForFish *0.0;
			}
			
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
		
	//main process buying Lin
	public static void buying(String username) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter the type of fish you are buying.");
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
	        if (fishData[i][0]>5.0) {
				System.out.println("Do you require different size of fish?(No=0; Yes=1)");
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
        else if(totalprice >= 100 && totalprice <200.0) {
			totalprice = totalprice *0.95;
			System.out.println("You are eligible for 5% off! ");
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
	
	//fishsize consideration JiaYin
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
	
	//saving record for selling Lin
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
	
	//saving record for buying Lin
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
	
	//discount calculation JiaYin
	public static double Discount (double totalprice) {
	
		if (totalprice >= 200) {
			totalprice = totalprice *0.9;
			System.out.println("You are eligible for 10% off! ");
		}
		
	return totalprice;

	}
	
	//view selling history Lin
	public static void readsellhistory(String username) {
		try {
			File sellhistory = new File("history.txt");
			Scanner reader = new Scanner(sellhistory);
			int userfound=0;
			int line = 0;
			
			while(reader.hasNextLine()) {
				String data = reader.nextLine();
				if (data.startsWith(username)) {
					System.out.println(data);
					userfound=1;
					line=3;
				}
				else if(line>0) {
					System.out.println(data);
					line--;
				}
			}
			if (userfound ==0){
				System.out.println("No records found for user: " + username);
			}
			reader.close();
		}catch(FileNotFoundException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
	}
	
	//view buying history Lin
	public static void readbuyhistory(String username) {
		try {
			File buyhistory = new File("buyhistory.txt");
			Scanner reader = new Scanner(buyhistory);
			int userfound=0;
			int line = 0;
			
			while(reader.hasNextLine()) {
				String data = reader.nextLine();
				if (data.startsWith(username)) {
					System.out.println(data);
					userfound=1;
					line =3;
				}
				else if(line>0) {
					System.out.println(data);
					line--;
				}
			}
			if (userfound ==0){
				System.out.println("No records found for user: " + username);
			}
			reader.close();
		}catch(FileNotFoundException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
	}
	//read complete history Lin
	public static void readFULLbuyhistory() {
		try {
			File buyhistory = new File("buyhistory.txt");
			Scanner reader = new Scanner(buyhistory);

			
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
	
	//deleting selling history Lin
	public static void deletesellhistory(String username) {
		
		File sellhistory = new File("history.txt");
		File tempfile= new File("tempfile.txt");
		
		try {
			Scanner reader = new Scanner(sellhistory);
			FileWriter writer = new FileWriter(tempfile);
			
			int userfound=0;
			int line = 0;
			
			while(reader.hasNextLine()) {
				String data = reader.nextLine();
				if (data.startsWith(username)) {
					userfound=1;
					line =3;
					continue;
				}
				else if(line>0) {
					line--;
					continue;
				}
				writer.write(data + System.lineSeparator());
				
			}
			if (userfound ==0){
				System.out.println("No records found for user: " + username);
			}
			else {
				System.out.println("Records deleted for user: " + username);
			}
			reader.close();
			writer.close();
		}catch(FileNotFoundException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}catch (IOException e) {
	        System.out.println("An error occurred while processing the file.");
	        e.printStackTrace();
	    }
		if (sellhistory.delete()) {
			if (tempfile.renameTo(sellhistory)) {
		        System.out.println("File updated successfully.");
		    }
			else {
		        System.out.println("An error occurred while updating the file.");
		    }
		}
		else {
	        System.out.println("An error occurred while deleting the original file.");
	    }
	}
	
	//deleting buying history Lin
	public static void deletebuyhistory(String username) {
		
		File buyhistory = new File("buyhistory.txt");
		File tempfile= new File("tempfile.txt");
		
		try (Scanner reader = new Scanner(buyhistory);
			FileWriter writer = new FileWriter(tempfile)){
			
			int userfound=0;
			int line = 0;
			
			while(reader.hasNextLine()) {
				String data = reader.nextLine();
				if (data.startsWith(username)) {
					userfound=1;
					line =3;
					continue;
				}
				else if(line>0) {
					line--;
					continue;
				}
				writer.write(data + System.lineSeparator());
				
			}
			if (userfound ==0){
				System.out.println("No records found for user: " + username);
			}
			else {
				System.out.println("Records deleted for user: " + username);
			}
			reader.close();
		}catch(FileNotFoundException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}catch (IOException e) {
	        System.out.println("An error occurred while processing the file.");
	        e.printStackTrace();
	    }
		if (buyhistory.delete()) {
			if (tempfile.renameTo(buyhistory)) {
		        System.out.println("File updated successfully.");
		    } else {
		        System.out.println("An error occurred while updating the file.");
		    }
		}
		else {
	        System.out.println("An error occurred while deleting the original file.");
	    }
		
	}
	
	//closing output JiaYin
	public static void cancelTrade() {
        System.out.println("");
		System.out.println("Thank you for using our system. Goodbye!");
	}
	
	//main function Lin+Sharvesh
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Lin
		Scanner input = new Scanner(System.in);
		int again = 1;
		int main = 0;
		String username ="";
		int historyrequest = 0;
		int viewhistory =0;
		int confirmdelete=0;
		int on =1;
		
		intro();
		
		do{
			System.out.println("Welcome to the System!");
			System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Admin page");
            System.out.println("4. Exit");
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
	        //Sharvesh
	        else if(choice ==3) {
	        	//Lin
	        	System.out.print("Enter your Username: ");
        		String adminusername = input.nextLine();
        		System.out.print("Enter your Password: ");
        		String adminpassword = input.nextLine();
        		
        		//Lin
        		if (adminusername.equals("group7")&&adminpassword.equals("1234")) {
        			while(on==1) {
        				//Sharvesh
	        			System.out.println("Admin Page");
			            System.out.println("1. View User Accounts");
			            System.out.println("2. View Sales History");
			            System.out.println("3. View Buy History");//Lin
			            System.out.println("4. Exit Admin Page");
			            System.out.print("Enter your choice: ");
			            int adminChoice = input.nextInt();
			            input.nextLine();
			            
			            if (adminChoice == 1) {
		                    viewUserAccounts();
		                } else if (adminChoice == 2) {
		                    viewSalesHistory();
		                } else if (adminChoice == 4) {
		                    System.out.println("Exiting Admin Page.");
		                    on=0;
		                } 
			            //Lin
		                else if (adminChoice == 3) {
		                    readFULLbuyhistory();
		                }else {
		                    System.out.println("Invalid choice. Please try again.");
		                }
	        		}
    			}
    			else {
    			System.out.println("Invalid username or password. Please try again.");
    			main=0;
    			again=1;
        		}
        		
            
	        }

	        /////////////////////////////////////////////////////////////////
	        
	        else if(choice ==4) {
	        	System.out.println("Exiting...");
	        	main=0;
	        	again=0;
	        }
	        else {
	        		System.out.println("Invalid Choice. Please try again.");
	        		again=1;
	        		main=0;
			}
	        
	        while(main ==1) {
	        	System.out.println("Are you selling or buying Fishes? ");
	        	System.out.println("1. Sell");
	        	System.out.println("2. Buy");
	        	System.out.println("3. History");
	        	System.out.println("4. Exit");
	        	
	        	int action = input.nextInt();
	        	
	        	if (action ==1) {
	        		selling(username);
	        		System.out.print("Do you have another things to do? (No=0; Yes=1)");
        			main = input.nextInt();
        			input.nextLine();
	        	}
	        	else if(action ==2) {
	        		buying(username);
	        		System.out.print("Do you have another things to do? (No=0; Yes=1)");
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
	        			System.out.println("Do you want to view /delete history");
		        		System.out.println("1. View");
		        		System.out.println("2. Delete");
	        			viewhistory = input.nextInt();
		        		input.nextLine();
	        			if (viewhistory==1) {
	        				readsellhistory(username);
	        			}
	        			else if (viewhistory ==2) {
	        				System.out.println("Are you sure you want to delete your sales history? (No =0; Yes=1)");
	        				confirmdelete = input.nextInt();
			        		input.nextLine();
			        		if (confirmdelete==0) {
			        			readsellhistory(username);
			        		}
			        		else if(confirmdelete==1) {
			        		deletesellhistory(username);
			        		}
	        				
	        			}
	        		}else if (historyrequest == 2) {
	        			System.out.println("Do you want to view /delete history");
		        		System.out.println("1. View");
		        		System.out.println("2. Delete");
	        			viewhistory = input.nextInt();
		        		input.nextLine();
	        			if (viewhistory==1) {
	        				readbuyhistory(username);
	        			}
	        			else if (viewhistory ==2) {
	        				System.out.println("Are you sure you want to delete your buy history? (No =0; Yes=1)");
	        				confirmdelete = input.nextInt();
			        		input.nextLine();
			        		if (confirmdelete==0) {
			        			readbuyhistory(username);
			        		}
			        		else if(confirmdelete==1) {
			        		deletebuyhistory(username);
			        		}
	        				
	        			}
	        			
	        		}
	        		
	        	}
	        	else if (action==4) {
	        		System.out.println("Thank you. Goodbye!");
	        		main=0;
	        	}
	        }
	        
	        System.out.print("Do you want to perform another account? (No =0; Yes=1)");
	        again = input.nextInt();
	        input.nextLine();
	        
		}while(again == 1);
		
		if (again==0) {
			cancelTrade();
		}
		
		
	}
}
