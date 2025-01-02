import java.util.ArrayList;
	import java.util.Scanner;

public class SHARVESH {

	
	class Fish {
	    String species;
	    String dateCaught;
	    String size;
	    String type;
	    double weight;
	    int quantity;

	    public Fish(String species, String dateCaught, String size, String type, double weight, int quantity) {
	        this.species = species;
	        this.dateCaught = dateCaught;
	        this.size = size;
	        this.type = type;
	        this.weight = weight;
	        this.quantity = quantity;
	    }

	 
	    public String toString() {
	        return "Species: " + species + ", Date Caught: " + dateCaught + ", Size: " + size +
	                ", Type: " + type + ", Weight: " + weight + "kg, Quantity: " + quantity;
	    }
	}

	class User {
	    String username;
	    String password;
	    ArrayList<Fish> fishList = new ArrayList<>();

	    public User(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }
	}

	public class FishermenMarket {
	    static ArrayList<User> users = new ArrayList<>();
	    static Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {
	        while (true) {
	            System.out.println("Welcome to the Fishermen's Market!");
	            System.out.println("1. Register");
	            System.out.println("2. Login");
	            System.out.println("3. Exit");
	            int choice = scanner.nextInt();
	            scanner.nextLine();
	            switch (choice) {
	                case 1:
	                    registerUser ();
	                    break;
	                case 2:
	                    loginUser ();
	                    break;
	                case 3:
	                    System.out.println("Exiting...");
	                    return;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	    }

	    private static void registerUser () {
	        System.out.print("Enter username: ");
	        String username = scanner.nextLine();
	        System.out.print("Enter password: ");
	        String password = scanner.nextLine();
	        users.add(new User(username, password));
	        System.out.println("Registration successful!");
	    }

	    private static void loginUser () {
	        System.out.print("Enter username: ");
	        String username = scanner.nextLine();
	        System.out.print("Enter password: ");
	        String password = scanner.nextLine();

	        for (User  user : users) {
	            if (user.username.equals(username) && user.password.equals(password)) {
	                System.out.println("Login successful!");
	                userMenu(user);
	                return;
	            }
	        }
	        System.out.println("Invalid username or password.");
	    }

	    private static void userMenu(User user) {
	        while (true) {
	            System.out.println("1. Add Fish");
	            System.out.println("2. View Fish List");
	            System.out.println("3. Exit to Main Menu");
	            int choice = scanner.nextInt();
	            scanner.nextLine(); 

	            switch (choice) {
	                case 1:
	                    addFish(user);
	                    break;
	                case 2:
	                    viewFishList(user);
	                    break;
	                case 3:
	                    return;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
	    }

	    private static void addFish(User user) {
	        System.out.print("Enter fish species: ");
	        String species = scanner.nextLine();
	        System.out.print("Enter date caught (YYYY-MM-DD): ");
	        String dateCaught = scanner.nextLine();
	        System.out.print("Enter size: ");
	        String size = scanner.nextLine();
	        System.out.print("Enter type: ");
	        String type = scanner.nextLine();
	        System.out.print("Enter weight (kg): ");
	        double weight = scanner.nextDouble();
	        System.out.print("Enter quantity: ");
	        int quantity = scanner.nextInt();
	        scanner.nextLine(); 

	        Fish fish = new Fish(species, dateCaught, size, type, weight, quantity);
	        user.fishList.add(fish);
	        System.out.println("Fish added successfully!");
	    }

	    private static void viewFishList(User user) {
	        if (user.fishList.isEmpty()) {
	            System.out.println("No fish available.");
	        } else {
	            System.out.println("Fish List:");
	            for (Fish fish : user.fishList) {
	                System.out.println(fish);
	            }
	        }
	    }
