package src;
import java.util.ArrayList;
/*
 * @author: Brooke MacQuarrie
 * Abstract Set class that defines how sets should behave
 */
public class Set {

    private ArrayList<SetComponent> setComponents;
    private int distance;
    private int reps;
    private double multiplier;
    private String title;
    
    //Default constructor used by all sets. Creates an ArrayList to store set components
    private Set(SetBuilder builder){
        this.setComponents = builder.setComponents;
        this.distance = builder.distance;
        this.reps = builder.reps;
        this.multiplier = builder.multiplier;
    }

    public abstract static class SetBuilder{
        protected ArrayList<SetComponent> setComponents;
        protected int distance;
        protected int targetDistance;
        protected int reps;
        protected double multiplier;
        protected String title;

        public SetBuilder(int targetDistance, double multiplier){
            this.targetDistance = targetDistance;
            this.multiplier = multiplier;
            this.reps = 0;
            this.distance = 0;
            this.setComponents = new ArrayList<SetComponent>();
        }
        public abstract SetBuilder addComponent();
        
        public Set build(){
            return new Set(this);
        }
    }

    //Getters
    public int getDistance(){return this.distance;}
    public int getReps(){return this.reps;}
    public double getMultiplier(){return this.multiplier;}
    public String getTitle(){return this.title;}

    /* 
    //Tool method used to merge two set components when adding if they share a distance and stroke.
    protected void mergeIfSame(int index){
        if (index <= 0 || index >= this.setComponents.size()){
            throw new IndexOutOfBoundsException("Invalid index for Set");
        }
        SetComponent current = this.getSetComponent(index);
        SetComponent previous = this.getSetComponent(index - 1);
        if (current.typeEquals(previous)) {     
            this.remove(index); // Remove the merged component 
            previous.setReps(current.getReps() + previous.getReps()); // Merge the components
            setDistance += current.getTotalDistance(); //Re add the distance removed by remove
        }
    }


    private void goalCheckAndAdjust(int goalDistance, SetComponent c){
        int closeness = c.getTotalDistance()-goalDistance;
        while (closeness > 100){
            if (c.getReps()==1){
                c.setComponentDistance((int)Math.max(25,c.getComponentDistance()/2));
            } else {
                c.setReps(c.getReps()-1);
            }
            closeness = c.getTotalDistance()-goalDistance;
        }
        while (closeness < -100){
            c.setReps(c.getReps()+1);
            closeness = c.getTotalDistance()-goalDistance;
        }
        
    }

    //Method used to remove a set component at a given index (Note remove for arraylists shifts elements so there is no empty space); returns null when empty
    public SetComponent remove(int index) throws IndexOutOfBoundsException{
        if (this.setComponents.size() == 0){
            return null;
        }
        if (index < 0 || index >= this.setComponents.size()){
            throw new IndexOutOfBoundsException("Invalid index for removal.");
        }
        SetComponent removed = this.setComponents.remove(index);
        this.setDistance -= removed.getTotalDistance();
        this.setSize--;
        return removed;
    }

    public static boolean distanceChecker(Set set){
        int runningTotal = 0;
        for (int i = 0; i < set.setComponents.size();i++){
            runningTotal += set.getSetComponent(i).getTotalDistance();
        }
        return (runningTotal == set.setDistance);
    }
    */
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


    //Random Set Builder
    public static class RandomSetBuilder extends SetBuilder{
        public RandomSetBuilder(int targetDistance, double multiplier){
            super(targetDistance, multiplier);
            this.reps = 1;
            this.title = "RandomSet";
        }
        public SetBuilder addComponent(){
            int componentTargetDistance = targetDistance/reps;
            SetComponent component = new SetComponent();
            int maxReps = componentTargetDistance/25;
            int componentReps = component.randomReps(1,maxReps);
            int componentDistance = 25;
            while (componentReps * componentDistance < componentTargetDistance){
                componentDistance += 25;
            }
            component.setMultiplier(multiplier);
            component.setComponentDistance(componentDistance);
            component.randomStroke();
            this.distance += component.getTotalDistance();
            setComponents.add(component);
            return this;
        }
    }

    //Warmup Set Builder
    public static class WarmupSetBuilder extends SetBuilder{
        public WarmupSetBuilder(int targetDistance, double multiplier){
            super(targetDistance, multiplier);
            this.reps = 1;
            this.title = "WarmupSet";
        }
        public SetBuilder addComponent(){
            int componentTargetDistance = targetDistance/reps;
            SetComponent component = new SetComponent();
            if (this.setComponents.size() == 0){
                component.randomDistance(100, 400);
                component.setReps(1);
                Stroke[] starting = {Stroke.FREE,Stroke.BACK};
                component.randomStroke(starting);
            }
            else{
                component.randomDistance(25, componentTargetDistance);
                component.setReps(1);
                component.randomStroke();
            }
            this.distance += component.getTotalDistance();
            setComponents.add(component);
            return this;
        }
    }

    //Cooldown Set Builder
    public static class CooldownSetBuilder extends SetBuilder{
        public CooldownSetBuilder(int targetDistance, double multiplier){
            super(targetDistance, multiplier);
            this.reps = 1;
            this.title = "CooldownSet";
        }
        public SetBuilder addComponent(){
            int componentTargetDistance = targetDistance/reps;
            SetComponent component = new SetComponent();
            component.randomDistance(25, componentTargetDistance);
            component.setReps(1);
            Stroke[] slowStrokes = {Stroke.FREE,Stroke.BACK,Stroke.BREAST};
            component.randomStroke(slowStrokes);
            this.distance += component.getTotalDistance();
            setComponents.add(component);
            return this;
        }
    }

}
