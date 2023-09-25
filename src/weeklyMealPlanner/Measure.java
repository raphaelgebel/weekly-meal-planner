package weeklyMealPlanner;

public enum Measure {

	WEIGHT, VOLUME, NONE;

	// Defining factors for conversion between different units
	public static final int CONVERSION_FACTOR_kg_mg = 1000000;
	public static final int CONVERSION_FACTOR_g_mg = 1000;
	public static final int CONVERSION_FACTOR_l_ml = 1000;
	public static final int CONVERSION_FACTOR_tbs_ml = 15;
	public static final int CONVERSION_FACTOR_tsp_ml = 5;

}