package src;
import java.util.ArrayList;
/*
 * @author: Brooke MacQuarrie
 * Abstract Set class that defines how sets should behave
 */
public abstract class Set {
    protected ArrayList<SetComponent> setComponents;
    protected int setDistance;
    
    //Default constructor used by all sets. Creates an ArrayList to store set components
    public Set(){
        ArrayList<SetComponent> setComponents = new ArrayList<SetComponent>();
        this.setComponents = setComponents;
    }

    //Getters
    public ArrayList<SetComponent> getSetComponents(){return this.setComponents;}
    public int getSetDistance(){return this.setDistance;}
    public SetComponent getSetComponent(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= this.setComponents.size()){
            throw new IndexOutOfBoundsException();
        }
        return this.setComponents.get(index);
    }

    //Template Method for abstract add method
    public SetComponent addComponent(int goalDistance){
        SetComponent c = new SetComponent();
        c = add(goalDistance,c); //Uses SetComponent add method
        this.setComponents.add(c); //uses ArrayList add method
        this.mergeIfSame(setComponents.size()-1);
        setDistance += c.getComponentDistance()*c.getReps();
        return c;
    }
    //Abstract methods
    abstract protected SetComponent add(int goalDistance, SetComponent c);
    abstract public String title(int setNum);

    //Tool method used to merge two set components when adding if they share a distance and stroke.
    protected void mergeIfSame(int latest){
        if (latest != 0){
            if (this.getSetComponent(latest).typeEquals(this.getSetComponent(latest-1))){
                this.getSetComponent(latest-1).setReps(this.getSetComponent(latest).getReps() + this.getSetComponent(latest-1).getReps());
                this.remove(latest);
            }
        }
    }

    //Method used to remove a set component at a given index (Note remove for arraylists shifts elements so there is no empty space)
    public SetComponent remove(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= this.setComponents.size()){
            throw new IndexOutOfBoundsException();
        }
        SetComponent c = this.setComponents.get(index);
        this.setComponents.remove(index);
        return c;
    }

    //Basic string representation of a set;
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < setComponents.size();i++){
            SetComponent c = setComponents.get(i);
            str.append(c.getReps() + " x " + c.getComponentDistance() + " "+ c.getStroke().string +" @ "+ c.secondsToString()+"\n");
        }
        return str.toString();
    }



}
