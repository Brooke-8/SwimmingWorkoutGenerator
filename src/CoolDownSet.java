package src;

public class CooldownSet extends Set{

    public SetComponent add(){
        SetComponent s = new SetComponent();
        s.randomDistance(50, 200);
        s.setReps(1);
        Stroke[] slowStrokes = {Stroke.FREE,Stroke.BACK,Stroke.BREAST};
        s.randomStroke(slowStrokes);
        this.setComponents.add(s);
        this.mergeIfSame(setComponents.size()-1);
        setDistance += s.getComponentDistance();
        return s;
    }

    public SetComponent add(int goalDistance){
        SetComponent s = new SetComponent();
        s.randomDistance(25, goalDistance);
        s.setReps(1);
        Stroke[] slowStrokes = {Stroke.FREE,Stroke.BACK,Stroke.BREAST};
        s.randomStroke(slowStrokes);
        this.setComponents.add(s);
        this.mergeIfSame(setComponents.size()-1);
        setDistance += s.getComponentDistance();
        return s;
    }
    public String title(){
        return "Cooldown";
    }
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < setComponents.size();i++){
            SetComponent c = setComponents.get(i);
            str.append(c.getComponentDistance() + " "+ c.getStroke().string +"\n");
        }
        return str.toString();
    }
}
