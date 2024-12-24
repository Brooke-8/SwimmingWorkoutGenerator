package src;
import java.util.ArrayList;

/*
 * @author Brooke MacQuarrie
 * Used to make a set with a given setType, number of components, and perferred distance
 */
public class Workout {
    private int distance;
    private String title;
    private ArrayList<Set> sets;

    private Workout(WorkoutBuilder workout){
        this.distance = workout.currentDistance;
        this.title = workout.title;
        this.sets = workout.sets;
    }

    public int getDistance() {return distance;}
    public String getTitle() {return title;}
    public ArrayList<Set> getSets(){return sets;}

    public static class WorkoutBuilder{
        private int currentDistance;
        private int remainingDistance;
        private String title;
        private ArrayList<Set> sets;

        public WorkoutBuilder(int distance, String title){
            this.remainingDistance = distance;
            this.currentDistance = 0;
            this.title = title;
            this.sets = new ArrayList<Set>();
        }

        private void updateDistance(int addedDistance){
            currentDistance += addedDistance;
            remainingDistance -= addedDistance;
        }
        
        public WorkoutBuilder addSet(Set set){
            if (set.getDistance() <= remainingDistance) {
                sets.add(set);
                updateDistance(set.getDistance());
               
            } else {
                System.err.println("Failed to add set "+ set.getTitle() + ", reached given distance");
            }
            return this;
        }
        public void addSet(String setType, int setDistance){
            Set newSet = makeSet(setType, setDistance);
            this.addSet(newSet);
        }

        public Workout build(){return new Workout(this);}
    }

    public static Set makeSet(String setType,int setDistance){
        Set set;
        switch(setType){
            case "RANDOMSET": set = new Set.RandomSet(setDistance,1); break;
            case "COOLDOWN": set = new Set.CooldownSet(setDistance,1); break;
            case "WARMUP": set = new Set.WarmupSet(setDistance,1); break;
            default: set = new Set.RandomSet(setDistance,1);
        }
        return (set.generate());
    }
}
