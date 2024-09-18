package src;
/*
 * @author Brooke MacQuarrie
 * Used to make a set with a given setType, number of components, and perferred distance
 */
public class SetCreator {
    public SetCreator(){}
    public Set makeSet(String setType,int numberOfComponents, int distance){
        Set set;
        switch(setType){
            case "RANDOMSET": set = new RandomSet(); break;
            case "COOLDOWN": set = new CooldownSet(); break;
            default: set = new RandomSet();
        }
        for (int j = 0; j <numberOfComponents; j++){
            set.add(distance/numberOfComponents);
        }
        return (set);
    }

}
