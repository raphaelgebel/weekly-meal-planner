package weeklyMealPlanner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Meal implements Serializable, Comparable<Meal> {

	private static final long serialVersionUID = 1L;
	MealType mealType;
	private String name;
	List<Ingredient> ingredients = new ArrayList<Ingredient>();

	// Logic

	/**
	 * Lets the user to define a new meal.
	 */
	public void userDefine(MealCollection mealCollection) {
		System.out.println("--------------------------------------------------------------------------------");
		
		// defining the meal type
		boolean validInput;
		do {
			// Letting the user select one of the given meal types
			System.out.println("Choose the meal type");
			System.out.println("(1) Breakfast");
			System.out.println("(2) Lunch");
			System.out.println("(3) Dinner");

			try {
				int choice = Main.scanner.nextInt();
				Main.scanner.nextLine();

				switch (choice) {
				case 1:
					setMealType(MealType.BREAKFAST);
					validInput = true;
					break;
				case 2:
					setMealType(MealType.LUNCH);
					validInput = true;
					break;
				case 3:
					setMealType(MealType.DINNER);
					validInput = true;
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

		// Letting the user define a name for the meal
		boolean mealNameAlreadyExists;
		do {
			mealNameAlreadyExists = false;
			System.out.print("Meal name: ");
			name = Main.scanner.nextLine();
			name = name.trim();
			name = name.replaceAll("\\s+", " ");
			
			// Checking if there is already a meal with the same name
			for(Meal meal : mealCollection.getMeals()) {
				if(meal.getName().equalsIgnoreCase(name)) {
					System.out.println("There is already a meal with the name: " + name);
					mealNameAlreadyExists = true;
				}
			}
		} while(mealNameAlreadyExists);
			
		// Letting the user create a list of ingredients for the meal
		boolean done = false;
		while (!done) {

			do {
				System.out.println("(1) Add ingredient");
				System.out.println("(0) Done");
				try {
					int choice = Main.scanner.nextInt();
					Main.scanner.nextLine();

					switch (choice) {
					case 1:
						Ingredient ingredient = new Ingredient();
						ingredient.userDefine(this);
						ingredients.add(ingredient);
						validInput = true;
						break;
					case 0:
						done = true;
						validInput = true;
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
		while (!done)
			;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Meal o) {
		if (this == o || o == null) {
			return 0;
		}
		if (name.compareTo(o.getName()) < 0) {
			return -1;
		} else if (name.compareTo(o.getName()) > 0) {
			return 1;
		}
		return 0;
	}

	// Getters / Setters

	public MealType getMealType() {
		return mealType;
	}

	public void setMealType(MealType mealType) {
		this.mealType = mealType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}
