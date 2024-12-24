package src;
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

    //Constructor that sets values to the given, and then calculates the seconds
    public SetComponent(int reps, int distance, Stroke stroke, double  multiplier){
        this.componentDistance = DistanceUtilities.roundToNearest25(distance);
        this.stroke = stroke;
        this.reps = reps;
        this.secondsMultiplier = multiplier;
        this.seconds = calculateSeconds();
    }

    //Getters 
    public int getComponentDistance(){return this.componentDistance;}
    public int getReps(){return this.reps;}
    public int getTotalDistance(){return this.componentDistance*this.reps;}
    public Stroke getStroke(){return this.stroke;}
    public int getSeconds(){return this.seconds;}
    public double getMultiplier(){return this.secondsMultiplier;}

    /*
     * Calculates the number of seconds the set should be paced to.
     * Takes the predefined pace for each stroke and then divides by 100 to get m/s, multiplies by the distance traveled
     * and rounds to the nearest 5
     */
    private int calculateSeconds(){
        int pace = this.getStroke().getPace();
        int distance = this.componentDistance;
        int seconds = 5 * (int) Math.round(((pace*secondsMultiplier / 100.0) * distance) / 5);
        return seconds;
    } 

    //Takes the seconds and converts to a string that has minutes and seconds
    public String secondsToString(){
        int minutes = this.seconds/60;
        int s = seconds % 60;
        return String.format("%d:%02d", minutes, s);
    }
      
}

