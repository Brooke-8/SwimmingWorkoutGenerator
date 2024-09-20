package src;
/*
 * @author Brooke MacQuarrie
 * RandomSet is a type of Set.
 * Used to make sets with entirely random set components
 */
public class RandomSet extends Set{
    /*
    * Adds a set component with a random stroke that aims for a specific distance
    * Calculates max possible reps under the distance, then randomly chooses a rep number
    * increases distance until the next increase is past the goal distance
    */
    public SetComponent add(int goalDistance, SetComponent c){
        int maxReps = goalDistance/25;
        int reps = c.randomReps(1,maxReps);
        int distance = 25;
        while (reps * distance < goalDistance){
            distance += 25;
        }
        c.setMultiplier(1.25);
        c.setComponentDistance(distance);
        c.randomStroke();
        return c;
    }
    //Title used for the random set
    public String title(int setNum){
        return ("Set " +setNum+" ("+this.setDistance + "m):\n");
    }
}
