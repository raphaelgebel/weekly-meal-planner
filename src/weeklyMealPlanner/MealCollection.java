package weeklyMealPlanner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;

public class MealCollection implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Meal> meals = new ArrayList<Meal>();

	/**
	 * Manages and prints the menu for the meal collection to the console.
	 */
	public void menu() {

		boolean exit = false;
		while (!exit) {
			exit = false;
			boolean validInput;
			do {
				// Letting the user select one of the given actions
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("(1) View meal collection");
				System.out.println("(2) Add new meal");
				System.out.println("(3) Delete a meal");
				System.out.println("(0) Go back");

				try {
					int choice = Main.scanner.nextInt();
					Main.scanner.nextLine();
					validInput = true;

					switch (choice) {
					case 1:
						print();
						break;
					case 2:
						addMeal();
						break;
					case 3:
						deleteMeal();
						break;
					case 0:
						exit = true;
						break;
					default:
						throw new InvalidInputException();
					}
				} catch (InputMismatchException e) {
					Main.scanner.nextLine();
					String message = new InvalidInputException().getMessage();
					System.out.println(message);
					validInput = false;
				} catch (InvalidInputException e) {
					System.out.println(e.getMessage());
					validInput = false;
				}

			} while (!validInput);
		}
	}

	/**
	 * Prints the meal collection formatted to the console.
	 */
	public void print() {
		System.out.println("--------------------------------------------------------------------------------");
		
		if (meals.isEmpty()) {
			System.out.println("There are no meals in the meal collection yet!");
		} else {
			Collections.sort(meals);
			System.out.println("Meals:");

			for (Meal meal : meals) {
				System.out.println(" - " + meal.toString() + " (" + meal.getMealType().toString() + ")");
			}
		}
	}

	/**
	 * Creates a new user-defined meal and adds it to the meal collection.
	 */
	public void addMeal() {
		Meal meal = new Meal();
		meal.userDefine(this);
		meals.add(meal);
	}

	/**
	 * Lets the user choose a meal to delete and deletes it from the meal
	 * collection.
	 */
	public void deleteMeal() {
		print();
		System.out.print("Name of the meal to delete: ");
		String mealName = Main.scanner.nextLine();
		mealName = mealName.trim();
		mealName = mealName.replaceAll("\\s+", " ");

		// Using the Iterator interface, to iterate over the list of meals and delete
		// the appropriate object.
		Iterator<Meal> iterator = meals.iterator();
		while (iterator.hasNext()) {
			Meal meal = iterator.next();
			if (meal.getName().equalsIgnoreCase(mealName)) {
				iterator.remove();
				System.out.println("The meal '" + mealName + "' has been deleted");
				return;
			}
		}
		System.out.println("There is no meal with the name '" + mealName + "'");
	}

	// Getters / Setters

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

}
