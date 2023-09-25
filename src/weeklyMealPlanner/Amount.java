package weeklyMealPlanner;

import java.io.Serializable;
import java.util.InputMismatchException;

public class Amount implements Serializable {

	private static final long serialVersionUID = 1L;
	private int value;
	private Measure measure;

	public Amount() {
	}

	// This constructor can be used for cloning an Amount-object
	public Amount(Amount other) {
		this.value = other.value;
		this.measure = other.measure;
	}

	// Logic

	/**
	 * Lets the user to define the amount of a specific ingredient with the
	 * corresponding unit.
	 */
	public void userDefine() {
		int conversionFactor = 1; // This factor is being used to ensure that different units can be added
									// together

		// Letting the user choose a unit
		boolean validInput;
		do {
			System.out.println("Choose a unit");
			System.out.println("(1) Kilogram");
			System.out.println("(2) Gram");
			System.out.println("(3) Milligram");
			System.out.println("(4) Liter");
			System.out.println("(5) Milliliter");
			System.out.println("(6) Tablespoon");
			System.out.println("(7) Teaspoon");
			System.out.println("(0) None");
			try {
				int choice = Main.scanner.nextInt();
				Main.scanner.nextLine();

				switch (choice) {
				case 1:
					measure = Measure.WEIGHT;
					conversionFactor = Measure.CONVERSION_FACTOR_kg_mg;
					validInput = true;
					break;
				case 2:
					measure = Measure.WEIGHT;
					conversionFactor = Measure.CONVERSION_FACTOR_g_mg;
					validInput = true;
					break;
				case 3:
					measure = Measure.WEIGHT;
					validInput = true;
					break;
				case 4:
					measure = Measure.VOLUME;
					conversionFactor = Measure.CONVERSION_FACTOR_l_ml;
					validInput = true;
					break;
				case 5:
					measure = Measure.VOLUME;
					validInput = true;
					break;
				case 6:
					measure = Measure.VOLUME;
					conversionFactor = Measure.CONVERSION_FACTOR_tsp_ml;
					validInput = true;
					break;
				case 7:
					measure = Measure.VOLUME;
					conversionFactor = Measure.CONVERSION_FACTOR_tsp_ml;
					validInput = true;
					break;
				case 0:
					measure = Measure.NONE;
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

		// Letting the user define a value
		do {
			System.out.print("Value: ");
			try {
				value = Main.scanner.nextInt() * conversionFactor;
				Main.scanner.nextLine();
				validInput = true;
			} catch (InputMismatchException e) {
				Main.scanner.nextLine();
				String message = new InvalidInputException().getMessage();
				System.out.println(message);
				validInput = false;
			}
		} while (!validInput);
	}

	/**
	 * Adds the Amount-object 'other' to the Amount-object which calls this
	 * function.
	 * 
	 * @param other the Amount-object that get added
	 */
	public void add(Amount other) {
		if (measure.equals(other.measure)) {
			value = value + other.value;
		}
	}

	public String toString() {
		String string = "";

		switch (measure) {
		// Deciding if the amount has to be specified in kg, g, or mg
		case WEIGHT:
			if (value >= Measure.CONVERSION_FACTOR_kg_mg) {
				string = String.format("%5.1f %s", (double) value / Measure.CONVERSION_FACTOR_kg_mg, "kg");
			} else if (value >= Measure.CONVERSION_FACTOR_g_mg) {
				string = String.format("%5.1f %s", (double) value / Measure.CONVERSION_FACTOR_g_mg, " g");
			} else {
				string = String.format("%5.1f %s", (double) value, "mg");
			}
			break;
		// Deciding if the amount has to be specified in l, ml
		case VOLUME:
			if (value >= Measure.CONVERSION_FACTOR_l_ml) {
				string = String.format("%5.1f %s", (double) value / Measure.CONVERSION_FACTOR_l_ml, " l");
			} else {
				string = String.format("%5.1f %s", (double) value, "ml");
			}
			break;
		// If the amount is not given as weight or volume, then it's specified
		// without a specific unit. (e.g. 1 Banana, or 2 Eggs)
		default:
			string = String.format("%5.1f %s", (double) value, " x");
			break;
		}
		return string;
	}

	// Getters / Setters

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Measure getMeasure() {
		return measure;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

}
