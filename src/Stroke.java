package src;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import static src.Stroke.Descriptor.*;

/*
 * Defines the Stroke data type. Each stroke has a string version and a pace
 */
public enum Stroke {
    FREE("Free",Settings.FREE_PACE,BLANK, PULL,FINS,PADDLES,SNORKEL,DRILL),
    BACK("Back",Settings.BACK_PACE,BLANK,PULL,FINS,PADDLES,SNORKEL,DRILL),
    FLY("Fly",Settings.FLY_PACE,BLANK,PULL,FINS,PADDLES,SNORKEL,DRILL),
    BREAST("Breast",Settings.BREAST_PACE,BLANK,PULL,PADDLES,SNORKEL,DRILL),
    IM("IM",Settings.IM_PACE,BLANK,SNORKEL,FINS,DRILL),
    KICK("Kick",100,ON_FRONT,BLANK, ON_BACK,BOARD,SNORKEL,FINS),
    FLUTTER("Flutter kick",90, BLANK,ON_FRONT,ON_BACK,BOARD,SNORKEL,FINS),
    WHIPKICK("Whip kick", 100,BLANK,ON_FRONT,ON_BACK,BOARD,SNORKEL,FINS),
    DOLPHINKICK("Dolphin kick",90,BLANK,ON_FRONT, ON_BACK,BOARD,SNORKEL,FINS);

    private final int pace;
    private final String name;
    private final List<Descriptor> descriptors;
    
    private Stroke(String name, int pace, Descriptor...descriptors){
        this.pace = pace;
        this.name = name;
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
        Stroke[] strokes = values();
        return strokes[new Random().nextInt(strokes.length)];
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
