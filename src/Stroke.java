package src;
import java.util.*;
import static src.Stroke.Descriptor.*;

/*
 * Defines the Stroke data type. Each stroke has a string version and a pace
 */
public enum Stroke {
    FREE    ("Free",Settings.FREE_PACE,     2.1f,    BLANK,PULL,FINS,PADDLES,DRILL,SNORKEL),
    BACK    ("Back",Settings.BACK_PACE,     0.8f,    BLANK,PULL,FINS,PADDLES,DRILL),
    FLY     ("Fly",Settings.FLY_PACE,       0.9f,    BLANK,PULL,FINS,PADDLES,DRILL,SNORKEL),
    BREAST  ("Breast",Settings.BREAST_PACE, 0.5f,    BLANK,PULL,PADDLES,DRILL,SNORKEL),
    IM      ("IM",Settings.IM_PACE,         0.7f,    BLANK,PULL,FINS,PADDLES,DRILL),
    KICK    ("Kick",100,                    0.6f,    BLANK,ON_FRONT,ON_BACK,BOARD,SNORKEL,FINS),
    FLUTTER ("Flutter kick",90,             0.2f,    BLANK,ON_FRONT,ON_BACK,BOARD,SNORKEL,FINS),
    WHIP    ("Whip kick", 100,              0.2f,    BLANK,ON_FRONT,ON_BACK,BOARD,SNORKEL,FINS),
    DOLPHIN ("Dolphin kick",90,             0.2f,    BLANK,ON_FRONT,ON_BACK,BOARD,SNORKEL,FINS),
    DBACK   ("Double arm back", 100,        0.01f,   BLANK);

    private final int pace;
    private final String name;
    private final float weight;
    private final List<Descriptor> descriptors;
    
    private Stroke(String name, int pace, float weight, Descriptor...descriptors){
        this.pace = pace;
        this.name = name;
        this.weight = weight;
        this.descriptors = Arrays.asList(descriptors);
    } 

    public int getPace(){return pace;}
    public String getName(){return name;}
    public String toString(){
        StringBuilder builder = new StringBuilder(name);
        if (!descriptors.isEmpty()){
            String descriptor = descriptors.get(new Random().nextInt(descriptors.size())).getDescription();
            if (!descriptor.isEmpty()) builder.append(" ");
            builder.append(descriptor);
        }
        return builder.toString();
    }

    public static Stroke randStroke(){
        List<Stroke> strokes = Arrays.asList(values());
        Collections.shuffle(strokes);
        float totalWeight = 0f;
        for (Stroke s : strokes){
            totalWeight += s.weight;
        }
        float rand = new Random().nextFloat()*totalWeight;
        for (Stroke s : strokes){
            rand -= s.weight;
            if (rand < 0){
                return s;
            }
        }
        return null;
        
    }
    public static Stroke randStroke(Stroke[] include){
        return include[new Random().nextInt(include.length)];
    }

    public enum Descriptor{
        BLANK(""),
        ON_FRONT("on front"),
        ON_BACK("on back"),
        ON_SIDE("on side"),
        BOARD("with board"),
        PADDLES("with paddles"),
        FINS("with fins"),
        SNORKEL("with snorkel"),
        PULL ("pull"),
        DRILL("drill");

        private final String description;
        Descriptor(String description){
            this.description = description;
        }
        public String getDescription(){
            return description;
        }
    }
}
