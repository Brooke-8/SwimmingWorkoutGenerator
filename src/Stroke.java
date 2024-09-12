package src;
import java.util.Random;
public enum Stroke {
    FREE,BACK,FLY,BREAST;

    public static final int NUM_OF_STROKES = 4;

    public String getStroke(){
        switch(this){
            case FREE: return "free";
            case BACK: return "back";
            case FLY: return "fly";
            case BREAST: return "breast";
            default: return null;
        }
    }
    public static Stroke randStroke(){
        Random r = new Random();
        int s = r.nextInt(NUM_OF_STROKES);
        switch(s){
            case 1: return FREE;
            case 2: return BACK;
            case 3: return FLY;
            case 4: return BREAST;
            default: return null;
        }
    }
}
