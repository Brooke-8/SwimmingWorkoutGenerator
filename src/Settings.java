package src;

public class Settings {
    public static final String DOCUMENT_NAME = "Workout.pdf";
    public static final String SAVE_LOCATION = "";

    //Set paces to the number of seconds it takes for your fastest 100m.
    public static final int FREE_PACE = 60;
    public static final int BACK_PACE = 70;
    public static final int FLY_PACE = 65;
    public static final int BREAST_PACE = 80;

    /*
     * Workout Format
     * "RANDOMSET" - Completely random set
     * "COOLDOWN" - Cooldown set with no times or reps; only includes cooldown strokes.
     */
    public static final String[][] FORMAT = 
    {
        {"RANDOMSET","5"},
        {"RANDOMSET","5"},
        {"RANDOMSET","5"},
        {"COOLDOWN","3"}

    };

}
