package src;
import java.util.Random;

/*
 * Defines the Stroke data type. Each stroke has a string version and a pace
 */
public enum Stroke {
    FREE("free",Settings.FREE_PACE),
    BACK("back",Settings.BACK_PACE),
    FLY("fly",Settings.FLY_PACE),
    BREAST("breast",Settings.BREAST_PACE),
    IM("IM",Settings.IM_PACE);

    public final int pace;
    public final String string;
    
    private Stroke(String string, int pace){
        this.pace = pace;
        this.string = string;
    }
    
    public static final int NUM_OF_STROKES = 5;

    //Returns a random stroke
    public static Stroke randStroke(){
        Random r = new Random();
        int s = r.nextInt(NUM_OF_STROKES)+1;

        switch(s){
            case 1: return FREE;
            case 2: return BACK;
            case 3: return FLY;
            case 4: return BREAST;
            case 5: return IM;
            default: return null;
        }
    }

    //Returns a random stroke from the included list
    public static Stroke randStroke(Stroke[] include){
        Random r = new Random();
        int s = r.nextInt(include.length);
        return include[s];
    }
}
