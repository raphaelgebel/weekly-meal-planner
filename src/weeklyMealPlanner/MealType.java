package weeklyMealPlanner;

public enum MealType {

	BREAKFAST("Breakfast"), LUNCH("Lunch"), DINNER("Dinner");

	private String stringRepresentation;

	private MealType(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}

	// Logic

	public String toString() {
		return stringRepresentation;
	}

	// Getters / Setters

	public String getStringRepresentation() {
		return stringRepresentation;
	}

	public void setStringRepresentation(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}
}
