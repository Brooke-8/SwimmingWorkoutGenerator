package src;

public class RandomSet extends Set{

    public SetComponent add(){
        SetComponent c = new SetComponent();
        c.randomDistance(25,200);
        c.randomReps(1,16);
        c.randomStroke();
        setComponents.add(c);
        this.mergeIfSame(setComponents.size()-1);
        setDistance += c.getComponentDistance()*c.getReps();
        return c;
    }

    public SetComponent add(int goalDistance){
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
        this.mergeIfSame(setComponents.size()-1);
        setDistance += c.getComponentDistance()*c.getReps();
        return c;
    }
    public String title(){
        return "Set";
    }
}
