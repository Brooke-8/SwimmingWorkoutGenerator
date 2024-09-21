package src;

public class WarmupSet extends Set{

    public SetComponent add(int goalDistance, SetComponent c){
        if (this.setComponents.size() == 0){
            c.randomDistance(100, 400);
            c.setReps(1);
            Stroke[] starting = {Stroke.FREE,Stroke.BACK};
            c.randomStroke(starting);
        }
        else{
            c.randomDistance(25, goalDistance);
            c.setReps(1);
            c.randomStroke();
        }
        return c;
    }
    //Title used for a warmup
    public String title(int setNum){
        return ("Warm up ("+this.setDistance + "m):\n");
    }
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < setComponents.size();i++){
            SetComponent c = setComponents.get(i);
            str.append(c.getComponentDistance()*c.getReps() + " "+ c.getStroke().string +"\n");
        }
        return str.toString();
    }
}
