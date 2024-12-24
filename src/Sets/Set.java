package src.Sets;
import java.util.ArrayList;
import src.*;
/*
 * @author: Brooke MacQuarrie
 * Abstract Set class that defines how sets should behave
 */
public abstract class Set {

    protected ArrayList<SetComponent> setComponents;
    protected int distance;
    protected int reps;
    protected double multiplier;
    protected String title;
    
    //Default constructor used by all sets. Creates an ArrayList to store set components
    public Set(int distance, double multiplier){
        this.setComponents = new ArrayList<SetComponent>();
        this.distance = distance;
        this.multiplier = multiplier;
    }

    public abstract Set generate();
    
    protected void mergeSimilarComponents(ArrayList<SetComponent> components){
        for (int i = 1; i < components.size(); i++){
            SetComponent component1 = components.get(i-1);
            SetComponent component2 = components.get(i);
            if (component1.getStroke() == component2.getStroke() && component1.getComponentDistance() == component2.getComponentDistance()){
                int newReps = component1.getReps() + component2.getReps();
                components.remove(i-1);
                components.remove(i-1);
                components.add(i-1,new SetComponent(newReps, component1.getComponentDistance(), component1.getStroke(), component1.getMultiplier()));
            }
        }
    }

    //Getters
    public int getDistance(){return this.distance;}
    public int getReps(){return this.reps;}
    public double getMultiplier(){return this.multiplier;}
    public String getTitle(){return this.title;}
    public int getNumberOfComponents(){return setComponents.size();}
    
    //Basic string representation of a set;
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (SetComponent component : setComponents) {
            if (component.getReps() == 1) {
                str.append(String.format("- %d %s on %s\n",
                                         component.getComponentDistance(),
                                         component.getStroke(),
                                         component.secondsToString()));
            } else {
                str.append(String.format("- %d x %d %s on %s\n", 
                                         component.getReps(), 
                                         component.getComponentDistance(), 
                                         component.getStroke(), 
                                         component.secondsToString()));
            }
        }
        return str.toString();
    }
}
