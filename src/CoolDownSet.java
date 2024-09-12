package src;

public class CoolDownSet extends Set{
    public SetComponent addCooldownComponent(){
        SetComponent s = new SetComponent();
        s.randomDistance(50, 200);
        Stroke[] slowStrokes = {Stroke.FREE,Stroke.BACK,Stroke.BREAST};
        s.randomStroke(slowStrokes);
        this.setComponents.add(s);
        return s;
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
