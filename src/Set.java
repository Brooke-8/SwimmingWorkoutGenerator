package src;
import java.util.ArrayList;

public class Set {
    protected ArrayList<SetComponent> setComponents;
    private int setDistance;
    
    public Set(){
        ArrayList<SetComponent> setComponents = new ArrayList<SetComponent>();
        this.setComponents = setComponents;
    }

    public Set (int setDistance){
        ArrayList<SetComponent> setComponents = new ArrayList<SetComponent>();
        this.setComponents = setComponents;
        this.setDistance = setDistance; 
    }

    public ArrayList<SetComponent> getSetComponents(){
        return this.setComponents;
    }
    public int getSetDistance(){
        return this.setDistance;
    }

    public void add(SetComponent component){
        setComponents.add(component);
    }

    public SetComponent addRandomSetComponent(){
        SetComponent c = new SetComponent();
        c.randomDistance(25,200);
        c.randomReps(1,16);
        c.randomStroke();
        setComponents.add(c);
        setDistance += c.getComponentDistance()*c.getReps();
        return c;
    }
    public SetComponent addRandomSetComponent(int goalDistance){
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
        setDistance += c.getComponentDistance()*c.getReps();
        return c;
        
    }
        
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < setComponents.size();i++){
            SetComponent c = setComponents.get(i);
            str.append(c.getReps() + " x " + c.getComponentDistance() + " "+ c.getStroke().string +" @ "+ c.secondsToString()+"\n");
        }
        return str.toString();
    }



}
