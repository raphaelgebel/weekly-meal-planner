package weeklyMealPlanner;

import java.io.Serializable;

public class Ingredient implements Serializable, Comparable<Ingredient> {

	private static final long serialVersionUID = 1L;
	private String name;
	private Amount amount;

	public Ingredient() {
	}

	// This constructor can be used for cloning an Ingredient-object
	public Ingredient(Ingredient other) {
		this.name = other.name;
		this.amount = new Amount(other.amount);
	}

	// Logic

	/**
	 * Lets the user define an ingredient with a name and the amount of the
	 * ingredient
	 */
	public void userDefine(Meal meal) {
		// Letting the user choose a name
		System.out.println("--------------------------------------------------------------------------------");
		System.out.print("Ingredient name: ");
		name = Main.scanner.nextLine();
		name = name.trim();
		name = name.replaceAll("\\s+", " ");
		
		// Letting the user choose an amount
		amount = new Amount();
		amount.userDefine();
	}

	@Override
	public String toString() {
		return amount.toString() + " " + name;
	}

	public boolean equals(Object o) {
		if (this.getClass() != o.getClass()) {
			return false;
		}
		Ingredient other = (Ingredient) o;

		if (name.equals(other.getName())) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Ingredient o) {
		// Comparing the ingredients in alphabetical order by name
		if (name.compareTo(o.getName()) > 0) {
			return 1;
		}
		if (name.compareTo(o.getName()) < 0) {
			return -1;
		}
		return 0;
	}

	// Getters / Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Amount getAmount() {
		return amount;
	}

	public void setAmount(Amount amount) {
		this.amount = amount;
	}
}
