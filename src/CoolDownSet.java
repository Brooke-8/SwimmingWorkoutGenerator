package src;
/*
 * @author Brooke MacQuarrie
 * CooldownSet is a Set type that is for cooldown
 * Has no times or repetitions and only includes FREE, BACK, BREAST.
 */
public class CooldownSet extends Set{

     //Adds a set component that abides by cooldown set rules
    public SetComponent add(){
        SetComponent s = new SetComponent();
        s.randomDistance(50, 200);
        s.setReps(1);
        Stroke[] slowStrokes = {Stroke.FREE,Stroke.BACK,Stroke.BREAST};
        s.randomStroke(slowStrokes);
        this.setComponents.add(s);
        this.mergeIfSame(setComponents.size()-1);
        setDistance += s.getComponentDistance();
        return s;
    }
    //Same as add() but only adds components up to a certain distance
    public SetComponent add(int goalDistance){
        SetComponent s = new SetComponent();
        s.randomDistance(25, goalDistance);
        s.setReps(1);
        Stroke[] slowStrokes = {Stroke.FREE,Stroke.BACK,Stroke.BREAST};
        s.randomStroke(slowStrokes);
        this.setComponents.add(s);
        this.mergeIfSame(setComponents.size()-1);
        setDistance += s.getComponentDistance();
        return s;
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
