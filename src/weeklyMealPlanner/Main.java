package weeklyMealPlanner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		// Loading the previously saved meal collection through deserialization of the
		// saved object.
		MealCollection mealCollection = null;
		try {
			FileInputStream fileInputStream = new FileInputStream("MyMealCollection.ser");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			mealCollection = (MealCollection) objectInputStream.readObject();

			objectInputStream.close();
			fileInputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// If no meal collection has been saved before, then a new one has to be created
		if (mealCollection == null) {
			mealCollection = new MealCollection();
		}
		
		boolean exit = false;
		while (!exit) {

			boolean validInput;
			do {
				exit = false;
				// General program menu
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Choose an action");
				System.out.println("(1) Manage meal collection");
				System.out.println("(2) Generate meal plan (with grocery list)");
				System.out.println("(0) Quit program");

				try {
					int choice = scanner.nextInt();
					scanner.nextLine();
					validInput = true;

					switch (choice) {
					case 1:
						mealCollection.menu();
						break;
					case 2:
						// Generating a new weekly meal plan and printing in to the console
						WeeklyMealPlan weeklyMealPlan = new WeeklyMealPlan();
						weeklyMealPlan.generate(mealCollection);
						weeklyMealPlan.print();

						// Generating a new grocery list for the week and printing in to the console
						GroceryList groceryList = new GroceryList();
						groceryList.generate(weeklyMealPlan);
						Collections.sort(groceryList.getGroceryList());
						groceryList.print();
						break;
					case 0:
						exit = true;
						break;
					default:
						throw new InvalidInputException();
					}

				} catch (InputMismatchException e) {
					scanner.nextLine();
					String message = new InvalidInputException().getMessage();
					System.out.println(message);
					validInput = false;
				} catch (InvalidInputException e) {
					System.out.println("ERROE" + e.getMessage());
					validInput = false;
				}

			} while (!validInput);
		}

		scanner.close();

		// Saving the meal collection and its changes through serialization of the
		// object
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("MyMealCollection.ser");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(mealCollection);

			objectOutputStream.close();
			fileOutputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
