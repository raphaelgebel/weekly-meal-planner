package weeklyMealPlanner;

import java.util.Collections;
import java.util.List;

public class DailyMealPlan {

	Meal breakfast;
	Meal lunch;
	Meal dinner;

	// Logic

	/**
	 * Randomly generates a meal plan for a single day.
	 * 
	 * @param mealCollection the collection of meals from which the meals are taken.
	 */
	public void generate(MealCollection mealCollection) {
		List<Meal> mealList = mealCollection.getMeals();
		Collections.shuffle(mealList);

		setBreakfast(getNextMeal(mealList, MealType.BREAKFAST));
		setLunch(getNextMeal(mealList, MealType.LUNCH));
		setDinner(getNextMeal(mealList, MealType.DINNER));
	}

	/**
	 * Gets the next meal of the specified MealType from the list, whilst ensuring,
	 * that no meal is repeated twice within a single daily meal plan.
	 * 
	 * @param mealList The list of meals from which the meals are taken.
	 * @param mealType The type of meal that the meal should be.
	 * @return The next occurring meal from a given MealType within the List of
	 *         meals.
	 */
	private Meal getNextMeal(List<Meal> mealList, MealType mealType) {
		for (Meal meal : mealList) {
			if (meal.getMealType().equals(mealType)) {
				return meal;
			}
		}
		return null;
	}

	/**
	 * Prints the meal plan formatted to the console.
	 */
	public void print() {
		if (breakfast != null) {
			System.out.println("    - Breakfast: " + breakfast.getName());
		} else {
			System.out.println("    - Breakfast: There are no breakfasts in the meal collection yet!");
		}

		if (lunch != null) {
			System.out.println("    - Lunch: " + lunch.getName());
		} else {
			System.out.println("    - Lunch: There are no lunches in the meal collection yet!");
		}

		if (dinner != null) {
			System.out.println("    - Dinner: " + dinner.getName());
		} else {
			System.out.println("    - Dinner: There are no dinners in the meal collection yet!");
		}
	}

	// Getters / Setters

	public Meal getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(Meal breakfast) {
		this.breakfast = breakfast;
	}

	public Meal getLunch() {
		return lunch;
	}

	public void setLunch(Meal lunch) {
		this.lunch = lunch;
	}

	public Meal getDinner() {
		return dinner;
	}

	public void setDinner(Meal dinner) {
		this.dinner = dinner;
	}

}
