package src;
/*
 * @author Brooke MacQuarrie
 * CooldownSet is a Set type that is for cooldown
 * Has no times or repetitions and only includes FREE, BACK, BREAST.
 */
public class CooldownSet extends Set{

     //Adds a set component that abides by cooldown set rules
    public SetComponent add(int goalDistance, SetComponent c){
        c.randomDistance(25, goalDistance);
        c.setReps(1);
        Stroke[] slowStrokes = {Stroke.FREE,Stroke.BACK,Stroke.BREAST};
        c.randomStroke(slowStrokes);
        return c;
    }
    //Returns the title to be used for the set.
    public String title(){
        return "Cooldown";
    }
    //Returns string of cooldown set, doesn't include seconds or reps.
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < setComponents.size();i++){
            SetComponent c = setComponents.get(i);
            str.append(c.getComponentDistance() + " "+ c.getStroke().string +"\n");
        }
        return str.toString();
    }
}
