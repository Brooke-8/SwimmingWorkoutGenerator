package src;
import java.util.ArrayList;
/*
 * @author: Brooke MacQuarrie
 * Abstract Set class that defines how sets should behave
 */
public abstract class Set {
    protected ArrayList<SetComponent> setComponents;
    protected int setDistance;
    protected int setSize;
    
    //Default constructor used by all sets. Creates an ArrayList to store set components
    public Set(){
        ArrayList<SetComponent> setComponents = new ArrayList<SetComponent>();
        this.setComponents = setComponents;
        this.setDistance = 0;
        this.setSize = 0;
    }

    //Getters
    public int getSetDistance(){return this.setDistance;}
    public int getSetSize(){return this.setSize;}
    public SetComponent getSetComponent(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= this.setComponents.size()){
            throw new IndexOutOfBoundsException("Invalid index for Set");
        }
        return this.setComponents.get(index);
    }

    //Template Method for abstract add method
    public SetComponent addComponent(int goalDistance){
        SetComponent component = new SetComponent();

        component = add(goalDistance,component); //Uses abstract add method
        goalCheckAndAdjust(goalDistance, component);

        this.setComponents.add(component); //adds component to the list
        if(setComponents.size() >=2){
            mergeIfSame(setComponents.size()-1);
        };

        this.setDistance += component.getTotalDistance();
        this.setSize++;
        return component;
    }

    //Abstract methods
    abstract protected SetComponent add(int goalDistance, SetComponent c);
    abstract public String title(int setNum);

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



}
