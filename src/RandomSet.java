package src;
/*
 * @author Brooke MacQuarrie
 * RandomSet is a type of Set.
 * Used to make sets with entirely random set components
 */
public class RandomSet extends Set{

    //Adds a set component with 25 <= distance <= 200; 1 <= reps <= 16, and random stroke
    public SetComponent add(){
        SetComponent c = new SetComponent();
        c.randomDistance(25,200);
        c.randomReps(1,16);
        c.randomStroke();
        setComponents.add(c);
        this.mergeIfSame(setComponents.size()-1);
        setDistance += c.getComponentDistance()*c.getReps();
        return c;
    }

    /*
    * Adds a set of random stroke that aims for a specific distance
    * Calculates max possible reps under the distance, then randomly chooses a rep number
    * in
    */
    public SetComponent add(int goalDistance){
        SetComponent c = new SetComponent();
        int maxReps = goalDistance/25;
        int reps = c.randomReps(1,maxReps);
        int distance = 25;
        while (reps * distance < goalDistance){
            distance += 25;
        }
        c.setComponentDistance(distance);
        c.randomStroke();
        setComponents.add(c);
        this.mergeIfSame(setComponents.size()-1);
        setDistance += c.getComponentDistance()*c.getReps();
        return c;
    }
    public String title(){
        return "Set";
    }
}
