package src;
import java.util.ArrayList;

public class Set {
    private ArrayList<SetComponent> setComponents;
    private int setDistance;
    
    public Set(){}

    public Set (int setDistance){
        this.setDistance = setDistance; 
    }

    public SetComponent addRandomSetComponent(){
        SetComponent c = new SetComponent();
        c.randomDistance(25,200);
        c.randomReps(1,16);
        c.randomStroke();
        setComponents.add(c);
        return c;
        
    }

}
