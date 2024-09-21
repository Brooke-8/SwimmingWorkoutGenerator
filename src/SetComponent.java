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
    private int seconds; //Seconds is the number of seconds based on the distance, pace, and seconds multiplier.
    private double secondsMultiplier;

    //Constructor that sets default values for a set component
    public SetComponent(){
        componentDistance = Settings.DEFAULT_COMPONENT_DISTANCE;
        reps = Settings.DEFAULT_REPS;
        seconds = Settings.DEFAULT_SECONDS;
        stroke = Settings.DEFAULT_STROKE;
        secondsMultiplier = 1.0;
    }
    //Constructor that sets values to the given, and then calculates the seconds
    public SetComponent(int reps, int distance, Stroke stroke, double  multiplier){
        this.componentDistance = roundToNearest25(distance);
        this.stroke = stroke;
        this.reps = reps;
        this.secondsMultiplier = multiplier;
        updateSeconds();
    }

    //Getters 
    public int getComponentDistance(){return this.componentDistance;}
    public int getReps(){return this.reps;}
    public Stroke getStroke(){return this.stroke;}
    public int getSeconds(){return this.seconds;}
    public double getMultiplier(){return this.secondsMultiplier;}

    //Setters
    public void setComponentDistance(int componentDistance){
        this.componentDistance = roundToNearest25(componentDistance);
        updateSeconds();
    }
    public void setReps(int reps){
        this.reps = reps;
        updateSeconds();
    }
    public void setStroke(Stroke stroke){
        this.stroke = stroke;
        this.checkIM();
        updateSeconds();
    }
    public void setMultiplier(double multiplier){
        this.secondsMultiplier=multiplier;
        updateSeconds();
    }

    //equals method, checks if all variables in a set component are equal
    public boolean equals(Object o){
        if (this == o){return true;}
        if (o == null){return false;}
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
        if ((max-min) <= 0){
            max++;
        }
        this.componentDistance = roundToNearest25(new Random().nextInt(max - min) + min);
        checkIM();
        updateSeconds();
        return this.componentDistance;
    }

    //Sets the components stroke to be a random stroke, and then returns the stroke
    public Stroke randomStroke(){
        this.stroke = Stroke.randStroke();
        checkIM();
        updateSeconds();
        return this.stroke;
    }
    //Another version of random stroke that lets you define which strokes to include
    public Stroke randomStroke(Stroke[] include){
        this.stroke = Stroke.randStroke(include);
        checkIM();
        updateSeconds();
        return this.stroke;
    }

    //Sets the components reps to a random in between given values, then returns the reps
    public int randomReps(int min, int max){
        if ((max-min) <= 0){
            max++;
        }
        this.reps = new Random().nextInt(max-min)+min;
        updateSeconds();
        return this.reps;
    }

    /*
     * Calculates the number of seconds the set should be paced to.
     * Takes the predefined pace for each stroke and then divides by 100 to get m/s, multiplies by the distance travelled
     * and rounds to the nearest 5
     */
    private int calculateSeconds(){
        int pace = this.getStroke().pace;
        int distance = this.componentDistance;
        int seconds = 5 * (int) Math.round(((pace*secondsMultiplier / 100.0) * distance) / 5);
        return seconds;
    } 
    private void updateSeconds() {
        this.seconds = calculateSeconds();
    }

    private int roundToNearest25(int distance){
        return (int)Math.round(distance/25.0)*25;
    }


    //Checks if the stroke is IM and than changes the distance
    private void checkIM(){
        if (this.stroke == Stroke.IM){
            int[] distanceIM = {100,200,300,400,800};
            this.componentDistance = distanceIM[new Random().nextInt(distanceIM.length)];
        }
    }

    //Takes the seconds and converts to a string that has minutes and seconds
    public String secondsToString(){
        int minutes = this.seconds/60;
        int s = seconds % 60;
        return String.format("%d:%02d", minutes, s);
    }
      
}

