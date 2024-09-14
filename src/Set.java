package src;
import java.util.ArrayList;

public abstract class Set {
    protected ArrayList<SetComponent> setComponents;
    protected int setDistance;
    
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

    abstract public SetComponent add();
    abstract public SetComponent add(int goalDistance);
    abstract public String title();

    protected void mergeIfSame(int latest){
        if (latest != 0){
            if (this.getSetComponent(latest).typeEquals(this.getSetComponent(latest-1))){
                this.getSetComponent(latest-1).setReps(this.getSetComponent(latest).getReps() + this.getSetComponent(latest-1).getReps());
                this.remove(latest);
            }
        }
    }
    
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
