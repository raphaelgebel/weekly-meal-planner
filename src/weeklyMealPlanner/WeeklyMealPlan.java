package weeklyMealPlanner;

public class WeeklyMealPlan {

	private static final int DAYS_PER_WEEK = 7;

	DailyMealPlan[] dailyMealPlans = new DailyMealPlan[DAYS_PER_WEEK];

	// Logic

	/**
	 * Generates a random weekly meal plan.
	 * 
	 * @param mealCollection
	 */
	public void generate(MealCollection mealCollection) {
		// Generating and adding a random daily meal plan for each day of the week
		for (int i = 0; i < DAYS_PER_WEEK; i++) {
			DailyMealPlan dailyMealPlan = new DailyMealPlan();
			dailyMealPlan.generate(mealCollection);
			dailyMealPlans[i] = dailyMealPlan;
		}
	}

	/**
	 * Prints the weekly meal plan formatted to the console.
	 */
	public void print() {
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Meal plan:");
		System.out.println(" - Mon:");
		getDailyMealPlans()[0].print();
		System.out.println(" - Tue:");
		getDailyMealPlans()[1].print();
		System.out.println(" - Wed:");
		getDailyMealPlans()[2].print();
		System.out.println(" - Thu:");
		getDailyMealPlans()[3].print();
		System.out.println(" - Fri:");
		getDailyMealPlans()[4].print();
		System.out.println(" - Sat:");
		getDailyMealPlans()[5].print();
		System.out.println(" - Sun:");
		getDailyMealPlans()[6].print();
	}

	// Getters / Setters

	public DailyMealPlan[] getDailyMealPlans() {
		return dailyMealPlans;
	}

	public void setDailyMealPlans(DailyMealPlan[] dailyMealPlans) {
		this.dailyMealPlans = dailyMealPlans;
	}

}
