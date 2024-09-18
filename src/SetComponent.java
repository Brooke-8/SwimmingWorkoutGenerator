package src;
import java.util.Random;
/*
 * @author Brooke MacQuarrie
 * Set Components are lines in a set that contain information on the reps, stroke, distance, and time.
 */
public class SetComponent {
    private int componentDistance;
    private int reps;
    private Stroke stroke;
    private int seconds;

    //Constructor that sets default values for a set component
    public SetComponent(){
        componentDistance = Settings.DEFAULT_COMPONENT_DISTANCE;
        reps = Settings.DEFAULT_REPS;
        seconds = Settings.DEFAULT_SECONDS;
        stroke = Settings.DEFAULT_STROKE;
    }
    //Constructor that sets values to the given, and then calculates the seconds
    public SetComponent(int reps, int distance, Stroke stroke){
        this.componentDistance = distance;
        this.stroke = stroke;
        this.reps = reps;
        this.seconds = this.calculateSeconds();
    }

    //Getters 
    public int getComponentDistance(){return this.componentDistance;}
    public int getReps(){return this.reps;}
    public Stroke getStroke(){return this.stroke;}
    public int getSeconds(){return this.seconds;}

    //Setters
    public void setComponentDistance(int componentDistance){this.componentDistance = componentDistance;}
    public void setReps(int reps){this.reps = reps;}
    public void setStroke(Stroke stroke){this.stroke = stroke;}
    public void setSeconds(int seconds){this.seconds=seconds;}


    //equals method, checks if all variables in a set component are equal
    public boolean equals(Object o){
        if (this == o){return true;}
        if (this == null || o == null){return false;}
        if (this.getClass() != o.getClass()){return false;}
        SetComponent c = (SetComponent)o;
        return (this.componentDistance == c.componentDistance &&
                this.reps == c.reps &&
                this.stroke == c.stroke &&
                this.seconds == c.seconds);
    }
    //only checks if the distance and stroke of the components are equal
    public boolean typeEquals(Object o){
        if (this == o){return true;}
        if (this == null || o == null){return false;}
        if (this.getClass() != o.getClass()){return false;}
        SetComponent c = (SetComponent)o;
        return (this.componentDistance == c.componentDistance &&
                this.stroke == c.stroke);
    }

    //Sets the components distance to be a random multiple of 25 between the given values, then returns the distance
    public int randomDistance(int min,int max){
        Random r = new Random();
        int d = (int)Math.round((r.nextInt(max-min)+min)/25)*25;
        this.componentDistance = d;
        this.seconds = this.calculateSeconds();
        return d;
    }

    //Sets the components stroke to be a random stroke, and then returns the stroke
    public Stroke randomStroke(){
        Stroke s = Stroke.randStroke();
        this.stroke = s;
        this.seconds = this.calculateSeconds();
        return s;
    }
    //Another version of random stroke that lets you define which strokes to include
    public Stroke randomStroke(Stroke[] include){
        Stroke s = Stroke.randStroke(include);
        this.stroke = s;
        this.seconds = this.calculateSeconds();
        return s;
    }

    //Sets the components reps to a random in between given values, then returns the reps
    public int randomReps(int min, int max){
        Random r= new Random();
        int reps = r.nextInt(max-min)+min;
        this.reps = reps;
        this.seconds = this.calculateSeconds();
        return reps;
    }

    /*
     * Calculates the number of seconds the set should be paced to.
     * Takes the predefined pace for each stroke and then divides by 100 to get m/s, multiplies by the distance travelled
     * and rounds to the nearest 5
     */
    private int calculateSeconds(){
        int pace = this.getStroke().pace;
        int distance = this.componentDistance;
        int seconds = 5 * (int) Math.round(((pace / 100.0) * distance) / 5);
        return seconds;
    } 

    //Takes the seconds and converts to a string that has minutes and seconds
    public String secondsToString(){
        int t = this.seconds;
        int minutes = Math.floorDiv(t, 60);
        int s = t - (60*minutes);
        if (s <=9){
            return (minutes+ ":"+s+"0");
        }
        return (minutes+":"+s);
    }
      
}

