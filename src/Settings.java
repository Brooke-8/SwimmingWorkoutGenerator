package src;

public class Settings {
    public static final String DOCUMENT_NAME = "Workout["+java.time.LocalDate.now()+ "].pdf";
    public static final String DOCUMENT_TITLE = "Workout ["+java.time.LocalDate.now()+ "]";
    public static final String SAVE_LOCATION = "";

    //Set paces to the number of seconds it takes for your fastest 100m.
    public static final int FREE_PACE = 60;
    public static final int BACK_PACE = 70;
    public static final int FLY_PACE = 65;
    public static final int BREAST_PACE = 80;
    public static final int IM_PACE = 75;

    /*
     * Workout Format
     * "RANDOMSET" - Completely random set
     * "COOLDOWN" - Cooldown set with no times or reps; only includes cooldown strokes.
     */
    public static final int WORKOUT_DISTANCE = 5000;
    public static final String[][] FORMAT = 
    {
        {"WARMUP","1000"},
        {"RANDOMSET","1000"},
        {"RANDOMSET","1000"},
        {"RANDOMSET","1000"},
        {"COOLDOWN","1000"}

    };
}
