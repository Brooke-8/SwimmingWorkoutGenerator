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
            case "WARMUP": set = new WarmupSet(); break;
            default: set = new RandomSet();
        }
        int distancePerComponent = distance/numberOfComponents;
        for (int j = 0; j <numberOfComponents; j++){
            set.addComponent(distancePerComponent);
            //Adjusts distancePerComponent to based on how much of the distance has been used and how many components are left
            distancePerComponent = (distance - set.setDistance)/(numberOfComponents-j+1); 
            //Exits loop if there is not enough distance left to add another component;
            if (distancePerComponent < 25){
                break;
            }
        }
        return (set);
    }

}
