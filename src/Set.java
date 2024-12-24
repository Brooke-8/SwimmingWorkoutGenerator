package src;
import java.util.ArrayList;
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

    //Getters
    public int getDistance(){return this.distance;}
    public int getReps(){return this.reps;}
    public double getMultiplier(){return this.multiplier;}
    public String getTitle(){return this.title;}
    public int getNumberOfComponents(){return setComponents.size();}
    
    //Basic string representation of a set;
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (SetComponent component : setComponents){
            str.append(String.format("%d x %d %s @ %s\n", 
                                     component.getReps(), 
                                     component.getComponentDistance(), 
                                     component.getStroke().string, 
                                     component.secondsToString()));
        }
        return str.toString();
    }

    public static class RandomSet extends Set{
        public RandomSet(int distance, double multiplier){
            super(distance, multiplier);
            this.reps = 1;
            this.title = "Random";
        }
        public Set generate(){
            ArrayList<Integer> componentDistances = DistanceUtilities.separateTo25s(distance);
            for (int d : componentDistances){
                int componentDistance = DistanceUtilities.random25Factor(d);
                int componentReps = d/componentDistance;
                Stroke componentStroke = Stroke.randStroke();
                SetComponent component = new SetComponent(componentReps, componentDistance,componentStroke, multiplier);
                this.setComponents.add(component);
            }
            return this;
        }
    }

    public static class WarmupSet extends Set{
        public WarmupSet(int distance, double multiplier){
            super(distance, multiplier);
            this.reps = 1;
            this.title = "Warm Up";
        }
        public Set generate(){
            ArrayList<Integer> componentDistances = DistanceUtilities.separateTo25s(distance);
            for (int d : componentDistances){
                int componentDistance = DistanceUtilities.random25Factor(d);
                int componentReps = d/componentDistance;
                Stroke[] warmupStrokes = {Stroke.FREE,Stroke.BACK,Stroke.BREAST, Stroke.IM};
                Stroke componentStroke = Stroke.randStroke(warmupStrokes);
                SetComponent component = new SetComponent(componentReps, componentDistance,componentStroke, multiplier);
                this.setComponents.add(component);
            }
            return this;
        }
        
    }

    public static class CooldownSet extends Set{
        public CooldownSet(int distance, double multiplier){
            super(distance, multiplier);
            this.reps = 1;
            this.title = "Cooldown";
        }
        public Set generate(){
            ArrayList<Integer> componentDistances = DistanceUtilities.separateTo25s(distance);
            for (int d : componentDistances){
                int componentDistance = DistanceUtilities.random25Factor(d);
                int componentReps = d/componentDistance;
                Stroke[] slowStrokes = {Stroke.FREE,Stroke.BACK,Stroke.BREAST};
                Stroke componentStroke = Stroke.randStroke(slowStrokes);
                SetComponent component = new SetComponent(componentReps, componentDistance,componentStroke, multiplier);
                this.setComponents.add(component);
            }
            return this;
        }
    }
}
