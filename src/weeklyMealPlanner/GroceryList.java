package weeklyMealPlanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroceryList {

	List<Ingredient> groceryList = new ArrayList<Ingredient>();

	// Logic

	/**
	 * Generates a list of all groceries needed to prepare the meals from the weekly
	 * meal plan.
	 * 
	 * @param weeklyMealPlan weekly meal plan for which a grocery list is to be
	 *                       generated.
	 */
	public void generate(WeeklyMealPlan weeklyMealPlan) {
		// Adding the ingredients for each meal of each day to the list of ingredients
		for (DailyMealPlan dailyMealPlan : weeklyMealPlan.getDailyMealPlans()) {
			Meal breakfast = dailyMealPlan.getBreakfast();
			if (breakfast != null) {
				addIngredients(breakfast.getIngredients());
			}
			Meal lunch = dailyMealPlan.getLunch();
			if (lunch != null) {
				addIngredients(lunch.getIngredients());
			}
			Meal dinner = dailyMealPlan.getDinner();
			if (dinner != null) {
				addIngredients(dinner.getIngredients());
			}
		}
		Collections.sort(groceryList);
	}

	/**
	 * Adds the ingredients to the grocery list.
	 * 
	 * @param mealIngredients list of ingredients that are to be added to the
	 *                        grocery list.
	 */
	public void addIngredients(List<Ingredient> mealIngredients) {
		for (Ingredient ingredient : mealIngredients) {
			boolean notContained;
			notContained = true;

			// checking if the grocery list already contains the ingredient to add
			// if yes, just add the amount of the ingredient
			for (int i = 0; i < groceryList.size(); i++) {
				if (groceryList.get(i).equals(ingredient)) {
					notContained = false;
					groceryList.get(i).getAmount().add(ingredient.getAmount());
				}
			}
			// if not, add the ingredient to the grocery list
			if (notContained) {
				// The ingredient-object in the grocery list must be a different object than the
				// object in the meal's ingredient list. This ensures that the defined recipe of
				// the meals are not changed.
				Ingredient ingredientToAdd = new Ingredient();
				ingredientToAdd.setName(ingredient.getName());
				ingredientToAdd.setAmount(new Amount(ingredient.getAmount()));

				groceryList.add(ingredientToAdd);
			}
		}
	}

	/**
	 * Prints the grocery list formatted to the console.
	 */
	public void print() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Grocery list:");
		for (Ingredient ingredient : groceryList) {
			System.out.println(" - " + ingredient.toString());
		}
	}

	// Getters / Setters

	public List<Ingredient> getGroceryList() {
		return groceryList;
	}

	public void setGroceryList(List<Ingredient> groceryList) {
		this.groceryList = groceryList;
	}
}
