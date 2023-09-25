package weeklyMealPlanner;

public class InvalidInputException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Thrown to indicate, that the user input is not valid for the given options.
	 */
	public InvalidInputException() {
		super("Invalid Input! Try again!");
	}
}
